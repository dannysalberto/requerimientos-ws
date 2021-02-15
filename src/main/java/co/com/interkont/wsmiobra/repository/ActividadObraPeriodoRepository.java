package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;


@Repository
public interface ActividadObraPeriodoRepository extends JpaRepository<ActividadObraPeriodo, Integer> {

	List<ActividadObraPeriodo> findByPeriodo_Id(Integer id);

}
