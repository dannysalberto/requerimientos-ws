package co.com.interkont.wscobra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.GaleriaVideos;

@Repository
public interface GaleriaVideosRepository extends JpaRepository<GaleriaVideos, Integer> {

	List<GaleriaVideos> findByObra_id(Integer idObra);
	
}
