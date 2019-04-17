--------------- se crea el esquema para las vistas de movil
DROP SCHEMA appmobile CASCADE;
CREATE SCHEMA appmobile  AUTHORIZATION cobra;


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
SET strcolorlineapp = '#227fc6', strlogolineapp = 'jovenes'
WHERE intidtipoproyecto = 1;

UPDATE tipoproyecto 
SET strcolorlineapp = '#37aed0', strlogolineapp = 'fortalecimiento'
WHERE intidtipoproyecto = 2;

UPDATE tipoproyecto 
SET strcolorlineapp = '#69c699', strlogolineapp = 'educacion'
WHERE intidtipoproyecto = 3;


UPDATE tipoproyecto 
SET strcolorlineapp = '#91cf81', strlogolineapp = 'desarrollo'
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

--------------------- se agregan nuevos campos en la encuestasue.indicador para configuracion de indicadores

ALTER TABLE encuestasue.indicador ADD COLUMN descripcion VARCHAR(500) NULL DEFAULT '';
ALTER TABLE encuestasue.indicador ADD COLUMN logomobile VARCHAR(100) NULL DEFAULT '';


UPDATE encuestasue.indicador
SET indicador = 'FORMACIÓN PARA LA PRÁCTICA',
    descripcion = 'El {***}% de los jóvenes rurales obtienen mejores resultados a partir de la puesta en práctica de los conocimientos adquiridos con el apoyo de la Unión Europea.',
    logomobile = 'formacion_practica'
WHERE id = 2;

UPDATE encuestasue.indicador
SET indicador = 'CORRESPONSABILIDAD Y CONSTRUCCIÓN DE LO PÚBLICO ',
    descripcion = 'Producto del apoyo de la Unión Europea, el  {***}% de los jóvenes rurales,  integrantes de espacios de participación,  han presentado propuestas, en las instancias para la toma de decisiones de políticas públicas, asuntos gremiales o sociales,  para el progreso social de su región.',
    logomobile = 'corresponsabilidad'
WHERE id = 3;

UPDATE encuestasue.indicador
SET indicador = 'PARTICIPACIÓN Y VOZ',
    descripcion = 'El  {***} de propuestas presentadas por los jóvenes rurales, en instancias decisorias,  han sido incorporadas en la definición de políticas públicas, gremiales y/o sociales.',
    logomobile = 'participacion_voz'
WHERE id = 4;


UPDATE encuestasue.indicador
SET indicador = 'PARTICIPACIÓN  CIUDADANA',
    descripcion = 'El {***} de jóvenes de los territorios priorizados hacen parte de los consejos directivos o instancias de decisión de las organizaciones a las que pertenecen.',
    logomobile = 'participacion'
WHERE id = 5;


UPDATE encuestasue.indicador
SET indicador = 'COOPERACIÓN',
    descripcion = 'Gracias al apoyo de la Unión Europea, el {***}% de los jóvenes rurales  pudieron asociarse a una organización de base formal o informal para trabajar de forma colectiva y solidaria.',
    logomobile = 'cooperacion'
WHERE id = 6;

UPDATE encuestasue.indicador
SET indicador = 'EMPRENDIMIENTO RURAL',
    descripcion = '{***}% de jóvenes rurales que tienen la oportunidad de trabajar con otros en emprendimientos productivos para el desarrollo local.',
    logomobile = 'emprendimiento_rural'
WHERE id = 7;


UPDATE encuestasue.indicador
SET indicador = 'AUTOSOSTENIBILIDAD ',
    descripcion = 'Con apoyo de la UE el {***}% de los jóvenes rurales logran vincularse a una ocupación productiva  o conseguir un empleo.',
    logomobile = 'autosostenibilidad'
WHERE id = 8;


UPDATE encuestasue.indicador
SET indicador = 'MEJORAMIENTO DE INGRESOS ',
    descripcion = 'Con el apoyo de la Unión europea los jóvenes rurales lograron un ingreso promedio de $ {***}',
    logomobile = 'mejoramiento_ingresos'
WHERE id = 9;

UPDATE encuestasue.indicador
SET indicador = 'SOSTENIBILIDAD',
    descripcion = 'El {***}% de los jóvenes rurales obtienen mejores resultados a partir de la puesta en práctica de los conocimientos adquiridos con el apoyo de la Unión Europea.',
    logomobile = 'sostenibilidad'
WHERE id = 10;

UPDATE encuestasue.indicador
SET indicador = 'FORMACIÓN PARA LA PRÁCTICA',
    descripcion = 'Por cuenta del apoyo de la UE el {***}% de los jóvenes rurales pueden acceder a mecanismos de financiamiento para apalancar sus emprendimientos.',
    logomobile = 'formacion_practica'
