package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.interfaces.IActividadObraWS;
import co.com.interkont.wsmiobra.models.ActividadobraWS;
import co.com.interkont.wsmiobra.repository.ActividadObraRepositoryWS;


@Service
public class ActividadObraWSService implements IActividadObraWS {

	
	@Autowired
	ActividadObraRepositoryWS repository;

	@Override
	public ActividadobraWS buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		try {
			return repository.findById(id).get();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}

	@Override
	public void Guardar(ActividadobraWS actividad) {
		// TODO Auto-generated method stub
		repository.save(actividad);
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		try {
			repository.deleteById(id);			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void actualizar(ActividadobraWS actividad) {
		// TODO Auto-generated method stub
		repository.save(actividad);
		
	}

	@Override
	public List<ActividadobraWS> desplegarTodos(Pageable page) {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<ActividadobraWS> desplegarTodos(Obra obra) {
		// TODO Auto-generated method stub
		return repository.findByObra(obra);
	}
	
	
	
}
