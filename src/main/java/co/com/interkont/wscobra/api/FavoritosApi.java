package co.com.interkont.wscobra.api;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wscobra.dto.VistaFavoritoAsignados;
import co.com.interkont.wscobra.service.FavoritosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import co.com.interkont.wscobra.utils.Utils;
import co.com.interkont.wscobra.api.request.FavoritosRequest;
import co.com.interkont.wscobra.api.response.FavoritosResponse;


@RestController
@Api(value = "Microservicio de mis proyectos favoritos", 
     description = "Está API, muestra los servicisios de mis proyectos favoritos ",
     consumes="application/json")
public class FavoritosApi {

	@Autowired
	FavoritosService favoritosService; 
	
	@Autowired
	Mapper mapper;
	
	
	@RequestMapping(value="/mis-favoritos", method=RequestMethod.POST)
	@ApiOperation(value = "Búsqueda de proyectos favoritos del usuario", 
				  notes = "Este servicio retorna los proyectos favoritos del usuario") 
	public List<FavoritosResponse> getListaFavoritos(@RequestBody FavoritosRequest favoritosRequest){
		
		List<VistaFavoritoAsignados> favoritos =favoritosService.proyectosPorUsuario(favoritosRequest);
		
		List<FavoritosResponse> favoritosResponse = new ArrayList<FavoritosResponse>();

		for (VistaFavoritoAsignados favorito : favoritos) {
			String distacia = Utils.distanciaCoord(favorito.getLatitud(), favorito.getLongitud(), favoritosRequest.getLatitud(), favoritosRequest.getLongitud());
			
			favorito.setDistaciaproyecto(distacia);
			
			favoritosResponse.add(mapper.map(favorito, FavoritosResponse.class));
		}
		
		return favoritosResponse;
	}
	
}
