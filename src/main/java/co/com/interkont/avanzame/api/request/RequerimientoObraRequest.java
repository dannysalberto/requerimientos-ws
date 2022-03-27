package co.com.interkont.avanzame.api.request;

import javax.validation.constraints.NotNull;

public class RequerimientoObraRequest {

	@NotNull
	private Integer obraid;
	
	@NotNull
	private Integer requerimientoid;
	
	@NotNull
	private String valor;
	

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
	 * @return the requerimientoid
	 */
	public Integer getRequerimientoid() {
		return requerimientoid;
	}

	/**
	 * @param requerimientoid the requerimientoid to set
	 */
	public void setRequerimientoid(Integer requerimientoid) {
		this.requerimientoid = requerimientoid;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
	
}
