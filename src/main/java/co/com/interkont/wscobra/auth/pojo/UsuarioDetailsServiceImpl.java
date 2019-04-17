package co.com.interkont.wscobra.auth.pojo;



import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private JsfUsuarioRepository jsfUsuarioDAO;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JsfUsuario usuario = jsfUsuarioDAO.findByUsuLogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(usuario.getUsuLogin(), usuario.getUsuPasswd(), emptyList());
	}
}