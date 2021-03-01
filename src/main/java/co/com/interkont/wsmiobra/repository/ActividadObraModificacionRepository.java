package co.com.interkont.wsmiobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.ActividadObraModificacion;

@Repository
public interface ActividadObraModificacionRepository extends JpaRepository<ActividadObraModificacion,Integer> {

}
