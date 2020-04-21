package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE vista lista de proyectos")
public class ProyectosListaResponse {
	
	@ApiModelProperty(value = "Código del proyecto")
    private Integer codigoproyecto;
	@ApiModelProperty(value = "Nombre del proyecto")
	private String nombreproyecto;
	@ApiModelProperty(value = "Costo del proyecto")
    private BigDecimal valorproyecto;
	@ApiModelProperty(value = "Valor ejecutado del proyecto (Avance)")
    private BigDecimal valorejecutado;
	@ApiModelProperty(value = "Nombre del color del semaforo del proyecto (verde, amarillo, rojo)")
    private String semaforoproyecto;
	@ApiModelProperty(value = "Código de la categoría u/o línea de negocio")
    private Integer codigocategoria;
	@ApiModelProperty(value = "Imagen de la categoría u/o línea de negocio")
    private String imagencategoria;
	@ApiModelProperty(value = "Nombre de la categoría u/o línea de negocio")
    private String nombrecategoria;
	
	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}

	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}
	
	public String getImagencategoria() {
		return imagencategoria;
	}

	public void setImagencategoria(String imagencategoria) {
		this.imagencategoria = imagencategoria;
	}

	public String getNombrecategoria() {
		return nombrecategoria;
	}

	public void setNombrecategoria(String nombrecategoria) {
		this.nombrecategoria = nombrecategoria;
	}

	public String getNombreproyecto() {
		return nombreproyecto;
	}

	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}
	
	public String getSemaforoproyecto() {
		return semaforoproyecto;
	}

	public void setSemaforoproyecto(String semaforoproyecto) {
		this.semaforoproyecto = semaforoproyecto;
	}

	public Integer getCodigocategoria() {
		return codigocategoria;
	}

	public void setCodigocategoria(Integer codigocategoria) {
		this.codigocategoria = codigocategoria;
	}

	public BigDecimal getValorproyecto() {
		return valorproyecto;
	}

	public void setValorproyecto(BigDecimal valorproyecto) {
		this.valorproyecto = valorproyecto;
	}

	public BigDecimal getValorejecutado() {
		return valorejecutado;
	}

	public void setValorejecutado(BigDecimal valorejecutado) {
		this.valorejecutado = valorejecutado;
	}
	
}
