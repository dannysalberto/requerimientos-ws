package co.com.interkont.wsmiobra.api.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActividadObraRequest {
	
	private Integer id;
	
	private String nombre;
	
	private Integer idcategoria;

	private String unidadMedida;
	
	private BigDecimal cantidad;
	
	private String fechainicio ;
	
	private String fechafin;
	
	private Integer idobra;
	
	private BigDecimal valorunitario; //crear valor q escribe el usuario
	
	private BigDecimal valortotal; //aqui va el precio unitario + aiu + porcentaje float por otros
	
	private Double cantidadejecutada;
	
	private String idusuario;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		if (cantidadejecutada==null) {
			return (double) 0;
		}
		return cantidadejecutada;
	}

	public void setCantidadejecutada(Double cantidadejecutada) {
		this.cantidadejecutada = cantidadejecutada;
	}

	/**
	 * @return the idusuario
	 */
	public String getIdusuario() {
		return idusuario;
	}

	/**
	 * @param idusuario the idusuario to set
	 */
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	

	

	
}
