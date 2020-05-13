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
@Table(name = "vista_indicadores_obra", catalog = "cobracondor", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaIndicadoresObra.findAll", query = "SELECT v FROM VistaIndicadoresObra v"),
    @NamedQuery(name = "VistaIndicadoresObra.findById", query = "SELECT v FROM VistaIndicadoresObra v WHERE v.id = :id"),
    @NamedQuery(name = "VistaIndicadoresObra.findByCodigoproyecto", query = "SELECT v FROM VistaIndicadoresObra v WHERE v.codigoproyecto = :codigoproyecto")})
public class VistaIndicadoresObra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer codigoproyecto;
    @Column(length = 100)
    private String descripcionindicadoralcance;
    @Column(length = 100)
    private String unidadmedida;
    private BigDecimal cantidadprogramada;
    private BigDecimal cantidadejecutada;
    private BigDecimal porcentajeavance;
	
    
    public VistaIndicadoresObra() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCodigoproyecto() {
		return codigoproyecto;
	}


	public void setCodigoproyecto(Integer codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}


	public String getDescripcionindicadoralcance() {
		return descripcionindicadoralcance;
	}


	public void setDescripcionindicadoralcance(String descripcionindicadoralcance) {
		this.descripcionindicadoralcance = descripcionindicadoralcance;
	}


	public String getUnidadmedida() {
		return unidadmedida;
	}


	public void setUnidadmedida(String unidadmedida) {
		this.unidadmedida = unidadmedida;
	}


	public BigDecimal getCantidadprogramada() {
		return cantidadprogramada;
	}


	public void setCantidadprogramada(BigDecimal cantidadprogramada) {
		this.cantidadprogramada = cantidadprogramada;
	}


	public BigDecimal getCantidadejecutada() {
		return cantidadejecutada;
	}


	public void setCantidadejecutada(BigDecimal cantidadejecutada) {
		this.cantidadejecutada = cantidadejecutada;
	}


	public BigDecimal getPorcentajeavance() {
		return porcentajeavance;
	}


	public void setPorcentajeavance(BigDecimal porcentajeavance) {
		this.porcentajeavance = porcentajeavance;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadejecutada == null) ? 0 : cantidadejecutada.hashCode());
		result = prime * result + ((cantidadprogramada == null) ? 0 : cantidadprogramada.hashCode());
		result = prime * result + ((codigoproyecto == null) ? 0 : codigoproyecto.hashCode());
		result = prime * result + ((descripcionindicadoralcance == null) ? 0 : descripcionindicadoralcance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((porcentajeavance == null) ? 0 : porcentajeavance.hashCode());
		result = prime * result + ((unidadmedida == null) ? 0 : unidadmedida.hashCode());
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
		VistaIndicadoresObra other = (VistaIndicadoresObra) obj;
		if (cantidadejecutada == null) {
			if (other.cantidadejecutada != null)
				return false;
		} else if (!cantidadejecutada.equals(other.cantidadejecutada))
			return false;
		if (cantidadprogramada == null) {
			if (other.cantidadprogramada != null)
				return false;
		} else if (!cantidadprogramada.equals(other.cantidadprogramada))
			return false;
		if (codigoproyecto == null) {
			if (other.codigoproyecto != null)
				return false;
		} else if (!codigoproyecto.equals(other.codigoproyecto))
			return false;
		if (descripcionindicadoralcance == null) {
			if (other.descripcionindicadoralcance != null)
				return false;
		} else if (!descripcionindicadoralcance.equals(other.descripcionindicadoralcance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (porcentajeavance == null) {
			if (other.porcentajeavance != null)
				return false;
		} else if (!porcentajeavance.equals(other.porcentajeavance))
			return false;
		if (unidadmedida == null) {
			if (other.unidadmedida != null)
				return false;
		} else if (!unidadmedida.equals(other.unidadmedida))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "VistaIndicadoresObra [id=" + id + ", codigoproyecto=" + codigoproyecto
				+ ", descripcionindicadoralcance=" + descripcionindicadoralcance + ", unidadmedida=" + unidadmedida
				+ ", cantidadprogramada=" + cantidadprogramada + ", cantidadejecutada=" + cantidadejecutada
				+ ", porcentajeavance=" + porcentajeavance + "]";
	}
	    
}
