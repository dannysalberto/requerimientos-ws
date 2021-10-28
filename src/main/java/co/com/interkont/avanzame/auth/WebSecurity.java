package co.com.interkont.avanzame.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import co.com.interkont.avanzame.auth.pojo.UsuarioDetailsServiceImpl;
import co.com.interkont.avanzame.config.Constantes;
import co.com.interkont.avanzame.filters.JWTFilters;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter{

	/**
	 * se inyecta el servicio
	 */
	@Autowired	
	private UsuarioDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JWTFilters jWTFilters;
	
	//@Autowired
	//private JwtAuthenticationEntryPoint unauthorizedHandler;

		
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	/**
	 * se sobre escribe este metodo de la clase WebSecurityConfigurerAdapter
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {		
		return DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
	} 
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST,Constantes.LOGIN_URL).permitAll()
				.antMatchers("/**","/test","/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
				//.antMatchers("/test","/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.cors();
		httpSecurity.addFilterBefore(jWTFilters, UsernamePasswordAuthenticationFilter.class)
			.headers();
		
	}			

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("RefreshToken");
        source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	
	
}
