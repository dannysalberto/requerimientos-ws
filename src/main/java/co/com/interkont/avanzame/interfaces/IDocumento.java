package co.com.interkont.avanzame.interfaces;

import java.util.List;

import co.com.interkont.avanzame.models.Documento;



public interface IDocumento {
	
	Documento buscarPorId(Integer id);
	void borrar(Integer id);
	
	
}
