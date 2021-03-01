package co.com.interkont.wsmiobra.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="relacioncontratoobra",schema="public")
@JsonIgnoreProperties(value = { "obra" })
public class RelacionContratoObra{

	@Id
	@NotNull
	@Column(name="intidserial")
	private Integer id;
	
	@NotNull
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="intcodigoobra",columnDefinition="integer NOT NULL",
	referencedColumnName="intcodigoobra")
	private ObraModificacion obra;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intidcontrato",columnDefinition="integer NOT NULL")
	private Contrato contrato;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public ObraModificacion getObra() {
		return obra;
	}


	public void setObra(ObraModificacion obra) {
		this.obra = obra;
	}


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}


	

	
		
}
