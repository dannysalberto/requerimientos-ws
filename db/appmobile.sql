--------------- se crea el esquema para las vistas de movil
DROP SCHEMA appmobile CASCADE;
CREATE SCHEMA appmobile  AUTHORIZATION cobra;

--------------- funcion que calcula el avance de una obra con el schema de indicadores

CREATE OR REPLACE FUNCTION appmobile.f_avance_total_obra(p_intcodigoobra integer)
  RETURNS CHARACTER VARYING AS
$BODY$
DECLARE
    v_sumaindicadores NUMERIC;
    v_avance_total_obra NUMERIC;    
    v_int_avance_total_obra INTEGER;
    v_str_avance_total_obra VARCHAR;
BEGIN

    SELECT sum(porcentaje)
    INTO v_sumaindicadores
    FROM indicadores.base
    WHERE intcodigoobra = p_intcodigoobra;
    

    SELECT COALESCE(v_sumaindicadores,0) * COALESCE(100,0) 
    INTO v_avance_total_obra;

    SELECT  CAST (v_avance_total_obra AS INTEGER)
    INTO v_int_avance_total_obra;

    SELECT  CAST (v_int_avance_total_obra AS VARCHAR)
    INTO v_str_avance_total_obra;
     
    RETURN  CONCAT(v_str_avance_total_obra, '%');
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_avance_total_obra(integer)  OWNER TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_avance_total_obra(integer) TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_avance_total_obra(integer) TO public;

--------------- FUNCION PARA CALCULAR EL PORCENTAJE DE AVANCE DE LOS PROJECTO EN INDICADORES


CREATE OR REPLACE FUNCTION appmobile.f_sumar_avance_tipo_proyecto(p_intidtipoproyecto integer)
  RETURNS NUMERIC AS
$BODY$
DECLARE
    v_sumaporcentajetipoproyecto NUMERIC;
    v_totalproyectos INTEGER;
    v_porcentajeportipoproyecto NUMERIC;    
BEGIN


	SELECT SUM(base.porcentaje)
	INTO v_sumaporcentajetipoproyecto
	FROM OBRA
	INNER JOIN tipoobra ON tipoobra.inttipoobra = obra.inttipoobra
	INNER JOIN tipoproyecto ON tipoproyecto.intidtipoproyecto = tipoobra.intidtipoproyecto	
	INNER JOIN indicadores.base as base  ON obra.intcodigoobra = base.intcodigoobra
	WHERE tipoproyecto.intidtipoproyecto = p_intidtipoproyecto;


	SELECT COUNT(1)
	INTO v_totalproyectos
	FROM OBRA
		INNER JOIN tipoobra ON tipoobra.inttipoobra = obra.inttipoobra
		INNER JOIN tipoproyecto ON tipoproyecto.intidtipoproyecto = tipoobra.intidtipoproyecto		
	WHERE tipoproyecto.intidtipoproyecto = p_intidtipoproyecto;

	SELECT  ((v_sumaporcentajetipoproyecto / v_totalproyectos) * 100)
	INTO v_porcentajeportipoproyecto;
    
         
	RETURN v_porcentajeportipoproyecto;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_sumar_avance_tipo_proyecto(integer)  OWNER TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_sumar_avance_tipo_proyecto(integer) TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_sumar_avance_tipo_proyecto(integer) TO public;

