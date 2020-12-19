package co.com.interkont.wscobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.models.ActividadobraWS;


@Repository
public interface ActividadObraRepositoryWS extends JpaRepository<ActividadobraWS, Integer>{

	
	
}
