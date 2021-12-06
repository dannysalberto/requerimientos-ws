package co.com.interkont.avanzame.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(schema="public",name="v_obra_fpo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObraFPO {
	
	@Id
	@Column(name="intcodigoobra")
	private Integer id;
	
	@Column(name="fpocompromisoenergia")
	private Date fechaPuestaOperacion;
	
	@Column(name="intcodigo")
	private Integer idTercero;
	
	@Column(name="nombreentidad")
	private String nombreEntidad;
	
	@Column(name="strnombreobra")
	private String strNombreObra;
	
	@Column(name="intcedula")
	private String intCedula;

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

	/**
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * @return the strNombreObra
	 */
	public String getStrNombreObra() {
		return strNombreObra;
	}

	/**
	 * @param strNombreObra the strNombreObra to set
	 */
	public void setStrNombreObra(String strNombreObra) {
		this.strNombreObra = strNombreObra;
	}

	/**
	 * @return the intCedula
	 */
	public String getIntCedula() {
		return intCedula;
	}

	/**
	 * @param intCedula the intCedula to set
	 */
	public void setIntCedula(String intCedula) {
		this.intCedula = intCedula;
	}

	@Override
	public String toString() {
		return "ObraFPO [id=" + id + ", fechaPuestaOperacion=" + fechaPuestaOperacion + ", idTercero=" + idTercero
				+ ", nombreEntidad=" + nombreEntidad + ", strNombreObra=" + strNombreObra + ", intCedula=" + intCedula
				+ "]";
	}	
}
