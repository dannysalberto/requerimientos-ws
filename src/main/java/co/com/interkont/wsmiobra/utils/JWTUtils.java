package co.com.interkont.wsmiobra.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.config.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtils {
	
	
	public String extractUsuario(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(Constantes.SUPER_SECRET_KEY).parseClaimsJws(token).getBody();		
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims , userDetails.getUsername());
		
	}
	
	private String createToken(Map<String, Object> claims, String usuario) {
		return Jwts.builder().setIssuedAt(new Date()).setIssuer(Constantes.ISSUER_INFO)
		.setSubject(usuario)
		.setExpiration(new Date(System.currentTimeMillis() + Constantes.TOKEN_EXPIRATION_TIME))
		.signWith(SignatureAlgorithm.HS512, Constantes.SUPER_SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String usuario = extractUsuario(token);
		return (usuario.equals(userDetails.getUsername())) && !isTokenExpired(token);
		
	}

}
