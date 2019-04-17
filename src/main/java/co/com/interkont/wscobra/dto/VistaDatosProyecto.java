/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "vista_datos_proyecto", catalog = "cobrauepruebas", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaDatosProyecto.findAll", query = "SELECT v FROM VistaDatosProyecto v"),
    @NamedQuery(name = "VistaDatosProyecto.findById", query = "SELECT v FROM VistaDatosProyecto v WHERE v.id = :id"),
    @NamedQuery(name = "VistaDatosProyecto.findByCodigoproyecto", query = "SELECT v FROM VistaDatosProyecto v WHERE v.codigoproyecto = :codigoproyecto"),
    @NamedQuery(name = "VistaDatosProyecto.findByNombreproyecto", query = "SELECT v FROM VistaDatosProyecto v WHERE v.nombreproyecto = :nombreproyecto"),
    @NamedQuery(name = "VistaDatosProyecto.findByImagenproyecto", query = "SELECT v FROM VistaDatosProyecto v WHERE v.imagenproyecto = :imagenproyecto")})
public class VistaDatosProyecto implements Serializable {
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
    private String objetoproyecto;
    @Temporal(TemporalType.DATE)
    private Date fechainicioproyecto;
    @Temporal(TemporalType.DATE)
    private Date fechafinproyecto;
    private Integer duracionproyecto;
    @Column(length = 2147483647)
    private String valorproyecto;
    @Column(length = 2147483647)
    private String avanceproyecto;
    @Column(length = 2147483647)
    private String semaforoproyecto;
    @Column(length = 2147483647)
    private String localidadproyecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 20, scale = 14)
    private BigDecimal latitudproyecto;
    @Column(precision = 20, scale = 14)
    private BigDecimal longitudproyecto;
    private Integer codigocategoria;
    @Column(length = 10)
    private String colorcategoria;
    @Column(length = 100)
    private String imagencategoria;
    @Column(length = 2147483647)
    private String nombrecategoria;
    @Column(length = 50)
    private String estadoproyecto;
    @Column(length = 2147483647)
    private String logoestadoproyecto;

    public VistaDatosProyecto() {
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

    public String getObjetoproyecto() {
        return objetoproyecto;
    }

    public void setObjetoproyecto(String objetoproyecto) {
        this.objetoproyecto = objetoproyecto;
    }

    public Date getFechainicioproyecto() {
        return fechainicioproyecto;
    }

    public void setFechainicioproyecto(Date fechainicioproyecto) {
        this.fechainicioproyecto = fechainicioproyecto;
    }

    public Date getFechafinproyecto() {
        return fechafinproyecto;
    }

    public void setFechafinproyecto(Date fechafinproyecto) {
        this.fechafinproyecto = fechafinproyecto;
    }

    public Integer getDuracionproyecto() {
        return duracionproyecto;
    }

    public void setDuracionproyecto(Integer duracionproyecto) {
        this.duracionproyecto = duracionproyecto;
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

    public String getLocalidadproyecto() {
        return localidadproyecto;
    }

    public void setLocalidadproyecto(String localidadproyecto) {
        this.localidadproyecto = localidadproyecto;
    }

    public BigDecimal getLatitudproyecto() {
        return latitudproyecto;
    }

    public void setLatitudproyecto(BigDecimal latitudproyecto) {
        this.latitudproyecto = latitudproyecto;
    }

    public BigDecimal getLongitudproyecto() {
        return longitudproyecto;
    }

    public void setLongitudproyecto(BigDecimal longitudproyecto) {
        this.longitudproyecto = longitudproyecto;
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

    public String getLogoestadoproyecto() {
        return logoestadoproyecto;
    }

    public void setLogoestadoproyecto(String logoestadoproyecto) {
        this.logoestadoproyecto = logoestadoproyecto;
    }
    
}
