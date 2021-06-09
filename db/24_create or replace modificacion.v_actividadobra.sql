create or replace view modificacion.v_actividadobra as SELECT *
   FROM modificacion.actividadobra
  WHERE actividadobra.tipomodificacion::text <> 'D'::text;