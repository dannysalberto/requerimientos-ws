package co.com.interkont.wsmiobra.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import co.com.interkont.wsmiobra.dto.Obra;

@Entity
@Table(schema="modificacion",name="periodo")
public class PeriodoModificacion {

	@Id
	@Column(name="id")
	@NotNull
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
		
	@NotNull
	@Column(name="datefeciniperiodo",columnDefinition="DATE NOT NULL")
	private Date fechainicio;
	
	@NotNull
	@Column(name="datefecfinperiodo",columnDefinition="DATE NOT NULL")
	private Date fechafin;
	
	@NotNull
	@Column(name="numvaltotplanif",columnDefinition="NUMERIC")
	private BigDecimal valtotplanif;
	
    @ManyToOne
	@JoinColumn(name="obra_id",columnDefinition="INTEGER NOT NULL")
	private ObraModificacion obraModificacion;
	

	
	@OneToMany(mappedBy="periodoModificacion",fetch=FetchType.LAZY)
	private List<ActividadObraPeriodoModificacion> actividadObraPeriodoModificacion;


	@NotNull
	@Column(name="intcodigoobra",columnDefinition="integer")
	private Integer obraOrigenId;
	
	@Column(name="intidperiodo",columnDefinition="integer")
	private Integer periodo_id;
	
	public Integer GenerarId(Obra obra, int i) {
		
		String sid = String.valueOf(obra.getId());
		if (i < 10) {
			sid = sid + "0"+ String.valueOf(i);
		}else {
			sid = sid + String.valueOf(i);	
		}
		id = Integer.valueOf(sid);
		return id;
		
	}
	
	
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

	

	/**
	 * @return the actividadObraPeriodoModificacion
	 */
	public List<ActividadObraPeriodoModificacion> getActividadObraPeriodoModificacion() {
		return actividadObraPeriodoModificacion;
	}


	/**
	 * @param actividadObraPeriodoModificacion the actividadObraPeriodoModificacion to set
	 */
	public void setActividadObraPeriodoModificacion(
			List<ActividadObraPeriodoModificacion> actividadObraPeriodoModificacion) {
		this.actividadObraPeriodoModificacion = actividadObraPeriodoModificacion;
	}


	public void addActividadObraPeriodo(ActividadObraPeriodoModificacion obj) {
		if (this.actividadObraPeriodoModificacion==null) {
			this.actividadObraPeriodoModificacion = new ArrayList<>();
		}
		this.actividadObraPeriodoModificacion.add(obj);
	}


	public ObraModificacion getObraModificacion() {
		return obraModificacion;
	}


	public void setObraModificacion(ObraModificacion obraModificacion) {
		this.obraModificacion = obraModificacion;
	}

	
	public Integer getObraOrigenId() {
		return obraOrigenId;
	}


	public void setObraOrigenId(Integer obraOrigenId) {
		this.obraOrigenId = obraOrigenId;
	}


	
	/**
	 * @return the periodo_id
	 */
	public Integer getPeriodo_id() {
		return periodo_id;
	}


	/**
	 * @param periodo_id the periodo_id to set
	 */
	public void setPeriodo_id(Integer periodo_id) {
		this.periodo_id = periodo_id;
	}

	
}
