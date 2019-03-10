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
@Table(name = "vista_proyectos_lista", catalog = "cobrauepruebas", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaProyectosLista.findByAll", 
    			query = "SELECT v "
    				  + "FROM VistaProyectosLista v "
    				  + "ORDER BY (acos(sin(radians(latitud)) * sin(radians(:latitud)) + cos(radians(latitud)) * cos(radians(:latitud)) * cos(radians(longitud) - radians(:longitud))) * 6371)"),
    @NamedQuery(name = "VistaProyectosLista.findByNombreproyecto", 
    			query = "SELECT v "
    				  + "FROM VistaProyectosLista v "
    				  + "WHERE v.nombreproyecto LIKE CONCAT('%',:nombreproyecto,'%') "
    				  + "ORDER BY (acos(sin(radians(latitud)) * sin(radians(:latitud)) + cos(radians(latitud)) * cos(radians(:latitud)) * cos(radians(longitud) - radians(:longitud))) * 6371)"),
    @NamedQuery(name = "VistaProyectosLista.findByCodigocategoria", 
    			query = "SELECT v "
    				  + "FROM VistaProyectosLista v "
    				  + "WHERE v.codigocategoria = :codigocategoria "
    				  + "ORDER BY (acos(sin(radians(latitud)) * sin(radians(:latitud)) + cos(radians(latitud)) * cos(radians(:latitud)) * cos(radians(longitud) - radians(:longitud))) * 6371)"),
    @NamedQuery(name = "VistaProyectosLista.findById", query = "SELECT v FROM VistaProyectosLista v WHERE v.id = :id"),
    @NamedQuery(name = "VistaProyectosLista.findByCodigoproyecto", query = "SELECT v FROM VistaProyectosLista v WHERE v.codigoproyecto = :codigoproyecto"),
    @NamedQuery(name = "VistaProyectosLista.findByImagenproyecto", query = "SELECT v FROM VistaProyectosLista v WHERE v.imagenproyecto = :imagenproyecto"),
    @NamedQuery(name = "VistaProyectosLista.findByValorproyecto", query = "SELECT v FROM VistaProyectosLista v WHERE v.valorproyecto = :valorproyecto"),
    @NamedQuery(name = "VistaProyectosLista.findByAvanceproyecto", query = "SELECT v FROM VistaProyectosLista v WHERE v.avanceproyecto = :avanceproyecto"),
    @NamedQuery(name = "VistaProyectosLista.findBySemaforoproyecto", query = "SELECT v FROM VistaProyectosLista v WHERE v.semaforoproyecto = :semaforoproyecto"),
    @NamedQuery(name = "VistaProyectosLista.findByLatitud", query = "SELECT v FROM VistaProyectosLista v WHERE v.latitud = :latitud"),
    @NamedQuery(name = "VistaProyectosLista.findByLongitud", query = "SELECT v FROM VistaProyectosLista v WHERE v.longitud = :longitud"),    
    @NamedQuery(name = "VistaProyectosLista.findByColorcategoria", query = "SELECT v FROM VistaProyectosLista v WHERE v.colorcategoria = :colorcategoria"),
    @NamedQuery(name = "VistaProyectosLista.findByImagencategoria", query = "SELECT v FROM VistaProyectosLista v WHERE v.imagencategoria = :imagencategoria"),
    @NamedQuery(name = "VistaProyectosLista.findByNombrecategoria", query = "SELECT v FROM VistaProyectosLista v WHERE v.nombrecategoria = :nombrecategoria"),
    @NamedQuery(name = "VistaProyectosLista.findByEstadoproyecto", query = "SELECT v FROM VistaProyectosLista v WHERE v.estadoproyecto = :estadoproyecto")})
public class VistaProyectosLista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer codigoproyecto;
    @Column(length = 2147483647)
    private String nombreproyecto;
    @Column(length = 200)
    private String imagenproyecto;
    @Column(length = 2147483647)
    private String valorproyecto;
    @Column(length = 2147483647)
    private String avanceproyecto;
    @Column(length = 2147483647)
    private String semaforoproyecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 20, scale = 14)
    private BigDecimal latitud;
    @Column(precision = 20, scale = 14)
    private BigDecimal longitud;
    private Integer codigocategoria;
    @Column(length = 10)
    private String colorcategoria;
    @Column(length = 100)
    private String imagencategoria;
    @Column(length = 2147483647)
    private String nombrecategoria;
    @Column(length = 50)
    private String estadoproyecto;

    public VistaProyectosLista() {
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

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public String getImagenproyecto() {
        return imagenproyecto;
    }

    public void setImagenproyecto(String imagenproyecto) {
        this.imagenproyecto = imagenproyecto;
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

    public Integer getCodigocategoria() {
        return codigocategoria;
    }

    public void setCodigocategoria(Integer codigocategoria) {
        this.codigocategoria = codigocategoria;
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

    public String getEstadoproyecto() {
        return estadoproyecto;
    }

    public void setEstadoproyecto(String estadoproyecto) {
        this.estadoproyecto = estadoproyecto;
    }
    
}
