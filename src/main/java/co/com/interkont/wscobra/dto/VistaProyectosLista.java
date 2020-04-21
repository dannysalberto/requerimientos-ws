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
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon Eduard Ortiz
 */
@Entity
@Table(name = "vista_proyectos_lista", catalog = "cobracondor", schema = "appmobile")
@XmlRootElement
public class VistaProyectosLista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer codigoproyecto;
    @Column(length = 100)
    private String nombreproyecto;
    @Column(precision = 20, scale = 6)
    private BigDecimal valorproyecto;
    @Column(length = 250)
    private String semaforoproyecto;
    private Integer codigocategoria;
    @Column(length = 100)
    private String imagencategoria;
    @Column(length = 100)
    private String nombrecategoria;
    @Column(length = 255)
    private String usuario;
    
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



	public BigDecimal getValorproyecto() {
		return valorproyecto;
	}



	public void setValorproyecto(BigDecimal valorproyecto) {
		this.valorproyecto = valorproyecto;
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

	

	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
        if (!(object instanceof VistaProyectosLista)) {
            return false;
        }
        VistaProyectosLista other = (VistaProyectosLista) object;
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
