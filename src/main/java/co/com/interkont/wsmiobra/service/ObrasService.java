
package co.com.interkont.wsmiobra.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.interfaces.IObraWS;
import co.com.interkont.wsmiobra.repository.ObrasRepository;

@Service
public class ObrasService implements IObraWS {
	
	@Autowired
	ObrasRepository obrasRepository;
	

	@Override
	public Obra buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		try {
			return obrasRepository.findById(id).get();
		}catch (Exception e){
			System.out.println("No existe el Id de Obra");
		}
		return null;
	}

	@Override
	public Obra actualizar(Obra obra) {
		// TODO Auto-generated method stub
		return obrasRepository.save(obra);
	}
	
	@Override
	public Double totalPrecioActividades(Integer idObra) {
		// TODO Auto-generated method stub
		//return repositorio.totalActividades(idObra);
		double total = 0;
		try {
			
			total = obrasRepository.totalActividades(idObra);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return total;
	}

	@Override
	public Integer cantidadActividades(Integer idObra) {
		// TODO Auto-generated method stub
		try {
			return obrasRepository.cantidadActividades(idObra);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		obrasRepository.deleteById(id);
	}

	@Override
	public Double totalCostoDirecto(Integer id) {
		// TODO Auto-generated method stub
		double total = 0;
		try {
			
			total = obrasRepository.totalCostoDirecto(id);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return total;
	}

	@Override
	public boolean tieneContratoObra(Integer idObra) {
		// TODO Auto-generated method stub
		int count = obrasRepository.tieneContratoObra(idObra);
		System.out.println(count);
		if (count==0) {
			return false;			
		}else {
			return true;
		}
	}

	@Override
	public Date fechaMaxAlimentacion(Integer idObra) {
		// TODO Auto-generated method stub
		try {
			return obrasRepository.fechaMaxAlimentacion(idObra);			
		}catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	
}
