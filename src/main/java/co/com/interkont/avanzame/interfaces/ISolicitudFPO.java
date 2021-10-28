package co.com.interkont.avanzame.interfaces;

import java.util.List;

import co.com.interkont.avanzame.models.SolicitudFPO;

public interface ISolicitudFPO {

	SolicitudFPO guardar(SolicitudFPO solicitudfpo);
	SolicitudFPO actualizar(SolicitudFPO solicitudfpo);
	SolicitudFPO buscarPorId(Integer id);
	List<SolicitudFPO> desplegarTodos();
	void eliminar(Integer id);
	List<SolicitudFPO> desplegarTodos(Integer idObra);

	
}
