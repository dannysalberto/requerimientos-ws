package co.com.interkont.avanzame.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.interkont.avanzame.api.request.SolicitudFPOUpdateRequest;
import co.com.interkont.avanzame.api.response.ResponseGeneric;
import co.com.interkont.avanzame.businnes.BusinnesSolicitudFPOServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar las solicitudes FPO",consumes="application/json")
@CrossOrigin(origins="*")
public class SolicitudFPOApi {

	@Autowired
	BusinnesSolicitudFPOServices serviceSolicitudesFPO;
	
	@ResponseBody
	@RequestMapping(value= {"/solicitud/{idobra}", "/solicitud"} , method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una lista de solicitudes.")
	public ResponseEntity<?> listarSolicitudes(@PathVariable("idobra") int idobra){
		
		return this.serviceSolicitudesFPO.solicitudes(idobra);	
	}
	
	@ResponseBody
	@RequestMapping(value= {"/getsolicitud/{idsolicitud}"} , method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una solicitud.")
	public ResponseEntity<?> obtenerSolicitud(@PathVariable("idsolicitud") int id){
		
		return this.serviceSolicitudesFPO.solicitud(id);	
	}
	
	@ApiOperation(value = "Guardar una solicitud.")
	@RequestMapping(value = {"/solicitud"}, method = {RequestMethod.POST})
	public ResponseEntity<?> guardarSolicitud(@RequestBody String SolicitudFPORequest,HttpServletRequest request) 
			throws JsonParseException, JsonMappingException, IOException {
	
		return this.serviceSolicitudesFPO.guardarSolicitud(SolicitudFPORequest,request);				
	}
		
	@ResponseBody
	@RequestMapping(value="/solicitud", method=RequestMethod.PUT)
	@ApiOperation(value = "Actualizar una solicitud.")
	public ResponseEntity<?> actualizarSolicitud(@Valid @RequestBody SolicitudFPOUpdateRequest solicitud,
			BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			ResponseGeneric response = new ResponseGeneric();
			response.setStatus(false);
			response.setMensaje(bindingResult.getFieldErrors().get(0).getDefaultMessage());
			System.out.println(response.getMensaje());
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);

		}
		 
		return this.serviceSolicitudesFPO.actualizarSolicitud(solicitud);				
	}
	
}