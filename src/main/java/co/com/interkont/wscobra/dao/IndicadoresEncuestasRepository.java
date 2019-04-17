package co.com.interkont.wscobra.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.FuncionIndicadoresEncuestas;

@Repository
public interface IndicadoresEncuestasRepository extends JpaRepository<FuncionIndicadoresEncuestas, Integer>{
	
	
	@Query(nativeQuery = true,
		   value="SELECT * from encuestasue.f_calcular_indicadores_ue(NULL)")
	public FuncionIndicadoresEncuestas findByAll();
	
	@Query(nativeQuery = true,
		   value="SELECT * from encuestasue.f_calcular_indicadores_ue(:codigoproyecto)")
	public FuncionIndicadoresEncuestas findByProyecto(@Param("codigoproyecto") Integer codigoproyecto);

}
