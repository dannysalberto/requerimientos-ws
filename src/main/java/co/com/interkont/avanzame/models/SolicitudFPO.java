/**
 * 
 */
package co.com.interkont.avanzame.models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import co.com.interkont.avanzame.api.request.RadicarDocumentoParamRequest;

/**
 * @author DannysMuria
 *
 */
@Entity
@Table(schema="public",name="solicitudfpo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolicitudFPO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@Column(name="diassolicitados",columnDefinition="NUMBER NOT NULL")
	private Integer diasSolicitados;
	
	@Column(name="fechasolicitud",columnDefinition="DATE NOT NULL")
	private Date fechaSolicitud;
		
	@Column(name="solicitudorigenid",columnDefinition="NUMBER")
	private Integer SolicitudOrigenId;
	
	@Column(name="justificacion",columnDefinition="VARCHAR(250) NOT NULL")	
	private String justificacion;
	
	@Column(name="estado",columnDefinition="VARCHAR(30) NOT NULL")	
	private String estado;
		
	@Column(name="diasotorgados",columnDefinition="NUMBER")	
	private Integer diasOtorgados;
		
	@Column(name="resolucionotorgante",columnDefinition="VARCHAR(60)")	
	private String resolucionOtorgante;

	@Column(name="fechanueva",columnDefinition="DATE")	
	private Date fechaNueva;
	
	@NotNull
	@Column(name="intcodigoobra",columnDefinition="INTEGER NOT NULL")
	private Integer obraid;
	
	@Column(name="justificacionFPOOtorgada",columnDefinition="VARCHAR(250)")
	private String justificacionFPOOtorgada;
	
	@Column(name="fpocompromisoenergia",columnDefinition="DATE")
	private Date fpocompromisoenergia;
	

	@Column(name="numeroRadicado",columnDefinition="VARCHAR(250)")
	private String numeroRadicado;
	
	
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
	@JsonFormat(pattern="yyyy-MM-dd" , locale= "es_CO")
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * @return the solicitudOrigenId
	 */
	public Integer getSolicitudOrigenId() {
		return SolicitudOrigenId;
	}

	/**
	 * @param solicitudOrigenId the solicitudOrigenId to set
	 */
	public void setSolicitudOrigenId(Integer solicitudOrigenId) {
		SolicitudOrigenId = solicitudOrigenId;
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
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

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
	 * @return the fechaNueva
	 */
	@JsonFormat(pattern="yyyy-MM-dd",locale = "es_CO")
	public Date getFechaNueva() {
		return fechaNueva;
	}

	/**
	 * @param fechaNueva the fechaNueva to set
	 */
	public void setFechaNueva(Date fechaNueva) {
		this.fechaNueva = fechaNueva;
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

	/**
	 * @return the fpocompromisoenergia
	 */
	public Date getFpocompromisoenergia() {
		return fpocompromisoenergia;
	}

	/**
	 * @param fpocompromisoenergia the fpocompromisoenergia to set
	 */
	public void setFpocompromisoenergia(Date fpocompromisoenergia) {
		this.fpocompromisoenergia = fpocompromisoenergia;
	}

	/**
	 * @return the numeroRadicado
	 */
	public String getNumeroRadicado() {
		return numeroRadicado;
	}

	/**
	 * @param numeroRadicado the numeroRadicado to set
	 */
	public void setNumeroRadicado(String numeroRadicado) {
		this.numeroRadicado = numeroRadicado;
	}
	
}
