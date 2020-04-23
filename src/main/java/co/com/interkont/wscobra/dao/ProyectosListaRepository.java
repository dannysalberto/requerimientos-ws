package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaProyectosLista;

@Repository
public interface ProyectosListaRepository extends JpaRepository<VistaProyectosLista, Integer>{
	
	
	public List<VistaProyectosLista> findByUsuario(String usuario);
	
}
