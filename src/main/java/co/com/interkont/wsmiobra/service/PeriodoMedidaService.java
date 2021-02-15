package co.com.interkont.wsmiobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.IPeriodoMedida;
import co.com.interkont.wsmiobra.models.PeriodoMedida;
import co.com.interkont.wsmiobra.repository.PeriodoMedidaRepository;


@Service
public class PeriodoMedidaService implements IPeriodoMedida{

	@Autowired
	PeriodoMedidaRepository repository;
	
	
	@Override
	public PeriodoMedida buscarPorId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		try {
			return repository.findById(idPeriodo).get();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}

}
