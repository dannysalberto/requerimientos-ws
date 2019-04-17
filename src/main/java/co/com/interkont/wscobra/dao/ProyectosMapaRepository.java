
package co.com.interkont.wscobra.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaProyectosMapa;

@Repository
public interface ProyectosMapaRepository extends JpaRepository<VistaProyectosMapa, Integer>{
	
	
	public List<VistaProyectosMapa> findByAll(BigDecimal latitudusuario, BigDecimal longitudusuario);
	
	
	public List<VistaProyectosMapa> findByNombreproyecto(BigDecimal latitudusuario, BigDecimal longitudusuario, String nombreproyecto);
	
	
	public List<VistaProyectosMapa> findByCodigocategoria(BigDecimal latitudusuario, BigDecimal longitudusuario, Integer codigocategoria);

}