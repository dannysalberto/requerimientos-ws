package co.com.interkont.wsmiobra.interfaces;

import java.util.List;

import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ObraModificacion;

public interface IActividadObraModificacion {

	ActividadObraModificacion guardar(ActividadObraModificacion actividadObraModificacion);
	ActividadObraModificacion actualizar(ActividadObraModificacion actividadObraModificacion);
	ActividadObraModificacion buscarPorId(Integer id);
	List<ActividadObraModificacion> desplegarTodos();
	List<ActividadObraModificacion> desplegarTodos(ObraModificacion obra);
	void eliminar(Integer id);

	
}
