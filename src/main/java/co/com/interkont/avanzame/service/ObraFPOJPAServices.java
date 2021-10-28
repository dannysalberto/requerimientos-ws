package co.com.interkont.avanzame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.avanzame.interfaces.IObraFPO;
import co.com.interkont.avanzame.models.ObraFPO;
import co.com.interkont.avanzame.repository.ObraFPORepository;

@Service
public class ObraFPOJPAServices implements IObraFPO {
	
	@Autowired
	ObraFPORepository repo;

	@Override
	public ObraFPO buscarPorId(Integer idObra) {
		// TODO Auto-generated method stub
		return repo.findById(idObra).get();
	}

	@Override
	public ObraFPO actualizar(ObraFPO obraFpo) {
		// TODO Auto-generated method stub
		return repo.save(obraFpo);
	}

}
