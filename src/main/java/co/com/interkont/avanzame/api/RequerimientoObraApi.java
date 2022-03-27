package co.com.interkont.avanzame.api;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.avanzame.api.request.RequerimientoObraRequest;
import co.com.interkont.avanzame.api.request.RequerimientoObraRequestUpd;
import co.com.interkont.avanzame.api.response.RequerimientoObraResponse;
import co.com.interkont.avanzame.businnes.RequerimientosObraBussines;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@Api(value = "Requerimientos de obra", consumes = "application/json", produces = "application/json" , tags = { "requerimientoObra" })
public class RequerimientoObraApi {
	
	@Autowired
	RequerimientosObraBussines service;
	
	@ResponseBody
	@RequestMapping(value= "/workreq/list/{idobra}" , method=RequestMethod.GET)
	@ApiOperation(value = "Listar requerimientos asociados a una obra", nickname="list")
	public ResponseEntity<?> obtenerRequerimientos(@PathVariable("idobra") @NotNull @Positive Integer obraid){
		
		return new ResponseEntity<List<RequerimientoObraResponse>>(this.service.obtenerRequerimientos(obraid), HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = "/workreq/save", method = {RequestMethod.POST})
	@ApiOperation(value = "Guardar un requerimiento para una obra.", nickname="save")
	public ResponseEntity<?> guardar(@RequestBody(required=true) @Valid List<RequerimientoObraRequest> request) {		

		return new ResponseEntity<>(this.service.crear(request), HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/workreq/update", method = {RequestMethod.PUT})
	@ApiOperation(value = "Guardar un requerimiento para una obra.", nickname="save")
	public ResponseEntity<?> actualizar(@RequestBody(required=true) List<RequerimientoObraRequestUpd> request) {		

		return new ResponseEntity<>(this.service.actualizar(request), HttpStatus.OK);		
	}

	
	@RequestMapping(value = {"/workreq/delete/{id}"}, method = {RequestMethod.DELETE})
	@ApiOperation(value = "Borrar un requerimiento para una obra.", nickname="delete") 
	public ResponseEntity<?> borrar(@PathVariable("id") Integer id) {		
		
		return new ResponseEntity<>(this.service.borrar(id), HttpStatus.OK);		
	}

}
