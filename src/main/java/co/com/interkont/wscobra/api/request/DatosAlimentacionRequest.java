package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("proyectosListaRequest DTO-REQUEST (Objeto para el request de datos de alimentacion de proyecto)")
public class DatosAlimentacionRequest {
	
	@NotNull
	@ApiModelProperty(value = "Código del proyecto a alimentar.")
	private Integer codigoProyecto;

	public Integer getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(Integer codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	
}
