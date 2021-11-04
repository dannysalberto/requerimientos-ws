package co.com.interkont.avanzame.businnes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.com.interkont.avanzame.bussines.interfaces.ISolicitudBussines;
import co.com.interkont.avanzame.config.Constantes;
import co.com.interkont.avanzame.models.Obra;
import co.com.interkont.avanzame.models.ObraFPO;
import co.com.interkont.avanzame.models.SolicitudFPO;
import co.com.interkont.avanzame.service.ObraFPOJPAServices;
import co.com.interkont.avanzame.service.ObraJPAServices;
import co.com.interkont.avanzame.service.SolicitudFPOJPAServices;
import co.com.interkont.avanzame.api.request.SolicitudFPORequest;
import co.com.interkont.avanzame.api.request.SolicitudFPOUpdateRequest;
import co.com.interkont.avanzame.api.response.ResponseGeneric;


@Component
public class BusinnesSolicitudFPOServices implements ISolicitudBussines{

	@Autowired
	SolicitudFPOJPAServices service;
	
	@Autowired
	ObraFPOJPAServices serviceObraFPO;
	
	@Autowired
	ObraJPAServices serviceObra;
	
	@Override
	public ResponseEntity<?> solicitudes(int idobra) {
		// TODO Auto-generated method stub
		ResponseGeneric response = new ResponseGeneric(); 

		try {
			List<SolicitudFPO> lstsolicitudes = service.desplegarTodos(idobra);
			
			if (lstsolicitudes!=null) {
				lstsolicitudes.sort(Comparator.comparing(SolicitudFPO::getId).reversed());
				return new ResponseEntity<List<SolicitudFPO>>(lstsolicitudes, HttpStatus.OK);
			}else {
				response.setStatus(false);
				response.setMensaje(Constantes.NO_EXISTE);
				return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
			}	
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ResponseEntity<?> guardarSolicitud(SolicitudFPORequest solicitud) {
		// TODO Auto-generated method stub
		SolicitudFPO objSolicitud = new SolicitudFPO();
		objSolicitud.setDiasSolicitados(solicitud.getDiasSolicitados());
		objSolicitud.setEstado(Constantes.ENREVISION);
		objSolicitud.setObraid(solicitud.getObraid());
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			objSolicitud.setFechaSolicitud(formato.parse(solicitud.getFechaSolicitud()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObraFPO obraFPO = serviceObraFPO.buscarPorId(objSolicitud.getObraid());

		objSolicitud.setJustificacion(solicitud.getJustificacion());
		objSolicitud.setFpocompromisoenergia(obraFPO.getFechaPuestaOperacion());
		if (solicitud.getSolicitudOrigenId()>0) {
			objSolicitud.setSolicitudOrigenId(solicitud.getSolicitudOrigenId());			
		}else {
			objSolicitud.setSolicitudOrigenId(null);
		}
		
		return new ResponseEntity<SolicitudFPO>(service.guardar(objSolicitud), HttpStatus.OK);
	}

	@SuppressWarnings("static-access")
	public ResponseEntity<?> actualizarSolicitud(SolicitudFPOUpdateRequest solicitud) {
		// TODO Auto-generated method stub
		ResponseGeneric response = new ResponseGeneric(); 
		SolicitudFPO objSolicitud = service.buscarPorId(solicitud.getId());		
		
		if (objSolicitud == null ) {
			response.setStatus(false);
			response.setMensaje(Constantes.NO_EXISTE_SOLICITUD);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		ObraFPO obraFPO = serviceObraFPO.buscarPorId(objSolicitud.getObraid());
		Obra obra = serviceObra.buscarPorId(objSolicitud.getObraid());
		if (obra.getFechaPuestaOperacion() == null ) {
			response.setStatus(false);
			response.setMensaje(Constantes.NO_OTORGAR_FPO);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.BAD_REQUEST);
		}
		
		objSolicitud.setDiasOtorgados(solicitud.getDiasOtorgados());
		if (objSolicitud.getDiasOtorgados()>0) {
			objSolicitud.setEstado(Constantes.CONCEDIDO);	
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(obraFPO.getFechaPuestaOperacion());
			calendar.add(calendar.DAY_OF_YEAR, solicitud.getDiasOtorgados()-1);
			objSolicitud.setFechaNueva(calendar.getTime());
			
			obra.setFechaPuestaOperacion(objSolicitud.getFechaNueva());
		}else {
			objSolicitud.setEstado(Constantes.NOCONCEDIDO);
		}
		
		objSolicitud.setJustificacionFPOOtorgada(solicitud.getJustificacionFPOOtorgada());
		objSolicitud.setResolucionOtorgante(solicitud.getResolucionOtorgante());
		try {
			objSolicitud = service.actualizar(objSolicitud);
			if (objSolicitud != null) {
				serviceObra.actualizar(obra);
				return new ResponseEntity<SolicitudFPO>(service.actualizar(objSolicitud), HttpStatus.OK);				
			}

		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus(false);
			response.setMensaje(e.getMessage().toString());
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

	@Override
	public ResponseEntity<?> solicitud(int id) {
		// TODO Auto-generated method stub
		return new ResponseEntity<SolicitudFPO>(service.buscarPorId(id), HttpStatus.OK);
	}

	
}
