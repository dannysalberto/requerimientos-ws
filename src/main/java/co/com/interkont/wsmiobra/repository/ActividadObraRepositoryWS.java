package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ActividadobraWS;


@Repository
public interface ActividadObraRepositoryWS extends JpaRepository<ActividadobraWS, Integer>{

	List<ActividadobraWS> findByObra(Obra obra);
	List<ActividadobraWS> findByObraOrderByOidactiviobra(Obra obra);

	
}
