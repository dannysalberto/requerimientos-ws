package co.com.interkont.wscobra.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import co.com.interkont.wscobra.api.request.ActividadObraRequest;
import co.com.interkont.wscobra.api.response.ActividadObraResponse;
import co.com.interkont.wscobra.auth.config.ConfiguracionConstantes;
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
		actividad.setFechainicio(objRequest.getFechainicio());
		actividad.setFechafin(objRequest.getFechafin());
		Obra obra = serviceObra.buscarPorId(objRequest.getIdobra());
		actividad.setObra(obra);
		if (obra==null) {
			response.setStatus(false);
			response.setMessage("Obra no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		
		actividad.setValorunitario(objRequest.getValorunitario());
		actividad.setFloatcantidadejecutao(objRequest.getCantidadejecutada());
		actividad.setNumvalorplanifao(objRequest.getValortotal());
		actividad.setBoolaiu(false);
		actividad.setIntcedula( 0 );
		
		serviceactividadWS.Guardar(actividad);

		response.setStatus(true);
		response.setMessage("Guardado con éxito");
		
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);			
	}

	@RequestMapping(value="/actividadobra", method=RequestMethod.PUT)
	@ApiOperation(value = "Actualizar datos de actividadobra")
	public ResponseEntity<?> update(@RequestBody ActividadObraRequest objRequest) {

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
		actividad.setStrdescactividad(objRequest.getNombre());
		actividad.setIdcategoria(objRequest.getIdcategoria());
		actividad.setStrtipounidadmed(objRequest.getUnidadMedida());
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
		actividad.setNumvalorplanifao(objRequest.getValortotal());
		actividad.setBoolaiu(false);
		actividad.setIntcedula( 0 );
		
		serviceactividadWS.actualizar(actividad);
		
		response.setStatus(true);
		response.setMessage("Guardado con éxito");
		
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);			
	}

}
