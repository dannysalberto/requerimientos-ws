/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonatan
 */
@Entity
@Table(name = "vista_periodos_obra", catalog = "cobracondor", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaPeriodosObra.findAll", query = "SELECT v FROM VistaPeriodosObra v"),
    @NamedQuery(name = "VistaPeriodosObra.findById", query = "SELECT v FROM VistaPeriodosObra v WHERE v.id = :id"),
    @NamedQuery(name = "VistaPeriodosObra.findByCodigoproyecto", query = "SELECT v FROM VistaPeriodosObra v WHERE v.codigoproyecto = :codigoproyecto")})
public class VistaPeriodosObra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(length = 100)
    private String fechainicioperiodo;
    @Column(length = 100)
    private String fechafinperiodo;
    private BigDecimal porcentajeproyectado;
    private Integer codigoproyecto;
    
	public VistaPeriodosObra() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFechainicioperiodo() {
		return fechainicioperiodo;
	}
	public void setFechainicioperiodo(String fechainicioperiodo) {
		this.fechainicioperiodo = fechainicioperiodo;
	}
	public String getFechafinperiodo() {
		return fechafinperiodo;
	}
	public void setFechafinperiodo(String fechafinperiodo) {
		this.fechafinperiodo = fechafinperiodo;
	}
	public BigDecimal getPorcentajeproyectado() {
		return porcentajeproyectado;
	}
	public void setPorcentajeproyectado(BigDecimal porcentajeproyectado) {
		this.porcentajeproyectado = porcentajeproyectado;
	}
	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}
	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoproyecto == null) ? 0 : codigoproyecto.hashCode());
		result = prime * result + ((fechafinperiodo == null) ? 0 : fechafinperiodo.hashCode());
		result = prime * result + ((fechainicioperiodo == null) ? 0 : fechainicioperiodo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((porcentajeproyectado == null) ? 0 : porcentajeproyectado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VistaPeriodosObra other = (VistaPeriodosObra) obj;
		if (codigoproyecto == null) {
			if (other.codigoproyecto != null)
				return false;
		} else if (!codigoproyecto.equals(other.codigoproyecto))
			return false;
		if (fechafinperiodo == null) {
			if (other.fechafinperiodo != null)
				return false;
		} else if (!fechafinperiodo.equals(other.fechafinperiodo))
			return false;
		if (fechainicioperiodo == null) {
			if (other.fechainicioperiodo != null)
				return false;
		} else if (!fechainicioperiodo.equals(other.fechainicioperiodo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (porcentajeproyectado == null) {
			if (other.porcentajeproyectado != null)
				return false;
		} else if (!porcentajeproyectado.equals(other.porcentajeproyectado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VistaPeriodosObra [id=" + id + ", fechainicioperiodo=" + fechainicioperiodo + ", fechafinperiodo="
				+ fechafinperiodo + ", porcentajeproyectado=" + porcentajeproyectado + ", codigoproyecto="
				+ codigoproyecto + "]";
	}
    
	
    
}
