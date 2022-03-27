package co.com.interkont.avanzame.businnes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.interkont.avanzame.api.request.RequerimientoRequest;
import co.com.interkont.avanzame.api.response.ResponseGeneric;
import co.com.interkont.avanzame.models.Requerimiento;
import co.com.interkont.avanzame.service.RequerimientoServices;

@Component
public class RequerimientosBussines {

	@Autowired
	RequerimientoServices repo;
	
	public List<Requerimiento> obtenerRequerimientos() {
		// TODO Auto-generated method stub
		return repo.obtenerRequerimientos();
	}
	
	public Requerimiento crear(RequerimientoRequest requerimiento) {

		Requerimiento obj = new Requerimiento();
		obj.setNombreRequerimiento(requerimiento.getNombreRequerimiento());
		obj.setTipoCaptura(requerimiento.getTipoCaptura());

		return repo.crear(obj);
	}
	
	public ResponseGeneric borrar(Integer id) {
		
		ResponseGeneric resp = new ResponseGeneric();
		try {
			repo.borrar(id);		
			resp.setStatus(true);
			resp.setMensaje("Elemento Borrado");
		}catch (Exception e) {
			// TODO: handle exception
			resp.setStatus(false);
			resp.setMensaje(e.getMessage());
			
		}
		return resp;
		
		
	}

}
