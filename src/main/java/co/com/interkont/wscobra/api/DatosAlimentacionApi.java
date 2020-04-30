package co.com.interkont.wscobra.api;

import java.util.ArrayList;
import java.util.List;

//import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import co.com.interkont.wscobra.api.request.AlimentacionRequest;
import co.com.interkont.wscobra.api.request.DatosAlimentacionRequest;
import co.com.interkont.wscobra.api.response.AlimentacionResponse;

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
import co.com.interkont.wscobra.api.response.MensajeResponse;
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
	
	@RequestMapping(value="/guardar-alimentacion", method=RequestMethod.POST)
	@ApiOperation(value = "Guardar alimentación de un proyecto.")
	public AlimentacionResponse getGuardarAlimentacion(@RequestBody AlimentacionRequest alimentacionRequest){
		
		AlimentacionResponse response = new AlimentacionResponse();
		
		
		if(alimentacionRequest.getCodigoproyecto() == 2) {
			response.setStatus(0);
			List<MensajeResponse> mensajes = new ArrayList<MensajeResponse>();
			mensajes.add(new MensajeResponse("Este periodo ya fue raportado"));
			mensajes.add(new MensajeResponse("Debes justificar el factor de atraso"));
			mensajes.add(new MensajeResponse("Debes subir al menos una foto"));			
			response.setMensajes(mensajes);
			return response;
		}
		
		response.setStatus(1);
		return response;
	}
	
}