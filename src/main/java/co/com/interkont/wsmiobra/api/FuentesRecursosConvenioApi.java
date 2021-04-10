package co.com.interkont.wsmiobra.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.dto.Contrato;
import co.com.interkont.wsmiobra.dto.Fuenterecursosconvenio;
import co.com.interkont.wsmiobra.dto.FuenterecursosconvenioLog;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.repository.FuenterecursosconvenioLogRepository;
import co.com.interkont.wsmiobra.repository.FuenterecursosconvenioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Api para gestionar los datos de las fuentes de recursos de convenio",
     consumes="application/json")
@CrossOrigin(origins="*")
public class FuentesRecursosConvenioApi {
	
	@Autowired
	FuenterecursosconvenioRepository fuenterecursosconvenioRepository;
	@Autowired
	FuenterecursosconvenioLogRepository fuenterecursosconvenioLogRepository;
	
	@RequestMapping(value="/fuentesrecursosconvenio", method=RequestMethod.GET)
	@ApiOperation(value = "Obtener lista de fuentes de recursos de un convenio.")
	public ResponseEntity<?> getFuentesRecursosConvenio(Integer idConvenio) {
		ResponseGeneric response = new ResponseGeneric(); 
		Contrato contrato = new Contrato();
		contrato.setIntidcontrato(idConvenio);
		List<Fuenterecursosconvenio> listaFuenterecursosconvenio  = fuenterecursosconvenioRepository.findByContratoAndCdpNot(contrato,"Mayor Permanencia");
		List<Fuenterecursosconvenio> listaFuenterecursosconvenioCdpNull = fuenterecursosconvenioRepository.findByContratoAndCdpIsNull(contrato);
		if (listaFuenterecursosconvenio!=null) {
			if(listaFuenterecursosconvenioCdpNull!=null) {
				listaFuenterecursosconvenio.addAll(listaFuenterecursosconvenioCdpNull);
			}
			return new ResponseEntity<List<Fuenterecursosconvenio>>(listaFuenterecursosconvenio, HttpStatus.OK);			
		}else {
			response.setStatus(false);
			response.setMessage(Constantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/fuentesrecursosconvenio", method=RequestMethod.PUT)
	@ApiOperation(value = "Actualizar datos de una fuente de recursos")
	public ResponseEntity<?> update(@RequestBody Fuenterecursosconvenio objRequest) {
		ResponseGeneric response = new ResponseGeneric();
		if(objRequest.getIdfuenterecursosconvenio() > 0) {
			guardarFuenterecursosconvenioLog(objRequest.getIdfuenterecursosconvenio());
		}
		fuenterecursosconvenioRepository.save(objRequest);
		response.setStatus(true);
		response.setMessage("Guardado con éxito");
		
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);			
	}

	/**
	 * Guarda el log de una fuente de recursos de convenio antes de ser editada o eliminada
	 * @param fuenterecursosconvenio
	 */
	private void guardarFuenterecursosconvenioLog(int idfuenterecursosconvenio) {
		Fuenterecursosconvenio fuenterecursosconvenioOld = fuenterecursosconvenioRepository.getOne(idfuenterecursosconvenio);
		FuenterecursosconvenioLog fuenterecursosconvenioLog = new FuenterecursosconvenioLog();
		fuenterecursosconvenioLog.setLogAt(new Date());
		fuenterecursosconvenioLog.setCdp(fuenterecursosconvenioOld.getCdp());
		fuenterecursosconvenioLog.setContrato(fuenterecursosconvenioOld.getContrato());
		fuenterecursosconvenioLog.setContratoOrigen(fuenterecursosconvenioOld.getContratoOrigen());
		fuenterecursosconvenioLog.setIdfuenterecursosconvenio(fuenterecursosconvenioOld.getIdfuenterecursosconvenio());
		fuenterecursosconvenioLog.setOtrasreservas(fuenterecursosconvenioOld.getOtrasreservas());
		fuenterecursosconvenioLog.setPorcentajegerencia(fuenterecursosconvenioOld.getPorcentajegerencia());
		fuenterecursosconvenioLog.setReservaiva(fuenterecursosconvenioOld.getReservaiva());
		fuenterecursosconvenioLog.setRolentidad(fuenterecursosconvenioOld.getRolentidad());
		fuenterecursosconvenioLog.setRpc(fuenterecursosconvenioOld.getRpc());
		fuenterecursosconvenioLog.setTercero(fuenterecursosconvenioOld.getTercero());
		fuenterecursosconvenioLog.setTipoaporte(fuenterecursosconvenioOld.getTipoaporte());
		fuenterecursosconvenioLog.setValoraportado(fuenterecursosconvenioOld.getValoraportado());
		fuenterecursosconvenioLog.setValorcuotagerencia(fuenterecursosconvenioOld.getValorcuotagerencia());
		fuenterecursosconvenioLog.setValordisponible(fuenterecursosconvenioOld.getValordisponible());
		fuenterecursosconvenioLog.setVigencia(fuenterecursosconvenioOld.getVigencia());
		fuenterecursosconvenioLogRepository.save(fuenterecursosconvenioLog);
		
	}
	
	@RequestMapping(value="/fuentesrecursosconvenio/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Borrar una fuente de recursos")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
	
		ResponseGeneric response = new ResponseGeneric();
		if (id>0) {
			try {
				guardarFuenterecursosconvenioLog(id);
				fuenterecursosconvenioRepository.deleteById(id);
				response.setStatus(true);
				response.setMessage(Constantes.ELIMINADO);
				return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
			} catch (Exception e){
				response.setStatus(false);
				response.setMessage(Constantes.NO_EXISTE);
				return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
			}
			
		}else {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
	}
}

