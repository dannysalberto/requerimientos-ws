ALTER TABLE public.actividadobra
  DROP CONSTRAINT fkusuario4;
CREATE SEQUENCE public.seq_actividad_obra_new
   INCREMENT 1
   START 6000
   MINVALUE 6000
   MAXVALUE 600000;
ALTER SEQUENCE public.seq_actividad_obra_new
  OWNER TO cobra;
