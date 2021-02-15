package co.com.interkont.wsmiobra.models;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObraUpdRequest{
	
	private Integer id;
	
	@Column(name="datefeciniobra")
	private String fechaInicio; 
	
	@Column(name="datefecfinobra")
	private String fechaFin;
	
	@Column(name="intidperiomedida")
	private Integer periodoMedida;
	
	@Column(name="intplazoobra")
	private Integer plazoObra;
	
	@Column(name="boolincluyeaiu")
	private boolean incluyeAiu;

	@Column(name="floatporadmon",nullable=false)
	private Float porAdmon;
	
	@Column(name="floatporimprevi",nullable=false)
	private Float porImprevi; //por imprevisto
	
	@Column(name="floatporutilidad",nullable=false)
	private Float porUtilidad;
	
	
	@Column(name="floatporivasobreutil",nullable=false)
	private Float porIvaSobreUtil;

	@Column(name="floatporotros", nullable=false)
	private Float porOtros;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getPeriodoMedida() {
		return periodoMedida;
	}

	public void setPeriodoMedida(Integer periodoMedida) {
		this.periodoMedida = periodoMedida;
	}

	public Integer getPlazoObra() {
		return plazoObra;
	}

	public void setPlazoObra(Integer plazoObra) {
		this.plazoObra = plazoObra;
	}

	public boolean isIncluyeAiu() {
		return incluyeAiu;
	}

	public void setIncluyeAiu(boolean incluyeAiu) {
		this.incluyeAiu = incluyeAiu;
	}

	public Float getPorAdmon() {
		return porAdmon;
	}

	public void setPorAdmon(Float porAdmon) {
		this.porAdmon = porAdmon;
	}

	public Float getPorImprevi() {
		return porImprevi;
	}

	public void setPorImprevi(Float porImprevi) {
		this.porImprevi = porImprevi;
	}

	public Float getPorUtilidad() {
		return porUtilidad;
	}

	public void setPorUtilidad(Float porUtilidad) {
		this.porUtilidad = porUtilidad;
	}

	public Float getPorIvaSobreUtil() {
		return porIvaSobreUtil;
	}

	public void setPorIvaSobreUtil(Float porIvaSobreUtil) {
		this.porIvaSobreUtil = porIvaSobreUtil;
	}

	public Float getPorOtros() {
		return porOtros;
	}

	public void setPorOtros(Float porOtros) {
		this.porOtros = porOtros;
	}

	@Override
	public String toString() {
		return "ObraUpdRequest [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", periodoMedida=" + periodoMedida + ", plazoObra=" + plazoObra + ", incluyeAiu=" + incluyeAiu
				+ ", porAdmon=" + porAdmon + ", porImprevi=" + porImprevi + ", porUtilidad=" + porUtilidad
				+ ", porIvaSobreUtil=" + porIvaSobreUtil + ", porOtros=" + porOtros + "]";
	}

	
  	
}
