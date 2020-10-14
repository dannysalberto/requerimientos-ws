package co.com.interkont.wscobra.auth.config;

public class ConfiguracionConstantes {
	
	
	// passwordEncoder
	
	public static final String ENCODER = "Custom";
	public static final String KEY_ENCODER = "euNuWPuienBrq2t/lEnpCw3f3IpFuQRA";
	
	// Spring Security	
	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Ikont ";

	// JWT	
	public static final String SUPER_SECRET_KEY = "lPOscTr2g6KeICpZ2WQ3CmxjWBfGAHOO";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
	public static final String ISSUER_INFO = "/swagger-ui.html";	
	
	public static final Integer VIDEO_CAMARA = 1;
	public static final Integer VIDEO_COMPLEMENTARIO = 2;
	
	public static final String NO_EXISTE = "No existe información para mostrar";
	public static final String DATOS_INCORRECTOS = "Parámetros incorrectos";
	public static final String NO_EXISTE_OBRA = "Dato de obra inválido";
	public static final String REGISTRO_ELIMINADO = "Registro Eliminado";
	

}
