package co.com.interkont.wsmiobra.api.request;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public class ObraUpdateRequest {
	
	private int idModificacion;
	
	@JsonFormat(pattern="yyyy-MM-dd",locale="es_CO",shape=Shape.STRING)
	private String fechafin;
	
	private Integer plazo;
	
	private String usuario;

 
	
	/**
	 * @return the idModificacion
	 */
	public int getIdModificacion() {
		return idModificacion;
	}

	/**
	 * @param idModificacion the idModificacion to set
	 */
	public void setIdModificacion(int idModificacion) {
		this.idModificacion = idModificacion;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	
	

}
