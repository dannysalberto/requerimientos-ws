
package co.com.interkont.wscobra.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.interkont.wscobra.dto.VistaProyectosMapa;

public interface ProyectosMapaRepository extends JpaRepository<VistaProyectosMapa, Integer>{
	
	
	public List<VistaProyectosMapa> findByAll(BigDecimal latitud, BigDecimal longitud);
	
	
	public List<VistaProyectosMapa> findByNombreproyecto(BigDecimal latitud, BigDecimal longitud, String nombreproyecto);
	
	
	public List<VistaProyectosMapa> findByCodigocategoria(BigDecimal latitud, BigDecimal longitud, Integer codigocategoria);

}