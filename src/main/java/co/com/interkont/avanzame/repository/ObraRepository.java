package co.com.interkont.avanzame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.avanzame.models.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {

}
