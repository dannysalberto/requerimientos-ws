package co.com.interkont.wsmiobra.models;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.interkont.wsmiobra.config.Constantes;

@Entity
@Table(name="actividadobra",schema="modificacion")
public class ActividadObraModificacion {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="obra_id",columnDefinition="INTEGER NOT NULL")
	private ObraModificacion obraModificacion;
	
	@NotNull
	@Column(name="oidactiviobra",columnDefinition="OID NOT NULL")
	private Integer oidactiviobra;
	
	@NotNull
	@Column(name="strdescactividad",columnDefinition="VARCHAR(200) NOT NULL")
	private String strdescactividad;
	
	@NotNull
	@Column(name="idcategoria",columnDefinition="INTEGER NOT NULL")
	private Integer idcategoria;
	
	@NotNull
	@Column(name="strtipounidadmed",columnDefinition="VARCHAR(10) NOT NULL")
	private String strtipounidadmed;
	
	@NotNull
	@Column(name="floatcantplanifao",columnDefinition="DOUBLE PRECISION NOT NULL")
	private BigDecimal floatcantplanifao;
	
	@Column(name="fechainicio",columnDefinition="DATE")
	private Date fechainicio;

	@Column(name="fechafin",columnDefinition="DATE")
	private Date fechafin;

	@Column(name="intcodigoobra",columnDefinition="integer NOT NULL")
    private Integer intcodigoobra;
    
	private BigDecimal valorunitario;
    private BigDecimal numvalorplanifao;
    private boolean boolaiu;

    private Double floatcantidadejecutao;
    
    @Column(name="valortotalactividadaiu",columnDefinition="numeric(20,6) null")
 	private BigDecimal valortotalactividadaiu; //aqui va el (precio unitario + aiu + porcentaje float por otros) * cantidad 
 	
    @Column(name="numvalorejecutao",columnDefinition="numeric(20,6) null")
    private BigDecimal numvalorejecutao;
    
    @Column(name="tipomodificacion",columnDefinition="VARCHAR(1)")
    private String tipoModificacion = Constantes.ACTIVIDAD_CLONADA;

    /**/
    
	@Column(name="newfechainicio",columnDefinition="DATE")
	private Date newfechainicio;
	
	@Column(name="newfechafin",columnDefinition="DATE")
	private Date newfechafin;

    @Column(name="newnumvalorplanifao")
    private BigDecimal newnumvalorplanifao;
    
    @Column(name="newfloatcantplanifao")
    private BigDecimal newfloatcantplanifao;
    
    @Column(name="newvalorunitario",columnDefinition="numeric(20,6) null")
    private BigDecimal newvalorunitario;
    
    @Column(name="newvalortotalactividadaiu",columnDefinition="numeric(20,6) null")
 	private BigDecimal newvalortotalactividadaiu;
    
    
    
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ObraModificacion getObraModificacion() {
		return obraModificacion;
	}

	public void setObraModificacion(ObraModificacion obraModificacion) {
		this.obraModificacion = obraModificacion;
	}

	public Integer getOidactiviobra() {
		return oidactiviobra;
	}

	public void setOidactiviobra(Integer oidactiviobra) {
		this.oidactiviobra = oidactiviobra;
	}

	public String getStrdescactividad() {
		return strdescactividad;
	}

	public void setStrdescactividad(String strdescactividad) {
		this.strdescactividad = strdescactividad;
	}

	public Integer getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getStrtipounidadmed() {
		return strtipounidadmed;
	}

	public void setStrtipounidadmed(String strtipounidadmed) {
		this.strtipounidadmed = strtipounidadmed;
	}

	public BigDecimal getFloatcantplanifao() {
		return floatcantplanifao;
	}

	public void setFloatcantplanifao(BigDecimal floatcantplanifao) {
		this.floatcantplanifao = floatcantplanifao;
	}

	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public BigDecimal getValorunitario() {
		return valorunitario;
	}

	public void setValorunitario(BigDecimal valorunitario) {
		this.valorunitario = valorunitario;
	}

	public BigDecimal getNumvalorplanifao() {
		return numvalorplanifao;
	}

	public void setNumvalorplanifao(BigDecimal numvalorplanifao) {
		this.numvalorplanifao = numvalorplanifao;
	}

	public Double getFloatcantidadejecutao() {
		return floatcantidadejecutao;
	}

	public void setFloatcantidadejecutao(Double floatcantidadejecutao) {
		this.floatcantidadejecutao = floatcantidadejecutao;
	}

	public boolean isBoolaiu() {
		return boolaiu;
	}

	public void setBoolaiu(boolean boolaiu) {
		this.boolaiu = boolaiu;
	}

	public BigDecimal getValortotalactividadaiu() {
		return valortotalactividadaiu;
	}

	public void setValortotalactividadaiu(BigDecimal valortotalactividadaiu) {
		this.valortotalactividadaiu = valortotalactividadaiu;
	}

	public Integer getIntcodigoobra() {
		return intcodigoobra;
	}

	public void setIntcodigoobra(Integer intcodigoobra) {
		this.intcodigoobra = intcodigoobra;
	}

	public BigDecimal getNumvalorejecutao() {
		return numvalorejecutao;
	}

	public void setNumvalorejecutao(BigDecimal numvalorejecutao) {
		this.numvalorejecutao = numvalorejecutao;
	}

	public String getTipoModificacion() {
		return tipoModificacion;
	}

	public void setTipomodificacion(String tipoModificacion) {
		this.tipoModificacion = tipoModificacion;
	}

	public Date getNewfechainicio() {
		return newfechainicio;
	}

	public void setNewfechainicio(Date newfechainicio) {
		this.newfechainicio = newfechainicio;
	}

	public Date getNewfechafin() {
		return newfechafin;
	}

	public void setNewfechafin(Date newfechafin) {
		this.newfechafin = newfechafin;
	}

	public BigDecimal getNewnumvalorplanifao() {
		return newnumvalorplanifao;
	}

	public void setNewnumvalorplanifao(BigDecimal newnumvalorplanifao) {
		this.newnumvalorplanifao = newnumvalorplanifao;
	}

	public BigDecimal getNewfloatcantplanifao() {
		return newfloatcantplanifao;
	}

	public void setNewfloatcantplanifao(BigDecimal newfloatcantplanifao) {
		this.newfloatcantplanifao = newfloatcantplanifao;
	}

	public BigDecimal getNewvalorunitario() {
		return newvalorunitario;
	}

	public void setNewvalorunitario(BigDecimal newvalorunitario) {
		this.newvalorunitario = newvalorunitario;
	}

	public BigDecimal getNewvalortotalactividadaiu() {
		return newvalortotalactividadaiu;
	}

	public void setNewvalortotalactividadaiu(BigDecimal newvalortotalactividadaiu) {
		this.newvalortotalactividadaiu = newvalortotalactividadaiu;
	}

	public void setTipoModificacion(String tipoModificacion) {
		this.tipoModificacion = tipoModificacion;
	}

	@Override
	public String toString() {
		return "ActividadObraModificacion [id=" + id + ", obraModificacion=" + obraModificacion + ", oidactiviobra="
				+ oidactiviobra + ", strdescactividad=" + strdescactividad + ", idcategoria=" + idcategoria
				+ ", strtipounidadmed=" + strtipounidadmed + ", floatcantplanifao=" + floatcantplanifao
				+ ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", intcodigoobra=" + intcodigoobra
				+ ", valorunitario=" + valorunitario + ", numvalorplanifao=" + numvalorplanifao + ", boolaiu=" + boolaiu
				+ ", floatcantidadejecutao=" + floatcantidadejecutao + ", valortotalactividadaiu="
				+ valortotalactividadaiu + ", numvalorejecutao=" + numvalorejecutao + ", tipoModificacion="
				+ tipoModificacion + ", newfechainicio=" + newfechainicio + ", newfechafin=" + newfechafin
				+ ", newnumvalorplanifao=" + newnumvalorplanifao + ", newfloatcantplanifao=" + newfloatcantplanifao
				+ ", newvalorunitario=" + newvalorunitario + ", newvalortotalactividadaiu=" + newvalortotalactividadaiu
				+ "]";
	}
	
	

	
    

}



