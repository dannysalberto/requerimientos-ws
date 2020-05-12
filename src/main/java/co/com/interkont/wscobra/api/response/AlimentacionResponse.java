package co.com.interkont.wscobra.api.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("AlimentacionResponse DTO-RESPONSE Mensaje de respuesta después de una alimentación")
public class AlimentacionResponse {

	@ApiModelProperty(value = "Estado de la actualización:(0=Exitoso) || (1=Error)")
    private Integer status;
	@ApiModelProperty(value = "Listado de mensajes")
	private List<MensajeResponse> mensajes;
	
	public AlimentacionResponse() {
		
	}
	
	public AlimentacionResponse(Integer status, List<MensajeResponse> mensajes) {
		this.status = status;
		this.mensajes = mensajes;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<MensajeResponse> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<MensajeResponse> mensajes) {
		this.mensajes = mensajes;
	}
	
	
}
