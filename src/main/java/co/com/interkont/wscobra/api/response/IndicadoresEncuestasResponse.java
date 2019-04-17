package co.com.interkont.wscobra.api.response;



import java.math.BigDecimal;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DTO-RESPONSE Objeto de respuesta para los indicadores del esquema de encuetasue")
public class IndicadoresEncuestasResponse {

	@ApiModelProperty(value="Nombre del indicador del esquema de encuetasue.")
    private String indicador;
	@ApiModelProperty(value="Tipo de indicador del esquema de encuetasue.")
    private Integer tipofuente;
	@ApiModelProperty(value="Código de indicador del esquema de encuetasue.")
    private String codigo;
	@ApiModelProperty(value="Descripción del indicador del esquema de encuetasue.")
    private String descripcion;
	@ApiModelProperty(value="Logo del indicador del esquema de encuetasue.")
    private String logomobile;
	@ApiModelProperty(value="Logo del indicador del esquema de encuetasue.")
	private BigDecimal numInd;
	@ApiModelProperty(value="Logo del indicador del esquema de encuetasue.")
    private BigDecimal denInd;
	@ApiModelProperty(value="Valor del indicador del esquema de encuetasue.")
    private BigDecimal ind;
	@ApiModelProperty(value="Valor base del indicador del esquema de encuetasue.")
    private BigDecimal baseInd;
	@ApiModelProperty(value="Valor esperado del indicador del esquema de encuetasue.")
    private BigDecimal esperadoInd;
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public Integer getTipofuente() {
		return tipofuente;
	}
	public void setTipofuente(Integer tipofuente) {
		this.tipofuente = tipofuente;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLogomobile() {
		return logomobile;
	}
	public void setLogomobile(String logomobile) {
		this.logomobile = logomobile;
	}
	public BigDecimal getNumInd() {
		return numInd;
	}
	public void setNumInd(BigDecimal numInd) {
		this.numInd = numInd;
	}
	public BigDecimal getDenInd() {
		return denInd;
	}
	public void setDenInd(BigDecimal denInd) {
		this.denInd = denInd;
	}
	public BigDecimal getInd() {
		return ind;
	}
	public void setInd(BigDecimal ind) {
		this.ind = ind;
	}
	public BigDecimal getBaseInd() {
		return baseInd;
	}
	public void setBaseInd(BigDecimal baseInd) {
		this.baseInd = baseInd;
	}
	public BigDecimal getEsperadoInd() {
		return esperadoInd;
	}
	public void setEsperadoInd(BigDecimal esperadoInd) {
		this.esperadoInd = esperadoInd;
	}
	
	

}
