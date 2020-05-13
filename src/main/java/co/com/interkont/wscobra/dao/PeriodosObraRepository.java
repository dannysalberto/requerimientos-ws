package co.com.interkont.wscobra.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import co.com.interkont.wscobra.dto.VistaPeriodosObra;

public interface PeriodosObraRepository extends JpaRepository<VistaPeriodosObra, Integer>{

	public List<VistaPeriodosObra> findByCodigoproyecto(Integer codigoproyecto);
}
