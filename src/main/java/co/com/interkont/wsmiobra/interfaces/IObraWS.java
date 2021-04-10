package co.com.interkont.wsmiobra.interfaces;

import co.com.interkont.wsmiobra.dto.Obra;

public interface IObraWS {

	Obra buscarPorId(Integer id);
	void actualizar(Obra obra);
	void eliminar(Integer id);
	Double totalPrecioActividades(Integer id);
	Double totalCostoDirecto(Integer id);
	Integer cantidadActividades(Integer idObra); //cabecera de totales cronograma Web
	boolean tieneContratoObra(Integer idObra);


}
