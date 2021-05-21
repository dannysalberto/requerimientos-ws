package co.com.interkont.wsmiobra.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.com.interkont.wsmiobra.dto.Obra;


@Repository
public interface ObrasRepository extends JpaRepository<Obra, Integer>{
	
	@Query("SELECT SUM(m.valortotalactividadaiu) FROM ActividadobraWS m where m.obra.id = ?1")
	double totalActividades(Integer idObra);
	
	@Query("SELECT SUM(m.floatcantplanifao*m.valorunitario) FROM ActividadobraWS m where m.obra.id = ?1")
	double totalCostoDirecto(Integer idObra);

	@Query("SELECT COUNT(m) FROM ActividadobraWS m "
			+ " inner join Obra o on m.obra.id = o.id where m.obra.id = ?1")
	Integer cantidadActividades(Integer idObra);
	
	@Query(value="SELECT cto.intidcontrato\r\n" + 
			"    FROM \"public\".\"obra\" o LEFT JOIN\r\n" + 
			"	(SELECT rco.intcodigoobra as intcodigoobra, \r\n" + 
			"	MIN(rco.intidcontrato) as intidcontrato \r\n" + 
			"	FROM \"public\".\"relacioncontratoobra\" rco\r\n" + 
			" 		INNER JOIN \"public\".\"contrato\" pc ON rco.intidcontrato=pc.intidcontrato \r\n" + 
			"        WHERE pc.booltipocontratoconvenio=False AND pc.inttipocontrato IS NOT NULL \r\n" + 
			"        GROUP BY rco.intcodigoobra) cto on o.intcodigoobra=cto.intcodigoobra"+
			" WHERE o.intcodigoobra=?1" , nativeQuery = true)
	Integer tieneContratoObra(Integer idObra);
	
	@Query("SELECT MAX(c.datefecha) FROM Alimentacion c where c.intcodigoobra = ?1")
	Date fechaMaxAlimentacion(Integer idObra);
	
}
