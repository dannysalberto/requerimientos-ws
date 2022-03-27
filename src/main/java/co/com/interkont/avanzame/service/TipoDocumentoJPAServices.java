package co.com.interkont.avanzame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.avanzame.bussines.interfaces.ITipoDocumento;
import co.com.interkont.avanzame.models.TipoDocumento;
import co.com.interkont.avanzame.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoJPAServices implements ITipoDocumento{
	
	@Autowired
	TipoDocumentoRepository repo;

	@Override
	public TipoDocumento buscarPorId(Integer idTipoDoc) {
		// TODO Auto-generated method stub
		return repo.findById(idTipoDoc).get();
	}

	@Override
	public List<TipoDocumento> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	
}
