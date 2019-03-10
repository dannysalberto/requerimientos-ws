package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.api.request.IndicadoresGlobalesRequest;
import co.com.interkont.wscobra.dao.IndicadoresGlobalesRepository;
import co.com.interkont.wscobra.dto.VistaIndicadoresGlobales;

@Service
public class IndicadoresGlobalesService {
	
	@Autowired
	IndicadoresGlobalesRepository indicadoresGlobalesDAO;
	
	
	public List<VistaIndicadoresGlobales> findByAll(IndicadoresGlobalesRequest indicadoresGlobalesRequest){
		
		
		if(indicadoresGlobalesRequest.getCodigocategoria() != -1) {
			return indicadoresGlobalesDAO.findByCodigocategoria(indicadoresGlobalesRequest.getCodigocategoria());
		} 
		
		return indicadoresGlobalesDAO.findAll();		
	}	

}