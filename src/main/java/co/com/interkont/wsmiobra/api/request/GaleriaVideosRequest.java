package co.com.interkont.wsmiobra.api.request;

import io.swagger.annotations.ApiModelProperty;



public class GaleriaVideosRequest {
	
	@ApiModelProperty(value = "Id de la obra")
	private Integer obra_id;
	
	@ApiModelProperty(value = "Tipo de video: Cámara/Complementario")
	private Integer tipovideo;
	
	@ApiModelProperty(value = "Etiqueta descriptiva del video")
	private String nombre;

	@ApiModelProperty(value = "Url del video a incrustar")
	private String url;

	public Integer getObra_id() {
		return obra_id;
	}

	public void setObra_id(Integer obra_id) {
		this.obra_id = obra_id;
	}

	public Integer getTipovideo() {
		return tipovideo;
	}

	public void setTipovideo(Integer tipovideo) {
		this.tipovideo = tipovideo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "GaleriaVideos [obra_id=" + obra_id + ", tipovideo=" + tipovideo + ", nombre=" + nombre + ", url=" + url
				+ "]";
	}
	
	
	
}
