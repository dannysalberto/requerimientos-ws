package co.com.interkont.wsmiobra.auth;


import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.com.interkont.wsmiobra.auth.encrypt.CustomPasswordEncoder;
import co.com.interkont.wsmiobra.config.Constantes;

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

        encoders.put(Constantes.ENCODER, customPassword);

        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(Constantes.ENCODER, encoders);

        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(customPassword);
        return delegatingPasswordEncoder;
    }

}