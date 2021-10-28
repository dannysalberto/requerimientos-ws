package co.com.interkont.avanzame.bussines.interfaces;

import org.springframework.http.ResponseEntity;

import co.com.interkont.avanzame.api.request.ObraFPORequest;

public interface IObraFPOBussines {

	ResponseEntity<?> obtenerObraFPO(Integer idobra);
	ResponseEntity<?> guardarObraFPO(ObraFPORequest obraFPO);
	
}
