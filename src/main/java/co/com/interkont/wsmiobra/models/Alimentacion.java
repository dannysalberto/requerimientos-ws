package co.com.interkont.wsmiobra.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alimentacion",schema="public")
public class Alimentacion {

	@Id
	@Column(name="intidalimenta")
	private Integer id;
	
	@Column(name="datefecha",columnDefinition="DATE NOT NULL")
	private Date datefecha;
	
	private Integer intcodigoobra;

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
	 * @return the dateFecha
	 */
	public Date getDateFecha() {
		return datefecha;
	}

	/**
	 * @param dateFecha the dateFecha to set
	 */
	public void setDateFecha(Date dateFecha) {
		this.datefecha = dateFecha;
	}

	/**
	 * @return the intcodigoobra
	 */
	public Integer getIntcodigoobra() {
		return intcodigoobra;
	}

	/**
	 * @param intcodigoobra the intcodigoobra to set
	 */
	public void setIntcodigoobra(Integer intcodigoobra) {
		this.intcodigoobra = intcodigoobra;
	}
	
	
	
}
