package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Indicador;

@Repository
public interface IndicadoresRepository extends JpaRepository<Indicador, Integer>{
	
	
	public List<Indicador> findAllIndicadores();

}
