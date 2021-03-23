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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@SequenceGenerator(name="modificacion.obra_id_seq",sequenceName="modificacion.obra_id_seq",
 	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="modificacion.obra_id_seq")
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
	
	//@Transient
	@JsonManagedReference
	@OneToMany(mappedBy="obra",fetch=FetchType.LAZY)
	private List<RelacionContratoObra> relacioncontratos = new ArrayList<>();
	
	/**/
	@Column(name="newdatefecfinobra",columnDefinition="DATE")
	private Date newfechafin;
	
	@Column(name="newintplazoobra",columnDefinition="INTEGER")
	private Integer newplazo;
	
    @Column(name="numvaltotobra", nullable=false, precision=20, scale=6)
    private BigDecimal numvaltotobra;

    @Column(name="newnumvaltotobra", nullable=false, precision=20, scale=6)
    private BigDecimal newnumvaltotobra;
    
    @Column(name="newcosto_directo", columnDefinition="numeric(20,6)")
	private BigDecimal newcosto_directo;
    
    @Transient
	private Integer cantidadActividades;
	
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

	@JsonProperty(value="plazoInicial")
	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	@JsonProperty(value="fechaInicioInicial")
	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	@JsonProperty(value="fechaFinInicial")
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
	@JsonProperty(value="fechaFinActual")
	public Date getNewfechafin() {
		return newfechafin;
	}

	public void setNewfechafin(Date newfechafin) {
		this.newfechafin = newfechafin;
	}

	@JsonProperty(value="plazoActual")
	public Integer getNewplazo() {
		return newplazo;
	}

	public void setNewplazo(Integer newplazo) {
		this.newplazo = newplazo;
	}

	@JsonProperty(value="valorTotalObraInicial")
	public BigDecimal getNumvaltotobra() {
		return numvaltotobra;
	}

	public void setNumvaltotobra(BigDecimal numvaltotobra) {
		this.numvaltotobra = numvaltotobra;
	}

	@JsonProperty(value="valorTotalObraActual")
	public BigDecimal getNewnumvaltotobra() {
		return newnumvaltotobra;
	}

	public void setNewnumvaltotobra(BigDecimal newnumvaltotobra) {
		this.newnumvaltotobra = newnumvaltotobra;
	}

	@JsonIgnore
	public BigDecimal getNewcosto_directo() {
		return newcosto_directo;
	}

	public void setNewcosto_directo(BigDecimal costo_directo) {
		if (costo_directo==null) {
			this.newcosto_directo = new BigDecimal(0);
		}else {
			this.newcosto_directo = costo_directo;			
		}
	}

	@JsonIgnore
	public Integer getCantidadActividades() {
		return cantidadActividades;
	}

	public void setCantidadActividades(Integer cantidadActividades) {
		this.cantidadActividades = cantidadActividades;
	}
	
	
	
	
	
	


	

}
