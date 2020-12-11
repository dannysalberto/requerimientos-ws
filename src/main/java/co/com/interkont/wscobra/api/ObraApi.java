package co.com.interkont.wscobra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wscobra.api.response.ObraResponse;
import co.com.interkont.wscobra.auth.config.ConfiguracionConstantes;
import co.com.interkont.wscobra.dto.Obra;
import co.com.interkont.wscobra.models.ResponseGeneric;
import co.com.interkont.wscobra.service.ObrasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar los datos de la obra",
     consumes="application/json")
@CrossOrigin(origins="*")
public class ObraApi {

	@Autowired
	ObrasService serviceObra;
	
	
	@RequestMapping(value="/obra/{idObra}", method=RequestMethod.GET)
	@ApiOperation(value = "Obtener una obra especifica por ID.")
	public ResponseEntity<?> getObra(Integer id) {
		ResponseGeneric response = new ResponseGeneric(); 
		
		Obra obra = serviceObra.findById(id);
		
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

}
