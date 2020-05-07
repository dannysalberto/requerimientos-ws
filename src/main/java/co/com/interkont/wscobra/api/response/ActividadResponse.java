package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos de la actividad")
public class ActividadResponse {
	
	@ApiModelProperty(value = "identificador de la actividad")
	private Long actividadId;
	@ApiModelProperty(value = "Descripción de la actividad")
	private String descripcionActividad;
	@ApiModelProperty(value = "Unidad de medida de la actividad")
	private String unidadMedida;
	@ApiModelProperty(value = "Valor unitario de la actividad")
	private BigDecimal valorUnitario;
	@ApiModelProperty(value = "Cantidad programada de la actividad")
	private Double cantidadProgramada;
	@ApiModelProperty(value = "Cantidad ejecutada de la actividad")
	private Double cantidadEjecutada;
	@ApiModelProperty(value = "Valor programado de la actividad")
	private BigDecimal valorProgramado;
	@ApiModelProperty(value = "Valor ejecutado de la actividad")
	private BigDecimal valorEjecutado;
	@ApiModelProperty(value = "Porcentaje de avance de la actividad")
	private Double porcentajeAvance;
	@ApiModelProperty(value = "Cantidad ejecutada de la actividad antes de iniciar la alimentación")
	private Double cantidadEjecutadaInicial;
	@ApiModelProperty(value = "Valor ejecutado de la actividad antes de iniciar la alimentación")
	private BigDecimal valorEjecutadoInicial;
	@ApiModelProperty(value = "Porcentaje de avance de la actividad antes de iniciar la alimentación")
	private Double porcentajeAvanceInicial;
	
	public ActividadResponse() {
	}

	public ActividadResponse(Long actividadId, String descripcionActividad, String unidadMedida,
			BigDecimal valorUnitario, Double cantidadProgramada, Double cantidadEjecutada, BigDecimal valorProgramado,
			BigDecimal valorEjecutado, Double porcentajeAvance) {
		super();
		this.actividadId = actividadId;
		this.descripcionActividad = descripcionActividad;
		this.unidadMedida = unidadMedida;
		this.valorUnitario = valorUnitario;
		this.cantidadProgramada = cantidadProgramada;
		this.cantidadEjecutada = cantidadEjecutada;
		this.valorProgramado = valorProgramado;
		this.valorEjecutado = valorEjecutado;
		this.porcentajeAvance = porcentajeAvance;
		
		this.cantidadEjecutadaInicial = cantidadEjecutada;
		this.valorEjecutadoInicial = valorEjecutado;
		this.porcentajeAvanceInicial = porcentajeAvance;
	}

	public Long getActividadId() {
		return actividadId;
	}

	public void setActividadId(Long actividadId) {
		this.actividadId = actividadId;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
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
		this.cantidadEjecutadaInicial = cantidadEjecutada;
	}

	public BigDecimal getValorProgramado() {
		return valorProgramado;
	}

	public void setValorProgramado(BigDecimal valorProgramado) {
		this.valorProgramado = valorProgramado;
	}

	public BigDecimal getValorEjecutado() {
		return valorEjecutado;
	}

	public void setValorEjecutado(BigDecimal valorEjecutado) {
		this.valorEjecutado = valorEjecutado;
		this.valorEjecutadoInicial = valorEjecutado;
	}

	public Double getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(Double porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
		this.porcentajeAvanceInicial = porcentajeAvance;
	}

	public Double getCantidadEjecutadaInicial() {
		return cantidadEjecutadaInicial;
	}

	public void setCantidadEjecutadaInicial(Double cantidadEjecutadaInicial) {
		this.cantidadEjecutadaInicial = cantidadEjecutadaInicial;
	}

	public BigDecimal getValorEjecutadoInicial() {
		return valorEjecutadoInicial;
	}

	public void setValorEjecutadoInicial(BigDecimal valorEjecutadoInicial) {
		this.valorEjecutadoInicial = valorEjecutadoInicial;
	}

	public Double getPorcentajeAvanceInicial() {
		return porcentajeAvanceInicial;
	}

	public void setPorcentajeAvanceInicial(Double porcentajeAvanceInicial) {
		this.porcentajeAvanceInicial = porcentajeAvanceInicial;
	}
	
}
