package co.com.interkont.avanzame.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.interkont.avanzame.models.Documento;
import co.com.interkont.avanzame.repository.DocumentoRepository;


@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository repo;
	
	public Documento buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		try {
			return repo.findById(id).get();			
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	public List<Documento> listarPorObra(Integer id) {
		// TODO Auto-generated method stub
		return repo.findByobraid(id);
	}

	public List<Documento> listarPorContrato(Integer id) {
		// TODO Auto-generated method stub
		return repo.findBycontratoid(id);
	}

	public Documento guardar(Documento doc) {
		// TODO Auto-generated method stub
		return repo.save(doc);
	}


}
