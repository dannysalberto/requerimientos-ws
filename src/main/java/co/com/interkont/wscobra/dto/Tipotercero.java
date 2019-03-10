/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonatan
 */
@Entity
@Table(catalog = "cobrauepruebas", schema = "autenticacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipotercero.findAll", query = "SELECT t FROM Tipotercero t"),
    @NamedQuery(name = "Tipotercero.findByIntcodigotipotercero", query = "SELECT t FROM Tipotercero t WHERE t.intcodigotipotercero = :intcodigotipotercero"),
    @NamedQuery(name = "Tipotercero.findByStrnombretipotercero", query = "SELECT t FROM Tipotercero t WHERE t.strnombretipotercero = :strnombretipotercero")})
public class Tipotercero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer intcodigotipotercero;
    @Column(length = 255)
    private String strnombretipotercero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intcodigotipotercero", fetch = FetchType.LAZY)
    private List<Tercero> terceroList;

    public Tipotercero() {
    }

    public Tipotercero(Integer intcodigotipotercero) {
        this.intcodigotipotercero = intcodigotipotercero;
    }

    public Integer getIntcodigotipotercero() {
        return intcodigotipotercero;
    }

    public void setIntcodigotipotercero(Integer intcodigotipotercero) {
        this.intcodigotipotercero = intcodigotipotercero;
    }

    public String getStrnombretipotercero() {
        return strnombretipotercero;
    }

    public void setStrnombretipotercero(String strnombretipotercero) {
        this.strnombretipotercero = strnombretipotercero;
    }

    @XmlTransient
    public List<Tercero> getTerceroList() {
        return terceroList;
    }

    public void setTerceroList(List<Tercero> terceroList) {
        this.terceroList = terceroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intcodigotipotercero != null ? intcodigotipotercero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipotercero)) {
            return false;
        }
        Tipotercero other = (Tipotercero) object;
        if ((this.intcodigotipotercero == null && other.intcodigotipotercero != null) || (this.intcodigotipotercero != null && !this.intcodigotipotercero.equals(other.intcodigotipotercero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Tipotercero[ intcodigotipotercero=" + intcodigotipotercero + " ]";
    }
    
}
