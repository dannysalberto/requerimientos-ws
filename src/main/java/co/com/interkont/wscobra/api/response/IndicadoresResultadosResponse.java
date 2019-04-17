package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DTO-ASOCIATE Indicadores resultados asociados al proyecto")
public class IndicadoresResultadosResponse {
	
	
	@ApiModelProperty(value = "Id del indicador de resultado del proyecto u/o obra.")
	private Integer idindicador;
	@ApiModelProperty(value= "Nombre del indicador de resultado")
    private String nombre;
	@ApiModelProperty(value= "Avance del indicador de resultado")
    private BigDecimal avanceresultado;
	@ApiModelProperty(value= "Peso ponderado del indicador de resultado")
    private BigDecimal peso;
	@ApiModelProperty(value= "Porcentaje de avance del indicador de resultado")
    private BigDecimal porcentaje;
	public Integer getIdindicador() {
		return idindicador;
	}
	public void setIdindicador(Integer idindicador) {
		this.idindicador = idindicador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getAvanceresultado() {
		return avanceresultado;
	}
	public void setAvanceresultado(BigDecimal avanceresultado) {
		this.avanceresultado = avanceresultado.multiply(new BigDecimal(100));
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public BigDecimal getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje.multiply(new BigDecimal(100));
	}
	
}