WHERE id = 11;


UPDATE encuestasue.indicador
SET indicador = 'CAMBIOS EN LA MIGRACIÓN',
    descripcion = 'Producto del apoyo de la Unión Europea  el {***}% de los jóvenes rurales deciden permanecer en su territorio. ',
    logomobile = 'cambios_migracion'
WHERE id = 12;


UPDATE encuestasue.indicador
SET indicador = 'USO DE LA TIERRA',
    descripcion = 'El {***}% de los jóvenes rurales han logrado acceder al uso productivo de la tierra para afianzar sus emprendimientos y generar arraigo social.',
    logomobile = 'uso_tierra'
WHERE id = 13;

UPDATE encuestasue.indicador
SET indicador = 'FORTALECIMIENTO  CAPACIDADES  DE LAS ORGANIZACIONES',
    descripcion = 'Con el apoyo de la Unión Europea, el {***}% de las organizaciones han fortalecido sus capacidades para desempeñar de manera más efectiva su papel como actores de desarrollo, gracias al levantamiento de un Índice Capacidad Organizacional (ICO).',
    logomobile = 'fortalecimiento'
WHERE id = 14;


UPDATE encuestasue.indicador
SET indicador = 'FORMACIÓN PARA LA PRÁCTICA',
    descripcion = 'El {***}% de las organizaciones obtienen mejores resultados a partir de la puesta en práctica de los conocimientos adquiridos con el apoyo de la Unión Europea.',
    logomobile = 'formacion_practica'
WHERE id = 15;


UPDATE encuestasue.indicador
SET indicador = 'PARTICIPACIÓN  CIUDADANA ',
    descripcion = 'El  {***}% de las organizaciones participan en instancias para la toma de decisiones de políticas públicas, asuntos gremiales o sociales para sus territorios, con el apoyo de la Unión Europea.',
    logomobile = 'participacion_ciudadana'
WHERE id = 16;

UPDATE encuestasue.indicador
SET indicador = 'CORRESPONSABILIDAD Y CONSTRUCCIÓN DE LO PÚBLICO ',
    descripcion = 'Producto del apoyo de la Unión Europea, el  {***}% de las organizaciones,  integrantes de espacios de participación,  han presentado propuestas, en las instancias para la toma de decisiones de políticas públicas, asuntos gremiales o sociales,  para el progreso social de su región.',
    logomobile = 'corresponsabilidad'
WHERE id = 17;


UPDATE encuestasue.indicador
SET indicador = 'PARTICIPACIÓN Y VOZ ',
    descripcion = 'El  {***}% de propuestas presentadas por las organizaciones, en instancias decisorias,  han sido incorporadas en la definición de políticas públicas, gremiales y/o sociales..',
    logomobile = 'participacion_voz'
WHERE id = 18;


--------------------- FUNCION QUE RETORNA EL LOCALIDAD DEL PROYECTO


CREATE OR REPLACE FUNCTION appmobile.f_localidad_obra(p_intcodigoobra integer)
  RETURNS character varying AS
$BODY$
DECLARE
    v_str_departamento VARCHAR;
    v_str_municipio VARCHAR;
    v_str_departamento_corto VARCHAR;
    v_str_municipio_corto VARCHAR;
        
BEGIN

	SELECT l.strdepartamento, l.strmunicipio
	INTO v_str_departamento, v_str_municipio
	FROM obra as o 
		INNER JOIN obralocalidad  AS  ol ON ol.intcodigoobra = o.intcodigoobra
		INNER JOIN chsolicitudes.localidad AS l ON l.strcodigolocalidad = ol.strcodigolocalidad
	where o.intcodigoobra = p_intcodigoobra
	GROUP BY l.strdepartamento, l.strmunicipio
	LIMIT 1;


	IF length(v_str_municipio) > 18 THEN 
		v_str_municipio := "substring"(v_str_municipio, 0, 17) || '...'::VARCHAR;
	END IF;

	IF length(v_str_departamento) > 15 THEN 
		v_str_departamento := "substring"(v_str_departamento, 0, 14) || '.'::VARCHAR;
	END IF;
     
	RETURN  CONCAT(v_str_municipio, ', ',v_str_departamento);
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_localidad_obra(integer)
  OWNER TO cobra;
