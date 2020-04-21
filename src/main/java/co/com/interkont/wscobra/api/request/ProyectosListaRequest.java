package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("proyectosListaRequest DTO-REQUEST (Objeto para el request de vista lista proyectos)")
public class ProyectosListaRequest {
	
	@NotNull
	@ApiModelProperty(value = "Usuario que consulta.")
	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
