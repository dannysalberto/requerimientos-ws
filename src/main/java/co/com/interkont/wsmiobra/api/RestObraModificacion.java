package co.com.interkont.wsmiobra.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.interkont.wsmiobra.api.request.ObraModificacionRequest;
import co.com.interkont.wsmiobra.api.request.ObraUpdateRequest;
import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodoModificacion;
import co.com.interkont.wsmiobra.models.ActividadobraWS;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.models.PeriodoMedida;
import co.com.interkont.wsmiobra.models.PeriodoModificacion;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.ActividadObraModificacionService;
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoModificacionService;
import co.com.interkont.wsmiobra.service.ActividadObraWSService;
import co.com.interkont.wsmiobra.service.ObraModificacionService;
import co.com.interkont.wsmiobra.service.ObrasService;
import co.com.interkont.wsmiobra.service.PeriodoMedidaService;
import co.com.interkont.wsmiobra.service.PeriodoModificacionService;
import co.com.interkont.wsmiobra.service.PeriodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins="*")
@Api(value = "xxxx",
consumes="application/json")
@RequestMapping("/obra")
public class RestObraModificacion {
	
	protected static final int ESTADO_OBRA_MODIFICACION = 3;
	public double por_totalAUI = 0;	
	public int i=0;
	public boolean itero = false;
	
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
	
	@Autowired
	ActividadObraWSService serviceActividadObraWS;
	
	@Autowired
	PeriodoMedidaService servicePeriodoMedida;
	
	@Autowired
	PeriodoService servicePeriodo; 
	
