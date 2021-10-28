/**
 * 
 */
package co.com.interkont.avanzame.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.avanzame.interfaces.ISolicitudFPO;
import co.com.interkont.avanzame.models.SolicitudFPO;
import co.com.interkont.avanzame.repository.SolicitudFPORepository;

/**
 * @author DannysMuria
 *
 */
@Service
public class SolicitudFPOJPAServices implements ISolicitudFPO{
	
	@Autowired
	SolicitudFPORepository repository;

	@Override
	public SolicitudFPO guardar(SolicitudFPO solicitudfpo) {
		// TODO Auto-generated method stub
		return repository.save(solicitudfpo);
	}

	@Override
	public SolicitudFPO actualizar(SolicitudFPO solicitudfpo) {
		// TODO Auto-generated method stub
		return repository.save(solicitudfpo);
	}

	@Override
	public SolicitudFPO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<SolicitudFPO> desplegarTodos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		
	}

	@Override
	public List<SolicitudFPO> desplegarTodos(Integer idObra) {
		// TODO Auto-generated method stub
		return repository.findByObraid(idObra);
	}
	

}
