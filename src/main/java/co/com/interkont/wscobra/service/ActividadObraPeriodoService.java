package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.interfaces.IActividadObraPeriodo;
import co.com.interkont.wscobra.models.ActividadObraPeriodo;
import co.com.interkont.wscobra.repository.ActividadObraPeriodoRepository;

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
	public void eliminarAll(Iterable<ActividadObraPeriodo> actividadObraPeriodo) {
		// TODO Auto-generated method stub
		repository.deleteInBatch(actividadObraPeriodo);		
	}

	@Override
	public List<ActividadObraPeriodo> listarPorPeriodo(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return repository.findByPeriodo_Id(idPeriodo);
	}

	

}
