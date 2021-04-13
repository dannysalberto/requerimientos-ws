package co.com.interkont.wsmiobra.api.response;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ObraSoloFechasResponse implements Serializable{
	
	private Integer id;
	
	@JsonProperty(value="fechaInicioInicial")
	private Date fechaInicio; 

	@JsonProperty(value="fechaFinInicial")
	private Date fechaFin;

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

	

	
	
}
