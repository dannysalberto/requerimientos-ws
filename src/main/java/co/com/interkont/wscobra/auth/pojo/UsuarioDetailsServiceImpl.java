package co.com.interkont.wscobra.auth.pojo;



import static java.util.Collections.emptyList;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.auth.LDAP;
import co.com.interkont.wscobra.auth.encrypt.Encrypter;
import co.com.interkont.wscobra.service.JsfUsuariosService;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private JsfUsuariosService jsfUsuariosService;
	
	@Autowired
	public Environment env;

	private Boolean requieredLDAP;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		JsfUsuario usuario = jsfUsuariosService.findByUsuLogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		this.setRequiereLDAP(usuario.getLdap());
		/*Encrypter encrypter;
		String password = null;
		try {
			encrypter = Encrypter.getInstance();
			password = encrypter.decrypt(usuario.getUsuPasswd());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//Se utiliza el atributo accountNonLocked para bloquear el usuario si está configurado con ldap pero 
		//la autenticacion ldap no fue exitosa
		//sboolean accountNonLocked = ((usuario.getLdap())?(LDAP.validarLDAP(usuario.getUsuLogin(), password, env)):true);
		boolean accountNonLocked = true;
		boolean accountNonExpired = ((usuario.getUsuFchaVncmnto().after(new Date()))?true:false);
		User user = new User(usuario.getUsuLogin(), usuario.getUsuPasswd(), 
					usuario.getUsuEstado(), accountNonExpired, true, accountNonLocked, emptyList());
		return user;
	}
	
	public Boolean getRequiereLDAP() {
		return requieredLDAP;
	}
	
	public Boolean setRequiereLDAP(Boolean valor) {
		return this.requieredLDAP = valor;
	}
	
}