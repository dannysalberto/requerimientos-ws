package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.api.response.ActividadObraResponse;
import co.com.interkont.wscobra.dao.vActividadesObraRepository;
import co.com.interkont.wscobra.dto.Obra;

@Service
public class vActividadObraService {

	@Autowired
	vActividadesObraRepository repository;
	
	public List<ActividadObraResponse> findAllByObra(Obra obra){
		return repository.findByObra(obra);		
	}
}
