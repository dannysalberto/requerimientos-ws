package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.CategoriaRepository;
import co.com.interkont.wscobra.models.Categoria;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repo;
	
	public List<Categoria> obtenerCategorias(){
		
		return repo.findAll();
		
	}
	
}
