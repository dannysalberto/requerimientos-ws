/**
 * 
 */
package co.com.interkont.wsmiobra.models;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author VictorGomez
 *
 */
@Entity
@Table(schema="public",name="tipodocumento")
public class TipoDocumentos {
	
	@Id
	private int inttipodoc;	
    private String strdesctipodoc;
    private boolean obligatorio;
    private boolean obligatorioconvenio;
    private boolean condicionEspecial;
    private int controlvisualizacion;
    private int intparametipdoc;
    
    /**
	 * @return the inttipodoc
	 */
	public int getInttipodoc() {
		return inttipodoc;
	}
	/**
	 * @param inttipodoc the inttipodoc to set
	 */
	public void setInttipodoc(int inttipodoc) {
		this.inttipodoc = inttipodoc;
	}
	/**
	 * @return the strdesctipodoc
	 */
	public String getStrdesctipodoc() {
		return strdesctipodoc;
	}
	/**
	 * @param strdesctipodoc the strdesctipodoc to set
	 */
	public void setStrdesctipodoc(String strdesctipodoc) {
		this.strdesctipodoc = strdesctipodoc;
	}
	/**
	 * @return the obligatorio
	 */
	public boolean isObligatorio() {
		return obligatorio;
	}
	/**
	 * @param obligatorio the obligatorio to set
	 */
	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	/**
	 * @return the obligatorioconvenio
	 */
	public boolean isObligatorioconvenio() {
		return obligatorioconvenio;
	}
	/**
	 * @param obligatorioconvenio the obligatorioconvenio to set
	 */
	public void setObligatorioconvenio(boolean obligatorioconvenio) {
		this.obligatorioconvenio = obligatorioconvenio;
	}
	/**
	 * @return the condicionEspecial
	 */
	public boolean isCondicionEspecial() {
		return condicionEspecial;
	}
	/**
	 * @param condicionEspecial the condicionEspecial to set
	 */
	public void setCondicionEspecial(boolean condicionEspecial) {
		this.condicionEspecial = condicionEspecial;
	}
	/**
	 * @return the controlvisualizacion
	 */
	public int getControlvisualizacion() {
		return controlvisualizacion;
	}
	/**
	 * @param controlvisualizacion the controlvisualizacion to set
	 */
	public void setControlvisualizacion(int controlvisualizacion) {
		this.controlvisualizacion = controlvisualizacion;
	}
	/**
	 * @return the intparametipdoc
	 */
	public int getIntparametipdoc() {
		return intparametipdoc;
	}
	/**
	 * @param intparametipdoc the intparametipdoc to set
	 */
	public void setIntparametipdoc(int intparametipdoc) {
		this.intparametipdoc = intparametipdoc;
	}
	@Override
	public String toString() {
		return "TipoDocumentos [inttipodoc=" + inttipodoc + ", strdesctipodoc=" + strdesctipodoc + ", obligatorio="
				+ obligatorio + ", obligatorioconvenio=" + obligatorioconvenio + ", condicionEspecial="
				+ condicionEspecial + ", controlvisualizacion=" + controlvisualizacion + ", intparametipdoc="
				+ intparametipdoc + "]";
	}
	
	
	
}
