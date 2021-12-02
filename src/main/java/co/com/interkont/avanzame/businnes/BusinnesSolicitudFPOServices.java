package co.com.interkont.avanzame.businnes;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import co.com.interkont.avanzame.api.request.FileRequest;
import co.com.interkont.avanzame.api.request.SolicitudFPORequest;
import co.com.interkont.avanzame.api.request.SolicitudFPOUpdateRequest;
import co.com.interkont.avanzame.api.response.ResponseArgo;
import co.com.interkont.avanzame.api.response.ResponseGeneric;
import co.com.interkont.avanzame.bussines.interfaces.ISolicitudBussines;
import co.com.interkont.avanzame.config.Constantes;
import co.com.interkont.avanzame.models.Documento;
import co.com.interkont.avanzame.models.Obra;
import co.com.interkont.avanzame.models.ObraFPO;
import co.com.interkont.avanzame.models.SolicitudFPO;
import co.com.interkont.avanzame.service.DocumentoService;
import co.com.interkont.avanzame.service.ObraFPOJPAServices;
import co.com.interkont.avanzame.service.ObraJPAServices;
import co.com.interkont.avanzame.service.SolicitudFPOJPAServices;
import co.com.interkont.avanzame.utils.RandomString;




@Component
public class BusinnesSolicitudFPOServices implements ISolicitudBussines{

	@Autowired
	SolicitudFPOJPAServices service;
	
	@Autowired
	ObraFPOJPAServices serviceObraFPO;
	
	@Autowired
	ObraJPAServices serviceObra;
	
	@Autowired
	DocumentoService serviceDocumento;
		
	@Value("${app.servicioRadicado}")
	private String urlRadicado;
	
