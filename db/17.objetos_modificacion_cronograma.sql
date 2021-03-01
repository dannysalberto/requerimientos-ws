CREATE SCHEMA modificacion;

CREATE TABLE modificacion.obra
(
  id serial NOT NULL,
  fechamodificacion date NOT NULL,
  datefecfinobra date NOT NULL,
  datefeciniciobra date NOT NULL,
  intcodigoobra integer NOT NULL,
  justificmodificacion character varying(250) NOT NULL,
  intidperiomedida integer NOT NULL,
  intplazoobra integer NOT NULL,
  estadomodificacion character varying(1),
  CONSTRAINT obra_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE modificacion.obra
  OWNER TO cobra;

-- Index: modificacion.idx_obra

-- DROP INDEX modificacion.idx_obra;

CREATE INDEX idx_obra
  ON modificacion.obra
  USING btree
  (intcodigoobra);



CREATE TABLE modificacion.actividadobra
(
  id integer NOT NULL,
  boolaiu boolean NOT NULL,
  fechafin date,
  fechainicio date,
  floatcantidadejecutao double precision,
  floatcantplanifao double precision NOT NULL,
  idcategoria integer NOT NULL,
  intcodigoobra integer NOT NULL,
  numvalorejecutao numeric(20,6),
  numvalorplanifao numeric(19,2),
  oidactiviobra oid NOT NULL,
  strdescactividad character varying(200) NOT NULL,
  strtipounidadmed character varying(10) NOT NULL,
  tipomodificacion character varying(1),
  valortotalactividadaiu numeric(20,6),
  valorunitario numeric(19,2),
  obra_id integer NOT NULL,
  CONSTRAINT actividadobra_pkey PRIMARY KEY (id),
  CONSTRAINT fk6rm74swr0u28usqwsggxym4g1 FOREIGN KEY (obra_id)
      REFERENCES modificacion.obra (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE modificacion.actividadobra
  OWNER TO cobra;


CREATE TABLE modificacion.periodo
(
  id integer NOT NULL,
  datefecfinperiodo date NOT NULL,
  datefeciniperiodo date NOT NULL,
  intidperiodo integer,
  numvaltotplanif numeric NOT NULL,
  intcodigoobra integer NOT NULL,
  CONSTRAINT periodo_pkey PRIMARY KEY (id),
  CONSTRAINT fkb9p8s9dm6p7cl4smkx0knqqu5 FOREIGN KEY (intcodigoobra)
      REFERENCES public.obra (intcodigoobra) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE modificacion.periodo
  OWNER TO cobra;


CREATE TABLE modificacion.relacionactividadobraperiodo
(
  id integer NOT NULL,
  oidnmid oid NOT NULL,
  actividadobra_id integer,
  floatcantplanif numeric(20,6),
  intidperiodo numeric(20,6),
  numvalplanif numeric(20,6),
  oidactiviobra oid,
  CONSTRAINT relacionactividadobraperiodo_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE modificacion.relacionactividadobraperiodo
  OWNER TO cobra;



 