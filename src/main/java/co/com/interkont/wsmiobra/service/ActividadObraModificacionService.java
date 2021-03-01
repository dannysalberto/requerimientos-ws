package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.interfaces.IActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.repository.ActividadObraModificacionRepository;

@Service
public class ActividadObraModificacionService implements IActividadObraModificacion{

	@Autowired
	ActividadObraModificacionRepository repo;

	@Override
	public ActividadObraModificacion guardar(ActividadObraModificacion actividadObraModificacion) {
		// TODO Auto-generated method stub
		return repo.save(actividadObraModificacion);
	}

	@Override
	public ActividadObraModificacion actualizar(ActividadObraModificacion actividadObraModificacion) {
		// TODO Auto-generated method stub
		return repo.save(actividadObraModificacion);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		ActividadObraModificacion act = repo.findById(id).get();
		act.setTipomodificacion(Constantes.ACTIVIDAD_ELIMINADA);
		repo.save(act);
	}

	@Override
	public ActividadObraModificacion buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public List<ActividadObraModificacion> desplegarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<ActividadObraModificacion> desplegarTodos(ObraModificacion obra) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	

}
