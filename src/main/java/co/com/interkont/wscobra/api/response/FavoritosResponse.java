package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE lista de mis proyectos favoritos")
public class FavoritosResponse {
	
	@ApiModelProperty(value = "Código del proyecto u/o obra")
    private Integer codigoproyecto;
	@ApiModelProperty(value = "Color de la categoría u/o línea de negocio")
    private String colorcategoria;
	@ApiModelProperty(value = "Imagen de la categoría u/o línea de negocio")
    private String imagencategoria;
	@ApiModelProperty(value = "Nombre de la categoría u/o línea de negocio")
    private String nombrecategoria;
	@ApiModelProperty(value = "Nombre del proyecto u/o obra")
    private String nombreproyecto;
	@ApiModelProperty(value = "Distancía del usuario al proyecto u/o obra")
    private String distaciaproyecto;
	@ApiModelProperty(value = "Costo del proyecto u/o obra, en fortato de miles")
    private String valorproyecto;
	@ApiModelProperty(value = "Porcentaje de avance del proyecto u/o obra")
    private String avanceproyecto;
	@ApiModelProperty(value = "Imagen de semaforo del proyecto u/o obra")
    private String semaforoproyecto;
	@ApiModelProperty(value = "Imagen de favorito del proyecto u/o obra")
    private String favorito;
	@ApiModelProperty(value = "Código del usuario asociado al registro")
	private Integer codigousuario;
	@ApiModelProperty(value = "Coordenada de longitud del proyecto u/o obra")
    private BigDecimal latitud;
	@ApiModelProperty(value = "Coordenada de latitud del proyecto u/o obra")
    private BigDecimal longitud;


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

	public String getFavorito() {
		return favorito;
	}

	public void setFavorito(String favorito) {
		this.favorito = favorito;
	}

    public Integer getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Integer codigousuario) {
        this.codigousuario = codigousuario;
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

    
    
}
