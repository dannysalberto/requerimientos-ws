package co.com.interkont.wsmiobra.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.com.interkont.wsmiobra.interfaces.IPeriodoModificacion;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.PeriodoModificacion;
import co.com.interkont.wsmiobra.repository.PeriodoModificacionRepository;

@Service	
public class PeriodoModificacionService implements IPeriodoModificacion {

	@Autowired
	PeriodoModificacionRepository repository;

	@Override
	public List<PeriodoModificacion> desplegarTodos() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by("id"));
	}

	@Override
	public List<PeriodoModificacion> ListarPorObra(Integer idObra) {
		// TODO Auto-generated method stub
		return repository.findByObraModificacion_Id(idObra);
	}

	@Override
	public void guardar(PeriodoModificacion periodo) {
		// TODO Auto-generated method stub
		repository.save(periodo);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void actualizar(PeriodoModificacion periodo) {
		// TODO Auto-generated method stub
		repository.save(periodo);
	}

	@Override
	public PeriodoModificacion buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public boolean eliminarAll(Iterable<PeriodoModificacion> periodo) {
		// TODO Auto-generated method stub
		try {
			repository.deleteAll(periodo);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
	
	}

	@Override
	public List<PeriodoModificacion> ListarPorObraFecha(Integer idObra, Date FechaIni, Date FechaFin) {
		// TODO Auto-generated method stub
		//return repository.findByObraModificacion_Id(idObra);
		return repository.findByObraModificacion_IdAndFechafinGreaterThanEqualAndFechainicioLessThanEqualOrderByFechainicioAsc(idObra, FechaIni, FechaFin);
	}

	@Override
	public boolean eliminarByObra(ObraModificacion obra) {
		// TODO Auto-generated method stub
		try {
			repository.deleteByObraModificacion(obra);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	@Override
	public List<PeriodoModificacion> ListarPeriodosPorObraFechaInicio(Integer idObra, Date fechaIni) {
		// TODO Auto-generated method stub
		return repository.findByObraModificacion_IdAndFechainicioGreaterThanEqualOrderByFechainicioAsc(idObra, fechaIni);
	}
	

	
	
}
