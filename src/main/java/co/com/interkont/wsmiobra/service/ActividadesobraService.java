
package co.com.interkont.wsmiobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.dao.ActividadesobraRepository;
import co.com.interkont.wsmiobra.dto.Actividadobra;

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
