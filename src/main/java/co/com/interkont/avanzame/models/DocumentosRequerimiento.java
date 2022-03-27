package co.com.interkont.avanzame.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.checkerframework.checker.units.qual.Time;
import org.springframework.data.jpa.repository.Temporal;

@Entity
@Table(name="documentosrequerimiento",schema="requerimientos")
public class DocumentosRequerimiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="obraid")	
	private Integer obraid;
	
	@Column(name="documentoid")	
	private Integer documentoid;
	
	@Column(name="usuario")	
	private String usuario;
	
	@Column(name="fecha")	
	private Date fecha;
	
	@Column(name="fechaop")	
	private Date fechaop = new Date();
	
	

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
	 * @return the documentoid
	 */
	public Integer getDocumentoid() {
		return documentoid;
	}

	/**
	 * @param documentoid the documentoid to set
	 */
	public void setDocumentoid(Integer documentoid) {
		this.documentoid = documentoid;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the fechaop
	 */
	public Date getFechaop() {
		return fechaop;
	}

	/**
	 * @param fechaop the fechaop to set
	 */
	public void setFechaop(Date fechaop) {
		this.fechaop = fechaop;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DocumentosRequerimiento [id=" + id + ", obraid=" + obraid + ", documentoid=" + documentoid
				+ ", usuario=" + usuario + ", fecha=" + fecha + ", fechaop=" + fechaop + "]";
	}
	
	

	
}
