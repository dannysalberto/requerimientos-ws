package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ObraModificacion;

@Repository
public interface ActividadObraModificacionRepository extends JpaRepository<ActividadObraModificacion,Integer> {

	List<ActividadObraModificacion> findByObraModificacion(ObraModificacion obra);

	List<ActividadObraModificacion> findByObraModificacion_IdAndTipoModificacionNot(Integer id, String tipo);
	
}
