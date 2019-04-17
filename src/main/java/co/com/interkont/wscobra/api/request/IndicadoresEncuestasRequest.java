package co.com.interkont.wscobra.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IndicadoresEncuestasRequest DTO-REQUEST (Objeto para el request de los indicadores resultados por proyecto)")
public class IndicadoresEncuestasRequest {

	@ApiModelProperty(value="Código del proyecto u/o obra del cual se van a retornar los indicadores")
	private Integer codigoproyecto;

	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}

	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}
	
	
	
}
