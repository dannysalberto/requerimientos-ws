package co.com.interkont.wscobra.api;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * imports DTO
 */
import co.com.interkont.wscobra.dto.VistaProyectosLista;

/**
 * imports SERVICE
 */
import co.com.interkont.wscobra.service.ProyectosListaService;


/**
 * imports REQUEST
 */
import co.com.interkont.wscobra.api.request.ProyectosListaRequest;
//import co.com.interkont.wscobra.api.request.DatosProyectoRequest;

/**
 * imports RESPONSE
 */
import co.com.interkont.wscobra.api.response.ProyectosListaResponse;
//import co.com.interkont.wscobra.api.response.DatosProyectoResponse;



//@RestController
@Api(value = "Api para servicios de proyectos", 
     description = "Está API, muestra los servicisios de vista lista de proyectos, vista mapa",
     consumes="application/json")

public class ProyectosApi {
	
	@Autowired
	ProyectosListaService proyectosListaService;
	
	@Autowired
	Mapper mapper;
	
	
	@RequestMapping(value="/vista-lista", method=RequestMethod.POST)
	@ApiOperation(value = "Listado de proyectos para la vista lista del home.", 
				  notes = "Este servicio retorna los proyectos de vista de lista.")
	public List<ProyectosListaResponse> getListaProyectos(@RequestBody ProyectosListaRequest proyectosListaRequest){
		
		List<VistaProyectosLista> proyectos = proyectosListaService.findByUsuario(proyectosListaRequest);
		
		List<ProyectosListaResponse> proyectosResponse = new ArrayList<ProyectosListaResponse>();

		for (VistaProyectosLista proyecto : proyectos) {
			ProyectosListaResponse proyectoResponse = mapper.map(proyecto, ProyectosListaResponse.class);
			proyectosResponse.add(proyectoResponse);
		}
		
		return proyectosResponse;
	}
}
