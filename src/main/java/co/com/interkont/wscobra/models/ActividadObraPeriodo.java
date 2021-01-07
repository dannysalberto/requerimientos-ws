package co.com.interkont.wscobra.models;

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
@Table(name="relacionactividadobraperiodo",schema="public")
public class ActividadObraPeriodo {

	@Id
	@NotNull
	@Column(name="oidnmid", columnDefinition="BIGINT")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="numvalplanif",  columnDefinition="numeric(20,6)")
	private Double valPlanif;
	
	@Column(name="floatcantplanif",  columnDefinition="numeric(5,3)")
	private Double cantidadPlanif;
	
	@ManyToOne
	@JoinColumn(name="actividadobra_id",columnDefinition="integer NOT NULL")
	private ActividadobraWS actividadObra;
	
	@ManyToOne
	@JoinColumn(name="intidperiodo",columnDefinition="integer NOT NULL")
	private Periodo periodo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getValPlanif() {
		return valPlanif;
	}

	public void setValPlanif(Double valPlanif) {
		this.valPlanif = valPlanif;
	}

	public Double getCantidadPlanif() {
		return cantidadPlanif;
	}

	public void setCantidadPlanif(Double cantidadPlanif) {
		this.cantidadPlanif = cantidadPlanif;
	}

	public ActividadobraWS getActividadObra() {
		return actividadObra;
	}

	public void setActividadObra(ActividadobraWS actividadObra) {
		this.actividadObra = actividadObra;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		return "ActividadObraPeriodo [id=" + id + ", valPlanif=" + valPlanif + ", cantidadPlanif=" + cantidadPlanif
				+ ", actividadObra=" + actividadObra + ", periodo=" + periodo + "]";
	}
	  
}
