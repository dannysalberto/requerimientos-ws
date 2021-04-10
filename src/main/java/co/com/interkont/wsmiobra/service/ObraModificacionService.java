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
		//return null;
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
		try {
			return repo.findById(id).get();		
		}catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	@Override
	public Double totalPrecioActividades(Integer id) {
		// TODO Auto-generated method stub
		Double ret = repo.totalActividades(id);
		if (ret == null) {
			return (double) 0;
		}
		return ret;
	}

	@Override
	public Double totalCostoDirecto(Integer id) {
		// TODO Auto-generated method stub
		Double ret = repo.totalCostoDirecto(id);
		if (ret == null) {
			return (double) 0;
		}
		return ret;
	}

	@Override
	public Integer cantidadActividades(Integer idObra) {
		// TODO Auto-generated method stub
		Integer ret = repo.cantidadActividades(idObra);
		if (ret == null) {
			return  0;
		}
		return ret;
		
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
