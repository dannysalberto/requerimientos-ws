package co.com.interkont.wscobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.models.ActividadObraPeriodo;


@Repository
public interface ActividadObraPeriodoRepository extends JpaRepository<ActividadObraPeriodo, Integer> {

	List<ActividadObraPeriodo> findByPeriodo_Id(Integer id);

}
