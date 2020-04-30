package co.com.interkont.wscobra.api.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ActividadRequest DTO-REQUEST (Objeto para el request de una actividad)")
public class ActividadRequest {

	@NotNull
	@ApiModelProperty(value = "identificador de la actividad")
	private Integer actividadId;	
	@ApiModelProperty(value = "Cantidad ejecutado de la actividad")
	private BigDecimal cantidadEjecutada;
	
	public Integer getActividadId() {
		return actividadId;
	}
	public void setActividadId(Integer actividadId) {
		this.actividadId = actividadId;
	}
	public BigDecimal getCantidadEjecutada() {
		return cantidadEjecutada;
	}
	public void setCantidadEjecutada(BigDecimal cantidadEjecutada) {
		this.cantidadEjecutada = cantidadEjecutada;
	}
	
	
}
