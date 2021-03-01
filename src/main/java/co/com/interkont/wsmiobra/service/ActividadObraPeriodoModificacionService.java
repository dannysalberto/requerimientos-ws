package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.interkont.wsmiobra.interfaces.IActividadObraPeriodoModificacion;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodoModificacion;
import co.com.interkont.wsmiobra.repository.ActividadObraPeriodoModificacionRepository;

@Service
public class ActividadObraPeriodoModificacionService implements IActividadObraPeriodoModificacion{

	@Autowired
	ActividadObraPeriodoModificacionRepository repository;

	@Override
	public void guardar(ActividadObraPeriodoModificacion actividadObraPeriodoModificacion) {
		// TODO Auto-generated method stub
		repository.save(actividadObraPeriodoModificacion);
	}

	@Override
	public void eliminarAll(Iterable<ActividadObraPeriodoModificacion> actividadObraPeriodoModificacion) {
		// TODO Auto-generated method stub
		repository.deleteInBatch(actividadObraPeriodoModificacion);
	}

	@Override
	public List<ActividadObraPeriodoModificacion> listarPorPeriodo(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return repository.findByPeriodoModificacion_Id(idPeriodo);
	}
	
	

	

}
