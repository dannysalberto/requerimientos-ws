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
import javax.persistence.Transient;

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
	@JoinColumn(name="obra_id",columnDefinition="INTEGER NOT NULL") //obramodificacion_id
	private ObraModificacion obraModificacion;
	
	@Column(name="oidactiviobra",columnDefinition="OID NOT NULL")
	private Integer oidactiviobra;
	
	@Column(name="strdescactividad",columnDefinition="VARCHAR(200) NOT NULL")
	private String strdescactividad;
	
	@ManyToOne
	@JoinColumn(name="idcategoria",columnDefinition="INTEGER NOT NULL")
	private Categoria categoria;
	
	
	@Column(name="strtipounidadmed",columnDefinition="VARCHAR(10) NOT NULL")
	private String strtipounidadmed;
	
	@Column(name="floatcantplanifao",columnDefinition="DOUBLE PRECISION NOT NULL")
	private BigDecimal floatcantplanifao;
	
	@Column(name="fechainicio",columnDefinition="DATE")
	private Date fechainicio;

	@Column(name="fechafin",columnDefinition="DATE")
	private Date fechafin;

	@Column(name="intcodigoobra",columnDefinition="integer NOT NULL")
    private Integer intcodigoobra;
    
	private BigDecimal valorunitario = new BigDecimal(0);
    private BigDecimal numvalorplanifao = new BigDecimal(0);
    private boolean boolaiu;

    private Double floatcantidadejecutao = (double) 0;
    
    @Column(name="valortotalactividadaiu",columnDefinition="numeric(20,6) null")
 	private BigDecimal valortotalactividadaiu  = new BigDecimal(0); //aqui va el (precio unitario + aiu + porcentaje float por otros) * cantidad 
 	
    @Column(name="numvalorejecutao",columnDefinition="numeric(20,6) null")
    private BigDecimal numvalorejecutao  = new BigDecimal(0);
    
    @Column(name="tipomodificacion",columnDefinition="VARCHAR(1)")
    private String tipoModificacion = Constantes.ACTIVIDAD_CLONADA;

    /**/
    
	@Column(name="newfechainicio",columnDefinition="DATE")
	private Date newfechainicio;
	
	@Column(name="newfechafin",columnDefinition="DATE")
	private Date newfechafin;

    @Column(name="newnumvalorplanifao")
    private BigDecimal newnumvalorplanifao = new BigDecimal(0);
    
    @Column(name="newfloatcantplanifao")
    private BigDecimal newfloatcantplanifao = new BigDecimal(0);
    
    @Column(name="newvalorunitario",columnDefinition="numeric(20,6) null")
    private BigDecimal newvalorunitario = new BigDecimal(0);
    
    @Column(name="newvalortotalactividadaiu",columnDefinition="numeric(20,6) null")
 	private BigDecimal newvalortotalactividadaiu = new BigDecimal(0); 

	@Transient   
	private BigDecimal porcentajeavance;
	
	@Transient   
	private BigDecimal newporcentajeavance;
	
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

	

	public String getStrtipounidadmed() {
		return strtipounidadmed;
	}

	public void setStrtipounidadmed(String strtipounidadmed) {
		this.strtipounidadmed = strtipounidadmed;
	}

	public BigDecimal getFloatcantplanifao() {
		if (floatcantplanifao == null) {
			return new BigDecimal(0);
		}
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
		if (valorunitario == null) {
			return new BigDecimal(0);
		}
		return valorunitario;
	}

	public void setValorunitario(BigDecimal valorunitario) {
		this.valorunitario = valorunitario;
	}

	public BigDecimal getNumvalorplanifao() {
		if (numvalorplanifao == null) {
			return new BigDecimal(0);
		}
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
		if (valortotalactividadaiu == null) {
			return new BigDecimal(0);
		}
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
		if (numvalorejecutao == null) {
			return new BigDecimal(0);
		}
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public Date getNewfechainicio() {
		return newfechainicio;
	}

	public void setNewfechainicio(Date newfechainicio) {
		this.newfechainicio = newfechainicio;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public Date getNewfechafin() {
		return newfechafin;
	}

	public void setNewfechafin(Date newfechafin) {
		this.newfechafin = newfechafin;
	}

	public BigDecimal getNewnumvalorplanifao() {
		if (newnumvalorplanifao == null) {
			return new BigDecimal(0);
		}
		return newnumvalorplanifao;
	}

	public void setNewnumvalorplanifao(BigDecimal newnumvalorplanifao) {
		this.newnumvalorplanifao = newnumvalorplanifao;
	}

	public BigDecimal getNewfloatcantplanifao() {
		if (newfloatcantplanifao == null) {
			return new BigDecimal(0);
		}
		return newfloatcantplanifao;
	}

	public void setNewfloatcantplanifao(BigDecimal newfloatcantplanifao) {
		this.newfloatcantplanifao = newfloatcantplanifao;
	}

	public BigDecimal getNewvalorunitario() {
		if (newvalorunitario == null) {
			return new BigDecimal(0);
		}
		return newvalorunitario;
	}

	public void setNewvalorunitario(BigDecimal newvalorunitario) {
		this.newvalorunitario = newvalorunitario;
	}

	public BigDecimal getNewvalortotalactividadaiu() {
		if (newvalortotalactividadaiu == null) {
			return new BigDecimal(0);
		}
		return newvalortotalactividadaiu;
	}

	public void setNewvalortotalactividadaiu(BigDecimal newvalortotalactividadaiu) {
		this.newvalortotalactividadaiu = newvalortotalactividadaiu;
	}

	public void setTipoModificacion(String tipoModificacion) {
		this.tipoModificacion = tipoModificacion;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public BigDecimal getPorcentajeavance() {
		
		if (floatcantidadejecutao == null) {
			floatcantidadejecutao = (double) 0;
		}
		if (floatcantplanifao == null) {
			floatcantplanifao = new BigDecimal(0);
		}
		if (floatcantplanifao.doubleValue()>0) {
			porcentajeavance = new BigDecimal(((floatcantidadejecutao*100)/floatcantplanifao.doubleValue()));
			return porcentajeavance ;
		}else {
			return  new BigDecimal(0);
			
		}
	}

	public void setPorcentajeavance(BigDecimal porcentajeavance) {
		this.porcentajeavance = porcentajeavance;
	}
	

	/**
	 * @return the newporcentajeavance
	 */
	public BigDecimal getNewporcentajeavance() {
		if (floatcantidadejecutao == null) {
			floatcantidadejecutao = (double) 0;
		}
		if (newfloatcantplanifao == null) {
			newfloatcantplanifao = new BigDecimal(0);
		}
		if (newfloatcantplanifao.doubleValue()>0) {
			newporcentajeavance = new BigDecimal(((floatcantidadejecutao*100)/newfloatcantplanifao.doubleValue()));
			return newporcentajeavance ;
		}else {
			return  new BigDecimal(0);
			
		}
	}

	/**
	 * @param newporcentajeavance the newporcentajeavance to set
	 */
	public void setNewporcentajeavance(BigDecimal newporcentajeavance) {
		this.newporcentajeavance = newporcentajeavance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActividadObraModificacion [id=" + id + ", obraModificacion=" + obraModificacion + ", oidactiviobra="
				+ oidactiviobra + ", strdescactividad=" + strdescactividad + ", categoria=" + categoria
				+ ", strtipounidadmed=" + strtipounidadmed + ", floatcantplanifao=" + floatcantplanifao
				+ ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", intcodigoobra=" + intcodigoobra
				+ ", valorunitario=" + valorunitario + ", numvalorplanifao=" + numvalorplanifao + ", boolaiu=" + boolaiu
				+ ", floatcantidadejecutao=" + floatcantidadejecutao + ", valortotalactividadaiu="
				+ valortotalactividadaiu + ", numvalorejecutao=" + numvalorejecutao + ", tipoModificacion="
				+ tipoModificacion + ", newfechainicio=" + newfechainicio + ", newfechafin=" + newfechafin
				+ ", newnumvalorplanifao=" + newnumvalorplanifao + ", newfloatcantplanifao=" + newfloatcantplanifao
				+ ", newvalorunitario=" + newvalorunitario + ", newvalortotalactividadaiu=" + newvalortotalactividadaiu
				+ ", porcentajeavance=" + porcentajeavance + ", newporcentajeavance=" + newporcentajeavance + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

	
    

}



