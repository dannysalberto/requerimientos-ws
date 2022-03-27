package co.com.interkont.avanzame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="requerimientoobra",schema="requerimientos")
@ApiModel
public class RequerimientoObra {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes = "id", example = "1", required = true) 
	private Integer id;
	
	
	@Column(name="obraid")
	private Integer obraid;
	
	@Column(name="requerimientoid")
	private Integer requerimientoid;
	
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
	 * @return the obraid
	 */
	public Integer getObraid() {
		return obraid;
	}

	/**
	 * @param obraid the obraid to set
	 */
	public void setObraid(Integer obraid) {
		this.obraid = obraid;
	}

	/**
	 * @return the requerimientoid
	 */
	public Integer getRequerimientoid() {
		return requerimientoid;
	}

	/**
	 * @param requerimientoid the requerimientoid to set
	 */
	public void setRequerimientoid(Integer requerimientoid) {
		this.requerimientoid = requerimientoid;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
