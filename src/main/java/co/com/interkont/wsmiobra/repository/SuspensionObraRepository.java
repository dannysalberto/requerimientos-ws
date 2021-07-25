package co.com.interkont.wsmiobra.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.SuspensionObra;

@Repository
public interface SuspensionObraRepository extends JpaRepository<SuspensionObra, Integer> {

	SuspensionObra findByObra_IdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqualOrderByFechaInicioAsc(Integer idObra, Date FechaIni,Date FechaFin);
	SuspensionObra findByObra_IdAndFechaInicioGreaterThanAndFechaInicioLessThan(Integer idObra, Date FechaIni,Date FechaFin);
	SuspensionObra findByObra_IdAndFechaFinGreaterThanAndFechaFinLessThan(Integer idObra, Date FechaIni,Date FechaFin);
	SuspensionObra findByObra_IdAndFechaInicio(Integer idObra, Date fechaFin);
	SuspensionObra findByObra_IdAndFechaFin(Integer idObra, Date fechaInicio);
	List<SuspensionObra> findByObra_IdOrderByFechaInicioAsc(Integer idObra);

}
