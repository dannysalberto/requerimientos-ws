package co.com.interkont.avanzame.auth;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.interkont.avanzame.auth.pojo.JsfUsuarioRequest;
import co.com.interkont.avanzame.config.Constantes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			JsfUsuarioRequest credenciales = new ObjectMapper().readValue(request.getInputStream(), JsfUsuarioRequest.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getUsuario(), credenciales.getContrasena(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(Constantes.ISSUER_INFO)
				.setSubject(((User)auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + Constantes.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, Constantes.SUPER_SECRET_KEY).compact();
		System.out.println(Constantes.TOKEN_BEARER_PREFIX + " " + token);
		response.addHeader(Constantes.HEADER_AUTHORIZACION_KEY, Constantes.TOKEN_BEARER_PREFIX + " " + token);
	}
}