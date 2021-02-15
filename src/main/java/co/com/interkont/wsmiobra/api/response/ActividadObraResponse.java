package co.com.interkont.wsmiobra.api.response;

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
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import co.com.interkont.wsmiobra.dto.Obra;


@Entity
@Table(schema="public", name="v_actividadobra")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ActividadObraResponse {
	
	@Id
	@Column(name="oidactiviobra")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="strdescactividad")
	private String nombre;
	
	@Column(name="categoria")
	private String categoria;

	@Column(name="idcategoria")
	private Integer idcategoria;

	@Column(name="strtipounidadmed")
	private String unidadMedida;
	
	@Column(name="floatcantplanifao", columnDefinition="NUMERIC NOT NULL")
	private Double cantidad;
	
	@Column(name="fechainicio",columnDefinition="DATE")
	private Date fechainicio ;
	
	@Column(name="fechafin",columnDefinition="DATE")
	private Date fechafin;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="intcodigoobra",columnDefinition="integer NOT NULL")
	private Obra obra;
	
	@Column(name="valorunitario",columnDefinition="numeric(20,6) null")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BigDecimal valorunitario; //crear valor q escribe el usuario
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="numvalorplanifao",columnDefinition="numeric(20,6) null")
	private BigDecimal valortotal; //aqui va el precio unitario + aiu + porcentaje float por otros
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="valortotalactividadaiu",columnDefinition="numeric(20,6) null")
	private BigDecimal valortotalactividadaiu; //aqui va el (precio unitario + aiu + porcentaje float por otros) * cantidad 
	
	@Column(name="floatcantidadejecutao",columnDefinition="numeric(20,6) null")
	private Double cantidadejecutada;
	
	
	@Transient   
	//retornar por consulta
	private BigDecimal porcentajeavance;

	

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
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
		if (valorunitario==null) {
			return new BigDecimal(0);
		}
		return valorunitario;
	}

	public void setValorunitario(BigDecimal valorunitario) {
		this.valorunitario = valorunitario;
	}

	public BigDecimal getValortotal() {
		if (valortotal==null) {
			return new BigDecimal(0);
		}
		return valortotal;
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public BigDecimal getValortotalactividadaiu() {
		if (valortotalactividadaiu==null) {
			return new BigDecimal(0);
		}
		return valortotalactividadaiu;
	}

	public void setValortotalactividadaiu(BigDecimal valortotalactividadaiu) {
		this.valortotalactividadaiu = valortotalactividadaiu;
	}

	public Double getCantidadejecutada() {
		if (cantidadejecutada==null) {
			return (double) 0;
		}
		return cantidadejecutada;
	}

	public void setCantidadejecutada(Double cantidadejecutada) {
		this.cantidadejecutada = cantidadejecutada;
	}

	public BigDecimal getPorcentajeavance() {
		/*if (porcentajeavance==null) {
			return new BigDecimal(0);
		}*/
		if (cantidadejecutada == null) {
			cantidadejecutada = (double) 0;
		}
		if (cantidad == null) {
			cantidadejecutada = (double) 0;
		}
		porcentajeavance = new BigDecimal((cantidadejecutada*100)/cantidad);
		return porcentajeavance;
	}

	public void setPorcentajeavance(BigDecimal porcentajeavance) {
		this.porcentajeavance = porcentajeavance;
	}

	@Override
	public String toString() {
		return "ActividadObra [id=" + id + ", cantidad=" + cantidad + ", fechainicio=" + fechainicio + ", fechafin="
				+ fechafin + ", obra=" + obra + ", valorunitario=" + valorunitario + ", valortotal=" + valortotal
				+ ", valortotalactividadaiu=" + valortotalactividadaiu + ", cantidadejecutada=" + cantidadejecutada
				+ ", porcentajeavance=" + porcentajeavance + "]";
	}

	
}
