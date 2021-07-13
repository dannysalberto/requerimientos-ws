package co.com.interkont.wsmiobra.service;

import java.math.BigDecimal;
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
		return repository.findByObra_IdOrderByFechainicioAsc(idObra);
	}

	@Override
	public void guardar(Periodo periodo) {
		repository.save(periodo);
	}

	@Override
	public boolean eliminar(Periodo per) {
		// TODO Auto-generated method stub
		try {
			per.setValtotplanif(new BigDecimal(0));
			repository.save(per);
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
			return false;
		}
	}
	
	@Override
	public void eliminar(int per) {
		// TODO Auto-generated method stub
		try {
			repository.deleteById(per);
			System.out.println("periodo borrado "+per);
		}catch (Exception e) {
			Periodo periodo = buscarPorId(per);
			periodo.setValtotplanif(new BigDecimal(0));
			repository.save(periodo);
		}
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
	public void eliminarAll(List<Periodo> lstperiodos) {
		repository.deleteInBatch(lstperiodos);
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

	@Override
	public Periodo buscarPorObraFecha(Integer idObra, Date fechaIni) {
		// TODO Auto-generated method stub
		try {
			return repository.findByObra_IdAndFechainicio(idObra, fechaIni);			
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}


}
