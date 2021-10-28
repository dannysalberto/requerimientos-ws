package co.com.interkont.avanzame.api.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Actualizacion de Solicitud FPO")
public class SolicitudFPOUpdateRequest {

    @ApiModelProperty(value = "Id solicitud",example = "320")
	private Integer id;
    
    /*@ApiModelProperty(value = "Nueva Fecha de FPO")
    @NotEmpty(message = "La nueva fecha FPO no puede estar vacia")
    private String fechaNueva;*/
	
    /*@ApiModelProperty(value = "Estado de la solicitud")
    @NotEmpty(message = "El estado debe contener al menos el valor por defecto")
	private String estado;*/
	
    @ApiModelProperty(value = "Días otorgados a la solicitud")
    private Integer diasOtorgados;
	
    @ApiModelProperty(value = "Resolución o documento otorgante")
    @Size(min=1,max=60,message="Debe especificar una resolución válida, Máximo 60 carácteres")
    private String resolucionOtorgante;
    
    @ApiModelProperty(value = "Justificación FPO Otorgada")
    @NotEmpty(message = "Falta Justificación FPO Otorgada")
    private String justificacionFPOOtorgada;

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
	 * @return the estado
	 */
//	public String getEstado() {
//		return estado;
//	}
//
//	/**
//	 * @param estado the estado to set
//	 */
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}

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
	 * @return the justificacionFPOOtorgada
	 */
	public String getJustificacionFPOOtorgada() {
		return justificacionFPOOtorgada;
	}

	/**
	 * @param justificacionFPOOtorgada the justificacionFPOOtorgada to set
	 */
	public void setJustificacionFPOOtorgada(String justificacionFPOOtorgada) {
		this.justificacionFPOOtorgada = justificacionFPOOtorgada;
	}
    	
}
