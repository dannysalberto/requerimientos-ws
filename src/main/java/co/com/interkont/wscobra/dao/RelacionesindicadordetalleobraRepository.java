package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.dto.Relacionindicadordetalleobra;

@Repository
public interface RelacionesindicadordetalleobraRepository extends JpaRepository<Relacionindicadordetalleobra, Integer>{
	
	public List<Relacionindicadordetalleobra> findByObra(Obra obra);
	
}
