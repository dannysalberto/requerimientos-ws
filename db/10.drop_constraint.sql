ALTER TABLE public.actividadobra
  DROP CONSTRAINT fkusuario4;
CREATE SEQUENCE public.seq_actividad_obra_new
   INCREMENT 1
   START 6000
   MINVALUE 6000
   MAXVALUE 600000;
ALTER SEQUENCE public.seq_actividad_obra_new
  OWNER TO cobra;


alter table public.actividadobra add column fechainicionew date;
alter table public.actividadobra add column fechafinnew date;
update public.actividadobra  set fechainicionew = fechainicio;
update public.actividadobra  set fechafinnew = fechafin;

alter table public.actividadobra drop column fechainicio cascade; 
alter table public.actividadobra drop column fechafin;


alter table public.actividadobra rename fechainicionew to fechainicio;
alter table public.actividadobra rename fechafinnew to fechafin;

CREATE OR REPLACE VIEW public.v_actividadobra AS 
 SELECT a.oidactiviobra,
    a.strdescactividad,
    b.nombre AS categoria,
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
    categoria b
  WHERE a.idcategoria = b.id;

ALTER TABLE public.v_actividadobra
  OWNER TO postgres;
