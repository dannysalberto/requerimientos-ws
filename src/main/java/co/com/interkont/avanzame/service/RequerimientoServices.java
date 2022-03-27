package co.com.interkont.avanzame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.core.http.rest.Page;

import co.com.interkont.avanzame.api.request.RequerimientoRequest;
import co.com.interkont.avanzame.bussines.interfaces.IRequerimientos;
import co.com.interkont.avanzame.models.Requerimiento;
import co.com.interkont.avanzame.repository.RequerimientoRepository;

@Service
public class RequerimientoServices implements IRequerimientos{
	
	@Autowired
	RequerimientoRepository repo;

	@Override
	public List<Requerimiento> obtenerRequerimientos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Requerimiento crear(Requerimiento requerimiento) {
		// TODO Auto-generated method stub
		return repo.save(requerimiento);
	}

	@Override
	public Requerimiento actualizar(Requerimiento requerimiento) {
		// TODO Auto-generated method stub
		return repo.save(requerimiento);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Requerimiento buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	

}