--------------- VISTA HOME GENERAL
CREATE OR REPLACE VIEW public.vista_home_general AS 
 SELECT 1 AS id,
        CASE
            WHEN sum(obra.numvaltotobra) IS NULL THEN 0::numeric
            ELSE sum(obra.numvaltotobra)
        END AS numvaltotobra,
        CASE
            WHEN sum(obra.numvalejecobra) IS NULL THEN 0::numeric
            ELSE sum(obra.numvalejecobra)
        END AS numvalejecobra,
        CASE
            WHEN (select (sum(porcentaje) / (select count(1) from obra)) * 100 from indicadores.base) IS NULL THEN 0::numeric
            ELSE (select (sum(porcentaje) / (select count(1) from obra)) * 100 from indicadores.base)
        END AS avance,
        CASE
            WHEN sum(obra.numempdirectos) IS NULL THEN 0::bigint
            ELSE sum(obra.numempdirectos)
        END AS numempdirectos,
        CASE
            WHEN sum(obra.numempindirectos) IS NULL THEN 0::bigint
            ELSE sum(obra.numempindirectos)
        END AS numempindirectos,
        CASE
            WHEN sum(obra.numhabbeneficiados) IS NULL THEN 0::bigint
            ELSE sum(obra.numhabbeneficiados)
        END AS numhabbeneficiados,
    count(obra.strnombreobra) AS cantidadproyectos,
    count(teo1.strdesctipoestado) AS proyectosejecucion,
    count(teo2.strdesctipoestado) AS proyectosiniciar,
    count(teo3.strdesctipoestado) AS proyectosterminados
   FROM obra
     LEFT JOIN tipoestadobra teo1 ON teo1.intestadoobra = obra.intestadoobra AND teo1.strdesctipoestado::text = 'En ejecución.'::text
     LEFT JOIN tipoestadobra teo2 ON teo2.intestadoobra = obra.intestadoobra AND teo2.strdesctipoestado::text = 'En estructuración'::text
     LEFT JOIN tipoestadobra teo3 ON teo3.intestadoobra = obra.intestadoobra AND teo3.strdesctipoestado::text = 'Acta de recibo final.'::text AND teo3.strdesctipoestado::text = 'Liquidado.'::text;

ALTER TABLE public.vista_home_general
  OWNER TO cobra;
GRANT ALL ON TABLE public.vista_home_general TO cobra;


--------------- VISTA HOME TIPO

CREATE OR REPLACE VIEW public.vista_home_tipo AS 
 SELECT
        CASE
            WHEN sum(obra.numvaltotobra) IS NULL THEN 0::numeric
            ELSE sum(obra.numvaltotobra)
        END AS numvaltotobra,
        CASE
            WHEN sum(obra.numvalejecobra) IS NULL THEN 0::numeric
            ELSE sum(obra.numvalejecobra)
        END AS numvalejecobra,
        CASE
            WHEN appmobile.f_sumar_avance_tipo_proyecto(tipoproyecto.intidtipoproyecto) IS NULL THEN 0::numeric
            ELSE appmobile.f_sumar_avance_tipo_proyecto(tipoproyecto.intidtipoproyecto) 
        END AS avance,
        CASE
            WHEN sum(obra.numempdirectos) IS NULL THEN 0::bigint
            ELSE sum(obra.numempdirectos)
        END AS numempdirectos,
        CASE
            WHEN sum(obra.numempindirectos) IS NULL THEN 0::bigint
            ELSE sum(obra.numempindirectos)
        END AS numempindirectos,
        CASE
            WHEN sum(obra.numhabbeneficiados) IS NULL THEN 0::bigint
            ELSE sum(obra.numhabbeneficiados)
        END AS numhabbeneficiados,
    tipoproyecto.intidtipoproyecto AS idtipo,
    tipoproyecto.strnombre AS tipo,
    tipoproyecto.strurlimagen AS urlimg,
    ''::character varying AS strcolor,
    ''::character varying AS strurllogo,
    ''::character varying AS strurlimagenmodal,
    replace(tipoproyecto.strurlimagen::text, '.png'::text, '-seleccionado.png'::text) AS urlimgseleccionada,
    0 AS cantidadproyectos,
    0 AS proyectosejecucion,
    0 AS proyectosiniciar,
    0 AS proyectosterminados
   FROM tipoproyecto
     LEFT JOIN tipoobra ON tipoobra.intidtipoproyecto = tipoproyecto.intidtipoproyecto
     LEFT JOIN obra ON obra.inttipoobra = tipoobra.inttipoobra AND obra.intestadoobra <> 0
     LEFT JOIN indicadores.base as base  ON obra.intcodigoobra = base.intcodigoobra
  GROUP BY tipoproyecto.intidtipoproyecto
  ORDER BY tipoproyecto.strnombre;

ALTER TABLE public.vista_home_tipo OWNER TO cobra;
GRANT ALL ON TABLE public.vista_home_tipo TO cobra;


--------------- se altera la tabla de tipo proyecto, para tener los datos para la vista de favoritos.
ALTER TABLE tipoproyecto ADD COLUMN strcolorlineapp VARCHAR(10) NULL;
ALTER TABLE tipoproyecto ADD COLUMN strlogolineapp VARCHAR(100) NULL;

UPDATE tipoproyecto 
SET strcolorlineapp = '#3F6FBA', strlogolineapp = 'header_jovenes.svg'
WHERE intidtipoproyecto = 1;

