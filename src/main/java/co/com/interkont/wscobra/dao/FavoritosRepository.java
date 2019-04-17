package co.com.interkont.wscobra.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.VistaFavoritoAsignados;

@Repository
public interface FavoritosRepository extends JpaRepository<VistaFavoritoAsignados, Integer>{
	
	
	public List<VistaFavoritoAsignados> findByUsuario(Integer codigousuario, BigDecimal latitud, BigDecimal longitud);

}
