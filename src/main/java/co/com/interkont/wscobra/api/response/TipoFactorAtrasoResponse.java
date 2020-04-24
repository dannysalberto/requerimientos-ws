package co.com.interkont.wscobra.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos del tipo de factor de atraso")
public class TipoFactorAtrasoResponse {
	
	@ApiModelProperty(value = "identificador del tipo de factor de atraso")
	private Integer tipoFactorAtrasoId;
	@ApiModelProperty(value = "Descripción del tipo de factor de atraso")
	private String tipoFactorAtraso;
	
	public TipoFactorAtrasoResponse() {
	}

	public TipoFactorAtrasoResponse(Integer tipoFactorAtrasoId, String tipoFactorAtraso) {
		super();
		this.tipoFactorAtrasoId = tipoFactorAtrasoId;
		this.tipoFactorAtraso = tipoFactorAtraso;
	}

	public Integer getTipoFactorAtrasoId() {
		return tipoFactorAtrasoId;
	}

	public void setTipoFactorAtrasoId(Integer tipoFactorAtrasoId) {
		this.tipoFactorAtrasoId = tipoFactorAtrasoId;
	}

	public String getTipoFactorAtraso() {
		return tipoFactorAtraso;
	}

	public void setTipoFactorAtraso(String tipoFactorAtraso) {
		this.tipoFactorAtraso = tipoFactorAtraso;
	}
	
	
}
