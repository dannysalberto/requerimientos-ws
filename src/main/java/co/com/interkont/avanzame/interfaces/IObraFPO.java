package co.com.interkont.avanzame.interfaces;

import co.com.interkont.avanzame.models.ObraFPO;

public interface IObraFPO {
	
	ObraFPO buscarPorId(Integer idObra);
	ObraFPO actualizar(ObraFPO obraFpo);

}
