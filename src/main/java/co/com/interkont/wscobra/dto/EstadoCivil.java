/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "estado_civil", catalog = "cobrauepruebas", schema = "autenticacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCivil.findAll", query = "SELECT e FROM EstadoCivil e"),
    @NamedQuery(name = "EstadoCivil.findByIntestadoCivil", query = "SELECT e FROM EstadoCivil e WHERE e.intestadoCivil = :intestadoCivil"),
    @NamedQuery(name = "EstadoCivil.findByStrnombre", query = "SELECT e FROM EstadoCivil e WHERE e.strnombre = :strnombre")})
public class EstadoCivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "intestado_civil", nullable = false)
    private Integer intestadoCivil;
    @Column(length = 2147483647)
    private String strnombre;
    @OneToMany(mappedBy = "intestadoCivil", fetch = FetchType.LAZY)
    private List<Tercero> terceroList;

    public EstadoCivil() {
    }

    public EstadoCivil(Integer intestadoCivil) {
        this.intestadoCivil = intestadoCivil;
    }

    public Integer getIntestadoCivil() {
        return intestadoCivil;
    }

    public void setIntestadoCivil(Integer intestadoCivil) {
        this.intestadoCivil = intestadoCivil;
    }

    public String getStrnombre() {
        return strnombre;
    }

    public void setStrnombre(String strnombre) {
        this.strnombre = strnombre;
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
        hash += (intestadoCivil != null ? intestadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivil)) {
            return false;
        }
        EstadoCivil other = (EstadoCivil) object;
        if ((this.intestadoCivil == null && other.intestadoCivil != null) || (this.intestadoCivil != null && !this.intestadoCivil.equals(other.intestadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.EstadoCivil[ intestadoCivil=" + intestadoCivil + " ]";
    }
    
}
