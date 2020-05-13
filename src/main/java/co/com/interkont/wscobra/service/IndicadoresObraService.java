package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.IndicadoresObraRepository;
import co.com.interkont.wscobra.dto.VistaIndicadoresObra;

@Service
public class IndicadoresObraService {

	@Autowired
	IndicadoresObraRepository indicadoresObraDAO;
	
	public List<VistaIndicadoresObra> findByCodigoProyecto(Integer codigoProyecto){
		return indicadoresObraDAO.findByCodigoproyecto(codigoProyecto);		
	}
}
