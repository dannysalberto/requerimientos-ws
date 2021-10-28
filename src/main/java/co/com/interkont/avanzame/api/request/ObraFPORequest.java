package co.com.interkont.avanzame.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class ObraFPORequest {

	 	@ApiModelProperty(value = "Id Obra")
		private Integer id;
		
	    @ApiModelProperty(value = "Fecha FPO")
		@NotNull(message = "Debe especificar la fecha probable de puesta en operaciones")
		private String FechaFPO;

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
		 * @return the fechaFPO
		 */
		public String getFechaFPO() {
			return FechaFPO;
		}

		/**
		 * @param fechaFPO the fechaFPO to set
		 */
		public void setFechaFPO(String fechaFPO) {
			FechaFPO = fechaFPO;
		}
	    
}
