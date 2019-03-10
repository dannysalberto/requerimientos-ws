package co.com.interkont.wscobra.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DTO-FILTRO - Objeto que sirve para filtrar la búsqueda de proyectos  ")
public class FiltroProyectosHome {
	@NotNull
	@ApiModelProperty(value = "Nombre del proyecto u/o obra, por el cúal se va ha buscar.")
	private String nombreproyecto;
	@NotNull
	@ApiModelProperty(value = "Código de la categoría del proyecto u/o obra, por el cúal se va ha buscar.")
	private int categoriaproyecto;

	public String getNombreproyecto() {
		return nombreproyecto;
	}

	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}

	public int getCategoriaproyecto() {
		return categoriaproyecto;
	}

	public void setCategoriaproyecto(int categoriaproyecto) {
		this.categoriaproyecto = categoriaproyecto;
	}	
	
	

}
