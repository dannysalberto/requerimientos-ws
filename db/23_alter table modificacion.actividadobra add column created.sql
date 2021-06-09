alter table modificacion.actividadobra add column created_at timestamp without time zone;
alter table modificacion.actividadobra add column updated_at timestamp without time zone;
alter table modificacion.actividadobra add column create_by character varying(20);
alter table modificacion.actividadobra add column update_by character varying(20);

