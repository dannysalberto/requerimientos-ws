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
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByIntcargo", query = "SELECT c FROM Cargo c WHERE c.intcargo = :intcargo"),
    @NamedQuery(name = "Cargo.findByStrdescripcion", query = "SELECT c FROM Cargo c WHERE c.strdescripcion = :strdescripcion")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer intcargo;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String strdescripcion;
    @OneToMany(mappedBy = "intcargo", fetch = FetchType.LAZY)
    private List<Tercero> terceroList;

    public Cargo() {
    }

    public Cargo(Integer intcargo) {
        this.intcargo = intcargo;
    }

    public Cargo(Integer intcargo, String strdescripcion) {
        this.intcargo = intcargo;
        this.strdescripcion = strdescripcion;
    }

    public Integer getIntcargo() {
        return intcargo;
    }

    public void setIntcargo(Integer intcargo) {
        this.intcargo = intcargo;
    }

    public String getStrdescripcion() {
        return strdescripcion;
    }

    public void setStrdescripcion(String strdescripcion) {
        this.strdescripcion = strdescripcion;
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
        hash += (intcargo != null ? intcargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.intcargo == null && other.intcargo != null) || (this.intcargo != null && !this.intcargo.equals(other.intcargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Cargo[ intcargo=" + intcargo + " ]";
    }
    
}
