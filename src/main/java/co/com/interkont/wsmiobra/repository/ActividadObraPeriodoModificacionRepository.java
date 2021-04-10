package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.ActividadObraPeriodoModificacion;

@Repository
public interface ActividadObraPeriodoModificacionRepository extends JpaRepository<ActividadObraPeriodoModificacion, Integer> {

	List<ActividadObraPeriodoModificacion> findByPeriodoModificacion_Id(Integer idPeriodo);
	
	@Query("select u from ActividadObraPeriodoModificacion u where u.periodoModificacion.obraModificacion.id = ?1")
	List<ActividadObraPeriodoModificacion> findByListPeriodosModificacion(Integer idModificacion);
	
	void deleteByPeriodoModificacion_Id(Integer idPeriodo);


}
