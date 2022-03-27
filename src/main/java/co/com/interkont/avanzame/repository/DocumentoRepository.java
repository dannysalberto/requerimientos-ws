package co.com.interkont.avanzame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.avanzame.models.Documento;


@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

	List<Documento> findByobraid(Integer id);
	List<Documento> findBycontratoid(Integer id);
	Documento findBydownloadid(String sId);

}
