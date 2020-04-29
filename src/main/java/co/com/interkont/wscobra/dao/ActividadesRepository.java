package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaActividades;
import co.com.interkont.wscobra.dto.VistaProyectosLista;

@Repository
public interface ActividadesRepository extends JpaRepository<VistaProyectosLista, Integer>{
	
	
	public List<VistaActividades> findByCodigoProyecto(Integer codigoProyecto);
	
}
