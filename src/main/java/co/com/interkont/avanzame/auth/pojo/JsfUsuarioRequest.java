package co.com.interkont.avanzame.auth.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("JsfUsuarioRequest (Request login de usuario)")
public class JsfUsuarioRequest {

	@ApiModelProperty(value="username, del usuario que está realizando login")
	private String usuario;
	@ApiModelProperty(value="Contraseña del usuario que está realizando login")	
	private String contrasena;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
