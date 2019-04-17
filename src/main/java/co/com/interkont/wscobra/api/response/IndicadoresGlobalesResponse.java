package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("DTO-RESPONSE Indicadores Globales Proyectos")
public class IndicadoresGlobalesResponse {
	
	@ApiModelProperty(value = "Suma total de costo de proyectos u/o obras.")
	private BigDecimal totalvalorproyectos;
	
	@ApiModelProperty(value = "Logo total de costo de proyectos u/o obras.")
	private String logototalvalorproyectos;
	
	@ApiModelProperty(value = "Suma total de valor ejecutado de proyectos u/o obras.")
	private BigDecimal totalvalorejecutadoproyectos;
	
	@ApiModelProperty(value = "Logo total de valor ejecutado de proyectos u/o obras.")
	private String logototalvalorejecutadoproyectos;
	
	@ApiModelProperty(value = "Suma total del avance de los proyectos u/o obras.")	
	private BigDecimal totalavanceproyectos;
	
	@ApiModelProperty(value = "Logo total del avance de los proyectos u/o obras.")
	private String logototalavanceproyectos;
	
	@ApiModelProperty(value = "Suma total de empleos directos generados por el proyectos u/o obras.")
	private BigDecimal totalempleosdirectos;
	
	@ApiModelProperty(value = "Logo total de empleos directos generados por el proyectos u/o obras.")
	private String logototalempleosdirectos;
	
	@ApiModelProperty(value = "Suma total de empleos indirectos generados por el proyectos u/o obras.")
	private BigDecimal totalempleosindirectos;
	
	@ApiModelProperty(value = "Logo total de empleos directos generados por el proyectos u/o obras.")
	private String logototalempleosindirectos;
	
	@ApiModelProperty(value = "Suma total de habitantes beneficiados por el proyectos u/o obras.")
	private BigDecimal totalhabitantesbeneficiados;
	
	@ApiModelProperty(value = "Logo total de habitantes beneficiados por el proyectos u/o obras.")
	private String logototalhabitantesbeneficiados;	
	
	@ApiModelProperty(value = "Suma cantidad de proyectos u/o obras.")
	private BigDecimal totalproyectos;
	
	@ApiModelProperty(value = "Logo cantidad de proyectos u/o obras.")
	private String logototalproyectos;
	
	@ApiModelProperty(value = "Suma cantidad de proyectos u/o obras en estado de ejecución.")
	private BigDecimal totalproyectosejecucion;
	
	@ApiModelProperty(value = "Log cantidad de proyectos u/o obras en estado de ejecución.")
	private String logototalproyectosejecucion;
	
	@ApiModelProperty(value = "Suma cantidad de proyectos u/o obras en estado por iniciar.")
	private BigDecimal totalproyectosiniciar;
	
	@ApiModelProperty(value = "Logo cantidad de proyectos u/o obras en estado por iniciar.")
	private String logototalproyectosiniciar;
	
	@ApiModelProperty(value = "Suma cantidad de proyectos u/o obras en estado terminados")
	private BigDecimal totalproyectosterminados;
	
	@ApiModelProperty(value = "Logo cantidad de proyectos u/o obras en estado terminados")
	private String logototalproyectosterminados;
	
	@ApiModelProperty(value = "Código de la categoría, línea tematíca u/o línea de negocío")
	private BigDecimal codigocategoria;
	
	@ApiModelProperty(value = "Nombre de la categoría, línea tematíca u/o línea de negocío")
	private String nombrecategoria;
	
	@ApiModelProperty(value = "Color de la categoría, línea tematíca u/o línea de negocío")
	private String colorcategoria;
	
	@ApiModelProperty(value = "Botón activo de la categoría, línea tematíca u/o línea de negocío")
	private String botoncategoriainactivo;
	
	@ApiModelProperty(value = "Botón inactivo de la categoría, línea tematíca u/o línea de negocío")
	private String botoncategoriaactivo;

	public BigDecimal getTotalvalorproyectos() {
		return totalvalorproyectos;
	}

	public void setTotalvalorproyectos(BigDecimal totalvalorproyectos) {
		this.totalvalorproyectos = totalvalorproyectos;
	}

	public String getLogototalvalorproyectos() {
		return logototalvalorproyectos;
	}

	public void setLogototalvalorproyectos(String logototalvalorproyectos) {
		this.logototalvalorproyectos = logototalvalorproyectos;
	}

	public BigDecimal getTotalvalorejecutadoproyectos() {
		return totalvalorejecutadoproyectos;
	}

	public void setTotalvalorejecutadoproyectos(BigDecimal totalvalorejecutadoproyectos) {
		this.totalvalorejecutadoproyectos = totalvalorejecutadoproyectos;
	}

	public String getLogototalvalorejecutadoproyectos() {
		return logototalvalorejecutadoproyectos;
	}

	public void setLogototalvalorejecutadoproyectos(String logototalvalorejecutadoproyectos) {
		this.logototalvalorejecutadoproyectos = logototalvalorejecutadoproyectos;
	}

