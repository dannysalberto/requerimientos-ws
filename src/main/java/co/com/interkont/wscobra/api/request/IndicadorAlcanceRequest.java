package co.com.interkont.wscobra.api.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IndicadorAlcanceRequest DTO-REQUEST (Objeto para el request de una indicador de alcance)")
public class IndicadorAlcanceRequest {

	@NotNull
	@ApiModelProperty(value = "Identificador del indicador de alcance")
	private Integer indicadorAlcanceId;
	
	@NotNull
	@ApiModelProperty(value = "Cantidad ejecuta en el periodo del indicador de alcance")
	private BigDecimal cantidadEjecucion;

	public Integer getIndicadorAlcanceId() {
		return indicadorAlcanceId;
	}

	public void setIndicadorAlcanceId(Integer indicadorAlcanceId) {
		this.indicadorAlcanceId = indicadorAlcanceId;
	}

	public BigDecimal getCantidadEjecucion() {
		return cantidadEjecucion;
	}

	public void setCantidadEjecucion(BigDecimal cantidadEjecucion) {
		this.cantidadEjecucion = cantidadEjecucion;
	}

	@Override
	public String toString() {
		return "IndicadorAlcanceRequest [indicadorAlcanceId=" + indicadorAlcanceId + ", cantidadEjecucion="
				+ cantidadEjecucion + "]";
	}
	
}
