alter table modificacion.actividadobra add newfechainicio DATE;
alter table modificacion.actividadobra add newfechafin DATE;
alter table modificacion.actividadobra add newnumvalorplanifao numeric(20,6);
alter table modificacion.actividadobra add newfloatcantplanifao numeric(20,6);
alter table modificacion.actividadobra add newvalorunitario numeric(20,6);
alter table modificacion.actividadobra add newvalortotalactividadaiu numeric(20,6);
alter table modificacion.obra add newdatefecfinobra date;
alter table modificacion.obra add newintplazoobra integer;
alter table modificacion.obra add numvaltotobra numeric(20,6);
alter table modificacion.periodo add obra_id integer;
alter table modificacion.obra add newcosto_directo numeric(20,6);



alter table modificacion.relacionactividadobraperiodo add periodomodificacion integer;

alter table modificacion.periodo drop constraint fkb9p8s9dm6p7cl4smkx0knqqu5;

alter table modificacion.periodo add constraint fk_obramod_periodo FOREIGN KEY (obra_id)
REFERENCES modificacion.obra (id) MATCH SIMPLE
      ON DELETE CASCADE;

alter table modificacion.actividadobra drop constraint fk6rm74swr0u28usqwsggxym4g1;
alter table modificacion.actividadobra add constraint fk_obramod_actividadobra FOREIGN KEY (obra_id)
REFERENCES modificacion.obra (id) MATCH SIMPLE ON DELETE CASCADE;

