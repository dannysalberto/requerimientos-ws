package co.com.interkont.wsmiobra.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wsmiobra.api.request.ActividadObraRequest;
import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.models.ActividadobraWS;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.ActividadObraModificacionService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin (origins="*")
@RequestMapping("/actividadobra")
@ApiOperation(value = "Modificacion de Activdad Obra", consumes="applications/json")
public class RestActividadObraModificacion {
	
	@Autowired
	ActividadObraModificacionService serviceActividadObraMod;
 
	@RequestMapping(value="/agregaractividad", method=RequestMethod.POST)
	@ApiOperation(value = "Guardar datos de modificación actividadobra")
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
		/*
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
		*/
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);			
	}
	
	@RequestMapping(value="/eliminaractividad/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Borrar una actividadobra")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
	
		ResponseGeneric response = new ResponseGeneric();
		if (id>0) {
			try {
				serviceActividadObraMod.eliminar(id);
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