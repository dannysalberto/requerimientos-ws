package co.com.interkont.wsmiobra.interfaces;

import java.util.Date;

import co.com.interkont.wsmiobra.dto.Obra;

public interface IObraWS {

	Obra buscarPorId(Integer id);
	Obra actualizar(Obra obra);
	void eliminar(Integer id);
	Double totalPrecioActividades(Integer id);
	Double totalCostoDirecto(Integer id);
	Integer cantidadActividades(Integer idObra); //cabecera de totales cronograma Web
	boolean tieneContratoObra(Integer idObra);
	Date fechaMaxAlimentacion(Integer idObra);


}
