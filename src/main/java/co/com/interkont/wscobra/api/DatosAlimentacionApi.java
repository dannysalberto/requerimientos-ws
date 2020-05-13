package co.com.interkont.wscobra.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import co.com.interkont.wscobra.api.request.ActividadRequest;
import co.com.interkont.wscobra.api.request.AlimentacionRequest;
import co.com.interkont.wscobra.api.request.AspectoEvaluarRequest;
import co.com.interkont.wscobra.api.request.DatosAlimentacionRequest;
import co.com.interkont.wscobra.api.request.FactorAtrasoRequest;
import co.com.interkont.wscobra.api.request.ImagenRequest;
import co.com.interkont.wscobra.api.request.IndicadorAlcanceRequest;
import co.com.interkont.wscobra.api.response.ActividadResponse;
import co.com.interkont.wscobra.api.response.AlimentacionResponse;
import co.com.interkont.wscobra.api.response.DatosAlimentacionResponse;
import co.com.interkont.wscobra.api.response.IndicadorAlcanceResponse;
import co.com.interkont.wscobra.api.response.MensajeResponse;
import co.com.interkont.wscobra.dto.Actividadobra;
import co.com.interkont.wscobra.dto.Alimentacion;
import co.com.interkont.wscobra.dto.Alimentacioncualificacion;
import co.com.interkont.wscobra.dto.Imagenevolucionobra;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.dto.Relacionalimentacionactividad;
import co.com.interkont.wscobra.dto.Relacionalimentacionfactoratraso;
import co.com.interkont.wscobra.dto.RelacionalimentacionfactoratrasoId;
import co.com.interkont.wscobra.dto.Relacionindicadordetalleobra;
import co.com.interkont.wscobra.dto.Semaforo;
import co.com.interkont.wscobra.api.response.PeriodoResponse;
/**
 * imports RESPONSE
 */
import co.com.interkont.wscobra.dto.VistaActividades;
import co.com.interkont.wscobra.dto.VistaIndicadoresObra;
import co.com.interkont.wscobra.dto.VistaPeriodosObra;
import co.com.interkont.wscobra.service.ActividadesService;
import co.com.interkont.wscobra.service.AlimentacionesService;
import co.com.interkont.wscobra.service.ImagenesevolucionobraService;
import co.com.interkont.wscobra.service.IndicadoresObraService;
import co.com.interkont.wscobra.service.JsfUsuariosService;
import co.com.interkont.wscobra.service.ObrasService;
import co.com.interkont.wscobra.service.PeriodosService;
import co.com.interkont.wscobra.service.RelacionesalimentacionfactoratrasoService;
import co.com.interkont.wscobra.service.RelacionesindicadordetalleobraService;
import co.com.interkont.wscobra.service.PeriodosObraService;
import co.com.interkont.wscobra.utils.Utils;



@RestController
@Api(value = "Api para consultar los datos necesarios para alimentar un proyecto",
     consumes="application/json")

public class DatosAlimentacionApi {
    
	@Autowired
	ActividadesService actividadesService;
	
	@Autowired
	PeriodosService periodosService;
	
	@Autowired
	JsfUsuariosService jsfUsuariosService;
	
	@Autowired
	ObrasService obrasService;
	
	@Autowired
	AlimentacionesService alimentacionesService;
	
	@Autowired
	ImagenesevolucionobraService imagenesevolucionobraService;

	@Autowired
	RelacionesindicadordetalleobraService relacionesindicadordetalleobraService;

	@Autowired
	PeriodosObraService periodosObraService;

	@Autowired
	RelacionesalimentacionfactoratrasoService relacionesalimentacionfactoratrasoService;
	
	@Autowired
	IndicadoresObraService indicadoresObraService;
	
	@Autowired
	Mapper mapper;
	
	
	@RequestMapping(value="/datos-alimentacion", method=RequestMethod.POST)
	@ApiOperation(value = "Datos necesarios para alimentar un proyecto.")
	public DatosAlimentacionResponse getDatosAlimentacionResponse(@RequestBody DatosAlimentacionRequest datosAlimentacionRequest){
		DatosAlimentacionResponse datosAlimentacionResponse = new DatosAlimentacionResponse();
		
		List<VistaActividades> actividades = actividadesService.findByCodigoProyecto(datosAlimentacionRequest.getCodigoProyecto());
		List<VistaPeriodosObra> periodos = periodosObraService.findByCodigoProyecto(datosAlimentacionRequest.getCodigoProyecto());
		List<VistaIndicadoresObra> indicadores = indicadoresObraService.findByCodigoProyecto(datosAlimentacionRequest.getCodigoProyecto());
		
		List<ActividadResponse> actividadesResponse = new ArrayList<ActividadResponse>();
		List<PeriodoResponse> periodosResponse = new ArrayList<PeriodoResponse>();
		List<IndicadorAlcanceResponse> indicadoresResponse = new ArrayList<IndicadorAlcanceResponse>();

		for (VistaActividades actividad : actividades) {
			ActividadResponse actividadResponse = mapper.map(actividad, ActividadResponse.class);
			actividadesResponse.add(actividadResponse);
		}
		
		for (VistaPeriodosObra periodo : periodos) {
			PeriodoResponse periodoResponse = new PeriodoResponse(periodo.getId(), periodo.getFechainicioperiodo(), periodo.getFechafinperiodo(), periodo.getPorcentajeproyectado()); 
			periodosResponse.add(periodoResponse);
		}
		
		for (VistaIndicadoresObra indicador : indicadores) {
			IndicadorAlcanceResponse indicadorResponse = new IndicadorAlcanceResponse(indicador.getId(),indicador.getDescripcionindicadoralcance(), indicador.getUnidadmedida(), indicador.getCantidadprogramada(),indicador.getCantidadejecutada(), indicador.getPorcentajeavance());
			indicadoresResponse.add(indicadorResponse);
		}
		
		datosAlimentacionResponse.setActividades(actividadesResponse);
		datosAlimentacionResponse.setPeriodos(periodosResponse);
		datosAlimentacionResponse.setIndicadoresAlcance(indicadoresResponse);
		
		return datosAlimentacionResponse;
	}
	
