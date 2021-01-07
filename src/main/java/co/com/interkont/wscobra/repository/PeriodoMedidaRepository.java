package co.com.interkont.wscobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.models.PeriodoMedida;


@Repository
public interface PeriodoMedidaRepository extends JpaRepository<PeriodoMedida, Integer> {

}
