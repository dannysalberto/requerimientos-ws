package co.com.interkont.wsmiobra.auth;


import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.com.interkont.wsmiobra.auth.encrypt.CustomPasswordEncoder;

import static co.com.interkont.wsmiobra.auth.config.ConfiguracionConstantes.ENCODER;

import java.util.HashMap;
import java.util.Map;

/**
 * La DelegatingPasswordEncoderclase hace posible soportar múltiples 
 * password encodersbasados ​​en un prefijo. 
 * 
 * Spring Security 5 trae la PasswordEncoderFactoriesclase práctica, 
 * actualmente esta clase admite la mayoría de codificadores:
 * @return
 */
class DefaultPasswordEncoderFactories {

    @SuppressWarnings("deprecation")
    static PasswordEncoder createDelegatingPasswordEncoder() {

        CustomPasswordEncoder customPassword = new CustomPasswordEncoder();
        
        
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put(ENCODER, customPassword);

        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(ENCODER, encoders);

        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(customPassword);
        return delegatingPasswordEncoder;
    }

}