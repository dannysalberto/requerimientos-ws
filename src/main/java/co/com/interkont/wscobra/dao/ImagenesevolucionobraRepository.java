package co.com.interkont.wscobra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Imagenevolucionobra;

@Repository
public interface ImagenesevolucionobraRepository extends JpaRepository<Imagenevolucionobra, Integer>{
	
}
