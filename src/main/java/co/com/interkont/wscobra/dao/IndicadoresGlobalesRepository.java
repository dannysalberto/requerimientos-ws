package co.com.interkont.wscobra.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.interkont.wscobra.dto.VistaIndicadoresGlobales;

public interface IndicadoresGlobalesRepository extends JpaRepository<VistaIndicadoresGlobales, Integer>{
	
	public List<VistaIndicadoresGlobales> findByCodigocategoria(Integer codigocategoria);

}