package co.com.interkont.wscobra.api;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wscobra.models.*;

import co.com.interkont.wscobra.api.response.ObraResponse;
import co.com.interkont.wscobra.auth.config.ConfiguracionConstantes;
import co.com.interkont.wscobra.config.Constantes;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.service.ActividadObraPeriodoService;
import co.com.interkont.wscobra.service.ActividadObraWSService;
import co.com.interkont.wscobra.service.ObrasService;
import co.com.interkont.wscobra.service.PeriodoMedidaService;
import co.com.interkont.wscobra.service.PeriodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar los datos de la obra",
     consumes="application/json")
@CrossOrigin(origins="*")
@RequestMapping("/obra")
public class ObraApi {


	
	@Autowired
	ObrasService serviceObra;
	
	@Autowired
	ActividadObraWSService serviceactividadWS;
	
	@Autowired
	PeriodoService servicePeriodo; 
	
	@Autowired
	PeriodoMedidaService servicePeriodoMedida;
	
	@Autowired
	ActividadObraPeriodoService serviceActividadObraPeriodo;
	
	public int i=0;
	public boolean itero = false;
	public int cantidad=0;
	public double cantPor=0; //cantidad porcentual
	public double por_totalAUI = 0;
	public double diasRestantes = 0;
	BigDecimal montoTotalActividadRestante = new BigDecimal (0); 
	List<Periodo> lstPeriodos = new ArrayList<>();

	public static final long CONVERTIR_TIME = (long) 0.0115740740740741; // 1000/3600/24