UPDATE tipoproyecto 
SET strcolorlineapp = '#77B4D2', strlogolineapp = 'header_fortalecimiento.svg'
WHERE intidtipoproyecto = 2;

UPDATE tipoproyecto 
SET strcolorlineapp = '#94CAA3', strlogolineapp = 'header_educacion.svg'
WHERE intidtipoproyecto = 3;


UPDATE tipoproyecto 
SET strcolorlineapp = '#A2CE81', strlogolineapp = 'header_desarrollo.svg'
WHERE intidtipoproyecto = 4;


--------------------------- se agrega nuevo campo en la tabla estado obra.
ALTER TABLE tipoestadobra  ADD COLUMN strdescripcionestadomobile VARCHAR(50) NULL DEFAULT '';

UPDATE tipoestadobra
SET strdescripcionestadomobile = 'Estructuración'
WHERE intestadoobra = 0;

UPDATE tipoestadobra
SET strdescripcionestadomobile = 'Ejecución'
WHERE intestadoobra = 1;

UPDATE tipoestadobra
SET strdescripcionestadomobile = 'Cerrado'
WHERE intestadoobra = 2;

UPDATE tipoestadobra
SET strdescripcionestadomobile = 'Modificación'
WHERE intestadoobra = 3;

UPDATE tipoestadobra
SET strdescripcionestadomobile = 'En Cierre'
WHERE intestadoobra = 4;

UPDATE tipoestadobra
SET strdescripcionestadomobile = 'Recibo final'
WHERE intestadoobra = 5;

UPDATE tipoestadobra
SET strdescripcionestadomobile = 'Suspendido'
WHERE intestadoobra = 6;

--------------------- function sumar valor total de la obra

CREATE OR REPLACE FUNCTION appmobile.f_sumar_total_valor_obra(p_intcodigoobra integer)
  RETURNS character varying AS
$BODY$
DECLARE
    v_sumacontratointerventoria NUMERIC;
    v_valorobra NUMERIC;
    v_sumatotalvalorobra NUMERIC;
    v_int_sumatotalvalorobra INTEGER;
    v_strsumatotalvalorobra VARCHAR;
    v_str_totalvalorobra VARCHAR;
    
BEGIN

    SELECT sum(numvalorrelacion) 
    INTO v_sumacontratointerventoria
    FROM contrato c INNER JOIN relacioncontratoobra rco ON rco.intidcontrato = c.intidcontrato 
    WHERE inttipocontrato IS NULL AND intcodigoobra = p_intcodigoobra;

    SELECT numvaltotobra 
    INTO v_valorobra
    FROM obra o
    WHERE intcodigoobra = p_intcodigoobra; 

    SELECT COALESCE(v_sumacontratointerventoria,0) + COALESCE(v_valorobra,0)
    INTO v_sumatotalvalorobra;

    SELECT  CAST (v_sumatotalvalorobra AS INTEGER)
    INTO v_int_sumatotalvalorobra;

    SELECT CAST(sum(v_int_sumatotalvalorobra) AS money)
    INTO v_strsumatotalvalorobra;	

    SELECT REPLACE(v_strsumatotalvalorobra, ',00', '')
    INTO v_str_totalvalorobra;
         
    RETURN REPLACE(v_str_totalvalorobra, '$', '€') ;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_sumar_total_valor_obra(integer)  OWNER TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_sumar_total_valor_obra(integer) TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_sumar_total_valor_obra(integer) TO public;




------------------------ funcion que retorna un dato para columna distacia

CREATE OR REPLACE FUNCTION appmobile.f_distacia_obra(p_intcodigoobra integer)
  RETURNS CHARACTER VARYING AS
$BODY$
DECLARE
 
    v_str_distancia VARCHAR;
BEGIN

    SELECT  CAST ('230' AS VARCHAR)
    INTO v_str_distancia;

     
    RETURN  CONCAT(v_str_distancia, 'Km ');
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_distacia_obra(integer)  OWNER TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_distacia_obra(integer) TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_distacia_obra(integer) TO public;




------------------ view retorna el semaforo de una obra.

CREATE OR REPLACE FUNCTION appmobile.f_proyecto_semaforo(p_intcodigoobra integer)
  RETURNS character varying AS
