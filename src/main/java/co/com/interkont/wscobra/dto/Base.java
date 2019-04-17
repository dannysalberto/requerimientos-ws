/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonatan
 */
@Entity
@Table(catalog = "cobrauepruebas", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Base.findAll", query = "SELECT b FROM Base b"),
    @NamedQuery(name = "Base.findByIdindicador", query = "SELECT b FROM Base b WHERE b.idindicador = :idindicador"),
    @NamedQuery(name = "Base.findByIntcodigoobra", query = "SELECT b FROM Base b WHERE b.intcodigoobra = :intcodigoobra"),
    @NamedQuery(name = "Base.findByNombre", query = "SELECT b FROM Base b WHERE b.nombre = :nombre")})
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idindicador;
    @Basic(optional = false)
    @Column(nullable = false)
    private int intcodigoobra;
    @Basic(optional = false)
    @Column(nullable = false, length = 600)
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal avanceresultado;
    @Basic(optional = false)
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal peso;
    @Basic(optional = false)
    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @Column(name = "usu_id", nullable = false)
    private int usuId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;

    public Base() {
    }

    public Base(Integer idindicador) {
        this.idindicador = idindicador;
    }

    public Base(Integer idindicador, int intcodigoobra, String nombre, BigDecimal avanceresultado, BigDecimal peso, BigDecimal porcentaje, int usuId) {
        this.idindicador = idindicador;
        this.intcodigoobra = intcodigoobra;
        this.nombre = nombre;
        this.avanceresultado = avanceresultado;
        this.peso = peso;
        this.porcentaje = porcentaje;
        this.usuId = usuId;
    }

    public Integer getIdindicador() {
        return idindicador;
    }

    public void setIdindicador(Integer idindicador) {
        this.idindicador = idindicador;
    }

    public int getIntcodigoobra() {
        return intcodigoobra;
    }

    public void setIntcodigoobra(int intcodigoobra) {
        this.intcodigoobra = intcodigoobra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getAvanceresultado() {
        return avanceresultado;
    }

    public void setAvanceresultado(BigDecimal avanceresultado) {
        this.avanceresultado = avanceresultado;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getUsuId() {
        return usuId;
    }

    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idindicador != null ? idindicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Base)) {
            return false;
        }
        Base other = (Base) object;
        if ((this.idindicador == null && other.idindicador != null) || (this.idindicador != null && !this.idindicador.equals(other.idindicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Base[ idindicador=" + idindicador + " ]";
    }
    
}
