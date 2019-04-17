package co.com.interkont.wscobra.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.ImagenesProyectoRepository;
import co.com.interkont.wscobra.dto.ImagenEvolucionObra;


@Service
public class ImagenesProyectoService {
	
	@Autowired 
	ImagenesProyectoRepository imagenesProyectoDAO;
	
	
	public List<ImagenEvolucionObra> getImagenesProyecto(Integer codigoproyecto){
		
		return imagenesProyectoDAO.findByCodigoProyecto(codigoproyecto);
		
	}

}
