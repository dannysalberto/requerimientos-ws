package co.com.interkont.wscobra.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("MensajeResponse DTO-RESPONSE Mensaje de respuesta")
public class MensajeResponse {

	@ApiModelProperty(value = "Mensaje de respuesta")
    private String mensaje;

	public MensajeResponse() {	
		
	}
	
	public MensajeResponse(String mensaje) {	
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
