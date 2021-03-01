package co.com.interkont.wsmiobra.interfaces;

import java.util.List;

import co.com.interkont.wsmiobra.models.ActividadObraPeriodoModificacion;

public interface IActividadObraPeriodoModificacion {

	void guardar(ActividadObraPeriodoModificacion actividadObraPeriodoModificacion);
	void eliminarAll(Iterable<ActividadObraPeriodoModificacion> actividadObraPeriodoModificacion);
	List<ActividadObraPeriodoModificacion> listarPorPeriodo(Integer idPeriodo);
	
}

