package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.PeriodosObraRepository;
import co.com.interkont.wscobra.dto.VistaPeriodosObra;

@Service
public class PeriodosObraService {

	@Autowired
	PeriodosObraRepository periodosObraDAO;
	
	public List<VistaPeriodosObra> findByCodigoProyecto(Integer codigoProyecto){
		return periodosObraDAO.findByCodigoproyecto(codigoProyecto);		
	}
}
