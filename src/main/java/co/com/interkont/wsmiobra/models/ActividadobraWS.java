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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.interkont.wsmiobra.dto.Obra;

@Entity
@Table(name="actividadobra",schema="public")
public class ActividadobraWS  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_actividad_obra_new",sequenceName="seq_actividad_obra_new",
     	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_actividad_obra_new")
	private Integer oidactiviobra;
	
	private String strdescactividad;
	private Integer idcategoria;
	private String strtipounidadmed;
	private BigDecimal floatcantplanifao;
	
	@Column(name="fechainicio")
	private Date fechainicio;
	
	@Column(name="fechafin")
	private Date fechafin;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="intcodigoobra",columnDefinition="integer NOT NULL")
    private Obra obra;
    
	private BigDecimal valorunitario;
    private BigDecimal numvalorplanifao;
    private Double floatcantidadejecutao;
    private boolean boolaiu;
    
    @Column(name="valortotalactividadaiu",columnDefinition="numeric(20,6) null")
 	private BigDecimal valortotalactividadaiu; //aqui va el (precio unitario + aiu + porcentaje float por otros) * cantidad 
 	
    @Column(name="intcedula",columnDefinition="INT8 NOT NULL")
    private Integer intcedula;
	
    public ActividadobraWS() {
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


	public Obra getObra() {
		return obra;
	}


	public void setObra(Obra obra) {
		this.obra = obra;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getIntcedula() {
		return intcedula;
	}


	public void setIntcedula(Integer intcedula) {
		this.intcedula = intcedula;
	}


	@Override
	public String toString() {
		return "ActividadobraWS [oidactiviobra=" + oidactiviobra + ", strdescactividad=" + strdescactividad
				+ ", idcategoria=" + idcategoria + ", strtipounidadmed=" + strtipounidadmed + ", floatcantplanifao="
				+ floatcantplanifao + ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", obra=" + obra
				+ ", valorunitario=" + valorunitario + ", numvalorplanifao=" + numvalorplanifao
				+ ", floatcantidadejecutao=" + floatcantidadejecutao + ", boolaiu=" + boolaiu
				+ ", valortotalactividadaiu=" + valortotalactividadaiu + ", intcedula=" + intcedula + "]";
	}

	


}


