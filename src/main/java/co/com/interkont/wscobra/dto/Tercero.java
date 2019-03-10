/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.interkont.wscobra.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Tercero.findAll", query = "SELECT t FROM Tercero t"),
    @NamedQuery(name = "Tercero.findByIntcodigo", query = "SELECT t FROM Tercero t WHERE t.intcodigo = :intcodigo"),
    @NamedQuery(name = "Tercero.findByIntcedula", query = "SELECT t FROM Tercero t WHERE t.intcedula = :intcedula"),
    @NamedQuery(name = "Tercero.findByStremail", query = "SELECT t FROM Tercero t WHERE t.stremail = :stremail"),
    @NamedQuery(name = "Tercero.findByStrnombre", query = "SELECT t FROM Tercero t WHERE t.strnombre = :strnombre"),
    @NamedQuery(name = "Tercero.findByStrapellido1", query = "SELECT t FROM Tercero t WHERE t.strapellido1 = :strapellido1"),
    @NamedQuery(name = "Tercero.findByStrapellido2", query = "SELECT t FROM Tercero t WHERE t.strapellido2 = :strapellido2"),
    @NamedQuery(name = "Tercero.findByStrtelefono1", query = "SELECT t FROM Tercero t WHERE t.strtelefono1 = :strtelefono1"),
    @NamedQuery(name = "Tercero.findByStrtelefono2", query = "SELECT t FROM Tercero t WHERE t.strtelefono2 = :strtelefono2"),
    @NamedQuery(name = "Tercero.findByStrmovil", query = "SELECT t FROM Tercero t WHERE t.strmovil = :strmovil"),
    @NamedQuery(name = "Tercero.findByDatefechanacimiento", query = "SELECT t FROM Tercero t WHERE t.datefechanacimiento = :datefechanacimiento"),
    @NamedQuery(name = "Tercero.findByBoolestado", query = "SELECT t FROM Tercero t WHERE t.boolestado = :boolestado"),
    @NamedQuery(name = "Tercero.findByDateusuariocreacion", query = "SELECT t FROM Tercero t WHERE t.dateusuariocreacion = :dateusuariocreacion"),
    @NamedQuery(name = "Tercero.findByStrdireccionprincipal", query = "SELECT t FROM Tercero t WHERE t.strdireccionprincipal = :strdireccionprincipal"),
    @NamedQuery(name = "Tercero.findByStrcodigolocalidad", query = "SELECT t FROM Tercero t WHERE t.strcodigolocalidad = :strcodigolocalidad"),
    @NamedQuery(name = "Tercero.findByIntcodigoverificacion", query = "SELECT t FROM Tercero t WHERE t.intcodigoverificacion = :intcodigoverificacion"),
    @NamedQuery(name = "Tercero.findByStrlocalidadexpdocumento", query = "SELECT t FROM Tercero t WHERE t.strlocalidadexpdocumento = :strlocalidadexpdocumento"),
    @NamedQuery(name = "Tercero.findByStrlocalidadnacimiento", query = "SELECT t FROM Tercero t WHERE t.strlocalidadnacimiento = :strlocalidadnacimiento"),
    @NamedQuery(name = "Tercero.findByStrnombrecompleto", query = "SELECT t FROM Tercero t WHERE t.strnombrecompleto = :strnombrecompleto"),
    @NamedQuery(name = "Tercero.findByStrpaginaweb", query = "SELECT t FROM Tercero t WHERE t.strpaginaweb = :strpaginaweb"),
    @NamedQuery(name = "Tercero.findByStrfoto", query = "SELECT t FROM Tercero t WHERE t.strfoto = :strfoto"),
    @NamedQuery(name = "Tercero.findByStrtwiter", query = "SELECT t FROM Tercero t WHERE t.strtwiter = :strtwiter"),
    @NamedQuery(name = "Tercero.findByStrfacebook", query = "SELECT t FROM Tercero t WHERE t.strfacebook = :strfacebook"),
    @NamedQuery(name = "Tercero.findByStrcontacto", query = "SELECT t FROM Tercero t WHERE t.strcontacto = :strcontacto"),
    @NamedQuery(name = "Tercero.findByBoolobraslocalidad", query = "SELECT t FROM Tercero t WHERE t.boolobraslocalidad = :boolobraslocalidad"),
    @NamedQuery(name = "Tercero.findByIntidtipoorigen", query = "SELECT t FROM Tercero t WHERE t.intidtipoorigen = :intidtipoorigen"),
    @NamedQuery(name = "Tercero.findByIntsector", query = "SELECT t FROM Tercero t WHERE t.intsector = :intsector"),
    @NamedQuery(name = "Tercero.findByInttiposolicitante", query = "SELECT t FROM Tercero t WHERE t.inttiposolicitante = :inttiposolicitante"),
    @NamedQuery(name = "Tercero.findByBoolentidadnacional", query = "SELECT t FROM Tercero t WHERE t.boolentidadnacional = :boolentidadnacional"),
    @NamedQuery(name = "Tercero.findByBoolcliente", query = "SELECT t FROM Tercero t WHERE t.boolcliente = :boolcliente"),
    @NamedQuery(name = "Tercero.findByStrlegal", query = "SELECT t FROM Tercero t WHERE t.strlegal = :strlegal")})
