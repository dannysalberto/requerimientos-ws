package co.com.interkont.wscobra.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaProyectosLista;

@Repository
public interface ProyectosListaRepository extends JpaRepository<VistaProyectosLista, Integer>{
	
	
	public List<VistaProyectosLista> findByAll(BigDecimal latitudusuario, BigDecimal longitudusuario);
	
	
	public List<VistaProyectosLista> findByNombreproyecto(BigDecimal latitudusuario, BigDecimal longitudusuario, String nombreproyecto);
	
	
	public List<VistaProyectosLista> findByCodigocategoria(BigDecimal latitudusuario, BigDecimal longitudusuario, Integer codigocategoria);

}
