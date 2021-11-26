package co.com.interkont.avanzame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.interkont.avanzame.interfaces.IDocumento;
import co.com.interkont.avanzame.models.Documento;
import co.com.interkont.avanzame.repository.DocumentoRepository;

@Service
public class DocumentoService implements IDocumento{

	@Autowired
	private DocumentoRepository repo;
	
	@Override
	public Documento buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		try {
			return repo.findByoidsolicitudfpo(id);			
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}


}