$BODY$
DECLARE
    v_fecha DATE;
    v_fechafinperiodoanterior DATE;
    v_datefecfinobra DATE;
    v_booleantienehijos BOOLEAN;
    v_deberiaestar NUMERIC;
    v_intestadoobra INTEGER;
    v_numvaltotobra NUMERIC;
    v_numvalejecobra NUMERIC;
    v_semaforo VARCHAR;
    v_inttipoobra INTEGER;
    v_intidalarma INTEGER;
    v_porcentajeatraso NUMERIC;
BEGIN
    v_fecha := NOW();
 
    SELECT datefecfinobra, booleantienehijos, intestadoobra, numvaltotobra, numvalejecobra, inttipoobra
    INTO v_datefecfinobra, v_booleantienehijos, v_intestadoobra, v_numvaltotobra, v_numvalejecobra, v_inttipoobra
    FROM obra
    WHERE intcodigoobra = p_intcodigoobra;
 
    IF v_intestadoobra = 1 THEN
        IF v_numvalejecobra < v_numvaltotobra - 1 THEN
            v_deberiaestar := public.f_proyecto_deberia_estar(p_intcodigoobra);
            IF v_deberiaestar != 0 THEN
                v_porcentajeatraso :=  100 - 100 * v_numvalejecobra / v_deberiaestar;
            ELSE
                v_porcentajeatraso := 0;
            END IF;
            IF v_fecha <= v_datefecfinobra THEN
                IF v_porcentajeatraso <= 0 THEN
                    v_semaforo := 'semaforo_verde.svg';
                END IF;
                IF v_porcentajeatraso > 7.9 AND v_porcentajeatraso < 15 THEN
                    v_semaforo := 'semaforo_naranja.svg';
                END IF;
                IF v_porcentajeatraso >=15THEN
                    v_semaforo := 'semaforo_rojo.svg';
                END IF;
            ELSE
                IF v_porcentajeatraso > 2 THEN
                    v_semaforo := 'semaforo_rojo.svg';
                ELSE
                    v_semaforo := 'semaforo_verde.svg';
                END IF;
            END IF;
        ELSE
            v_semaforo := 'semaforo_verde.svg';
        END IF;
    ELSE --Si la obra no está en ejecución
        v_semaforo := 'semaforo_verde.svg';   
    END IF;
    IF v_semaforo IS NULL THEN
        v_semaforo := 'semaforo_verde.svg';
    END IF;

    IF v_semaforo = '' THEN
        v_semaforo := 'semaforo_verde.svg';
    END IF;
     
    RETURN v_semaforo;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_proyecto_semaforo(integer) OWNER TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_proyecto_semaforo(integer) TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_proyecto_semaforo(integer) TO public;

------------- vista favoritos asignados 

