package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ActividadRequest DTO-REQUEST (Objeto para el request de una actividad)")
public class ActividadRequest {

	@NotNull
	@ApiModelProperty(value = "identificador de la actividad")
	private Long actividadId;	
	@ApiModelProperty(value = "Cantidad ejecutado de la actividad")
	private Double cantidadEjecutada;
	
	public Long getActividadId() {
		return actividadId;
	}
	public void setActividadId(Long actividadId) {
		this.actividadId = actividadId;
	}
	public Double getCantidadEjecutada() {
		return cantidadEjecutada;
	}
	public void setCantidadEjecutada(Double cantidadEjecutada) {
		this.cantidadEjecutada = cantidadEjecutada;
	}
	
	@Override
	public String toString() {
		return "ActividadRequest [actividadId=" + actividadId + ", cantidadEjecutada=" + cantidadEjecutada + "]";
	}
	
	
}
