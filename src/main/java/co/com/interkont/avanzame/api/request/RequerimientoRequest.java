package co.com.interkont.avanzame.api.request;

import javax.validation.constraints.NotNull;

public class RequerimientoRequest {

	@NotNull
	private String nombreRequerimiento;
	
	@NotNull
	private String tipoCaptura;

	/**
	 * @return the nombreRequerimiento
	 */
	public String getNombreRequerimiento() {
		return nombreRequerimiento;
	}

	/**
	 * @param nombreRequerimiento the nombreRequerimiento to set
	 */
	public void setNombreRequerimiento(String nombreRequerimiento) {
		this.nombreRequerimiento = nombreRequerimiento;
	}

	/**
	 * @return the tipoCaptura
	 */
	public String getTipoCaptura() {
		return tipoCaptura;
	}

	/**
	 * @param tipoCaptura the tipoCaptura to set
	 */
	public void setTipoCaptura(String tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	

}
