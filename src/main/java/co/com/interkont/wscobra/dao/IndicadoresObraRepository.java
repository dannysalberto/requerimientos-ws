package co.com.interkont.wscobra.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import co.com.interkont.wscobra.dto.VistaIndicadoresObra;

public interface IndicadoresObraRepository extends JpaRepository<VistaIndicadoresObra, Integer>{

	public List<VistaIndicadoresObra> findByCodigoproyecto(Integer codigoproyecto);
}
