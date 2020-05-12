
package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.AlimentacionesRepository;
import co.com.interkont.wscobra.dto.Alimentacion;

@Service
public class AlimentacionesService {
	
	@Autowired
	AlimentacionesRepository alimentacionesRepository;
	
	public void save(Alimentacion alimentacion) {
		alimentacionesRepository.save(alimentacion);
	}
	
}
