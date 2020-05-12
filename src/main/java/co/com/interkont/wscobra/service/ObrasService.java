
package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.ObrasRepository;
import co.com.interkont.wscobra.dao.PeriodosRepository;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.dto.Periodo;

@Service
public class ObrasService {
	
	@Autowired
	ObrasRepository obrasRepository;
	
	public Obra findById(Integer id){
		return obrasRepository.findById(id).get();		
	}
	
}