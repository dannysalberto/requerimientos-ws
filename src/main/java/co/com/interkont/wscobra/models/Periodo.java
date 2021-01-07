package co.com.interkont.wscobra.models;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.interkont.wscobra.dto.Obra;

@Entity
@Table(schema="public",name="periodo")
public class Periodo {

	@Id
	@Column(name="intidperiodo")
	private Integer id;
	
	@NotNull
	//@Column(name="datefeciniperiodo",columnDefinition="TIMESTAMP")
	@Column(name="datefeciniperiodo",columnDefinition="DATE NOT NULL")
	private Date fechainicio;
	
	@NotNull
	@Column(name="datefecfinperiodo",columnDefinition="DATE NOT NULL")
	private Date fechafin;
	
	@NotNull
	@Column(name="numvaltotplanif",columnDefinition="NUMERIC")
	private BigDecimal valtotplanif;
	
	@ManyToOne
	@JsonIgnore
	@NotNull
	@JoinColumn(name="intcodigoobra",columnDefinition="integer")
	private Obra obra;

	@JsonIgnore
	@Transient
	@OneToMany(mappedBy="periodo",fetch=FetchType.LAZY)
	private List<ActividadObraPeriodo> actividadObra;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getValtotplanif() {
		return valtotplanif;
	}

	public void setValtotplanif(BigDecimal valtotplanif) {
		this.valtotplanif = valtotplanif;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public List<ActividadObraPeriodo> getActividadObra() {
		return actividadObra;
	}

	public void setActividadObra(List<ActividadObraPeriodo> actividadObra) {
		this.actividadObra = actividadObra;
	}

	public void addActividadObraPeriodo(ActividadObraPeriodo obj) {
		if (this.actividadObra==null) {
			this.actividadObra = new ArrayList<>();
		}
		this.actividadObra.add(obj);
	}
	
	@Override
	public String toString() {
		return "Periodo [id=" + id + ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", valtotplanif="
				+ valtotplanif + ", obra=" + obra + ", actividadObra=" + actividadObra + "]";
	}



	
}
