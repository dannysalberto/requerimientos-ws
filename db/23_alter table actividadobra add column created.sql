alter table actividadobra add column created_at timestamp without time zone;
alter table actividadobra add column updated_at timestamp without time zone;
alter table actividadobra add column create_by character varying(20);
alter table actividadobra add column update_by character varying(20);

