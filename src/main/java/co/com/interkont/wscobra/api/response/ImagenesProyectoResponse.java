package co.com.interkont.wscobra.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DTO-ASOCIATE Imágenes asociadas al detalle del proyecto")
public class ImagenesProyectoResponse {
	
	@ApiModelProperty(value = "Id de la imagen del proyecto u/o obra.")
	private Integer intidimagen;
	@ApiModelProperty(value = "Nombre de la imagen del proyecto u/o obra.")
	private String strnombre;    
	@ApiModelProperty(value = "URL de la imagen del proyecto u/o obra.")
    private String strubicacion;

	public Integer getIntidimagen() {
		return intidimagen;
	}

	public void setIntidimagen(Integer intidimagen) {
		this.intidimagen = intidimagen;
	}

	public String getStrnombre() {
		return strnombre;
	}

	public void setStrnombre(String strnombre) {
		this.strnombre = strnombre;
	}

	public String getStrubicacion() {
		return strubicacion;
	}

	public void setStrubicacion(String strubicacion) {
		this.strubicacion = strubicacion;
	}    

    
    
}
