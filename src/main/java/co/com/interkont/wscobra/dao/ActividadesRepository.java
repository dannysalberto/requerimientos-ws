package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaActividades;

@Repository
public interface ActividadesRepository extends JpaRepository<VistaActividades, Integer>{
	
	
	public List<VistaActividades> findByCodigoProyecto(Integer codigoProyecto);
	
	public VistaActividades findByActividadId(Long id);
	
}
