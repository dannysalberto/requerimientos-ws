
package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.auth.pojo.JsfUsuario;
import co.com.interkont.wscobra.auth.pojo.JsfUsuarioRepository;

@Service
public class JsfUsuariosService {
	
	@Autowired
	private JsfUsuarioRepository jsfUsuarioRepository;
	
	public JsfUsuario findByUsuLogin(String usuLogin){
		return jsfUsuarioRepository.findByUsuLogin(usuLogin);		
	}
	
}
