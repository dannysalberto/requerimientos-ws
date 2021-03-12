package co.com.interkont.wsmiobra.api.request;

import java.util.Date;


public class ObraUpdateRequest {
	
	private int id;
	
	private Date newfechafin;
	
	private Integer newplazo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNewfechafin() {
		return newfechafin;
	}

	public void setNewfechafin(Date newfechafin) {
		this.newfechafin = newfechafin;
	}

	public Integer getNewplazo() {
		return newplazo;
	}

	public void setNewplazo(Integer newplazo) {
		this.newplazo = newplazo;
	}
	
	
	

}
