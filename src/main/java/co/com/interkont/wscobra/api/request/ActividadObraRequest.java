package co.com.interkont.wscobra.api.request;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actividadobra", schema="public")
public class ActividadObraRequest {
	
	@Id
	@Column(name="oidactiviobra")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="strdescactividad")
	private String nombre;
	
	@Column(name="idcategoria")
	private Integer idcategoria;

	@Column(name="strtipounidadmed")
	private String unidadMedida;
	
	@Column(name="floatcantplanifao", columnDefinition="NUMERIC NOT NULL")
	private Double cantidad;
	
	@Column(name="fechainicio",columnDefinition="DATE NOT NULL")
	private Date fechainicio ;
	
	@Column(name="fechafin",columnDefinition="DATE NOT NULL")
	private Date fechafin;
	
	@Column(name="intcodigoobra",columnDefinition="integer NOT NULL")
	private Integer idobra;
	
	@Column(name="valorunitario",columnDefinition="numeric(20,6) null")
	private BigDecimal valorunitario; //crear valor q escribe el usuario
	
	@Column(name="numvalorplanifao",columnDefinition="numeric(20,6) null")
	private BigDecimal valortotal; //aqui va el precio unitario + aiu + porcentaje float por otros
	
	@Column(name="floatcantidadejecutao",columnDefinition="numeric(20,6) null")
	private Double cantidadejecutada;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Integer getIdobra() {
		return idobra;
	}

	public void setIdobra(Integer idobra) {
		this.idobra = idobra;
	}

	public BigDecimal getValorunitario() {
		return valorunitario;
	}

	public void setValorunitario(BigDecimal valorunitario) {
		this.valorunitario = valorunitario;
	}

	public BigDecimal getValortotal() {
		return valortotal;
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public Double getCantidadejecutada() {
		return cantidadejecutada;
	}

	public void setCantidadejecutada(Double cantidadejecutada) {
		this.cantidadejecutada = cantidadejecutada;
	}

	

	
}
