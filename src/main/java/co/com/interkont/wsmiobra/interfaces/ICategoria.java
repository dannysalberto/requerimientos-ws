package co.com.interkont.wsmiobra.interfaces;

import java.util.List;

import co.com.interkont.wsmiobra.models.Categoria;

public interface ICategoria {
	
	List<Categoria> obtenerCategorias();
	Categoria buscarPorId(Integer id);

}
