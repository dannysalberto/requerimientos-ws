package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ImagenRequest DTO-REQUEST (Objeto para el request de una imagen)")
public class ImagenRequest {

	@NotNull
	@ApiModelProperty(value = "Imagen en Base64")
	private String image;
	
	@ApiModelProperty(value = "Imagen del al imagen")
	private String nombre;
	
	@NotNull
	@ApiModelProperty(value = "Tipo de imagen(.png | .jpg | .jpge)")
	private String tipo;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ImagenRequest [image=" + image.substring(0, 10) + "..., nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
}
