/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "imagenevolucionobra", catalog = "cobrauepruebas", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagenEvolucionObra.findAll", query = "SELECT i FROM ImagenEvolucionObra i"),
    @NamedQuery(name = "ImagenEvolucionObra.findByIntidimagen", query = "SELECT i FROM ImagenEvolucionObra i WHERE i.intidimagen = :intidimagen"),
    @NamedQuery(name = "ImagenEvolucionObra.findByStrnombre", query = "SELECT i FROM ImagenEvolucionObra i WHERE i.strnombre = :strnombre"),
    @NamedQuery(name = "ImagenEvolucionObra.findByCodigoProyecto", query = "SELECT i FROM ImagenEvolucionObra i WHERE i.intcodigoobra = :intcodigoobra")
    })
public class ImagenEvolucionObra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer intidimagen;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String strnombre;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datefecha;
    @Column(length = 255)
    private String strubicacion;
    @Column(length = 255)
    private String strnombrearchivo;
    private Boolean booleanvalla;
    private Boolean booleanactual;
    private Integer intusucreacion;
    private Integer intcodigoobra;

    public ImagenEvolucionObra() {
    }

    public ImagenEvolucionObra(Integer intidimagen) {
        this.intidimagen = intidimagen;
    }

    public ImagenEvolucionObra(Integer intidimagen, String strnombre, Date datefecha) {
        this.intidimagen = intidimagen;
        this.strnombre = strnombre;
        this.datefecha = datefecha;
    }

    public Integer getIntidimagen() {
        return intidimagen;
    }

    public void setIntidimagen(Integer intidimagen) {
        this.intidimagen = intidimagen;
    }

    public String getStrnombre() {
        return strnombre;
    }

    public void setStrnombre(String strnombre) {
        this.strnombre = strnombre;
    }

    public Date getDatefecha() {
        return datefecha;
    }

    public void setDatefecha(Date datefecha) {
        this.datefecha = datefecha;
    }

    public String getStrubicacion() {
        return strubicacion;
    }

    public void setStrubicacion(String strubicacion) {
        this.strubicacion = strubicacion;
    }

    public String getStrnombrearchivo() {
        return strnombrearchivo;
    }

    public void setStrnombrearchivo(String strnombrearchivo) {
        this.strnombrearchivo = strnombrearchivo;
    }

    public Boolean getBooleanvalla() {
        return booleanvalla;
    }

    public void setBooleanvalla(Boolean booleanvalla) {
        this.booleanvalla = booleanvalla;
    }

    public Boolean getBooleanactual() {
        return booleanactual;
    }

    public void setBooleanactual(Boolean booleanactual) {
        this.booleanactual = booleanactual;
    }

    public Integer getIntusucreacion() {
        return intusucreacion;
    }

    public void setIntusucreacion(Integer intusucreacion) {
        this.intusucreacion = intusucreacion;
    }

    public Integer getIntcodigoobra() {
        return intcodigoobra;
    }

    public void setIntcodigoobra(Integer intcodigoobra) {
        this.intcodigoobra = intcodigoobra;
    }
        

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intidimagen != null ? intidimagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagenEvolucionObra)) {
            return false;
        }
        ImagenEvolucionObra other = (ImagenEvolucionObra) object;
        if ((this.intidimagen == null && other.intidimagen != null) || (this.intidimagen != null && !this.intidimagen.equals(other.intidimagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.ImagenEvolucionObra[ intidimagen=" + intidimagen + " ]";
    }
    
}
