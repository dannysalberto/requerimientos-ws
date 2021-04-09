SELECT setval('planoperativo.fuenterecursosconvenio_idfuenterecursosconvenio_seq', (SELECT MAX(idfuenterecursosconvenio)::INTEGER FROM planoperativo.fuenterecursosconvenio));

DROP TABLE IF EXISTS planoperativo.fuenterecursosconvenio_log;
CREATE TABLE planoperativo.fuenterecursosconvenio_log
(
  idfuenterecursosconvenio integer NOT NULL,
  fkconvenio integer,
  fkentidad integer,
  fkrolentidad integer,
  valoraportado numeric(20,6),
  otrasreservas numeric(20,6),
  reservaiva numeric(20,6),
  valorcuotagerencia numeric(20,6),
  tipoaporte integer,
  valordisponible numeric(20,6),
  vigencia integer,
  porcentajegerencia double precision,
  rpc character varying(100),
  cdp character varying(100),
  contrato_origen integer,
  log_at timestamp default CURRENT_TIMESTAMP,
  --log_usu_id integer,
  CONSTRAINT fuenterecursosconvenio_log_pkey PRIMARY KEY (idfuenterecursosconvenio),
  CONSTRAINT fuenterecursosconvenio_log_fkconvenio_fkey FOREIGN KEY (fkconvenio)
      REFERENCES public.contrato (intidcontrato) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fuenterecursosconvenio_log_fkentidad_fkey FOREIGN KEY (fkentidad)
      REFERENCES autenticacion.tercero (intcodigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fuenterecursosconvenio_log_fkrolentidad_fkey FOREIGN KEY (fkrolentidad)
      REFERENCES planoperativo.rolentidad (idrolentidad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION--,
  --CONSTRAINT fuenterecursosconvenio_log_log_usu_id_fkey FOREIGN KEY (log_usu_id)
      --REFERENCES autenticacion.jsf_usuario (usu_id) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE planoperativo.fuenterecursosconvenio_log
  OWNER TO cobra;
GRANT ALL ON TABLE planoperativo.fuenterecursosconvenio_log TO cobra;