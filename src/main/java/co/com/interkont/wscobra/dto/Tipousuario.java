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
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t"),
    @NamedQuery(name = "Tipousuario.findByIntcodigotipousuario", query = "SELECT t FROM Tipousuario t WHERE t.intcodigotipousuario = :intcodigotipousuario"),
    @NamedQuery(name = "Tipousuario.findByStrtipousuario", query = "SELECT t FROM Tipousuario t WHERE t.strtipousuario = :strtipousuario")})
public class Tipousuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer intcodigotipousuario;
    @Column(length = 255)
    private String strtipousuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intcodigotipousuario", fetch = FetchType.LAZY)
    private List<JsfUsuario> jsfUsuarioList;

    public Tipousuario() {
    }

    public Tipousuario(Integer intcodigotipousuario) {
        this.intcodigotipousuario = intcodigotipousuario;
    }

    public Integer getIntcodigotipousuario() {
        return intcodigotipousuario;
    }

    public void setIntcodigotipousuario(Integer intcodigotipousuario) {
        this.intcodigotipousuario = intcodigotipousuario;
    }

    public String getStrtipousuario() {
        return strtipousuario;
    }

    public void setStrtipousuario(String strtipousuario) {
        this.strtipousuario = strtipousuario;
    }

    @XmlTransient
    public List<JsfUsuario> getJsfUsuarioList() {
        return jsfUsuarioList;
    }

    public void setJsfUsuarioList(List<JsfUsuario> jsfUsuarioList) {
        this.jsfUsuarioList = jsfUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intcodigotipousuario != null ? intcodigotipousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.intcodigotipousuario == null && other.intcodigotipousuario != null) || (this.intcodigotipousuario != null && !this.intcodigotipousuario.equals(other.intcodigotipousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Tipousuario[ intcodigotipousuario=" + intcodigotipousuario + " ]";
    }
    
}
