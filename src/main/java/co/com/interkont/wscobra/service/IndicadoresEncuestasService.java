package co.com.interkont.wscobra.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.api.response.IndicadoresEncuestasResponse;
import co.com.interkont.wscobra.dao.IndicadoresEncuestasRepository;
import co.com.interkont.wscobra.dao.IndicadoresRepository;
import co.com.interkont.wscobra.dto.FuncionIndicadoresEncuestas;
import co.com.interkont.wscobra.dto.Indicador;

@Service
public class IndicadoresEncuestasService {
	
	@Autowired 
	IndicadoresEncuestasRepository indicadoresEncuestasDAO;
	
	@Autowired 
	IndicadoresRepository indicadoresDAO;
	
	@Autowired
	Mapper mapper;
	
	
	
	public List<IndicadoresEncuestasResponse> getIndicadoresEncuestasGlobales() {
		
		FuncionIndicadoresEncuestas indicadoresResultados = indicadoresEncuestasDAO.findByAll(); 
		return getIndicadoresResultados(indicadoresResultados);
	}
	
	public List<IndicadoresEncuestasResponse> getIndicadoresEncuetasProyecto(Integer codigoproyecto) {
		
		FuncionIndicadoresEncuestas indicadoresResultados = indicadoresEncuestasDAO.findByProyecto(codigoproyecto); 
		return getIndicadoresResultados(indicadoresResultados);
	}
	
	
	private List<IndicadoresEncuestasResponse> getIndicadoresResultados(FuncionIndicadoresEncuestas indicadoresResultados){
		
		List<Indicador> indicadoresUE = indicadoresDAO.findAllIndicadores();		
		List<IndicadoresEncuestasResponse> indicadoresResponse = new ArrayList<IndicadoresEncuestasResponse>();
		
		for (Indicador indicador : indicadoresUE) {
					
			
			IndicadoresEncuestasResponse indicadorResponse = mapper.map(indicador, IndicadoresEncuestasResponse.class);
			
			if (indicador.getId() < 14) {
				indicadorResponse.setIndicador("IND - "+indicadorResponse.getIndicador());
			} else {
				indicadorResponse.setIndicador("ORG - "+indicadorResponse.getIndicador());
			}
			
			switch (indicador.getId()) {
			case 2:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd2().toString()));
				break;
			case 3:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd3().toString()));
				break;
			case 4:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd4().toString()));
				break;
			case 5:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd5().toString()));
				break;
			case 6:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd6().toString()));
				break;
			case 7:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd7().toString()));
				break;
			case 8:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd8().toString()));
				break;
			case 9:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd9().toString()));
				break;
			case 10:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd10().toString()));
				break;
			case 11:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd11().toString()));
				break;
			case 12:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd12().toString()));
				break;
			case 13:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpInd13().toString()));
				break;
			case 14:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpIndOrg1().toString()));
				break;
			case 15:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpIndOrg2().toString()));
				break;
			case 16:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpIndOrg3().toString()));
				break;
			case 17:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpIndOrg4().toString()));
				break;	
			case 18:
				indicadorResponse.setDescripcion(indicador.getDescripcion().replace("{***}", indicadoresResultados.getpIndOrg5().toString()));
				break;	
			}
			
			
			indicadoresResponse.add(indicadorResponse);
		}
		
		
		return indicadoresResponse;
	}

}
