
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
	
	
	public List<VistaProyectosLista> findByUsuario(ProyectosListaRequest proyectosListaRequest){
		return proyectoslistaDAO.findByUsuario(proyectosListaRequest.getUsuario());		
	}	

}