CREATE OR REPLACE VIEW appmobile.vista_favorito_asignados AS 
 SELECT row_number() OVER (ORDER BY tabla1.intcodigoobra)::integer AS id,
    tabla1.intcodigoobra AS codigoproyecto,
    tabla1.strcolorlineapp AS colorcategoria,
    tabla1.strlogolineapp AS imagencategoria,
    tabla1.strnombre AS nombrecategoria,
        CASE
            WHEN length(tabla1.nombre_obra) > 50 THEN "substring"(tabla1.nombre_obra, 0, 49) || '.....'::text
            ELSE tabla1.nombre_obra
        END::character varying AS nombreproyecto,
    appmobile.f_distacia_obra(tabla1.intcodigoobra) AS distaciaproyecto,
    appmobile.f_sumar_total_valor_obra(tabla1.intcodigoobra) AS valorproyecto,
    appmobile.f_avance_total_obra(tabla1.intcodigoobra) AS avanceproyecto,
    appmobile.f_proyecto_semaforo(tabla1.intcodigoobra) AS semaforoproyecto,
        CASE
            WHEN sum(tabla1.seguidaxusuario) > 0 THEN 'corazon_activado.svg'::text
            ELSE 'corazon_desactivado.svg'::text
        END AS favorito,
    tabla1.ter_id AS codigousuario,
    tabla1.latitud as latitud,
    tabla1.longitud as longitud
   FROM ( SELECT obra.intcodigoobra,
            tipoproyecto.strlogolineapp,
            tipoproyecto.strcolorlineapp,
            tipoproyecto.strnombre,
            obra.strnombreobra AS nombre_obra,
            1 AS asignadasxusuario,
            0 AS seguidaxusuario,
            jsf_usuario.usu_id AS ter_id,
            obra.floatlatitud as latitud,
            obra.floatlongitud as longitud
           FROM obra
             JOIN tipoobra ON tipoobra.inttipoobra = obra.inttipoobra
             JOIN tipoproyecto ON tipoobra.intidtipoproyecto = tipoproyecto.intidtipoproyecto
             JOIN autenticacion.tercero ON tercero.intcodigo = obra.codigo_supervisor
             JOIN autenticacion.jsf_usuario ON jsf_usuario.usu_ter_cdigo = tercero.intcodigo
        UNION ALL
         SELECT obra.intcodigoobra,
            tipoproyecto.strlogolineapp,
            tipoproyecto.strcolorlineapp,
            tipoproyecto.strnombre,
            obra.strnombreobra AS nombre_obra,
            1 AS asignadasxusuario,
            0 AS seguidaxusuario,
            rojsfusu.usu_id AS ter_id,
            obra.floatlatitud as latitud,
            obra.floatlongitud as longitud
           FROM obra
             JOIN tipoobra ON tipoobra.inttipoobra = obra.inttipoobra
             JOIN tipoproyecto ON tipoobra.intidtipoproyecto = tipoproyecto.intidtipoproyecto
             JOIN relacionobrajsf_usuario rojsfusu ON obra.intcodigoobra = rojsfusu.intcodigoobra
        UNION ALL
         SELECT obra.intcodigoobra,
            tipoproyecto.strlogolineapp,
            tipoproyecto.strcolorlineapp,
            tipoproyecto.strnombre,
            obra.strnombreobra AS nombre_obra,
            0 AS asignadasxusuario,
            1 AS seguidaxusuario,
            jsf_usuario.usu_id AS ter_id,
            obra.floatlatitud as latitud,
            obra.floatlongitud as longitud
           FROM relacionobraseguidor
             JOIN obra ON relacionobraseguidor.intcodigoobra = obra.intcodigoobra
             JOIN tipoobra ON tipoobra.inttipoobra = obra.inttipoobra
             JOIN tipoproyecto ON tipoobra.intidtipoproyecto = tipoproyecto.intidtipoproyecto
             JOIN autenticacion.jsf_usuario ON jsf_usuario.usu_id = relacionobraseguidor.usu_id) tabla1
  GROUP BY tabla1.intcodigoobra, tabla1.strlogolineapp, tabla1.strcolorlineapp, tabla1.strnombre, tabla1.nombre_obra, tabla1.asignadasxusuario, tabla1.seguidaxusuario, tabla1.ter_id, tabla1.latitud, tabla1.longitud;

ALTER TABLE appmobile.vista_favorito_asignados
  OWNER TO cobra;




---------------------------------------- VIEW LISTA DE PROYECTOS

CREATE OR REPLACE VIEW appmobile.vista_proyectos_lista AS 
 SELECT row_number() OVER (ORDER BY tabla1.intcodigoobra)::integer AS id,
    tabla1.intcodigoobra AS codigoproyecto,
        CASE
            WHEN length(tabla1.nombreobra) > 50 THEN "substring"(tabla1.nombreobra, 0, 49) || '.....'::text
            ELSE tabla1.nombreobra
        END::character varying AS nombreproyecto,
    tabla1.imagen AS imagenproyecto,
    appmobile.f_sumar_total_valor_obra(tabla1.intcodigoobra) AS valorproyecto,
    appmobile.f_avance_total_obra(tabla1.intcodigoobra) AS avanceproyecto,
    appmobile.f_proyecto_semaforo(tabla1.intcodigoobra) AS semaforoproyecto,
    tabla1.latitud as latitud,
    tabla1.longitud as longitud,
    tabla1.tipoproyecto as codigocategoria,
    tabla1.strcolorlineapp AS colorcategoria,
    tabla1.strlogolineapp AS imagencategoria,
    tabla1.strnombre AS nombrecategoria,        
    tabla1.strdescripcionestadomobile as estadoproyecto    
   FROM ( SELECT OBRA.intcodigoobra,
	    OBRA.strnombreobra AS nombreobra,	
	    OBRA.floatlatitud as latitud,
            OBRA.floatlongitud as longitud,
	    OBRA.strimagenobra AS imagen,
	    TIPR.intidtipoproyecto,
	    TIPR.strnombre,
            TIPR.strlogolineapp,
            TIPR.strcolorlineapp,            
	    TIPR.intidtipoproyecto as tipoproyecto,
            ESOB.strdescripcionestadomobile                        
           FROM obra AS  OBRA
             INNER JOIN tipoobra AS TIOB ON TIOB.inttipoobra = OBRA.inttipoobra
             INNER JOIN tipoproyecto AS TIPR ON TIPR.intidtipoproyecto = TIOB.intidtipoproyecto
             INNER JOIN tipoestadobra AS ESOB ON ESOB.intestadoobra = OBRA.intestadoobra
             ) tabla1
  GROUP BY tabla1.intcodigoobra, tabla1.strlogolineapp, tabla1.strcolorlineapp, tabla1.strnombre, tabla1.nombreobra, tabla1.latitud, tabla1.longitud, tabla1.tipoproyecto, tabla1.strdescripcionestadomobile, tabla1.imagen;

