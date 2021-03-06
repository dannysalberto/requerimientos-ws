package co.com.interkont.avanzame.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(schema="public",name="obra")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Obra {
	
	@Id
	@Column(name="intcodigoobra")
	private Integer id;
	

	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Obra [id=" + id + "]";
	}
	
	
	

	
}
