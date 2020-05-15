
package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.ActividadesobraRepository;
import co.com.interkont.wscobra.dto.Actividadobra;

@Service
public class ActividadesobraService {
	
	@Autowired
	ActividadesobraRepository actividadesobraRepository;
	
	public Actividadobra findByOidactiviobra(Long oidactiviobra){
		return actividadesobraRepository.findByOidactiviobra(oidactiviobra);		
	}
	
	public void save(Actividadobra actividadobra) {
		actividadesobraRepository.save(actividadobra);
	}

}
