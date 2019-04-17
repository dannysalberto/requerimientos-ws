package co.com.interkont.wscobra.api.response;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DTO-ASOCIATE Contratistas asociados al detalle del proyecto")
public class ContratistasProyectoResponse {
	
	@ApiModelProperty(value="Código del proyecto u/o obra")
	private Integer codigoobra;
	@ApiModelProperty(value="Nombre del proyecto u/o obra")
    private String nombreobra;
	@ApiModelProperty(value="Código del contrato asociado al proyecto u/o obra")
    private Integer codigocontrato;
	@ApiModelProperty(value="Nombre del contrato asociado al proyecto u/o obra")
    private String nombrecontrato;
	@ApiModelProperty(value="Código del contratista ejecutor del contrato asociado al proyecto u/o obra")
    private Integer codigocontratista;
	@ApiModelProperty(value="Nombre del contratista ejecutor del contrato asociado al proyecto u/o obra")
    private String nombrecontratista;
	public Integer getCodigoobra() {
		return codigoobra;
	}
	public void setCodigoobra(Integer codigoobra) {
		this.codigoobra = codigoobra;
	}
	public String getNombreobra() {
		return nombreobra;
	}
	public void setNombreobra(String nombreobra) {
		this.nombreobra = nombreobra;
	}
	public Integer getCodigocontrato() {
		return codigocontrato;
	}
	public void setCodigocontrato(Integer codigocontrato) {
		this.codigocontrato = codigocontrato;
	}
	public String getNombrecontrato() {
		return nombrecontrato;
	}
	public void setNombrecontrato(String nombrecontrato) {
		this.nombrecontrato = nombrecontrato;
	}
	public Integer getCodigocontratista() {
		return codigocontratista;
	}
	public void setCodigocontratista(Integer codigocontratista) {
		this.codigocontratista = codigocontratista;
	}
	public String getNombrecontratista() {
		return nombrecontratista;
	}
	public void setNombrecontratista(String nombrecontratista) {
		this.nombrecontratista = nombrecontratista;
	}
	
	

}
