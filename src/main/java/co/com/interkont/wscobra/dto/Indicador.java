/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(catalog = "cobrauepruebas", schema = "encuestasue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.findAllIndicadores", query = "SELECT i FROM Indicador i ORDER BY i.tipofuente ASC, i.id ASC"),
    @NamedQuery(name = "Indicador.findById", query = "SELECT i FROM Indicador i WHERE i.id = :id"),
    @NamedQuery(name = "Indicador.findByIndicador", query = "SELECT i FROM Indicador i WHERE i.indicador = :indicador ORDER BY i.tipofuente ASC, i.id ASC"),
    @NamedQuery(name = "Indicador.findByTipofuente", query = "SELECT i FROM Indicador i WHERE i.tipofuente = :tipofuente"),
    @NamedQuery(name = "Indicador.findByCodigo", query = "SELECT i FROM Indicador i WHERE i.codigo = :codigo")})
public class Indicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String indicador;
    private Integer tipofuente;
    @Column(length = 10)
    private String codigo;
    @Column(length = 500)
    private String descripcion;
    @Column(length = 100)
    private String logomobile;

    public Indicador() {
    }

    public Indicador(Integer id) {
        this.id = id;
    }

    public Indicador(Integer id, String indicador) {
        this.id = id;
        this.indicador = indicador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public Integer getTipofuente() {
        return tipofuente;
    }

    public void setTipofuente(Integer tipofuente) {
        this.tipofuente = tipofuente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogomobile() {
        return logomobile;
    }

    public void setLogomobile(String logomobile) {
        this.logomobile = logomobile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Indicador[ id=" + id + " ]";
    }
    
}
