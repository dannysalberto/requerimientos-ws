package co.com.interkont.wsmiobra.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="relacioncontratoobra",schema="public")
public class RelacionContratoObraTable{

	@Id
	@NotNull
	@Column(name="intidserial")
	private Integer id;

	@Column(name="intcodigoobra")
	private Integer obraOriginal;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intidcontrato",columnDefinition="integer NOT NULL")
	private Contrato contrato;

	private BigDecimal numvalorrelacion;

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
	 * @return the obraOriginal
	 */
	public Integer getObraOriginal() {
		return obraOriginal;
	}

	/**
	 * @param obraOriginal the obraOriginal to set
	 */
	public void setObraOriginal(Integer obraOriginal) {
		this.obraOriginal = obraOriginal;
	}

	/**
	 * @return the contrato
	 */
	public Contrato getContrato() {
		return contrato;
	}

	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	/**
	 * @return the numvalorrelacion
	 */
	public BigDecimal getNumvalorrelacion() {
		return numvalorrelacion;
	}

	/**
	 * @param numvalorrelacion the numvalorrelacion to set
	 */
	public void setNumvalorrelacion(BigDecimal numvalorrelacion) {
		this.numvalorrelacion = numvalorrelacion;
	}
	
	
		
}
