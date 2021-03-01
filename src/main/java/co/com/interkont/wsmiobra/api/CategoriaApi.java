package co.com.interkont.wsmiobra.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.models.Categoria;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar las categorias de actividades cronograma web",
     consumes="application/json")
@CrossOrigin(origins="*")
public class CategoriaApi {

	@Autowired
	CategoriaService service;
	
	@RequestMapping(value="/categoria", method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una lista de categorias.")
	public ResponseEntity<?> listarCategorias(){

		ResponseGeneric response = new ResponseGeneric(); 

		List<Categoria> lstCategorias = service.obtenerCategorias();

		if (!lstCategorias.isEmpty()) {
			return new ResponseEntity<List<Categoria>>(lstCategorias, HttpStatus.OK);
		}else {
			response.setStatus(false);
			response.setMessage(Constantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		
	}
}
