package co.com.interkont.avanzame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.avanzame.models.DocumentosRequerimiento;

@Repository
public interface DocumentosRequerimientoRepository extends JpaRepository<DocumentosRequerimiento, Integer> {

	List<DocumentosRequerimiento> findByobraid(Integer obraid);
}
