package co.com.interkont.wsmiobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.Contrato;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
}
