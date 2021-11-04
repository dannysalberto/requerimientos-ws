package co.com.interkont.avanzame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.avanzame.interfaces.IObra;
import co.com.interkont.avanzame.models.Obra;
import co.com.interkont.avanzame.repository.ObraRepository;

@Service
public class ObraJPAServices implements IObra {
	
	@Autowired
	ObraRepository repo;

	@Override
	public Obra buscarPorId(Integer idObra) {
		// TODO Auto-generated method stub
		return repo.findById(idObra).get();
	}

	@Override
	public Obra actualizar(Obra obra) {
		// TODO Auto-generated method stub
		return repo.save(obra);
	}

}
