package co.com.interkont.avanzame.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import co.com.interkont.avanzame.models.Obra;
import co.com.interkont.avanzame.models.Requerimiento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class RequerimientoObraResponse {
	
	private Integer id;
	
	private Obra obra;

	private Requerimiento requerimiento;
	
	private String valor;

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
	 * @return the obra
	 */
	public Obra getObra() {
		return obra;
	}

	/**
	 * @param obra the obra to set
	 */
	public void setObra(Obra obra) {
		this.obra = obra;
	}

	/**
	 * @return the requerimiento
	 */
	public Requerimiento getRequerimiento() {
		return requerimiento;
	}

	/**
	 * @param requerimiento the requerimiento to set
	 */
	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = requerimiento;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequerimientoObra [id=" + id + ", obra=" + obra + ", requerimiento=" + requerimiento + ", valor="
				+ valor + "]";
	}
	
	
	
	
	

}
