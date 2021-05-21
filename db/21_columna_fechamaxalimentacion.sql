

alter table modificacion.obra add fechaminimafin date; 
ALTER TABLE modificacion.actividadobra
    ALTER COLUMN strdescactividad TYPE character varying(240) COLLATE pg_catalog."default";