
package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.ImagenesevolucionobraRepository;
import co.com.interkont.wscobra.dto.Imagenevolucionobra;

@Service
public class ImagenesevolucionobraService {
	
	@Autowired
	ImagenesevolucionobraRepository imagenesevolucionobraRepository;
	
	public void save(Imagenevolucionobra imagenevolucionobra){
		imagenesevolucionobraRepository.save(imagenevolucionobra);		
	}
	
}
