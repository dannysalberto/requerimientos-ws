package co.com.interkont.wsmiobra.models;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ResponseGeneric implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean status;
	
	private String message;
	
	private Object obj;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the objReturn
	 */
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public Object getObj() {
		return obj;
	}

	/**
	 * @param objReturn the objReturn to set
	 */
	public void setObj(Object objReturn) {
		this.obj = objReturn;
	}

	


}
