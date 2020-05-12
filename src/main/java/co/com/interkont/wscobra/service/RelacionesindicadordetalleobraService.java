
package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.RelacionesindicadordetalleobraRepository;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.dto.Relacionindicadordetalleobra;

@Service
public class RelacionesindicadordetalleobraService {
	
	@Autowired
	RelacionesindicadordetalleobraRepository relacionesindicadordetalleobraRepository;
	
	
	public List<Relacionindicadordetalleobra> findByObra(Obra obra){
		return relacionesindicadordetalleobraRepository.findByObra(obra);		
	}
	
	public void saveAll(List<Relacionindicadordetalleobra> relacionindicadordetalleobras) {
		relacionesindicadordetalleobraRepository.saveAll(relacionindicadordetalleobras);
	}

}
