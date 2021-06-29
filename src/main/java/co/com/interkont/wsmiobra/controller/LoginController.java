package co.com.interkont.wsmiobra.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wsmiobra.auth.LDAP;
import co.com.interkont.wsmiobra.auth.pojo.UsuarioDetailsServiceImpl;
import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.models.AuthenticationRequest;
import co.com.interkont.wsmiobra.models.AuthenticationResponse;
import co.com.interkont.wsmiobra.utils.JWTUtils;

@RestController
@CrossOrigin(origins="*")
public class LoginController {
	
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTUtils jwtService;
	
	@Autowired
	UsuarioDetailsServiceImpl userDetailsService;
		
	@Autowired
	LDAP LdapService;
	 
	@Autowired
	Environment env;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String Home() {
		return "Testing Security";
	}
	
	private static Boolean bContinue;
	
	@RequestMapping(value=Constantes.LOGIN_URL, method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response )
		throws Exception{
		bContinue = false;  //validamos si debe continuar la authenticación
	    String token = null;
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsuario());

	    //si el usuario requiere LDAP, entonces vamos a validar contra LDAP
		if (userDetailsService.getRequiereLDAP()) {
			
			int result = this.authLDAP(authenticationRequest);
			if (result == 0) {
				//si la autenticación resulta succesfull, entonces ya la validación contra BD no procede
				bContinue = true;
			}else {
					return ResponseEntity.status(HttpStatus.FORBIDDEN)
							.body(String.valueOf(LdapService.getError()));
			}
		}
		
		if (!bContinue) {
			try {
				authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsuario(), 
						authenticationRequest.getContrasena(),new ArrayList<>()));
			} catch (BadCredentialsException e) {
						System.out.println("Usuario o contraseña inválido");
						return ResponseEntity.status(HttpStatus.FORBIDDEN)
								.body(String.valueOf("Usuario o contraseña inválido"));
			}
			
		}
		
		//respuesta a la cabecera
		HttpHeaders responseHeaders = new HttpHeaders();
		token = this.getToken(authenticationRequest,userDetails);
	    responseHeaders.set(Constantes.HEADER_AUTHORIZACION_KEY, token);
	    
	    @SuppressWarnings("unused")
		AuthenticationResponse Authenticate = new AuthenticationResponse(token);
	    return ResponseEntity.ok()
	      .headers(responseHeaders).body("Authenticated succesfull: "+token);
		
	}
	
	public String getToken(AuthenticationRequest authenticationRequest, UserDetails userDetails) {
		final String JavaWebToken = jwtService.generateToken(userDetails);
		String token = Constantes.TOKEN_BEARER_PREFIX + " " + JavaWebToken ; 
		return token;
	}
	
	private int authLDAP(AuthenticationRequest authenticationRequest) {
		
		@SuppressWarnings("static-access")
		int resp = LdapService.validarLDAP(authenticationRequest.getUsuario(),
					authenticationRequest.getContrasena(), env);
		
		return resp;
		
		
	}
	
	

}
