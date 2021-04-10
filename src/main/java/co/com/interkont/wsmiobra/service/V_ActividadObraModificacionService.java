package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.IVActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.V_ActividadObraModificacion;
import co.com.interkont.wsmiobra.repository.V_ActividadObraModificacionRepository;

@Service
public class V_ActividadObraModificacionService implements IVActividadObraModificacion{

	@Autowired
	V_ActividadObraModificacionRepository repo;

	@Override
	public List<V_ActividadObraModificacion> desplegarTodos(ObraModificacion obra) {
		// TODO Auto-generated method stub
		return repo.findByObraModificacion(obra);
	}
	
	

	
	

}
