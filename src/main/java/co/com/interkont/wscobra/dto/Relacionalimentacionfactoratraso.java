package co.com.interkont.wscobra.dto;
// Generated 11/05/2020 05:39:08 PM by Hibernate Tools 3.6.0


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relacionalimentacionfactoratraso generated by hbm2java
 */
@Entity
@Table(name="relacionalimentacionfactoratraso"
    ,schema="public"
)
public class Relacionalimentacionfactoratraso  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RelacionalimentacionfactoratrasoId id;
     private Alimentacion alimentacion;

    public Relacionalimentacionfactoratraso() {
    }

    public Relacionalimentacionfactoratraso(RelacionalimentacionfactoratrasoId id, Alimentacion alimentacion) {
       this.id = id;
       this.alimentacion = alimentacion;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="intidfactor", column=@Column(name="intidfactor", nullable=false) ), 
        @AttributeOverride(name="intidalimenta", column=@Column(name="intidalimenta", nullable=false) ) } )
    public RelacionalimentacionfactoratrasoId getId() {
        return this.id;
    }
    
    public void setId(RelacionalimentacionfactoratrasoId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="intidalimenta", nullable=false, insertable=false, updatable=false)
    public Alimentacion getAlimentacion() {
        return this.alimentacion;
    }
    
    public void setAlimentacion(Alimentacion alimentacion) {
        this.alimentacion = alimentacion;
    }




}

