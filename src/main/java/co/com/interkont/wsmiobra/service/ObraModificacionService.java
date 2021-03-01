package co.com.interkont.wsmiobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.IObraModificacion;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.repository.ObraModificacionRepository;

@Service
public class ObraModificacionService implements IObraModificacion{

	@Autowired
	ObraModificacionRepository repo;
	
	@Override
	public ObraModificacion guardar(ObraModificacion obraModificacion) {
		// TODO Auto-generated method stub
		return repo.save(obraModificacion);
	}

	@Override
	public ObraModificacion actualizar(ObraModificacion obraModificacion) {
		// TODO Auto-generated method stub
		return repo.save(obraModificacion);
	}

	@Override
	public void Eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public ObraModificacion buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Double totalPrecioActividades(Integer id) {
		// TODO Auto-generated method stub
		return repo.totalActividades(id);
	}

	@Override
	public Double totalCostoDirecto(Integer id) {
		// TODO Auto-generated method stub
		return repo.totalCostoDirecto(id);
	}

	@Override
	public Integer cantidadActividades(Integer idObra) {
		// TODO Auto-generated method stub
		return repo.cantidadActividades(idObra);
	}

	@Override
	public ObraModificacion buscarPorIdEstado(Integer idObra, String estado) {
		// TODO Auto-generated method stub
		ObraModificacion obraModificacion = new ObraModificacion(); 
		try {
			obraModificacion = repo.findByObraidAndEstadoModificacion(idObra,estado);
			return obraModificacion;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

}
