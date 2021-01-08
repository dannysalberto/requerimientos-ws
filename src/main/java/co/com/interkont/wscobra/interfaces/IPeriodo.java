package co.com.interkont.wscobra.interfaces;

import java.util.Date;
import java.util.List;

import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.models.Periodo;


public interface IPeriodo {

	List<Periodo> desplegarTodos();
	List<Periodo> ListarPorObra(Integer idObra);
	Periodo ListarPorObraFecha(Integer idObra,Date fechaIni, Date FechaFin);
	void guardar(Periodo periodo);
	void actualizar(Periodo periodo);
	Periodo buscarPorId(Integer id);
	void eliminar(Integer id);
	boolean eliminarAll(Iterable<Periodo> periodo);
	boolean eliminarByObra(Obra obra);

}
