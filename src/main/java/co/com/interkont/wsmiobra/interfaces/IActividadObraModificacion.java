package co.com.interkont.wsmiobra.interfaces;

import java.util.List;
import co.com.interkont.wsmiobra.models.ActividadObraModificacion;

public interface IActividadObraModificacion {

	ActividadObraModificacion guardar(ActividadObraModificacion actividadObraModificacion);
	ActividadObraModificacion actualizar(ActividadObraModificacion actividadObraModificacion);
	ActividadObraModificacion buscarPorId(Integer id);
	List<ActividadObraModificacion> desplegarTodos();
	void eliminar(Integer id);
	List<ActividadObraModificacion> desplegarTodos(Integer id, String tipo);

	
}
