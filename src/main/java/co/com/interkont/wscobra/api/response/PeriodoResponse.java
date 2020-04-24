package co.com.interkont.wscobra.api.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos del periodo de alimentación")
public class PeriodoResponse {
	
	@ApiModelProperty(value = "identificador del periodo")
	private Integer periodoId;
	@ApiModelProperty(value = "Fecha inicial del periodo")
	private Date fechaIniPeriodo;
	@ApiModelProperty(value = "Fecha final del periodo")
	private Date fechaFinPeriodo;
	
	public PeriodoResponse() {
	}
	
	
	public PeriodoResponse(Integer periodoid, Date fechaIniPeriodo, Date fechaFinPeriodo) {
		super();
		this.periodoId = periodoid;
		this.fechaIniPeriodo = fechaIniPeriodo;
		this.fechaFinPeriodo = fechaFinPeriodo;
	}


	public Integer getPeriodoId() {
		return periodoId;
	}


	public void setPeriodoId(Integer periodoid) {
		this.periodoId = periodoid;
	}


	public Date getFechaIniPeriodo() {
		return fechaIniPeriodo;
	}


	public void setFechaIniPeriodo(Date fechaIniPeriodo) {
		this.fechaIniPeriodo = fechaIniPeriodo;
	}


	public Date getFechaFinPeriodo() {
		return fechaFinPeriodo;
	}


	public void setFechaFinPeriodo(Date fechaFinPeriodo) {
		this.fechaFinPeriodo = fechaFinPeriodo;
	}
	
}
