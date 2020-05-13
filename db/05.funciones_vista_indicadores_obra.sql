CREATE OR REPLACE FUNCTION appmobile.f_porcentaje_avance_indicador(valorprogramado NUMERIC, valorejecutado NUMERIC)
  RETURNS NUMERIC(20,6) AS
$BODY$
DECLARE
    v_total_avance NUMERIC;    
    v_total_avance_porcentaje NUMERIC;
BEGIN

	SELECT COALESCE(valorejecutado,0) / COALESCE(valorprogramado,0)
	INTO v_total_avance;

	SELECT COALESCE(v_total_avance,0) * 100
	INTO v_total_avance_porcentaje;

	RETURN v_total_avance_porcentaje;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION appmobile.f_porcentaje_avance_indicador(NUMERIC, NUMERIC) OWNER TO cobra;


CREATE OR REPLACE VIEW appmobile.vista_indicadores_obra AS 
	SELECT rio.intidindicadortipodet AS id,
		rio.intcodigoobra AS codigoproyecto, 
		itd.strnombre AS descripcionindicadoralcance,
		itd.strmedida AS unidadmedida,
		rio.strvalor as cantidadprogramada,
		rio.strvalorejecutado AS cantidadejecutada,
		appmobile.f_porcentaje_avance_indicador(rio.strvalor,rio.strvalorejecutado) AS porcentajeAvance
	FROM relacionindicadordetalleobra AS rio 
		INNER JOIN indicadortipodetalle AS itd ON rio.intidindicadortipodet = itd.intidindicadortipodet;
ALTER TABLE appmobile.vista_indicadores_obra OWNER TO cobra;
