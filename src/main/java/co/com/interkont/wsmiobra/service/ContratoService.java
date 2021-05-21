package co.com.interkont.wsmiobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.IContrato;
import co.com.interkont.wsmiobra.models.Contrato;
import co.com.interkont.wsmiobra.repository.ContratoRepository;

@Service
public class ContratoService implements IContrato{

	@Autowired
	ContratoRepository repo;
	
	@Override
	public Contrato guardar(Contrato contrato) {
		// TODO Auto-generated method stub
		return repo.save(contrato);
	}

}
