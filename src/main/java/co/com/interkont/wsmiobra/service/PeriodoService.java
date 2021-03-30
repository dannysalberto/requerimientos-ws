package co.com.interkont.wsmiobra.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.interfaces.IPeriodo;
import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.repository.PeriodoRepository;


@Service	
public class PeriodoService implements IPeriodo {

	@Autowired
	PeriodoRepository repository;
	
	@Override
	public List<Periodo> desplegarTodos() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by("id"));
	}

	@Override
	public List<Periodo> ListarPorObra(Integer idObra) {
		// TODO Auto-generated method stub
		return repository.findByObra_Id(idObra);
	}

	@Override
	public void guardar(Periodo periodo) {
		// TODO Auto-generated method stub
		repository.save(periodo);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void actualizar(Periodo periodo) {
		// TODO Auto-generated method stub
		repository.save(periodo);
	}

	@Override
	public Periodo buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		try {
			return repository.findById(id).get();			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean eliminarAll(Iterable<Periodo> periodo) {
		// TODO Auto-generated method stub
		try {
			repository.deleteAll(periodo);
			//repository.deleteInBatch(periodo);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
	
	}

	@Override
	public List<Periodo> ListarPorObraFecha(Integer idObra, Date FechaIni, Date FechaFin) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdAndFechafinGreaterThanEqualAndFechainicioLessThanEqualOrderByFechainicioAsc(idObra, FechaIni, FechaFin);
	}

	@Override
	public boolean eliminarByObra(Obra obra) {
		// TODO Auto-generated method stub
		try {
			repository.deleteByObra(obra);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	@Override
	public List<Periodo> ListarPeriodosPorObraFechaInicio(Integer idObra, Date fechaIni) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdAndFechainicioGreaterThanEqualOrderByFechainicioAsc(idObra, fechaIni);
	}


}
