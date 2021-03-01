package co.com.interkont.wsmiobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.ObraModificacion;

@Repository
public interface ObraModificacionRepository extends JpaRepository<ObraModificacion, Integer> {

	@Query("SELECT SUM(m.valortotalactividadaiu) FROM ActividadObraModificacion m where m.obraModificacion.id = ?1")
	double totalActividades(Integer idObra);
	
	@Query("SELECT SUM(m.floatcantplanifao*m.valorunitario) FROM ActividadObraModificacion m where m.obraModificacion.id = ?1")
	double totalCostoDirecto(Integer idObra);

	@Query("SELECT COUNT(m) FROM ActividadObraModificacion m "
			+ " inner join ObraModificacion o on m.obraModificacion.id = o.id where m.obraModificacion.id = ?1")
	Integer cantidadActividades(Integer idObra);
	
	ObraModificacion findByObraidAndEstadoModificacion(Integer IdObra,String estado);
}
