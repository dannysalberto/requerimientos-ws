DROP VIEW IF EXISTS public.vista_proyectos_convenios_excel_ch;
DROP VIEW IF EXISTS vista_movimientos_financieros_convenios;

ALTER TABLE public.contrato
   ALTER COLUMN intentidad TYPE INT USING CAST(intentidad AS INT);

 -- View: public.vista_proyectos_convenios_excel_ch

-- DROP VIEW public.vista_proyectos_convenios_excel_ch;

CREATE OR REPLACE VIEW public.vista_proyectos_convenios_excel_ch AS 
 SELECT vista_recursividad_convenios.convmacro AS codigoconvsiente,
    sector.strnombre,
    contrato.strnumcontrato AS numconvenio,
    contrato.datefechaini AS feciniconvenio,
    contrato.datefechafin AS fecfinconvenio,
    contrato.numvlrcontrato AS valorconvenio,
    tercero.strnombrecompleto AS entidadconvenio,
    contrato.numrecursospropios,
    contrato.numrecursosch,
    contrato.numrecursostercero,
    contrato.textobjeto AS objetoconvenio,
    obra.intcodigoobra AS codigoobrasiente,
    obra.strnombreobra,
    tipoobra.strdesctipoobra,
    tipoestadobra.strdesctipoestado,
    obra.floatlatitud,
    obra.floatlongitud,
    obra.strobjetoobra,
    obra.datefeciniobra,
    obra.datefecfinobra,
    obra.numvaldeclarado,
    obra.numvalavanfisicodeclarado,
    obra.numvalavanfinanciaerodeclarado,
    obra.numvalprogramejec,
    obra.boolobraterminada,
    contrato.inttipificacionadicional,
    vista_recursividad_convenios.convmacro::integer AS codconvenio
   FROM vista_recursividad_convenios
     JOIN obra ON obra.intconvenio = vista_recursividad_convenios.intidcontrato
     JOIN tipoobra ON obra.inttipoobra = tipoobra.inttipoobra
     JOIN tipoestadobra ON obra.intestadoobra = tipoestadobra.intestadoobra
     JOIN contrato ON vista_recursividad_convenios.convmacro::integer = contrato.intidcontrato
     JOIN autenticacion.tercero ON tercero.intcodigo = contrato.intentidad
     LEFT JOIN sector ON sector.intsector = tercero.intsector
  ORDER BY (vista_recursividad_convenios.convmacro::integer);

ALTER TABLE public.vista_proyectos_convenios_excel_ch
  OWNER TO cobra;
GRANT ALL ON TABLE public.vista_proyectos_convenios_excel_ch TO cobra;

-- View: public.vista_movimientos_financieros_convenios

-- DROP VIEW public.vista_movimientos_financieros_convenios;

CREATE OR REPLACE VIEW public.vista_movimientos_financieros_convenios AS 
 SELECT contrato.intidcontrato,
    contrato.strnumcontrato,
    contrato.textobjeto,
    contrato.numvlrcontrato,
    ordendepago.oidcodigoordenpago,
    tercero.strnombrecompleto,
    movimiento.oidcodigomovimiento,
    movimiento.numvlrmovimiento,
    movimiento.numvlrejecutado,
    movimiento.numvlrlegalizado,
    movimiento.numvlrreintegro,
    movimiento.datefechamovimientoentidad,
    tipomovimiento.strnombretipomovimiento,
    ordendepago.intnumcargofiduciario,
    movimiento.strnombrecontratista
   FROM financiera.movimiento
     JOIN financiera.ordendepago ON movimiento.oidcodigoordenpago = ordendepago.oidcodigoordenpago
     JOIN financiera.encargofiduciario ON encargofiduciario.intnumencargofiduciario = ordendepago.intnumcargofiduciario
     JOIN contrato ON contrato.numcargofiduciario = encargofiduciario.intnumencargofiduciario
     JOIN autenticacion.tercero ON contrato.intentidad = tercero.intcodigo
     JOIN financiera.tipomovimiento ON tipomovimiento.oidtipomovimiento = movimiento.oidtipomovimiento
  WHERE (contrato.intidcontrato IN ( SELECT DISTINCT vista_recursividad_convenios.intidcontrato
           FROM vista_recursividad_convenios));

ALTER TABLE public.vista_movimientos_financieros_convenios
  OWNER TO cobra;
GRANT ALL ON TABLE public.vista_movimientos_financieros_convenios TO cobra;


