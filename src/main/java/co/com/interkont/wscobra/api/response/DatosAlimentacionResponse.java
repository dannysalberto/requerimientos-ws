package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos necesarios para efectuar una alimentación")
public class DatosAlimentacionResponse {
	@ApiModelProperty(value = "Si (100 - 100 * porcentajeAvance / porcentajeProyectado) <= limitePorcentajeAtraso entonces el semáforo de alimentación será Verde; "
			+ "\nSi (100 - 100 * porcentajeAvance / porcentajeProyectado) > limitePorcentajeAtraso entonces está atrasado y se debe mostrar la funcionalidad de factores de atraso. "
			+ "\nNota: El porcentajeProyectado se refiere al correspondiente al periodo seleccionado para la alimentación.")
    private Double limitePorcentajeAtraso;
	@ApiModelProperty(value = "Si ((100 - 100 * porcentajeAvance / porcentajeProyectado) > limitePorcentajeAtraso) y (100 - 100 * porcentajeAvance / porcentajeProyectado) <= limitePorcentajeAtrasoAmarillo)) entonces el semáforo será Amarillo; "
			+ "\nSi (100 - 100 * porcentajeAvance / porcentajeProyectado) > limitePorcentajeAtrasoAmarillo entonces el semáforo de alimentación será Rojo. "
			+ "\nSemáforo: 0 <= Verde <= limitePorcentajeAtraso < Amarillo <= limitePorcentajeAtrasoAmarillo < Rojo "
			+ "\nNotas: "
			+ "\n1. El porcentajeProyectado se refiere correspondiente al periodo seleccionado para la alimentación. ")
    private Double limitePorcentajeAtrasoAmarillo;
	@ApiModelProperty(value = "Listado de periodos")
	private List<PeriodoResponse> periodos;
	@ApiModelProperty(value = "Listado de actividades")
	private List<ActividadResponse> actividades;
	@ApiModelProperty(value = "Listado de indicadores de alcance")
	private List<IndicadorAlcanceResponse> indicadoresAlcance;
	@ApiModelProperty(value = "Listado de aspectos a evaluar en el avance cualitativo")
	private List<AspectoEvaluarResponse> apectosEvaluar;
	@ApiModelProperty(value = "Listado de tipos de factores de atraso")
	private List<TipoFactorAtrasoResponse> tiposFactorAtraso;
	@ApiModelProperty(value = "Listado de factores de atraso")
	private List<FactorAtrasoResponse> factoresAtraso;
	
	public void mock() {
		limitePorcentajeAtraso=7.0;
		limitePorcentajeAtrasoAmarillo=20.0;
		

	}
	
	public DatosAlimentacionResponse() {
		mock();
	}


	public Double getLimitePorcentajeAtraso() {
		return limitePorcentajeAtraso;
	}


	public void setLimitePorcentajeAtraso(Double limitePorcentajeAtraso) {
		this.limitePorcentajeAtraso = limitePorcentajeAtraso;
	}

	public List<PeriodoResponse> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<PeriodoResponse> periodos) {
		this.periodos = periodos;
	}

	public List<ActividadResponse> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadResponse> actividades) {
		this.actividades = actividades;
	}

	public List<IndicadorAlcanceResponse> getIndicadoresAlcance() {
		return indicadoresAlcance;
	}

	public void setIndicadoresAlcance(List<IndicadorAlcanceResponse> indicadoresAlcance) {
		this.indicadoresAlcance = indicadoresAlcance;
	}

	public List<AspectoEvaluarResponse> getApectosEvaluar() {
		return apectosEvaluar;
	}

	public void setApectosEvaluar(List<AspectoEvaluarResponse> apectosEvaluar) {
		this.apectosEvaluar = apectosEvaluar;
	}

	public List<TipoFactorAtrasoResponse> getTiposFactorAtraso() {
		return tiposFactorAtraso;
	}

	public void setTiposFactorAtraso(List<TipoFactorAtrasoResponse> tiposFactorAtraso) {
		this.tiposFactorAtraso = tiposFactorAtraso;
	}

	public List<FactorAtrasoResponse> getFactoresAtraso() {
		return factoresAtraso;
	}

	public void setFactoresAtraso(List<FactorAtrasoResponse> factoresAtraso) {
		this.factoresAtraso = factoresAtraso;
	}

	public Double getLimitePorcentajeAtrasoAmarillo() {
		return limitePorcentajeAtrasoAmarillo;
	}

	public void setLimitePorcentajeAtrasoAmarillo(Double limitePorcentajeAtrasoAmarillo) {
		this.limitePorcentajeAtrasoAmarillo = limitePorcentajeAtrasoAmarillo;
	}
}
