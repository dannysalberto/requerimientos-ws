package co.com.interkont.wscobra.dto;
// Generated 11/05/2020 05:39:08 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RelacionalimentacionfactoratrasoId generated by hbm2java
 */
@Embeddable
public class RelacionalimentacionfactoratrasoId  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int intidfactor;
     private long intidalimenta;

    public RelacionalimentacionfactoratrasoId() {
    }

    public RelacionalimentacionfactoratrasoId(int intidfactor, long intidalimenta) {
       this.intidfactor = intidfactor;
       this.intidalimenta = intidalimenta;
    }
   


    @Column(name="intidfactor", nullable=false)
    public int getIntidfactor() {
        return this.intidfactor;
    }
    
    public void setIntidfactor(int intidfactor) {
        this.intidfactor = intidfactor;
    }


    @Column(name="intidalimenta", nullable=false)
    public long getIntidalimenta() {
        return this.intidalimenta;
    }
    
    public void setIntidalimenta(long intidalimenta) {
        this.intidalimenta = intidalimenta;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RelacionalimentacionfactoratrasoId) ) return false;
		 RelacionalimentacionfactoratrasoId castOther = ( RelacionalimentacionfactoratrasoId ) other; 
         
		 return (this.getIntidfactor()==castOther.getIntidfactor())
 && (this.getIntidalimenta()==castOther.getIntidalimenta());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIntidfactor();
         result = 37 * result + (int) this.getIntidalimenta();
         return result;
   }   


}

