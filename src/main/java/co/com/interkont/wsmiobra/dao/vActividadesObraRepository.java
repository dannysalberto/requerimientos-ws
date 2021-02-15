package co.com.interkont.wsmiobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.api.response.ActividadObraResponse;
import co.com.interkont.wsmiobra.dto.Obra;

@Repository
public interface vActividadesObraRepository extends JpaRepository<ActividadObraResponse, Integer>{

	public List<ActividadObraResponse> findByObra(Obra obra);
	
}
