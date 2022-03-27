package co.com.interkont.avanzame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.avanzame.models.Obra;
import co.com.interkont.avanzame.models.Requerimiento;

@Repository
public interface RequerimientoRepository extends JpaRepository<Requerimiento, Integer> {

}
