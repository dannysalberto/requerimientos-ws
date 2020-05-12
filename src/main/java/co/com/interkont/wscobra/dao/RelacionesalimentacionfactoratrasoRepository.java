package co.com.interkont.wscobra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Relacionalimentacionfactoratraso;

@Repository
public interface RelacionesalimentacionfactoratrasoRepository extends JpaRepository<Relacionalimentacionfactoratraso, Integer>{
	
}
