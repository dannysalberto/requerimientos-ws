package co.com.interkont.wscobra.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipofactoratraso" ,schema="public")
public class TipoFactorAtraso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name="inttipoatraso", unique=true, nullable=false)
	private Integer inttipoatraso;
	private String strdesctipoatraso;	
	
	public TipoFactorAtraso() {
	}

	public TipoFactorAtraso(Integer inttipoatraso, String strdesctipoatraso) {
		super();
		this.inttipoatraso = inttipoatraso;
		this.strdesctipoatraso = strdesctipoatraso;
	}

	public Integer getInttipoatraso() {
		return inttipoatraso;
	}

	public void setInttipoatraso(Integer inttipoatraso) {
		this.inttipoatraso = inttipoatraso;
	}

	public String getStrdesctipoatraso() {
		return strdesctipoatraso;
	}

	public void setStrdesctipoatraso(String strdesctipoatraso) {
		this.strdesctipoatraso = strdesctipoatraso;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (inttipoatraso ^ (inttipoatraso >>> 32));
		result = prime * result + ((strdesctipoatraso == null) ? 0 : strdesctipoatraso.hashCode());
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
		TipoFactorAtraso other = (TipoFactorAtraso) obj;
		if (inttipoatraso != other.inttipoatraso)
			return false;
		if (strdesctipoatraso == null) {
			if (other.strdesctipoatraso != null)
				return false;
		} else if (!strdesctipoatraso.equals(other.strdesctipoatraso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoFactorAtraso [inttipoatraso=" + inttipoatraso + ", strdesctipoatraso=" + strdesctipoatraso + "]";
	}
	

}
