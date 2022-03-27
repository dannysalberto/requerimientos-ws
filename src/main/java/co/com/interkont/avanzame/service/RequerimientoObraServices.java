package co.com.interkont.avanzame.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.interkont.avanzame.bussines.interfaces.IRequerimientosObra;
import co.com.interkont.avanzame.models.RequerimientoObra;
import co.com.interkont.avanzame.repository.RequerimientoObraRepository;

@Service
public class RequerimientoObraServices implements IRequerimientosObra{
	
	@Autowired
	RequerimientoObraRepository repo;

	@Override
	public List<RequerimientoObra> obtenerRequerimientosObra(Integer obraid) {
		// TODO Auto-generated method stub
		return repo.findByobraid(obraid);
	}

	@Override
	public RequerimientoObra crear(RequerimientoObra requerimientoObra) {
		// TODO Auto-generated method stub
		return repo.save(requerimientoObra);
	}

	@Override
	public RequerimientoObra actualizar(RequerimientoObra requerimiento) {
		// TODO Auto-generated method stub
		return repo.save(requerimiento);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public RequerimientoObra buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	

}
