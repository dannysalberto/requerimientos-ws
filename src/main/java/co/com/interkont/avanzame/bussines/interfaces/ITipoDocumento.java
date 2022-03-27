package co.com.interkont.avanzame.bussines.interfaces;

import java.util.List;

import co.com.interkont.avanzame.models.TipoDocumento;

public interface ITipoDocumento {
	
	TipoDocumento buscarPorId(Integer idTipoDoc);
	List<TipoDocumento> listar();

}
