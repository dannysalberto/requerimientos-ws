package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.dao.CategoriaRepository;
import co.com.interkont.wsmiobra.interfaces.ICategoria;
import co.com.interkont.wsmiobra.models.Categoria;

@Service
public class CategoriaService implements ICategoria{

	@Autowired
	CategoriaRepository repo;
	
	@Override
	public List<Categoria> obtenerCategorias(){
		
		return repo.findAll();
		
	}

	@Override
	public Categoria buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		try {
			return repo.findById(id).get();
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
}
