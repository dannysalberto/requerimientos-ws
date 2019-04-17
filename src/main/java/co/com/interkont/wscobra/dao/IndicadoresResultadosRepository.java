package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Base;

@Repository
public interface IndicadoresResultadosRepository extends JpaRepository<Base, Integer>{
	
	
	public List<Base> findByIntcodigoobra(Integer intcodigoobra);

}
