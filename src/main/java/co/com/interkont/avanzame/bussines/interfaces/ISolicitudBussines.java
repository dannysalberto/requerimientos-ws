package co.com.interkont.avanzame.bussines.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import co.com.interkont.avanzame.api.request.SolicitudFPORequest;
import co.com.interkont.avanzame.api.request.SolicitudFPOUpdateRequest;

public interface ISolicitudBussines {

	ResponseEntity<?> solicitudes(int idobra);
	ResponseEntity<?> solicitud(int id);
	ResponseEntity<?> guardarSolicitud(SolicitudFPORequest solicitud);
	ResponseEntity<?> actualizarSolicitud(SolicitudFPOUpdateRequest solicitud);
	//ResponseEntity<?> guardarSolicitud(String solicitud);
	ResponseEntity<?> guardarSolicitud(String solicitud, HttpServletRequest request);

}
