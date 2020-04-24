package co.com.interkont.wscobra.api;

//import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import co.com.interkont.wscobra.api.request.DatosAlimentacionRequest;

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
/**
 * imports RESPONSE
 */
import co.com.interkont.wscobra.api.response.ProyectosListaResponse;
//import co.com.interkont.wscobra.api.response.DatosProyectoResponse;



@RestController
@Api(value = "Api para consultar los datos necesarios para alimentar un proyecto",
     consumes="application/json")

public class DatosAlimentacionApi {
	/*
	@Autowired
	ProyectosListaService proyectosListaService;
	
	@Autowired
	Mapper mapper;
	*/
	
	@RequestMapping(value="/datos-alimentacion", method=RequestMethod.POST)
	@ApiOperation(value = "Datos necesarios para alimentar un proyecto.")
	public DatosAlimentacionResponse getDatosAlimentacionResponse(@RequestBody DatosAlimentacionRequest datosAlimentacionResponse){
		return new DatosAlimentacionResponse();
	}
}
