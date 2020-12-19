
package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.ObrasRepository;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.interfaces.IObraWS;

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
	public void actualizar(Obra obra) {
		// TODO Auto-generated method stub
		obrasRepository.save(obra);
		System.out.println("Actualizado");		
	}
	
}