	@Transactional
	@PostMapping(value="/iniciarModificacion")
	@ApiOperation(value = "Inicia el proceso de copia de datos para modificación")
	public ResponseEntity<?> IniciarModificacion(@RequestBody ObraModificacionRequest request){
		
		
		Obra obra = cambiarEstadoObra(request);
		ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(
				request.getId(), 
				Constantes.MODIFICACION_INICIADA);
		
		if (obraMod!=null) {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(true);
			response.setMessage(Constantes.MODIFICACION_EXISTENTE_OK);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.ACCEPTED); 
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
				periodoModificacion.setObraModificacion(obraModificacion);
				periodoModificacion.setFechafin(periodo.getFechafin());
				periodoModificacion.setFechainicio(periodo.getFechainicio());
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
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);		
		}
		
	}
	
	@Transactional
	@PutMapping(value="/actualizarObraModificacion")
	@ApiOperation(value = "Actualiza los datos de la obra")
	public ResponseEntity<?> actualizarObraModificacion(@RequestBody ObraUpdateRequest request){
		
		ObraModificacion obraMod = serviceObraModificacion.buscarPorId(request.getId());
		if (obraMod!=null) {
			obraMod.setNewfechafin(request.getFechafin());
			obraMod.setNewplazo(request.getPlazo());	
			return new ResponseEntity<ObraModificacion>(serviceObraModificacion.actualizar(obraMod), HttpStatus.OK);			
		}else {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.BAD_REQUEST);		
		}
		
	}
	
	@Transactional
	@PutMapping(value="/reajustarCalculos/{idObra}")
	@ApiOperation(value = "Realiza el proceso de cálculos basado en las modificaciones")
	public ResponseEntity<?> reajustarCalculosModificacion(@PathVariable("idObra") Integer idObra){
			
			ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(
					idObra,Constantes.MODIFICACION_INICIADA);
			this.calcularCostos(obraMod);

			//pendiente
			//this.planeacionPorPeriodo(obra.getId());
		
		
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(true);
			response.setMessage(Constantes.CALCULO_REALIZADO);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);		
	}
		
	
	@GetMapping(value="/obtenerObraModificacion/{idobra}")
	@ApiOperation(value = "Obtiene los datos de modificación de una obra por su id")
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
	
	@GetMapping(value="/realizarModificacion/{idmodificacion}")
	@ApiOperation(value = "Realiza las modificaciones en la persistencia original")
	public ResponseEntity<?> realizarModificacion(@PathVariable("idmodificacion") Integer idmodificacion){
		
		ObraModificacion obraModificacion = new ObraModificacion();
		obraModificacion = serviceObraModificacion.buscarPorId(idmodificacion);
		if (obraModificacion==null) {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);	
		}
		Obra obra = serviceObra.buscarPorId(obraModificacion.getObraid());
		obraModificacion.getActividades().forEach((actividadObraModificacion)->{
			
			ActividadobraWS actividadobra = new ActividadobraWS();
			switch(actividadObraModificacion.getTipoModificacion()) {
			  case Constantes.ACTIVIDAD_ELIMINADA:
					serviceActividadObraWS.eliminar(actividadObraModificacion.getOidactiviobra());
				    break;
			  case Constantes.ACTIVIDAD_MODIFICADA:
				    actividadobra = serviceActividadObraWS.buscarPorId(actividadObraModificacion.getOidactiviobra());
				    if (actividadobra != null) {
				    	assignData(obra, actividadObraModificacion, actividadobra);
					    serviceActividadObraWS.actualizar(actividadobra);	
				    }
				    break;
			  case Constantes.ACTIVIDAD_AGREGADA:
				  	assignData(obra, actividadObraModificacion, actividadobra);
				    serviceActividadObraWS.Guardar(actividadobra);
			    	break;
			  default:
					System.out.println(actividadObraModificacion);
					break;
			}
		});
		
        
		return new ResponseEntity<>(obraModificacion, HttpStatus.OK);		
	}


	private void assignData(Obra obra, ActividadObraModificacion actividadObraModificacion,
			ActividadobraWS actividadobra) {
		
			actividadobra.setOidactiviobra(actividadObraModificacion.getOidactiviobra());
			actividadobra.setStrdescactividad(actividadObraModificacion.getStrdescactividad());
			actividadobra.setIdcategoria(actividadObraModificacion.getIdcategoria());
			actividadobra.setStrtipounidadmed(actividadObraModificacion.getStrtipounidadmed());
			actividadobra.setFloatcantidadejecutao(actividadObraModificacion.getFloatcantidadejecutao());
			actividadobra.setFloatcantplanifao(actividadObraModificacion.getFloatcantplanifao());
			actividadobra.setFechafin(actividadObraModificacion.getFechafin());
			actividadobra.setFechainicio(actividadObraModificacion.getFechainicio());
			actividadobra.setObra(obra);
			actividadobra.setValorunitario(actividadObraModificacion.getValortotalactividadaiu());
			actividadobra.setNumvalorplanifao(actividadObraModificacion.getNumvalorplanifao());
			actividadobra.setBoolaiu(actividadObraModificacion.isBoolaiu());
			actividadobra.setValortotalactividadaiu(actividadObraModificacion.getValortotalactividadaiu());
			actividadobra.setNumvalorplanifao(actividadObraModificacion.getNumvalorplanifao());
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
	
	public void calcularCostos(ObraModificacion obra) {

		Obra obraOrigen = serviceObra.buscarPorId(obra.getObraid());
		List<ActividadObraModificacion> lst = serviceActividadObraModificacion.desplegarTodos(obra);
		if (obraOrigen.isBoolincluyeaiu()) {
			lst.forEach((act)->{
				System.out.println("incluye aiu");
				act.setNewnumvalorplanifao(act.getValorunitario());
				act.setNewvalortotalactividadaiu(
						act.getNewfloatcantplanifao().multiply(act.getNewnumvalorplanifao()));
				serviceActividadObraModificacion.actualizar(act);
			});
		}else {
			lst.forEach((act)->{
				System.out.println("no incluye aiu");
				this.por_totalAUI = ((
						obraOrigen.getFloatporadmon() 
						+ obraOrigen.getFloatporimprevi() 
						+ obraOrigen.getFloatporutilidad() + 
						+ obraOrigen.getFloatporotros() +
						((obraOrigen.getFloatporutilidad()*obraOrigen.getFloatporivasobreutil())/100)));
				BigDecimal porAIU = truncateDecimal(por_totalAUI,2);
				
				act.setNewnumvalorplanifao(act.getNewvalorunitario().multiply(
						new BigDecimal(1).add(porAIU.divide(new BigDecimal(100)))));
				act.setNewnumvalorplanifao(truncateDecimal(act.getNewnumvalorplanifao().doubleValue(),2));
				act.setNewvalortotalactividadaiu(
						act.getNewfloatcantplanifao().multiply(act.getNewnumvalorplanifao()));
				
				serviceActividadObraModificacion.actualizar(act);
			});

		}
			
	}

	private static BigDecimal truncateDecimal(double x,int numberofDecimals)
	{
	       if ( x > 0) {
	           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, 
	        		   BigDecimal.ROUND_HALF_DOWN);
	       } else {
	           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	       }
	}
	
	public void generarPeriodos(Obra obra) {
		System.out.println("Generando Periodos");					

		if (obra.getIntidperiomedida()>0) {
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());
			BigDecimal DiasObra = new BigDecimal(1 + ((obra.getDatefecfinobra().getTime()
					-obra.getDatefeciniobra().getTime()) / 1000/3600/24));
			System.out.println("Dias obra: "+DiasObra.doubleValue());

			BigDecimal ciclos = DiasObra.divide(new BigDecimal(Math.max(periodoMedida.getDiasPeriodo(),1)),6,RoundingMode.HALF_EVEN) ;
			System.out.println("Generando Periodos, ciclos: "+ciclos);					

			int iter  = ciclos.intValue();
			
			int diasRestantes = 0;
			System.out.println("Generando Periodos, iteraciones: "+iter);					
			if (servicePeriodoModificacion.eliminarAll(servicePeriodoModificacion.ListarPorObra(obra.getId()))) {
		
				Date fechaInicio = obra.getDatefeciniobra();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(fechaInicio);
				BigDecimal montoRestante = obra.getNumvaltotobra();
				montoRestante.setScale(3, RoundingMode.HALF_EVEN);
				if (iter>0) {
					diasRestantes = DiasObra.intValue();				
				}
				itero=false;
				for (i=1;i<=iter;i++) {
					
					PeriodoModificacion periodo = new PeriodoModificacion();
					if (i==1) {
						periodo.setFechainicio(calendar.getTime());
						System.out.println("Caso 1");					
					}else {
						System.out.println("Caso 2");
						calendar.add(calendar.DAY_OF_YEAR,1);
						
						periodo.setFechainicio(calendar.getTime());
					}
					calendar.add(calendar.DAY_OF_YEAR,periodoMedida.getDiasPeriodo()-1);
					periodo.setFechafin(calendar.getTime());
					periodo.setValtotplanif(new BigDecimal(0));
					ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA);
					periodo.setObraModificacion(obraMod);
					diasRestantes = diasRestantes - (periodoMedida.getDiasPeriodo());

					periodo.GenerarId(obra, i);
					System.out.println(periodo);
					servicePeriodoModificacion.guardar(periodo);
					itero = true;
				}
				
				if (itero == false) {
					System.out.println("Sin interaciones por periodo");	
					Periodo periodo = new Periodo();
					periodo.setFechainicio(obra.getDatefeciniobra());
					periodo.setFechafin(obra.getDatefecfinobra());
					periodo.setValtotplanif(new BigDecimal(0));
					periodo.setObra(obra);
					periodo.GenerarId(obra, 1);
					servicePeriodo.guardar(periodo);
				}else if (diasRestantes>0){
					System.out.println("Con interaciones por periodo");
					Periodo periodo = new Periodo();
					calendar.add(calendar.DAY_OF_YEAR,1);
					periodo.setFechainicio(calendar.getTime());
					periodo.setFechafin(obra.getDatefecfinobra());
					periodo.setValtotplanif(new BigDecimal(0));

					periodo.setObra(obra);
					periodo.GenerarId(obra, i);
					servicePeriodo.guardar(periodo);
				}

			}
			
		}
	}
	

}
