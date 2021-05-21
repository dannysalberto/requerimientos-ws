package co.com.interkont.wsmiobra.interfaces;

import java.util.List;

import co.com.interkont.wsmiobra.models.RelacionContratoObra;

public interface IRelacionContratoObra {
	
	List<RelacionContratoObra> desplegarPorObra(Integer idObra);
	RelacionContratoObra buscarPorObraContrato(Integer idObra, Integer idContrato);

}
