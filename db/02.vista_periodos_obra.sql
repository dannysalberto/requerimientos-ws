CREATE OR REPLACE FUNCTION appmobile.f_porcentaje_proyectado_periodo(p_intcodigoobra integer, p_fechaincio date)
  RETURNS NUMERIC AS
$BODY$
DECLARE
    v_valor_proyectodo NUMERIC;
    v_valor_total NUMERIC;
    v_total_proyectado NUMERIC;    
    v_total_proyectado_porcentaje NUMERIC;
BEGIN

	SELECT SUM(numvaltotplanif) 
	INTO v_valor_proyectodo
	FROM periodo  
	WHERE intcodigoobra = p_intcodigoobra AND datefeciniperiodo <= p_fechaincio;

	SELECT SUM(numvaltotplanif) 
	INTO v_valor_total
	FROM periodo  
	WHERE intcodigoobra = p_intcodigoobra;

	SELECT COALESCE(v_total_proyectado,0) * 100
	INTO v_total_proyectado_porcentaje;
	
    RETURN v_total_proyectado_porcentaje;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_porcentaje_proyectado_periodo(integer, date) OWNER TO cobra;


CREATE OR REPLACE FUNCTION appmobile.f_fecha_ultimo_avance(p_intcodigoobra integer) RETURNS DATE
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_fecha_ultimo_avance DATE;
BEGIN

	SELECT datefecha 
	INTO v_fecha_ultimo_avance
	FROM alimentacion  
	WHERE intcodigoobra = p_intcodigoobra AND aprobado = TRUE
	ORDER BY intidalimenta
	LIMIT 1;     
    RETURN v_fecha_ultimo_avance;
END
$$;

DROP VIEW IF EXISTS appmobile.vista_periodos_obra;
CREATE OR REPLACE VIEW appmobile.vista_periodos_obra AS
	SELECT pe.intidperiodo AS id,
		pe.datefeciniperiodo AS fechainicioperiodo,
		pe.datefecfinperiodo AS fechafinperiodo,
		pe.numvaltotplanif AS porcentajeproyectado,
		pe.intcodigoobra AS codigoproyecto
	FROM periodo AS pe 	
	WHERE pe.datefeciniperiodo > appmobile.f_fecha_ultimo_avance(pe.intcodigoobra);
ALTER TABLE appmobile.vista_periodos_obra OWNER TO cobra;


