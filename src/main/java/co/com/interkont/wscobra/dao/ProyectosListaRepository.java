package co.com.interkont.wscobra.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.interkont.wscobra.dto.VistaProyectosLista;

public interface ProyectosListaRepository extends JpaRepository<VistaProyectosLista, Integer>{
	
	
	public List<VistaProyectosLista> findByAll(BigDecimal latitud, BigDecimal longitud);
	
	
	public List<VistaProyectosLista> findByNombreproyecto(BigDecimal latitud, BigDecimal longitud, String nombreproyecto);
	
	
	public List<VistaProyectosLista> findByCodigocategoria(BigDecimal latitud, BigDecimal longitud, Integer codigocategoria);

}
