package co.com.interkont.wscobra.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaContratistasContratoProyecto;

@Repository
public interface ContratistaProyectoRepository extends JpaRepository<VistaContratistasContratoProyecto, Integer>{
	
	public List<VistaContratistasContratoProyecto> findByCodigoobra(Integer codigoobra);

}
