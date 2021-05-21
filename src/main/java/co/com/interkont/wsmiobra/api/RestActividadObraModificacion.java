package co.com.interkont.wsmiobra.api;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wsmiobra.api.request.ActividadObraRequest;
import co.com.interkont.wsmiobra.api.request.ActividadUpdObraRequest;
import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.ActividadObraModificacionService;
import co.com.interkont.wsmiobra.service.CategoriaService;
import co.com.interkont.wsmiobra.service.ObraModificacionService;
import co.com.interkont.wsmiobra.service.ObrasService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin (origins="*")
@RequestMapping("/modificacion")
@ApiOperation(value = "Modificacion de Activdad Obra", consumes="applications/json")
public class RestActividadObraModificacion {
	
	@Autowired
	ActividadObraModificacionService serviceActividadObraMod;
	
	@Autowired
	ObrasService serviceObra;
	
	@Autowired
	ObraModificacionService serviceObraModificacion;
	
	@Autowired
	CategoriaService serviceCategoria;
 
	@RequestMapping(value="/agregaractividad", method=RequestMethod.POST)
	@ApiOperation(value = "Guardar datos de modificación actividadobra")
	public ResponseEntity<?> save(@RequestBody ActividadObraRequest objRequest) {

		//validar si al agregar la actividad se supera el valor total disponible de la obra
		ResponseGeneric response = new ResponseGeneric(); 
		ActividadObraModificacion actividad = new ActividadObraModificacion();
		
		actividad.setOidactiviobra(0);
		actividad.setStrdescactividad(objRequest.getNombre());
		actividad.setCategoria(serviceCategoria.buscarPorId(objRequest.getIdcategoria()));
		actividad.setStrtipounidadmed(objRequest.getUnidadMedida());
	    
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
		try {
			actividad.setFechainicio(formato.parse(objRequest.getFechainicio()));
			actividad.setNewfechainicio(formato.parse(objRequest.getFechainicio()));

		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			actividad.setFechafin(formato.parse(objRequest.getFechafin()));
			actividad.setNewfechafin(formato.parse(objRequest.getFechafin()));

		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObraModificacion obraModificacion = serviceObraModificacion.buscarPorIdEstado(objRequest.getIdobra(),
				Constantes.MODIFICACION_INICIADA);
		actividad.setObraModificacion(obraModificacion);
		actividad.setIntcodigoobra(objRequest.getIdobra());
		if (obraModificacion==null) {
			response.setStatus(false);
			response.setMessage("Obra en modificación no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		actividad.setFloatcantplanifao(new BigDecimal(0));
		actividad.setValorunitario(new BigDecimal(0));
		actividad.setFloatcantidadejecutao((double) 0);
		actividad.setNumvalorplanifao(new BigDecimal(0));
		actividad.setValortotalactividadaiu(new BigDecimal(0));
		
		actividad.setNewfloatcantplanifao(objRequest.getCantidad());
		actividad.setNewvalorunitario(objRequest.getValorunitario());
		actividad.setNewnumvalorplanifao(new BigDecimal(0));
		
		//ojo error
		actividad.setNewvalortotalactividadaiu(objRequest.getValortotal());
		
		actividad.setBoolaiu(false);
		actividad.setTipomodificacion(Constantes.ACTIVIDAD_AGREGADA);
		serviceActividadObraMod.guardar(actividad);
		
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
		ActividadObraModificacion actividad = new ActividadObraModificacion();
		actividad = serviceActividadObraMod.buscarPorId(objRequest.getId());
		if (actividad==null) {
			response.setStatus(false);
			response.setMessage("Actividad de obra no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			actividad.setNewfechainicio(formato.parse(objRequest.getFechainicio()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(objRequest.getFechainicio());
		try {
			actividad.setNewfechafin(formato.parse(objRequest.getFechafin()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Obra obra = serviceObra.buscarPorId(actividad.getIntcodigoobra());
		if (obra==null) {
			response.setStatus(false);
			response.setMessage("Obra no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		
		
		ObraModificacion obraModificacion = serviceObraModificacion.buscarPorIdEstado(actividad.getIntcodigoobra(),
				Constantes.MODIFICACION_INICIADA);
		actividad.setObraModificacion(obraModificacion);
		if (obraModificacion==null) {
			response.setStatus(false);
			response.setMessage("Obra en modificación no existe");
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		
		actividad.setNewvalorunitario(objRequest.getValorunitario());
		actividad.setNewfloatcantplanifao(objRequest.getCantidad());
		actividad.setNewnumvalorplanifao(new BigDecimal(0));
		actividad.setTipoModificacion(Constantes.ACTIVIDAD_MODIFICADA);
		serviceActividadObraMod.actualizar(actividad);
		
		response.setStatus(true);
		response.setMessage("Actualizado con éxito");
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