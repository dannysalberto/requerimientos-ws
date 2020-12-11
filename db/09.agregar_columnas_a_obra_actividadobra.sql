alter table public.actividadobra add valortotalactividadaiu numeric(20,6);

alter table public.actividadobra add valorunitario numeric(20,6);


create table public.categoria (id serial,codigo varchar(6) not null,nombre varchar(50) not null);

alter table public.obra add costo_directo numeric(20,6);
alter table public.obra add costo_directo_aiu numeric(20,6);
alter table public.obra add costo_total numeric(20,6);
alter table public.obra add intidperiomedida int not null; 

alter table public.actividadobra add idcategoria int ; 
alter table public.actividadobra add porcentajeavance int ; 

update public.actividadobra set idcategoria=1;
insert into public.categoria values(1,'01','Genérica');
create view public.v_actividadobra as
select a.oidactiviobra,a.strdescactividad,b.nombre as categoria,a.strtipounidadmed,a.floatcantplanifao,a.fechainicio,a.fechafin,a.intcodigoobra,
a.valorunitario,a.numvalorplanifao,a.valortotalactividadaiu,a.floatcantidadejecutao,a.porcentajeavance from public.actividadobra a,categoria b where a.idcategoria=b.id;
