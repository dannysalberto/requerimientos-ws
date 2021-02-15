package co.com.interkont.wsmiobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.models.PeriodoMedida;


@Repository
public interface PeriodoMedidaRepository extends JpaRepository<PeriodoMedida, Integer> {

}
