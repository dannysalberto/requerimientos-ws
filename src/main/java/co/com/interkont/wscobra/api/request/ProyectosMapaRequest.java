package co.com.interkont.wscobra.api.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ProyectosMapaRequest DTO-REQUEST (Objeto para el request de vista mapa proyectos)")
public class ProyectosMapaRequest {
	
	@ApiModelProperty(value = "Código del usuario, para identificar si el proyecto es favorito.")
	private Integer codigousuario;
	@NotNull
	@ApiModelProperty(value = "{Obligatorío} Longitud de las coordenas del usuario.")
	private BigDecimal longitud;
	@NotNull
	@ApiModelProperty(value = "{Obligatorío} Latitud de las coordenas del usuario.")
	private BigDecimal latitud;	
	@NotNull
	@ApiModelProperty(value = "{Obligatorío} DTO-FILTRO - Objeto que recibe parámetros de filtro para los proyectos")
	private FiltroProyectosHome filtroproyectos;
	
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

	public FiltroProyectosHome getFiltroproyectos() {
		return filtroproyectos;
	}

	public void setFiltroproyectos(FiltroProyectosHome filtroproyectos) {
		this.filtroproyectos = filtroproyectos;
	}
	
	

}