	@RequestMapping(value="/guardar-alimentacion", method=RequestMethod.POST)
	@ApiOperation(value = "Guardar alimentación de un proyecto.")
	public AlimentacionResponse getGuardarAlimentacion(@RequestBody AlimentacionRequest alimentacionRequest){
		System.out.println(alimentacionRequest);
		AlimentacionResponse response = new AlimentacionResponse();
		
		try {
			Obra obra = obrasService.findById(alimentacionRequest.getCodigoproyecto());
			List<Relacionindicadordetalleobra> relacionindicadordetalleobras = relacionesindicadordetalleobraService.findByObra(obra);
			List<Relacionalimentacionfactoratraso> relacionesalimentacionfactoratraso = new ArrayList<>();
			Alimentacion alimentacion = new Alimentacion();
			
			alimentacion.setAprobado(null);
			alimentacion.setDatefecha(periodosService.findById(alimentacionRequest.getPeriodoId()).getDatefecfinperiodo());
			alimentacion.setDatefechaalimen(new Date());
			alimentacion.setJsfUsuarioByIntusuAlimenta(jsfUsuariosService.findByUsuLogin(alimentacionRequest.getUsuario()));
			alimentacion.setObra(new Obra(alimentacionRequest.getCodigoproyecto()));
			alimentacion.setTextcomentario(alimentacionRequest.getDescripcion());
			//TODO Probablemente sea necesario calcular el siguiente dato para efectuar la alimentación
			alimentacion.setNumtotalproyacu(BigDecimal.ZERO);
			//TODO Implementar lógica del semáforo
			alimentacion.setSemaforo(new Semaforo(1));
			BigDecimal valorEjecutadoAlimentacion = BigDecimal.ZERO;
			for (ActividadRequest actividadRequest: alimentacionRequest.getActividades()) {
				VistaActividades vistaActividades = actividadesService.findByActividadId(actividadRequest.getActividadId());
				Relacionalimentacionactividad relacionalimentacionactividad = new Relacionalimentacionactividad();
				relacionalimentacionactividad.setAlimentacion(alimentacion);
				relacionalimentacionactividad.setFloatcantejec(actividadRequest.getCantidadEjecutada());
				relacionalimentacionactividad.setActividadobra(new Actividadobra(actividadRequest.getActividadId()));
				BigDecimal valorEjecutado = vistaActividades.getValorUnitario().multiply(BigDecimal.valueOf(actividadRequest.getCantidadEjecutada()));
				relacionalimentacionactividad.setNumvalejec(vistaActividades.getValorEjecutado().add(valorEjecutado));
				valorEjecutadoAlimentacion = valorEjecutadoAlimentacion.add(valorEjecutado);
				alimentacion.getRelacionalimentacionactividads().add(relacionalimentacionactividad);
			}
			alimentacion.setNumtotalejec(valorEjecutadoAlimentacion);
			alimentacion.setNumtotalejecacu(obra.getNumvalejecobra().add(valorEjecutadoAlimentacion));
			
			for (AspectoEvaluarRequest aspectoEvaluarRequest: alimentacionRequest.getAspectosEvaluar()) {
				Alimentacioncualificacion alimentacioncualificacion = new Alimentacioncualificacion();
				alimentacioncualificacion.setAlimentacion(alimentacion);
				alimentacioncualificacion.setIntidtipocualificacion(aspectoEvaluarRequest.getAspectoEvaluarId());
				alimentacioncualificacion.setStrdificultad(aspectoEvaluarRequest.getDificultadesAspectoEvaluar());
				alimentacioncualificacion.setStrlogro(aspectoEvaluarRequest.getLogrosAspectoEvaluar());
				alimentacion.getAlimentacioncualificacions().add(alimentacioncualificacion);
			}
			
			for (IndicadorAlcanceRequest indicadorAlcanceRequest : alimentacionRequest.getIndicadoresAlcance()) {
				for (Relacionindicadordetalleobra relacionindicadordetalleobra: relacionindicadordetalleobras) {
					
					if (relacionindicadordetalleobra.getIntid() == indicadorAlcanceRequest.getIndicadorAlcanceId()) {
						relacionindicadordetalleobra.setStrvalorejecutado(relacionindicadordetalleobra.getStrvalorejecutado().add(indicadorAlcanceRequest.getCantidadEjecucion()));
					}
				}
			}
			Imagenevolucionobra imagenevolucionobra = new Imagenevolucionobra();
			imagenevolucionobra.setDatefecha(alimentacion.getDatefecha());
			imagenevolucionobra.setDatefecharegistro(new Date());
			imagenevolucionobra.setIntidtipoimagen(6);
			imagenevolucionobra.setJsfUsuario(alimentacion.getJsfUsuarioByIntusuAlimenta());
			imagenevolucionobra.setObra(obra);
			imagenevolucionobra.setStrnombre("Foto Principal Alimentación");
			imagenevolucionobra.setStrnombrearchivo(alimentacionRequest.getFotoPrincipal().getNombre());
			imagenevolucionobra.setStrubicacion(Utils.URL_CARPETA_OBRAS_VIGENTES+"/"+obra.getIntcodigoobra()+"/"+Utils.CARPETA_IMGS_ALIMENTACION+"/"+alimentacionRequest.getFotoPrincipal().getNombre()+Utils.SEPARADOR_TIEMPO+Utils.subfijoTiempoDateFormat.format(new Date())+"."+alimentacionRequest.getFotoPrincipal().getTipo());
			
			Utils.saveFileBase64(alimentacionRequest.getFotoPrincipal().getImage(), Utils.PATH_CARPETA_PROYECTO_WEB+imagenevolucionobra.getStrubicacion());
			
			alimentacion.setImagenevolucionobra(imagenevolucionobra);
			
			if (alimentacionRequest.getImagenesComplementarias() != null) {
			for (ImagenRequest imagenRequest : alimentacionRequest.getImagenesComplementarias()) {
				Imagenevolucionobra imagenevolucionobraComplementaria = new Imagenevolucionobra();
					imagenevolucionobraComplementaria.setDatefecha(alimentacion.getDatefecha());
					imagenevolucionobraComplementaria.setDatefecharegistro(new Date());
					imagenevolucionobraComplementaria.setIntidtipoimagen(6);
					imagenevolucionobraComplementaria.setJsfUsuario(alimentacion.getJsfUsuarioByIntusuAlimenta());
					imagenevolucionobraComplementaria.setObra(obra);
					imagenevolucionobraComplementaria.setStrnombre("Foto Complementaria Alimentación");
					imagenevolucionobraComplementaria.setStrnombrearchivo(alimentacionRequest.getFotoPrincipal().getNombre());
					imagenevolucionobraComplementaria.setStrubicacion(Utils.URL_CARPETA_OBRAS_VIGENTES+"/"+obra.getIntcodigoobra()+"/"+Utils.CARPETA_IMGS_ALIMENTACION+"/"+imagenRequest.getNombre()+Utils.SEPARADOR_TIEMPO+Utils.subfijoTiempoDateFormat.format(new Date())+"."+alimentacionRequest.getFotoPrincipal().getTipo());
					Utils.saveFileBase64(imagenRequest.getImage(), Utils.PATH_CARPETA_PROYECTO_WEB+imagenevolucionobraComplementaria.getStrubicacion());
					imagenesevolucionobraService.save(imagenevolucionobraComplementaria);
				}
			}
			
			alimentacionesService.save(alimentacion);
			
			for (FactorAtrasoRequest factorAtrasoRequest : alimentacionRequest.getFactoresAtraso()) {
				Relacionalimentacionfactoratraso relacionalimentacionfactoratraso = new Relacionalimentacionfactoratraso();
				relacionalimentacionfactoratraso.setId(new RelacionalimentacionfactoratrasoId(factorAtrasoRequest.getFactorAtrasoId(),alimentacion.getIntidalimenta()));
				relacionalimentacionfactoratraso.setAlimentacion(alimentacion);
				relacionesalimentacionfactoratraso.add(relacionalimentacionfactoratraso);
			}
			relacionesalimentacionfactoratrasoService.saveAll(relacionesalimentacionfactoratraso);
			relacionesindicadordetalleobraService.saveAll(relacionindicadordetalleobras);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(1);
			List<MensajeResponse> mensajes = new ArrayList<MensajeResponse>();
			mensajes.add(new MensajeResponse("Este periodo ya fue raportado"));
			mensajes.add(new MensajeResponse("Debes justificar el factor de atraso"));
			mensajes.add(new MensajeResponse("Debes subir al menos una foto"));
			mensajes.add(new MensajeResponse(e.getMessage()));
			mensajes.add(new MensajeResponse(alimentacionRequest.toString()));
			response.setMensajes(mensajes);
			return response;
		}
		response.setStatus(0);
		List<MensajeResponse> mensajes = new ArrayList<MensajeResponse>();
		mensajes.add(new MensajeResponse("¡Felicitaciones!"));
		mensajes.add(new MensajeResponse("Tu proyecto ha sido actualizado exitosamente."));
		response.setMensajes(mensajes);
		return response;
	}
	
}
