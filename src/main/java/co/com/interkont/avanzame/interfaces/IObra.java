package co.com.interkont.avanzame.interfaces;

import co.com.interkont.avanzame.models.Obra;

public interface IObra {
	
	Obra buscarPorId(Integer idObra);
	Obra actualizar(Obra obraFpo);

}
