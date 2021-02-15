package co.com.interkont.wsmiobra.models;

public class AuthenticationResponse {
	
	private final String JavaWebToken;

	public AuthenticationResponse(String javaWebToken) {
		super();
		JavaWebToken = javaWebToken;
	}

	public String getJavaWebToken() {
		return JavaWebToken;
	}
	
	

}
