package co.com.interkont.wsmiobra.api.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ObraModificacionRequest {
	
	private int id;
	
	@JsonFormat(pattern="yyyy-MM-dd",locale="es_CO",shape=Shape.STRING)
	private String fechaModificacion;
	
	private String justificacionModificacion;

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

	
	
	

}
