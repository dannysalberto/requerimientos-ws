package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("FactoresAtrasoRequest DTO-REQUEST (Objeto para el request de un factor de atraso)")
public class FactoresAtrasoRequest {

	@NotNull
	@ApiModelProperty(value = "Identificador del factor de atraso")
	private Integer factorAtrasoId;

	public Integer getFactorAtrasoId() {
		return factorAtrasoId;
	}

	public void setFactorAtrasoId(Integer factorAtrasoId) {
		this.factorAtrasoId = factorAtrasoId;
	}
	
	
	
}