ALTER TABLE appmobile.vista_proyectos_lista OWNER TO cobra;


------------------------------------------------------------------

CREATE OR REPLACE VIEW appmobile.vista_proyectos_mapa AS 
 SELECT row_number() OVER (ORDER BY tabla1.intcodigoobra)::integer AS id,
    tabla1.intcodigoobra AS codigoproyecto,
        CASE
            WHEN length(tabla1.nombreobra) > 50 THEN "substring"(tabla1.nombreobra, 0, 49) || '.....'::text
            ELSE tabla1.nombreobra
        END::character varying AS nombreproyecto,
    tabla1.imagen AS imagenproyecto,
    appmobile.f_sumar_total_valor_obra(tabla1.intcodigoobra) AS valorproyecto,
    appmobile.f_avance_total_obra(tabla1.intcodigoobra) AS avanceproyecto,
    appmobile.f_proyecto_semaforo(tabla1.intcodigoobra) AS semaforoproyecto,
    tabla1.latitud as latitud,
    tabla1.longitud as longitud,
    tabla1.tipoproyecto as codigocategoria,
    tabla1.strcolorlineapp AS colorcategoria,
    tabla1.strlogolineapp AS imagencategoria,
    tabla1.strnombre AS nombrecategoria,        
    tabla1.strdescripcionestadomobile as estadoproyecto	    
   FROM ( SELECT OBRA.intcodigoobra,
	    OBRA.strnombreobra AS nombreobra,	
	    OBRA.floatlatitud as latitud,
            OBRA.floatlongitud as longitud,
	    OBRA.strimagenobra AS imagen,
	    TIPR.intidtipoproyecto,
	    TIPR.strnombre,
            TIPR.strlogolineapp,
            TIPR.strcolorlineapp,            
	    TIPR.intidtipoproyecto as tipoproyecto,
            ESOB.strdescripcionestadomobile                        
           FROM obra AS  OBRA
             INNER JOIN tipoobra AS TIOB ON TIOB.inttipoobra = OBRA.inttipoobra
             INNER JOIN tipoproyecto AS TIPR ON TIPR.intidtipoproyecto = TIOB.intidtipoproyecto
             INNER JOIN tipoestadobra AS ESOB ON ESOB.intestadoobra = OBRA.intestadoobra
             ) tabla1
  GROUP BY tabla1.intcodigoobra, tabla1.strlogolineapp, tabla1.strcolorlineapp, tabla1.strnombre, tabla1.nombreobra, tabla1.latitud, tabla1.longitud, tabla1.tipoproyecto, tabla1.strdescripcionestadomobile, tabla1.imagen;

ALTER TABLE appmobile.vista_proyectos_mapa OWNER TO cobra;


---------------------------------------------- view valores globales lienas

