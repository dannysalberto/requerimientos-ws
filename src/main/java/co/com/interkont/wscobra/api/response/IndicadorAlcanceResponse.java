package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos del indicador de alcance")
public class IndicadorAlcanceResponse {
	
	@ApiModelProperty(value = "identificador del indicador de alcance")
	private Integer indicadorAlcanceId;
	@ApiModelProperty(value = "Descripción del indicador de alcance")
	private String descripcionIndicadorAlcance;
	@ApiModelProperty(value = "Unidad de medida del indicador de alcance")
	private String unidadMedida;
	@ApiModelProperty(value = "Cantidad programada de la actividad")
	private Double cantidadProgramada;
	@ApiModelProperty(value = "Cantidad ejecutada de la actividad")
	private Double cantidadEjecutada;
	@ApiModelProperty(value = "Porcentaje de avance de la actividad")
	private Double porcentajeAvance;
	
	public IndicadorAlcanceResponse() {
	}
	
	public IndicadorAlcanceResponse(Integer indicadorAlcanceId, String descripcionIndicadorAlcance, String unidadMedida,
			Double cantidadProgramada, Double cantidadEjecutada, Double porcentajeAvance) {
		super();
		this.indicadorAlcanceId = indicadorAlcanceId;
		this.descripcionIndicadorAlcance = descripcionIndicadorAlcance;
		this.unidadMedida = unidadMedida;
		this.cantidadProgramada = cantidadProgramada;
		this.cantidadEjecutada = cantidadEjecutada;
		this.porcentajeAvance = porcentajeAvance;
	}



	public Integer getIndicadorAlcanceId() {
		return indicadorAlcanceId;
	}

	public void setIndicadorAlcanceId(Integer indicadorAlcanceId) {
		this.indicadorAlcanceId = indicadorAlcanceId;
	}

	public String getDescripcionIndicadorAlcance() {
		return descripcionIndicadorAlcance;
	}

	public void setDescripcionIndicadorAlcance(String descripcionIndicadorAlcance) {
		this.descripcionIndicadorAlcance = descripcionIndicadorAlcance;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Double getCantidadProgramada() {
		return cantidadProgramada;
	}

	public void setCantidadProgramada(Double cantidadProgramada) {
		this.cantidadProgramada = cantidadProgramada;
	}

	public Double getCantidadEjecutada() {
		return cantidadEjecutada;
	}

	public void setCantidadEjecutada(Double cantidadEjecutada) {
		this.cantidadEjecutada = cantidadEjecutada;
	}

	public Double getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(Double porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}
	
}
