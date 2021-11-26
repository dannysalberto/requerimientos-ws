package co.com.interkont.avanzame.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="documentoobra",schema="public")
public class Documento  {
	
	@Id
	@SequenceGenerator(name="documentoobra_oididdoc_seq",sequenceName="documentoobra_oididdoc_seq",
 	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="documentoobra_oididdoc_seq")
	@Column(name="oididdoc",columnDefinition="OID")
	private Integer id;
	
	@Column(name="strubicacion",columnDefinition="VARCHAR(250)")
	private String ubicacion;
	
	@Column(name="strnombre",columnDefinition="VARCHAR(250)")
	private String nombredocumento;
	
	@Column(name="datefecha",columnDefinition="DATE")
	private Date fecha = new Date();
	
	@Column(name="intcodigoobra",columnDefinition="INTEGER")
	private Integer obraid;
	
	@Column(name="inttipodoc",columnDefinition="INTEGER")
	private Integer tipodocumentoid;
	
	@Column(name="intidcontrato",columnDefinition="INTEGER")
	private Integer contratoid;
	
	@Column(name="oidmodificacion",columnDefinition="INTEGER")
	private Integer modificacionid;
	
	@Column(name="oidhistorico",columnDefinition="INTEGER")
	private Integer historicoid;
	
	@Column(name="intestadocontrato",columnDefinition="INTEGER")
	private Integer estadocontratoid;
	
	@Column(name="intusucreacion",columnDefinition="INTEGER")
	private Integer usuarioid;
	
	@Column(name="oidsolicitudfpo",columnDefinition="INTEGER")
	private Integer oidsolicitudfpo;
	
	@Column(name="downloadid",columnDefinition="VARCHAR")
	private String downloadid;

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
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the nombredocumento
	 */
	public String getNombredocumento() {
		return nombredocumento;
	}

	/**
	 * @param nombredocumento the nombredocumento to set
	 */
	public void setNombredocumento(String nombredocumento) {
		this.nombredocumento = nombredocumento;
	}

	/**
	 * @return the fecha
	 */
	
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * @return the tipodocumentoid
	 */
	public Integer getTipodocumentoid() {
		return tipodocumentoid;
	}

	/**
	 * @param tipodocumentoid the tipodocumentoid to set
	 */
	public void setTipodocumentoid(Integer tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	/**
	 * @return the contratoid
	 */
	public Integer getContratoid() {
		return contratoid;
	}

	/**
	 * @param contratoid the contratoid to set
	 */
	public void setContratoid(Integer contratoid) {
		this.contratoid = contratoid;
	}

	/**
	 * @return the modificacionid
	 */
	public Integer getModificacionid() {
		return modificacionid;
	}

	/**
	 * @param modificacionid the modificacionid to set
	 */
	public void setModificacionid(Integer modificacionid) {
		this.modificacionid = modificacionid;
	}

	/**
	 * @return the historicoid
	 */
	public Integer getHistoricoid() {
		return historicoid;
	}

	/**
	 * @param historicoid the historicoid to set
	 */
	public void setHistoricoid(Integer historicoid) {
		this.historicoid = historicoid;
	}

	/**
	 * @return the estadocontratoid
	 */
	public Integer getEstadocontratoid() {
		return estadocontratoid;
	}

	/**
	 * @param estadocontratoid the estadocontratoid to set
	 */
	public void setEstadocontratoid(Integer estadocontratoid) {
		this.estadocontratoid = estadocontratoid;
	}


	/**
	 * @return the usuarioid
	 */
	public Integer getUsuarioid() {
		return usuarioid;
	}

	/**
	 * @param usuarioid the usuarioid to set
	 */
	public void setUsuarioid(Integer usuarioid) {
		this.usuarioid = usuarioid;
	}

	/**
	 * @return the oidsolicitudfpo
	 */
	public Integer getOidsolicitudfpo() {
		return oidsolicitudfpo;
	}

	/**
	 * @param oidsolicitudfpo the oidsolicitudfpo to set
	 */
	public void setOidsolicitudfpo(Integer oidsolicitudfpo) {
		this.oidsolicitudfpo = oidsolicitudfpo;
	}

	/**
	 * @return the downloadid
	 */
	public String getDownloadid() {
		return downloadid;
	}

	/**
	 * @param string the downloadid to set
	 */
	public void setDownloadid(String string) {
		this.downloadid = string;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	


}
