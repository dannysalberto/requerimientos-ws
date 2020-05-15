
package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.VistaActividadesRepository;
import co.com.interkont.wscobra.dto.VistaActividades;

@Service
public class VistaActividadesService {
	
	@Autowired
	VistaActividadesRepository actividadesRepository;
	
	
	public List<VistaActividades> findByCodigoProyecto(Integer codigoProyecto){
		return actividadesRepository.findByCodigoProyecto(codigoProyecto);		
	}

}
