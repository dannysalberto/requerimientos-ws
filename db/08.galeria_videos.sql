
CREATE SEQUENCE public.galeriavideos_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.galeriavideos_id_seq
  OWNER TO cobra;

CREATE TABLE public.galeriavideos
(
  id integer NOT NULL DEFAULT nextval('galeriavideos_id_seq'::regclass),
  obra_id integer,
  tipovideo integer,
  nombre character varying(50),
  url character varying(250),
  fecha_carga date,
  CONSTRAINT pk_galeriavideos PRIMARY KEY (id),
  CONSTRAINT fk_galeriavideo_obra FOREIGN KEY (obra_id)
      REFERENCES public.obra (intcodigoobra) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.galeriavideos
  OWNER TO cobra;
