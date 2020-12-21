package co.com.interkont.wscobra.api.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(schema="public",name="obra")
public class ObraResponse {
	
	@Id
	@Column(name="intcodigoobra")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="datefeciniobra", columnDefinition="DATE")
	private Date fechaInicio; 

	@Column(name="datefecfinobra",columnDefinition="DATE")
	private Date fechaFin; 

	@Column(name="intplazoobra")
	private Integer plazoObra;
	
	@Column(name="intidperiomedida")
	private Integer periodoMedida;

	@Column(name="floatporadmon",nullable=false)
	private Float porAdmon;
	
	@Column(name="floatporimprevi",nullable=false)
	private Float porImprevi; //por imprevisto
	
	@Column(name="floatporutilidad",nullable=false)
	private Float porUtilidad;
	
	@Column(name="floatporivasobreutil",nullable=false)
	private Float porIvaSobreUtil;
	
	@Column(name="floatporotros")
	private Float porOtros; 
	
	@Column(name="numvaltotobra",  columnDefinition="numeric(20,6)")
	private BigDecimal valTotalObra;
	
	@Column(name="numvalejecobra", columnDefinition="NUMERIC DEFAULT 0")
	private BigDecimal valEjecucionObra; 
	  
	@JsonIgnore
	@OneToMany(mappedBy="obra",cascade=CascadeType.ALL)
	private List<ActividadObraResponse> actividadObra;
	
	@Column(name="costo_directo", columnDefinition="numeric(20,6)")
	private BigDecimal costo_directo; //sumatoria de las actividades sin aiu

	@JsonProperty(value="incluyeaiu")
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
		if (boolincluyeaiu==null) {
			return false;
		}
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
