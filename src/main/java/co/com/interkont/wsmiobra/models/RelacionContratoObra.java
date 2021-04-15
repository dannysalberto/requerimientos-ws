package co.com.interkont.wsmiobra.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.com.interkont.wsmiobra.dto.Obra;

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
	private ObraModificacion obraModificacion; //Se cambio, original tenia ObraModificacion
	
	@Column(name="intcodigoobra",insertable=false ,updatable=false)
	private Integer obraid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intidcontrato",columnDefinition="integer NOT NULL")
	private Contrato contrato;

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
	 * @return the obraModificacion
	 */
	public ObraModificacion getObraModificacion() {
		return obraModificacion;
	}

	/**
	 * @param obraModificacion the obraModificacion to set
	 */
	public void setObraModificacion(ObraModificacion obraModificacion) {
		this.obraModificacion = obraModificacion;
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

		
}
