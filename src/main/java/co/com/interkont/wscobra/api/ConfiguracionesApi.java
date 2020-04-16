package co.com.interkont.wscobra.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wscobra.api.response.ConfiguraciónResponse;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Controlador para el servicio de configuración", 
	 description = "Está API, muestra los servicisios para configuración de proyectos",
	 consumes="application/json")
public class ConfiguracionesApi {
	
	
	@RequestMapping(value="/configuracion-app", method=RequestMethod.POST)
	public ConfiguraciónResponse getConfiguracionApp() {
		ConfiguraciónResponse config = new ConfiguraciónResponse();
		
		config.setEndpoint("http://13.59.62.87:8080/cobra-ws-condor/");
		config.setEndpointimagenes("http://18.224.218.156:8080/ue/");
		config.setFormatofecha("dd-MM-yyyy");
		config.setKeygmaps("AIzaSyAyeHv9c380QhNLQr93abJZLXECSydJVMY");
		config.setTipomoneda("$");
		
		return config;
		
	}

}
