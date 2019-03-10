package co.com.interkont.wscobra.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import co.com.interkont.wscobra.dto.JsfUsuario;

public interface JsfUsuarioRepository extends JpaRepository<JsfUsuario, Integer>{
	
	public abstract JsfUsuario findByUsuLogin(String usuLogin);

}
