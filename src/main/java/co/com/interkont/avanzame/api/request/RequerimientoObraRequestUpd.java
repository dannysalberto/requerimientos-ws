package co.com.interkont.avanzame.api.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequerimientoObraRequestUpd {

	
	public RequerimientoObraRequestUpd(@NotNull @NotEmpty(message = "Movie name cannot be empty.") Integer id,
			@NotNull Integer obraid, @NotNull Integer requerimientoid, @NotNull String valor) {
		super();
		this.id = id;
		this.obraid = obraid;
		this.requerimientoid = requerimientoid;
		this.valor = valor;
	}

	@NotNull
	@NotEmpty(message = "Movie name cannot be empty.")
	private Integer id;
	
	@NotNull
	private Integer obraid;
	
	@NotNull
	private Integer requerimientoid;
	
	@NotNull
	private String valor;
	

	
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
