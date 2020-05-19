package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.AspectosEvaluarRepository;
import co.com.interkont.wscobra.dao.FactorAtrasoRepository;
import co.com.interkont.wscobra.dao.TipoFactorAtrasoRepository;
import co.com.interkont.wscobra.dto.AspectoEvaluar;
import co.com.interkont.wscobra.dto.FactorAtraso;
import co.com.interkont.wscobra.dto.TipoFactorAtraso;

@Service
public class ParametrosService {

	@Autowired
	TipoFactorAtrasoRepository tipoFactorAtrasoRepository; 
	
	@Autowired
	FactorAtrasoRepository factorAtrasoRepository;
	
	@Autowired
	AspectosEvaluarRepository aspectosEvaluarRepository;
	
	public List<TipoFactorAtraso> tipoFactorAtrasoAll(){
		return tipoFactorAtrasoRepository.findAll();
	}
	
	public List<FactorAtraso> factoresAtrasoAll(){
		return factorAtrasoRepository.findAll();
	}
	
	public List<AspectoEvaluar> aspectosEvaluarAll(){
		return aspectosEvaluarRepository.findAll();
	}

}
