package co.com.interkont.wsmiobra.dto;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.interkont.wsmiobra.auth.config.ConfiguracionConstantes;


@Entity
@Table(name="galeriavideos", schema="public")
public class GaleriaVideos {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="obra_id",columnDefinition="int4 NOT NULL")
	private Obra obra;
	
	private Integer tipovideo = ConfiguracionConstantes.VIDEO_CAMARA;
	
	@NotNull
	@Column(name="nombre",nullable=false, length=50)
	private String nombre;

	@NotNull
	@Column(name="url",nullable=false, length=250)
	private String url;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CO")
	@Column(name="fecha_carga",columnDefinition="DATE")
    private Date fecha_carga = new Date();

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Integer getTipovideo() {
		return tipovideo;
	}

	public void setTipovideo(Integer tipoVideo) {
		this.tipovideo = tipoVideo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getFecha_carga() {
		return fecha_carga;
	}

	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}

	@Override
	public String toString() {
		return "GaleriaVideos [id=" + id + ", obra=" + obra + ", tipovideo=" + tipovideo + ", nombre=" + nombre
				+ ", url=" + url + ", fecha_carga=" + fecha_carga + "]";
	}


}
