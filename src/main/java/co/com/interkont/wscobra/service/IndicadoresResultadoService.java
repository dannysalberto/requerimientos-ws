package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dto.Base;
import co.com.interkont.wscobra.dao.IndicadoresResultadosRepository;


@Service
public class IndicadoresResultadoService {
	
	@Autowired
	IndicadoresResultadosRepository indicadoresResultadosDAO;
	
	
	public List<Base> getIndicadoresResultadosProyecto (Integer codigoproyecto){
		return indicadoresResultadosDAO.findByIntcodigoobra(codigoproyecto);
		
	}
	

}
