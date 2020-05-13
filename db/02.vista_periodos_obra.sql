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


