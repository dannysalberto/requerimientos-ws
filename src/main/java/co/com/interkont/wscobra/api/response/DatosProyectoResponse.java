package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import co.com.interkont.wscobra.api.response.ImagenesProyectoResponse;
import co.com.interkont.wscobra.api.response.IndicadoresResultadosResponse;
import co.com.interkont.wscobra.api.response.ContratistasProyectoResponse;


@ApiModel("DTO-RESPONSE Datos del proyecto")
public class DatosProyectoResponse {

	@ApiModelProperty(value = "Código del proyecto u/o obra.")
    private Integer codigoproyecto;
	@ApiModelProperty(value = "Nombre del proyecto u/o obra.")
    private String nombreproyecto;
	@ApiModelProperty(value = "Imagen del proyecto u/o obra.")
    private String imagenproyecto;
	@ApiModelProperty(value = "Objeto del proyecto u/o obra.")
    private String objetoproyecto;
	@ApiModelProperty(value = "Fecha de inicio del proyecto u/o obra.")
    private Date fechainicioproyecto;
	@ApiModelProperty(value = "Fecha de Fin del proyecto u/o obra.")
    private Date fechafinproyecto;
	@ApiModelProperty(value = "Duración en días del proyecto u/o obra.")
    private Integer duracionproyecto;
	@ApiModelProperty(value = "Valor del proyecto u/o obra.")
    private String valorproyecto;
	@ApiModelProperty(value = "Porcentaje de proyecto u/o obra.")
    private String avanceproyecto;
	@ApiModelProperty(value = "Semaforo del proyecto u/o obra.")
    private String semaforoproyecto;
	@ApiModelProperty(value = "Localidad u/o municipio del proyecto u/o obra.")
    private String localidadproyecto;
	@ApiModelProperty(value = "Latitud del proyecto u/o obra.")
    private BigDecimal latitudproyecto;
	@ApiModelProperty(value = "Longitud del proyecto u/o obra.")
    private BigDecimal longitudproyecto;
	@ApiModelProperty(value = "Código de la categoría, línea tematíca u/o línea de negocío")
    private Integer codigocategoria;
	@ApiModelProperty(value = "Nombre de la categoría, línea tematíca u/o línea de negocío")
    private String colorcategoria;
	@ApiModelProperty(value = "Imagen de la categoría, línea tematíca u/o línea de negocío")
    private String imagencategoria;
	@ApiModelProperty(value = "Nombre de la categoría, línea tematíca u/o línea de negocío")
    private String nombrecategoria;
	@ApiModelProperty(value = "Estado del proyecto u/o obra.")
    private String estadoproyecto;
	@ApiModelProperty(value = "Logo estado del proyecto u/o obra.")
    private String logoestadoproyecto;
	@ApiModelProperty(value = "Imagenes del proyecto u/o obra.")
    private List<ImagenesProyectoResponse> imagenesproyecto;
	@ApiModelProperty(value = "Distancia del usuario frente al proyecto u/o obra.")
	private String distancia;
	@ApiModelProperty(value="Indicadores resultados asociados al proyecto")
	private List<IndicadoresResultadosResponse> indicadoresresultadosproyecto;
	@ApiModelProperty(value="Contratistas asociados al proyecto")
	private List<ContratistasProyectoResponse> contratistasproyecto;

	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}

	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}

	public String getNombreproyecto() {
		return nombreproyecto;
	}

	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}

	public String getImagenproyecto() {
		return imagenproyecto;
	}

	public void setImagenproyecto(String imagenproyecto) {
		this.imagenproyecto = imagenproyecto;
	}

	public String getObjetoproyecto() {
		return objetoproyecto;
	}

	public void setObjetoproyecto(String objetoproyecto) {
		this.objetoproyecto = objetoproyecto;
	}

	public Date getFechainicioproyecto() {
		return fechainicioproyecto;
	}

	public void setFechainicioproyecto(Date fechainicioproyecto) {
		this.fechainicioproyecto = fechainicioproyecto;
	}

	public Date getFechafinproyecto() {
		return fechafinproyecto;
	}

	public void setFechafinproyecto(Date fechafinproyecto) {
		this.fechafinproyecto = fechafinproyecto;
	}

	public Integer getDuracionproyecto() {
		return duracionproyecto;
	}

	public void setDuracionproyecto(Integer duracionproyecto) {
		this.duracionproyecto = duracionproyecto;
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

	public String getLocalidadproyecto() {
		return localidadproyecto;
	}

	public void setLocalidadproyecto(String localidadproyecto) {
		this.localidadproyecto = localidadproyecto;
	}

	public BigDecimal getLatitudproyecto() {
		return latitudproyecto;
	}

	public void setLatitudproyecto(BigDecimal latitudproyecto) {
		this.latitudproyecto = latitudproyecto;
	}

	public BigDecimal getLongitudproyecto() {
		return longitudproyecto;
	}

	public void setLongitudproyecto(BigDecimal longitudproyecto) {
		this.longitudproyecto = longitudproyecto;
	}

	public Integer getCodigocategoria() {
		return codigocategoria;
	}

	public void setCodigocategoria(Integer codigocategoria) {
		this.codigocategoria = codigocategoria;
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

	public String getEstadoproyecto() {
		return estadoproyecto;
	}

	public void setEstadoproyecto(String estadoproyecto) {
		this.estadoproyecto = estadoproyecto;
	}

	public String getLogoestadoproyecto() {
		return logoestadoproyecto;
	}

	public void setLogoestadoproyecto(String logoestadoproyecto) {
		this.logoestadoproyecto = logoestadoproyecto;
	}

	public List<ImagenesProyectoResponse> getImagenesproyecto() {
		return imagenesproyecto;
	}

	public void setImagenesproyecto(List<ImagenesProyectoResponse> imagenesproyecto) {
		this.imagenesproyecto = imagenesproyecto;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public List<IndicadoresResultadosResponse> getIndicadoresresultadosproyecto() {
		return indicadoresresultadosproyecto;
	}

	public void setIndicadoresresultadosproyecto(List<IndicadoresResultadosResponse> indicadoresresultadosproyecto) {
		this.indicadoresresultadosproyecto = indicadoresresultadosproyecto;
	}

	public List<ContratistasProyectoResponse> getContratistasproyecto() {
		return contratistasproyecto;
	}

	public void setContratistasproyecto(List<ContratistasProyectoResponse> contratistasproyecto) {
		this.contratistasproyecto = contratistasproyecto;
	}
    
	
}
