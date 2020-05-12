/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.auth.pojo;

import java.io.Serializable;
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


/***
 * 
 * Entitidad de la tabla autenticacion.jsf_usuario
 *
 */
@Entity
@Table(name = "jsf_usuario", catalog = "cobrauepruebas", schema = "autenticacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsfUsuario.findAll", query = "SELECT j FROM JsfUsuario j"),
    @NamedQuery(name = "JsfUsuario.findByUsuId", query = "SELECT j FROM JsfUsuario j WHERE j.usuId = :usuId"),
    @NamedQuery(name = "JsfUsuario.findByUsuLogin", query = "SELECT j FROM JsfUsuario j WHERE j.usuLogin = :usuLogin"),
    @NamedQuery(name = "JsfUsuario.findByUsuPasswd", query = "SELECT j FROM JsfUsuario j WHERE j.usuPasswd = :usuPasswd"),
    @NamedQuery(name = "JsfUsuario.findByUsuFchaCrcion", query = "SELECT j FROM JsfUsuario j WHERE j.usuFchaCrcion = :usuFchaCrcion"),
    @NamedQuery(name = "JsfUsuario.findByUsuFchaVncmnto", query = "SELECT j FROM JsfUsuario j WHERE j.usuFchaVncmnto = :usuFchaVncmnto"),
    @NamedQuery(name = "JsfUsuario.findByUsuEstado", query = "SELECT j FROM JsfUsuario j WHERE j.usuEstado = :usuEstado"),
    @NamedQuery(name = "JsfUsuario.findByRelacioncontrato", query = "SELECT j FROM JsfUsuario j WHERE j.relacioncontrato = :relacioncontrato"),
    @NamedQuery(name = "JsfUsuario.findByLdap", query = "SELECT j FROM JsfUsuario j WHERE j.ldap = :ldap"),
    @NamedQuery(name = "JsfUsuario.findByUltimoIngreso", query = "SELECT j FROM JsfUsuario j WHERE j.ultimoIngreso = :ultimoIngreso")})
public class JsfUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_id", nullable = false)
    private Long usuId;
    @Basic(optional = false)
    @Column(name = "usu_login", nullable = false, length = 255)
    private String usuLogin;
    @Column(name = "usu_passwd", length = 150)
    private String usuPasswd;
    @Column(name = "usu_fcha_crcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuFchaCrcion;
    @Column(name = "usu_fcha_vncmnto")
    @Temporal(TemporalType.DATE)
    private Date usuFchaVncmnto;
    @Column(name = "usu_estado")
    private Boolean usuEstado;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean relacioncontrato;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean ldap;
    @Column(name = "ultimo_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoIngreso;
    

    public JsfUsuario() {
    }

    public JsfUsuario(Long usuId) {
        this.usuId = usuId;
    }

    public JsfUsuario(Long usuId, String usuLogin, boolean relacioncontrato, boolean ldap) {
        this.usuId = usuId;
        this.usuLogin = usuLogin;
        this.relacioncontrato = relacioncontrato;
        this.ldap = ldap;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuPasswd() {
    	return usuPasswd;
    }

    public void setUsuPasswd(String usuPasswd) {
        this.usuPasswd = usuPasswd;
    }

    public Date getUsuFchaCrcion() {
        return usuFchaCrcion;
    }

    public void setUsuFchaCrcion(Date usuFchaCrcion) {
        this.usuFchaCrcion = usuFchaCrcion;
    }

    public Date getUsuFchaVncmnto() {
        return usuFchaVncmnto;
    }

    public void setUsuFchaVncmnto(Date usuFchaVncmnto) {
        this.usuFchaVncmnto = usuFchaVncmnto;
    }

    public Boolean getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(Boolean usuEstado) {
        this.usuEstado = usuEstado;
    }

    public boolean getRelacioncontrato() {
        return relacioncontrato;
    }

    public void setRelacioncontrato(boolean relacioncontrato) {
        this.relacioncontrato = relacioncontrato;
    }

    public boolean getLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public Date getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(Date ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsfUsuario)) {
            return false;
        }
        JsfUsuario other = (JsfUsuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.JsfUsuario[ usuId=" + usuId + " ]";
    }
    
}
