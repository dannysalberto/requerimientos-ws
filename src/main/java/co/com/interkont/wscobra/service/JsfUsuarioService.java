package co.com.interkont.wscobra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dto.JsfUsuario;
import co.com.interkont.wscobra.dao.JsfUsuarioRepository;

@Service("usuarioService")
public class JsfUsuarioService implements UserDetailsService {
	
	@Autowired
	@Qualifier("jsfUsuarioRepository")
	private JsfUsuarioRepository jsfUsuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JsfUsuario user = jsfUsuarioRepository.findByUsuLogin(username);
		return new User(user.getUsuLogin(), user.getUsuPasswd(), 
				user.getUsuEstado(), user.getUsuEstado(), user.getUsuEstado(), user.getUsuEstado(), buildgrante((byte) 1) );
	}
	
	public List<GrantedAuthority> buildgrante(byte rol){
		String[] roles = {"LECTOR","USUARIO","ADMINISTRADOR"};
		List<GrantedAuthority> auths = new ArrayList<>();
		for(int i = 0; i<rol; i++) {
			auths.add(new SimpleGrantedAuthority(roles[i]));
		}
		return auths;
	}
	
}