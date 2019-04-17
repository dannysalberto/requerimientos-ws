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
@Table(name = "vista_proyectos_mapa", catalog = "cobrauepruebas", schema = "appmobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaProyectosMapa.findByAll", 
    			query = "SELECT v "
    				  + "FROM VistaProyectosMapa v "
    				  + "ORDER BY (acos(sin(radians(latitudproyecto)) * sin(radians(:latitudusuario)) + cos(radians(latitudproyecto)) * cos(radians(:latitudusuario)) * cos(radians(longitudproyecto) - radians(:longitudusuario))) * 6371)"),
    @NamedQuery(name = "VistaProyectosMapa.findByNombreproyecto", 
    			query = "SELECT v "
					  + "FROM VistaProyectosMapa v "
			          + "WHERE LOWER(v.nombreproyecto) LIKE LOWER(CONCAT('%',:nombreproyecto,'%')) "
			          + "ORDER BY (acos(sin(radians(latitudproyecto)) * sin(radians(:latitudusuario)) + cos(radians(latitudproyecto)) * cos(radians(:latitudusuario)) * cos(radians(longitudproyecto) - radians(:longitudusuario))) * 6371)"),
	@NamedQuery(name = "VistaProyectosMapa.findByCodigocategoria", 
		 		query = "SELECT v "
	 				  + "FROM VistaProyectosMapa v "
	 				  + "WHERE v.codigocategoria = :codigocategoria "
	 				 + "ORDER BY (acos(sin(radians(latitudproyecto)) * sin(radians(:latitudusuario)) + cos(radians(latitudproyecto)) * cos(radians(:latitudusuario)) * cos(radians(longitudproyecto) - radians(:longitudusuario))) * 6371)"),
	@NamedQuery(name = "VistaProyectosMapa.findById", query = "SELECT v FROM VistaProyectosMapa v WHERE v.id = :id"),
    @NamedQuery(name = "VistaProyectosMapa.findByCodigoproyecto", query = "SELECT v FROM VistaProyectosMapa v WHERE v.codigoproyecto = :codigoproyecto"),
    @NamedQuery(name = "VistaProyectosMapa.findByValorproyecto", query = "SELECT v FROM VistaProyectosMapa v WHERE v.valorproyecto = :valorproyecto"),
    @NamedQuery(name = "VistaProyectosMapa.findByAvanceproyecto", query = "SELECT v FROM VistaProyectosMapa v WHERE v.avanceproyecto = :avanceproyecto"),
    @NamedQuery(name = "VistaProyectosMapa.findBySemaforoproyecto", query = "SELECT v FROM VistaProyectosMapa v WHERE v.semaforoproyecto = :semaforoproyecto")
    })
public class VistaProyectosMapa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer codigoproyecto;
    @Column(length = 100)
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
    private BigDecimal latitudproyecto;
    @Column(precision = 20, scale = 14)
    private BigDecimal longitudproyecto;
    private Integer codigocategoria;
    @Column(length = 100)
    private String colorcategoria;
    @Column(length = 100)
    private String imagencategoria;
    @Column(length = 100)
    private String nombrecategoria;
    @Column(length = 100)
    private String estadoproyecto;
    @Column(length = 100)
    private String localidadproyecto;

    public VistaProyectosMapa() {
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

	public String getLocalidadproyecto() {
		return localidadproyecto;
	}

	public void setLocalidadproyecto(String localidadproyecto) {
		this.localidadproyecto = localidadproyecto;
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
        if (!(object instanceof VistaProyectosMapa)) {
            return false;
        }
        VistaProyectosMapa other = (VistaProyectosMapa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.JsfUsuario[ usuId=" + id + " ]";
    }
    
}
