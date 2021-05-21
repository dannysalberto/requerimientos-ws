package co.com.interkont.wsmiobra.api.request;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="actividadobra", schema="public")
public class ActividadUpdObraRequest {
	
	@Id
	@Column(name="oidactiviobra")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="floatcantplanifao", columnDefinition="NUMERIC NOT NULL")
	private BigDecimal cantidad;
	
	@Column(name="fechainicio",columnDefinition="DATE NOT NULL")
	private String fechainicio ;
	
	@Column(name="fechafin",columnDefinition="DATE NOT NULL")
	private String fechafin;
	
	@Column(name="valorunitario",columnDefinition="numeric(20,6) null")
	private BigDecimal valorunitario; //crear valor q escribe el usuario
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	public BigDecimal getValorunitario() {
		return valorunitario;
	}

	public void setValorunitario(BigDecimal valorunitario) {
		this.valorunitario = valorunitario;
	}
	
}
