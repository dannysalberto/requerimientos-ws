package co.com.interkont.wsmiobra.interfaces;

import java.util.List;

import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;

public interface IActividadObraPeriodo {

	void guardar(ActividadObraPeriodo actividadObraPeriodo);
	void eliminarAll(Iterable<ActividadObraPeriodo> actividadObraPeriodo);
	List<ActividadObraPeriodo> listarPorPeriodo(Integer idPeriodo);
	
}
