package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.api.response.ActividadObraResponse;
import co.com.interkont.wsmiobra.dao.vActividadesObraRepository;
import co.com.interkont.wsmiobra.dto.Obra;

@Service
public class vActividadObraService {

	@Autowired
	vActividadesObraRepository repository;
	
	public List<ActividadObraResponse> findAllByObra(Obra obra){
		return repository.findByObra(obra);		
	}
}
