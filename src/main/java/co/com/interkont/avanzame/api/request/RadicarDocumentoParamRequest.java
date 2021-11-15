package co.com.interkont.avanzame.api.request;


public class RadicarDocumentoParamRequest {

	public String base64Binary;
	
	public String fileName;
	
	public String usuarioLogin;
	
	public String destinatarioCCDocumento;
	
	public String destinatarioNombre;
	
	public String destinatarioPrimerApellido;
	
	public String destinatarioSegundoApellido;
	
	public String destinatarioTelefono;
	
	public String destinatarioDireccion;

	public String destinatarioCorreo;

	public String destinatarioDignatario;

	public String destinatarioIdContinente;

	public String destinatarioIdPais;
	
	public String destinatarioCodDepartamento;

	public String destinatarioMunicipio;

	public String asunto;
	
	public String medio;
	
	public String anexo;

	public String codigoDependenciaRadicadora;
	
	public String cuentai; //Cuentai Referencia /oficio (valor interno para búsquedas específicas)
	
	public String usuarioDocuActual; //documento actual de usuario

	public String tipoRadicado;

	public String tipoRemitente;

	public String tipoDocumento;

	public String tipoIdentificacion;

	public String codigoCarpeta;

	public String tipoCarpeta;
	
	public String key;
	
	public String formularioIdRadicado;

	public String camposAdicionales;
	
	public String expediente;
	
	
	
	public RadicarDocumentoParamRequest() {
		this.base64Binary = "";
		this.fileName = "";
		this.usuarioLogin = "";
		this.destinatarioCCDocumento = "";
		this.destinatarioNombre = "";
		this.destinatarioPrimerApellido = "";
		this.destinatarioSegundoApellido = "";
		this.destinatarioTelefono = "";
		this.destinatarioDireccion = "";
		this.destinatarioCorreo = "";
		this.destinatarioDignatario = "";
		this.destinatarioIdContinente = "";
		this.destinatarioIdPais = "";
		this.destinatarioCodDepartamento = "";
		this.destinatarioMunicipio = "";
		this.asunto = "";
		this.medio = "";
		this.anexo = "";
		this.codigoDependenciaRadicadora = "";
		this.cuentai = "";
		this.usuarioDocuActual = "";
		this.tipoRadicado = "";
		this.tipoRemitente = "";
		this.tipoDocumento = "";
		this.tipoIdentificacion = "";
		this.codigoCarpeta = "";
		this.tipoCarpeta = "";
		this.key = "";
		this.formularioIdRadicado = "";
		this.camposAdicionales = "";
		this.expediente = "";
	}

	/**
	 * @return the base64Binary
	 */
	public String getBase64Binary() {
		return base64Binary;
	}

