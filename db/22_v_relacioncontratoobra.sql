drop view modificacion.v_relacioncontratoobra;
create view modificacion.v_relacioncontratoobra(id,intcodigoobra,intidcontrato) as
SELECT o.intcodigoobra,o.intcodigoobra,
		   cto.intidcontrato
    FROM "public"."obra" o LEFT JOIN
	(SELECT rco.intcodigoobra as intcodigoobra, 
	MIN(rco.intidcontrato) as intidcontrato 
	FROM "public"."relacioncontratoobra" rco
 		INNER JOIN "public"."contrato" pc ON rco.intidcontrato=pc.intidcontrato 
        WHERE pc.booltipocontratoconvenio=False AND pc.inttipocontrato IS NOT NULL 
        GROUP BY rco.intcodigoobra) cto on o.intcodigoobra=cto.intcodigoobra