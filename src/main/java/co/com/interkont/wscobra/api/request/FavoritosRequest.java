package co.com.interkont.wscobra.api.request;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DTO-REQUEST lista de mis proyectos favoritos")
public class FavoritosRequest {
	
	@ApiModelProperty(value = "Código del usuario, para realizar la búsqueda.")
	private Integer codigousuario;
	@ApiModelProperty(value = "Longitud de las coordenas del usuario.")
	private BigDecimal longitud;
	@ApiModelProperty(value = "Latitud de las coordenas del usuario.")
	private BigDecimal latitud;
	

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
