package co.com.interkont.wsmiobra.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wsmiobra.interfaces.ISuspensionObra;
import co.com.interkont.wsmiobra.models.SuspensionObra;
import co.com.interkont.wsmiobra.repository.SuspensionObraRepository;

@Service
public class SuspensionObraService implements ISuspensionObra{

	@Autowired
	SuspensionObraRepository repository;
	
	@Override
	public SuspensionObra getPorObraCaso1(Integer idObra, Date fechaIni, Date FechaFin) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqualOrderByFechaInicioAsc(idObra, fechaIni, FechaFin);
	
	}

	@Override
	public SuspensionObra getPorObraCaso2(Integer idObra, Date fechaIni, Date FechaFin) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdAndFechaInicioGreaterThanAndFechaInicioLessThan(idObra, fechaIni, FechaFin);
	}

	@Override
	public SuspensionObra getPorObraCaso3(Integer idObra, Date fechaIni, Date FechaFin) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdAndFechaFinGreaterThanAndFechaFinLessThan(idObra, fechaIni, FechaFin);
	}

	@Override
	public SuspensionObra getPorObraCaso4(Integer idObra, Date fechaFin) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdAndFechaInicio(idObra, fechaFin);
	}

	@Override
	public SuspensionObra getPorObraCaso5(Integer idObra, Date fechaInicio) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdAndFechaFin(idObra, fechaInicio);
	}

	@Override
	public List<SuspensionObra> getSuspensiones(Integer idObra) {
		// TODO Auto-generated method stub
		return repository.findByObra_IdOrderByFechaInicioAsc(idObra);
	}

}
