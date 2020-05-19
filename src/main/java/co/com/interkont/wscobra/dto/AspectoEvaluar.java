package co.com.interkont.wscobra.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipocualitativa" ,schema="public")
public class AspectoEvaluar  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @Column(name="intidtipocualificacion", unique=true, nullable=false)
	private Integer intidtipocualificacion;
	private String strnombre;
	private String strdescripcion;
	
	public AspectoEvaluar() {
	}

	public AspectoEvaluar(Integer intidtipocualificacion, String strnombre, String strdescripcion) {
		this.intidtipocualificacion = intidtipocualificacion;
		this.strnombre = strnombre;
		this.strdescripcion = strdescripcion;
	}

	public Integer getIntidtipocualificacion() {
		return intidtipocualificacion;
	}

	public void setIntidtipocualificacion(Integer intidtipocualificacion) {
		this.intidtipocualificacion = intidtipocualificacion;
	}

	public String getStrnombre() {
		return strnombre;
	}

	public void setStrnombre(String strnombre) {
		this.strnombre = strnombre;
	}

	public String getStrdescripcion() {
		return strdescripcion;
	}

	public void setStrdescripcion(String strdescripcion) {
		this.strdescripcion = strdescripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((intidtipocualificacion == null) ? 0 : intidtipocualificacion.hashCode());
		result = prime * result + ((strdescripcion == null) ? 0 : strdescripcion.hashCode());
		result = prime * result + ((strnombre == null) ? 0 : strnombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AspectoEvaluar other = (AspectoEvaluar) obj;
		if (intidtipocualificacion == null) {
			if (other.intidtipocualificacion != null)
				return false;
		} else if (!intidtipocualificacion.equals(other.intidtipocualificacion))
			return false;
		if (strdescripcion == null) {
			if (other.strdescripcion != null)
				return false;
		} else if (!strdescripcion.equals(other.strdescripcion))
			return false;
		if (strnombre == null) {
			if (other.strnombre != null)
				return false;
		} else if (!strnombre.equals(other.strnombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AspectoEvaluar [intidtipocualificacion=" + intidtipocualificacion + ", strnombre=" + strnombre
				+ ", strdescripcion=" + strdescripcion + "]";
	}
	
	
	

}
