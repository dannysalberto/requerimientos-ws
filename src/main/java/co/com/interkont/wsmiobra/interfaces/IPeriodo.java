package co.com.interkont.wsmiobra.interfaces;

import java.util.Date;
import java.util.List;

import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.Periodo;


public interface IPeriodo {

	List<Periodo> desplegarTodos();
	List<Periodo> ListarPorObra(Integer idObra);
	List<Periodo> ListarPorObraFecha(Integer idObra,Date fechaIni, Date FechaFin);
	List<Periodo> ListarPeriodosPorObraFechaInicio(Integer idObra,Date fechaIni);
	void guardar(Periodo periodo);
	void actualizar(Periodo periodo);
	Periodo buscarPorId(Integer id);
	void eliminar(Integer id);
	boolean eliminarAll(Iterable<Periodo> periodo);
	boolean eliminarByObra(Obra obra);

}
