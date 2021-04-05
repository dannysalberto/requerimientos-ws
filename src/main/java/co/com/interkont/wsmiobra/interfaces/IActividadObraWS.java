package co.com.interkont.wsmiobra.interfaces;

import java.util.List;
import org.springframework.data.domain.Pageable;

import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ActividadobraWS;

public interface IActividadObraWS {
	
	void Guardar(ActividadobraWS actividad);
	void eliminar(Integer id);
	void actualizar(ActividadobraWS actividad);
	ActividadobraWS buscarPorId(Integer id);
	List<ActividadobraWS> desplegarTodos(Pageable page);
	List<ActividadobraWS> desplegarTodos(Obra obra);

	/*
	List<ActividadobraWS> desplegarActividadObra_Obra(Integer idObra);
	List<ActividadObra> desplegarActividadObra_Obra_Actividad(Integer idObra, Integer idActividad);
	List<ActividadObra> desplegarActividadObra_Obra_Actividad_Elemento(Integer idObra, Integer idActividad,Integer idElemento);
	*/

}
