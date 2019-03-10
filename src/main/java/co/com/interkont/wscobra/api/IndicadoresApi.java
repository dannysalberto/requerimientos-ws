package co.com.interkont.wscobra.api;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import co.com.interkont.wscobra.dto.VistaIndicadoresGlobales;
import co.com.interkont.wscobra.service.IndicadoresGlobalesService;
import co.com.interkont.wscobra.api.request.IndicadoresGlobalesRequest;
import co.com.interkont.wscobra.api.response.IndicadoresGlobalesResponse;


@RestController
@Api(value = "Api para servicios de indicadores", 
     description = "Está API, muestra los servicios de indicadores, como indicadores globales.",
     consumes="application/json")

public class IndicadoresApi {
	
	
	@Autowired
	IndicadoresGlobalesService indicadoresGlobalesService;
	
	
	@Autowired
	Mapper mapper;

	
	@RequestMapping(value="/indicadores-globales", method=RequestMethod.POST)
	@ApiOperation(value = "Listado de los indicadores globales", 
				  notes = "Este servicio retorna con los consolidados por cada línea tematíca u/o línea de negocio.")
	public List<IndicadoresGlobalesResponse> getIndicadoresGlobales(@RequestBody IndicadoresGlobalesRequest indicadoresGlobalesRequest){
		
		List<VistaIndicadoresGlobales> indicadores = indicadoresGlobalesService.findByAll(indicadoresGlobalesRequest);
		
		List<IndicadoresGlobalesResponse> indicadoresResponse = new ArrayList<IndicadoresGlobalesResponse>();

		for (VistaIndicadoresGlobales indicador : indicadores) {
			
			 indicadoresResponse.add(mapper.map(indicador, IndicadoresGlobalesResponse.class));
		}
		
		return indicadoresResponse;
	}

}

