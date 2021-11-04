package co.com.interkont.avanzame.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(schema="public",name="obra")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Obra {
	
	@Id
	@Column(name="intcodigoobra")
	private Integer id;
	
	@Column(name="fpocompromisoenergia")
	private Date fechaPuestaOperacion;
	
	@Column(name="intcodigo")
	private Integer idTercero;
	

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
	 * @return the fechaPuestaOperacion
	 */
	@JsonFormat(pattern="yyyy-MM-dd",locale = "es_CO")
	public Date getFechaPuestaOperacion() {
		return fechaPuestaOperacion;
	}

	/**
	 * @param fechaPuestaOperacion the fechaPuestaOperacion to set
	 */
	public void setFechaPuestaOperacion(Date fechaPuestaOperacion) {
		this.fechaPuestaOperacion = fechaPuestaOperacion;
	}

	/**
	 * @return the idTercero
	 */
	public Integer getIdTercero() {
		return idTercero;
	}

	/**
	 * @param idTercero the idTercero to set
	 */
	public void setIdTercero(Integer idTercero) {
		this.idTercero = idTercero;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Obra [id=" + id + ", fechaPuestaOperacion=" + fechaPuestaOperacion + ", idTercero=" + idTercero + "]";
	}

	
	

	
}
