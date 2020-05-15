package co.com.interkont.wscobra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wscobra.dto.Actividadobra;

@Repository
public interface ActividadesobraRepository extends JpaRepository<Actividadobra, Integer>{
	
	public Actividadobra findByOidactiviobra(Long oidactiviobra);
		
}
