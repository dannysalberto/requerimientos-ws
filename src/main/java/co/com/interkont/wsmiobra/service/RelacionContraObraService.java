package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.IRelacionContratoObra;
import co.com.interkont.wsmiobra.models.RelacionContratoObra;
import co.com.interkont.wsmiobra.repository.RelacionContratoObraRepository;

@Service
public class RelacionContraObraService implements IRelacionContratoObra{

	@Autowired
	RelacionContratoObraRepository repo;
	
	@Override
	public List<RelacionContratoObra> desplegarPorObra(Integer idObra) {
		// TODO Auto-generated method stub
		List<RelacionContratoObra> lstRelacionContratoObra;
		try{
			lstRelacionContratoObra = repo.findByobraOriginal(idObra);	
			return lstRelacionContratoObra;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}

	@Override
	public RelacionContratoObra buscarPorObraContrato(Integer idObra, Integer idContrato) {
		// TODO Auto-generated method stub
		return repo.findByobraOriginalAndContrato_Id(idObra, idContrato);
	}
	
	

}
