package co.com.interkont.wscobra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.FactorAtraso;

@Repository
public interface FactorAtrasoRepository extends JpaRepository<FactorAtraso, Integer>{

}
