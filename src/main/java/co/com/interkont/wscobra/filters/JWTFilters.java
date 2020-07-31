package co.com.interkont.wscobra.filters;

import static co.com.interkont.wscobra.auth.config.ConfiguracionConstantes.HEADER_AUTHORIZACION_KEY;
import static co.com.interkont.wscobra.auth.config.ConfiguracionConstantes.TOKEN_BEARER_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.interkont.wscobra.auth.pojo.UsuarioDetailsServiceImpl;
import co.com.interkont.wscobra.utils.JWTUtils;
@Component
public class JWTFilters extends OncePerRequestFilter{

	@Autowired
	JWTUtils jwtService;
	
	@Autowired
	UsuarioDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String header = request.getHeader(HEADER_AUTHORIZACION_KEY);
		String usuario = null;
		String JWTtoken = null;
		
		
		if (header != null && header.startsWith(TOKEN_BEARER_PREFIX)) {
			JWTtoken = header.substring(6);
			usuario = jwtService.extractUsuario(JWTtoken);			
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

