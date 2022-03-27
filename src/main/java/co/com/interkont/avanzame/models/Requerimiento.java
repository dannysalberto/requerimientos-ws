package co.com.interkont.avanzame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="requerimiento",schema="requerimientos")
public class Requerimiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombrerequerimiento",columnDefinition="VARCHAR(200) NOT NULL")	
	private String nombreRequerimiento;
	
	@Column(name="tipocaptura",columnDefinition="VARCHAR(14) NOT NULL")	
	private String tipoCaptura;

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
	 * @return the nombreRequerimiento
	 */
	public String getNombreRequerimiento() {
		return nombreRequerimiento;
	}

	/**
	 * @param nombreRequerimiento the nombreRequerimiento to set
	 */
	public void setNombreRequerimiento(String nombreRequerimiento) {
		this.nombreRequerimiento = nombreRequerimiento;
	}

	/**
	 * @return the tipoCaptura
	 */
	public String getTipoCaptura() {
		return tipoCaptura;
	}

	/**
	 * @param tipoCaptura the tipoCaptura to set
	 */
	public void setTipoCaptura(String tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Requerimiento [id=" + id + ", nombreRequerimiento=" + nombreRequerimiento + ", tipoCaptura="
				+ tipoCaptura + "]";
	}
	
	

}
