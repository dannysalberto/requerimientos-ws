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
@Table(catalog = "cobrauepruebas", schema = "autenticacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g"),
    @NamedQuery(name = "Genero.findByIntgenero", query = "SELECT g FROM Genero g WHERE g.intgenero = :intgenero"),
    @NamedQuery(name = "Genero.findByStrnombre", query = "SELECT g FROM Genero g WHERE g.strnombre = :strnombre")})
public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer intgenero;
    @Column(length = 2147483647)
    private String strnombre;
    @OneToMany(mappedBy = "intgenero", fetch = FetchType.LAZY)
    private List<Tercero> terceroList;

    public Genero() {
    }

    public Genero(Integer intgenero) {
        this.intgenero = intgenero;
    }

    public Integer getIntgenero() {
        return intgenero;
    }

    public void setIntgenero(Integer intgenero) {
        this.intgenero = intgenero;
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
        hash += (intgenero != null ? intgenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.intgenero == null && other.intgenero != null) || (this.intgenero != null && !this.intgenero.equals(other.intgenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Genero[ intgenero=" + intgenero + " ]";
    }
    
}