GRANT EXECUTE ON FUNCTION appmobile.f_localidad_obra(integer) TO public;
GRANT EXECUTE ON FUNCTION appmobile.f_localidad_obra(integer) TO cobra;
	






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
                    v_semaforo := 'semaforo_verde';
                END IF;
                IF v_porcentajeatraso > 7.9 AND v_porcentajeatraso < 15 THEN
                    v_semaforo := 'semaforo_naranja';
                END IF;
                IF v_porcentajeatraso >=15THEN
                    v_semaforo := 'semaforo_rojo';
                END IF;
            ELSE
                IF v_porcentajeatraso > 2 THEN
                    v_semaforo := 'semaforo_rojo';
                ELSE
                    v_semaforo := 'semaforo_verde';
                END IF;
            END IF;
        ELSE
            v_semaforo := 'semaforo_verde';
        END IF;
    ELSE --Si la obra no está en ejecución
        v_semaforo := 'semaforo_verde';   
    END IF;
    IF v_semaforo IS NULL THEN
        v_semaforo := 'semaforo_verde';
    END IF;

    IF v_semaforo = '' THEN
        v_semaforo := 'semaforo_verde';
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
            WHEN sum(tabla1.seguidaxusuario) > 0 THEN 'corazon_activado'::text
            ELSE 'corazon_desactivado'::text
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
    appmobile.f_localidad_obra(tabla1.intcodigoobra) AS localidadproyecto,
    tabla1.latitud as latitudproyecto,
    tabla1.longitud as longitudproyecto,
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
    appmobile.f_localidad_obra(tabla1.intcodigoobra) AS localidadproyecto,
    tabla1.latitud as latitudproyecto,
    tabla1.longitud as longitudproyecto,
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
            '0_modena'::character varying AS logototalvalorproyectos,
            vista_home_general.numvalejecobra AS totalvalorejecutadoproyectos,
            '0_modena'::character varying AS logototalvalorejecutadoproyectos,
            vista_home_general.avance AS totalavanceproyectos,
            '0_chart'::character varying AS logototalavanceproyectos,
            vista_home_general.numempdirectos AS totalempleosdirectos,
            '0_barras'::character varying AS logototalempleosdirectos,
            vista_home_general.numempindirectos AS totalempleosindirectos,
            '0_barras'::character varying AS logototalempleosindirectos,
            vista_home_general.numhabbeneficiados AS totalhabitantesbeneficiados,
            '0_barras'::character varying AS logototalhabitantesbeneficiados,
            vista_home_general.cantidadproyectos AS totalproyectos,
            '0_icn'::character varying AS logototalproyectos,
            vista_home_general.proyectosejecucion AS totalproyectosejecucion,
            '0_icn'::character varying AS logototalproyectosejecucion,
            vista_home_general.proyectosiniciar AS totalproyectosiniciar,
            '0_icn'::character varying AS logototalproyectosiniciar,
            vista_home_general.proyectosterminados AS totalproyectosterminados,
            '0_icn'::character varying AS logototalproyectosterminados,
            0::numeric AS codigocategoria,
            'Todos'::character varying AS nombrecategoria,
            '#E19E3D'::character varying AS colorcategoria,
            'btn_0'::character varying AS botoncategoriainactivo,
            'btn_0_activo'::character varying AS botoncategoriaactivo
           FROM public.vista_home_general
        UNION ALL
         SELECT vista_home_tipo.numvaltotobra AS totalvalorproyectos,
            replace('**_modena'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalvalorproyectos,
            vista_home_tipo.numvalejecobra AS totalvalorejecutadoproyectos,
            replace('**_modena'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalvalorejecutadoproyectos,
            vista_home_tipo.avance AS totalavanceproyectos,
            replace('**_chart'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalavanceproyectos,
            vista_home_tipo.numempdirectos AS totalempleosdirectos,
            replace('**_barras'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalempleosdirectos,
            vista_home_tipo.numempindirectos AS totalempleosindirectos,
            replace('**_barras'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalempleosindirectos,
            vista_home_tipo.numhabbeneficiados AS totalhabitantesbeneficiados,
            replace('**_barras'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalhabitantesbeneficiados,
            vista_home_tipo.cantidadproyectos AS totalproyectos,
            replace('**_barras'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectos,
            vista_home_tipo.proyectosejecucion AS totalproyectosejecucion,
            replace('**_icn'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectosejecucion,
            vista_home_tipo.proyectosiniciar AS totalproyectosiniciar,
            replace('**_icn'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectosiniciar,
            vista_home_tipo.proyectosterminados AS totalproyectosterminados,
            replace('**_icn'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS logototalproyectosterminados,
            vista_home_tipo.idtipo AS codigocategoria,
            vista_home_tipo.tipo AS nombrecategoria,
            '#E19E3D'::character varying AS colorcategoria,
            replace('btn_**'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS botoncategoriainactivo,
            replace('btn_**_activo'::character varying::text, '**'::character varying::text, tipoproyecto.intidtipoproyecto::character varying::text)::character varying AS botoncategoriaactivo
           FROM public.vista_home_tipo
             JOIN tipoproyecto ON tipoproyecto.intidtipoproyecto = vista_home_tipo.idtipo) tabla1;

ALTER TABLE appmobile.vista_indicadores_globales OWNER TO cobra;


------------------------- create view 

CREATE  VIEW appmobile.vista_datos_proyecto AS 
 SELECT row_number() OVER (ORDER BY tabla1.intcodigoobra)::integer AS id,
    tabla1.intcodigoobra AS codigoproyecto,
        CASE
            WHEN length(tabla1.nombreobra) > 50 THEN "substring"(tabla1.nombreobra, 0, 49) || '.....'::text
            ELSE tabla1.nombreobra
        END::character varying AS nombreproyecto,
    tabla1.imagen AS imagenproyecto,
    tabla1.objecto AS objetoproyecto,
    tabla1.fechainicio AS fechainicioproyecto,
    tabla1.fechafin AS fechafinproyecto,
    tabla1.tiempoproyecto AS duracionproyecto,
    appmobile.f_sumar_total_valor_obra(tabla1.intcodigoobra) AS valorproyecto,
    appmobile.f_avance_total_obra(tabla1.intcodigoobra) AS avanceproyecto,
    appmobile.f_proyecto_semaforo(tabla1.intcodigoobra) AS semaforoproyecto,
    appmobile.f_localidad_obra(tabla1.intcodigoobra) AS localidadproyecto,
    tabla1.latitud AS latitudproyecto,
    tabla1.longitud AS longitudproyecto,
    tabla1.tipoproyecto AS codigocategoria,
    tabla1.strcolorlineapp AS colorcategoria,
    tabla1.strlogolineapp AS imagencategoria,
    tabla1.strnombre AS nombrecategoria,
    tabla1.strdescripcionestadomobile AS estadoproyecto,
    tabla1.logoestadoproyecto AS logoestadoproyecto
   FROM ( SELECT obra.intcodigoobra,
            obra.strnombreobra AS nombreobra,
            obra.floatlatitud AS latitud,
            obra.floatlongitud AS longitud,
            obra.strimagenobra AS imagen,
            obra.strobjetoobra AS objecto,
            obra.datefeciniobra AS fechainicio,
            obra.datefecfinobra AS fechafin,    
            (obra.datefecfinobra::date - obra.datefeciniobra::date)::integer +1  as tiempoproyecto,        
            tipr.intidtipoproyecto,
            tipr.strnombre,
            tipr.strlogolineapp,
            tipr.strcolorlineapp,
            tipr.intidtipoproyecto AS tipoproyecto,
            esob.strdescripcionestadomobile,
            CONCAT(esob.intestadoobra, '_estado')::VARCHAR AS logoestadoproyecto
           FROM obra obra
             JOIN tipoobra tiob ON tiob.inttipoobra = obra.inttipoobra
             JOIN tipoproyecto tipr ON tipr.intidtipoproyecto = tiob.intidtipoproyecto
             JOIN tipoestadobra esob ON esob.intestadoobra = obra.intestadoobra) tabla1
  GROUP BY tabla1.intcodigoobra, tabla1.nombreobra, tabla1.latitud, tabla1.longitud, tabla1.imagen, tabla1.objecto, tabla1.fechainicio, tabla1.fechafin, tabla1.tiempoproyecto, tabla1.intidtipoproyecto, tabla1.intidtipoproyecto, tabla1.strnombre, tabla1.strlogolineapp, tabla1.strcolorlineapp, tabla1.tipoproyecto, tabla1.strdescripcionestadomobile, tabla1.logoestadoproyecto;

ALTER TABLE appmobile.vista_datos_proyecto OWNER TO cobra;


---------------------------------- VIEW CONTRATISTA CONTRATO OBRA 

CREATE  VIEW appmobile.vista_contratistas_contrato_proyecto AS 
 SELECT row_number() OVER (ORDER BY tabla1.codigoobra)::integer AS id,
   tabla1.codigoobra, 
   tabla1.nombreobra,
   tabla1.codigocontrato,
   tabla1.nombrecontrato,
   tabla1.codigocontratista,
   tabla1.nombrecontratista
   FROM ( SELECT ob.intcodigoobra AS codigoobra, 
	       ob.strnombreobra::VARCHAR AS nombreobra, 
	       co.intidcontrato AS codigocontrato,  
	       co.strnombre AS nombrecontrato,  
	       te.intcodigo AS codigocontratista, 
	       CONCAT(te.strnombre, te.strapellido1, te.strapellido2)::VARCHAR AS nombrecontratista
	FROM obra AS ob
		INNER JOIN relacioncontratoobra AS roc ON roc.intcodigoobra = ob.intcodigoobra
		INNER JOIN contrato AS co ON co.intidcontrato = roc.intidcontrato
		INNER JOIN contratocontratista AS cc ON cc.intidcontrato = co.intidcontrato
		INNER JOIN autenticacion.contratista AS ac ON ac.intcodigo = cc.intcodigo
		INNER JOIN autenticacion.tercero AS te ON te.intcodigo = ac.intcodigo) tabla1
  GROUP BY tabla1.codigoobra, tabla1.nombreobra, tabla1.codigocontrato, tabla1.nombrecontrato, tabla1.codigocontratista, tabla1.nombrecontratista;

ALTER TABLE appmobile.vista_contratistas_contrato_proyecto OWNER TO cobra;



--------------------------------- TABLA QUE CLONA LOS CAMPOS LA FUNCION encuestasue.f_calcular_indicadores_ue
DROP TABLE encuestasue.funcion_indicadores_encuestas;
CREATE TABLE encuestasue.funcion_indicadores_encuestas(p_num_ind_2 numeric,
    p_obra serial,
    p_den_ind_2 numeric,
    p_ind_2 numeric,
    p_base_ind_2 numeric,
    p_esperado_ind_2 numeric,
    p_num_ind_3 numeric,
    p_den_ind_3 numeric,
    p_ind_3 numeric,
    p_base_ind_3 numeric,
    p_esperado_ind_3 numeric,
    p_num_ind_4 numeric,
    p_den_ind_4 numeric,
    p_ind_4 numeric,
    p_base_ind_4 numeric,
    p_esperado_ind_4 numeric,
    p_num_ind_5 numeric,
    p_den_ind_5 numeric,
    p_ind_5 numeric,
    p_base_ind_5 numeric,
    p_esperado_ind_5 numeric,
    p_num_ind_6 numeric,
    p_den_ind_6 numeric,
    p_ind_6 numeric,
    p_base_ind_6 numeric,
    p_esperado_ind_6 numeric,
    p_num_ind_7 numeric,
    p_den_ind_7 numeric,
    p_ind_7 numeric,
    p_base_ind_7 numeric,
    p_esperado_ind_7 numeric,
    p_num_ind_8 numeric,
    p_den_ind_8 numeric,
    p_ind_8 numeric,
    p_base_ind_8 numeric,
    p_esperado_ind_8 numeric,
    p_num_ind_9 numeric,
    p_den_ind_9 numeric,
    p_ind_9 numeric,
    p_base_ind_9 numeric,
    p_esperado_ind_9 numeric,
    p_num_ind_10 numeric,
    p_den_ind_10 numeric,
    p_ind_10 numeric,
    p_base_ind_10 numeric,
    p_esperado_ind_10 numeric,
    p_num_ind_11 numeric,
    p_den_ind_11 numeric,
    p_ind_11 numeric,
    p_base_ind_11 numeric,
    p_esperado_ind_11 numeric,
    p_num_ind_12 numeric,
    p_den_ind_12 numeric,
    p_ind_12 numeric,
    p_base_ind_12 numeric,
    p_esperado_ind_12 numeric,
    p_num_ind_13 numeric,
    p_den_ind_13 numeric,
    p_ind_13 numeric,
    p_base_ind_13 numeric,
    p_esperado_ind_13 numeric,
    p_num_ind_org_1 numeric,
    p_den_ind_org_1 numeric,
    p_ind_org_1 numeric,
    p_base_ind_org_1 numeric,
    p_esperado_ind_org_1 numeric,
    p_num_ind_org_2 numeric,
    p_den_ind_org_2 numeric,
    p_ind_org_2 numeric,
    p_base_ind_org_2 numeric,
    p_esperado_ind_org_2 numeric,
    p_num_ind_org_3 numeric,
    p_den_ind_org_3 numeric,
    p_ind_org_3 numeric,
    p_base_ind_org_3 numeric,
    p_esperado_ind_org_3 numeric,
    p_num_ind_org_4 numeric,
    p_den_ind_org_4 numeric,
    p_ind_org_4 numeric,
    p_base_ind_org_4 numeric,
    p_esperado_ind_org_4 numeric,
    p_num_ind_org_5 numeric,
    p_den_ind_org_5 numeric,
    p_ind_org_5 numeric,
    p_base_ind_org_5 numeric,
    p_esperado_ind_org_5 numeric);











