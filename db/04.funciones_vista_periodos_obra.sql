DROP VIEW IF EXISTS appmobile.vista_periodos_obra;
DROP FUNCTION IF EXISTS appmobile.f_porcentaje_proyectado_periodo(integer, date);
DROP FUNCTION IF EXISTS appmobile.f_fecha_ultimo_avance(integer);

--------------------------- FUNCTION PORCENTAJE PROGRAMADO

CREATE OR REPLACE FUNCTION appmobile.f_porcentaje_proyectado_periodo(p_intcodigoobra integer, p_fechaincio date)
  RETURNS NUMERIC AS
$BODY$
DECLARE
    v_valor_periodo NUMERIC;
    v_valor_total NUMERIC;
    v_total_proyectado NUMERIC;    
    v_total_proyectado_porcentaje NUMERIC;
BEGIN

	SELECT SUM(numvaltotplanif) 
	INTO v_valor_periodo
	FROM periodo  
	WHERE intcodigoobra = p_intcodigoobra AND datefeciniperiodo <= p_fechaincio;

	SELECT SUM(numvaltotplanif) 
	INTO v_valor_total
	FROM periodo  
	WHERE intcodigoobra = p_intcodigoobra;

	SELECT COALESCE(v_valor_periodo,0) / COALESCE(v_valor_total,0)
	INTO v_total_proyectado;

	SELECT COALESCE(v_total_proyectado,0) * 100
	INTO v_total_proyectado_porcentaje;

	RETURN v_total_proyectado_porcentaje;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_porcentaje_proyectado_periodo(integer, date) OWNER TO cobra;

--------------------------- FUNCTION ULTIMO AVANCE

CREATE OR REPLACE FUNCTION appmobile.f_fecha_ultimo_avance(p_intcodigoobra integer)
  RETURNS date AS
$BODY$
DECLARE
    v_fecha_ultimo_avance DATE;
    v_fecha_primer_periodo DATE;
BEGIN

	SELECT datefecha 
	INTO v_fecha_ultimo_avance
	FROM alimentacion  
	WHERE intcodigoobra = p_intcodigoobra AND aprobado = TRUE
	ORDER BY intidalimenta
	LIMIT 1;    

	SELECT MIN(datefeciniperiodo)  
	INTO v_fecha_primer_periodo
	FROM periodo  
	WHERE intcodigoobra = 6420;

	SELECT CASE WHEN v_fecha_ultimo_avance IS NULL 
            THEN v_fecha_primer_periodo 
            ELSE v_fecha_ultimo_avance
	END
	INTO v_fecha_ultimo_avance; 

	
    RETURN v_fecha_ultimo_avance;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_fecha_ultimo_avance(integer)
  OWNER TO cobra;


--------------------------- VIEW VISTA PERIODOS OBRA

CREATE OR REPLACE VIEW appmobile.vista_periodos_obra AS 
 SELECT pe.intidperiodo AS id,
    pe.datefeciniperiodo::character varying AS fechainicioperiodo,
    pe.datefecfinperiodo::character varying AS fechafinperiodo,
    appmobile.f_porcentaje_proyectado_periodo(pe.intcodigoobra, pe.datefeciniperiodo)  AS porcentajeproyectado,
    pe.intcodigoobra AS codigoproyecto
   FROM periodo pe
  WHERE pe.datefeciniperiodo > appmobile.f_fecha_ultimo_avance(pe.intcodigoobra);

ALTER TABLE appmobile.vista_periodos_obra OWNER TO cobra;