	@Value("${app.servicioOneDrive}")
	private String urlOneDrive;
	
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
	@Transactional
	public ResponseEntity<?> guardarSolicitud(String solicitud,HttpServletRequest request) {
		// TODO Auto-generated method stub
		SolicitudFPO objSolicitud = new SolicitudFPO();
		SolicitudFPORequest solicitudFPO = new Gson().fromJson(solicitud, SolicitudFPORequest.class);
		
		objSolicitud.setDiasSolicitados(solicitudFPO.getDiasSolicitados());
		objSolicitud.setEstado(Constantes.ENREVISION);
		objSolicitud.setObraid(solicitudFPO.getObraid());
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			objSolicitud.setFechaSolicitud(formato.parse(solicitudFPO.getFechaSolicitud()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObraFPO obraFPO = serviceObraFPO.buscarPorId(solicitudFPO.getObraid());
		System.out.println(solicitudFPO);
		objSolicitud.setJustificacion(solicitudFPO.getJustificacion());
		objSolicitud.setFpocompromisoenergia(obraFPO.getFechaPuestaOperacion());
		if (solicitudFPO.getSolicitudOrigenId()!=null && solicitudFPO.getSolicitudOrigenId()>0) {
			objSolicitud.setSolicitudOrigenId(solicitudFPO.getSolicitudOrigenId());			
		}else {
			objSolicitud.setSolicitudOrigenId(null);
		}
		
        String resourceUrl = urlRadicado;
        resourceUrl = resourceUrl + "/documento/radicar"  ;
       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        
        /*datos para el bodyrequest argo*/
        genBody(solicitudFPO, body);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
          .postForEntity(resourceUrl, requestEntity, String.class);
        
        Gson gson = new Gson();
        ResponseArgo resp = new ResponseArgo();
        resp = gson.fromJson(response.getBody().toString(), ResponseArgo.class);
        objSolicitud.setNumeroRadicado(resp.getContent());   
        objSolicitud = service.guardar(objSolicitud);
        /**/

        HttpHeaders headersDrive = new HttpHeaders();
        headersDrive.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
       
        String JWTtoken = request.getHeader(Constantes.HEADER_AUTHORIZACION_KEY);
        //JWTtoken = JWTtoken.substring(6);
        headersDrive.set("Authorization", JWTtoken);
        MultiValueMap<String, String> bodyDrive = new LinkedMultiValueMap<String, String>();
        
        FileRequest fileReq = new FileRequest();
        fileReq.setFileBase64(solicitudFPO.getRadicarDocumento().getBase64Binary());
        fileReq.setClasificacion("4");
        fileReq.setIdtipodocumento("10");
        fileReq.setIddocumento(Integer.toString(objSolicitud.getId()));
        fileReq.setNombredocumento(solicitudFPO.getRadicarDocumento().getFileName());
        fileReq.setUsuarioid("4");
        
        ObjectMapper mapper = new ObjectMapper();
        String JSON = null;
		try {
			JSON = mapper.writeValueAsString(fileReq);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //generarRequest(objSolicitud, solicitudFPO, fileReq);
        
        HttpEntity<String> requestEntityDrive = 
        		new HttpEntity<>(JSON, headersDrive);
        
        System.out.println(JSON);
        String resourceUrlDrive= urlOneDrive;
        RestTemplate restTemplateDrive = new RestTemplate();

        
        @SuppressWarnings("unused")
		ResponseEntity<String> respOneDrive = restTemplateDrive
          .postForEntity(resourceUrlDrive, requestEntityDrive, String.class);
       
        /**/
        
		return new ResponseEntity<SolicitudFPO>(service.guardar(objSolicitud), HttpStatus.OK);
	}

	/**
	 * @param objSolicitud
	 * @param solicitudFPO
	 * @param fileReq
	 */
	private void generarRequest(SolicitudFPO objSolicitud, SolicitudFPORequest solicitudFPO, FileRequest fileReq) {
		fileReq.setFileBase64(solicitudFPO.getRadicarDocumento().getBase64Binary());
        fileReq.setClasificacion("4");
        fileReq.setIdtipodocumento("10");
        fileReq.setIddocumento(Integer.toString(objSolicitud.getId()));
        fileReq.setNombredocumento(solicitudFPO.getRadicarDocumento().getFileName());
        fileReq.setUsuarioid("4");
	}

	/**
	 * @param objSolicitud
	 * @param solicitudFPO
	 * @param bodyOneDrive
	 */
	private void genBodyDrive(SolicitudFPO objSolicitud, SolicitudFPORequest solicitudFPO,
			MultiValueMap<String, String> bodyOneDrive) {
		bodyOneDrive.add("file", solicitudFPO.getRadicarDocumento().getBase64Binary());
        bodyOneDrive.add("clasificaciondoc", "4");
        bodyOneDrive.add("idtipodocumento", "10");
        bodyOneDrive.add("iddocumento", Integer.toString(objSolicitud.getId()));
        bodyOneDrive.add("nombredocumento", solicitudFPO.getRadicarDocumento().getFileName());
        bodyOneDrive.add("usuarioid", "4");
	}
	
	
	/**
	 * @param objSolicitud
	 * @param solicitudFPO
	 * @param bodyOneDrive
	 */
	private void genBodyOneDrive(SolicitudFPO objSolicitud, SolicitudFPORequest solicitudFPO,
			MultiValueMap<String, Object> bodyOneDrive) {
		bodyOneDrive.add("file", solicitudFPO.getRadicarDocumento().getBase64Binary());
        bodyOneDrive.add("clasificaciondoc", 4);
        bodyOneDrive.add("idtipodocumento", 10);
        bodyOneDrive.add("iddocumento", objSolicitud.getId());
        bodyOneDrive.add("nombredocumento", solicitudFPO.getRadicarDocumento().getFileName());
        bodyOneDrive.add("usuarioid", "4");
        bodyOneDrive.add("publico", "0");
        bodyOneDrive.add("size", solicitudFPO.getRadicarDocumento().getSize());
	}

	/**
	 * @param solicitudFPO
	 * @param body
	 */
	private void genBody(SolicitudFPORequest solicitudFPO, MultiValueMap<String, Object> body) {
		body.add("anexo", solicitudFPO.getRadicarDocumento().getAsunto());
        body.add("camposAdicionales", "");
        body.add("codigoCarpeta", "");
        body.add("codigoDependenciaRadicadora", "100");
        body.add("cuentai", "");
        body.add("destinatarioCCDocumento", "102345210"); //pudieran ser datos del supervisor
        body.add("destinatarioDireccion", "Calle 43 No. 57 - 31 Bogota, Colombia");
        body.add("destinatarioContinente", "1");
        body.add("destinatarioIdPais", "170");
		body.add("destinatarioCodDepartamento", "11");
        body.add("destinatarioMunicipio", "001");
        body.add("destinatarioNombre", "DANNYS ALBERTO");
        body.add("destinatarioPrimerApellido", " ");
        body.add("destinatarioSegundoApellido", " ");
        body.add("destinatarioTelefono", "+57 1 2200300");
        body.add("formularioIdRadicado", "");
        body.add("key", "");
        body.add("medio", "1");
        body.add("tipoCarpeta", "0");
        body.add("tipoIdentificacion", "0");
        body.add("tipoRadicado", "1");
        body.add("tipoRemitente", "1");
        body.add("usuarioDocuActual", "23540024145");
        body.add("usuarioLogin", "INTERKONT");
        body.add("asunto", "Solicitud Prorroga FPO ");
        body.add("base64Binary", solicitudFPO.getRadicarDocumento().getBase64Binary());
        body.add("fileName", solicitudFPO.getRadicarDocumento().getFileName());
        body.add("tipoDocumento", solicitudFPO.getRadicarDocumento().getTipoDocumento());
        body.add("expediente", solicitudFPO.getRadicarDocumento().getExpediente());
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

		SolicitudFPO solicitud = service.buscarPorId(id);
		Documento documento = serviceDocumento.buscarPorId(id);
		if (documento!=null) {
			solicitud.setDocumentoObra(serviceDocumento.buscarPorId(id));
			generateDownloadID(id, solicitud);			
		}
		
		return new ResponseEntity<SolicitudFPO>(solicitud, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @param solicitud
	 * @throws IllegalArgumentException
	 */
	private void generateDownloadID(int id, SolicitudFPO solicitud) throws IllegalArgumentException {
	
	    String generatedString = RandomString.getAlphaNumericString(128);
		solicitud.getDocumentoObra().setDownloadid(generatedString);
		service.actualizar(solicitud);
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


	
}
