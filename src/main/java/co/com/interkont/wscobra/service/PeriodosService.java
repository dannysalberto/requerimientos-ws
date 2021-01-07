package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.models.Periodo;
import co.com.interkont.wscobra.repository.PeriodoRepository;

@Service
public class PeriodosService {
	
	@Autowired
	PeriodoRepository periodosRepository;
	
	public Periodo findById(Integer id){
		return periodosRepository.findById(id).get();		
	}
	
}
