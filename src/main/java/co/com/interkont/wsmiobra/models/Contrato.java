package co.com.interkont.wsmiobra.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
	
	@Column(name="numvlrcontrato")
	private String valorContrato;
	
	@Column(name="inttipocontrato")
	private Integer tipoContrato;
		

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(String valorContrato) {
		this.valorContrato = valorContrato;
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

	@Override
	public String toString() {
		return "Contrato [id=" + id + ", numeroContrato=" + numeroContrato + ", valorContrato=" + valorContrato
				+ ", tipoContrato=" + tipoContrato + "]";
	}

	
	
	

}
