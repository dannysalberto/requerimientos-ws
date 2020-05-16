update relacionindicadordetalleobra set strvalorejecutado=0; delete from relacionalimentacionfactoratraso;delete from alimentacioncualificacion; delete from alimentacion; update obra set numvalejecobra=0; update actividadobra set numvalorejecutao = 0, floatcantidadejecutao = 0;

select * from alimentacion where intidalimenta >= 439969;
select * from relacionindicadordetalleobra where intcodigoobra = 6406;
select * from alimentacioncualificacion where intidalimenta >= 439969;
select * from relacionalimentacionfactoratraso where intidalimenta >= 439969;
select * from relacionalimentacionactividad where intidalimenta >= 439969;
