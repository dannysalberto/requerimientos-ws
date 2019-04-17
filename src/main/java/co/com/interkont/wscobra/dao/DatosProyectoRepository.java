package co.com.interkont.wscobra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaDatosProyecto;

@Repository
public interface DatosProyectoRepository extends JpaRepository<VistaDatosProyecto, Integer>{
	
	public VistaDatosProyecto findByCodigoproyecto(Integer codigoproyecto);

}
