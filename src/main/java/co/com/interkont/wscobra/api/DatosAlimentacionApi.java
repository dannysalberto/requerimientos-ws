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
import co.com.interkont.wscobra.api.request.DatosAlimentacionRequest;
import co.com.interkont.wscobra.api.response.ActividadResponse;

/**
 * imports DTO
 */

/**
 * imports SERVICE
 */
/**
 * imports REQUEST
 */
import co.com.interkont.wscobra.api.response.DatosAlimentacionResponse;
import co.com.interkont.wscobra.api.response.PeriodoResponse;
/**
 * imports RESPONSE
 */
import co.com.interkont.wscobra.dto.VistaActividades;
import co.com.interkont.wscobra.dto.VistaPeriodosObra;
import co.com.interkont.wscobra.service.ActividadesService;
import co.com.interkont.wscobra.service.PeriodosObraService;



@RestController
@Api(value = "Api para consultar los datos necesarios para alimentar un proyecto",
     consumes="application/json")

public class DatosAlimentacionApi {
	@Autowired
	ActividadesService actividadesService;
	@Autowired
	PeriodosObraService periodosObraService;
	
	@Autowired
	Mapper mapper;
	
	
	@RequestMapping(value="/datos-alimentacion", method=RequestMethod.POST)
	@ApiOperation(value = "Datos necesarios para alimentar un proyecto.")
	public DatosAlimentacionResponse getDatosAlimentacionResponse(@RequestBody DatosAlimentacionRequest datosAlimentacionRequest){
		DatosAlimentacionResponse datosAlimentacionResponse = new DatosAlimentacionResponse();
		
		List<VistaActividades> actividades = actividadesService.findByCodigoProyecto(datosAlimentacionRequest.getCodigoProyecto());
		List<VistaPeriodosObra> periodos = periodosObraService.findByCodigoProyecto(datosAlimentacionRequest.getCodigoProyecto());
		
		List<ActividadResponse> actividadesResponse = new ArrayList<ActividadResponse>();
		List<PeriodoResponse> periodosResponse = new ArrayList<PeriodoResponse>();

		for (VistaActividades actividad : actividades) {
			ActividadResponse actividadResponse = mapper.map(actividad, ActividadResponse.class);
			actividadesResponse.add(actividadResponse);
		}
		
		periodos.forEach(periodo->{
			periodosResponse.add(mapper.map(periodo, PeriodoResponse.class));
		});
		
		datosAlimentacionResponse.setActividades(actividadesResponse);
		datosAlimentacionResponse.setPeriodos(periodosResponse);
		
		return datosAlimentacionResponse;
	}
}
