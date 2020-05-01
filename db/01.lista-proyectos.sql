update configuracion.configuracion_siente set strvalorparametro='condor' where strcodigoparametro='nombreContextoSiente';
--------------- se crea el esquema para las vistas de movil
DROP SCHEMA IF EXISTS appmobile CASCADE;
CREATE SCHEMA appmobile  AUTHORIZATION cobra;

---------------------------------------- VIEW LISTA DE PROYECTOS
DROP VIEW IF EXISTS appmobile.vista_proyectos;
CREATE OR REPLACE VIEW appmobile.vista_proyectos AS 
select 
	row_number() OVER (ORDER BY obra.intcodigoobra)::integer AS id, 
	obra.intcodigoobra codigoproyecto,
	CASE
	    WHEN length(obra.strnombreobra) > 50 THEN substring(obra.strnombreobra, 0, 49) || '...'::text
	    ELSE obra.strnombreobra
	END::character varying AS nombreproyecto,
	obra.numvaltotobra valorproyecto,
	obra.numvalejecobra valorejecutado,
	split_part(split_part(f_proyecto_semaforo(obra.intcodigoobra),'/',4),'.',1)::character varying(250) semaforoproyecto,
	obra.intlineanegocio codigocategoria,
	obra.strdesclineanegocio nombrecategoria,
	(select strvalorparametro from configuracion.configuracion_siente where strcodigoparametro='direccionIPContexto')
	||(select strvalorparametro from configuracion.configuracion_siente where strcodigoparametro='nombreContextoSiente')
	||split_part(obra.strurllogo,'.svg',1)||'.png' imagencategoria,
	obra.strcolor colorcategoria,
	obra.usu_login usuario
from (
	select 
		obra.intcodigoobra,
		obra.strnombreobra,
		obra.numvaltotobra,
		obra.numvalejecobra,
		ln.intlineanegocio,
		ln.strdesclineanegocio,
		ln.strurllogo,
		ln.strcolor,
		usu.usu_login                    
	from obra
	inner join lineanegocio ln on ln.intlineanegocio = obra.lineanegocio
	inner join relacionobrajsf_usuario ou on ou.intcodigoobra = obra.intcodigoobra
	inner join autenticacion.jsf_usuario usu on usu.usu_id=ou.usu_id
	where obra.intestadoobra=1
	UNION
	select 
		obra.intcodigoobra,
		obra.strnombreobra,
		obra.numvaltotobra,
		obra.numvalejecobra,
		ln.intlineanegocio,
		ln.strdesclineanegocio,
		ln.strurllogo,
		ln.strcolor,
		sup.usu_login                    
	from obra
	inner join lineanegocio ln on ln.intlineanegocio = obra.lineanegocio
	inner join autenticacion.tercero tersup on tersup.intcodigo = obra.codigo_supervisor
	inner join autenticacion.jsf_usuario sup on sup.usu_ter_cdigo=tersup.intcodigo
	where obra.intestadoobra=1
	UNION
	select 
		obra.intcodigoobra,
		obra.strnombreobra,
		obra.numvaltotobra,
		obra.numvalejecobra,
		ln.intlineanegocio,
		ln.strdesclineanegocio,
		ln.strurllogo,
		ln.strcolor,
		contratista.usu_login                    
	from obra
	inner join lineanegocio ln on ln.intlineanegocio = obra.lineanegocio
	inner join relacioncontratoobra co on co.intcodigoobra = obra.intcodigoobra
	inner join contrato contcontratista on (contcontratista.intidcontrato = co.intidcontrato)
	inner join contratocontratista on contratocontratista.intidcontrato = contcontratista.intidcontrato
	inner join autenticacion.jsf_usuario contratista on contratista.usu_ter_cdigo=contratocontratista.intcodigo
	where obra.intestadoobra=1
) obra;

ALTER TABLE appmobile.vista_proyectos OWNER TO cobra;
------------------------------------------------------------------
