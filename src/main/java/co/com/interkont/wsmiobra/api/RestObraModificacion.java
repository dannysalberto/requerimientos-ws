package co.com.interkont.wsmiobra.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoService;
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
	
	public double por_totalAUI = 0;	
	public int i=0;
	public boolean itero = false;

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
	ActividadObraPeriodoService serviceActividadObraPeriodo;
	
	@Autowired
	ActividadObraWSService serviceActividadObraWS;
	
	@Autowired
	PeriodoMedidaService servicePeriodoMedida;
	
	@Autowired
	PeriodoService servicePeriodo; 
	
	//@Transactional
	@PostMapping(value="/iniciarModificacion")
	@ApiOperation(value = "Inicia el proceso de copia de datos para modificación")
	public ResponseEntity<?> IniciarModificacion(@RequestBody ObraModificacionRequest request){
		
		
		Obra obra = cambiarEstadoObra(request);
		ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(request.getId()
				,Constantes.MODIFICACION_INICIADA);
		
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
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				obraModificacion.setFechaModificacion(format.parse(request.getFechaModificacion()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obraModificacion.setJustificacionModificacion(request.getJustificacionModificacion());
			
			System.out.println(obraModificacion);
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
				actividadObraModificacion.setIntcodigoobra(obra.getId());
				actividadObraModificacion.setFloatcantidadejecutao(actividadObra.getFloatcantidadejecutao());
				actividadObraModificacion.setNumvalorejecutao( new BigDecimal(0));
				
				actividadObraModificacion.setNewfechafin(actividadObra.getFechafin());
				actividadObraModificacion.setNewfechainicio(actividadObra.getFechainicio());
				actividadObraModificacion.setNewfloatcantplanifao(actividadObra.getFloatcantplanifao());
				actividadObraModificacion.setNewnumvalorplanifao(actividadObra.getNumvalorplanifao());
				actividadObraModificacion.setNewvalortotalactividadaiu(actividadObra.getValortotalactividadaiu());
				actividadObraModificacion.setNewvalorunitario(actividadObra.getValorunitario());
				
				serviceActividadObraModificacion.guardar(actividadObraModificacion);
			});
			
			Set<Periodo> listaPeriodos = obra.getPeriodos();
			listaPeriodos.forEach(periodo->{
				PeriodoModificacion periodoModificacion = new PeriodoModificacion();
				periodoModificacion.setObraModificacion(obraModificacion);
				periodoModificacion.setFechafin(periodo.getFechafin());
				periodoModificacion.setPeriodo_id(periodo.getId());
				periodoModificacion.setFechainicio(periodo.getFechainicio());
				periodoModificacion.setValtotplanif(periodo.getValtotplanif());
				periodoModificacion.setObraOrigenId(obra.getId());
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
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				obraMod.setNewfechafin(format.parse(request.getFechafin()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			obraMod.setCantidadActividades(serviceObraModificacion.cantidadActividades(obraMod.getId()));
			obraMod.setNewnumvaltotobra(new BigDecimal(serviceObraModificacion.totalPrecioActividades(obraMod.getId())));
			obraMod.setNewcosto_directo(new BigDecimal(serviceObraModificacion.totalCostoDirecto(obraMod.getId())));
			serviceObraModificacion.actualizar(obraMod);
			
			//pendiente
			this.planeacionPorPeriodo(obraMod.getId());
		
		
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
		System.out.println(obraModificacion.getRelacioncontratos());
        obraModificacion.getRelacioncontratos().removeIf(contrato->contrato
        		.getContrato().getTipoContrato()!=Constantes.CONTRATO_EJECUCION);
		return new ResponseEntity<>(obraModificacion, HttpStatus.OK);		
	}
		
	@Transactional
	@PostMapping(value="/finalizarModificacion/{idModificacion}")
	@ApiOperation(value = "Finaliza la modificación, actualizando los datos de la obra")
	public ResponseEntity<?> realizarModificacion(@PathVariable("idModificacion") Integer idModificacion){
		
		ObraModificacion obraModificacion = new ObraModificacion();
		obraModificacion = serviceObraModificacion.buscarPorId(idModificacion);
		if (obraModificacion==null) {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);	
		}
		Obra obra = serviceObra.buscarPorId(obraModificacion.getObraid());
		obra.setIntplazoobra(obraModificacion.getPlazo());
		obra.setDatefecfinobra(obraModificacion.getNewfechafin());
		obra.setIntestadoobra(Constantes.ESTADO_OBRA_EJECUCION);
		serviceObra.actualizar(obra);
		
		obraModificacion.getActividades().forEach((actividadObraModificacion)->{
			
			ActividadobraWS actividadobra = new ActividadobraWS();
			switch(actividadObraModificacion.getTipoModificacion()) {
			  case Constantes.ACTIVIDAD_ELIMINADA:
					serviceActividadObraWS.eliminar(actividadObraModificacion.getOidactiviobra());
				    break;
			  case Constantes.ACTIVIDAD_MODIFICADA:
				    actividadobra = serviceActividadObraWS.buscarPorId(actividadObraModificacion.getOidactiviobra());
				    if (actividadobra != null) {
				    	actividadobra = assignData(actividadObraModificacion);
					    serviceActividadObraWS.actualizar(actividadobra);	
				    }
				    break;
			  case Constantes.ACTIVIDAD_AGREGADA:
				  	actividadobra = assignData(actividadObraModificacion);
				  	actividadobra.setObra(obra);
				    serviceActividadObraWS.Guardar(actividadobra);
			    	break;
			  default:
					System.out.println(actividadObraModificacion);
					break;
			}
		});
		
		List<PeriodoModificacion> lstPeriodos = servicePeriodoModificacion
				.ListarPorObra(obraModificacion.getId());
		
		i = 0;
		lstPeriodos.forEach((periodo)->{
			i = i +1;
			System.out.println(periodo.getId());
			
			Periodo periodoOrigen = new  Periodo();
			periodoOrigen = servicePeriodo.buscarPorId(periodoOrigen.GenerarId(obra, i));
			int idPeriodo;
			if (periodoOrigen != null) {
				periodoOrigen.setFechainicio(periodo.getFechainicio());
				periodoOrigen.setFechafin(periodo.getFechafin());
				periodoOrigen.setValtotplanif(periodo.getValtotplanif());
				servicePeriodo.actualizar(periodoOrigen);
				idPeriodo = periodoOrigen.getId();
			}else {
				Periodo periodoNew = new  Periodo();
				periodoNew.setId(periodoNew.GenerarId(obra,i));
				periodoNew.setObra(obra);
				periodoNew.setFechainicio(periodo.getFechainicio());
				periodoNew.setFechafin(periodo.getFechafin());
				periodoNew.setValtotplanif(periodo.getValtotplanif());
				servicePeriodo.guardar(periodoNew);
				idPeriodo = periodoNew.getId();
			}
			serviceActividadObraPeriodo.eliminarAll(
					serviceActividadObraPeriodo.listarPorPeriodo(periodoOrigen.getId()));
		
			List<ActividadObraPeriodoModificacion> lstActividadPeriodo = periodo.getActividadObraPeriodoModificacion();			
		
			lstActividadPeriodo.forEach((actividadObraPeriodoModificacion)->{
				
				ActividadObraPeriodo actividadObraPeriodo = new ActividadObraPeriodo(); 
				actividadObraPeriodo.setActividadObra(serviceActividadObraWS
						.buscarPorId((int) actividadObraPeriodoModificacion.getOidactiviobra()));

				actividadObraPeriodo.setPeriodo(servicePeriodo.buscarPorId(idPeriodo));
				actividadObraPeriodo.setCantidadPlanif(actividadObraPeriodoModificacion.getFloatcantplanif());
				actividadObraPeriodo.setValPlanif(actividadObraPeriodoModificacion.getNumvalplanif());
				serviceActividadObraPeriodo.guardar(actividadObraPeriodo);	

			});
			
		});
		
		//falta periodos y relacionactividadobraperiodo
		ResponseGeneric response = new ResponseGeneric();
		response.setStatus(true);
		response.setMessage(Constantes.MODIFICACION_DE_OBRA_FINALIZADA);
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);		
	}
	
	@DeleteMapping(value="/cancelarmodificacion/{idModificacion}")
	@ApiOperation(value="Borra los datos de una modificación")
	public ResponseEntity<ResponseGeneric> cancelarModificacion(@PathVariable("idModificacion") Integer idModificacion){
		
		try {
			serviceObraModificacion.Eliminar(idModificacion);			
		}catch (Exception e) {
			ResponseGeneric response = new ResponseGeneric();
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
		}
		
		ResponseGeneric response = new ResponseGeneric();
		response.setStatus(false);
		response.setMessage(Constantes.MODIFICACION_DE_OBRA_CANCELADA);
		return new ResponseEntity<> (response, HttpStatus.ACCEPTED);
		
	}


	private ActividadobraWS assignData(ActividadObraModificacion actividadObraModificacion) {
			
			ActividadobraWS actividadobra = new ActividadobraWS();
			actividadobra.setOidactiviobra(actividadObraModificacion.getOidactiviobra());
			actividadobra.setStrdescactividad(actividadObraModificacion.getStrdescactividad());
			actividadobra.setIdcategoria(actividadObraModificacion.getIdcategoria());
			actividadobra.setStrtipounidadmed(actividadObraModificacion.getStrtipounidadmed());
			//actividadobra.setFloatcantidadejecutao(actividadObraModificacion.getn);
			actividadobra.setFloatcantplanifao(actividadObraModificacion.getNewfloatcantplanifao());
			actividadobra.setFechafin(actividadObraModificacion.getNewfechafin());
			actividadobra.setFechainicio(actividadObraModificacion.getNewfechainicio());
			actividadobra.setValorunitario(actividadObraModificacion.getNewvalortotalactividadaiu());
			actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
			actividadobra.setBoolaiu(actividadObraModificacion.isBoolaiu());
			actividadobra.setValortotalactividadaiu(actividadObraModificacion.getNewvalortotalactividadaiu());
			actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
			return actividadobra;
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
		obra.setIntestadoobra(Constantes.ESTADO_OBRA_MODIFICACION);
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
	
	public boolean generarPeriodos(ObraModificacion obraMod, Obra obra) {
		System.out.println("Generando Periodos");					
		System.out.println(obra);
		
		if (obra.getIntidperiomedida()>0) {
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());
			BigDecimal DiasObra = new BigDecimal(1 + ((obraMod.getNewfechafin().getTime()
					-obra.getDatefeciniobra().getTime()) / 1000/3600/24));
			System.out.println("Dias obra: "+DiasObra.doubleValue());

			BigDecimal ciclos = DiasObra.divide(new BigDecimal(Math.max(periodoMedida.getDiasPeriodo(),1)),6,RoundingMode.HALF_EVEN) ;
			System.out.println("Generando Periodos, ciclos: "+ciclos);					

			int iter  = ciclos.intValue();
			
			int diasRestantes = 0;
			System.out.println("Generando Periodos, iteraciones: "+iter);		
			//eliminar todos menos los que tengan alimentación
			List<PeriodoModificacion> lstPeriodoModDelete = servicePeriodoModificacion.ListarPorObra(obraMod.getId());
			lstPeriodoModDelete.forEach(periodoDele->{
				serviceActividadObraPeriodoModificacion.eliminarPorIdPeriodo(periodoDele.getId());
			});
			
			if (servicePeriodoModificacion.eliminarAll(lstPeriodoModDelete )) {
		
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
					//ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA);
					periodo.setObraModificacion(obraMod);
					diasRestantes = diasRestantes - (periodoMedida.getDiasPeriodo());
					periodo.GenerarId(obra, i);
					System.out.println(periodo);
					periodo.setObraOrigenId(obra.getId());
					//periodo.setPeriodo_id(periodo_id);

					servicePeriodoModificacion.guardar(periodo);
					itero = true;
				}
				
				if (itero == false) {
					System.out.println("Sin interaciones por periodo");	
					PeriodoModificacion periodo = new PeriodoModificacion();
					periodo.setFechainicio(obra.getDatefeciniobra());
					periodo.setFechafin(obraMod.getNewfechafin());
					periodo.setValtotplanif(new BigDecimal(0));
					//ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA);
					periodo.setObraModificacion(obraMod);
					periodo.setObraOrigenId(obra.getId());

					periodo.GenerarId(obra, 1);
					servicePeriodoModificacion.guardar(periodo);
				}else if (diasRestantes>0){
					System.out.println("Con interaciones por periodo");
					PeriodoModificacion periodo = new PeriodoModificacion();
					calendar.add(calendar.DAY_OF_YEAR,1);
					periodo.setFechainicio(calendar.getTime());
					periodo.setFechafin(obraMod.getNewfechafin());
					periodo.setValtotplanif(new BigDecimal(0));

					//ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA);
					periodo.setObraModificacion(obraMod);
					periodo.setObraOrigenId(obra.getId());
					periodo.GenerarId(obra, i);
					servicePeriodoModificacion.guardar(periodo);
				}

			}
			
		}
		return true;
	}
	
	//@PutMapping("/planificacion/{idObra}")
	//@ApiOperation(value = "Realizar la planificación de actividades por periodos V2 Interkont.")
	public ResponseEntity<?> planeacionPorPeriodo(@PathVariable(value="idObra") Integer idObra) {	
		ObraModificacion obra = serviceObraModificacion.buscarPorId(idObra);
		Obra obraOrigen = serviceObra.buscarPorId(obra.getObraid());
		
		/*serviceActividadObraPeriodoModificacion.eliminarAll(
	 	serviceActividadObraPeriodoModificacion.listarPorModificacion(obra.getId()));
		*/
		if (this.generarPeriodos(obra, obraOrigen)){
			List<ActividadObraModificacion> lstactividadObra = serviceActividadObraModificacion.desplegarTodos(obra); 
			lstactividadObra.forEach((actObra)-> {
				System.out.println(actObra);
				List<PeriodoModificacion> lstPeriodos = servicePeriodoModificacion
						.ListarPorObraFecha(obra.getId(), actObra.getNewfechainicio(), actObra.getNewfechafin());
				
				long DiasPeriodo = 0;
				double porcionDiasPeriodo = 0;
				
				BigDecimal acumCantidad = new BigDecimal(0);
				BigDecimal acumvalPlanif = new BigDecimal (0); 
				BigDecimal diarioActividad = new BigDecimal(0);
				double diasTotalActividad = 
						1+ ((actObra.getNewfechafin().getTime() - actObra.getNewfechainicio().getTime())
						/ 1000 / 3600 / 24);
				diarioActividad = actObra.getValortotalactividadaiu().divide(new BigDecimal(diasTotalActividad),6,RoundingMode.HALF_EVEN);
				//System.out.println(lstPeriodos.size());
				for (int i = 0; i < lstPeriodos.size(); i++) {

					if (lstPeriodos.get(i).getFechainicio().after(actObra.getFechafin())) {
						break;
					}
					
					
					if (i==0) {
						//PRIMER PERIODO
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
								-actObra.getNewfechainicio().getTime()) / 1000/3600/24);
						if (DiasPeriodo > diasTotalActividad) {
							DiasPeriodo = 1 + ((actObra.getNewfechafin().getTime()
									-actObra.getNewfechainicio().getTime()) / 1000/3600/24);
						}
					}else if (i==(lstPeriodos.size()-1)){
						//ULTIMO PERIODO
						DiasPeriodo = 1 + ((actObra.getNewfechafin().getTime()
								-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
					}else {
						//PERIODOS INTERMEDIOS
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
								-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
					}
					
					porcionDiasPeriodo = this.truncateDecimal((DiasPeriodo * actObra.getNewfloatcantplanifao().doubleValue())/diasTotalActividad, 6).doubleValue();
					
					ActividadObraPeriodoModificacion actividadObraPeriodoMod = new ActividadObraPeriodoModificacion(); 
					actividadObraPeriodoMod.setOidactiviobra(actObra.getId());
					actividadObraPeriodoMod.setPeriodoModificacion(lstPeriodos.get(i));
					
					if (i==(lstPeriodos.size()-1)){
						//ULTIMO PERIODO
						actividadObraPeriodoMod.setFloatcantplanif(actObra.getNewfloatcantplanifao().subtract(acumCantidad));
						actividadObraPeriodoMod.setNumvalplanif(actObra.getNewvalortotalactividadaiu().subtract(acumvalPlanif));
					}else {
						//PERIODOS INTERMEDIOS
						System.out.println(porcionDiasPeriodo);
						actividadObraPeriodoMod.setFloatcantplanif(new BigDecimal(porcionDiasPeriodo));
						
						actividadObraPeriodoMod.setNumvalplanif(diarioActividad.multiply(new BigDecimal(DiasPeriodo)));
						acumCantidad = acumCantidad.add(actividadObraPeriodoMod.getFloatcantplanif());
						acumvalPlanif = acumvalPlanif.add(actividadObraPeriodoMod.getNumvalplanif());				
					}

					actividadObraPeriodoMod.setIntidperiodo(lstPeriodos.get(i).getPeriodo_id());
					if (actividadObraPeriodoMod.getFloatcantplanif().doubleValue()>0) {
						serviceActividadObraPeriodoModificacion.guardar(actividadObraPeriodoMod);
						
						lstPeriodos.get(i).setValtotplanif(
								lstPeriodos.get(i).getValtotplanif()
								.add(actividadObraPeriodoMod.getNumvalplanif()));
						servicePeriodoModificacion.guardar(lstPeriodos.get(i));
						
					}
			}	
			});
		}

		
		
		
		/**FIN PROCESO DE PLANIFICACION DE ACTIVIDADES POR CADA PERIODO**/
		ResponseGeneric rspconfirmacion = new ResponseGeneric();
		rspconfirmacion.setStatus(true);
		rspconfirmacion.setMessage(Constantes.PLANIFICACION_COMPLETADA);
		return new ResponseEntity<ResponseGeneric>(rspconfirmacion, HttpStatus.OK);
		
	}

	

}

