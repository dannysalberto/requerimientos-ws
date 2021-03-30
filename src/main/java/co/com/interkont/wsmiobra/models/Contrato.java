package co.com.interkont.wsmiobra.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="contrato",schema="public")
@JsonIgnoreProperties(value = { "relacioncontratoobra" })
public class Contrato {
	
	
	@Id
	@NotNull
	@Column(name="intidcontrato")
	private Integer id;
	

	@Column(name="strnumcontrato")
	private String numeroContrato;
	
	@Column(name="valordisponible")
	private BigDecimal valorDisponible;
	
	@Column(name="inttipocontrato")
	private Integer tipoContrato;
	
	@Column(name="numvlrsumaproyectos",columnDefinition="NUMERIC(20,6)")
	private BigDecimal numvlrsumaproyectos;
	
	@Column(name="numvlrcontrato",columnDefinition="NUMERIC(20,6)")
	private BigDecimal numvlrcontrato;
	
	

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	
	public BigDecimal getValorDisponible() {
		valorDisponible = numvlrcontrato.subtract(numvlrsumaproyectos)
				.setScale(4,BigDecimal.ROUND_HALF_DOWN);
		return valorDisponible;
	}

	public void setValorDisponible(BigDecimal valorDisponible) {
		this.valorDisponible = valorDisponible;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(Integer tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	/**
	 * @return the numvlrsumaproyectos
	 */
	@JsonIgnore
	public BigDecimal getNumvlrsumaproyectos() {
		return numvlrsumaproyectos;
	}

	/**
	 * @param numvlrsumaproyectos the numvlrsumaproyectos to set
	 */
	public void setNumvlrsumaproyectos(BigDecimal numvlrsumaproyectos) {
		this.numvlrsumaproyectos = numvlrsumaproyectos;
	}

	/**
	 * @return the numvlrcontrato
	 */
	@JsonIgnore
	public BigDecimal getNumvlrcontrato() {
		return numvlrcontrato;
	}

	/**
	 * @param numvlrcontrato the numvlrcontrato to set
	 */
	public void setNumvlrcontrato(BigDecimal numvlrcontrato) {
		this.numvlrcontrato = numvlrcontrato;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contrato [id=" + id + ", numeroContrato=" + numeroContrato + ", valorDisponible=" + valorDisponible
				+ ", tipoContrato=" + tipoContrato + ", numvlrsumaproyectos=" + numvlrsumaproyectos
				+ ", numvlrcontrato=" + numvlrcontrato + "]";
	}

	

	

}
