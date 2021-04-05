/**
 * 
 */
package co.com.interkont.wsmiobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.ITipoDocumento;
import co.com.interkont.wsmiobra.models.TipoDocumentos;
import co.com.interkont.wsmiobra.repository.TipoDocumentoRepository;


/**
 * @author VictorGomez
 *
 */
@Service
public class TipoDocumentosJPA implements ITipoDocumento{
	
	@Autowired
	TipoDocumentoRepository repository;

	@Override
	public List<TipoDocumentos> buscarTipoDocumentos(Integer inttipodoc) {
		// TODO Auto-generated method stub
		return repository.findByIntparametipdoc(inttipodoc);
	}


	

}
