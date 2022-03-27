package co.com.interkont.avanzame.bussines.interfaces;

import java.util.List;
import co.com.interkont.avanzame.models.RequerimientoObra;


public interface IRequerimientosObra{

	List<RequerimientoObra> obtenerRequerimientosObra(Integer obraid);
	RequerimientoObra crear(RequerimientoObra requerimientoObra);
	RequerimientoObra buscarPorId(Integer id);
	RequerimientoObra actualizar(RequerimientoObra requerimiento);
	void borrar(Integer id);
	
	
}
