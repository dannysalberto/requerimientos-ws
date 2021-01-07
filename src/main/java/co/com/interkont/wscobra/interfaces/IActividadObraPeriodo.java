package co.com.interkont.wscobra.interfaces;

import java.util.List;

import co.com.interkont.wscobra.models.ActividadObraPeriodo;

public interface IActividadObraPeriodo {

	void guardar(ActividadObraPeriodo actividadObraPeriodo);
	void eliminarAll(Iterable<ActividadObraPeriodo> actividadObraPeriodo);
	List<ActividadObraPeriodo> listarPorPeriodo(Integer idPeriodo);
	
}