	public BigDecimal getTotalavanceproyectos() {
		return totalavanceproyectos;
	}

	public void setTotalavanceproyectos(BigDecimal totalavanceproyectos) {
		this.totalavanceproyectos = totalavanceproyectos;
	}

	public String getLogototalavanceproyectos() {
		return logototalavanceproyectos;
	}

	public void setLogototalavanceproyectos(String logototalavanceproyectos) {
		this.logototalavanceproyectos = logototalavanceproyectos;
	}

	public BigDecimal getTotalempleosdirectos() {
		return totalempleosdirectos;
	}

	public void setTotalempleosdirectos(BigDecimal totalempleosdirectos) {
		this.totalempleosdirectos = totalempleosdirectos;
	}

	public String getLogototalempleosdirectos() {
		return logototalempleosdirectos;
	}

	public void setLogototalempleosdirectos(String logototalempleosdirectos) {
		this.logototalempleosdirectos = logototalempleosdirectos;
	}

	public BigDecimal getTotalempleosindirectos() {
		return totalempleosindirectos;
	}

	public void setTotalempleosindirectos(BigDecimal totalempleosindirectos) {
		this.totalempleosindirectos = totalempleosindirectos;
	}

	public String getLogototalempleosindirectos() {
		return logototalempleosindirectos;
	}

	public void setLogototalempleosindirectos(String logototalempleosindirectos) {
		this.logototalempleosindirectos = logototalempleosindirectos;
	}

	public BigDecimal getTotalhabitantesbeneficiados() {
		return totalhabitantesbeneficiados;
	}

	public void setTotalhabitantesbeneficiados(BigDecimal totalhabitantesbeneficiados) {
		this.totalhabitantesbeneficiados = totalhabitantesbeneficiados;
	}

	public String getLogototalhabitantesbeneficiados() {
		return logototalhabitantesbeneficiados;
	}

	public void setLogototalhabitantesbeneficiados(String logototalhabitantesbeneficiados) {
		this.logototalhabitantesbeneficiados = logototalhabitantesbeneficiados;
	}

	public BigDecimal getTotalproyectos() {
		return totalproyectos;
	}

	public void setTotalproyectos(BigDecimal totalproyectos) {
		this.totalproyectos = totalproyectos;
	}

	public String getLogototalproyectos() {
		return logototalproyectos;
	}

	public void setLogototalproyectos(String logototalproyectos) {
		this.logototalproyectos = logototalproyectos;
	}

	public BigDecimal getTotalproyectosejecucion() {
		return totalproyectosejecucion;
	}

	public void setTotalproyectosejecucion(BigDecimal totalproyectosejecucion) {
		this.totalproyectosejecucion = totalproyectosejecucion;
	}

	public String getLogototalproyectosejecucion() {
		return logototalproyectosejecucion;
	}

	public void setLogototalproyectosejecucion(String logototalproyectosejecucion) {
		this.logototalproyectosejecucion = logototalproyectosejecucion;
	}

	public BigDecimal getTotalproyectosiniciar() {
		return totalproyectosiniciar;
	}

	public void setTotalproyectosiniciar(BigDecimal totalproyectosiniciar) {
		this.totalproyectosiniciar = totalproyectosiniciar;
	}

	public String getLogototalproyectosiniciar() {
		return logototalproyectosiniciar;
	}

	public void setLogototalproyectosiniciar(String logototalproyectosiniciar) {
		this.logototalproyectosiniciar = logototalproyectosiniciar;
	}

	public BigDecimal getTotalproyectosterminados() {
		return totalproyectosterminados;
	}

	public void setTotalproyectosterminados(BigDecimal totalproyectosterminados) {
		this.totalproyectosterminados = totalproyectosterminados;
	}

	public String getLogototalproyectosterminados() {
		return logototalproyectosterminados;
	}

	public void setLogototalproyectosterminados(String logototalproyectosterminados) {
		this.logototalproyectosterminados = logototalproyectosterminados;
	}

	public BigDecimal getCodigocategoria() {
		return codigocategoria;
	}

	public void setCodigocategoria(BigDecimal codigocategoria) {
		this.codigocategoria = codigocategoria;
	}

	public String getNombrecategoria() {
		return nombrecategoria;
	}

	public void setNombrecategoria(String nombrecategoria) {
		this.nombrecategoria = nombrecategoria;
	}

	public String getColorcategoria() {
		return colorcategoria;
	}

	public void setColorcategoria(String colorcategoria) {
		this.colorcategoria = colorcategoria;
	}

	public String getBotoncategoriainactivo() {
		return botoncategoriainactivo;
	}

	public void setBotoncategoriainactivo(String botoncategoriainactivo) {
		this.botoncategoriainactivo = botoncategoriainactivo;
	}

	public String getBotoncategoriaactivo() {
		return botoncategoriaactivo;
	}

	public void setBotoncategoriaactivo(String botoncategoriaactivo) {
		this.botoncategoriaactivo = botoncategoriaactivo;
	}
	
	

}
