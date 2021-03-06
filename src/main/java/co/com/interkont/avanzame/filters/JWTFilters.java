package co.com.interkont.avanzame.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.interkont.avanzame.auth.pojo.UsuarioDetailsServiceImpl;
import co.com.interkont.avanzame.config.Constantes;
import co.com.interkont.avanzame.utils.AppContext;
import co.com.interkont.avanzame.utils.JWTUtils;
import co.com.interkont.avanzame.utils.Utils;
@Component
public class JWTFilters extends OncePerRequestFilter{

	@Autowired
	JWTUtils jwtService;
	
	@Autowired
	UsuarioDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AppContext appContext;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String header = request.getHeader(Constantes.HEADER_AUTHORIZACION_KEY);
		String usuario = null;
		String JWTtoken = null;
		
		
		if (header != null && header.startsWith(Constantes.TOKEN_BEARER_PREFIX)) {
			JWTtoken = header.substring(6);
			usuario = jwtService.extractUsuario(JWTtoken);	
			appContext.setUsuarioId(Utils.extractAllClaims(JWTtoken).get("usuId").toString());

		}
		
		if (usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(usuario);
			if (jwtService.validateToken(JWTtoken, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
				usernamePasswordAuthenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			

		}
		filterChain.doFilter(request, response);
	}

}

