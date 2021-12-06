package co.com.interkont.avanzame.api.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Solicitud FPO")
public class SolicitudFPORequest {

    @ApiModelProperty(value = "Id solicitud",example = "320")
	private Integer id;
	
    @ApiModelProperty(value = "Días solicitados",example = "10")
	@NotNull(message = "Debe especificar los días de solicitud")
	private Integer diasSolicitados;
	
    @ApiModelProperty(value = "fecha de la solicitud",example = "2021-09-06")
	@NotEmpty(message = "La fecha debe contener un formato válido")
	private String fechaSolicitud;
	
    @ApiModelProperty(value = "Solicitud origen")
    private Integer solicitudOrigenId;
	
    @ApiModelProperty(value = "Justificación")
    @NotEmpty(message = "Justificación de la solicitud no puede estar en blanco")
	private String justificacion;
	
    /*
    @ApiModelProperty(value = "Estado de la solicitud")
    @NotEmpty(message = "El estado debe contener al menos el valor por defecto")
	private String estado;
	*/
	
    @ApiModelProperty(value = "Días otorgados a la solicitud")
    private Integer diasOtorgados;
	
    @ApiModelProperty(value = "Resolución o documento otorgante")
	private String resolucionOtorgante;
    
    @ApiModelProperty(value = "Id de obra")
    private Integer obraid;
    
    private RadicarDocumentoParamRequest radicarDocumento;
	
    
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the diasSolicitados
	 */
	public Integer getDiasSolicitados() {
		return diasSolicitados;
	}

	/**
	 * @param diasSolicitados the diasSolicitados to set
	 */
	public void setDiasSolicitados(Integer diasSolicitados) {
		this.diasSolicitados = diasSolicitados;
	}

	/**
	 * @return the fechaSolicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * @return the solicitudOrigenId
	 */
	public Integer getSolicitudOrigenId() {
		return solicitudOrigenId;
	}

	/**
	 * @param solicitudOrigenId the solicitudOrigenId to set
	 */
	public void setSolicitudOrigenId(Integer solicitudOrigenId) {
		solicitudOrigenId = solicitudOrigenId;
	}

	/**
	 * @return the justificacion
	 */
	public String getJustificacion() {
		return justificacion;
	}

	/**
	 * @param justificacion the justificacion to set
	 */
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	/**
	 * @return the estado
	 */
	/*public String getEstado() {
		return estado;
	}*/

	/**
	 * @param estado the estado to set
	 */
	/*
	public void setEstado(String estado) {
		this.estado = estado;
	}
	*/

	/**
	 * @return the diasOtorgados
	 */
	public Integer getDiasOtorgados() {
		return diasOtorgados;
	}

	/**
	 * @param diasOtorgados the diasOtorgados to set
	 */
	public void setDiasOtorgados(Integer diasOtorgados) {
		this.diasOtorgados = diasOtorgados;
	}

	/**
	 * @return the resolucionOtorgante
	 */
	public String getResolucionOtorgante() {
		return resolucionOtorgante;
	}

	/**
	 * @param resolucionOtorgante the resolucionOtorgante to set
	 */
	public void setResolucionOtorgante(String resolucionOtorgante) {
		this.resolucionOtorgante = resolucionOtorgante;
	}

	/**
	 * @return the obraid
	 */
	public Integer getObraid() {
		return obraid;
	}

	/**
	 * @param obraid the obraid to set
	 */
	public void setObraid(Integer obraid) {
		this.obraid = obraid;
	}

	/**
	 * @return the radicarDocumento
	 */
	public RadicarDocumentoParamRequest getRadicarDocumento() {
		return radicarDocumento;
	}

	/**
	 * @param radicarDocumento the radicarDocumento to set
	 */
	public void setRadicarDocumento(RadicarDocumentoParamRequest radicarDocumento) {
		this.radicarDocumento = radicarDocumento;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SolicitudFPORequest [id=" + id + ", diasSolicitados=" + diasSolicitados + ", fechaSolicitud="
				+ fechaSolicitud + ", solicitudOrigenId=" + solicitudOrigenId + ", justificacion=" + justificacion
				+ ", diasOtorgados=" + diasOtorgados + ", resolucionOtorgante=" + resolucionOtorgante + ", obraid="
				+ obraid + ", radicarDocumento=" + radicarDocumento + "]";
	}

	
	
		
	
}
