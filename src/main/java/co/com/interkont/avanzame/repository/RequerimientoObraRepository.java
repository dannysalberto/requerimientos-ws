package co.com.interkont.avanzame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.com.interkont.avanzame.models.RequerimientoObra;

@Repository
public interface RequerimientoObraRepository extends JpaRepository<RequerimientoObra, Integer> {

	List<RequerimientoObra> findByobraid(Integer obraid);
	
}
