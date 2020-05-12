package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("AspectosEvaluarRequest DTO-REQUEST (Objeto para el request de un aspecto a evaluar)")
public class AspectoEvaluarRequest {
	
	@NotNull
	@ApiModelProperty(value = "Identificador del aspecto a evaluar")
	private Integer aspectoEvaluarId;
	@ApiModelProperty(value = "Logros del aspecto a evaluar")
	private String logrosAspectoEvaluar;
	@ApiModelProperty(value = "Dificultades del aspecto a evaluar")
	private String dificultadesAspectoEvaluar;
	
	public Integer getAspectoEvaluarId() {
		return aspectoEvaluarId;
	}
	public void setAspectoEvaluarId(Integer aspectoEvaluarId) {
		this.aspectoEvaluarId = aspectoEvaluarId;
	}
	public String getLogrosAspectoEvaluar() {
		return logrosAspectoEvaluar;
	}
	public void setLogrosAspectoEvaluar(String logrosAspectoEvaluar) {
		this.logrosAspectoEvaluar = logrosAspectoEvaluar;
	}
	public String getDificultadesAspectoEvaluar() {
		return dificultadesAspectoEvaluar;
	}
	public void setDificultadesAspectoEvaluar(String dificultadesAspectoEvaluar) {
		this.dificultadesAspectoEvaluar = dificultadesAspectoEvaluar;
	}

	
}
