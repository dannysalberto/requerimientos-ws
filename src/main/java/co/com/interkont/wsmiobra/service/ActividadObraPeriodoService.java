package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.IActividadObraPeriodo;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;
import co.com.interkont.wsmiobra.repository.ActividadObraPeriodoRepository;

@Service
public class ActividadObraPeriodoService implements IActividadObraPeriodo{

	@Autowired
	ActividadObraPeriodoRepository repository;
	
	@Override
	public void guardar(ActividadObraPeriodo actividadObraPeriodo) {
		// TODO Auto-generated method stub
		repository.save(actividadObraPeriodo);			
		
	}

	@Override
	public void eliminarAll(List<ActividadObraPeriodo> actividadObraPeriodo) {
		// TODO Auto-generated method stub
		actividadObraPeriodo.forEach((act)->{
			repository.deleteById(act.getId());
		});
	}

	@Override
	public List<ActividadObraPeriodo> listarPorPeriodo(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return repository.findByPeriodo_Id(idPeriodo);
	}

	@Override
	public ActividadObraPeriodo buscarPorId(Long id) {
		// TODO Auto-generated method stub
		try {
			return repository.findById(id).get();
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public ActividadObraPeriodo buscarPorPeriodoActividad(Integer idPeriodo, Integer idActividad) {
		// TODO Auto-generated method stub
		return repository.findByPeriodo_IdAndActividadObra_oidactiviobra(idPeriodo, idActividad);
	}

	

}
