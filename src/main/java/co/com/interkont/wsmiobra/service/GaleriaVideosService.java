package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.dao.GaleriaVideosRepository;
import co.com.interkont.wsmiobra.dto.GaleriaVideos;

@Service
public class GaleriaVideosService {
	
	@Autowired
	GaleriaVideosRepository repo;
	
	public List<GaleriaVideos> findByAllVideos(Integer idObra){
		return repo.findByObra_id(idObra);		
	}
	
	public void Guardar(GaleriaVideos obj) {
			repo.save(obj);
	}
	
	public Boolean Borrar(Integer idVideo) {
		try {
			repo.deleteById(idVideo);
			return true;
		}catch(Exception e){
			
			System.out.println("No existe el dato que se intenta borrar");
			return false;
		}
		
	}
	

}
