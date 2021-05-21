package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.RelacionContratoObra;

@Repository
public interface RelacionContratoObraRepository extends JpaRepository<RelacionContratoObra, Integer>{
	
	List<RelacionContratoObra> findByobraOriginal(Integer idObra);
	RelacionContratoObra findByobraOriginalAndContrato_Id(Integer idObra, Integer idContrato);

}
