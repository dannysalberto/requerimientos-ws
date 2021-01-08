package co.com.interkont.wscobra.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wscobra.api.request.ActividadObraRequest;
import co.com.interkont.wscobra.api.request.ActividadUpdObraRequest;
import co.com.interkont.wscobra.api.response.ActividadObraResponse;
import co.com.interkont.wscobra.auth.config.ConfiguracionConstantes;
import co.com.interkont.wscobra.config.Constantes;
import co.com.interkont.wscobra.dao.vActividadesObraRepository;
import co.com.interkont.wscobra.models.ActividadobraWS;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.models.ResponseGeneric;
import co.com.interkont.wscobra.service.ActividadObraWSService;
import co.com.interkont.wscobra.service.ObrasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar los datos de las actividades de una obra",
     consumes="application/json")
@CrossOrigin(origins="*")
public class ActividadObraApi {
	
	@Autowired
	vActividadesObraRepository service;
	
	@Autowired
	ActividadObraWSService serviceactividadWS;
	
	@Autowired
	ObrasService serviceObra;

	
	@RequestMapping(value="/actividadobra/{idObra}", method=RequestMethod.GET)
	@ApiOperation(value = "Obtener lista de actividades de obra, para una obra especifica.")
	public ResponseEntity<?> getObra(Integer id) {
		ResponseGeneric response = new ResponseGeneric(); 
		Obra obra = serviceObra.buscarPorId(id);
		if (obra==null) {
			response.setStatus(false);
			response.setMessage("Obra no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		List<ActividadObraResponse> lst = service.findByObra(obra);
		
		if (lst!=null) {
			return new ResponseEntity<List<ActividadObraResponse>>(lst, HttpStatus.OK);			
		}else {
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/actividadobra", method=RequestMethod.POST)
	@ApiOperation(value = "Guardar datos de actividadobra")
	public ResponseEntity<?> save(@RequestBody ActividadObraRequest objRequest) {

		ResponseGeneric response = new ResponseGeneric(); 
		ActividadobraWS actividad = new ActividadobraWS();
		
		actividad.setOidactiviobra(objRequest.getId());
		actividad.setStrdescactividad(objRequest.getNombre());
		actividad.setIdcategoria(objRequest.getIdcategoria());
		actividad.setStrtipounidadmed(objRequest.getUnidadMedida());
		actividad.setFloatcantplanifao(objRequest.getCantidad());
	    
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
		try {
			actividad.setFechainicio(formato.parse(objRequest.getFechainicio()));
		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			actividad.setFechafin(formato.parse(objRequest.getFechafin()));
		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Obra obra = serviceObra.buscarPorId(objRequest.getIdobra());
		actividad.setObra(obra);
		if (obra==null) {
			response.setStatus(false);
			response.setMessage("Obra no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		
		actividad.setValorunitario(objRequest.getValorunitario());
		actividad.setFloatcantidadejecutao(objRequest.getCantidadejecutada());
		actividad.setNumvalorplanifao(new BigDecimal(0));
		actividad.setValortotalactividadaiu(objRequest.getValortotal());
		actividad.setBoolaiu(false);
		actividad.setIntcedula( 0 );
		
		//this.calcularCostos(actividad, obra);
		serviceactividadWS.Guardar(actividad);

		response.setStatus(true);
		response.setMessage("Guardado con éxito");
		
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);			
	}

	@RequestMapping(value="/actividadobra", method=RequestMethod.PUT)
	@ApiOperation(value = "Actualizar datos de actividadobra")
	public ResponseEntity<?> update(@RequestBody ActividadUpdObraRequest objRequest) {

		ResponseGeneric response = new ResponseGeneric();
		if (objRequest.getId()==null) {
			response.setStatus(false);
			response.setMessage("Imposible actualizar sin id");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		System.out.println(objRequest.getId());
		ActividadobraWS actividad = new ActividadobraWS();
		actividad = serviceactividadWS.buscarPorId(objRequest.getId());
		if (actividad==null) {
			response.setStatus(false);
			response.setMessage("Actividad de obra no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		
		actividad.setOidactiviobra(objRequest.getId());

		actividad.setFloatcantplanifao(objRequest.getCantidad());
		actividad.setFechainicio(objRequest.getFechainicio());
		actividad.setFechafin(objRequest.getFechafin());
		Obra obra = serviceObra.buscarPorId(objRequest.getIdobra());
		if (obra==null) {
			response.setStatus(false);
			response.setMessage("Obra no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		
		actividad.setObra(obra);
		actividad.setValorunitario(objRequest.getValorunitario());
		actividad.setFloatcantidadejecutao(objRequest.getCantidadejecutada());
		//actividad.setNumvalorplanifao(objRequest.getValorunitario());
		//actividad.setValortotalactividadaiu(
		//			actividad.getNumvalorplanifao().multiply(
		//					new BigDecimal(actividad.getFloatcantidadejecutao())));
		actividad.setBoolaiu(false);
		actividad.setIntcedula( 0 );

		//this.calcularCostos(actividad, obra);
		serviceactividadWS.actualizar(actividad);
		
		response.setStatus(true);
		response.setMessage("Guardado con éxito");
		
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);			
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
	
	public ActividadobraWS calcularCostos(ActividadobraWS actividadobra,Obra obra) {

		double por_totalAUI = 0;
		BigDecimal totalAux = actividadobra.getValorunitario();
		BigDecimal total 	= totalAux; 

	
		por_totalAUI = 1+ (obra.getFloatporadmon() 
					+ obra.getFloatporimprevi() 
					+ obra.getFloatporutilidad()
					+ obra.getFloatporotros());
		BigDecimal totalAUI = new BigDecimal (0);
		double cantact = actividadobra.getFloatcantplanifao();			
		totalAUI = totalAUI.setScale(3, RoundingMode.HALF_EVEN);

		if (!obra.isBoolincluyeaiu()) {
			por_totalAUI = por_totalAUI  + (obra.getFloatporutilidad()*obra.getFloatporivasobreutil()/100);
			totalAUI = total.multiply(new BigDecimal(truncateDecimal(por_totalAUI,2).doubleValue()))
					.divide(new BigDecimal(100));
		
			actividadobra.setNumvalorplanifao(total.multiply(new BigDecimal(por_totalAUI)));
			
		}else {
			actividadobra.setNumvalorplanifao(totalAux);
		}
		actividadobra.setValortotalactividadaiu(actividadobra.getNumvalorplanifao().multiply(new BigDecimal(cantact)));		
		
		BigDecimal costo_directo_aiu = total.add(totalAUI);
		
		costo_directo_aiu = costo_directo_aiu.setScale(3, RoundingMode.HALF_EVEN);
		BigDecimal factorIncremento =  new BigDecimal(obra.getFloatporotros());
		BigDecimal incremento = costo_directo_aiu.multiply(factorIncremento);
		incremento = incremento.divide(new BigDecimal(100));
		incremento = incremento.setScale(3, RoundingMode.HALF_EVEN);

		actividadobra.setValortotalactividadaiu(costo_directo_aiu.add(incremento.abs()));
		
		return actividadobra;
	
	}
	
	@RequestMapping(value="/actividadobra/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Borrar una actividadobra")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
	
		ResponseGeneric response = new ResponseGeneric();
		if (id>0) {
			try {
				serviceactividadWS.eliminar(id);
				response.setStatus(true);
				response.setMessage(Constantes.ELIMINADO);
				return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
			}catch (Exception e){
				response.setStatus(false);
				response.setMessage(Constantes.NO_EXISTE);
				return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
			}
			
		}else {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
	}


}