	/**
	 * @param base64Binary the base64Binary to set
	 */
	public void setBase64Binary(String base64Binary) {
		this.base64Binary = base64Binary;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		fileName= (fileName==null) ? "" : fileName;
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the usuarioLogin
	 */
	public String getUsuarioLogin() {
		usuarioLogin= (usuarioLogin==null) ? "" : usuarioLogin;
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	/**
	 * @return the destinatarioCCDocumento
	 */
	public String getDestinatarioCCDocumento() {
		destinatarioCCDocumento= (destinatarioCCDocumento==null) ? "" : destinatarioCCDocumento;
		return destinatarioCCDocumento;
	}

	/**
	 * @param destinatarioCCDocumento the destinatarioCCDocumento to set
	 */
	public void setDestinatarioCCDocumento(String destinatarioCCDocumento) {
		this.destinatarioCCDocumento = destinatarioCCDocumento;
	}

	/**
	 * @return the destinatarioNombre
	 */
	public String getDestinatarioNombre() {
		destinatarioNombre= (destinatarioNombre==null) ? "" : destinatarioNombre;
		return destinatarioNombre;
	}

	/**
	 * @param destinatarioNombre the destinatarioNombre to set
	 */
	public void setDestinatarioNombre(String destinatarioNombre) {
		this.destinatarioNombre = destinatarioNombre;
	}

	/**
	 * @return the destinatarioPrimerApellido
	 */
	public String getDestinatarioPrimerApellido() {
		destinatarioPrimerApellido= (destinatarioPrimerApellido==null) ? "" : destinatarioPrimerApellido;
		return destinatarioPrimerApellido;
	}

	/**
	 * @param destinatarioPrimerApellido the destinatarioPrimerApellido to set
	 */
	public void setDestinatarioPrimerApellido(String destinatarioPrimerApellido) {
		this.destinatarioPrimerApellido = destinatarioPrimerApellido;
	}

	/**
	 * @return the destinatarioSegundoApellido
	 */
	public String getDestinatarioSegundoApellido() {
		destinatarioSegundoApellido= (destinatarioSegundoApellido==null) ? "" : destinatarioSegundoApellido;
		return destinatarioSegundoApellido;
	}

	/**
	 * @param destinatarioSegundoApellido the destinatarioSegundoApellido to set
	 */
	public void setDestinatarioSegundoApellido(String destinatarioSegundoApellido) {
		this.destinatarioSegundoApellido = destinatarioSegundoApellido;
	}

	/**
	 * @return the destinatarioTelefono
	 */
	public String getDestinatarioTelefono() {
		destinatarioTelefono= (destinatarioTelefono==null) ? "" : destinatarioTelefono;
		return destinatarioTelefono;
	}

	/**
	 * @param destinatarioTelefono the destinatarioTelefono to set
	 */
	public void setDestinatarioTelefono(String destinatarioTelefono) {
		this.destinatarioTelefono = destinatarioTelefono;
	}

	/**
	 * @return the destinatarioDireccion
	 */
	public String getDestinatarioDireccion() {
		return destinatarioDireccion;
	}

	/**
	 * @param destinatarioDireccion the destinatarioDireccion to set
	 */
	public void setDestinatarioDireccion(String destinatarioDireccion) {
		this.destinatarioDireccion = destinatarioDireccion;
	}

	/**
	 * @return the destinatarioCorreo
	 */
	public String getDestinatarioCorreo() {
		return destinatarioCorreo;
	}

	/**
	 * @param destinatarioCorreo the destinatarioCorreo to set
	 */
	public void setDestinatarioCorreo(String destinatarioCorreo) {
		this.destinatarioCorreo = destinatarioCorreo;
	}

	/**
	 * @return the destinatarioDignatario
	 */
	public String getDestinatarioDignatario() {
		return destinatarioDignatario;
	}

	/**
	 * @param destinatarioDignatario the destinatarioDignatario to set
	 */
	public void setDestinatarioDignatario(String destinatarioDignatario) {
		this.destinatarioDignatario = destinatarioDignatario;
	}

	/**
	 * @return the destinatarioIdContinente
	 */
	public String getDestinatarioIdContinente() {
		return destinatarioIdContinente;
	}

	/**
	 * @param destinatarioIdContinente the destinatarioIdContinente to set
	 */
	public void setDestinatarioIdContinente(String destinatarioIdContinente) {
		this.destinatarioIdContinente = destinatarioIdContinente;
	}

	/**
	 * @return the destinatarioIdPais
	 */
	public String getDestinatarioIdPais() {
		return destinatarioIdPais;
	}

	/**
	 * @param destinatarioIdPais the destinatarioIdPais to set
	 */
	public void setDestinatarioIdPais(String destinatarioIdPais) {
		this.destinatarioIdPais = destinatarioIdPais;
	}

	/**
	 * @return the destinatarioCodDepartamento
	 */
	public String getDestinatarioCodDepartamento() {
		return destinatarioCodDepartamento;
	}

	/**
	 * @param destinatarioCodDepartamento the destinatarioCodDepartamento to set
	 */
	public void setDestinatarioCodDepartamento(String destinatarioCodDepartamento) {
		this.destinatarioCodDepartamento = destinatarioCodDepartamento;
	}

	/**
	 * @return the destinatarioMunicipio
	 */
	public String getDestinatarioMunicipio() {
		return destinatarioMunicipio;
	}

	/**
	 * @param destinatarioMunicipio the destinatarioMunicipio to set
	 */
	public void setDestinatarioMunicipio(String destinatarioMunicipio) {
		this.destinatarioMunicipio = destinatarioMunicipio;
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the medio
	 */
	public String getMedio() {
		return medio;
	}

	/**
	 * @param medio the medio to set
	 */
	public void setMedio(String medio) {
		this.medio = medio;
	}

	/**
	 * @return the anexo
	 */
	public String getAnexo() {
		return anexo;
	}

	/**
	 * @param anexo the anexo to set
	 */
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	/**
	 * @return the codigoDependenciaRadicadora
	 */
	public String getCodigoDependenciaRadicadora() {
		return codigoDependenciaRadicadora;
	}

	/**
	 * @param codigoDependenciaRadicadora the codigoDependenciaRadicadora to set
	 */
	public void setCodigoDependenciaRadicadora(String codigoDependenciaRadicadora) {
		this.codigoDependenciaRadicadora = codigoDependenciaRadicadora;
	}

	/**
	 * @return the cuentai
	 */
	public String getCuentai() {
		return cuentai;
	}

	/**
	 * @param cuentai the cuentai to set
	 */
	public void setCuentai(String cuentai) {
		this.cuentai = cuentai;
	}

	/**
	 * @return the usuarioDocuActual
	 */
	public String getUsuarioDocuActual() {
		return usuarioDocuActual;
	}

	/**
	 * @param usuarioDocuActual the usuarioDocuActual to set
	 */
	public void setUsuarioDocuActual(String usuarioDocuActual) {
		this.usuarioDocuActual = usuarioDocuActual;
	}

	/**
	 * @return the tipoRadicado
	 */
	public String getTipoRadicado() {
		return tipoRadicado;
	}

	/**
	 * @param tipoRadicado the tipoRadicado to set
	 */
	public void setTipoRadicado(String tipoRadicado) {
		this.tipoRadicado = tipoRadicado;
	}

	/**
	 * @return the tipoRemitente
	 */
	public String getTipoRemitente() {
		return tipoRemitente;
	}

	/**
	 * @param tipoRemitente the tipoRemitente to set
	 */
	public void setTipoRemitente(String tipoRemitente) {
		this.tipoRemitente = tipoRemitente;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * @param tipoIdentificacion the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * @return the codigoCarpeta
	 */
	public String getCodigoCarpeta() {
		return codigoCarpeta;
	}

	/**
	 * @param codigoCarpeta the codigoCarpeta to set
	 */
	public void setCodigoCarpeta(String codigoCarpeta) {
		this.codigoCarpeta = codigoCarpeta;
	}

	/**
	 * @return the tipoCarpeta
	 */
	public String getTipoCarpeta() {
		return tipoCarpeta;
	}

	/**
	 * @param tipoCarpeta the tipoCarpeta to set
	 */
	public void setTipoCarpeta(String tipoCarpeta) {
		this.tipoCarpeta = tipoCarpeta;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the formularioIdRadicado
	 */
	public String getFormularioIdRadicado() {
		return formularioIdRadicado;
	}

	/**
	 * @param formularioIdRadicado the formularioIdRadicado to set
	 */
	public void setFormularioIdRadicado(String formularioIdRadicado) {
		this.formularioIdRadicado = formularioIdRadicado;
	}

	/**
	 * @return the camposAdicionales
	 */
	public String getCamposAdicionales() {
		return camposAdicionales;
	}

	/**
	 * @param camposAdicionales the camposAdicionales to set
	 */
	public void setCamposAdicionales(String camposAdicionales) {
		this.camposAdicionales = camposAdicionales;
	}

	/**
	 * @return the expediente
	 */
	public String getExpediente() {
		return expediente;
	}

	/**
	 * @param expediente the expediente to set
	 */
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RadicarDocumentoParamRequest [base64Binary=" + base64Binary + ", fileName=" + fileName
				+ ", usuarioLogin=" + usuarioLogin + ", destinatarioCCDocumento=" + destinatarioCCDocumento
				+ ", destinatarioNombre=" + destinatarioNombre + ", destinatarioPrimerApellido="
				+ destinatarioPrimerApellido + ", destinatarioSegundoApellido=" + destinatarioSegundoApellido
				+ ", destinatarioTelefono=" + destinatarioTelefono + ", destinatarioDireccion=" + destinatarioDireccion
				+ ", destinatarioCorreo=" + destinatarioCorreo + ", destinatarioDignatario=" + destinatarioDignatario
				+ ", destinatarioIdContinente=" + destinatarioIdContinente + ", destinatarioIdPais="
				+ destinatarioIdPais + ", destinatarioCodDepartamento=" + destinatarioCodDepartamento
				+ ", destinatarioMunicipio=" + destinatarioMunicipio + ", asunto=" + asunto + ", medio=" + medio
				+ ", anexo=" + anexo + ", codigoDependenciaRadicadora=" + codigoDependenciaRadicadora + ", cuentai="
				+ cuentai + ", usuarioDocuActual=" + usuarioDocuActual + ", tipoRadicado=" + tipoRadicado
				+ ", tipoRemitente=" + tipoRemitente + ", tipoDocumento=" + tipoDocumento + ", tipoIdentificacion="
				+ tipoIdentificacion + ", codigoCarpeta=" + codigoCarpeta + ", tipoCarpeta=" + tipoCarpeta + ", key="
				+ key + ", formularioIdRadicado=" + formularioIdRadicado + ", camposAdicionales=" + camposAdicionales
				+ ", expediente=" + expediente + "]";
	}
	
	
	 
}
