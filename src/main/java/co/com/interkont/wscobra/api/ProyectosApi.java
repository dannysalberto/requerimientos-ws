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

import co.com.interkont.wscobra.utils.Utils;
import co.com.interkont.wscobra.dto.VistaProyectosLista;
import co.com.interkont.wscobra.dto.VistaProyectosMapa;
import co.com.interkont.wscobra.service.ProyectosListaService;
import co.com.interkont.wscobra.service.ProyectosMapaService;
import co.com.interkont.wscobra.service.IndicadoresGlobalesService;
import co.com.interkont.wscobra.api.request.ProyectosListaRequest;
import co.com.interkont.wscobra.api.request.ProyectosMapaRequest;
import co.com.interkont.wscobra.api.response.ProyectosListaResponse;
import co.com.interkont.wscobra.api.response.ProyectosMapaResponse;



@RestController
@Api(value = "Api para servicios de proyectos", 
     description = "Está API, muestra los servicisios de vista lista de proyectos, vista mapa",
     consumes="application/json")

public class ProyectosApi {
	
	@Autowired
	ProyectosListaService proyectosListaService;
	
	@Autowired
	ProyectosMapaService proyectosMapaService;
	
	@Autowired
	IndicadoresGlobalesService indicadoresGlobalesService;
	
	
	@Autowired
	Mapper mapper;
	
	
	@RequestMapping(value="/vista-lista", method=RequestMethod.POST)
	@ApiOperation(value = "Listado de proyectos para la vista lista del home.", 
				  notes = "Este servicio retorna los proyectos de vista de lista.")
	public List<ProyectosListaResponse> getListaProyectos(@RequestBody ProyectosListaRequest proyectosListaRequest){
		
		List<VistaProyectosLista> proyectos = proyectosListaService.findByAll(proyectosListaRequest);
		
		List<ProyectosListaResponse> proyectosResponse = new ArrayList<ProyectosListaResponse>();

		for (VistaProyectosLista proyecto : proyectos) {
			String distacia = Utils.distanciaCoord(proyecto.getLatitud(), proyecto.getLongitud(), proyectosListaRequest.getLatitud(), proyectosListaRequest.getLongitud());
			
			ProyectosListaResponse proyectoResponse = mapper.map(proyecto, ProyectosListaResponse.class); 
			
			proyectoResponse.setDistaciaproyecto(distacia);
			
			proyectosResponse.add(proyectoResponse);
		}
		
		return proyectosResponse;
	}
	
	@RequestMapping(value="/vista-mapa", method=RequestMethod.POST)
	@ApiOperation(value = "Listado de proyectos para la vista mapa del home.", 
				  notes = "Este servicio retorna los proyectos de vista mapa.")
	public List<ProyectosMapaResponse> getMapaProyectos(@RequestBody ProyectosMapaRequest proyectosMapaRequest){
		
		List<VistaProyectosMapa> proyectos = proyectosMapaService.findByAll(proyectosMapaRequest);
		
		List<ProyectosMapaResponse> proyectosResponse = new ArrayList<ProyectosMapaResponse>();

		for (VistaProyectosMapa proyecto : proyectos) {
			String distacia = Utils.distanciaCoord(proyecto.getLatitud(), proyecto.getLongitud(), proyectosMapaRequest.getLatitud(), proyectosMapaRequest.getLongitud());
			
			ProyectosMapaResponse proyectoResponse = mapper.map(proyecto, ProyectosMapaResponse.class); 
			
			proyectoResponse.setDistaciaproyecto(distacia);
			
			proyectosResponse.add(proyectoResponse);
		}
		
		return proyectosResponse;
	}
	


}
