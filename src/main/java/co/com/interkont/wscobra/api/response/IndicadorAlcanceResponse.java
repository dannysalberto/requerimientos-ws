package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;

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
	private BigDecimal cantidadProgramada;
	@ApiModelProperty(value = "Cantidad ejecutada de la actividad")
	private BigDecimal cantidadEjecutada;
	@ApiModelProperty(value = "Porcentaje de avance de la actividad")
	private BigDecimal porcentajeAvance;
	@ApiModelProperty(value = "Cantidad ejecutada de la actividad antes de iniciar la alimentación")
	private BigDecimal cantidadEjecutadaInicial;
	@ApiModelProperty(value = "Porcentaje de avance de la actividad antes de iniciar la alimentación")
	private BigDecimal porcentajeAvanceInicial;
	
	public IndicadorAlcanceResponse() {
	}
	
	public IndicadorAlcanceResponse(Integer indicadorAlcanceId, String descripcionIndicadorAlcance, String unidadMedida,
			BigDecimal cantidadProgramada, BigDecimal cantidadEjecutada, BigDecimal porcentajeAvance) {
		super();
		this.indicadorAlcanceId = indicadorAlcanceId;
		this.descripcionIndicadorAlcance = descripcionIndicadorAlcance;
		this.unidadMedida = unidadMedida;
		this.cantidadProgramada = cantidadProgramada;
		this.cantidadEjecutada = cantidadEjecutada;
		this.porcentajeAvance = porcentajeAvance;
		this.cantidadEjecutadaInicial = cantidadEjecutada;
		this.porcentajeAvanceInicial = porcentajeAvance;
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

	public BigDecimal getCantidadProgramada() {
		return cantidadProgramada;
	}

	public void setCantidadProgramada(BigDecimal cantidadProgramada) {
		this.cantidadProgramada = cantidadProgramada;
	}

	public BigDecimal getCantidadEjecutada() {
		return cantidadEjecutada;
	}

	public void setCantidadEjecutada(BigDecimal cantidadEjecutada) {
		this.cantidadEjecutada = cantidadEjecutada;
	}

	public BigDecimal getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(BigDecimal porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public BigDecimal getCantidadEjecutadaInicial() {
		return cantidadEjecutadaInicial;
	}

	public void setCantidadEjecutadaInicial(BigDecimal cantidadEjecutadaInicial) {
		this.cantidadEjecutadaInicial = cantidadEjecutadaInicial;
	}

	public BigDecimal getPorcentajeAvanceInicial() {
		return porcentajeAvanceInicial;
	}

	public void setPorcentajeAvanceInicial(BigDecimal porcentajeAvanceInicial) {
		this.porcentajeAvanceInicial = porcentajeAvanceInicial;
	}
	
	
	
}
