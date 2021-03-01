package co.com.interkont.wsmiobra.interfaces;

import co.com.interkont.wsmiobra.models.ObraModificacion;

public interface IObraModificacion {
	
	ObraModificacion buscarPorId(Integer id);
	ObraModificacion buscarPorIdEstado(Integer idObra,String estado);
	Double totalPrecioActividades(Integer id);
	Double totalCostoDirecto(Integer id);
	Integer cantidadActividades(Integer idObra); //cabecera de totales cronograma Web
	ObraModificacion guardar(ObraModificacion obraModificacion);
	ObraModificacion actualizar(ObraModificacion obraModificacion);
	void Eliminar(Integer id);

}
