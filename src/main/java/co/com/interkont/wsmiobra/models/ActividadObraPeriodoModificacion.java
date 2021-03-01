package co.com.interkont.wsmiobra.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="relacionactividadobraperiodo",schema="modificacion")
public class ActividadObraPeriodoModificacion {
	
	@Id
	@NotNull
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name="oidnmid",  columnDefinition="oid not null")
	private long actividadObraPeriodo_id;
	
	@Column(name="numvalplanif",  columnDefinition="numeric(20,6)")
	private BigDecimal numvalplanif;
	
	@Column(name="floatcantplanif", columnDefinition="numeric(20,6)")
	private BigDecimal floatcantplanif;
	
	@Column(name="oidactiviobra", columnDefinition="oid")
	private long oidactiviobra;
	
	@Column(name="intidperiodo", columnDefinition="numeric(20,6)")
	private Integer intidperiodo;
	
	@ManyToOne
	@JoinColumn(name="periodomodificacion",columnDefinition="integer NOT NULL")
	private PeriodoModificacion periodoModificacion;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getActividadObraPeriodo_id() {
		return actividadObraPeriodo_id;
	}

	public void setActividadObraPeriodo_id(long actividadObraPeriodo_id) {
		this.actividadObraPeriodo_id = actividadObraPeriodo_id;
	}

	public BigDecimal getNumvalplanif() {
		return numvalplanif;
	}

	public void setNumvalplanif(BigDecimal numvalplanif) {
		this.numvalplanif = numvalplanif;
	}

	public BigDecimal getFloatcantplanif() {
		return floatcantplanif;
	}

	public void setFloatcantplanif(BigDecimal floatcantplanif) {
		this.floatcantplanif = floatcantplanif;
	}

	public long getOidactiviobra() {
		return oidactiviobra;
	}

	public void setOidactiviobra(long oidactiviobra) {
		this.oidactiviobra = oidactiviobra;
	}

	public Integer getIntidperiodo() {
		return intidperiodo;
	}

	public void setIntidperiodo(Integer intidperiodo) {
		this.intidperiodo = intidperiodo;
	}

	
	public PeriodoModificacion getPeriodoModificacion() {
		return periodoModificacion;
	}

	public void setPeriodoModificacion(PeriodoModificacion periodoModificacion) {
		this.periodoModificacion = periodoModificacion;
	}

	@Override
	public String toString() {
		return "ActividadObraPeriodoModificacion [id=" + id + ", actividadObraPeriodo_id=" + actividadObraPeriodo_id
				+ ", numvalplanif=" + numvalplanif + ", floatcantplanif=" + floatcantplanif + ", oidactiviobra="
				+ oidactiviobra + ", intidperiodo=" + intidperiodo + ", periodoModificacion=" + periodoModificacion
				+ "]";
	}

}