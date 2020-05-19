DROP VIEW IF EXISTS appmobile.vista_proyectos;
DROP FUNCTION IF EXISTS appmobile.f_debeir_obra_alimentacion(p_intcodigoobra integer);
DROP FUNCTION IF EXISTS appmobile.f_obra_contratista_obra(p_intcodigoobra integer);

CREATE FUNCTION appmobile.f_debeir_obra_alimentacion(p_intcodigoobra integer) RETURNS NUMERIC
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_total_obra NUMERIC;    
    v_debeir_total_obra NUMERIC;  
BEGIN

    SELECT numvaltotobra
    INTO v_total_obra
    FROM obra
    WHERE intcodigoobra = p_intcodigoobra;

    SELECT  CASE
		WHEN v_total_obra <> 0::numeric THEN 100::numeric * "f_proyecto_deberia_estar(p_intcodigoobra int4)"(p_intcodigoobra) / v_total_obra
		ELSE 0::numeric
	    END AS deberiaestar
    INTO v_debeir_total_obra;

    SELECT COALESCE(v_debeir_total_obra,0) 
    INTO v_debeir_total_obra;
     
    RETURN  v_debeir_total_obra;
END
$$;

ALTER FUNCTION appmobile.f_debeir_obra_alimentacion(p_intcodigoobra integer) OWNER TO cobra;

CREATE FUNCTION appmobile.f_obra_contratista_obra(p_intcodigoobra integer) RETURNS CHARACTER VARYING 
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_contratista_obra CHARACTER VARYING;    
BEGIN

	SELECT CONCAT(te.strnombre, te.strapellido1, te.strapellido2)::character varying AS nombrecontratista
	INTO v_contratista_obra
	FROM (((((public.obra ob
             JOIN public.relacioncontratoobra roc ON ((roc.intcodigoobra = ob.intcodigoobra)))
             JOIN public.contrato co ON (((co.intidcontrato = roc.intidcontrato) AND (co.inttipocontrato IS NOT NULL) AND (co.inttipocontrato = 1))))
             JOIN public.contratocontratista cc ON ((cc.intidcontrato = co.intidcontrato)))
             JOIN autenticacion.contratista ac ON ((ac.intcodigo = cc.intcodigo)))
             JOIN autenticacion.tercero te ON ((te.intcodigo = ac.intcodigo)))
	WHERE ob.intcodigoobra = p_intcodigoobra;
     
    RETURN  v_contratista_obra;
END
$$;

ALTER FUNCTION appmobile.f_obra_contratista_obra(p_intcodigoobra integer) OWNER TO cobra;


CREATE OR REPLACE VIEW appmobile.vista_proyectos AS 
 SELECT row_number() OVER (ORDER BY obra.intcodigoobra)::integer AS id,
    obra.intcodigoobra AS codigoproyecto,
        CASE
            WHEN length(obra.strnombreobra) > 50 THEN "substring"(obra.strnombreobra, 0, 49) || '...'::text
            ELSE obra.strnombreobra
        END::character varying AS nombreproyecto,
    obra.numvaltotobra AS valorproyecto,
    obra.numvalejecobra AS valorejecutado,
    split_part(split_part(f_proyecto_semaforo(obra.intcodigoobra)::text, '/'::text, 4), '.'::text, 1)::character varying(250) AS semaforoproyecto,
    obra.intlineanegocio AS codigocategoria,
    obra.strdesclineanegocio AS nombrecategoria,
    ((((( SELECT configuracion_siente.strvalorparametro
           FROM configuracion.configuracion_siente
          WHERE configuracion_siente.strcodigoparametro::text = 'direccionIPContexto'::text))::text) || ((( SELECT configuracion_siente.strvalorparametro
           FROM configuracion.configuracion_siente
          WHERE configuracion_siente.strcodigoparametro::text = 'nombreContextoSiente'::text))::text)) || split_part(obra.strurllogo::text, '.svg'::text, 1)) || '.png'::text AS imagencategoria,
    obra.strcolor AS colorcategoria,
    obra.usu_login AS usuario,
    NOT COALESCE(( SELECT ali1.aprobado
           FROM alimentacion ali1
          WHERE ali1.intcodigoobra = obra.intcodigoobra AND ali1.datefecha = (( SELECT max(ali2.datefecha) AS max
                   FROM alimentacion ali2
                  WHERE ali2.intcodigoobra = ali1.intcodigoobra))
         LIMIT 1), false) AS pendienteaprobacion,
    appmobile.f_debeir_obra_alimentacion(obra.intcodigoobra) AS deberiair,
    appmobile.f_obra_contratista_obra(obra.intcodigoobra) AS contratista
   FROM ( SELECT obra_1.intcodigoobra,
            obra_1.strnombreobra,
            obra_1.numvaltotobra,
            obra_1.numvalejecobra,
            ln.intlineanegocio,
            ln.strdesclineanegocio,
            ln.strurllogo,
            ln.strcolor,
            usu.usu_login
           FROM obra obra_1
             JOIN lineanegocio ln ON ln.intlineanegocio = obra_1.lineanegocio
             JOIN relacionobrajsf_usuario ou ON ou.intcodigoobra = obra_1.intcodigoobra
             JOIN autenticacion.jsf_usuario usu ON usu.usu_id = ou.usu_id
          WHERE obra_1.intestadoobra = 1
        UNION
         SELECT obra_1.intcodigoobra,
            obra_1.strnombreobra,
            obra_1.numvaltotobra,
            obra_1.numvalejecobra,
            ln.intlineanegocio,
            ln.strdesclineanegocio,
            ln.strurllogo,
            ln.strcolor,
            sup.usu_login
           FROM obra obra_1
             JOIN lineanegocio ln ON ln.intlineanegocio = obra_1.lineanegocio
             JOIN autenticacion.tercero tersup ON tersup.intcodigo = obra_1.codigo_supervisor
             JOIN autenticacion.jsf_usuario sup ON sup.usu_ter_cdigo = tersup.intcodigo
          WHERE obra_1.intestadoobra = 1
        UNION
         SELECT obra_1.intcodigoobra,
            obra_1.strnombreobra,
            obra_1.numvaltotobra,
            obra_1.numvalejecobra,
            ln.intlineanegocio,
            ln.strdesclineanegocio,
            ln.strurllogo,
            ln.strcolor,
            contratista.usu_login
           FROM obra obra_1
             JOIN lineanegocio ln ON ln.intlineanegocio = obra_1.lineanegocio
             JOIN relacioncontratoobra co ON co.intcodigoobra = obra_1.intcodigoobra
             JOIN contrato contcontratista ON contcontratista.intidcontrato = co.intidcontrato
             JOIN contratocontratista ON contratocontratista.intidcontrato = contcontratista.intidcontrato
             JOIN autenticacion.jsf_usuario contratista ON contratista.usu_ter_cdigo = contratocontratista.intcodigo
          WHERE obra_1.intestadoobra = 1) obra;

ALTER TABLE appmobile.vista_proyectos OWNER TO cobra;

