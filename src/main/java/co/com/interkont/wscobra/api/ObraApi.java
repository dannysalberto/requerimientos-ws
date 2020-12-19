package co.com.interkont.wscobra.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import co.com.interkont.wscobra.models.*;

import co.com.interkont.wscobra.api.response.ObraResponse;
import co.com.interkont.wscobra.auth.config.ConfiguracionConstantes;
import co.com.interkont.wscobra.config.Constantes;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.service.ActividadObraWSService;
import co.com.interkont.wscobra.service.ObrasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar los datos de la obra",
     consumes="application/json")
@CrossOrigin(origins="*")
public class ObraApi {


	public int cantidad=0;
	public double cantPor=0; //cantidad porcentual
	
	@Autowired
	ObrasService serviceObra;
	
	@Autowired
	ActividadObraWSService serviceactividadWS;
	
	@RequestMapping(value="/obra/{idObra}", method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una obra especifica por ID.")
	public ResponseEntity<?> getObra(Integer id) {
		ResponseGeneric response = new ResponseGeneric(); 
		
		Obra obra = serviceObra.buscarPorId(id);
		
		if (obra!=null) {
			ObraResponse obranew = new ObraResponse();
			obranew.setId(obra.getIntcodigoobra());
			obranew.setFechaInicioObra(obra.getDatefeciniobra());
			obranew.setFechaFinObra(obra.getDatefecfinobra());
			obranew.setPlazoObra(obra.getIntplazoobra());
			obranew.setPeriodoMedida(obra.getIntidperiomedida());
			
			obranew.setCosto_directo(obra.getCosto_directo());
			//obranew.setCosto_directo_aiu(obra.getCosto_directo_aiu());
			//obranew.setCosto_total(obra.getCosto_total());
			
			obranew.setPorAdmon(obra.getFloatporadmon());
			obranew.setPorImprevi(obra.getFloatporimprevi());
			obranew.setPorIvaSobreUtil(obra.getFloatporivasobreutil());
			obranew.setPorOtros(obra.getFloatporotros());
			obranew.setPorUtilidad(obra.getFloatporutilidad());
			
			return new ResponseEntity<ObraResponse>(obranew, HttpStatus.OK);			
		}else {
			response.setStatus(false);
			response.setMessage(ConfiguracionConstantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ResponseGeneric valiChangeDate(ObraChangeDate objeto) {		
		ResponseGeneric response = new ResponseGeneric();
		if (objeto.getId()==null || objeto.getFechaFinObra()==null|| objeto.getFechaFinObra()=="" || objeto.getFechaInicioObra()==null|| objeto.getFechaInicioObra()=="") {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return response;
		}
		return null;			
	}
	
	@PutMapping(value="/ajustarfecha")
	public ResponseEntity<?> ajustarfecha(@RequestBody(required=true) ObraChangeDate request) {
		ResponseGeneric response = this.valiChangeDate(request) ; 
		if (response!=null){
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		Obra obra = serviceObra.buscarPorId(request.getId());
		if (obra==null) {
			ResponseGeneric response1 = new ResponseGeneric(); 
			response1.setStatus(false);
			response1.setMessage(Constantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response1, HttpStatus.OK);
		}
		
		ResponseGeneric rspconfirmacion = new ResponseGeneric();
		if(obra.getDatefeciniobra()!=null &&obra.getDatefecfinobra()!=null )
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String FechainicioObra=null;
			String FechaFinalObra=null;
			FechainicioObra = format.format(obra.getDatefeciniobra());	
			FechaFinalObra = format.format(obra.getDatefecfinobra());
			Boolean ResponseFechaInicioObra=FechainicioObra.equals(request.getFechaInicioObra());		
			Boolean ResponseFechaFinObra=FechaFinalObra.equals(request.getFechaFinObra());
			cantidad = 0;
			if(ResponseFechaInicioObra==false){
				List<ActividadobraWS> lstactividad=obra.getActividadesobras();			
				lstactividad.forEach((result)->
				{
					Date fechainicio = null;
					try {
						fechainicio = format.parse(request.getFechaInicioObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					if(obra.getDatefeciniobra()!=fechainicio)
					{				
						result.setFechainicio(fechainicio);		
						cantidad++;					
					}		
					serviceactividadWS.Guardar(result);
				});		
			}
			if(ResponseFechaFinObra==false){
				List<ActividadobraWS> lstactividad=obra.getActividadesobras();
				lstactividad.forEach((result)->
				{
					Date fechafin = null;
					try {
						fechafin = format.parse(request.getFechaFinObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); 
					}			
					if(obra.getDatefecfinobra()!=fechafin)
					{
						result.setFechafin(fechafin);
						cantidad++;	
					}			
					serviceactividadWS.Guardar(result);
				});				
			}if(cantidad==0){
				rspconfirmacion.setStatus(false);
				rspconfirmacion.setMessage(Constantes.NO_EXISTE_ACTIVIDAD );	
			}else{
				rspconfirmacion.setStatus(true);
				rspconfirmacion.setMessage(Constantes.ACTIVIDAD_ACTUALIZADA+cantidad+" registros" );
			}
			return new ResponseEntity<ResponseGeneric>(rspconfirmacion, HttpStatus.OK);			
		}
		else
		{
			rspconfirmacion.setStatus(false);
			rspconfirmacion.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return new ResponseEntity<ResponseGeneric>(rspconfirmacion, HttpStatus.OK);	
		}		
					
	}
	
	public ResponseGeneric valid(ObraUpdRequest objeto) {
		
		ResponseGeneric response = new ResponseGeneric();
		if (objeto.getFechaFin()==null  
				|| objeto.getFechaInicio()==null
				|| objeto.getPlazoObra()==null) {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return response;	
			
		}
		return null;			
	}	
	
	@PutMapping(value="/update")
	public ResponseEntity<?> actualizarObra(@RequestBody(required=true) ObraUpdRequest request) {
		ResponseGeneric response = new ResponseGeneric(); //this.valid(request); 
		/*if (response!=null){
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}*/

		Obra obra = new Obra();
		System.out.println(request.getId());

		if (request.getId()!=null) {
			System.out.println("prueba");
			obra = serviceObra.buscarPorId(request.getId());
			if (obra==null) {
				response.setStatus(false);
				response.setMessage(Constantes.NO_EXISTE);
				return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(request.getFechaFin());
		
		obra.setIntplazoobra(request.getPlazoObra());
		try {
			obra.setDatefeciniobra(format.parse(request.getFechaInicio()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obra.setDatefecfinobra(format.parse(request.getFechaFin()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obra.setIntidperiomedida(request.getPeriodoMedida());
		obra.setIntplazoobra(request.getPlazoObra());
		obra.setBoolincluyeaiu(request.isIncluyeAiu());
		obra.setFloatporadmon(request.getPorAdmon());
		obra.setFloatporimprevi(request.getPorImprevi());
		obra.setFloatporutilidad(request.getPorUtilidad());
		obra.setFloatporutilidad(request.getPorUtilidad());
		obra.setFloatporotros(request.getPorOtros());
		  
		
		//obra = this.calcularCostos(obra);
		serviceObra.actualizar(obra);
		
		//obra.setActividades(servicesObra.cantidadActividades(obra.getId()));
		response.setStatus(true);
		response.setMessage("Actualizado");
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		//return new ResponseEntity<Obra>(obra, HttpStatus.OK);
		
	}

}