public class Tercero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer intcodigo;
    @Column(length = 20)
    private String intcedula;
    @Column(length = 80)
    private String stremail;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String strnombre;
    @Column(length = 150)
    private String strapellido1;
    @Column(length = 2147483647)
    private String strapellido2;
    @Column(length = 100)
    private String strtelefono1;
    @Column(length = 30)
    private String strtelefono2;
    @Column(length = 30)
    private String strmovil;
    @Temporal(TemporalType.DATE)
    private Date datefechanacimiento;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean boolestado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateusuariocreacion;
    @Column(length = 80)
    private String strdireccionprincipal;
    @Column(length = 10)
    private String strcodigolocalidad;
    private Integer intcodigoverificacion;
    @Column(length = 10)
    private String strlocalidadexpdocumento;
    @Column(length = 10)
    private String strlocalidadnacimiento;
    @Column(length = 2147483647)
    private String strnombrecompleto;
    @Column(length = 2147483647)
    private String strpaginaweb;
    @Column(length = 2147483647)
    private String strfoto;
    @Column(length = 2147483647)
    private String strtwiter;
    @Column(length = 2147483647)
    private String strfacebook;
    @Column(length = 2147483647)
    private String strcontacto;
    private Boolean boolobraslocalidad;
    private Integer intidtipoorigen;
    private Integer intsector;
    private Integer inttiposolicitante;
    private Boolean boolentidadnacional;
    private Boolean boolcliente;
    @Column(length = 2147483647)
    private String strlegal;
    @JoinTable(name = "terceroentidad", joinColumns = {
        @JoinColumn(name = "intcodigoentidad", referencedColumnName = "intcodigo", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "intcodigotercero", referencedColumnName = "intcodigo", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tercero> terceroList;
    @ManyToMany(mappedBy = "terceroList", fetch = FetchType.LAZY)
    private List<Tercero> terceroList1;
    @OneToMany(mappedBy = "usuTerCdigo", fetch = FetchType.LAZY)
    private List<JsfUsuario> jsfUsuarioList;
    @JoinColumn(name = "intcodigotipotercero", referencedColumnName = "intcodigotipotercero", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipotercero intcodigotipotercero;
    @JoinColumn(name = "intcodigotipoiden", referencedColumnName = "intcodigotipoiden")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tipoidentificacion intcodigotipoiden;
    @JoinColumn(name = "intgenero", referencedColumnName = "intgenero")
    @ManyToOne(fetch = FetchType.LAZY)
    private Genero intgenero;
    @JoinColumn(name = "intestado_civil", referencedColumnName = "intestado_civil")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoCivil intestadoCivil;
    @JoinColumn(name = "depid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dependencia depid;
    @JoinColumn(name = "intcargo", referencedColumnName = "intcargo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cargo intcargo;

    public Tercero() {
    }

    public Tercero(Integer intcodigo) {
        this.intcodigo = intcodigo;
    }

    public Tercero(Integer intcodigo, String strnombre, boolean boolestado) {
        this.intcodigo = intcodigo;
        this.strnombre = strnombre;
        this.boolestado = boolestado;
    }

    public Integer getIntcodigo() {
        return intcodigo;
    }

    public void setIntcodigo(Integer intcodigo) {
        this.intcodigo = intcodigo;
    }

    public String getIntcedula() {
        return intcedula;
    }

    public void setIntcedula(String intcedula) {
        this.intcedula = intcedula;
    }

    public String getStremail() {
        return stremail;
    }

    public void setStremail(String stremail) {
        this.stremail = stremail;
    }

    public String getStrnombre() {
        return strnombre;
    }

    public void setStrnombre(String strnombre) {
        this.strnombre = strnombre;
    }

    public String getStrapellido1() {
        return strapellido1;
    }

    public void setStrapellido1(String strapellido1) {
        this.strapellido1 = strapellido1;
    }

    public String getStrapellido2() {
        return strapellido2;
    }

    public void setStrapellido2(String strapellido2) {
        this.strapellido2 = strapellido2;
    }

    public String getStrtelefono1() {
        return strtelefono1;
    }

    public void setStrtelefono1(String strtelefono1) {
        this.strtelefono1 = strtelefono1;
    }

    public String getStrtelefono2() {
        return strtelefono2;
    }

    public void setStrtelefono2(String strtelefono2) {
        this.strtelefono2 = strtelefono2;
    }

    public String getStrmovil() {
        return strmovil;
    }

    public void setStrmovil(String strmovil) {
        this.strmovil = strmovil;
    }

    public Date getDatefechanacimiento() {
        return datefechanacimiento;
    }

    public void setDatefechanacimiento(Date datefechanacimiento) {
        this.datefechanacimiento = datefechanacimiento;
    }

    public boolean getBoolestado() {
        return boolestado;
    }

    public void setBoolestado(boolean boolestado) {
        this.boolestado = boolestado;
    }

    public Date getDateusuariocreacion() {
        return dateusuariocreacion;
    }

    public void setDateusuariocreacion(Date dateusuariocreacion) {
        this.dateusuariocreacion = dateusuariocreacion;
    }

    public String getStrdireccionprincipal() {
        return strdireccionprincipal;
    }

    public void setStrdireccionprincipal(String strdireccionprincipal) {
        this.strdireccionprincipal = strdireccionprincipal;
    }

    public String getStrcodigolocalidad() {
        return strcodigolocalidad;
    }

    public void setStrcodigolocalidad(String strcodigolocalidad) {
        this.strcodigolocalidad = strcodigolocalidad;
    }

    public Integer getIntcodigoverificacion() {
        return intcodigoverificacion;
    }

    public void setIntcodigoverificacion(Integer intcodigoverificacion) {
        this.intcodigoverificacion = intcodigoverificacion;
    }

    public String getStrlocalidadexpdocumento() {
        return strlocalidadexpdocumento;
    }

    public void setStrlocalidadexpdocumento(String strlocalidadexpdocumento) {
        this.strlocalidadexpdocumento = strlocalidadexpdocumento;
    }

    public String getStrlocalidadnacimiento() {
        return strlocalidadnacimiento;
    }

    public void setStrlocalidadnacimiento(String strlocalidadnacimiento) {
        this.strlocalidadnacimiento = strlocalidadnacimiento;
    }

    public String getStrnombrecompleto() {
        return strnombrecompleto;
    }

    public void setStrnombrecompleto(String strnombrecompleto) {
        this.strnombrecompleto = strnombrecompleto;
    }

    public String getStrpaginaweb() {
        return strpaginaweb;
    }

    public void setStrpaginaweb(String strpaginaweb) {
        this.strpaginaweb = strpaginaweb;
    }

    public String getStrfoto() {
        return strfoto;
    }

    public void setStrfoto(String strfoto) {
        this.strfoto = strfoto;
    }

    public String getStrtwiter() {
        return strtwiter;
    }

    public void setStrtwiter(String strtwiter) {
        this.strtwiter = strtwiter;
    }

    public String getStrfacebook() {
        return strfacebook;
    }

    public void setStrfacebook(String strfacebook) {
        this.strfacebook = strfacebook;
    }

    public String getStrcontacto() {
        return strcontacto;
    }

    public void setStrcontacto(String strcontacto) {
        this.strcontacto = strcontacto;
    }

    public Boolean getBoolobraslocalidad() {
        return boolobraslocalidad;
    }

    public void setBoolobraslocalidad(Boolean boolobraslocalidad) {
        this.boolobraslocalidad = boolobraslocalidad;
    }

    public Integer getIntidtipoorigen() {
        return intidtipoorigen;
    }

    public void setIntidtipoorigen(Integer intidtipoorigen) {
        this.intidtipoorigen = intidtipoorigen;
    }

    public Integer getIntsector() {
        return intsector;
    }

    public void setIntsector(Integer intsector) {
        this.intsector = intsector;
    }

    public Integer getInttiposolicitante() {
        return inttiposolicitante;
    }

    public void setInttiposolicitante(Integer inttiposolicitante) {
        this.inttiposolicitante = inttiposolicitante;
    }

    public Boolean getBoolentidadnacional() {
        return boolentidadnacional;
    }

    public void setBoolentidadnacional(Boolean boolentidadnacional) {
        this.boolentidadnacional = boolentidadnacional;
    }

    public Boolean getBoolcliente() {
        return boolcliente;
    }

    public void setBoolcliente(Boolean boolcliente) {
        this.boolcliente = boolcliente;
    }

    public String getStrlegal() {
        return strlegal;
    }

    public void setStrlegal(String strlegal) {
        this.strlegal = strlegal;
    }

    @XmlTransient
    public List<Tercero> getTerceroList() {
        return terceroList;
    }

    public void setTerceroList(List<Tercero> terceroList) {
        this.terceroList = terceroList;
    }

    @XmlTransient
    public List<Tercero> getTerceroList1() {
        return terceroList1;
    }

    public void setTerceroList1(List<Tercero> terceroList1) {
        this.terceroList1 = terceroList1;
    }

    @XmlTransient
    public List<JsfUsuario> getJsfUsuarioList() {
        return jsfUsuarioList;
    }

    public void setJsfUsuarioList(List<JsfUsuario> jsfUsuarioList) {
        this.jsfUsuarioList = jsfUsuarioList;
    }

    public Tipotercero getIntcodigotipotercero() {
        return intcodigotipotercero;
    }

    public void setIntcodigotipotercero(Tipotercero intcodigotipotercero) {
        this.intcodigotipotercero = intcodigotipotercero;
    }

    public Tipoidentificacion getIntcodigotipoiden() {
        return intcodigotipoiden;
    }

    public void setIntcodigotipoiden(Tipoidentificacion intcodigotipoiden) {
        this.intcodigotipoiden = intcodigotipoiden;
    }

    public Genero getIntgenero() {
        return intgenero;
    }

    public void setIntgenero(Genero intgenero) {
        this.intgenero = intgenero;
    }

    public EstadoCivil getIntestadoCivil() {
        return intestadoCivil;
    }

    public void setIntestadoCivil(EstadoCivil intestadoCivil) {
        this.intestadoCivil = intestadoCivil;
    }

    public Dependencia getDepid() {
        return depid;
    }

    public void setDepid(Dependencia depid) {
        this.depid = depid;
    }

    public Cargo getIntcargo() {
        return intcargo;
    }

    public void setIntcargo(Cargo intcargo) {
        this.intcargo = intcargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intcodigo != null ? intcodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tercero)) {
            return false;
        }
        Tercero other = (Tercero) object;
        if ((this.intcodigo == null && other.intcodigo != null) || (this.intcodigo != null && !this.intcodigo.equals(other.intcodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.interkont.wscobra.dto.Tercero[ intcodigo=" + intcodigo + " ]";
    }
    
}
