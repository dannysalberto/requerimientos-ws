package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos del periodo de alimentación")
public class PeriodoResponse {
	
	@ApiModelProperty(value = "identificador del periodo")
	private Integer periodoId;
	@ApiModelProperty(value = "Fecha inicial del periodo")
	private String fechaIniPeriodo;
	@ApiModelProperty(value = "Fecha final del periodo")
	private String fechaFinPeriodo;
	@ApiModelProperty(value = "Porcentaje en el que debería ir el proyecto para este periodo")
	private BigDecimal porcentajeProyectado;
	
	public PeriodoResponse() {
	}
	
	
	public PeriodoResponse(Integer periodoid, String fechaIniPeriodo, String fechaFinPeriodo, BigDecimal porcentajeProyectado) {
		super();
		this.periodoId = periodoid;
		this.fechaIniPeriodo = fechaIniPeriodo;
		this.fechaFinPeriodo = fechaFinPeriodo;
		this.porcentajeProyectado = porcentajeProyectado; 
	}


	public Integer getPeriodoId() {
		return periodoId;
	}


	public void setPeriodoId(Integer periodoid) {
		this.periodoId = periodoid;
	}


	public String getFechaIniPeriodo() {
		return fechaIniPeriodo;
	}


	public void setFechaIniPeriodo(String fechaIniPeriodo) {
		this.fechaIniPeriodo = fechaIniPeriodo;
	}


	public String getFechaFinPeriodo() {
		return fechaFinPeriodo;
	}


	public void setFechaFinPeriodo(String fechaFinPeriodo) {
		this.fechaFinPeriodo = fechaFinPeriodo;
	}


	public BigDecimal getPorcentajeProyectado() {
		return porcentajeProyectado;
	}


	public void setPorcentajeProyectado(BigDecimal porcentajeProyectado) {
		this.porcentajeProyectado = porcentajeProyectado;
	}
	
}
