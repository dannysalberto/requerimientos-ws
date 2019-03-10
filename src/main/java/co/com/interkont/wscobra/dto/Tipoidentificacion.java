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
    @NamedQuery(name = "Tipoidentificacion.findAll", query = "SELECT t FROM Tipoidentificacion t"),
    @NamedQuery(name = "Tipoidentificacion.findByIntcodigotipoiden", query = "SELECT t FROM Tipoidentificacion t WHERE t.intcodigotipoiden = :intcodigotipoiden"),
    @NamedQuery(name = "Tipoidentificacion.findByStrnombre", query = "SELECT t FROM Tipoidentificacion t WHERE t.strnombre = :strnombre"),
    @NamedQuery(name = "Tipoidentificacion.findByStrabreviatura", query = "SELECT t FROM Tipoidentificacion t WHERE t.strabreviatura = :strabreviatura")})
public class Tipoidentificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer intcodigotipoiden;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String strnombre;
    @Column(length = 4)
    private String strabreviatura;
    @OneToMany(mappedBy = "intcodigotipoiden", fetch = FetchType.LAZY)
    private List<Tercero> terceroList;

    public Tipoidentificacion() {
    }

    public Tipoidentificacion(Integer intcodigotipoiden) {
        this.intcodigotipoiden = intcodigotipoiden;
    }

    public Tipoidentificacion(Integer intcodigotipoiden, String strnombre) {
        this.intcodigotipoiden = intcodigotipoiden;
        this.strnombre = strnombre;
    }

    public Integer getIntcodigotipoiden() {
        return intcodigotipoiden;
    }

    public void setIntcodigotipoiden(Integer intcodigotipoiden) {
        this.intcodigotipoiden = intcodigotipoiden;
    }

    public String getStrnombre() {
        return strnombre;
    }

    public void setStrnombre(String strnombre) {
        this.strnombre = strnombre;
    }

    public String getStrabreviatura() {
        return strabreviatura;
    }

    public void setStrabreviatura(String strabreviatura) {
        this.strabreviatura = strabreviatura;
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
        hash += (intcodigotipoiden != null ? intcodigotipoiden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoidentificacion)) {
            return false;
        }
        Tipoidentificacion other = (Tipoidentificacion) object;
        if ((this.intcodigotipoiden == null && other.intcodigotipoiden != null) || (this.intcodigotipoiden != null && !this.intcodigotipoiden.equals(other.intcodigotipoiden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Tipoidentificacion[ intcodigotipoiden=" + intcodigotipoiden + " ]";
    }
    
}
