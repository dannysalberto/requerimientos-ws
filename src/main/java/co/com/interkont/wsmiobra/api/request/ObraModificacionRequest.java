package co.com.interkont.wsmiobra.api.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ObraModificacionRequest {
	
	@NotEmpty(message="Debe especificar el id")
	private int id;
	
	@NotEmpty(message="Debe especificar la fecha de modificación")
	@JsonFormat(pattern="yyyy-MM-dd",locale="es_CO",shape=Shape.STRING)
	private String fechaModificacion;
	
	@NotEmpty(message="No puede dejar el registro sin justificación")
	private String justificacionModificacion;
	
	private String usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getJustificacionModificacion() {
		return justificacionModificacion;
	}

	public void setJustificacionModificacion(String justificacionModificacion) {
		this.justificacionModificacion = justificacionModificacion;
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
