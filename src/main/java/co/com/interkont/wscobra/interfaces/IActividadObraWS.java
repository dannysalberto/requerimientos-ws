package co.com.interkont.wscobra.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import co.com.interkont.wscobra.models.ActividadobraWS;

public interface IActividadObraWS {
	
	ActividadobraWS buscarPorId(Integer id);
	void Guardar(ActividadobraWS actividad);
	void eliminar(Integer id);
	void actualizar(ActividadobraWS actividad);
	List<ActividadobraWS> desplegarTodos(Pageable page);
	

}
