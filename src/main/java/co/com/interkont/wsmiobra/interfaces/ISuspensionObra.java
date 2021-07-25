package co.com.interkont.wsmiobra.interfaces;

import java.util.Date;
import java.util.List;

import co.com.interkont.wsmiobra.models.SuspensionObra;


public interface ISuspensionObra {

	/*Periodo esta detro de la suspension*/
	SuspensionObra getPorObraCaso1(Integer idObra,Date fechaIni, Date FechaFin);
	
	/*fecha de inicio periodo esta fuera de suspension pero fecha fin esta dentro de suspension*/
	SuspensionObra getPorObraCaso2(Integer idObra,Date fechaIni, Date FechaFin);
	
	/*fecha de inicio esta dentro de la suspension pero fecha fin no*/
	SuspensionObra getPorObraCaso3(Integer idObra,Date fechaIni, Date FechaFin);

	/*fecha de inicio igual a fecha fin periodo*/
	SuspensionObra getPorObraCaso4(Integer idObra, Date fechaFin);

	/*fecha de fin igual a fecha inicio periodo*/
	SuspensionObra getPorObraCaso5(Integer idObra, Date fechaInicio);

	List<SuspensionObra> getSuspensiones(Integer idObra);

}
