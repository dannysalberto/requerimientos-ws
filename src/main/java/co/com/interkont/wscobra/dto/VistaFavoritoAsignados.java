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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonatan
 */
@Entity
@Table(name = "vista_favorito_asignados", catalog = "cobrauepruebas", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaFavoritoAsignados.findAll", query = "SELECT v FROM VistaFavoritoAsignados v"),
    @NamedQuery(name = "VistaFavoritoAsignados.findByUsuario", 
    			query = "SELECT v "
    				  + "FROM VistaFavoritoAsignados v "
    				  + "WHERE v.codigousuario = :codigousuario "
    				  + "ORDER BY (acos(sin(radians(latitud)) * sin(radians(:latitud)) + cos(radians(latitud)) * cos(radians(:latitud)) * cos(radians(longitud) - radians(:longitud))) * 6371)")})
public class VistaFavoritoAsignados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    private Integer codigoproyecto;
    @Column(length = 10)
    private String colorcategoria;
    @Column(length = 100)
    private String imagencategoria;
    @Column(length = 2147483647)
    private String nombrecategoria;
    @Column(length = 2147483647)
    private String nombreproyecto;
    @Column(length = 2147483647)
    private String distaciaproyecto;
    @Column(length = 2147483647)
    private String valorproyecto;
    @Column(length = 2147483647)
    private String avanceproyecto;
    @Column(length = 2147483647)
    private String semaforoproyecto;
    @Column(length = 2147483647)
    private String favorito;
    private Integer codigousuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 20, scale = 14)
    private BigDecimal latitud;
    @Column(precision = 20, scale = 14)
    private BigDecimal longitud;

    public VistaFavoritoAsignados() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoproyecto() {
        return codigoproyecto;
    }

    public void setCodigoproyecto(Integer codigoproyecto) {
        this.codigoproyecto = codigoproyecto;
    }

    public String getColorcategoria() {
        return colorcategoria;
    }

    public void setColorcategoria(String colorcategoria) {
        this.colorcategoria = colorcategoria;
    }

    public String getImagencategoria() {
        return imagencategoria;
    }

    public void setImagencategoria(String imagencategoria) {
        this.imagencategoria = imagencategoria;
    }

    public String getNombrecategoria() {
        return nombrecategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombrecategoria = nombrecategoria;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public String getDistaciaproyecto() {
        return distaciaproyecto;
    }

    public void setDistaciaproyecto(String distaciaproyecto) {
        this.distaciaproyecto = distaciaproyecto;
    }

    public String getValorproyecto() {
        return valorproyecto;
    }

    public void setValorproyecto(String valorproyecto) {
        this.valorproyecto = valorproyecto;
    }

    public String getAvanceproyecto() {
        return avanceproyecto;
    }

    public void setAvanceproyecto(String avanceproyecto) {
        this.avanceproyecto = avanceproyecto;
    }

    public String getSemaforoproyecto() {
        return semaforoproyecto;
    }

    public void setSemaforoproyecto(String semaforoproyecto) {
        this.semaforoproyecto = semaforoproyecto;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }

    public Integer getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Integer codigousuario) {
        this.codigousuario = codigousuario;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
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
        if (!(object instanceof VistaFavoritoAsignados)) {
            return false;
        }
        VistaFavoritoAsignados other = (VistaFavoritoAsignados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.interkont.bancoproyectos.entidades.LogProyecto[ id=" + id + " ]";
    }
    
    
}
