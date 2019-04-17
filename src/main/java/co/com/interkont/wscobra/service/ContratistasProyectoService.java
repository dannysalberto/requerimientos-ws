package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.ContratistaProyectoRepository;
import co.com.interkont.wscobra.dto.VistaContratistasContratoProyecto;


@Service
public class ContratistasProyectoService {
	
	@Autowired
	ContratistaProyectoRepository contratistaProyectoDAO;
	
	public List<VistaContratistasContratoProyecto> getContratistasProyecto(Integer codigoproyecto){

		return contratistaProyectoDAO.findByCodigoobra(codigoproyecto);
	}
	

}
