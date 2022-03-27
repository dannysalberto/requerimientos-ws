package co.com.interkont.avanzame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.interkont.avanzame.bussines.interfaces.IDocumentosRequerimiento;
import co.com.interkont.avanzame.models.DocumentosRequerimiento;
import co.com.interkont.avanzame.repository.DocumentosRequerimientoRepository;

@Service
public class DocumentosRequerimientoServices implements IDocumentosRequerimiento{
	
	@Autowired
	DocumentosRequerimientoRepository repo;

	@Override
	public DocumentosRequerimiento buscarPorId(Integer idDoc) {
		// TODO Auto-generated method stub
		return repo.findById(idDoc).get();
	}

	@Override
	public List<DocumentosRequerimiento> listar(Integer obraid) {
		// TODO Auto-generated method stub
		return repo.findByobraid(obraid);
	}

	@Override
	public DocumentosRequerimiento crear(DocumentosRequerimiento docReq) {
		// TODO Auto-generated method stub
		return repo.save(docReq);
	}

	@Override
	public void borrar(Integer iddocReq) {
		// TODO Auto-generated method stub
		repo.deleteById(iddocReq);
	}


}
