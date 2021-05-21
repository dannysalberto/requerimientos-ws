package co.com.interkont.wsmiobra.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.interkont.wsmiobra.api.request.ObraModificacionRequest;
import co.com.interkont.wsmiobra.api.request.ObraUpdateRequest;
import co.com.interkont.wsmiobra.api.response.ObraSoloFechasResponse;
import co.com.interkont.wsmiobra.components.OperacionPeriodoServices;
import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodoModificacion;
import co.com.interkont.wsmiobra.models.ActividadobraWS;
import co.com.interkont.wsmiobra.models.ObraChangeDate;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.models.PeriodoMedida;
import co.com.interkont.wsmiobra.models.PeriodoModificacion;
import co.com.interkont.wsmiobra.models.RelacionContratoObra;
import co.com.interkont.wsmiobra.models.RelacionContratoObraTable;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.models.V_ActividadObraModificacion;
import co.com.interkont.wsmiobra.service.ActividadObraModificacionService;
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoModificacionService;
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoService;
import co.com.interkont.wsmiobra.service.ActividadObraWSService;
import co.com.interkont.wsmiobra.service.CategoriaService;
import co.com.interkont.wsmiobra.service.ContratoService;
import co.com.interkont.wsmiobra.service.ObraModificacionService;
import co.com.interkont.wsmiobra.service.ObrasService;
import co.com.interkont.wsmiobra.service.PeriodoMedidaService;
import co.com.interkont.wsmiobra.service.PeriodoModificacionService;
import co.com.interkont.wsmiobra.service.PeriodoService;
import co.com.interkont.wsmiobra.service.RelacionContraObraService;
import co.com.interkont.wsmiobra.service.RelacionContraObraTableService;
import co.com.interkont.wsmiobra.service.V_ActividadObraModificacionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins="*")
@Api(value = "xxxx",
consumes="application/json")
@RequestMapping("/obra")
public class RestObraModificacion {
	
	public double por_totalAUI = 0;	
	public int i=0;
	public boolean itero = false;

	@Autowired
	ObraModificacionService serviceObraModificacion;
	
	@Autowired
	ObrasService serviceObra;
	
	@Autowired
	ActividadObraModificacionService serviceActividadObraModificacion;
	
	@Autowired
	PeriodoModificacionService servicePeriodoModificacion;
	
	@Autowired
	ActividadObraPeriodoModificacionService serviceActividadObraPeriodoModificacion;
	
	@Autowired
	ActividadObraPeriodoService serviceActividadObraPeriodo;
	
	@Autowired
	ActividadObraWSService serviceActividadObraWS;
	
	@Autowired
	PeriodoMedidaService servicePeriodoMedida;
	
	@Autowired
	PeriodoService servicePeriodo; 
	
	@Autowired
	CategoriaService serviceCategoria;
	
	@Autowired
	V_ActividadObraModificacionService serviceVActividadObraModificacion;
	
	@Autowired
	RelacionContraObraService serviceRelacionContratoObra;
	
	@Autowired
	OperacionPeriodoServices serviceOperacionPeriodos;
	
	@Autowired
	RelacionContraObraTableService serviceRelacionContraObraTable;
	
	@Autowired
	ContratoService serviceContrato;
	
	
	public int cantidad=0;

	
	@Transactional
	@PostMapping(value="/iniciarModificacion")
	@ApiOperation(value = "Inicia el proceso de copia de datos para modificación")
	public ResponseEntity<?> IniciarModificacion(@RequestBody ObraModificacionRequest request) {
		
		ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(request.getId(),Constantes.MODIFICACION_INICIADA);

		System.out.println(serviceObra.tieneContratoObra(request.getId()));
			
		if (!serviceObra.tieneContratoObra(request.getId())) {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(false);
			response.setMessage(Constantes.NO_TIENE_CONTRATO_OBRA);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK); 
		}

		//cuando se inicia la modificacion buscmos la fecha masxima de periodo alimentado
		//la cual sera la fecha min de finalizacion y se guarda en la tabla modificacion
		//si no existe alimenyacipn la fecha sera la fecha inicio de la obra
		
