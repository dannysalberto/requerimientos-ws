package co.com.interkont.wscobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Obra;

@Repository
public interface ObrasRepository extends JpaRepository<Obra, Integer>{
	
	@Query("SELECT SUM(m.valortotalactividadaiu) FROM ActividadobraWS m where m.obra.id = ?1")
	double totalActividades(Integer idObra);
	
	@Query("SELECT SUM(m.floatcantplanifao*m.valorunitario) FROM ActividadobraWS m where m.obra.id = ?1")
	double totalCostoDirecto(Integer idObra);

	@Query("SELECT COUNT(m) FROM ActividadobraWS m "
			+ " inner join Obra o on m.obra.id = o.id where m.obra.id = ?1")
	Integer cantidadActividades(Integer idObra);
	
}
