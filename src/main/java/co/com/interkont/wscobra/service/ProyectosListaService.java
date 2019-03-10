
package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.api.request.ProyectosListaRequest;
import co.com.interkont.wscobra.dao.ProyectosListaRepository;
import co.com.interkont.wscobra.dto.VistaProyectosLista;

@Service
public class ProyectosListaService {
	
	@Autowired
	ProyectosListaRepository proyectoslistaDAO;
	
	
	public List<VistaProyectosLista> findByAll(ProyectosListaRequest proyectosListaRequest){
		
		
		if(!proyectosListaRequest.getFiltroproyectos().getNombreproyecto().equals("")) {
			return proyectoslistaDAO.findByNombreproyecto(proyectosListaRequest.getLatitud(), 
														  proyectosListaRequest.getLongitud(), 
														  proyectosListaRequest.getFiltroproyectos().getNombreproyecto());
		}else if(proyectosListaRequest.getFiltroproyectos().getCategoriaproyecto() != 0) {
			return proyectoslistaDAO.findByCodigocategoria(proyectosListaRequest.getLatitud(), 
														   proyectosListaRequest.getLongitud(), 
														   proyectosListaRequest.getFiltroproyectos().getCategoriaproyecto());
		} 
		
		return proyectoslistaDAO.findByAll(proyectosListaRequest.getLatitud(), proyectosListaRequest.getLongitud());		
	}	

}
