alter table solicitudfpo add intcodigoobra integer not null;

create view v_obra_fpo as select a.intcodigoobra,a.fpocompromisoenergia,b.intcodigo,
COALESCE(b.strnombre,'')||' '||COALESCE(b.strapellido1,'') as nombreentidad from obra a, autenticacion.tercero b
where a.contratista = b.intcodigo;

ALTER TABLE public.obra
    ADD CONSTRAINT fk_obra_contratista FOREIGN KEY (contratista)
    REFERENCES autenticacion.tercero (intcodigo) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
CREATE INDEX fki_fk_obra_contratista
    ON public.obra(contratista);