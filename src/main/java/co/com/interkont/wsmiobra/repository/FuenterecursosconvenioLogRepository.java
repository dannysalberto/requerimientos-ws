package co.com.interkont.wsmiobra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.interkont.wsmiobra.dto.FuenterecursosconvenioLog;



@Repository
public interface FuenterecursosconvenioLogRepository extends JpaRepository<FuenterecursosconvenioLog, Integer> {
}
