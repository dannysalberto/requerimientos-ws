package co.com.interkont.avanzame.bussines.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.azure.core.http.rest.Page;

import co.com.interkont.avanzame.api.request.RequerimientoRequest;
import co.com.interkont.avanzame.models.Requerimiento;


public interface IRequerimientos{

	List<Requerimiento> obtenerRequerimientos() ;
	Requerimiento crear(Requerimiento requerimiento);
	Requerimiento actualizar(Requerimiento requerimiento);
	Requerimiento buscarPorId (Integer id);
	void borrar(Integer id);
	
	
}
