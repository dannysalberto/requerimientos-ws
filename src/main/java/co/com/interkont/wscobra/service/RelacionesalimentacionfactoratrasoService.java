
package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.dao.RelacionesalimentacionfactoratrasoRepository;
import co.com.interkont.wscobra.dto.Relacionalimentacionfactoratraso;

@Service
public class RelacionesalimentacionfactoratrasoService {
	
	@Autowired
	RelacionesalimentacionfactoratrasoRepository relacionesalimentacionfactoratrasoRepository;
	
	
	public void saveAll(List<Relacionalimentacionfactoratraso> relacionesalimentacionfactoratraso) {
		relacionesalimentacionfactoratrasoRepository.saveAll(relacionesalimentacionfactoratraso);
	}

}