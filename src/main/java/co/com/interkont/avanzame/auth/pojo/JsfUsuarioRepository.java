package co.com.interkont.avanzame.auth.pojo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * Repositorio para la entidad JsfUsuario
 *
 */

@Repository
public interface JsfUsuarioRepository extends JpaRepository<JsfUsuario, Integer>{
	
	/*
	 * Metodo abstracto para la implementación de login con UserDetailsService de spring.
	 */
	public abstract JsfUsuario findByUsuLogin(String usuLogin);

}
