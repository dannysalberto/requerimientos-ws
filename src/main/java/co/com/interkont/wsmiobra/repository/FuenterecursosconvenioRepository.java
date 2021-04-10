package co.com.interkont.wsmiobra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.dto.Contrato;
import co.com.interkont.wsmiobra.dto.Fuenterecursosconvenio;



@Repository
public interface FuenterecursosconvenioRepository extends JpaRepository<Fuenterecursosconvenio, Integer> {
	public List<Fuenterecursosconvenio> findByContratoAndCdpNot(Contrato contrato, String cdp);
	public List<Fuenterecursosconvenio> findByContratoAndCdpIsNull(Contrato contrato);
}
