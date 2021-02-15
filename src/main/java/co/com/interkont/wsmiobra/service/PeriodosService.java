package co.com.interkont.wsmiobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.repository.PeriodoRepository;

@Service
public class PeriodosService {
	
	@Autowired
	PeriodoRepository periodosRepository;
	
	public Periodo findById(Integer id){
		return periodosRepository.findById(id).get();		
	}
	
}
