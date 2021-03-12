package co.com.interkont.wsmiobra.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import co.com.interkont.wsmiobra.config.Constantes;


@Entity
@Table(name="obra",schema="modificacion",indexes = 
	{@Index(name = "idx_obra", unique=false ,columnList = "intcodigoobra")})
public class ObraModificacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@NotNull
	@Column(name="intcodigoobra",columnDefinition="INTEGER NOT NULL")
	private Integer obraid;
	
	@NotNull
	@Column(name="intplazoobra",columnDefinition="INTEGER NOT NULL")
	private Integer plazo;
	
	@NotNull
	@Column(name="datefeciniciobra",columnDefinition="DATE NOT NULL")
	private Date fechainicio;
	
	@NotNull
	@Column(name="datefecfinobra",columnDefinition="DATE NOT NULL")
	private Date fechafin;
	
	@NotNull
	@Column(name="intidperiomedida",columnDefinition="INTEGER NOT NULL")
	private Integer periodomedida;
	
	@JsonManagedReference
	@OneToMany(mappedBy="obraModificacion",fetch=FetchType.EAGER)
	private List<ActividadObraModificacion> actividades = new ArrayList<>();;
	

	@NotNull
	@Column(name="fechamodificacion",columnDefinition="DATE NOT NULL")
	private Date fechaModificacion;
	
	@NotNull
	@Column(name="justificmodificacion",columnDefinition="VARCHAR(250) NOT NULL")
	private String justificacionModificacion;	
	
	@Column(name="estadomodificacion",columnDefinition="VARCHAR(1)")
	private String estadoModificacion = Constantes.MODIFICACION_INICIADA;	
	
	@Transient
	@JsonManagedReference
	@OneToMany(mappedBy="obra",fetch=FetchType.LAZY)
	private List<RelacionContratoObra> relacioncontratos = new ArrayList<>();
	
	/**/
	@Column(name="newdatefecfinobra",columnDefinition="DATE")
	private Date newfechafin;
	
	@Column(name="newintplazoobra",columnDefinition="INTEGER")
	private Integer newplazo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getObraid() {
		return obraid;
	}

	public void setObraid(Integer obraid) {
		this.obraid = obraid;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Integer getPeriodomedida() {
		return periodomedida;
	}

	public void setPeriodomedida(Integer periodomedida) {
		this.periodomedida = periodomedida;
	}

	public List<ActividadObraModificacion> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadObraModificacion> actividades) {
		this.actividades = actividades;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getJustificacionModificacion() {
		return justificacionModificacion;
	}

	public void setJustificacionModificacion(String justificacionModificacion) {
		this.justificacionModificacion = justificacionModificacion;
	}

	public String getEstadoModificacion() {
		return estadoModificacion;
	}

	public void setEstadoModificacion(String estadoModificacion) {
		this.estadoModificacion = estadoModificacion;
	}

	public List<RelacionContratoObra> getRelacioncontratos() {
		return relacioncontratos;
	}

	public void setRelacioncontratos(List<RelacionContratoObra> relacioncontratos) {
		this.relacioncontratos = relacioncontratos;
	}

	@JsonFormat(pattern="yyyy-MM-dd",locale="es_CO",shape=Shape.STRING)
	public Date getNewfechafin() {
		return newfechafin;
	}

	public void setNewfechafin(Date newfechafin) {
		this.newfechafin = newfechafin;
	}

	public Integer getNewplazo() {
		return newplazo;
	}

	public void setNewplazo(Integer newplazo) {
		this.newplazo = newplazo;
	}


	

}
