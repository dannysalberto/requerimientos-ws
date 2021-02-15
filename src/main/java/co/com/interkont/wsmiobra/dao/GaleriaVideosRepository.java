package co.com.interkont.wsmiobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.dto.GaleriaVideos;

@Repository
public interface GaleriaVideosRepository extends JpaRepository<GaleriaVideos, Integer> {

	List<GaleriaVideos> findByObra_id(Integer idObra);
	
}
