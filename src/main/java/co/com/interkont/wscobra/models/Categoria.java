package co.com.interkont.wscobra.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(schema="public", name="categoria",indexes = {@Index(name = "idx_categoria", unique=true ,columnList = "codigo")})
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Categoria {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value = "Identificador de la categoria")
	private Integer id;
	
	@Column(name="codigo", length=10)
	@ApiModelProperty(value = "Código de la categoria")
	private String codigo;
	
	@ApiModelProperty(value = "Nombre de la categoria")
	private String nombre;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}

	

	
}
