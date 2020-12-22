package co.com.interkont.wscobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.models.ActividadobraWS;


@Repository
public interface ActividadObraRepositoryWS extends JpaRepository<ActividadobraWS, Integer>{

	List<ActividadobraWS> findByObra(Obra obra);
	
}
