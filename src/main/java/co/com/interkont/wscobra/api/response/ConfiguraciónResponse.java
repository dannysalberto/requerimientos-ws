package co.com.interkont.wscobra.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("DTO-RESPONSE Parámetros de Configuración de la aplicación")
public class ConfiguraciónResponse {
	
	@ApiModelProperty(value="End point del servicio donde se van a realizar las peticiones")
	private String endpoint;
	@ApiModelProperty(value="End point de las imagenes donde se van a realizar las peticiones")
	private String endpointimagenes;
	@ApiModelProperty(value="Tipo de moneda que va ha tener la aplicación")
	private String tipomoneda;
	@ApiModelProperty(value="Key google mas")
	private String keygmaps;
	@ApiModelProperty(value="Formato de fecha para los campos de fecha")
	private String formatofecha;
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getEndpointimagenes() {
		return endpointimagenes;
	}
	public void setEndpointimagenes(String endpointimagenes) {
		this.endpointimagenes = endpointimagenes;
	}
	public String getTipomoneda() {
		return tipomoneda;
	}
	public void setTipomoneda(String tipomoneda) {
		this.tipomoneda = tipomoneda;
	}
	public String getKeygmaps() {
		return keygmaps;
	}
	public void setKeygmaps(String keygmaps) {
		this.keygmaps = keygmaps;
	}
	public String getFormatofecha() {
		return formatofecha;
	}
	public void setFormatofecha(String formatofecha) {
		this.formatofecha = formatofecha;
	}
	
	
}
