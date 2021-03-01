package co.com.interkont.wsmiobra.interfaces;

import java.util.Date;
import java.util.List;

import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.PeriodoModificacion;


public interface IPeriodoModificacion {

	List<PeriodoModificacion> desplegarTodos();
	List<PeriodoModificacion> ListarPorObra(Integer idObra);
	List<PeriodoModificacion> ListarPorObraFecha(Integer idObra,Date fechaIni, Date FechaFin);
	List<PeriodoModificacion> ListarPeriodosPorObraFechaInicio(Integer idObra,Date fechaIni);
	void guardar(PeriodoModificacion periodo);
	void actualizar(PeriodoModificacion periodo);
	PeriodoModificacion buscarPorId(Integer id);
	void eliminar(Integer id);
	boolean eliminarAll(Iterable<PeriodoModificacion> periodoModificacion);
	boolean eliminarByObra(ObraModificacion obra);

}
