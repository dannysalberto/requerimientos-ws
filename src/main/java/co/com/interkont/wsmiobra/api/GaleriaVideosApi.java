package co.com.interkont.wsmiobra.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wsmiobra.api.request.GaleriaVideosRequest;
import co.com.interkont.wsmiobra.auth.config.ConfiguracionConstantes;
import co.com.interkont.wsmiobra.dto.GaleriaVideos;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.GaleriaVideosService;
import co.com.interkont.wsmiobra.service.ObrasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@RestController
@Api(value = "Api para gestionar los videos relacionados con la obra",
     consumes="application/json")
@CrossOrigin(origins="*")
public class GaleriaVideosApi {

	@Autowired
	GaleriaVideosService serviceGaleria;
	
	@Autowired
	ObrasService serviceObra;
	
	@RequestMapping(value="/galeriavideos/{idObra}", method=RequestMethod.GET)
	@ApiOperation(value = "Listado de videos asociados a una obra.")
	public ResponseEntity<?> listarPorObra(@PathVariable(name="idObra") Integer idObra){

		ResponseGeneric response = new ResponseGeneric(); 

		List<GaleriaVideos> lstVideos = serviceGaleria.findByAllVideos(idObra);

		if (!lstVideos.isEmpty()) {
			return new ResponseEntity<List<GaleriaVideos>>(lstVideos, HttpStatus.OK);
		}else {
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/galeriavideos", method=RequestMethod.POST)
	@ApiOperation(value = "Datos necesarios para cargar un video.")
	public ResponseEntity<?> save(@RequestBody(required=true) GaleriaVideosRequest galeriavideo){
		
		ResponseGeneric response = new ResponseGeneric();
		if (galeriavideo==null) {
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.DATOS_INCORRECTOS);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.BAD_REQUEST);
		}
		Obra obra = serviceObra.buscarPorId(galeriavideo.getObra_id());
		
		if (obra==null) {
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.NO_EXISTE_OBRA);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		GaleriaVideos obj = new GaleriaVideos (); 
		obj.setFecha_carga(new Date());
		obj.setNombre(galeriavideo.getNombre());
		obj.setObra(obra);
		obj.setTipovideo(galeriavideo.getTipovideo());
		obj.setUrl(galeriavideo.getUrl());
		System.out.println(obj);
		serviceGaleria.Guardar(obj);
		return new ResponseEntity<GaleriaVideos>(obj, HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value="/galeriavideos/{idVideo}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Borrar un video cargado a una obra.")
	public ResponseEntity<?> save(@PathVariable(name="idVideo") Integer idVideo){
		
		ResponseGeneric response = new ResponseGeneric();
		if (idVideo==null) {
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.DATOS_INCORRECTOS);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.BAD_REQUEST);
		}
		if (serviceGaleria.Borrar(idVideo)) {
			response.setStatus(true);
			response.setMessage(ConfiguracionConstantes.REGISTRO_ELIMINADO);
			
		}else{
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.DATOS_INCORRECTOS);
			
		}
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		
		
	}
	
}
