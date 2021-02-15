package co.com.interkont.wsmiobra.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="factoratraso" ,schema="public")
public class FactorAtraso implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @Column(name="intidfactor", unique=true, nullable=false)
	private Integer intidfactor;
	private String strdescfactor;
	private Integer inttipoatraso;
	
	public FactorAtraso() {
	}

	public FactorAtraso(Integer intidfactor, String strdescfactor, Integer inttipoatraso) {
		super();
		this.intidfactor = intidfactor;
		this.strdescfactor = strdescfactor;
		this.inttipoatraso = inttipoatraso;
	}

	public Integer getIntidfactor() {
		return intidfactor;
	}

	public void setIntidfactor(Integer intidfactor) {
		this.intidfactor = intidfactor;
	}

	public String getStrdescfactor() {
		return strdescfactor;
	}

	public void setStrdescfactor(String strdescfactor) {
		this.strdescfactor = strdescfactor;
	}

	public Integer getInttipoatraso() {
		return inttipoatraso;
	}

	public void setInttipoatraso(Integer inttipoatraso) {
		this.inttipoatraso = inttipoatraso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((intidfactor == null) ? 0 : intidfactor.hashCode());
		result = prime * result + ((inttipoatraso == null) ? 0 : inttipoatraso.hashCode());
		result = prime * result + ((strdescfactor == null) ? 0 : strdescfactor.hashCode());
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
		FactorAtraso other = (FactorAtraso) obj;
		if (intidfactor == null) {
			if (other.intidfactor != null)
				return false;
		} else if (!intidfactor.equals(other.intidfactor))
			return false;
		if (inttipoatraso == null) {
			if (other.inttipoatraso != null)
				return false;
		} else if (!inttipoatraso.equals(other.inttipoatraso))
			return false;
		if (strdescfactor == null) {
			if (other.strdescfactor != null)
				return false;
		} else if (!strdescfactor.equals(other.strdescfactor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FactorAtraso [intidfactor=" + intidfactor + ", strdescfactor=" + strdescfactor + ", inttipoatraso="
				+ inttipoatraso + "]";
	}

	
	
}
