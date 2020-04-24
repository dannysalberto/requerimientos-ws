package co.com.interkont.wscobra.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos del factor de atraso")
public class FactorAtrasoResponse {
	
	@ApiModelProperty(value = "identificador del factor de atraso")
	private Integer factorAtrasoId;
	@ApiModelProperty(value = "Descripción del factor de atraso")
	private String factorAtraso;
	@ApiModelProperty(value = "identificador del tipo factor de atraso")
	private Integer tipoFactorAtrasoId;
	
	public FactorAtrasoResponse() {
	}

	public FactorAtrasoResponse(Integer factorAtrasoId, String factorAtraso, Integer tipoFactorAtrasoId) {
		super();
		this.factorAtrasoId = factorAtrasoId;
		this.factorAtraso = factorAtraso;
		this.tipoFactorAtrasoId = tipoFactorAtrasoId;
	}

	public Integer getFactorAtrasoId() {
		return factorAtrasoId;
	}

	public void setFactorAtrasoId(Integer factorAtrasoId) {
		this.factorAtrasoId = factorAtrasoId;
	}

	public String getFactorAtraso() {
		return factorAtraso;
	}

	public void setFactorAtraso(String factorAtraso) {
		this.factorAtraso = factorAtraso;
	}

	public Integer getTipoFactorAtrasoId() {
		return tipoFactorAtrasoId;
	}

	public void setTipoFactorAtrasoId(Integer tipoFactorAtrasoId) {
		this.tipoFactorAtrasoId = tipoFactorAtrasoId;
	}
	
}
