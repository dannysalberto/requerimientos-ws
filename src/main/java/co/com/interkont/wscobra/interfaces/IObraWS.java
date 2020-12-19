package co.com.interkont.wscobra.interfaces;

import co.com.interkont.wscobra.dto.Obra;

public interface IObraWS {

	Obra buscarPorId(Integer id);
	void actualizar(Obra obra);

}
