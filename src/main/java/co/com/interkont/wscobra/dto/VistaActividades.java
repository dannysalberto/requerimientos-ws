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
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Datos de la Actividad de un proyecto
 * @author Jhon Eduard Ortiz
 */
@Entity
@Table(name = "vista_actividades", catalog = "cobracondor", schema = "appmobile")
@XmlRootElement
public class VistaActividades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer codigoProyecto;
    @Column(length = 100)
    private String descripcionActividad;
    @Column(length = 100)
    private String unidadMedida;
    
    @Column(precision = 20, scale = 6)
    private BigDecimal valorUnitario;
    @Column
    private Double cantidadProgramada;
    @Column
    private Double cantidadEjecutada;
    @Column(precision = 20, scale = 6)
    private BigDecimal valorProgramado;
    @Column(precision = 20, scale = 6)
    private BigDecimal valorEjecutado;
    @Column
    private Double porcentajeAvance;
    
    public VistaActividades() {
    }
    
    

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getCodigoProyecto() {
		return codigoProyecto;
	}



	public void setCodigoProyecto(Integer codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}



	public String getDescripcionActividad() {
		return descripcionActividad;
	}



	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}



	public String getUnidadMedida() {
		return unidadMedida;
	}



	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}



	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}



	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}



	public Double getCantidadProgramada() {
		return cantidadProgramada;
	}



	public void setCantidadProgramada(Double cantidadProgramada) {
		this.cantidadProgramada = cantidadProgramada;
	}



	public Double getCantidadEjecutada() {
		return cantidadEjecutada;
	}



	public void setCantidadEjecutada(Double cantidadEjecutada) {
		this.cantidadEjecutada = cantidadEjecutada;
	}



	public BigDecimal getValorProgramado() {
		return valorProgramado;
	}



	public void setValorProgramado(BigDecimal valorProgramado) {
		this.valorProgramado = valorProgramado;
	}



	public BigDecimal getValorEjecutado() {
		return valorEjecutado;
	}



	public void setValorEjecutado(BigDecimal valorEjecutado) {
		this.valorEjecutado = valorEjecutado;
	}



	public Double getPorcentajeAvance() {
		return porcentajeAvance;
	}



	public void setPorcentajeAvance(Double porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}



	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VistaActividades)) {
            return false;
        }
        VistaActividades other = (VistaActividades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
