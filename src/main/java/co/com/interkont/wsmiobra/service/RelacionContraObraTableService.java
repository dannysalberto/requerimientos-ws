package co.com.interkont.wsmiobra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.interkont.wsmiobra.interfaces.IRelacionContratoObraTable;
import co.com.interkont.wsmiobra.models.RelacionContratoObraTable;
import co.com.interkont.wsmiobra.repository.RelacionContratoObraTableRepository;

@Service
public class RelacionContraObraTableService implements IRelacionContratoObraTable{

	@Autowired
	RelacionContratoObraTableRepository repo;
	
	@Override
	public RelacionContratoObraTable buscarPorObraContrato(Integer idObra, Integer idContrato) {
		// TODO Auto-generated method stub
		return repo.findByobraOriginalAndContrato_Id(idObra, idContrato);
	}

	@Override
	public void guardar(RelacionContratoObraTable relacionContratoObra) {
		// TODO Auto-generated method stub
		repo.save(relacionContratoObra);
		
	}
	
	

}
