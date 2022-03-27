package co.com.interkont.avanzame.api;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.com.interkont.avanzame.api.response.ResponseGenericFile;
import co.com.interkont.avanzame.businnes.DocumentosRequerimientosObraBussines;
import co.com.interkont.avanzame.models.DocumentosRequerimiento;
import co.com.interkont.avanzame.service.DocumentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins="*")
@Api(value = "Documentos de Requerimientos de obra", consumes = "application/json", produces = "application/json" , tags = { "documentosrequerimiento" })
public class DocumentosRequerimientoObraApi {
	
	@Autowired
	DocumentosRequerimientosObraBussines service;
	
	@Autowired
	DocumentoService docServices;
	
	@ResponseBody
	@RequestMapping(value= "/docreq/list/{idobra}" , method=RequestMethod.GET)
	@ApiOperation(value = "Listar requerimientos asociados a una obra", nickname="list")
	public ResponseEntity<?> obtenerDocumentosRequerimiento(@PathVariable("idobra") @NotNull @Positive Integer obraid){
		
		return new ResponseEntity<List<?>>(this.docServices.listarPorObra(obraid), HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = "/docreq/save", method = {RequestMethod.POST}, consumes  = "multipart/form-data")
    @ApiOperation(value = "Guardar un documento a requerimiento de una obra.", nickname="save")
	public ResponseEntity<?> guardar(@RequestParam("obraid") Integer obraid,
			@RequestParam("nombrearchivo") String nombrearchivo,
			@RequestParam("fecha") String fecha,
			@RequestParam("tipodoc") Integer tipodoc,
			@RequestParam("file") MultipartFile file) {		

		ResponseGenericFile resp = this.service.crear(obraid,nombrearchivo,tipodoc,file,fecha);
		return new ResponseEntity<>(resp, HttpStatus.OK);		
	}
	
	@RequestMapping(value = {"/docreq/delete/{id}"}, method = {RequestMethod.DELETE})
	@ApiOperation(value = "Borrar un documento asociado a requerimiento de obra.", nickname="delete") 
	public ResponseEntity<?> borrar(@PathVariable("id") Integer id) {		
		
		return new ResponseEntity<>(this.service.borrar(id), HttpStatus.OK);		
	}

}
