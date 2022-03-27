package co.com.interkont.avanzame.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.avanzame.businnes.TipoDocumentoBussines;
import co.com.interkont.avanzame.models.TipoDocumento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@Api(value = "Catalogo de tipo de documentos", consumes = "application/json", produces = "application/json" , tags = { "tipodocumentos" })
public class TipoDocumentoApi {
	
	@Autowired
	TipoDocumentoBussines service;
	
	@ResponseBody
	@RequestMapping(value= {"/tipodocumento/list"} , method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una lista de tipos de documentos.",nickname = "list")
    public ResponseEntity<?> obtenerTiposDocumentos(){
		
		return new ResponseEntity<List<TipoDocumento>>(this.service.obtenerTipoDocumentos(), HttpStatus.OK);	
		
	}
}
