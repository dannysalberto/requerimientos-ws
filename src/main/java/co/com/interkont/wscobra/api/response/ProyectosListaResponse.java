package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE vista lista de proyectos")
public class ProyectosListaResponse {
	
	@ApiModelProperty(value = "Código del proyecto u/o obra")
    private Integer codigoproyecto;
	@ApiModelProperty(value = "Nombre del proyecto u/o obra")
	private String nombreproyecto;
	@ApiModelProperty(value = "Imagen del proyecto u/o obra")
    private String imagenproyecto;
	@ApiModelProperty(value = "Costo del proyecto u/o obra, en fortato de miles")
    private String valorproyecto;
	@ApiModelProperty(value = "Porcentaje de avance del proyecto u/o obra")
    private String avanceproyecto;
	@ApiModelProperty(value = "Imagen de semaforo del proyecto u/o obra")
    private String semaforoproyecto;
	@ApiModelProperty(value = "Coordenada de latitud del proyecto u/o obra")
    private BigDecimal latitud;
	@ApiModelProperty(value = "Coordenada de longitud del proyecto u/o obra")
    private BigDecimal longitud;
	@ApiModelProperty(value = "Código de la categoría u/o línea de negocio")
    private Integer codigocategoria;
	@ApiModelProperty(value = "Color de la categoría u/o línea de negocio")
    private String colorcategoria;
	@ApiModelProperty(value = "Imagen de la categoría u/o línea de negocio")
    private String imagencategoria;
	@ApiModelProperty(value = "Nombre de la categoría u/o línea de negocio")
    private String nombrecategoria;
	@ApiModelProperty(value = "Estado del proyecto u/o obra")
    private String estadoproyecto;
	@ApiModelProperty(value = "Distancía del usuario al proyecto u/o obra")
    private String distaciaproyecto;
	
	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}

	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}

	public String getColorcategoria() {
		return colorcategoria;
	}

	public void setColorcategoria(String colorcategoria) {
		this.colorcategoria = colorcategoria;
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

	public String getDistaciaproyecto() {
		return distaciaproyecto;
	}

	public void setDistaciaproyecto(String distaciaproyecto) {
		this.distaciaproyecto = distaciaproyecto;
	}

	public String getValorproyecto() {
		return valorproyecto;
	}

	public void setValorproyecto(String valorproyecto) {
		this.valorproyecto = valorproyecto;
	}

	public String getAvanceproyecto() {
		return avanceproyecto;
	}

	public void setAvanceproyecto(String avanceproyecto) {
		this.avanceproyecto = avanceproyecto;
	}

	public String getSemaforoproyecto() {
		return semaforoproyecto;
	}

	public void setSemaforoproyecto(String semaforoproyecto) {
		this.semaforoproyecto = semaforoproyecto;
	}

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

	public String getImagenproyecto() {
		return imagenproyecto;
	}

	public void setImagenproyecto(String imagenproyecto) {
		this.imagenproyecto = imagenproyecto;
	}

	public Integer getCodigocategoria() {
		return codigocategoria;
	}

	public void setCodigocategoria(Integer codigocategoria) {
		this.codigocategoria = codigocategoria;
	}

	public String getEstadoproyecto() {
		return estadoproyecto;
	}

	public void setEstadoproyecto(String estadoproyecto) {
		this.estadoproyecto = estadoproyecto;
	}

    
    
}
