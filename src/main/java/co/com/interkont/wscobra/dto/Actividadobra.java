package co.com.interkont.wscobra.dto;
// Generated 6/05/2020 11:05:19 AM by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.interkont.wscobra.auth.pojo.JsfUsuario;

/**
 * Actividadobra generated by hbm2java
 */
@Entity
@Table(name="actividadobra"
    ,schema="public"
)
public class Actividadobra  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long oidactiviobra;
     private Obra obra;
     private JsfUsuario jsfUsuario;
     private BigDecimal numvalorplanifao;
     private double floatcantplanifao;
     private String strdescactividad;
     private String strtipounidadmed;
     private String strcodcubs;
     private BigDecimal numvalorejecutao;
     private Double floatcantidadejecutao;
     private boolean boolaiu;
     private String strurlfoto;
     private String strurldocumento;
     private Long oididbdpu;
     private String strcodigolocalidad;
     private Boolean boolimprevisto;
     private Date fechainicio;
     private Date fechafin;
     private Double duracion;
     private Double peso;
     private Integer estado;
     private Integer tipotareagantt;
     private Integer intcodigocontrato;
     private Long fkactividadpadre;
     private Integer tipoactividad;
     private Boolean boolobligatoria;
     private Boolean booleditable;
     private Set<Relacionalimentacionactividad> relacionalimentacionactividads = new HashSet<Relacionalimentacionactividad>(0);

    public Actividadobra() {
    }
    
    public Actividadobra(long oidactiviobra) {
		super();
		this.oidactiviobra = oidactiviobra;
	}

	public Actividadobra(long oidactiviobra, JsfUsuario jsfUsuario, BigDecimal numvalorplanifao, double floatcantplanifao, boolean boolaiu) {
        this.oidactiviobra = oidactiviobra;
        this.jsfUsuario = jsfUsuario;
        this.numvalorplanifao = numvalorplanifao;
        this.floatcantplanifao = floatcantplanifao;
        this.boolaiu = boolaiu;
    }
    public Actividadobra(long oidactiviobra, Obra obra, JsfUsuario jsfUsuario, BigDecimal numvalorplanifao, double floatcantplanifao, String strdescactividad, String strtipounidadmed, String strcodcubs, BigDecimal numvalorejecutao, Double floatcantidadejecutao, boolean boolaiu, String strurlfoto, String strurldocumento, Long oididbdpu, String strcodigolocalidad, Boolean boolimprevisto, Date fechainicio, Date fechafin, Double duracion, Double peso, Integer estado, Integer tipotareagantt, Integer intcodigocontrato, Long fkactividadpadre, Integer tipoactividad, Boolean boolobligatoria, Boolean booleditable) {
       this.oidactiviobra = oidactiviobra;
       this.obra = obra;
       this.jsfUsuario = jsfUsuario;
       this.numvalorplanifao = numvalorplanifao;
       this.floatcantplanifao = floatcantplanifao;
       this.strdescactividad = strdescactividad;
       this.strtipounidadmed = strtipounidadmed;
       this.strcodcubs = strcodcubs;
       this.numvalorejecutao = numvalorejecutao;
       this.floatcantidadejecutao = floatcantidadejecutao;
       this.boolaiu = boolaiu;
       this.strurlfoto = strurlfoto;
       this.strurldocumento = strurldocumento;
       this.oididbdpu = oididbdpu;
       this.strcodigolocalidad = strcodigolocalidad;
       this.boolimprevisto = boolimprevisto;
       this.fechainicio = fechainicio;
       this.fechafin = fechafin;
       this.duracion = duracion;
       this.peso = peso;
       this.estado = estado;
       this.tipotareagantt = tipotareagantt;
       this.intcodigocontrato = intcodigocontrato;
       this.fkactividadpadre = fkactividadpadre;
       this.tipoactividad = tipoactividad;
       this.boolobligatoria = boolobligatoria;
       this.booleditable = booleditable;
    }
    @Id
    @Column(name="oidactiviobra", unique=true, nullable=false)
    public long getOidactiviobra() {
        return this.oidactiviobra;
    }
    
    public void setOidactiviobra(long oidactiviobra) {
        this.oidactiviobra = oidactiviobra;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="intcodigoobra")
    public Obra getObra() {
        return this.obra;
    }
    
    public void setObra(Obra obra) {
        this.obra = obra;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="intcedula", nullable=false, columnDefinition="int8")
    public JsfUsuario getJsfUsuario() {
        return this.jsfUsuario;
    }
    
    public void setJsfUsuario(JsfUsuario jsfUsuario) {
        this.jsfUsuario = jsfUsuario;
    }

    
    @Column(name="numvalorplanifao", nullable=false, precision=20, scale=6)
    public BigDecimal getNumvalorplanifao() {
        return this.numvalorplanifao;
    }
    
    public void setNumvalorplanifao(BigDecimal numvalorplanifao) {
        this.numvalorplanifao = numvalorplanifao;
    }

    
    @Column(name="floatcantplanifao", nullable=false, precision=17, scale=17)
    public double getFloatcantplanifao() {
        return this.floatcantplanifao;
    }
    
    public void setFloatcantplanifao(double floatcantplanifao) {
        this.floatcantplanifao = floatcantplanifao;
    }

    
    @Column(name="strdescactividad")
    public String getStrdescactividad() {
        return this.strdescactividad;
    }
    
    public void setStrdescactividad(String strdescactividad) {
        this.strdescactividad = strdescactividad;
    }

    
    @Column(name="strtipounidadmed", length=10)
    public String getStrtipounidadmed() {
        return this.strtipounidadmed;
    }
    
    public void setStrtipounidadmed(String strtipounidadmed) {
        this.strtipounidadmed = strtipounidadmed;
    }

    
    @Column(name="strcodcubs", length=20)
    public String getStrcodcubs() {
        return this.strcodcubs;
    }
    
    public void setStrcodcubs(String strcodcubs) {
        this.strcodcubs = strcodcubs;
    }

    
    @Column(name="numvalorejecutao", precision=20, scale=6)
    public BigDecimal getNumvalorejecutao() {
        return this.numvalorejecutao;
    }
    
    public void setNumvalorejecutao(BigDecimal numvalorejecutao) {
        this.numvalorejecutao = numvalorejecutao;
    }

    
    @Column(name="floatcantidadejecutao", precision=17, scale=17)
    public Double getFloatcantidadejecutao() {
        return this.floatcantidadejecutao;
    }
    
    public void setFloatcantidadejecutao(Double floatcantidadejecutao) {
        this.floatcantidadejecutao = floatcantidadejecutao;
    }

    
    @Column(name="boolaiu", nullable=false)
    public boolean isBoolaiu() {
        return this.boolaiu;
    }
    
    public void setBoolaiu(boolean boolaiu) {
        this.boolaiu = boolaiu;
    }

    
    @Column(name="strurlfoto")
    public String getStrurlfoto() {
        return this.strurlfoto;
    }
    
    public void setStrurlfoto(String strurlfoto) {
        this.strurlfoto = strurlfoto;
    }

    
    @Column(name="strurldocumento")
    public String getStrurldocumento() {
        return this.strurldocumento;
    }
    
    public void setStrurldocumento(String strurldocumento) {
        this.strurldocumento = strurldocumento;
    }

    
    @Column(name="oididbdpu")
    public Long getOididbdpu() {
        return this.oididbdpu;
    }
    
    public void setOididbdpu(Long oididbdpu) {
        this.oididbdpu = oididbdpu;
    }

    
    @Column(name="strcodigolocalidad", length=10)
    public String getStrcodigolocalidad() {
        return this.strcodigolocalidad;
    }
    
    public void setStrcodigolocalidad(String strcodigolocalidad) {
        this.strcodigolocalidad = strcodigolocalidad;
    }

    
    @Column(name="boolimprevisto")
    public Boolean getBoolimprevisto() {
        return this.boolimprevisto;
    }
    
    public void setBoolimprevisto(Boolean boolimprevisto) {
        this.boolimprevisto = boolimprevisto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechainicio", length=29)
    public Date getFechainicio() {
        return this.fechainicio;
    }
    
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechafin", length=29)
    public Date getFechafin() {
        return this.fechafin;
    }
    
    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    
    @Column(name="duracion", precision=17, scale=17)
    public Double getDuracion() {
        return this.duracion;
    }
    
    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    
    @Column(name="peso", precision=17, scale=17)
    public Double getPeso() {
        return this.peso;
    }
    
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    
    @Column(name="estado")
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
    @Column(name="tipotareagantt")
    public Integer getTipotareagantt() {
        return this.tipotareagantt;
    }
    
    public void setTipotareagantt(Integer tipotareagantt) {
        this.tipotareagantt = tipotareagantt;
    }

    
    @Column(name="intcodigocontrato")
    public Integer getIntcodigocontrato() {
        return this.intcodigocontrato;
    }
    
    public void setIntcodigocontrato(Integer intcodigocontrato) {
        this.intcodigocontrato = intcodigocontrato;
    }

    
    @Column(name="fkactividadpadre")
    public Long getFkactividadpadre() {
        return this.fkactividadpadre;
    }
    
    public void setFkactividadpadre(Long fkactividadpadre) {
        this.fkactividadpadre = fkactividadpadre;
    }

    
    @Column(name="tipoactividad")
    public Integer getTipoactividad() {
        return this.tipoactividad;
    }
    
    public void setTipoactividad(Integer tipoactividad) {
        this.tipoactividad = tipoactividad;
    }

    
    @Column(name="boolobligatoria")
    public Boolean getBoolobligatoria() {
        return this.boolobligatoria;
    }
    
    public void setBoolobligatoria(Boolean boolobligatoria) {
        this.boolobligatoria = boolobligatoria;
    }

    
    @Column(name="booleditable")
    public Boolean getBooleditable() {
        return this.booleditable;
    }
    
    public void setBooleditable(Boolean booleditable) {
        this.booleditable = booleditable;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="actividadobra")
    public Set<Relacionalimentacionactividad> getRelacionalimentacionactividads() {
        return this.relacionalimentacionactividads;
    }
    
    public void setRelacionalimentacionactividads(Set<Relacionalimentacionactividad> relacionalimentacionactividads) {
        this.relacionalimentacionactividads = relacionalimentacionactividads;
    }




}


