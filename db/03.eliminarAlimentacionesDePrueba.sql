update relacionindicadordetalleobra set strvalorejecutado=0 where intcodigoobra = 6406;
delete from relacionalimentacionfactoratraso where intidalimenta >= 439969;
delete from alimentacioncualificacion where intidalimenta >= 439969;
delete from alimentacion where intidalimenta >= 439969;

select * from alimentacion where intidalimenta >= 439969;
select * from relacionindicadordetalleobra where intcodigoobra = 6406;
select * from alimentacioncualificacion where intidalimenta >= 439969;
select * from relacionalimentacionfactoratraso where intidalimenta >= 439969;
