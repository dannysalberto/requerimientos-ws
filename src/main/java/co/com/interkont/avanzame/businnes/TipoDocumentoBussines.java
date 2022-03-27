package co.com.interkont.avanzame.businnes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.interkont.avanzame.models.TipoDocumento;
import co.com.interkont.avanzame.service.TipoDocumentoJPAServices;

@Component
public class TipoDocumentoBussines {

	@Autowired
	TipoDocumentoJPAServices repo;
	
	public List<TipoDocumento> obtenerTipoDocumentos() {
		// TODO Auto-generated method stub
		return repo.listar();
	}
	
}
