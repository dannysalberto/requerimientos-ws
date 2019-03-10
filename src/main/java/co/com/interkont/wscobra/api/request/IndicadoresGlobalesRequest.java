package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IndicadoresGlobalesRequest DTO-REQUEST (Objeto para el request de indicadores globales)")
public class IndicadoresGlobalesRequest {
	
	@NotNull
	@ApiModelProperty(value = "{Obligatorío} Código de la categoría o línea, por la cual se va ha filtar.")	
	private Integer codigocategoria = -1;

	public Integer getCodigocategoria() {
		return codigocategoria;
	}

	public void setCodigocategoria(Integer codigocategoria) {
		this.codigocategoria = codigocategoria;
	}
	
	

}
