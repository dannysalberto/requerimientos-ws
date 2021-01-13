package co.com.interkont.wscobra.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.models.Periodo;


@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

	List<Periodo> findByObra_Id(Integer id);
	void deleteByObra(Obra obra);
	List<Periodo> findByObra_IdAndFechafinGreaterThanEqualAndFechainicioLessThanEqualOrderByFechainicioAsc(Integer idObra, Date FechaIni,Date FechaFin);
	List<Periodo> findByObra_IdAndFechainicioGreaterThanEqualOrderByFechainicioAsc(Integer idObra, Date FechaIni);
	
	
}
