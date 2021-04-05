/**
 * 
 */
package co.com.interkont.wsmiobra.interfaces;

/**
 * @author VictorGomez
 *
 */
import java.util.List;

import co.com.interkont.wsmiobra.models.TipoDocumentos;

public interface ITipoDocumento {
	
	List<TipoDocumentos> buscarTipoDocumentos(Integer inttipodoc);

}
