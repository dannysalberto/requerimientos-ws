package co.com.interkont.wsmiobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.com.interkont.wsmiobra.models.RelacionContratoObraTable;

@Repository
public interface RelacionContratoObraTableRepository extends JpaRepository<RelacionContratoObraTable, Integer>{
	
	RelacionContratoObraTable findByobraOriginalAndContrato_Id(Integer idObra, Integer idContrato);

}
