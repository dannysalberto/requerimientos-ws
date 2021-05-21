package co.com.interkont.wsmiobra.interfaces;

import co.com.interkont.wsmiobra.models.RelacionContratoObraTable;

public interface IRelacionContratoObraTable {
	
	RelacionContratoObraTable buscarPorObraContrato(Integer idObra, Integer idContrato);
	void guardar(RelacionContratoObraTable relacionContratoObra);

}
