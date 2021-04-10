package co.com.interkont.wsmiobra.dto;
// Generated 26/02/2021 12:58:18 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Rolentidad generated by hbm2java
 */
@Entity
@Table(name="rolentidad"
    ,schema="planoperativo"
)
public class Rolentidad  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idrolentidad;
     private String strnombre;
     @JsonIgnore
     private Set<Fuenterecursosconvenio> fuenterecursosconvenios = new HashSet<Fuenterecursosconvenio>(0);
     @JsonIgnore
     private Set<FuenterecursosconvenioLog> fuenterecursosconvenioLogs = new HashSet<FuenterecursosconvenioLog>(0);

    public Rolentidad() {
    }

	
    public Rolentidad(int idrolentidad) {
        this.idrolentidad = idrolentidad;
    }
    public Rolentidad(int idrolentidad, String strnombre, Set<Fuenterecursosconvenio> fuenterecursosconvenios, Set<FuenterecursosconvenioLog> fuenterecursosconvenioLogs) {
       this.idrolentidad = idrolentidad;
       this.strnombre = strnombre;
       this.fuenterecursosconvenios = fuenterecursosconvenios;
       this.fuenterecursosconvenioLogs = fuenterecursosconvenioLogs;
    }
   
     @Id 

    
    @Column(name="idrolentidad", unique=true, nullable=false)
    public int getIdrolentidad() {
        return this.idrolentidad;
    }
    
    public void setIdrolentidad(int idrolentidad) {
        this.idrolentidad = idrolentidad;
    }

    
    @Column(name="strnombre")
    public String getStrnombre() {
        return this.strnombre;
    }
    
    public void setStrnombre(String strnombre) {
        this.strnombre = strnombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="rolentidad")
    public Set<Fuenterecursosconvenio> getFuenterecursosconvenios() {
        return this.fuenterecursosconvenios;
    }
    
    public void setFuenterecursosconvenios(Set<Fuenterecursosconvenio> fuenterecursosconvenios) {
        this.fuenterecursosconvenios = fuenterecursosconvenios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="rolentidad")
    public Set<FuenterecursosconvenioLog> getFuenterecursosconvenioLogs() {
        return this.fuenterecursosconvenioLogs;
    }
    
    public void setFuenterecursosconvenioLogs(Set<FuenterecursosconvenioLog> fuenterecursosconvenioLogs) {
        this.fuenterecursosconvenioLogs = fuenterecursosconvenioLogs;
    }




}


