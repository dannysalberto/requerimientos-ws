
CREATE SEQUENCE public.categoria_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.categoria_id_seq
  OWNER TO cobra;

CREATE TABLE public.categoria
(
  id integer NOT NULL DEFAULT nextval('categoria_id_seq'::regclass),
  codigo character varying(6) NOT NULL,
  nombre character varying(50) NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.categoria
  OWNER TO cobra;

INSERT INTO public.categoria(
            id, codigo, nombre)
    VALUES (1,'01','General');

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
  OWNER TO cobra;
