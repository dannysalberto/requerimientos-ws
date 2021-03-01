package co.com.interkont.wsmiobra.api.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ObraResponse {
	
	private Integer id;
	
	private Date fechaInicio; 

	private Date fechaFin; 

	private Integer plazoObra;
	
	private Integer periodoMedida;

	private Float porAdmon;
	
	private Float porImprevi; //por imprevisto
	
	private Float porUtilidad;
	
	private Float porIvaSobreUtil;
	
	private Float porOtros; 
	
	private BigDecimal valTotalObra;
	
	private BigDecimal valEjecucionObra; 
	  
	@JsonIgnore
	@OneToMany(mappedBy="obra",cascade=CascadeType.ALL)
	private List<ActividadObraResponse> actividadObra;
	
	private BigDecimal costo_directo; //sumatoria de las actividades sin aiu

	@JsonProperty(value="incluyeAiu")
	private Boolean boolincluyeaiu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getPlazoObra() {
		return plazoObra;
	}

	public void setPlazoObra(Integer plazoObra) {
		this.plazoObra = plazoObra;
	}

	public Integer getPeriodoMedida() {
		return periodoMedida;
	}

	public void setPeriodoMedida(Integer periodoMedida) {
		this.periodoMedida = periodoMedida;
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

	public BigDecimal getValTotalObra() {
		return valTotalObra;
	}

	public void setValTotalObra(BigDecimal valTotalObra) {
		this.valTotalObra = valTotalObra;
	}

	public BigDecimal getValEjecucionObra() {
		return valEjecucionObra;
	}

	public void setValEjecucionObra(BigDecimal valEjecucionObra) {
		this.valEjecucionObra = valEjecucionObra;
	}

	public List<ActividadObraResponse> getActividadObra() {
		return actividadObra;
	}

	public void setActividadObra(List<ActividadObraResponse> actividadObra) {
		this.actividadObra = actividadObra;
	}

	public BigDecimal getCosto_directo() {
		return costo_directo;
	}

	public void setCosto_directo(BigDecimal costo_directo) {
		this.costo_directo = costo_directo;
	}





	public Boolean getBoolincluyeaiu() {
		return boolincluyeaiu;
	}

	public void setBoolincluyeaiu(Boolean boolincluyeaiu) {
		this.boolincluyeaiu = boolincluyeaiu;
	}

	@Override
	public String toString() {
		return "ObraResponse [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", plazoObra="
				+ plazoObra + ", periodoMedida=" + periodoMedida + ", porAdmon=" + porAdmon + ", porImprevi="
				+ porImprevi + ", porUtilidad=" + porUtilidad + ", porIvaSobreUtil=" + porIvaSobreUtil + ", porOtros="
				+ porOtros + ", valTotalObra=" + valTotalObra + ", valEjecucionObra=" + valEjecucionObra
				+ ", actividadObra=" + actividadObra + ", costo_directo=" + costo_directo + ", boolincluyeaiu="
				+ boolincluyeaiu + "]";
	}

	

	
}
