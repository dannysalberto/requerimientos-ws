drop view modificacion.v_relacioncontratoobra ;

create view modificacion.v_relacioncontratoobra as
 SELECT o.intcodigoobra AS id,
    o.intcodigoobra,
    cto.intidcontrato
   FROM obra o
     LEFT JOIN ( SELECT rco.intcodigoobra,
            min(rco.intidcontrato) AS intidcontrato
           FROM relacioncontratoobra rco
             JOIN contrato pc ON rco.intidcontrato = pc.intidcontrato
          WHERE pc.inttipocontrato > 0
          GROUP BY rco.intcodigoobra) cto ON o.intcodigoobra = cto.intcodigoobra;