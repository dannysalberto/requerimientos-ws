---------------------------------------- VIEW LISTA DE ACTIVIDADES
DROP VIEW IF EXISTS appmobile.vista_actividades;
CREATE OR REPLACE VIEW appmobile.vista_actividades AS
select 
	row_number() OVER (ORDER BY actividadobra.oidactiviobra)::integer AS id,
	actividadobra.oidactiviobra actividad_id,
	actividadobra.intcodigoobra codigo_proyecto,
	actividadobra.strdescactividad descripcion_actividad,
	actividadobra.strtipounidadmed unidad_medida,
	actividadobra.numvalorplanifao valor_unitario,
	actividadobra.floatcantplanifao cantidad_programada,
	actividadobra.floatcantidadejecutao cantidad_ejecutada,
	(actividadobra.numvalorplanifao * actividadobra.floatcantplanifao)::numeric(20,6) valor_programado,
	actividadobra.numvalorejecutao valor_ejecutado,
	(case when actividadobra.floatcantplanifao = 0.0 then 0.0 
	else 100 * actividadobra.floatcantidadejecutao / actividadobra.floatcantplanifao
	end)::float porcentaje_avance
	
from 
	actividadobra;
ALTER VIEW appmobile.vista_actividades OWNER TO cobra;
------------------------------------------------------------------	