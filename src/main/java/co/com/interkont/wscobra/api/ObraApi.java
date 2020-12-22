package co.com.interkont.wscobra.api;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import co.com.interkont.wscobra.service.ActividadObraWSService;
import co.com.interkont.wscobra.service.ObrasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar los datos de la obra",
     consumes="application/json")
@CrossOrigin(origins="*")
@RequestMapping("/obra")
public class ObraApi {


	public int cantidad=0;
	public double cantPor=0; //cantidad porcentual
	public double por_totalAUI = 0;
	
	@Autowired
	ObrasService serviceObra;
	
	@Autowired
	ActividadObraWSService serviceactividadWS;
	
	@RequestMapping(value="/{idObra}", method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una obra especifica por ID.")
	public ResponseEntity<?> getObra(Integer id) {
		ResponseGeneric response = new ResponseGeneric(); 
		
		Obra obra = serviceObra.buscarPorId(id);
		
		if (obra!=null) {
			ObraResponse obranew = new ObraResponse();
			obranew.setId(obra.getIntcodigoobra());
			obranew.setFechaInicio(obra.getDatefeciniobra());
			obranew.setFechaFin(obra.getDatefecfinobra());
			obranew.setPlazoObra(obra.getIntplazoobra());
			obranew.setPeriodoMedida(obra.getIntidperiomedida());
			
			obranew.setCosto_directo(obra.getCosto_directo());
			//obranew.setCosto_directo_aiu(obra.getCosto_directo_aiu());
			//obranew.setCosto_total(obra.getCosto_total());
			
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
		obra.setActividades(serviceObra.cantidadActividades(obra.getIntcodigoobra()));
		obra.setNumvaltotobra(new BigDecimal(serviceObra.totalPrecioActividades(obra.getIntcodigoobra())));
		obra.setCosto_directo(new BigDecimal(serviceObra.totalCostoDirecto(obra.getIntcodigoobra())));
		serviceObra.actualizar(obra);
		  
		
		
		//serviceObra.actualizar(obra);
		response.setStatus(true);
		response.setMessage("Actualizado");
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		
	}
	
	public Obra calcularCostos(Obra obra) {

		
		
		/*por_totalAUI = ((obra.getFloatporadmon() 
					+ obra.getFloatporimprevi() 
					+ obra.getFloatporutilidad() + 
					((obra.getFloatporutilidad()*obra.getFloatporivasobreutil())/100)));
		*/
		
		List<ActividadobraWS> lst = serviceactividadWS.desplegarTodos(obra);
		if (obra.isBoolincluyeaiu()) {
			lst.forEach((act)->{
				act.setNumvalorplanifao(act.getValorunitario());
				act.setValortotalactividadaiu(new BigDecimal(
						act.getFloatcantplanifao()).multiply(act.getNumvalorplanifao()));
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
				act.setValortotalactividadaiu(new BigDecimal(
						act.getFloatcantplanifao()).multiply(act.getNumvalorplanifao()));
				
				//System.out.println(act.getValortotalactividadaiu());				
				//System.out.println("AIU NO INCLUIDO");
				serviceactividadWS.actualizar(act);
			});

		}
		double totalAux = serviceObra.totalPrecioActividades(obra.getIntcodigoobra());
		
		BigDecimal total = new BigDecimal(totalAux); 
		BigDecimal totalAUI =  total.multiply(new BigDecimal(truncateDecimal(por_totalAUI,2).doubleValue()));

		totalAUI = totalAUI.divide(new BigDecimal(100));
		totalAUI = totalAUI.setScale(4, RoundingMode.HALF_EVEN);

		BigDecimal costo_directo_aiu = totalAUI.add(total);
		costo_directo_aiu = costo_directo_aiu.setScale(2, RoundingMode.HALF_EVEN);

		BigDecimal factorIncremento=  new BigDecimal(obra.getFloatporotros());
		BigDecimal incremento = costo_directo_aiu.multiply(factorIncremento.divide(new BigDecimal(100)));
		incremento = incremento.setScale(2, RoundingMode.HALF_EVEN);
			
		//obra.setCosto_directo(new BigDecimal(serviceObra.totalCostoDirecto(obra.getIntcodigoobra())));
		BigDecimal costoTotal = costo_directo_aiu.add(incremento.abs());

		costoTotal = costoTotal.setScale(2, RoundingMode.HALF_EVEN); 
		obra.setNumvaltotobra(costoTotal);

		System.out.println("Completado proceso de cálculo");
		return obra;
	
	}
	
	/*public Obra calcularCostos(Obra obra) {

		double por_totalAUI = 0;
		double totalAux = serviceObra.totalPrecioActividades(obra.getIntcodigoobra());
		BigDecimal total = new BigDecimal(totalAux); 
		
		por_totalAUI = ((obra.getFloatporadmon() 
					+ obra.getFloatporimprevi() 
					+ obra.getFloatporutilidad() + 
					((obra.getFloatporutilidad()*obra.getFloatporivasobreutil())/100)));
		System.out.println(total);
		System.out.println(por_totalAUI);
		
		BigDecimal totalAUI =  total.multiply(new BigDecimal(truncateDecimal(por_totalAUI,2).doubleValue()));

		totalAUI = totalAUI.divide(new BigDecimal(100));
		totalAUI = totalAUI.setScale(4, RoundingMode.HALF_EVEN);
		System.out.println(totalAUI);

		BigDecimal costo_directo_aiu = totalAUI.add(total);
		costo_directo_aiu = costo_directo_aiu.setScale(2, RoundingMode.HALF_EVEN);

		BigDecimal factorIncremento=  new BigDecimal(obra.getFloatporotros());
		BigDecimal incremento = costo_directo_aiu.multiply(factorIncremento.divide(new BigDecimal(100)));
		incremento = incremento.setScale(2, RoundingMode.HALF_EVEN);
		
		obra.setCosto_directo(total);
		BigDecimal costoTotal = costo_directo_aiu.add(incremento.abs());

		costoTotal = costoTotal.setScale(2, RoundingMode.HALF_EVEN); 
		obra.setNumvaltotobra(costoTotal);

		obra.setNumvaltotobra(obra.getNumvaltotobra());
		System.out.println("Completado proceso de cálculo");
		return obra;
	
	}*/
	
	private static BigDecimal truncateDecimal(double x,int numberofDecimals)
	{
	       if ( x > 0) {
	           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, 
	        		   BigDecimal.ROUND_HALF_DOWN);
	       } else {
	           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	       }
	}

}
