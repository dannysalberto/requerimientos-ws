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

/**
 * imports DTO
 */
import co.com.interkont.wscobra.dto.VistaProyectosLista;
import co.com.interkont.wscobra.dto.VistaProyectosMapa;
import co.com.interkont.wscobra.dto.VistaDatosProyecto;
import co.com.interkont.wscobra.dto.ImagenEvolucionObra;
import co.com.interkont.wscobra.dto.Base;
import co.com.interkont.wscobra.dto.VistaContratistasContratoProyecto;

/**
 * imports SERVICE
 */
import co.com.interkont.wscobra.service.ProyectosListaService;
import co.com.interkont.wscobra.service.ProyectosMapaService;
import co.com.interkont.wscobra.service.IndicadoresGlobalesService;
import co.com.interkont.wscobra.service.DatosProyectoService;
import co.com.interkont.wscobra.service.ImagenesProyectoService;
import co.com.interkont.wscobra.service.IndicadoresResultadoService;
import co.com.interkont.wscobra.service.ContratistasProyectoService;

/**
 * imports REQUEST
 */
import co.com.interkont.wscobra.api.request.ProyectosListaRequest;
import co.com.interkont.wscobra.api.request.ProyectosMapaRequest;
import co.com.interkont.wscobra.api.request.DatosProyectoRequest;

/**
 * imports RESPONSE
 */
import co.com.interkont.wscobra.api.response.ProyectosListaResponse;
import co.com.interkont.wscobra.api.response.ProyectosMapaResponse;
import co.com.interkont.wscobra.api.response.DatosProyectoResponse;
import co.com.interkont.wscobra.api.response.ImagenesProyectoResponse;
import co.com.interkont.wscobra.api.response.IndicadoresResultadosResponse;
import co.com.interkont.wscobra.api.response.ContratistasProyectoResponse;



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
	DatosProyectoService datosProyectoService;
	
	@Autowired 
	ImagenesProyectoService imagenesProyectoService;
	
	@Autowired
	IndicadoresResultadoService indicadoresResultadoService;
	
	@Autowired
	ContratistasProyectoService contratistasProyectoService;
	
	
	@Autowired
	Mapper mapper;
	
	
	@RequestMapping(value="/vista-lista", method=RequestMethod.POST)
	@ApiOperation(value = "Listado de proyectos para la vista lista del home.", 
				  notes = "Este servicio retorna los proyectos de vista de lista.")
	public List<ProyectosListaResponse> getListaProyectos(@RequestBody ProyectosListaRequest proyectosListaRequest){
		
		List<VistaProyectosLista> proyectos = proyectosListaService.findByAll(proyectosListaRequest);
		
		List<ProyectosListaResponse> proyectosResponse = new ArrayList<ProyectosListaResponse>();

		for (VistaProyectosLista proyecto : proyectos) {
			String distacia = Utils.distanciaCoord(proyecto.getLatitudproyecto(), proyecto.getLongitudproyecto(), proyectosListaRequest.getLatitud(), proyectosListaRequest.getLongitud());
			
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
			String distacia = Utils.distanciaCoord(proyecto.getLatitudproyecto(), proyecto.getLongitudproyecto(), proyectosMapaRequest.getLatitud(), proyectosMapaRequest.getLongitud());
			
			ProyectosMapaResponse proyectoResponse = mapper.map(proyecto, ProyectosMapaResponse.class); 
			
			proyectoResponse.setDistaciaproyecto(distacia);
			
			proyectosResponse.add(proyectoResponse);
		}
		
		return proyectosResponse;
	}
	
	
	@RequestMapping(value="/detalle-proyecto", method=RequestMethod.POST)
	@ApiOperation(value = "Detalle del proyecto u/o obra", 
				  notes = "Este servicio retorna la información básica de un proyecto")
	public DatosProyectoResponse getDatosProyecto(@RequestBody DatosProyectoRequest datosProyectoRequest) {
		
		VistaDatosProyecto proyecto = datosProyectoService.getDatosProyecto(datosProyectoRequest.getCodigoproyecto());

		List<ImagenEvolucionObra> imagenesProyecto = imagenesProyectoService.getImagenesProyecto(datosProyectoRequest.getCodigoproyecto());
		List<ImagenesProyectoResponse> imagenesProyectoResponse = new ArrayList<ImagenesProyectoResponse>();
		
		List<Base> indicadoresProyecto = indicadoresResultadoService.getIndicadoresResultadosProyecto(datosProyectoRequest.getCodigoproyecto());
		List<IndicadoresResultadosResponse> indicadoresProyectoResponse = new ArrayList<IndicadoresResultadosResponse>();
		
		List<VistaContratistasContratoProyecto> contratistasProyecto = contratistasProyectoService.getContratistasProyecto(datosProyectoRequest.getCodigoproyecto());
		List<ContratistasProyectoResponse> contratistasProyectoResponse = new ArrayList<ContratistasProyectoResponse>();
		
		for (ImagenEvolucionObra imagen : imagenesProyecto) {
			ImagenesProyectoResponse imagenReponse = mapper.map(imagen, ImagenesProyectoResponse.class);
			imagenesProyectoResponse.add(imagenReponse);
		}
		
		for (Base indicador : indicadoresProyecto) {
			IndicadoresResultadosResponse indicadorRespose = mapper.map(indicador, IndicadoresResultadosResponse.class);
			indicadoresProyectoResponse.add(indicadorRespose);
		}
		
		for (VistaContratistasContratoProyecto contratista : contratistasProyecto) {
			ContratistasProyectoResponse contratistaResponse = mapper.map(contratista, ContratistasProyectoResponse.class);			
			contratistasProyectoResponse.add(contratistaResponse);
		}
		
		
		String distancia = Utils.distanciaCoord(proyecto.getLatitudproyecto(), proyecto.getLongitudproyecto(), datosProyectoRequest.getLatitud(), datosProyectoRequest.getLongitud());		
		
		DatosProyectoResponse proyectoResponse = mapper.map(proyecto, DatosProyectoResponse.class);
		
		proyectoResponse.setDistancia(distancia);
		proyectoResponse.setImagenesproyecto(imagenesProyectoResponse);
		proyectoResponse.setIndicadoresresultadosproyecto(indicadoresProyectoResponse);
		proyectoResponse.setContratistasproyecto(contratistasProyectoResponse);
		
		return proyectoResponse;
	}
	


}
