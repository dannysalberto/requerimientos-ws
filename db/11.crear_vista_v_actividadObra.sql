DROP VIEW public.v_actividadobra;

CREATE OR REPLACE VIEW public.v_actividadobra AS 
 SELECT a.oidactiviobra,
    a.strdescactividad,
    b.nombre AS categoria,a.idcategoria,
    a.strtipounidadmed,
    a.floatcantplanifao,
    a.fechainicio,
    a.fechafin,
    a.intcodigoobra,
    a.valorunitario,
    a.numvalorplanifao,
    a.valortotalactividadaiu,
    a.floatcantidadejecutao,
    a.porcentajeavance
   FROM actividadobra a,
    public.categoria b
  WHERE a.idcategoria = b.id;

ALTER TABLE public.v_actividadobra
  OWNER TO postgres;
