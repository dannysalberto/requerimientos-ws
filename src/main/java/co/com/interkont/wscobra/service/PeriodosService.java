
package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.PeriodosRepository;
import co.com.interkont.wscobra.dto.Periodo;

@Service
public class PeriodosService {
	
	@Autowired
	PeriodosRepository periodosRepository;
	
	public Periodo findById(Integer id){
		return periodosRepository.findById(id).get();		
	}
	
}
