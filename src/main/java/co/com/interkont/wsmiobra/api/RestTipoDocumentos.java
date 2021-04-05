/**
 * 
 */
package co.com.interkont.wsmiobra.api;

/**
 * @author VictorGomez
 *
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wsmiobra.models.TipoDocumentos;
import co.com.interkont.wsmiobra.service.TipoDocumentosJPA;


@RestController
@RequestMapping(value="/tipodocumento")
@CrossOrigin(origins="*")
public class RestTipoDocumentos {
	
	@Autowired
	TipoDocumentosJPA serviceTipoDocumento;
	
	@GetMapping(value="/tipo/{inttipodoc}")
	public ResponseEntity<?> listar(@PathVariable("inttipodoc") int inttipodoc) {
		List<TipoDocumentos> lista = null;
		lista = serviceTipoDocumento.buscarTipoDocumentos(inttipodoc);
		if (!lista.isEmpty()) {
			return new ResponseEntity<List<TipoDocumentos>>(lista , HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("No existe el dato", HttpStatus.NOT_FOUND);
		}		
	}
	

}
