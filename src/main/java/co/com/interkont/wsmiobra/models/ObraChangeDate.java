package co.com.interkont.wsmiobra.models;

public class ObraChangeDate{
	
	private Integer id;
	
	private String fechaInicioObra; 

	private String fechaFinObra;
	
	public ObraChangeDate(Integer id,String fechaInicioObra,String fechaFinObra) {
		this.id = id;
		this.fechaInicioObra = fechaInicioObra;
		this.fechaFinObra = fechaFinObra;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaInicioObra() {
		return fechaInicioObra;
	}

	public void setFechaInicioObra(String fechaInicioObra) {
		this.fechaInicioObra = fechaInicioObra;
	}

	public String getFechaFinObra() {
		return fechaFinObra;
	}

	public void setFechaFinObra(String fechaFinObra) {
		this.fechaFinObra = fechaFinObra;
	}

	@Override
	public String toString() {
		return "ObraChangeDate [id=" + id + ", fechaInicioObra=" + fechaInicioObra + ", fechaFinObra=" + fechaFinObra
				+ "]";
	} 
	
	
	

	





		
  	
}
