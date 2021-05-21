package co.com.interkont.wsmiobra.api.response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;	
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.com.interkont.wsmiobra.models.ResponseGeneric;	

//@ControllerAdvice	
public class ExceptionGlobalResponse {	
	
	ResponseGeneric result;	
	@ExceptionHandler(RuntimeException.class)		
	public ResponseEntity<ResponseGeneric> runtimeException(RuntimeException e) {
		result = new ResponseGeneric(false, e.getMessage());
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);		
	}	

}