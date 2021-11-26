package co.com.interkont.avanzame.api.request;

public class FileRequest {
	
	private String fileBase64;
	
	private String clasificacion;
	
	private String idtipodocumento;
	
	private String iddocumento;
	
	private String nombredocumento;
	
	private String usuarioid;

	/**
	 * @return the fileBase64
	 */
	public String getFileBase64() {
		return fileBase64;
	}

	/**
	 * @param fileBase64 the fileBase64 to set
	 */
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	/**
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * @param clasificacion the clasificacion to set
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * @return the idtipodocumento
	 */
	public String getIdtipodocumento() {
		return idtipodocumento;
	}

	/**
	 * @param idtipodocumento the idtipodocumento to set
	 */
	public void setIdtipodocumento(String idtipodocumento) {
		this.idtipodocumento = idtipodocumento;
	}

	/**
	 * @return the iddocumento
	 */
	public String getIddocumento() {
		return iddocumento;
	}

	/**
	 * @param iddocumento the iddocumento to set
	 */
	public void setIddocumento(String iddocumento) {
		this.iddocumento = iddocumento;
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
	 * @return the usuarioid
	 */
	public String getUsuarioid() {
		return usuarioid;
	}

	/**
	 * @param usuarioid the usuarioid to set
	 */
	public void setUsuarioid(String usuarioid) {
		this.usuarioid = usuarioid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileRequest [fileBase64=" + fileBase64 + ", clasificacion=" + clasificacion + ", idtipodocumento="
				+ idtipodocumento + ", iddocumento=" + iddocumento + ", nombredocumento=" + nombredocumento
				+ ", usuarioid=" + usuarioid + "]";
	}
	
	
	
}
