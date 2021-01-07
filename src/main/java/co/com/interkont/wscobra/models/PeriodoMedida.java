package co.com.interkont.wscobra.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public",name="periodomedida")
public class PeriodoMedida {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="intidperiomedida")
	private Integer id;
	
	@Column(name="strdescperiomedida")
	private String descripcion;
	
	@Column(name="intnrodiasperiomedida")
	private Integer diasPeriodo;
	
	@Column(name="intiniciorango")
	private Integer inicioRango;
	
	@Column(name="intfinrango")
	private Integer finRango;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDiasPeriodo() {
		return diasPeriodo;
	}

	public void setDiasPeriodo(Integer diasPeriodo) {
		this.diasPeriodo = diasPeriodo;
	}

	public Integer getInicioRango() {
		return inicioRango;
	}

	public void setInicioRango(Integer inicioRango) {
		this.inicioRango = inicioRango;
	}

	public Integer getFinRango() {
		return finRango;
	}

	public void setFinRango(Integer finRango) {
		this.finRango = finRango;
	}

	@Override
	public String toString() {
		return "PeriodoMedida [id=" + id + ", descripcion=" + descripcion + ", diasPeriodo=" + diasPeriodo
				+ ", inicioRango=" + inicioRango + ", finRango=" + finRango + "]";
	}
	
		
	
}
