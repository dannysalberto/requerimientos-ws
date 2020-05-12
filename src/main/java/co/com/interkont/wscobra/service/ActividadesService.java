
package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.ActividadesRepository;
import co.com.interkont.wscobra.dto.VistaActividades;

@Service
public class ActividadesService {
	
	@Autowired
	ActividadesRepository actividadesDAO;
	
	
	public List<VistaActividades> findByCodigoProyecto(Integer codigoProyecto){
		return actividadesDAO.findByCodigoProyecto(codigoProyecto);		
	}
	
	public VistaActividades findById(Integer id){
		return actividadesDAO.findById(id).get();		
	}

}
