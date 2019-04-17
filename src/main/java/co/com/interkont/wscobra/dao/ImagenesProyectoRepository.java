package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.ImagenEvolucionObra;

@Repository
public interface ImagenesProyectoRepository extends JpaRepository<ImagenEvolucionObra, Integer>{
	
	public List<ImagenEvolucionObra> findByCodigoProyecto(Integer intcodigoobra);

}