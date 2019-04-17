package co.com.interkont.wscobra.auth.encrypt;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		Encrypter encrypter;
		String password = null;
		try {
			encrypter = Encrypter.getInstance();
			password = encrypter.encrypt(rawPassword.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String password = encode(rawPassword);
		return password.equals(encodedPassword);
	}
	
}