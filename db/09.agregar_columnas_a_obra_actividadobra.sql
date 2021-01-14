alter table public.actividadobra add valortotalactividadaiu numeric(20,6);

alter table public.actividadobra add valorunitario numeric(20,6);



alter table public.obra add actividades int;

alter table public.obra add costo_directo numeric(20,6);
alter table public.obra add costo_directo_aiu numeric(20,6);
alter table public.obra add costo_total numeric(20,6);
alter table public.obra add intidperiomedida int not null; 

alter table public.actividadobra add idcategoria int ; 
alter table public.actividadobra add porcentajeavance int ; 

update public.actividadobra set idcategoria=1;