		if (obraMod!=null) {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(true);
			response.setMessage(Constantes.MODIFICACION_EXISTENTE_OK);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.ACCEPTED); 
		}
			Obra obra = cambiarEstadoObra(request.getId(),Constantes.ESTADO_OBRA_MODIFICACION);

			ObraModificacion obraModificacion = new ObraModificacion(); 
			obraModificacion.setObraid(request.getId());
			obraModificacion.setFechafin(obra.getDatefecfinobra());
			obraModificacion.setFechainicio(obra.getDatefeciniobra());
			obraModificacion.setPlazo(obra.getIntplazoobra());
			obraModificacion.setPeriodomedida(obra.getIntidperiomedida());
			
			obraModificacion.setNumvaltotobra(obra.getNumvaltotobra());
			obraModificacion.setNewcosto_directo(obra.getCosto_directo());
			obraModificacion.setNewfechafin(obra.getDatefecfinobra());
			obraModificacion.setNewnumvaltotobra(obra.getNumvaltotobra());
			obraModificacion.setNewplazo(obra.getIntplazoobra());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			String fechaMaxAlim = null;
			System.out.println(request.getId());
			try {
				fechaMaxAlim = serviceObra.fechaMaxAlimentacion(request.getId()).toString();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				if (fechaMaxAlim == null) {
					fechaMaxAlim = obra.getDatefeciniobra().toString();
				}
			}
			
			try {
				obraModificacion.setFechaMinimaFin(format.parse(fechaMaxAlim));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				obraModificacion.setFechaModificacion(format.parse(request.getFechaModificacion()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obraModificacion.setJustificacionModificacion(request.getJustificacionModificacion());
			
			serviceObraModificacion.guardar(obraModificacion);
			List<ActividadobraWS> listaActividades = obra.getActividadesobras();
			
			
			try {
					listaActividades.forEach(actividadObra->{
		
						ActividadObraModificacion actividadObraModificacion = new ActividadObraModificacion();
						actividadObraModificacion.setObraModificacion(obraModificacion);
						actividadObraModificacion.setOidactiviobra(actividadObra.getOidactiviobra());
						actividadObraModificacion.setCategoria(serviceCategoria.buscarPorId(actividadObra.getIdcategoria()));						
						actividadObraModificacion.setStrdescactividad(actividadObra.getStrdescactividad());
						actividadObraModificacion.setStrtipounidadmed(actividadObra.getStrtipounidadmed());
						actividadObraModificacion.setValorunitario(actividadObra.getValorunitario());
						actividadObraModificacion.setNumvalorplanifao(actividadObra.getNumvalorplanifao());
						actividadObraModificacion.setFloatcantplanifao(actividadObra.getFloatcantplanifao());
						actividadObraModificacion.setValortotalactividadaiu(actividadObra.getValortotalactividadaiu());
						actividadObraModificacion.setFechainicio(actividadObra.getFechainicio());
						actividadObraModificacion.setFechafin(actividadObra.getFechafin());
						actividadObraModificacion.setIntcodigoobra(obra.getId());
						actividadObraModificacion.setFloatcantidadejecutao(actividadObra.getFloatcantidadejecutao());
						actividadObraModificacion.setNumvalorejecutao( new BigDecimal(0));
						actividadObraModificacion.setNewfechafin(actividadObra.getFechafin());
						actividadObraModificacion.setNewfechainicio(actividadObra.getFechainicio());
						actividadObraModificacion.setNewfloatcantplanifao(actividadObra.getFloatcantplanifao());
						actividadObraModificacion.setNewnumvalorplanifao(actividadObra.getNumvalorplanifao());
						actividadObraModificacion.setNewvalortotalactividadaiu(actividadObra.getValortotalactividadaiu());
						actividadObraModificacion.setNewvalorunitario(actividadObra.getValorunitario());
						
						serviceActividadObraModificacion.guardar(actividadObraModificacion);
					});
			}catch (Exception e) {
				 throw new RuntimeException(e.getMessage());
			}
						
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(true);
			response.setMessage(Constantes.MODIFICACION_INICIADA_OK);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);		
	}

	
	@Transactional
	@PutMapping(value="/actualizarObraModificacion")
	@ApiOperation(value = "Actualiza los datos de la obra")
	public ResponseEntity<?> actualizarObraModificacion(@RequestBody ObraUpdateRequest request){
		
		ObraModificacion obraMod = serviceObraModificacion.buscarPorId(request.getIdModificacion());
		if (obraMod!=null) {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				obraMod.setNewfechafin(format.parse(request.getFechafin()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obraMod.setNewplazo(request.getPlazo());	
			obraMod = serviceObraModificacion.actualizar(obraMod);
			obraMod = this.reajustarCalculosModificacion(obraMod.getObraid());
			return new ResponseEntity<ObraModificacion>(obraMod, HttpStatus.OK);			
		}else {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.BAD_REQUEST);		
		}
		
	}
	
	@Transactional
	@PutMapping(value="/actualizarModificacion")
	@ApiOperation(value = "Actualiza los datos de la modificación sin afectar cálculos")
	public ResponseEntity<?> actualizarModificacion(@RequestBody ObraModificacionRequest request){
		
		ObraModificacion obraMod = serviceObraModificacion.buscarPorId(request.getId());
		if (obraMod!=null) {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				obraMod.setFechaModificacion(format.parse(request.getFechaModificacion()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obraMod.setJustificacionModificacion(request.getJustificacionModificacion());
			obraMod = serviceObraModificacion.actualizar(obraMod);
			return new ResponseEntity<ObraModificacion>(obraMod, HttpStatus.OK);			
		}else {
			ResponseGeneric response = new ResponseGeneric(); 
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.BAD_REQUEST);		
		}
		
	}
	
	public ObraModificacion reajustarCalculosModificacion(@PathVariable("idObra") Integer idObra){
			
			ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(
					idObra,Constantes.MODIFICACION_INICIADA);
			//System.out.println(idObra);
			this.calcularCostos(obraMod);
			obraMod.setCantidadActividades(serviceObraModificacion.cantidadActividades(obraMod.getId()));
			
			serviceObraModificacion.totalPrecioActividades(obraMod.getId());
			obraMod.setNewnumvaltotobra(new BigDecimal(serviceObraModificacion.totalPrecioActividades(obraMod.getId())));
			obraMod.setNewcosto_directo(new BigDecimal(serviceObraModificacion.totalCostoDirecto(obraMod.getId())));
			serviceObraModificacion.actualizar(obraMod);
			
			return obraMod;		
	}
		
	
	@GetMapping(value="/obtenerObraModificacion/{idobra}")
	@ApiOperation(value = "Obtiene los datos de modificación de una obra por su id")
	public ResponseEntity<?> obtenerObraModificacion(@PathVariable("idobra") Integer idobra){
			      
		ObraModificacion obraModificacion = new ObraModificacion();
		obraModificacion = serviceObraModificacion
				.buscarPorIdEstado(idobra,Constantes.MODIFICACION_INICIADA);
		ResponseGeneric response = new ResponseGeneric(); 
		List<RelacionContratoObra> lstRelacionContObra = new ArrayList<>();

		if (obraModificacion==null) {
			Obra obra = serviceObra.buscarPorId(idobra);
			if (obra!=null) {
				ObraSoloFechasResponse Obra = new ObraSoloFechasResponse();
				Obra.setId(obra.getId());
				Obra.setFechaFin(obra.getDatefecfinobra());
				Obra.setFechaInicio(obra.getDatefeciniobra());
				Object ret = Obra;			
				response.setObj(ret);
			}
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}
		List<RelacionContratoObra> lstRelacionContratoObra = 
				serviceRelacionContratoObra.desplegarPorObra(obraModificacion.getObraid());

		lstRelacionContratoObra.forEach(relacionContratoObra->{
			lstRelacionContObra.add(relacionContratoObra);
		});
		obraModificacion.setRelacioncontratos(lstRelacionContObra);
		return new ResponseEntity<>(obraModificacion, HttpStatus.OK);		
	}
		
	//@Transactional
	@PostMapping(value="/finalizarModificacion/{idModificacion}")
	@ApiOperation(value = "Finaliza la modificación, actualizando los datos de la obra")
	public ResponseEntity<?> realizarModificacion(@PathVariable("idModificacion") Integer idModificacion){
		
		ObraModificacion obraModificacion = new ObraModificacion();
		obraModificacion = serviceObraModificacion.buscarPorId(idModificacion);
	
		if (obraModificacion==null || obraModificacion.getEstadoModificacion().equals(Constantes.MODIFICACION_FINALIZADA)){
			ResponseGeneric responseNotFound = new ResponseGeneric(); 
			responseNotFound.setStatus(false);
			responseNotFound.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<ResponseGeneric>(responseNotFound, HttpStatus.NOT_FOUND);	
		}
		Obra obra = serviceObra.buscarPorId(obraModificacion.getObraid());

		obraModificacion.getActividadesObra().forEach((actividadObraModificacion)->{
			System.out.println("Entro al ciclo");
			ActividadobraWS actividadobra = new ActividadobraWS();
			
			if (actividadObraModificacion.getTipoModificacion().equals( Constantes.ACTIVIDAD_ELIMINADA)) {
					serviceActividadObraWS.eliminar(actividadObraModificacion.getOidactiviobra());
			}else if (actividadObraModificacion.getTipoModificacion().equals(Constantes.ACTIVIDAD_MODIFICADA)) {
				 actividadobra = serviceActividadObraWS.buscarPorId(actividadObraModificacion.getOidactiviobra());
				    if (actividadobra != null) {
						actividadobra.setFloatcantplanifao(actividadObraModificacion.getNewfloatcantplanifao());
						actividadobra.setFechafin(actividadObraModificacion.getNewfechafin());
						actividadobra.setFechainicio(actividadObraModificacion.getNewfechainicio());
						actividadobra.setValorunitario(actividadObraModificacion.getNewvalorunitario());
						
						actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
						actividadobra.setValortotalactividadaiu(actividadObraModificacion.getNewvalortotalactividadaiu());
						actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
						
				    	System.out.println(actividadobra);
					    serviceActividadObraWS.actualizar(actividadobra);	
				    }
			}else if (actividadObraModificacion.getTipoModificacion().equals(Constantes.ACTIVIDAD_AGREGADA)){
					actividadobra = assignData(actividadObraModificacion);
				  	actividadobra.setObra(obra);
				  	actividadobra.setOidactiviobra(null); //ID PRIMARY
				    serviceActividadObraWS.Guardar(actividadobra);
			}else {
			    	System.out.println("Default");
					System.out.println(actividadObraModificacion);
			}
		});
		this.reajustarCalculosModificacion(obraModificacion.getObraid());
		obra.setIntplazoobra(obraModificacion.getNewplazo());
		obra.setDatefecfinobra(obraModificacion.getNewfechafin());
		obra.setCosto_directo(obraModificacion.getNewcosto_directo());
		obra.setNumvaltotobra(obraModificacion.getNewnumvaltotobra());
		obra.setDateusuModifica(new Date());
		serviceObra.actualizar(obra);
		
		serviceOperacionPeriodos.planeacionPorPeriodo(obra.getId());
		obraModificacion.setEstadoModificacion(Constantes.MODIFICACION_FINALIZADA);
		serviceObraModificacion.actualizar(obraModificacion);
		
		obra.setIntestadoobra(Constantes.ESTADO_OBRA_EJECUCION);
		serviceObra.actualizar(obra);

		//vamos a actualizar los datos de contratoObra
		RelacionContratoObra relaConObra = obraModificacion.getRelacioncontratos().get(0);
		RelacionContratoObraTable relaConObrTab = 
				serviceRelacionContraObraTable.buscarPorObraContrato(obra.getId(), 
					relaConObra.getContrato().getId());
		relaConObrTab.setNumvalorrelacion(obra.getNumvaltotobra());
		serviceRelacionContraObraTable.guardar(relaConObrTab);
		
		relaConObra.getContrato().setNumvlrsumaproyectos(
					relaConObra.getContrato().getNumvlrsumaproyectos()
					.add(obraModificacion.getNewnumvaltotobra()
							.subtract(obraModificacion.getNumvaltotobra())));
		serviceContrato.guardar(relaConObra.getContrato());
		//fin actualización		
		
		ResponseGeneric response = new ResponseGeneric();
		response.setStatus(true);
		response.setMessage(Constantes.MODIFICACION_DE_OBRA_FINALIZADA);
		return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);		
	}
	
	@Transactional
	@DeleteMapping(value="/cancelarmodificacion/{idModificacion}")
	@ApiOperation(value="Borra los datos de una modificación")
	public ResponseEntity<ResponseGeneric> cancelarModificacion(@PathVariable("idModificacion") Integer idModificacion){
	
		try {
			ObraModificacion obraMod = serviceObraModificacion.buscarPorId(idModificacion);
			this.cambiarEstadoObra(obraMod.getObraid(), Constantes.ESTADO_OBRA_EJECUCION);
			serviceObraModificacion.Eliminar(idModificacion);			
		}catch (Exception e) {
			ResponseGeneric response = new ResponseGeneric();
			response.setStatus(false);
			response.setMessage(Constantes.MODIFICACION_INICIADA_NOTFOUND);
			return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
		}
		
		ResponseGeneric response = new ResponseGeneric();
		response.setStatus(false);
		response.setMessage(Constantes.MODIFICACION_DE_OBRA_CANCELADA);
		return new ResponseEntity<> (response, HttpStatus.ACCEPTED);
		
	}


	private ActividadobraWS assignData(ActividadObraModificacion actividadObraModificacion) {
			
			ActividadobraWS actividadobra = new ActividadobraWS();
			actividadobra.setOidactiviobra(actividadObraModificacion.getOidactiviobra());
			actividadobra.setStrdescactividad(actividadObraModificacion.getStrdescactividad());
			actividadobra.setIdcategoria(actividadObraModificacion.getCategoria().getId());
			actividadobra.setStrtipounidadmed(actividadObraModificacion.getStrtipounidadmed());
			
			actividadobra.setFloatcantplanifao(actividadObraModificacion.getNewfloatcantplanifao());
			actividadobra.setFechafin(actividadObraModificacion.getNewfechafin());
			actividadobra.setFechainicio(actividadObraModificacion.getNewfechainicio());
			actividadobra.setValorunitario(actividadObraModificacion.getNewvalorunitario());
			
			actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
			actividadobra.setBoolaiu(actividadObraModificacion.isBoolaiu());
			actividadobra.setValortotalactividadaiu(actividadObraModificacion.getNewvalortotalactividadaiu());
			actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
			return actividadobra;
	}
	
	private ActividadObraModificacion assignData(V_ActividadObraModificacion actividadObraModificacion) {
		
		ActividadObraModificacion actividadobra = new ActividadObraModificacion();
		actividadobra.setOidactiviobra(actividadObraModificacion.getOidactiviobra());
		actividadobra.setStrdescactividad(actividadObraModificacion.getStrdescactividad());
		actividadobra.setCategoria(actividadObraModificacion.getCategoria());
		actividadobra.setStrtipounidadmed(actividadObraModificacion.getStrtipounidadmed());
		//actividadobra.setFloatcantidadejecutao(actividadObraModificacion.getn);
		actividadobra.setFloatcantplanifao(actividadObraModificacion.getNewfloatcantplanifao());
		actividadobra.setFechafin(actividadObraModificacion.getNewfechafin());
		actividadobra.setFechainicio(actividadObraModificacion.getNewfechainicio());
		actividadobra.setValorunitario(actividadObraModificacion.getNewvalortotalactividadaiu());
		actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
		actividadobra.setBoolaiu(actividadObraModificacion.isBoolaiu());
		actividadobra.setValortotalactividadaiu(actividadObraModificacion.getNewvalortotalactividadaiu());
		actividadobra.setNumvalorplanifao(actividadObraModificacion.getNewnumvalorplanifao());
		return actividadobra;
}
	
	private Obra cambiarEstadoObra(Integer idObra,Integer estado) {
		
	    Logger logger = LoggerFactory.getLogger(RestObraModificacion.class);
	    Obra obra = null;
		try {
			obra = serviceObra.buscarPorId(idObra);
			obra.setIntestadoobra(estado);
			return serviceObra.actualizar(obra);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return obra;
	}
	
	public void calcularCostos(ObraModificacion obra) {
		Obra obraOrigen = serviceObra.buscarPorId(obra.getObraid());
		
		List<ActividadObraModificacion> lst = serviceActividadObraModificacion.desplegarTodos(obra);
		if (obraOrigen.isBoolincluyeaiu()) {
			lst.forEach((act)->{
				if (act.getTipoModificacion()!=Constantes.ACTIVIDAD_ELIMINADA) {
					act.setNewnumvalorplanifao(act.getNewvalorunitario());
					act.setNewvalortotalactividadaiu(
							act.getNewfloatcantplanifao().multiply(act.getNewnumvalorplanifao()));
					serviceActividadObraModificacion.actualizar(act);
				}				
			});
		}else {
			lst.forEach((act)->{
				if (act.getTipoModificacion()!=Constantes.ACTIVIDAD_ELIMINADA) {
					this.por_totalAUI = ((
							obraOrigen.getFloatporadmon() 
							+ obraOrigen.getFloatporimprevi() 
							+ obraOrigen.getFloatporutilidad() + 
							+ obraOrigen.getFloatporotros() +
							((obraOrigen.getFloatporutilidad()*obraOrigen.getFloatporivasobreutil())/100)));
					BigDecimal porAIU = truncateDecimal(por_totalAUI,2);
					act.setNewnumvalorplanifao(act.getNewvalorunitario().multiply(
							new BigDecimal(1).add(porAIU.divide(new BigDecimal(100)))));
					act.setNewnumvalorplanifao(truncateDecimal(act.getNewnumvalorplanifao().doubleValue(),2));
					act.setNewvalortotalactividadaiu(
							act.getNewfloatcantplanifao().multiply(act.getNewnumvalorplanifao()));
					
					serviceActividadObraModificacion.actualizar(act);
				}
			});

		}
			
	}

	private static BigDecimal truncateDecimal(double x,int numberofDecimals)
	{
	       if ( x > 0) {
	           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, 
	        		   BigDecimal.ROUND_HALF_DOWN);
	       } else {
	           return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	       }
	}
	
	/*public boolean generarPeriodos(ObraModificacion obraMod, Obra obra) {
		System.out.println("Generando Periodos");					
		
		if (obra.getIntidperiomedida()>0) {
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());
			BigDecimal DiasObra = new BigDecimal(1 + ((obraMod.getNewfechafin().getTime()
					-obra.getDatefeciniobra().getTime()) / 1000/3600/24));
			//System.out.println("Dias obra: "+DiasObra.doubleValue());

			BigDecimal ciclos = DiasObra.divide(new BigDecimal(Math.max(periodoMedida.getDiasPeriodo(),1)),6,RoundingMode.HALF_EVEN) ;
			//System.out.println("Generando Periodos, ciclos: "+ciclos);					

			int iter  = ciclos.intValue();
			
			int diasRestantes = 0;
			//System.out.println("Generando Periodos, iteraciones: "+iter);		
			//eliminar todos menos los que tengan alimentación
			List<PeriodoModificacion> lstPeriodoModDelete = servicePeriodoModificacion.ListarPorObra(obraMod.getId());
			lstPeriodoModDelete.forEach(periodoDele->{
				serviceActividadObraPeriodoModificacion.eliminarPorIdPeriodo(periodoDele.getId());
				System.out.println("borrando");
			});
			
			if (servicePeriodoModificacion.eliminarAll(lstPeriodoModDelete)) {
		
				Date fechaInicio = obra.getDatefeciniobra();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(fechaInicio);
				BigDecimal montoRestante = obra.getNumvaltotobra();
				montoRestante.setScale(3, RoundingMode.HALF_EVEN);
				if (iter>0) {
					diasRestantes = DiasObra.intValue();				
				}
				itero=false;
				for (i=1;i<=iter;i++) {
					
					PeriodoModificacion periodo = new PeriodoModificacion();
					if (i==1) {
						periodo.setFechainicio(calendar.getTime());
						//System.out.println("Caso 1");					
					}else {
						//System.out.println("Caso 2");
						calendar.add(calendar.DAY_OF_YEAR,1);
						
						periodo.setFechainicio(calendar.getTime());
					}
					calendar.add(calendar.DAY_OF_YEAR,periodoMedida.getDiasPeriodo()-1);
					periodo.setFechafin(calendar.getTime());
					periodo.setValtotplanif(new BigDecimal(0));
					//ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA);
					periodo.setObraModificacion(obraMod);
					diasRestantes = diasRestantes - (periodoMedida.getDiasPeriodo());
					periodo.GenerarId(obra, i);
					//System.out.println(periodo);
					periodo.setObraOrigenId(obra.getId());
					//periodo.setPeriodo_id(periodo_id);

					servicePeriodoModificacion.guardar(periodo);
					itero = true;
				}
				if (itero == false) {
					//System.out.println("Sin interaciones por periodo");	
					PeriodoModificacion periodo = new PeriodoModificacion();
					periodo.setFechainicio(obra.getDatefeciniobra());
					periodo.setFechafin(obraMod.getNewfechafin());
					periodo.setValtotplanif(new BigDecimal(0));
					//ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA);
					periodo.setObraModificacion(obraMod);
					periodo.setObraOrigenId(obra.getId());

					periodo.GenerarId(obra, 1);
					servicePeriodoModificacion.guardar(periodo);
				}else if (diasRestantes>0){
					//System.out.println("Con interaciones por periodo");
					PeriodoModificacion periodo = new PeriodoModificacion();
					calendar.add(calendar.DAY_OF_YEAR,1);
					periodo.setFechainicio(calendar.getTime());
					periodo.setFechafin(obraMod.getNewfechafin());
					periodo.setValtotplanif(new BigDecimal(0));

					//ObraModificacion obraMod = serviceObraModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA);
					periodo.setObraModificacion(obraMod);
					periodo.setObraOrigenId(obra.getId());
					periodo.GenerarId(obra, i);
					servicePeriodoModificacion.guardar(periodo);
				}

			}
			
		}
		return true;
	}
	
	public ResponseEntity<?> planeacionPorPeriodo(@PathVariable(value="idObra") Integer idObra) {	
		ObraModificacion obra = serviceObraModificacion.buscarPorId(idObra);
		Obra obraOrigen = serviceObra.buscarPorId(obra.getObraid());
		
		List<ActividadObraPeriodoModificacion> lstactPeriodo = serviceActividadObraPeriodoModificacion.listarPorModificacion(obra.getId());
		serviceActividadObraPeriodoModificacion.eliminarAll(lstactPeriodo);
		
		if (this.generarPeriodos(obra, obraOrigen)){
			List<ActividadObraModificacion> lstactividadObra = serviceActividadObraModificacion.desplegarTodos(obra); 
			lstactividadObra.forEach((actObra)-> {
				List<PeriodoModificacion> lstPeriodos = servicePeriodoModificacion
						.ListarPorObraFecha(obra.getId(), actObra.getNewfechainicio(), actObra.getNewfechafin());
				long DiasPeriodo = 0;
				double porcionDiasPeriodo = 0;
				
				BigDecimal acumCantidad = new BigDecimal(0);
				BigDecimal acumvalPlanif = new BigDecimal (0); 
				BigDecimal diarioActividad = new BigDecimal(0);
				double diasTotalActividad = 
						1+ ((actObra.getNewfechafin().getTime() - actObra.getNewfechainicio().getTime())
						/ 1000 / 3600 / 24);
				diarioActividad = actObra.getNewvalortotalactividadaiu().divide(new BigDecimal(diasTotalActividad),6,RoundingMode.HALF_EVEN);
				System.out.println(lstPeriodos.size());
				for (int i = 0; i < lstPeriodos.size(); i++) {
					System.out.println(lstPeriodos.get(i));

					if (lstPeriodos.get(i).getFechainicio().after(actObra.getFechafin())) {
						break;
					}
					
					
					if (i==0) {
						//PRIMER PERIODO
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
								-actObra.getNewfechainicio().getTime()) / 1000/3600/24);
						if (DiasPeriodo > diasTotalActividad) {
							DiasPeriodo = 1 + ((actObra.getNewfechafin().getTime()
									-actObra.getNewfechainicio().getTime()) / 1000/3600/24);
						}
					}else if (i==(lstPeriodos.size()-1)){
						//ULTIMO PERIODO
						DiasPeriodo = 1 + ((actObra.getNewfechafin().getTime()
								-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
					}else {
						//PERIODOS INTERMEDIOS
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
								-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
					}
					
					porcionDiasPeriodo = this.truncateDecimal((DiasPeriodo * actObra.getNewfloatcantplanifao().doubleValue())/diasTotalActividad, 6).doubleValue();
					
					ActividadObraPeriodoModificacion actividadObraPeriodoMod = new ActividadObraPeriodoModificacion(); 
					
					//OJO CON ESTE ATRIBNUTO, SUPRIMIR Y VALIDAR
					//actividadObraPeriodoMod.setOidactiviobra(actObra.getId());
					actividadObraPeriodoMod.setPeriodoModificacion(lstPeriodos.get(i));
					
					if (i==(lstPeriodos.size()-1)){
						//ULTIMO PERIODO
						actividadObraPeriodoMod.setFloatcantplanif(actObra.getNewfloatcantplanifao().subtract(acumCantidad));
						actividadObraPeriodoMod.setNumvalplanif(actObra.getNewvalortotalactividadaiu().subtract(acumvalPlanif));
					}else {
						//PERIODOS INTERMEDIOS
						//System.out.println(porcionDiasPeriodo);
						actividadObraPeriodoMod.setFloatcantplanif(new BigDecimal(porcionDiasPeriodo));
						
						actividadObraPeriodoMod.setNumvalplanif(diarioActividad.multiply(new BigDecimal(DiasPeriodo)));
						acumCantidad = acumCantidad.add(actividadObraPeriodoMod.getFloatcantplanif());
						acumvalPlanif = acumvalPlanif.add(actividadObraPeriodoMod.getNumvalplanif());				
					}

					actividadObraPeriodoMod.setIntidperiodo(lstPeriodos.get(i).getPeriodo_id());

					if (actividadObraPeriodoMod.getFloatcantplanif().doubleValue()>0) {
						serviceActividadObraPeriodoModificacion.guardar(actividadObraPeriodoMod);
						
						lstPeriodos.get(i).setValtotplanif(
								lstPeriodos.get(i).getValtotplanif()
								.add(actividadObraPeriodoMod.getNumvalplanif()));
						servicePeriodoModificacion.guardar(lstPeriodos.get(i));
						
					}
			}	
			});
		}

		
		
		
		/**FIN PROCESO DE PLANIFICACION DE ACTIVIDADES POR CADA PERIODO**/
	/*	ResponseGeneric rspconfirmacion = new ResponseGeneric();
		rspconfirmacion.setStatus(true);
		rspconfirmacion.setMessage(Constantes.PLANIFICACION_COMPLETADA);
		return new ResponseEntity<ResponseGeneric>(rspconfirmacion, HttpStatus.OK);
		
	}*/
	
	public ResponseGeneric valiChangeDate(ObraChangeDate objeto) {		
		ResponseGeneric response = new ResponseGeneric();
		if (objeto.getId()==null || objeto.getFechaFinObra()==null|| objeto.getFechaFinObra()=="" || objeto.getFechaInicioObra()==null|| objeto.getFechaInicioObra()=="") {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return response;
		}
		return null;			
	}
	
	@PutMapping(value="/ajustarfechamodificacion")
	public ResponseEntity<?> ajustarfecha(@RequestBody(required=true) ObraChangeDate request) {
		ResponseGeneric response = this.valiChangeDate(request) ; 
		if (response!=null){
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.OK);
		}
		ObraModificacion obra = serviceObraModificacion.buscarPorId(request.getId());
		if (obra==null) {
			ResponseGeneric response1 = new ResponseGeneric(); 
			response1.setStatus(false);
			response1.setMessage(Constantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response1, HttpStatus.OK);
		}
		
		ResponseGeneric rspconfirmacion = new ResponseGeneric();
		if(obra.getFechainicio()!=null &&obra.getFechafin()!=null )
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String FechainicioObra=null;
			String FechaFinalObra=null;
			FechainicioObra = format.format(obra.getFechainicio());	
			FechaFinalObra = format.format(obra.getFechainicio());
			Boolean ResponseFechaInicioObra=FechainicioObra.equals(request.getFechaInicioObra());		
			Boolean ResponseFechaFinObra=FechaFinalObra.equals(request.getFechaFinObra());
			cantidad = 0;
			if(ResponseFechaInicioObra==false){
				List<ActividadObraModificacion> lstactividad=obra.getActividadesObra();			
				lstactividad.forEach((result)->
				{
					Date fechainicio = null;
					try {
						fechainicio = format.parse(request.getFechaInicioObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					if(obra.getFechainicio()!=fechainicio)
					{				
						result.setFechainicio(fechainicio);		
						cantidad++;					
					}		
					serviceActividadObraModificacion.guardar(result);
				});		
			}
			if(ResponseFechaFinObra==false){
				List<ActividadObraModificacion> lstactividad=obra.getActividadesObra();
				lstactividad.forEach((result)->
				{
					Date fechafin = null;
					try {
						fechafin = format.parse(request.getFechaFinObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); 
					}			
					if(obra.getFechafin()!=fechafin)
					{
						result.setFechafin(fechafin);
						cantidad++;	
					}			
					serviceActividadObraModificacion.guardar(result);
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

}