	@RequestMapping(value="/{idObra}", method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una obra especifica por ID.")
	public ResponseEntity<?> getObra(Integer id) {
		ResponseGeneric response = new ResponseGeneric(); 
		
		Obra obra = serviceObra.buscarPorId(id);
		
		if (obra!=null) {
			ObraResponse obranew = new ObraResponse();
			obranew.setId(obra.getId());
			obranew.setFechaInicio(obra.getDatefeciniobra());
			obranew.setFechaFin(obra.getDatefecfinobra());
			obranew.setPlazoObra(obra.getIntplazoobra());
			obranew.setPeriodoMedida(obra.getIntidperiomedida());
			
			obranew.setCosto_directo(obra.getCosto_directo());
			
			obranew.setPorAdmon(obra.getFloatporadmon());
			obranew.setPorImprevi(obra.getFloatporimprevi());
			obranew.setPorIvaSobreUtil(obra.getFloatporivasobreutil());
			obranew.setPorOtros(obra.getFloatporotros());
			obranew.setPorUtilidad(obra.getFloatporutilidad());
			obranew.setValTotalObra(obra.getNumvaltotobra());
			obranew.setBoolincluyeaiu(obra.isBoolincluyeaiu());
			return new ResponseEntity<ObraResponse>(obranew, HttpStatus.OK);			
		}else {
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ResponseGeneric valiChangeDate(ObraChangeDate objeto) {		
		ResponseGeneric response = new ResponseGeneric();
		if (objeto.getId()==null || objeto.getFechaFinObra()==null|| objeto.getFechaFinObra()=="" || objeto.getFechaInicioObra()==null|| objeto.getFechaInicioObra()=="") {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return response;
		}
		return null;			
	}
	
	@PutMapping(value="/ajustarfecha")
	public ResponseEntity<?> ajustarfecha(@RequestBody(required=true) ObraChangeDate request) {
		ResponseGeneric response = this.valiChangeDate(request) ; 
		if (response!=null){
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		Obra obra = serviceObra.buscarPorId(request.getId());
		if (obra==null) {
			ResponseGeneric response1 = new ResponseGeneric(); 
			response1.setStatus(false);
			response1.setMessage(Constantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response1, HttpStatus.OK);
		}
		
		ResponseGeneric rspconfirmacion = new ResponseGeneric();
		if(obra.getDatefeciniobra()!=null &&obra.getDatefecfinobra()!=null )
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String FechainicioObra=null;
			String FechaFinalObra=null;
			FechainicioObra = format.format(obra.getDatefeciniobra());	
			FechaFinalObra = format.format(obra.getDatefecfinobra());
			Boolean ResponseFechaInicioObra=FechainicioObra.equals(request.getFechaInicioObra());		
			Boolean ResponseFechaFinObra=FechaFinalObra.equals(request.getFechaFinObra());
			cantidad = 0;
			if(ResponseFechaInicioObra==false){
				List<ActividadobraWS> lstactividad=obra.getActividadesobras();			
				lstactividad.forEach((result)->
				{
					Date fechainicio = null;
					try {
						fechainicio = format.parse(request.getFechaInicioObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					if(obra.getDatefeciniobra()!=fechainicio)
					{				
						result.setFechainicio(fechainicio);		
						cantidad++;					
					}		
					serviceactividadWS.Guardar(result);
				});		
			}
			if(ResponseFechaFinObra==false){
				List<ActividadobraWS> lstactividad=obra.getActividadesobras();
				lstactividad.forEach((result)->
				{
					Date fechafin = null;
					try {
						fechafin = format.parse(request.getFechaFinObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); 
					}			
					if(obra.getDatefecfinobra()!=fechafin)
					{
						result.setFechafin(fechafin);
						cantidad++;	
					}			
					serviceactividadWS.Guardar(result);
				});				
			}if(cantidad==0){
				rspconfirmacion.setStatus(false);
				rspconfirmacion.setMessage(Constantes.NO_EXISTE_ACTIVIDAD );	
			}else{
				rspconfirmacion.setStatus(true);
				rspconfirmacion.setMessage(Constantes.ACTIVIDAD_ACTUALIZADA+cantidad+" registros" );
			}
			return new ResponseEntity<ResponseGeneric>(rspconfirmacion, HttpStatus.OK);			
		}
		else
		{
			rspconfirmacion.setStatus(false);
			rspconfirmacion.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return new ResponseEntity<ResponseGeneric>(rspconfirmacion, HttpStatus.OK);	
		}		
					
	}
	
	public ResponseGeneric valid(ObraUpdRequest objeto) {
		
		ResponseGeneric response = new ResponseGeneric();
		if (objeto.getFechaFin()==null  
				|| objeto.getFechaInicio()==null
				|| objeto.getPlazoObra()==null) {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return response;	
			
		}
		return null;			
	}	
	
	@PutMapping
	public ResponseEntity<?> actualizarObra(@RequestBody(required=true) ObraUpdRequest request) {
		ResponseGeneric response = new ResponseGeneric(); //this.valid(request); 
		Obra obra = new Obra();
		if (request.getId()!=null) {
			obra = serviceObra.buscarPorId(request.getId());
			if (obra==null) {
				response.setStatus(false);
				response.setMessage(Constantes.NO_EXISTE);
				return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		obra.setIntplazoobra(request.getPlazoObra());
		try {
			obra.setDatefeciniobra(format.parse(request.getFechaInicio()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obra.setDatefecfinobra(format.parse(request.getFechaFin()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obra.setIntidperiomedida(request.getPeriodoMedida());
		obra.setIntplazoobra(request.getPlazoObra());
		obra.setBoolincluyeaiu(request.isIncluyeAiu());
		obra.setFloatporadmon(request.getPorAdmon());
		obra.setFloatporimprevi(request.getPorImprevi());
		obra.setFloatporutilidad(request.getPorUtilidad());
		obra.setFloatporotros(request.getPorOtros());
		obra.setFloatporivasobreutil(request.getPorIvaSobreUtil());
		
		obra = this.calcularCostos(obra);
		obra.setActividades(serviceObra.cantidadActividades(obra.getId()));
		obra.setNumvaltotobra(new BigDecimal(serviceObra.totalPrecioActividades(obra.getId())));
		obra.setCosto_directo(new BigDecimal(serviceObra.totalCostoDirecto(obra.getId())));
		serviceObra.actualizar(obra);
		
		this.planeacionPorPeriodo(obra.getId());

		response.setStatus(true);
		response.setMessage("Actualizado");
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		
	}
	
	public Obra calcularCostos(Obra obra) {

		List<ActividadobraWS> lst = serviceactividadWS.desplegarTodos(obra);
		if (obra.isBoolincluyeaiu()) {
			lst.forEach((act)->{
				act.setNumvalorplanifao(act.getValorunitario());
				act.setValortotalactividadaiu(
						act.getFloatcantplanifao().multiply(act.getNumvalorplanifao()));
				serviceactividadWS.actualizar(act);
				System.out.println("AIU INCLUIDO");
			});
		}else {
			lst.forEach((act)->{
				this.por_totalAUI = ((
						obra.getFloatporadmon() 
						+ obra.getFloatporimprevi() 
						+ obra.getFloatporutilidad() + 
						+ obra.getFloatporotros() +
						((obra.getFloatporutilidad()*obra.getFloatporivasobreutil())/100)));
				BigDecimal porAIU = truncateDecimal(por_totalAUI,2);
				System.out.println(porAIU);
				
				act.setNumvalorplanifao(act.getValorunitario().multiply(
						new BigDecimal(1).add(porAIU.divide(new BigDecimal(100)))));
				act.setNumvalorplanifao(truncateDecimal(act.getNumvalorplanifao().doubleValue(),2));
				act.setValortotalactividadaiu(
						act.getFloatcantplanifao().multiply(act.getNumvalorplanifao()));
				
				serviceactividadWS.actualizar(act);
			});

		}
		double totalAux = serviceObra.totalPrecioActividades(obra.getId());
		
		BigDecimal total = new BigDecimal(totalAux); 
		BigDecimal totalAUI =  total.multiply(new BigDecimal(truncateDecimal(por_totalAUI,2).doubleValue()));

		totalAUI = totalAUI.divide(new BigDecimal(100));
		totalAUI = totalAUI.setScale(4, RoundingMode.HALF_EVEN);

		BigDecimal costo_directo_aiu = totalAUI.add(total);
		costo_directo_aiu = costo_directo_aiu.setScale(2, RoundingMode.HALF_EVEN);

		BigDecimal factorIncremento=  new BigDecimal(obra.getFloatporotros());
		BigDecimal incremento = costo_directo_aiu.multiply(factorIncremento.divide(new BigDecimal(100)));
		incremento = incremento.setScale(2, RoundingMode.HALF_EVEN);
			
		BigDecimal costoTotal = costo_directo_aiu.add(incremento.abs());
		costoTotal = costoTotal.setScale(2, RoundingMode.HALF_EVEN); 
		obra.setNumvaltotobra(costoTotal);

		System.out.println("Completado proceso de cálculo");
		return obra;
	
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
			//BigDecimal ciclos = new BigDecimal((float) obra.getIntplazoobra() / (float) Math.max(periodoMedida.getDiasPeriodo(), 1));
			BigDecimal ciclos = DiasObra.divide(new BigDecimal(Math.max(periodoMedida.getDiasPeriodo(),1)),6,RoundingMode.HALF_EVEN) ;
			System.out.println("Generando Periodos, ciclos: "+ciclos);					

			int iter  = ciclos.intValue();
			
			int diasRestantes = 0;
			System.out.println("Generando Periodos, iteraciones: "+iter);					
			if (servicePeriodo.eliminarAll(servicePeriodo.ListarPorObra(obra.getId()))) {
		
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
					
					Periodo periodo = new Periodo();
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
					periodo.setObra(obra);
					diasRestantes = diasRestantes - (periodoMedida.getDiasPeriodo());
					/*
					if (DiasObra.doubleValue()<diasRestantes) {
						diasRestantes = diasRestantes - (DiasObra.intValue());
					}else {
						diasRestantes = diasRestantes - (periodoMedida.getDiasPeriodo());					
					} 
					DiasObra = DiasObra.subtract(new BigDecimal(periodoMedida.getDiasPeriodo()));
					*/
					System.out.println(periodo);
					periodo.GenerarId(obra, i);
					servicePeriodo.guardar(periodo);
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
					/*if (diasRestantes>0) {
						calendar.add(calendar.DAY_OF_YEAR,diasRestantes-1);				
						periodo.setFechafin(calendar.getTime());
					}else {
						periodo.setFechafin(obra.getDatefecfinobra());
					}*/
					
					periodo.setValtotplanif(new BigDecimal(0));

					periodo.setObra(obra);
					periodo.GenerarId(obra, i);
					servicePeriodo.guardar(periodo);
					
				}

			}
			
		}
	}
	
	@PutMapping("/planificacion/{idObra}")
	@ApiOperation(value = "Realizar la planificación de actividades por periodos V2 Interkont.")
	public ResponseEntity<?> planeacionPorPeriodo(@PathVariable(value="idObra") Integer idObra) {	
		Obra obra = serviceObra.buscarPorId(idObra);
		this.generarPeriodos(obra);
		serviceActividadObraPeriodo.eliminarAll(
				serviceActividadObraPeriodo.listarPorPeriodo(obra.getId()));

		List<ActividadobraWS> lstactividadObra = serviceactividadWS.desplegarTodos(obra); 
		lstactividadObra.forEach((actObra)-> {
			System.out.println(actObra);
			List<Periodo> lstPeriodos = servicePeriodo
					.ListarPorObraFecha(idObra, actObra.getFechainicio(), actObra.getFechafin());
			long DiasPeriodo = 0;
			double porcionDiasPeriodo = 0;
			
			BigDecimal acumCantidad = new BigDecimal(0);
			BigDecimal acumvalPlanif = new BigDecimal (0); 
			BigDecimal diarioActividad = new BigDecimal(0);
			double diasTotalActividad = 
					1+ ((actObra.getFechafin().getTime() - actObra.getFechainicio().getTime())
					/ 1000 / 3600 / 24);
			diarioActividad = actObra.getValortotalactividadaiu().divide(new BigDecimal(diasTotalActividad),6,RoundingMode.HALF_EVEN);
			System.out.println(lstPeriodos.size());
			for (int i = 0; i < lstPeriodos.size(); i++) {

				if (lstPeriodos.get(i).getFechainicio().after(actObra.getFechafin())) {
					break;
				}
				
				
				if (i==0) {
					//PRIMER PERIODO
					DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
							-actObra.getFechainicio().getTime()) / 1000/3600/24);
					if (DiasPeriodo > diasTotalActividad) {
						DiasPeriodo = 1 + ((actObra.getFechafin().getTime()
								-actObra.getFechainicio().getTime()) / 1000/3600/24);
					}
				}else if (i==(lstPeriodos.size()-1)){
					//ULTIMO PERIODO
					DiasPeriodo = 1 + ((actObra.getFechafin().getTime()
							-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
				}else {
					//PERIODOS INTERMEDIOS
					DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
							-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
				}
				
				porcionDiasPeriodo = this.truncateDecimal((DiasPeriodo * actObra.getFloatcantplanifao().doubleValue())/diasTotalActividad, 6).doubleValue();
				
				ActividadObraPeriodo actividadObraPeriodo = new ActividadObraPeriodo(); 
				actividadObraPeriodo.setActividadObra(actObra);
				actividadObraPeriodo.setPeriodo(lstPeriodos.get(i));
				
				if (i==(lstPeriodos.size()-1)){
					//ULTIMO PERIODO
					actividadObraPeriodo.setCantidadPlanif(actObra.getFloatcantplanifao().subtract(acumCantidad));
					actividadObraPeriodo.setValPlanif(actObra.getValortotalactividadaiu().subtract(acumvalPlanif));
				}else {
					//PERIODOS INTERMEDIOS
					System.out.println(porcionDiasPeriodo);
					actividadObraPeriodo.setCantidadPlanif(new BigDecimal(porcionDiasPeriodo));
					
					actividadObraPeriodo.setValPlanif(diarioActividad.multiply(new BigDecimal(DiasPeriodo)));
					//actividadObraPeriodo.setValPlanif(
					//		actObra.getValortotalactividadaiu().multiply(actividadObraPeriodo.getCantidadPlanif()));
					acumCantidad = acumCantidad.add(actividadObraPeriodo.getCantidadPlanif());
					acumvalPlanif = acumvalPlanif.add(actividadObraPeriodo.getValPlanif());
										
				}
				
				System.out.println(actividadObraPeriodo.getCantidadPlanif().doubleValue());
				if (actividadObraPeriodo.getCantidadPlanif().doubleValue()>0) {
					serviceActividadObraPeriodo.guardar(actividadObraPeriodo);
					
					lstPeriodos.get(i).setValtotplanif(
							lstPeriodos.get(i).getValtotplanif()
							.add(actividadObraPeriodo.getValPlanif()));
					servicePeriodo.guardar(lstPeriodos.get(i));
					
				}
		}	
		});
		/**FIN PROCESO DE PLANIFICACION DE ACTIVIDADES POR CADA PERIODO**/
		ResponseGeneric rspconfirmacion = new ResponseGeneric();
		rspconfirmacion.setStatus(true);
		rspconfirmacion.setMessage(Constantes.PLANIFICACION_COMPLETADA);
		return new ResponseEntity<ResponseGeneric>(rspconfirmacion, HttpStatus.OK);
		
	}


}
