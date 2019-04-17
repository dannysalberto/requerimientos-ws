package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dto.VistaDatosProyecto;
import co.com.interkont.wscobra.dao.DatosProyectoRepository;

@Service
public class DatosProyectoService {

	
	@Autowired 
	DatosProyectoRepository datosProyectoDAO;
	
	public VistaDatosProyecto getDatosProyecto(Integer codigoproyecto) {
		
		return datosProyectoDAO.findByCodigoproyecto(codigoproyecto);
	
	}
	
}
