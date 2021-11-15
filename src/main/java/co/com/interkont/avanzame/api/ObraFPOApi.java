package co.com.interkont.avanzame.api;

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

import co.com.interkont.avanzame.api.request.ObraFPORequest;
import co.com.interkont.avanzame.api.response.ResponseGeneric;
import co.com.interkont.avanzame.businnes.BussinnesObraFPOServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para obtener los datos relacionados a FPO desde la obra",consumes="application/json")
@CrossOrigin(origins="*")
public class ObraFPOApi {
	
	@Autowired
	BussinnesObraFPOServices service;
	
	@ResponseBody
	@RequestMapping(value="/obrafpo", method=RequestMethod.PUT)
	@ApiOperation(value = "Actualizar datos FPO de una obra.")
	public ResponseEntity<?> actualizarSolicitud(@Valid @RequestBody ObraFPORequest obra,
			BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			ResponseGeneric response = new ResponseGeneric();
			response.setStatus(false);
			response.setMensaje(bindingResult.getFieldErrors().get(0).getDefaultMessage());
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);

		}
		 
		return this.service.guardarObraFPO(obra);				
	}
	
	@ResponseBody
	@RequestMapping(value= {"/obrafpo/{idobra}"} , method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una solicitud.")
	public ResponseEntity<?> obtenerObraFPO(@PathVariable("idobra") Integer id){
		
		return this.service.obtenerObraFPO(id);	
		
	}

}
