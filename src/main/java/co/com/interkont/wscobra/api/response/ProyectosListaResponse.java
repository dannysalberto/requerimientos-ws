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
	@ApiModelProperty(value = "porcentaje en el que debería ir el proyecto")
	private Double porcentajeProyectado;
	@ApiModelProperty(value = "Nombre del color del semaforo del proyecto (verde, amarillo, rojo)")
    private String semaforoproyecto;
	@ApiModelProperty(value = "Código de la categoría u/o línea de negocio")
    private Integer codigocategoria;
	@ApiModelProperty(value = "Imagen de la categoría u/o línea de negocio")
    private String imagencategoria;
	@ApiModelProperty(value = "Color de la categoría u/o línea de negocio")
    private String colorcategoria;
	@ApiModelProperty(value = "Nombre de la categoría u/o línea de negocio")
    private String nombrecategoria;
	@ApiModelProperty(value = "Objeto del proyecto")
	private String objeto;
	@ApiModelProperty(value = "Contratista del contrato de tipo ejecución")
	private String contratista;
	@ApiModelProperty(value = "Indica si el proyecto tiene alimentaciones pendientes de aprobación por el supervisor")
	private Boolean pendienteAprobacion;
	
	
	
	public ProyectosListaResponse() {
		/*mock*/objeto="Estudios, diseños y construcción de centros de integración ciudadana cic, grupo 1 - región paciﬁco de colombia";
		/*mock*/contratista="Arqueada SAS";
		/*mock*/pendienteAprobacion = false;
		/*mock*/porcentajeProyectado = 67.0;
	}

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

	public String getColorcategoria() {
		return colorcategoria;
	}

	public void setColorcategoria(String colorcategoria) {
		this.colorcategoria = colorcategoria;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getContratista() {
		return contratista;
	}

	public void setContratista(String contratista) {
		this.contratista = contratista;
	}

	public Boolean getPendienteAprobacion() {
		return pendienteAprobacion;
	}

	public void setPendienteAprobacion(Boolean pendienteAprobacion) {
		this.pendienteAprobacion = pendienteAprobacion;
	}

	public Double getPorcentajeProyectado() {
		return porcentajeProyectado;
	}

	public void setPorcentajeProyectado(Double porcentajeProyectado) {
		this.porcentajeProyectado = porcentajeProyectado;
	}
}
