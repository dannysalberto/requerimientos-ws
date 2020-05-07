package co.com.interkont.wscobra.api.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("AlimentacionRequest DTO-REQUEST (Objeto para el request de una actividad)")
public class AlimentacionRequest {

	@NotNull
	@ApiModelProperty(value = "Código del proyecto")
    private Integer codigoproyecto;
	@ApiModelProperty(value = "Código del periodo de alimentación")
    private Integer periodoId;
	@ApiModelProperty(value = "Usuario que realiza la alimnetación del proyecto")
    private String usuario;
	@ApiModelProperty(value = "Descripción de la alimnetación del proyecto")
    private String descripcion;
	@NotNull
	@ApiModelProperty(value = "Imagen principal  de la alimnetación del proyecto")
    private ImagenRequest fotoPrincipal;
	@NotNull
	@ApiModelProperty(value = "Listado de actividades de la alimnetación del proyecto")
    private List<ActividadRequest> actividades;	
	@ApiModelProperty(value = "Listado de ascpectos a evaluar de la alimnetación del proyecto")
    private List<AspectosEvaluarRequest> aspectosEvaluar;
	@ApiModelProperty(value = "Listado de factores de atraso de la alimnetación del proyecto")
    private List<FactoresAtrasoRequest> factoresAtraso;
	@NotNull
	@ApiModelProperty(value = "Listado de indicadores de alcance de la alimnetación del proyecto")
    private List<IndicadorAlcanceRequest> indicadoresAlcance;
	@ApiModelProperty(value = "Listado de imagenes de la alimnetación del proyecto")
    private List<ImagenRequest> imagenesComplementarias;

	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}
	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ImagenRequest getFotoPrincipal() {
		return fotoPrincipal;
	}
	public void setFotoPrincipal(ImagenRequest fotoPrincipal) {
		this.fotoPrincipal = fotoPrincipal;
	}
	public List<ActividadRequest> getActividades() {
		return actividades;
	}
	public void setActividades(List<ActividadRequest> actividades) {
		this.actividades = actividades;
	}
	public List<AspectosEvaluarRequest> getAspectosEvaluar() {
		return aspectosEvaluar;
	}
	public void setAspectosEvaluar(List<AspectosEvaluarRequest> aspectosEvaluar) {
		this.aspectosEvaluar = aspectosEvaluar;
	}
	public List<FactoresAtrasoRequest> getFactoresAtraso() {
		return factoresAtraso;
	}
	public void setFactoresAtraso(List<FactoresAtrasoRequest> factoresAtraso) {
		this.factoresAtraso = factoresAtraso;
	}
	public List<IndicadorAlcanceRequest> getIndicadoresAlcance() {
		return indicadoresAlcance;
	}
	public void setIndicadoresAlcance(List<IndicadorAlcanceRequest> indicadoresAlcance) {
		this.indicadoresAlcance = indicadoresAlcance;
	}
	public List<ImagenRequest> getImagenesComplementarias() {
		return imagenesComplementarias;
	}
	public void setImagenesComplementarias(List<ImagenRequest> imagenesComplementarias) {
		this.imagenesComplementarias = imagenesComplementarias;
	}
	public Integer getPeriodoId() {
		return periodoId;
	}
	public void setPeriodoId(Integer periodoId) {
		this.periodoId = periodoId;
	}
	
}
