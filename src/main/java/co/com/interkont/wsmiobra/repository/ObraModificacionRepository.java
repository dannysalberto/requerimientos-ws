package co.com.interkont.wsmiobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.ObraModificacion;

@Repository
public interface ObraModificacionRepository extends JpaRepository<ObraModificacion, Integer> {

	@Query("SELECT SUM(m.newvalortotalactividadaiu) FROM ActividadObraModificacion m where m.obraModificacion.id = ?1 and "
			+ " m.tipoModificacion !='D'")
	Double totalActividades(Integer idObra);
	
	@Query("SELECT SUM(m.newfloatcantplanifao*m.newvalorunitario) FROM ActividadObraModificacion m where m.obraModificacion.id = ?1 and "
			+ " m.tipoModificacion !='D'")
	Double totalCostoDirecto(Integer idObra);

	@Query("SELECT COUNT(m) FROM ActividadObraModificacion m "
			+ " inner join ObraModificacion o on m.obraModificacion.id = o.id where m.obraModificacion.id = ?1 and"
			+ " m.tipoModificacion !='D'")
	Integer cantidadActividades(Integer idObra);
	
	ObraModificacion findByObraidAndEstadoModificacion(Integer IdObra,String estado);
}
