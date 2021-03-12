package co.com.interkont.wsmiobra.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.PeriodoModificacion;

@Repository
public interface PeriodoModificacionRepository extends JpaRepository<PeriodoModificacion, Integer> {
	
	List<PeriodoModificacion> findByObraModificacion_Id(Integer id);
	void deleteByObraModificacion(ObraModificacion obra);
	List<PeriodoModificacion> findByObraModificacion_IdAndFechafinGreaterThanEqualAndFechainicioLessThanEqualOrderByFechainicioAsc(Integer idObra, Date FechaIni,Date FechaFin);
	List<PeriodoModificacion> findByObraModificacion_IdAndFechainicioGreaterThanEqualOrderByFechainicioAsc(Integer idObra, Date FechaIni);
	
}
