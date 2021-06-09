package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.V_ActividadObraModificacion;

@Repository
public interface V_ActividadObraModificacionRepository extends JpaRepository<V_ActividadObraModificacion,Integer> {

	List<V_ActividadObraModificacion> findByObraModificacion_Id(Integer id);

	List<V_ActividadObraModificacion> findByObraModificacion(ObraModificacion obra);
	
}
