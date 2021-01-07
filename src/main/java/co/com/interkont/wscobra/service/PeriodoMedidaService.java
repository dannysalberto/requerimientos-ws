package co.com.interkont.wscobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.interfaces.IPeriodoMedida;
import co.com.interkont.wscobra.models.PeriodoMedida;
import co.com.interkont.wscobra.repository.PeriodoMedidaRepository;


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
