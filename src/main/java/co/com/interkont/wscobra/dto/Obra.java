package co.com.interkont.wscobra.dto;
// Generated 7/05/2020 03:33:23 PM by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.interkont.wscobra.auth.pojo.JsfUsuario;

/**
 * Obra generated by hbm2java
 */
@Entity
@Table(name="obra"
    ,schema="public"
)
public class Obra  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int intcodigoobra;
     private Obra obra;
     private JsfUsuario jsfUsuario;
     private String strnombreobra;
     private int intplazoobra;
     private float floatporadmon;
     private float floatporimprevi;
     private float floatporutilidad;
     private float floatporivasobreutil;
     private String strdireccion;
     private BigDecimal floatlatitud;
     private BigDecimal floatlongitud;
     private String strcomuna;
     private String strbarrio;
     private String strvereda;
     private String strobjetoobra;
     private Date datefeciniobra;
     private Date datefecfinobra;
     private String strimagenobra;
     private BigDecimal numvaltotobra;
     private String strcontratista;
     private BigDecimal numvalejecobra;
     private Date datefecresaprobobra;
     private BigDecimal numvaltotamorti;
     private String strinterventor;
     private String strcorregimiento;
     private String strresaprobobra;
     private String strnrocontrato;
     private String strnumcuenta;
     private BigDecimal numvaloranticipo;
     private float floatporotros;
     private String strestydis;
     private String strurlcronograma;
     private Date datefecrecfin;
     private Date datefecliqui;
     private boolean booleanfrenteobra;
     private Integer numempdirectos;
     private Integer numempindirectos;
     private Integer numhabbeneficiados;
     private String strconclucompro;
     private boolean booltieneplanos;
     private boolean booltieneespe;
     private Date datefechaactainiconint;
     private Date datefechaactafinconint;
     private String strlogros;
     private int intidclaseobra;
     private Integer intentidad;
     private int intidlugarobra;
     private int intidperiomedida;
     private int inttipoobra;
     private int intestadoobra;
     private int inttipocosto;
     private int intidtipoorigen;
     private int intidperiodoevento;
     private Integer intcodigobarrio;
     private Integer intcodigovereda;
     private String strimagenanterior;
     private Integer fksolicitudObra;
     private boolean enalimentacion;
     private BigDecimal numvaldeclarado;
     private BigDecimal numvalavanfisicodeclarado;
     private BigDecimal numvalavanfinanciaerodeclarado;
     private boolean booleantienehijos;
     private BigDecimal numvlrsumahijos;
     private boolean boolincluyeaiu;
     private Integer intconvenio;
     private Integer inttiposolicitante;
     private BigDecimal numvalprogramejec;
     private String strcodigopropio;
     private Integer fksolicitudAh;
     private String strcodigosedeeducativa;
     private String strcodigotramo;
     private boolean boolobraterminada;
     private Integer usuModifica;
     private Date dateusuModifica;
     private Long numvisitas;
     private Date dateactualizacionvisitas;
     private Double pagodirecto;
     private Double otrospagos;
     private BigDecimal valordisponible;
     private Boolean boolplanoperativo;
     private Long codigoSupervisor;
     private Long codigoInterventor;
     private Integer lineanegocio;
     private Integer intIdterceroConstructor;
     private Integer intidsubsector;
     private Set<Actividadobra> actividadobras = new HashSet<Actividadobra>(0);
     private Set<Periodo> periodos = new HashSet<Periodo>(0);
     private Set<Videoevolucionobra> videoevolucionobras = new HashSet<Videoevolucionobra>(0);
     private Set<Alimentacion> alimentacions = new HashSet<Alimentacion>(0);
     private Set<Imagenevolucionobra> imagenevolucionobras = new HashSet<Imagenevolucionobra>(0);
     private Set<Obra> obras = new HashSet<Obra>(0);

    public Obra() {
    }
    
    

	
    public Obra(int intcodigoobra) {
		super();
		this.intcodigoobra = intcodigoobra;
	}




	public Obra(int intcodigoobra, JsfUsuario jsfUsuario, String strnombreobra, int intplazoobra, float floatporadmon, float floatporimprevi, float floatporutilidad, float floatporivasobreutil, BigDecimal numvaltotobra, float floatporotros, boolean booleanfrenteobra, boolean booltieneplanos, boolean booltieneespe, int intidclaseobra, int intidlugarobra, int intidperiomedida, int inttipoobra, int intestadoobra, int inttipocosto, int intidtipoorigen, int intidperiodoevento, boolean enalimentacion, boolean booleantienehijos, boolean boolincluyeaiu, boolean boolobraterminada) {
        this.intcodigoobra = intcodigoobra;
        this.jsfUsuario = jsfUsuario;
        this.strnombreobra = strnombreobra;
        this.intplazoobra = intplazoobra;
        this.floatporadmon = floatporadmon;
        this.floatporimprevi = floatporimprevi;
        this.floatporutilidad = floatporutilidad;
        this.floatporivasobreutil = floatporivasobreutil;
        this.numvaltotobra = numvaltotobra;
        this.floatporotros = floatporotros;
        this.booleanfrenteobra = booleanfrenteobra;
        this.booltieneplanos = booltieneplanos;
        this.booltieneespe = booltieneespe;
        this.intidclaseobra = intidclaseobra;
        this.intidlugarobra = intidlugarobra;
        this.intidperiomedida = intidperiomedida;
        this.inttipoobra = inttipoobra;
        this.intestadoobra = intestadoobra;
        this.inttipocosto = inttipocosto;
        this.intidtipoorigen = intidtipoorigen;
        this.intidperiodoevento = intidperiodoevento;
        this.enalimentacion = enalimentacion;
        this.booleantienehijos = booleantienehijos;
        this.boolincluyeaiu = boolincluyeaiu;
        this.boolobraterminada = boolobraterminada;
    }
    public Obra(int intcodigoobra, Obra obra, JsfUsuario jsfUsuario, String strnombreobra, int intplazoobra, float floatporadmon, float floatporimprevi, float floatporutilidad, float floatporivasobreutil, String strdireccion, BigDecimal floatlatitud, BigDecimal floatlongitud, String strcomuna, String strbarrio, String strvereda, String strobjetoobra, Date datefeciniobra, Date datefecfinobra, String strimagenobra, BigDecimal numvaltotobra, String strcontratista, BigDecimal numvalejecobra, Date datefecresaprobobra, BigDecimal numvaltotamorti, String strinterventor, String strcorregimiento, String strresaprobobra, String strnrocontrato, String strnumcuenta, BigDecimal numvaloranticipo, float floatporotros, String strestydis, String strurlcronograma, Date datefecrecfin, Date datefecliqui, boolean booleanfrenteobra, Integer numempdirectos, Integer numempindirectos, Integer numhabbeneficiados, String strconclucompro, boolean booltieneplanos, boolean booltieneespe, Date datefechaactainiconint, Date datefechaactafinconint, String strlogros, int intidclaseobra, Integer intentidad, int intidlugarobra, int intidperiomedida, int inttipoobra, int intestadoobra, int inttipocosto, int intidtipoorigen, int intidperiodoevento, Integer intcodigobarrio, Integer intcodigovereda, String strimagenanterior, Integer fksolicitudObra, boolean enalimentacion, BigDecimal numvaldeclarado, BigDecimal numvalavanfisicodeclarado, BigDecimal numvalavanfinanciaerodeclarado, boolean booleantienehijos, BigDecimal numvlrsumahijos, boolean boolincluyeaiu, Integer intconvenio, Integer inttiposolicitante, BigDecimal numvalprogramejec, String strcodigopropio, Integer fksolicitudAh, String strcodigosedeeducativa, String strcodigotramo, boolean boolobraterminada, Integer usuModifica, Date dateusuModifica, Long numvisitas, Date dateactualizacionvisitas, Double pagodirecto, Double otrospagos, BigDecimal valordisponible, Boolean boolplanoperativo, Long codigoSupervisor, Long codigoInterventor, Integer lineanegocio, Integer intIdterceroConstructor, Integer intidsubsector, Set<Actividadobra> actividadobras, Set<Periodo> periodos, Set<Videoevolucionobra> videoevolucionobras, Set<Alimentacion> alimentacions, Set<Imagenevolucionobra> imagenevolucionobras, Set<Obra> obras) {
       this.intcodigoobra = intcodigoobra;
       this.obra = obra;
       this.jsfUsuario = jsfUsuario;
       this.strnombreobra = strnombreobra;
       this.intplazoobra = intplazoobra;
       this.floatporadmon = floatporadmon;
       this.floatporimprevi = floatporimprevi;
       this.floatporutilidad = floatporutilidad;
       this.floatporivasobreutil = floatporivasobreutil;
       this.strdireccion = strdireccion;
       this.floatlatitud = floatlatitud;
       this.floatlongitud = floatlongitud;
       this.strcomuna = strcomuna;
       this.strbarrio = strbarrio;
       this.strvereda = strvereda;
       this.strobjetoobra = strobjetoobra;
       this.datefeciniobra = datefeciniobra;
       this.datefecfinobra = datefecfinobra;
       this.strimagenobra = strimagenobra;
       this.numvaltotobra = numvaltotobra;
       this.strcontratista = strcontratista;
       this.numvalejecobra = numvalejecobra;
       this.datefecresaprobobra = datefecresaprobobra;
       this.numvaltotamorti = numvaltotamorti;
       this.strinterventor = strinterventor;
       this.strcorregimiento = strcorregimiento;
       this.strresaprobobra = strresaprobobra;
       this.strnrocontrato = strnrocontrato;
       this.strnumcuenta = strnumcuenta;
       this.numvaloranticipo = numvaloranticipo;
       this.floatporotros = floatporotros;
       this.strestydis = strestydis;
       this.strurlcronograma = strurlcronograma;
       this.datefecrecfin = datefecrecfin;
       this.datefecliqui = datefecliqui;
       this.booleanfrenteobra = booleanfrenteobra;
       this.numempdirectos = numempdirectos;
       this.numempindirectos = numempindirectos;
       this.numhabbeneficiados = numhabbeneficiados;
       this.strconclucompro = strconclucompro;
       this.booltieneplanos = booltieneplanos;
       this.booltieneespe = booltieneespe;
       this.datefechaactainiconint = datefechaactainiconint;
       this.datefechaactafinconint = datefechaactafinconint;
       this.strlogros = strlogros;
       this.intidclaseobra = intidclaseobra;
       this.intentidad = intentidad;
       this.intidlugarobra = intidlugarobra;
       this.intidperiomedida = intidperiomedida;
       this.inttipoobra = inttipoobra;
       this.intestadoobra = intestadoobra;
       this.inttipocosto = inttipocosto;
       this.intidtipoorigen = intidtipoorigen;
       this.intidperiodoevento = intidperiodoevento;
       this.intcodigobarrio = intcodigobarrio;
       this.intcodigovereda = intcodigovereda;
       this.strimagenanterior = strimagenanterior;
       this.fksolicitudObra = fksolicitudObra;
       this.enalimentacion = enalimentacion;
       this.numvaldeclarado = numvaldeclarado;
       this.numvalavanfisicodeclarado = numvalavanfisicodeclarado;
       this.numvalavanfinanciaerodeclarado = numvalavanfinanciaerodeclarado;
       this.booleantienehijos = booleantienehijos;
       this.numvlrsumahijos = numvlrsumahijos;
       this.boolincluyeaiu = boolincluyeaiu;
       this.intconvenio = intconvenio;
       this.inttiposolicitante = inttiposolicitante;
       this.numvalprogramejec = numvalprogramejec;
       this.strcodigopropio = strcodigopropio;
       this.fksolicitudAh = fksolicitudAh;
       this.strcodigosedeeducativa = strcodigosedeeducativa;
       this.strcodigotramo = strcodigotramo;
       this.boolobraterminada = boolobraterminada;
       this.usuModifica = usuModifica;
       this.dateusuModifica = dateusuModifica;
       this.numvisitas = numvisitas;
       this.dateactualizacionvisitas = dateactualizacionvisitas;
       this.pagodirecto = pagodirecto;
       this.otrospagos = otrospagos;
       this.valordisponible = valordisponible;
       this.boolplanoperativo = boolplanoperativo;
       this.codigoSupervisor = codigoSupervisor;
       this.codigoInterventor = codigoInterventor;
       this.lineanegocio = lineanegocio;
       this.intIdterceroConstructor = intIdterceroConstructor;
       this.intidsubsector = intidsubsector;
       this.actividadobras = actividadobras;
       this.periodos = periodos;
       this.videoevolucionobras = videoevolucionobras;
       this.alimentacions = alimentacions;
       this.imagenevolucionobras = imagenevolucionobras;
       this.obras = obras;
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obraSequenceGenerator")
    @SequenceGenerator(name = "obraSequenceGenerator", sequenceName="obra_intcodigoobra_seq")
    @Column(name="intcodigoobra", unique=true, nullable=false)
    public int getIntcodigoobra() {
        return this.intcodigoobra;
    }
    
    public void setIntcodigoobra(int intcodigoobra) {
        this.intcodigoobra = intcodigoobra;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_intcodigoobra")
    public Obra getObra() {
        return this.obra;
    }
    
    public void setObra(Obra obra) {
        this.obra = obra;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="intcedula", nullable=false)
    public JsfUsuario getJsfUsuario() {
        return this.jsfUsuario;
    }
    
    public void setJsfUsuario(JsfUsuario jsfUsuario) {
        this.jsfUsuario = jsfUsuario;
    }

    
    @Column(name="strnombreobra", nullable=false)
    public String getStrnombreobra() {
        return this.strnombreobra;
    }
    
    public void setStrnombreobra(String strnombreobra) {
        this.strnombreobra = strnombreobra;
    }

    
    @Column(name="intplazoobra", nullable=false)
    public int getIntplazoobra() {
        return this.intplazoobra;
    }
    
    public void setIntplazoobra(int intplazoobra) {
        this.intplazoobra = intplazoobra;
    }

    
    @Column(name="floatporadmon", nullable=false, precision=8, scale=8)
    public float getFloatporadmon() {
        return this.floatporadmon;
    }
    
    public void setFloatporadmon(float floatporadmon) {
        this.floatporadmon = floatporadmon;
    }

    
    @Column(name="floatporimprevi", nullable=false, precision=8, scale=8)
    public float getFloatporimprevi() {
        return this.floatporimprevi;
    }
    
    public void setFloatporimprevi(float floatporimprevi) {
        this.floatporimprevi = floatporimprevi;
    }

    
    @Column(name="floatporutilidad", nullable=false, precision=8, scale=8)
    public float getFloatporutilidad() {
        return this.floatporutilidad;
    }
    
    public void setFloatporutilidad(float floatporutilidad) {
        this.floatporutilidad = floatporutilidad;
    }

    
    @Column(name="floatporivasobreutil", nullable=false, precision=8, scale=8)
    public float getFloatporivasobreutil() {
        return this.floatporivasobreutil;
    }
    
    public void setFloatporivasobreutil(float floatporivasobreutil) {
        this.floatporivasobreutil = floatporivasobreutil;
    }

    
    @Column(name="strdireccion", length=400)
    public String getStrdireccion() {
        return this.strdireccion;
    }
    
    public void setStrdireccion(String strdireccion) {
        this.strdireccion = strdireccion;
    }

    
    @Column(name="floatlatitud", precision=20, scale=14)
    public BigDecimal getFloatlatitud() {
        return this.floatlatitud;
    }
    
    public void setFloatlatitud(BigDecimal floatlatitud) {
        this.floatlatitud = floatlatitud;
    }

    
    @Column(name="floatlongitud", precision=20, scale=14)
    public BigDecimal getFloatlongitud() {
        return this.floatlongitud;
    }
    
    public void setFloatlongitud(BigDecimal floatlongitud) {
        this.floatlongitud = floatlongitud;
    }

    
    @Column(name="strcomuna", length=100)
    public String getStrcomuna() {
        return this.strcomuna;
    }
    
    public void setStrcomuna(String strcomuna) {
        this.strcomuna = strcomuna;
    }

    
    @Column(name="strbarrio", length=100)
    public String getStrbarrio() {
        return this.strbarrio;
    }
    
    public void setStrbarrio(String strbarrio) {
        this.strbarrio = strbarrio;
    }

    
    @Column(name="strvereda", length=100)
    public String getStrvereda() {
        return this.strvereda;
    }
    
    public void setStrvereda(String strvereda) {
        this.strvereda = strvereda;
    }

    
    @Column(name="strobjetoobra")
    public String getStrobjetoobra() {
        return this.strobjetoobra;
    }
    
    public void setStrobjetoobra(String strobjetoobra) {
        this.strobjetoobra = strobjetoobra;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datefeciniobra", length=13)
    public Date getDatefeciniobra() {
        return this.datefeciniobra;
    }
    
    public void setDatefeciniobra(Date datefeciniobra) {
        this.datefeciniobra = datefeciniobra;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datefecfinobra", length=13)
    public Date getDatefecfinobra() {
        return this.datefecfinobra;
    }
    
    public void setDatefecfinobra(Date datefecfinobra) {
        this.datefecfinobra = datefecfinobra;
    }

    
    @Column(name="strimagenobra", length=200)
    public String getStrimagenobra() {
        return this.strimagenobra;
    }
    
    public void setStrimagenobra(String strimagenobra) {
        this.strimagenobra = strimagenobra;
    }

    
    @Column(name="numvaltotobra", nullable=false, precision=20, scale=6)
    public BigDecimal getNumvaltotobra() {
        return this.numvaltotobra;
    }
    
    public void setNumvaltotobra(BigDecimal numvaltotobra) {
        this.numvaltotobra = numvaltotobra;
    }

    
    @Column(name="strcontratista", length=100)
    public String getStrcontratista() {
        return this.strcontratista;
    }
    
    public void setStrcontratista(String strcontratista) {
        this.strcontratista = strcontratista;
    }

    
    @Column(name="numvalejecobra", precision=20, scale=6)
    public BigDecimal getNumvalejecobra() {
        return this.numvalejecobra;
    }
    
    public void setNumvalejecobra(BigDecimal numvalejecobra) {
        this.numvalejecobra = numvalejecobra;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datefecresaprobobra", length=13)
    public Date getDatefecresaprobobra() {
        return this.datefecresaprobobra;
    }
    
    public void setDatefecresaprobobra(Date datefecresaprobobra) {
        this.datefecresaprobobra = datefecresaprobobra;
    }

    
    @Column(name="numvaltotamorti", precision=20, scale=6)
    public BigDecimal getNumvaltotamorti() {
        return this.numvaltotamorti;
    }
    
    public void setNumvaltotamorti(BigDecimal numvaltotamorti) {
        this.numvaltotamorti = numvaltotamorti;
    }

    
    @Column(name="strinterventor", length=100)
    public String getStrinterventor() {
        return this.strinterventor;
    }
    
    public void setStrinterventor(String strinterventor) {
        this.strinterventor = strinterventor;
    }

    
    @Column(name="strcorregimiento", length=100)
    public String getStrcorregimiento() {
        return this.strcorregimiento;
    }
    
    public void setStrcorregimiento(String strcorregimiento) {
        this.strcorregimiento = strcorregimiento;
    }

    
    @Column(name="strresaprobobra", length=200)
    public String getStrresaprobobra() {
        return this.strresaprobobra;
    }
    
    public void setStrresaprobobra(String strresaprobobra) {
        this.strresaprobobra = strresaprobobra;
    }

    
    @Column(name="strnrocontrato", length=100)
    public String getStrnrocontrato() {
        return this.strnrocontrato;
    }
    
    public void setStrnrocontrato(String strnrocontrato) {
        this.strnrocontrato = strnrocontrato;
    }

    
    @Column(name="strnumcuenta", length=30)
    public String getStrnumcuenta() {
        return this.strnumcuenta;
    }
    
    public void setStrnumcuenta(String strnumcuenta) {
        this.strnumcuenta = strnumcuenta;
    }

    
    @Column(name="numvaloranticipo", precision=20, scale=6)
    public BigDecimal getNumvaloranticipo() {
        return this.numvaloranticipo;
    }
    
    public void setNumvaloranticipo(BigDecimal numvaloranticipo) {
        this.numvaloranticipo = numvaloranticipo;
    }

    
    @Column(name="floatporotros", nullable=false, precision=8, scale=8)
    public float getFloatporotros() {
        return this.floatporotros;
    }
    
    public void setFloatporotros(float floatporotros) {
        this.floatporotros = floatporotros;
    }

    
    @Column(name="strestydis", length=100)
    public String getStrestydis() {
        return this.strestydis;
    }
    
    public void setStrestydis(String strestydis) {
        this.strestydis = strestydis;
    }

    
    @Column(name="strurlcronograma")
    public String getStrurlcronograma() {
        return this.strurlcronograma;
    }
    
    public void setStrurlcronograma(String strurlcronograma) {
        this.strurlcronograma = strurlcronograma;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datefecrecfin", length=13)
    public Date getDatefecrecfin() {
        return this.datefecrecfin;
    }
    
    public void setDatefecrecfin(Date datefecrecfin) {
        this.datefecrecfin = datefecrecfin;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datefecliqui", length=13)
    public Date getDatefecliqui() {
        return this.datefecliqui;
    }
    
    public void setDatefecliqui(Date datefecliqui) {
        this.datefecliqui = datefecliqui;
    }

    
    @Column(name="booleanfrenteobra", nullable=false)
    public boolean isBooleanfrenteobra() {
        return this.booleanfrenteobra;
    }
    
    public void setBooleanfrenteobra(boolean booleanfrenteobra) {
        this.booleanfrenteobra = booleanfrenteobra;
    }

    
    @Column(name="numempdirectos")
    public Integer getNumempdirectos() {
        return this.numempdirectos;
    }
    
    public void setNumempdirectos(Integer numempdirectos) {
        this.numempdirectos = numempdirectos;
    }

    
    @Column(name="numempindirectos")
    public Integer getNumempindirectos() {
        return this.numempindirectos;
    }
    
    public void setNumempindirectos(Integer numempindirectos) {
        this.numempindirectos = numempindirectos;
    }

    
    @Column(name="numhabbeneficiados")
    public Integer getNumhabbeneficiados() {
        return this.numhabbeneficiados;
    }
    
    public void setNumhabbeneficiados(Integer numhabbeneficiados) {
        this.numhabbeneficiados = numhabbeneficiados;
    }

    
    @Column(name="strconclucompro")
    public String getStrconclucompro() {
        return this.strconclucompro;
    }
    
    public void setStrconclucompro(String strconclucompro) {
        this.strconclucompro = strconclucompro;
    }

    
    @Column(name="booltieneplanos", nullable=false)
    public boolean isBooltieneplanos() {
        return this.booltieneplanos;
    }
    
    public void setBooltieneplanos(boolean booltieneplanos) {
        this.booltieneplanos = booltieneplanos;
    }

    
    @Column(name="booltieneespe", nullable=false)
    public boolean isBooltieneespe() {
        return this.booltieneespe;
    }
    
    public void setBooltieneespe(boolean booltieneespe) {
        this.booltieneespe = booltieneespe;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datefechaactainiconint", length=13)
    public Date getDatefechaactainiconint() {
        return this.datefechaactainiconint;
    }
    
    public void setDatefechaactainiconint(Date datefechaactainiconint) {
        this.datefechaactainiconint = datefechaactainiconint;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datefechaactafinconint", length=13)
    public Date getDatefechaactafinconint() {
        return this.datefechaactafinconint;
    }
    
    public void setDatefechaactafinconint(Date datefechaactafinconint) {
        this.datefechaactafinconint = datefechaactafinconint;
    }

    
    @Column(name="strlogros")
    public String getStrlogros() {
        return this.strlogros;
    }
    
    public void setStrlogros(String strlogros) {
        this.strlogros = strlogros;
    }

    
    @Column(name="intidclaseobra", nullable=false)
    public int getIntidclaseobra() {
        return this.intidclaseobra;
    }
    
    public void setIntidclaseobra(int intidclaseobra) {
        this.intidclaseobra = intidclaseobra;
    }

    
    @Column(name="intentidad")
    public Integer getIntentidad() {
        return this.intentidad;
    }
    
    public void setIntentidad(Integer intentidad) {
        this.intentidad = intentidad;
    }

    
    @Column(name="intidlugarobra", nullable=false)
    public int getIntidlugarobra() {
        return this.intidlugarobra;
    }
    
    public void setIntidlugarobra(int intidlugarobra) {
        this.intidlugarobra = intidlugarobra;
    }

    
    @Column(name="intidperiomedida", nullable=false)
    public int getIntidperiomedida() {
        return this.intidperiomedida;
    }
    
    public void setIntidperiomedida(int intidperiomedida) {
        this.intidperiomedida = intidperiomedida;
    }

    
    @Column(name="inttipoobra", nullable=false)
    public int getInttipoobra() {
        return this.inttipoobra;
    }
    
    public void setInttipoobra(int inttipoobra) {
        this.inttipoobra = inttipoobra;
    }

    
    @Column(name="intestadoobra", nullable=false)
    public int getIntestadoobra() {
        return this.intestadoobra;
    }
    
    public void setIntestadoobra(int intestadoobra) {
        this.intestadoobra = intestadoobra;
    }

    
    @Column(name="inttipocosto", nullable=false)
    public int getInttipocosto() {
        return this.inttipocosto;
    }
    
    public void setInttipocosto(int inttipocosto) {
        this.inttipocosto = inttipocosto;
    }

    
    @Column(name="intidtipoorigen", nullable=false)
    public int getIntidtipoorigen() {
        return this.intidtipoorigen;
    }
    
    public void setIntidtipoorigen(int intidtipoorigen) {
        this.intidtipoorigen = intidtipoorigen;
    }

    
    @Column(name="intidperiodoevento", nullable=false)
    public int getIntidperiodoevento() {
        return this.intidperiodoevento;
    }
    
    public void setIntidperiodoevento(int intidperiodoevento) {
        this.intidperiodoevento = intidperiodoevento;
    }

    
    @Column(name="intcodigobarrio")
    public Integer getIntcodigobarrio() {
        return this.intcodigobarrio;
    }
    
    public void setIntcodigobarrio(Integer intcodigobarrio) {
        this.intcodigobarrio = intcodigobarrio;
    }

    
    @Column(name="intcodigovereda")
    public Integer getIntcodigovereda() {
        return this.intcodigovereda;
    }
    
    public void setIntcodigovereda(Integer intcodigovereda) {
        this.intcodigovereda = intcodigovereda;
    }

    
    @Column(name="strimagenanterior")
    public String getStrimagenanterior() {
        return this.strimagenanterior;
    }
    
    public void setStrimagenanterior(String strimagenanterior) {
        this.strimagenanterior = strimagenanterior;
    }

    
    @Column(name="fksolicitud_obra")
    public Integer getFksolicitudObra() {
        return this.fksolicitudObra;
    }
    
    public void setFksolicitudObra(Integer fksolicitudObra) {
        this.fksolicitudObra = fksolicitudObra;
    }

    
    @Column(name="enalimentacion", nullable=false)
    public boolean isEnalimentacion() {
        return this.enalimentacion;
    }
    
    public void setEnalimentacion(boolean enalimentacion) {
        this.enalimentacion = enalimentacion;
    }

    
    @Column(name="numvaldeclarado", precision=20, scale=6)
    public BigDecimal getNumvaldeclarado() {
        return this.numvaldeclarado;
    }
    
    public void setNumvaldeclarado(BigDecimal numvaldeclarado) {
        this.numvaldeclarado = numvaldeclarado;
    }

    
    @Column(name="numvalavanfisicodeclarado", precision=20, scale=6)
    public BigDecimal getNumvalavanfisicodeclarado() {
        return this.numvalavanfisicodeclarado;
    }
    
    public void setNumvalavanfisicodeclarado(BigDecimal numvalavanfisicodeclarado) {
        this.numvalavanfisicodeclarado = numvalavanfisicodeclarado;
    }

    
    @Column(name="numvalavanfinanciaerodeclarado", precision=20, scale=6)
    public BigDecimal getNumvalavanfinanciaerodeclarado() {
        return this.numvalavanfinanciaerodeclarado;
    }
    
    public void setNumvalavanfinanciaerodeclarado(BigDecimal numvalavanfinanciaerodeclarado) {
        this.numvalavanfinanciaerodeclarado = numvalavanfinanciaerodeclarado;
    }

    
    @Column(name="booleantienehijos", nullable=false)
    public boolean isBooleantienehijos() {
        return this.booleantienehijos;
    }
    
    public void setBooleantienehijos(boolean booleantienehijos) {
        this.booleantienehijos = booleantienehijos;
    }

    
    @Column(name="numvlrsumahijos", precision=20, scale=6)
    public BigDecimal getNumvlrsumahijos() {
        return this.numvlrsumahijos;
    }
    
    public void setNumvlrsumahijos(BigDecimal numvlrsumahijos) {
        this.numvlrsumahijos = numvlrsumahijos;
    }

    
    @Column(name="boolincluyeaiu", nullable=false)
    public boolean isBoolincluyeaiu() {
        return this.boolincluyeaiu;
    }
    
    public void setBoolincluyeaiu(boolean boolincluyeaiu) {
        this.boolincluyeaiu = boolincluyeaiu;
    }

    
    @Column(name="intconvenio")
    public Integer getIntconvenio() {
        return this.intconvenio;
    }
    
    public void setIntconvenio(Integer intconvenio) {
        this.intconvenio = intconvenio;
    }

    
    @Column(name="inttiposolicitante")
    public Integer getInttiposolicitante() {
        return this.inttiposolicitante;
    }
    
    public void setInttiposolicitante(Integer inttiposolicitante) {
        this.inttiposolicitante = inttiposolicitante;
    }

    
    @Column(name="numvalprogramejec", precision=20, scale=6)
    public BigDecimal getNumvalprogramejec() {
        return this.numvalprogramejec;
    }
    
    public void setNumvalprogramejec(BigDecimal numvalprogramejec) {
        this.numvalprogramejec = numvalprogramejec;
    }

    
    @Column(name="strcodigopropio")
    public String getStrcodigopropio() {
        return this.strcodigopropio;
    }
    
    public void setStrcodigopropio(String strcodigopropio) {
        this.strcodigopropio = strcodigopropio;
    }

    
    @Column(name="fksolicitud_ah")
    public Integer getFksolicitudAh() {
        return this.fksolicitudAh;
    }
    
    public void setFksolicitudAh(Integer fksolicitudAh) {
        this.fksolicitudAh = fksolicitudAh;
    }

    
    @Column(name="strcodigosedeeducativa")
    public String getStrcodigosedeeducativa() {
        return this.strcodigosedeeducativa;
    }
    
    public void setStrcodigosedeeducativa(String strcodigosedeeducativa) {
        this.strcodigosedeeducativa = strcodigosedeeducativa;
    }

    
    @Column(name="strcodigotramo")
    public String getStrcodigotramo() {
        return this.strcodigotramo;
    }
    
    public void setStrcodigotramo(String strcodigotramo) {
        this.strcodigotramo = strcodigotramo;
    }

    
    @Column(name="boolobraterminada", nullable=false)
    public boolean isBoolobraterminada() {
        return this.boolobraterminada;
    }
    
    public void setBoolobraterminada(boolean boolobraterminada) {
        this.boolobraterminada = boolobraterminada;
    }

    
    @Column(name="usu_modifica")
    public Integer getUsuModifica() {
        return this.usuModifica;
    }
    
    public void setUsuModifica(Integer usuModifica) {
        this.usuModifica = usuModifica;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dateusu_modifica", length=29)
    public Date getDateusuModifica() {
        return this.dateusuModifica;
    }
    
    public void setDateusuModifica(Date dateusuModifica) {
        this.dateusuModifica = dateusuModifica;
    }

    
    @Column(name="numvisitas")
    public Long getNumvisitas() {
        return this.numvisitas;
    }
    
    public void setNumvisitas(Long numvisitas) {
        this.numvisitas = numvisitas;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dateactualizacionvisitas", length=29)
    public Date getDateactualizacionvisitas() {
        return this.dateactualizacionvisitas;
    }
    
    public void setDateactualizacionvisitas(Date dateactualizacionvisitas) {
        this.dateactualizacionvisitas = dateactualizacionvisitas;
    }

    
    @Column(name="pagodirecto", precision=17, scale=17)
    public Double getPagodirecto() {
        return this.pagodirecto;
    }
    
    public void setPagodirecto(Double pagodirecto) {
        this.pagodirecto = pagodirecto;
    }

    
    @Column(name="otrospagos", precision=17, scale=17)
    public Double getOtrospagos() {
        return this.otrospagos;
    }
    
    public void setOtrospagos(Double otrospagos) {
        this.otrospagos = otrospagos;
    }

    
    @Column(name="valordisponible", precision=20, scale=6)
    public BigDecimal getValordisponible() {
        return this.valordisponible;
    }
    
    public void setValordisponible(BigDecimal valordisponible) {
        this.valordisponible = valordisponible;
    }

    
    @Column(name="boolplanoperativo")
    public Boolean getBoolplanoperativo() {
        return this.boolplanoperativo;
    }
    
    public void setBoolplanoperativo(Boolean boolplanoperativo) {
        this.boolplanoperativo = boolplanoperativo;
    }

    
    @Column(name="codigo_supervisor")
    public Long getCodigoSupervisor() {
        return this.codigoSupervisor;
    }
    
    public void setCodigoSupervisor(Long codigoSupervisor) {
        this.codigoSupervisor = codigoSupervisor;
    }

    
    @Column(name="codigo_interventor")
    public Long getCodigoInterventor() {
        return this.codigoInterventor;
    }
    
    public void setCodigoInterventor(Long codigoInterventor) {
        this.codigoInterventor = codigoInterventor;
    }

    
    @Column(name="lineanegocio")
    public Integer getLineanegocio() {
        return this.lineanegocio;
    }
    
    public void setLineanegocio(Integer lineanegocio) {
        this.lineanegocio = lineanegocio;
    }

    
    @Column(name="int_idtercero_constructor")
    public Integer getIntIdterceroConstructor() {
        return this.intIdterceroConstructor;
    }
    
    public void setIntIdterceroConstructor(Integer intIdterceroConstructor) {
        this.intIdterceroConstructor = intIdterceroConstructor;
    }

    
    @Column(name="intidsubsector")
    public Integer getIntidsubsector() {
        return this.intidsubsector;
    }
    
    public void setIntidsubsector(Integer intidsubsector) {
        this.intidsubsector = intidsubsector;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="obra")
    public Set<Actividadobra> getActividadobras() {
        return this.actividadobras;
    }
    
    public void setActividadobras(Set<Actividadobra> actividadobras) {
        this.actividadobras = actividadobras;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="obra")
    public Set<Periodo> getPeriodos() {
        return this.periodos;
    }
    
    public void setPeriodos(Set<Periodo> periodos) {
        this.periodos = periodos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="obra")
    public Set<Videoevolucionobra> getVideoevolucionobras() {
        return this.videoevolucionobras;
    }
    
    public void setVideoevolucionobras(Set<Videoevolucionobra> videoevolucionobras) {
        this.videoevolucionobras = videoevolucionobras;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="obra")
    public Set<Alimentacion> getAlimentacions() {
        return this.alimentacions;
    }
    
    public void setAlimentacions(Set<Alimentacion> alimentacions) {
        this.alimentacions = alimentacions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="obra")
    public Set<Imagenevolucionobra> getImagenevolucionobras() {
        return this.imagenevolucionobras;
    }
    
    public void setImagenevolucionobras(Set<Imagenevolucionobra> imagenevolucionobras) {
        this.imagenevolucionobras = imagenevolucionobras;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="obra")
    public Set<Obra> getObras() {
        return this.obras;
    }
    
    public void setObras(Set<Obra> obras) {
        this.obras = obras;
    }




}


