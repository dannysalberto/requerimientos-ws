package co.com.interkont.wsmiobra.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.interkont.wsmiobra.dto.Obra;

@Entity
@Table(name="suspensionobra",schema="public")
public class SuspensionObra {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="fecharegistro")
	private Date fechaRegistro;
	
	@Column(name="fecharegistroreinicio")
	private Date fechaReinico;
	
	@Column(name="fechaini")
	private Date fechaInicio;
	
	@Column(name="fechafin")
	private Date fechaFin;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="intcodigoobra",columnDefinition="integer NOT NULL")
    private Obra obra;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the fechaReinico
	 */
	public Date getFechaReinico() {
		return fechaReinico;
	}

	/**
	 * @param fechaReinico the fechaReinico to set
	 */
	public void setFechaReinico(Date fechaReinico) {
		this.fechaReinico = fechaReinico;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the obra
	 */
	public Obra getObra() {
		return obra;
	}

	/**
	 * @param obra the obra to set
	 */
	public void setObra(Obra obra) {
		this.obra = obra;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuspensionObra [id=" + id + ", fechaRegistro=" + fechaRegistro + ", fechaReinico=" + fechaReinico
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", obra=" + obra + ", getId()=" + getId()
				+ ", getFechaRegistro()=" + getFechaRegistro() + ", getFechaReinico()=" + getFechaReinico()
				+ ", getFechaInicio()=" + getFechaInicio() + ", getFechaFin()=" + getFechaFin() + ", getObra()="
				+ getObra() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
