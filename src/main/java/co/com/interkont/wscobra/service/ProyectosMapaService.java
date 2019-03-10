
package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.api.request.ProyectosMapaRequest;
import co.com.interkont.wscobra.dao.ProyectosMapaRepository;
import co.com.interkont.wscobra.dto.VistaProyectosMapa;

@Service
public class ProyectosMapaService {
	
	@Autowired
	ProyectosMapaRepository proyectoMapaDAO;
	
	
	public List<VistaProyectosMapa> findByAll(ProyectosMapaRequest proyectosMapaRequest){
		
		
		if(!proyectosMapaRequest.getFiltroproyectos().getNombreproyecto().equals("")) {
			return proyectoMapaDAO.findByNombreproyecto(
					proyectosMapaRequest.getLatitud(), 
					proyectosMapaRequest.getLongitud(), 
					proyectosMapaRequest.getFiltroproyectos().getNombreproyecto()
					);
		}else if(proyectosMapaRequest.getFiltroproyectos().getCategoriaproyecto() != 0) {
			return proyectoMapaDAO.findByCodigocategoria(
					proyectosMapaRequest.getLatitud(), 
					proyectosMapaRequest.getLongitud(), 
					proyectosMapaRequest.getFiltroproyectos().getCategoriaproyecto()
					);
		} 
		
		return proyectoMapaDAO.findByAll(proyectosMapaRequest.getLatitud(), proyectosMapaRequest.getLongitud());		
	}	

}