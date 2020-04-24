package co.com.interkont.wscobra.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos de los aspectos a evaluar del avance cualitativo")
public class AspectoEvaluarResponse {
	
	@ApiModelProperty(value = "identificador del aspecto a evaluar")
	private Integer aspectoEvaluarId;
	@ApiModelProperty(value = "Descripción del aspecto a evaluar")
	private String descripcionAspectoEvaluar;
	
	public AspectoEvaluarResponse() {
	}
	
	public AspectoEvaluarResponse(Integer aspectoEvaluarId, String descripcionAspectoEvaluar) {
		super();
		this.aspectoEvaluarId = aspectoEvaluarId;
		this.descripcionAspectoEvaluar = descripcionAspectoEvaluar;
	}

	public Integer getAspectoEvaluarId() {
		return aspectoEvaluarId;
	}

	public void setAspectoEvaluarId(Integer aspectoEvaluarId) {
		this.aspectoEvaluarId = aspectoEvaluarId;
	}

	public String getDescripcionAspectoEvaluar() {
		return descripcionAspectoEvaluar;
	}

	public void setDescripcionAspectoEvaluar(String descripcionAspectoEvaluar) {
		this.descripcionAspectoEvaluar = descripcionAspectoEvaluar;
	}
	
}
