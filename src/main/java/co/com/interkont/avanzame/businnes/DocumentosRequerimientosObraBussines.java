package co.com.interkont.avanzame.businnes;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;

import co.com.interkont.avanzame.api.response.RequerimientoObraResponse;
import co.com.interkont.avanzame.api.response.ResponseGeneric;
import co.com.interkont.avanzame.api.response.ResponseGenericFile;
import co.com.interkont.avanzame.models.DocumentosRequerimiento;
import co.com.interkont.avanzame.service.DocumentosRequerimientoServices;
import co.com.interkont.avanzame.service.ObraJPAServices;
import co.com.interkont.avanzame.service.RequerimientoObraServices;
import co.com.interkont.avanzame.service.RequerimientoServices;
import co.com.interkont.avanzame.utils.AppContext;

@Component
public class DocumentosRequerimientosObraBussines {

	@Autowired
	RequerimientoObraServices repo;
	
	@Autowired
	RequerimientoServices repoRequerimiento;
	
	@Autowired
	ObraJPAServices repoObra;
	
	@Autowired
	DocumentosRequerimientoServices repoDocumentos;
	
	@Autowired
	private AppContext appContext;
	
	@Value("${app.servicioUpload}")
	String urlUpload;
	
	
	public List<DocumentosRequerimiento> obtenerDocumentosRequerimiento(Integer obraId) {
		// TODO Auto-generated method stub
	    List<DocumentosRequerimiento> data= repoDocumentos.listar(obraId);
	    
	    List<RequerimientoObraResponse> lstresp = new ArrayList();
		return data;
	    
	    
	}
	
	public ResponseGenericFile crear(Integer obraid,String nombrearchivo, Integer tipodoc, MultipartFile file, String fecha) {

		DocumentosRequerimiento obj = new DocumentosRequerimiento();
		RestTemplate restUpload = new RestTemplate (); 
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
	    
	    MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();  
		
	    bodyMap.add("file", (convert(file)));
	    bodyMap.add("clasificaciondoc", 1);
	    bodyMap.add("idtipodocumento", tipodoc);
	    bodyMap.add("iddocumento", obraid);
	    bodyMap.add("nombredocumento", nombrearchivo);
	    bodyMap.add("usuarioid", appContext.getUsuarioId() );
	    
	
	    System.out.println(bodyMap);
		HttpEntity<MultiValueMap<String, Object>> request
		 = new HttpEntity<>(bodyMap, headers);
		ResponseEntity<String> resultado = 
				restUpload.postForEntity(this.urlUpload, request, String.class);
	    
		String responseString = "";
        String outputString = "";

        ResponseGenericFile resp = new ResponseGenericFile();
		try {
			InputStream input = getInputStream(resultado.toString(), "UTF-8");
	        InputStreamReader isr = null;
	        isr = new InputStreamReader(input);
	        BufferedReader in = new BufferedReader(isr);
	         
	        try {
				while ((responseString = in.readLine()) != null) {
				    outputString = outputString + responseString;
				}
		        String objXML = outputString.substring(5,outputString.indexOf("</ResponseGeneric>")+18 );

				org.json.JSONObject xmlJSONObj = XML.toJSONObject(objXML);
		        System.out.println(xmlJSONObj);
		        xmlJSONObj = (org.json.JSONObject) xmlJSONObj.getJSONObject("ResponseGeneric");
		        Gson g = new Gson(); 
		        resp = g.fromJson(xmlJSONObj.toString(), ResponseGenericFile.class);
		        System.out.println(resp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		    
		obj.setObraid(obraid);
		obj.setUsuario(appContext.getUsuarioId());
		obj.setDocumentoid(resp.getId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaCarga;
		try {
			fechaCarga = format.parse(fecha);
			obj.setFecha(fechaCarga);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repoDocumentos.crear(obj);
		return resp;
		
	}
	
	public static FileSystemResource convert(MultipartFile file){
		
	    File convFile = new File(file.getOriginalFilename());
	    try {
	        convFile.createNewFile();
	          FileOutputStream fos = new FileOutputStream(convFile); 
	            fos.write(file.getBytes());
	            fos.close(); 
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } 

	    return new FileSystemResource(convFile);
	}
	public ResponseGeneric borrar(Integer id) {
		
		ResponseGeneric resp = new ResponseGeneric();
		try {
			repoDocumentos.borrar(id);		
			resp.setStatus(true);
			resp.setMensaje("Elemento Borrado");
		}catch (Exception e) {
			// TODO: handle exception
			resp.setStatus(false);
			resp.setMensaje(e.getMessage());
			
		}
		return resp;
		
		
	}
	public static InputStream getInputStream(String str, String encoding) 
				throws UnsupportedEncodingException {
	      return new ByteArrayInputStream(str.getBytes(encoding));
	}

}
