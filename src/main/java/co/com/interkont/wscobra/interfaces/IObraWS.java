package co.com.interkont.wscobra.interfaces;

import co.com.interkont.wscobra.dto.Obra;

public interface IObraWS {

	Obra buscarPorId(Integer id);
	void actualizar(Obra obra);
	void eliminar(Integer id);
	Double totalPrecioActividades(Integer id);
	Double totalCostoDirecto(Integer id);
	Integer cantidadActividades(Integer idObra); //cabecera de totales cronograma Web
	

}
