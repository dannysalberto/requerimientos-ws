package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;


@Repository
public interface ActividadObraPeriodoRepository extends JpaRepository<ActividadObraPeriodo, Long> {

	List<ActividadObraPeriodo> findByPeriodo_Id(Integer id);
	ActividadObraPeriodo findByPeriodo_IdAndActividadObra_oidactiviobra(Integer idPeriodo,Integer idActividad);

}
