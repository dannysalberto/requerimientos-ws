package co.com.interkont.wsmiobra.api;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.interkont.wsmiobra.api.request.ObraModificacionRequest;
import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodoModificacion;
import co.com.interkont.wsmiobra.models.ActividadobraWS;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.models.PeriodoModificacion;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.ActividadObraModificacionService;
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoModificacionService;
import co.com.interkont.wsmiobra.service.ObraModificacionService;
import co.com.interkont.wsmiobra.service.ObrasService;
import co.com.interkont.wsmiobra.service.PeriodoModificacionService;
import io.swagger.annotations.Api;


@RestController
@CrossOrigin(origins="*")
@Api(value = "xxxx",
consumes="application/json")
@RequestMapping("/obra")
public class RestObraModificacion {
	
	protected static final int ESTADO_OBRA_MODIFICACION = 3;
	
	
	
	
	public static int getEstadoObraModificacion() {
		return ESTADO_OBRA_MODIFICACION;
	}

	@Autowired
	ObraModificacionService serviceObraModificacion;
	
	@Autowired
	ObrasService serviceObra;
	
	@Autowired
	ActividadObraModificacionService serviceActividadObraModificacion;
	
	@Autowired
	PeriodoModificacionService servicePeriodoModificacion;
	
	@Autowired
	ActividadObraPeriodoModificacionService serviceActividadObraPeriodoModificacion;
	
	
	
	@Transactional
	@PostMapping(value="/iniciarModificacion")
	public ResponseEntity<?> IniciarModificacion(@RequestBody ObraModificacionRequest request){
		
		
		Obra obra = cambiarEstadoObra(request);
		ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(
				request.getId(), 
				Constantes.MODIFICACION_INICIADA);
		
		if (obraMod!=null) {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(true);
			response.setMessage(Constantes.MODIFICACION_INICIADA_OK);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND); 
		}else {
			ObraModificacion obraModificacion = new ObraModificacion(); 
			obraModificacion.setObraid(request.getId());
			obraModificacion.setFechafin(obra.getDatefecfinobra());
			obraModificacion.setFechainicio(obra.getDatefeciniobra());
			obraModificacion.setPlazo(obra.getIntplazoobra());
			obraModificacion.setPeriodomedida(obra.getIntidperiomedida());
			obraModificacion.setFechaModificacion(request.getFechaModificacion());
			obraModificacion.setJustificacionModificacion(request.getJustificacionModificacion());
			
			serviceObraModificacion.guardar(obraModificacion);
			List<ActividadobraWS> listaActividades = obra.getActividadesobras();
			listaActividades.forEach(actividadObra->{

				ActividadObraModificacion actividadObraModificacion = new ActividadObraModificacion();
				actividadObraModificacion.setObraModificacion(obraModificacion);
				actividadObraModificacion.setOidactiviobra(actividadObra.getOidactiviobra());
				actividadObraModificacion.setIdcategoria(actividadObra.getIdcategoria());
				actividadObraModificacion.setStrdescactividad(actividadObra.getStrdescactividad());
				actividadObraModificacion.setStrtipounidadmed(actividadObra.getStrtipounidadmed());
				actividadObraModificacion.setValorunitario(actividadObra.getValorunitario());
				actividadObraModificacion.setNumvalorplanifao(actividadObra.getNumvalorplanifao());
				actividadObraModificacion.setFloatcantplanifao(actividadObra.getFloatcantplanifao());
				actividadObraModificacion.setValortotalactividadaiu(actividadObra.getValortotalactividadaiu());
				actividadObraModificacion.setFechainicio(actividadObra.getFechainicio());
				actividadObraModificacion.setFechafin(actividadObra.getFechafin());
				actividadObraModificacion.setIntcodigoobra(actividadObra.getObra().getId());
				actividadObraModificacion.setFloatcantidadejecutao(actividadObra.getFloatcantidadejecutao());
				actividadObraModificacion.setNumvalorejecutao(actividadObra.getNumvalorplanifao());
				serviceActividadObraModificacion.guardar(actividadObraModificacion);
			});
			
			Set<Periodo> listaPeriodos = obra.getPeriodos();
			listaPeriodos.forEach(periodo->{
				PeriodoModificacion periodoModificacion = new PeriodoModificacion();
				periodoModificacion.setObra(obra);
				periodoModificacion.setObraModificacion(obraModificacion);
				periodoModificacion.setFechafin(periodo.getFechafin());
				periodoModificacion.setFechainicio(periodo.getFechainicio());
				periodoModificacion.setIntidperiodo(periodo.getId());
				periodoModificacion.setValtotplanif(periodo.getValtotplanif());
				
				servicePeriodoModificacion.guardar(periodoModificacion);
				
				
				List<ActividadObraPeriodo> listaActividadPeriodos = periodo.getActividadObra();
				listaActividadPeriodos.forEach(actividadObraPeriodo->{
					ActividadObraPeriodoModificacion actividadObraPeriodoModificacion = new ActividadObraPeriodoModificacion();
					actividadObraPeriodoModificacion.setActividadObraPeriodo_id(actividadObraPeriodo.getId());
					actividadObraPeriodoModificacion.setOidactiviobra(actividadObraPeriodo.getActividadObra().getOidactiviobra());
					actividadObraPeriodoModificacion.setFloatcantplanif(actividadObraPeriodo.getCantidadPlanif());
					actividadObraPeriodoModificacion.setIntidperiodo(actividadObraPeriodo.getPeriodo().getId());
					actividadObraPeriodoModificacion.setNumvalplanif(actividadObraPeriodo.getValPlanif());
					actividadObraPeriodoModificacion.setPeriodoModificacion(periodoModificacion);
					
					serviceActividadObraPeriodoModificacion.guardar(actividadObraPeriodoModificacion);
				});
			});
			
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(true);
			response.setMessage(Constantes.MODIFICACION_INICIADA_OK);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);		
		}
		
	}
	
	
	private Obra cambiarEstadoObra(ObraModificacionRequest request) {
		
	    Logger logger = LoggerFactory.getLogger(RestObraModificacion.class);
	    Obra obra = null;
		try {
			obra = serviceObra.buscarPorId(request.getId());
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		obra.setIntestadoobra(getEstadoObraModificacion());
		serviceObra.actualizar(obra);
		return obra;
	}
	
	@GetMapping(value="/obtenerObraModificacion/{idobra}")
	public ResponseEntity<?> obtenerObraModificacion(@PathVariable("idobra") Integer idobra){
		
	       
	      
		ObraModificacion obraModificacion = new ObraModificacion();
		obraModificacion = serviceObraModificacion
				.buscarPorIdEstado(idobra,Constantes.MODIFICACION_INICIADA);
		if (obraModificacion==null) {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);	
		}
		
        obraModificacion.getRelacioncontratos().removeIf(contrato->contrato
        		.getContrato().getTipoContrato()!=Constantes.CONTRATO_EJECUCION);
		return new ResponseEntity<>(obraModificacion, HttpStatus.OK);		
	}

}

