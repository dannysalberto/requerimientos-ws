package co.com.interkont.avanzame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.avanzame.models.SolicitudFPO;


@Repository
public interface SolicitudFPORepository extends JpaRepository<SolicitudFPO, Integer> {
	
	List<SolicitudFPO> findByObraid(int idObra);

}
