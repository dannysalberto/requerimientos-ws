package co.com.interkont.wscobra.api.request;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DatosProyectoRequest DTO-REQUEST (Objeto para el request del detalle de proyecto u/o obra)")
public class DatosProyectoRequest {

	
	@ApiModelProperty(value = "{Obligatorío} Código del del proyecto del cual, se va buscar la información detallada.")
	private Integer codigoproyecto;	
	@ApiModelProperty(value = "Código del usuario, para identificar si el proyecto es favorito.")
	private Integer codigousuario;	
	@ApiModelProperty(value = "{Obligatorío} Longitud de las coordenas del usuario.")
	private BigDecimal longitud;	
	@ApiModelProperty(value = "{Obligatorío} Latitud de las coordenas del usuario.")
	private BigDecimal latitud;
	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}
	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}
	public Integer getCodigousuario() {
		return codigousuario;
	}
	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}
	public BigDecimal getLongitud() {
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public BigDecimal getLatitud() {
		return latitud;
	}
	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}	
	
	
	
}