CREATE OR REPLACE VIEW appmobile.vista_indicadores_globales AS 
 SELECT row_number() OVER (ORDER BY tabla1.codigocategoria)::integer AS id,
    tabla1.totalvalorproyectos::NUMERIC,
    tabla1.logototalvalorproyectos,
    tabla1.totalvalorejecutadoproyectos::NUMERIC,
    tabla1.logototalvalorejecutadoproyectos,
    tabla1.totalavanceproyectos::NUMERIC,
    tabla1.logototalavanceproyectos,
    tabla1.totalempleosdirectos::NUMERIC,
    tabla1.logototalempleosdirectos,
    tabla1.totalempleosindirectos::NUMERIC,
    tabla1.logototalempleosindirectos,
    tabla1.totalhabitantesbeneficiados::NUMERIC,
    tabla1.logototalhabitantesbeneficiados,
    tabla1.totalproyectos::NUMERIC,
    tabla1.logototalproyectos,
    tabla1.totalproyectosejecucion::NUMERIC,
    tabla1.logototalproyectosejecucion,
    tabla1.totalproyectosiniciar::NUMERIC,
    tabla1.logototalproyectosiniciar,
    tabla1.totalproyectosterminados::NUMERIC,
    tabla1.logototalproyectosterminados,
    tabla1.codigocategoria,
    tabla1.nombrecategoria,
    tabla1.colorcategoria,
    tabla1.botoncategoriainactivo,
    tabla1.botoncategoriaactivo
   FROM ( SELECT vista_home_general.numvaltotobra AS totalvalorproyectos,
            '0_chart2.svg'::character varying AS logototalvalorproyectos,
            vista_home_general.numvalejecobra AS totalvalorejecutadoproyectos,
            '0_chart2.svg'::character varying AS logototalvalorejecutadoproyectos,
            vista_home_general.avance AS totalavanceproyectos,
            '0_chart2.svg'::character varying AS logototalavanceproyectos,
            vista_home_general.numempdirectos AS totalempleosdirectos,
            '0_chart2.svg'::character varying AS logototalempleosdirectos,
            vista_home_general.numempindirectos AS totalempleosindirectos,
            '0_chart2.svg'::character varying AS logototalempleosindirectos,
            vista_home_general.numhabbeneficiados AS totalhabitantesbeneficiados,
            '0_chart2.svg'::character varying AS logototalhabitantesbeneficiados,
            vista_home_general.cantidadproyectos AS totalproyectos,
            '0_chart2.svg'::character varying AS logototalproyectos,
            vista_home_general.proyectosejecucion AS totalproyectosejecucion,
            '0_chart2.svg'::character varying AS logototalproyectosejecucion,
            vista_home_general.proyectosiniciar AS totalproyectosiniciar,
            '0_chart2.svg'::character varying AS logototalproyectosiniciar,
            vista_home_general.proyectosterminados AS totalproyectosterminados,
            '0_chart2.svg'::character varying AS logototalproyectosterminados,
            0::numeric AS codigocategoria,
            'Todos'::character varying AS nombrecategoria,
            '#E19E3D'::character varying AS colorcategoria,
            'btn_0.svg'::character varying AS botoncategoriainactivo,
            'btn_0_activo.svg'::character varying AS botoncategoriaactivo
           FROM public.vista_home_general
        UNION ALL
         SELECT vista_home_tipo.numvaltotobra AS totalvalorproyectos,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalvalorproyectos,
            vista_home_tipo.numvalejecobra AS totalvalorejecutadoproyectos,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalvalorejecutadoproyectos,
            vista_home_tipo.avance AS totalavanceproyectos,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalavanceproyectos,
            vista_home_tipo.numempdirectos AS totalempleosdirectos,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalempleosdirectos,
            vista_home_tipo.numempindirectos AS totalempleosindirectos,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalempleosindirectos,
            vista_home_tipo.numhabbeneficiados AS totalhabitantesbeneficiados,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalhabitantesbeneficiados,
            vista_home_tipo.cantidadproyectos AS totalproyectos,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectos,
            vista_home_tipo.proyectosejecucion AS totalproyectosejecucion,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectosejecucion,
            vista_home_tipo.proyectosiniciar AS totalproyectosiniciar,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectosiniciar,
            vista_home_tipo.proyectosterminados AS totalproyectosterminados,
            replace('**_chart2.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectosterminados,
            vista_home_tipo.idtipo AS codigocategoria,
            vista_home_tipo.tipo AS nombrecategoria,
            '#E19E3D'::character varying AS colorcategoria,
            replace('btn_**.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS botoncategoriainactivo,
            replace('btn_**_activo.svg'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS botoncategoriaactivo
           FROM public.vista_home_tipo
             JOIN tipoproyecto ON tipoproyecto.intidtipoproyecto = vista_home_tipo.idtipo) tabla1;

ALTER TABLE appmobile.vista_indicadores_globales OWNER TO cobra;

