/**
 * 
 */
package co.com.interkont.wsmiobra.repository;

/**
 * @author VictorGomez
 *
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.TipoDocumentos;
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentos, Integer>  {

	List<TipoDocumentos> findByIntparametipdoc(Integer inttipodoc);
	
	
}
