package co.com.interkont.avanzame.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.avanzame.api.request.RequerimientoRequest;
import co.com.interkont.avanzame.businnes.RequerimientosBussines;
import co.com.interkont.avanzame.models.Requerimiento;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@Api(value = "Catalogo de requerimientos", consumes = "application/json", produces = "application/json" , tags = { "requerimientos" })
public class RequerimientoApi {
	
	@Autowired
	RequerimientosBussines service;
	
	@ResponseBody
	@RequestMapping(value= {"/requerimiento/list"} , method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una lista de requerimientos.",nickname = "list")
    public ResponseEntity<?> obtenerRequerimientos(){
		
		return new ResponseEntity<List<Requerimiento>>(this.service.obtenerRequerimientos(), HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = {"/requerimiento/save"}, method = {RequestMethod.POST})
	@ApiOperation(value = "Guardar un requerimiento.",nickname = "save")
    public ResponseEntity<?> guardar(@RequestBody(required=true) RequerimientoRequest request) {		

		return new ResponseEntity<>(this.service.crear(request), HttpStatus.OK);		
	}
	
	@RequestMapping(value = {"/requerimiento/delete/{id}"}, method = {RequestMethod.DELETE})
	@ApiOperation(value = "Permite borrar la configuración de un requerimiento.", nickname="delete")
	public ResponseEntity<?> borrar(@PathVariable("id") Integer id) {		
		
		return new ResponseEntity<>(this.service.borrar(id), HttpStatus.OK);		
	}

}
