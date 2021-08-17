package co.com.interkont.wsmiobra.businnes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.interfaces.ICalculosPeriodo;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;
import co.com.interkont.wsmiobra.models.ActividadobraWS;
import co.com.interkont.wsmiobra.models.ObraChangeDate;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.models.PeriodoMedida;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.models.SuspensionObra;
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoService;
import co.com.interkont.wsmiobra.service.ActividadObraWSService;
import co.com.interkont.wsmiobra.service.ObraModificacionService;
import co.com.interkont.wsmiobra.service.ObrasService;
import co.com.interkont.wsmiobra.service.PeriodoMedidaService;
import co.com.interkont.wsmiobra.service.PeriodoService;
import co.com.interkont.wsmiobra.service.SuspensionObraService;
import co.com.interkont.wsmiobra.utils.Utils;

@Component
public class BusinnesPeriodoServices implements ICalculosPeriodo{
	
	@Autowired
	PeriodoService servicePeriodo; 
	
	@Autowired
	PeriodoMedidaService servicePeriodoMedida;
	
	@Autowired
	ActividadObraPeriodoService serviceActividadObraPeriodo;

	@Autowired
	ObrasService serviceObra;
	
	@Autowired
	ActividadObraWSService serviceactividadWS;
	
	@Autowired
	SuspensionObraService serviceSuspensionObra;
	
	@Autowired
	ObraModificacionService serviceModificacion;
	
	public int i,j=0;
	public boolean itero = false;
	public int cantidad=0;
	public double diasTotalActividad;
	public boolean bRemanente;

	double diasSusp = 0;
	double diasPeriodo = 0;
	double acumControl = 0;
	double diasActividad = 0;
	int idLastPeriod = 0;


	Date fechaFinUltimaSusp;
	Date fechaFinUltimoPeriodo;
	Date fechainicio;
	long ultimoIdPeriodo = 0;
	boolean bReinicializarRangos;
	
	@Override
	public boolean generarPeriodos(Obra obra) {
		System.out.println("REGENERANDO PERIODOS "+obra.getIntestadoobra()+" vacio "+obra.getSuspensionesobra().isEmpty());
		if ((obra.getIntestadoobra() == Constantes.ESTADO_OBRA_MODIFICACION && obra.getSuspensionesobra().isEmpty() ) ||
				obra.getIntestadoobra()==Constantes.ESTADO_OBRA_POR_INICIAR ) {
			System.out.println("Actualizando Periodo");
			this.regenerarPeriodos(obra);
			return true;
		}
		if (obra.getIntestadoobra() == Constantes.ESTADO_OBRA_MODIFICACION && !obra.getSuspensionesobra().isEmpty() ) {
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());
			//Obra obraSusp = this.regenerarPeriodos(obra);
			System.out.println("Generando periodos con suspensiones");
			validateSuspension(obra,periodoMedida,serviceModificacion.buscarPorIdEstado(obra.getId(), Constantes.MODIFICACION_INICIADA));	
			return true;
		}
		return false;
	}
	
	private Obra regenerarPeriodos(Obra obra) {

		if (obra.getIntidperiomedida()>0) {
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());
			BigDecimal DiasObra = new BigDecimal(((obra.getDatefecfinobra().getTime()
					- obra.getDatefeciniobra().getTime()) / 1000/3600/24 )).add(new BigDecimal(1));
			
			BigDecimal ciclos = DiasObra.divide(new BigDecimal(Math.max(periodoMedida.getDiasPeriodo(),1)),6,RoundingMode.HALF_EVEN) ;
			int iter  = ciclos.intValue();		

			eliminarPeriodos(obra);
			
			Date fechaInicio = obra.getDatefeciniobra();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaInicio);

			iterarPeriodos(obra, periodoMedida, DiasObra, iter, calendar);
		}
		return obra;
		
	}

	/**
	 * @param obra
	 */
	private void eliminarPeriodos(Obra obra) {
		List<Periodo> periodos = servicePeriodo.ListarPorObra(obra.getId());
		periodos.forEach((per) -> {
			servicePeriodo.eliminar(per.getId());
		});
	}
	
	@SuppressWarnings("static-access")
	private void iterarPeriodos(Obra obra, PeriodoMedida periodoMedida, BigDecimal DiasObra, int iter,
								Calendar calendar) {
		System.out.println("Generando Periodos, iteraciones: " + iter);

		int i;
		int diasRestantes = 0;
		if (iter > 0) {
			diasRestantes = DiasObra.intValue();
		}

		itero = false;
		for (i = 1; i <= iter; i++) {

			Periodo periodo = new Periodo();
			if (i == 1) {
				periodo.setFechainicio(calendar.getTime());
			} else {
				calendar.add(calendar.DAY_OF_YEAR, 1);
				periodo.setFechainicio(calendar.getTime());
			}
			calendar.add(calendar.DAY_OF_YEAR, periodoMedida.getDiasPeriodo() - 1);
			periodo.setValtotplanif(new BigDecimal(0));
			periodo.setObra(obra);
			diasRestantes = diasRestantes - (periodoMedida.getDiasPeriodo());

			if (i == iter && diasRestantes == 0) {
				periodo.setFechafin(obra.getDatefecfinobra());
			} else {
				periodo.setFechafin(calendar.getTime());
			}

			Periodo val = servicePeriodo.buscarPorObraFecha(obra.getId(), periodo.getFechainicio());
			if (val != null) {
				periodo.setId(val.getId());
			}else {
				periodo.GenerarId(obra, i);				
			}
			servicePeriodo.guardar(periodo);
			itero = true;

		}

		if (diasRestantes > 0) {
			Periodo periodo = new Periodo();
			calendar.add(calendar.DAY_OF_YEAR, 1);
			periodo.setFechainicio(calendar.getTime());
			periodo.setFechafin(obra.getDatefecfinobra());
			periodo.setValtotplanif(new BigDecimal(0));
			periodo.setObra(obra);
			Periodo val = servicePeriodo.buscarPorObraFecha(obra.getId(), periodo.getFechainicio());
			if (val != null) {
				periodo.setId(val.getId());
			}else {
				periodo.GenerarId(obra, i);				
			}
			servicePeriodo.guardar(periodo);

		}
	}

	@Override
	public ResponseGeneric planeacionPorPeriodo(Integer idObra) {

		Obra obra = serviceObra.buscarPorId(idObra);
		try {
			if (obra == null) {
				ResponseGeneric rspconfirmacion = new ResponseGeneric();
				rspconfirmacion.setStatus(false);
				rspconfirmacion.setMessage(Constantes.OBRA_INCORRECTA);
				return rspconfirmacion;
			}
			
			if (!this.generarPeriodos(obra)) {
				ResponseGeneric rspconfirmacion = new ResponseGeneric();
				rspconfirmacion.setStatus(false);
				rspconfirmacion.setMessage(Constantes.ESTADO_OBRA_INCORRECTO);
				return rspconfirmacion;
			}	
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		

		/*
		 * necesitamos crear un registro ActividadObraPeriodo por cada Periodo}
		 * 
		 * */
		List<Periodo> periodosGenerados = servicePeriodo.ListarPorObra(obra.getId());
		
		periodosGenerados.forEach((periodo)->{
			List<ActividadObraPeriodo> lstActivObraPeriodo = serviceActividadObraPeriodo.listarPorPeriodo(periodo.getId());
			serviceActividadObraPeriodo.eliminarAll(lstActivObraPeriodo);		
			periodo.setValtotplanif(new BigDecimal(0));
			servicePeriodo.actualizar(periodo);
		});

		List<ActividadobraWS> lstactividadObra = serviceactividadWS.desplegarTodosOrdenado(obra); 
	
		lstactividadObra.forEach((actObra)-> {
			List<Periodo> lstPeriodos = servicePeriodo.ListarPorObra(obra.getId());
			for (int i = 0; i <lstPeriodos.size(); i++) {			
				ActividadObraPeriodo actividadObraPeriodo = new ActividadObraPeriodo(); 
				actividadObraPeriodo.setActividadObra(actObra);
				actividadObraPeriodo.setPeriodo(lstPeriodos.get(i));
				actividadObraPeriodo.setCantidadPlanif(new BigDecimal(0));
				actividadObraPeriodo.setValPlanif(new BigDecimal(0));
				serviceActividadObraPeriodo.guardar(actividadObraPeriodo);
			}	
		});
		//indice de la actividad
		System.out.println(lstactividadObra);
		lstactividadObra.forEach((actObra)-> {
			System.out.println(actObra);
			List<Periodo> lstPeriodos = servicePeriodo.ListarPorObra(obra.getId());
			System.out.println(lstPeriodos);

			idLastPeriod = lstPeriodos.get(lstPeriodos.size()-1).getId();
			
			BigDecimal diarioActividad = new BigDecimal(0);
			double DiasPeriodo  = 0;
			double porcionDiasPeriodo = 0;
			BigDecimal acumCantidad = new BigDecimal(0);
			BigDecimal acumvalPlanif = new BigDecimal (0); 
			diasTotalActividad=0;
			diasTotalActividad = diasSuspension(actObra);
			diarioActividad = actObra.getValortotalactividadaiu()
					.divide(new BigDecimal(Math.max(diasTotalActividad,1)),6,RoundingMode.HALF_EVEN);

			bRemanente = false;
			acumControl = 0;
			for (int i = 0; i < lstPeriodos.size(); i++) {				
			
				if (obra.getIntestadoobra() != Constantes.ESTADO_OBRA_SUSPENDIDA) {
					
					/**
					 *el primer caso es cuando la fecha de inicio de la actividad es mejor a la fecha 
					 *de inicio del periodo pero la fecha fin de la actividad es mayor a ñla fecha de inicio
					 *de dicho periodo
					 *	Caso 1	Rango de fechas de la actividad esta anterior al rango del periodo	0
						Caso 2	Rango de fechas de la actividad esta posterior al rango del periodo	0
						Caso 3	Rango de fechas de la actividad inicia dentro del rango del periodo	FechaFinPeriodo - FechaIniActividad
						Caso 4	Rango de fechas de la actividad esta entre el rango del periodo	FechaFinActividad - FechaIniActividad
						Caso 5	Rango de fechas del periodo esta dentro del rango de fechas de la actividad	FechaFinPeriodo - FechaIniPeriodo
						Caso 6	Rango de fechas de la actividad termina en el rango del periodo	FechaFinActividad - FechaIniPeriodo
					 *
					 *
					 */
					
					if (actObra.getFechafin().getTime()<lstPeriodos.get(i).getFechainicio().getTime()) {
						DiasPeriodo = 0;
					}
					
					if (actObra.getFechainicio().getTime()>lstPeriodos.get(i).getFechafin().getTime()) {
						DiasPeriodo = 0;
					}
					
					if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechainicio().getTime()
							&& actObra.getFechafin().getTime()>lstPeriodos.get(i).getFechafin().getTime() ) {
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()-actObra.getFechainicio().getTime()) / 1000/3600/24);	
					}else if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechainicio().getTime()
							&& actObra.getFechafin().getTime()<=lstPeriodos.get(i).getFechafin().getTime()) {
						DiasPeriodo = 1 + ((actObra.getFechafin().getTime()-actObra.getFechainicio().getTime()) / 1000/3600/24);	
					}else if (lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
							&& lstPeriodos.get(i).getFechafin().getTime()<=actObra.getFechafin().getTime()) {
						/*caso 5 ojo*/
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
								-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);	
					}else if (lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
							&& actObra.getFechafin().getTime()<lstPeriodos.get(i).getFechafin().getTime()) {
						DiasPeriodo = 1 + ((actObra.getFechafin().getTime()
								-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);	
					}
					
					System.out.println("control ");

					System.out.println((acumControl+porcionDiasPeriodo));
					System.out.println(actObra.getFloatcantplanifao().doubleValue());

					if (acumControl+porcionDiasPeriodo>=actObra.getFloatcantplanifao().doubleValue()) {	
						bRemanente = true;
						System.out.println("last remanente");
					}
					System.out.println("Porcion periodo "+porcionDiasPeriodo);
					
				}
				
				
				porcionDiasPeriodo =  Utils.truncateDecimal((DiasPeriodo * 
						actObra.getFloatcantplanifao().doubleValue())/Math.max(diasTotalActividad,1), 4).doubleValue();
				
				porcionDiasPeriodo = porcionDiasPeriodo > 0 ? porcionDiasPeriodo : 0;
				DiasPeriodo = (DiasPeriodo < 0) ? 0 : DiasPeriodo;

				if (obra.getIntestadoobra() == Constantes.ESTADO_OBRA_MODIFICACION) {
					if (DiasPeriodo>0 || porcionDiasPeriodo==0) {
						ActividadObraPeriodo actObraPer = 
								serviceActividadObraPeriodo.buscarPorPeriodoActividad(lstPeriodos.get(i).getId(),
										actObra.getOidactiviobra());
						System.out.println(i);
						System.out.println(porcionDiasPeriodo);
						System.out.println(acumCantidad);
						System.out.println(acumvalPlanif);						
						System.out.println(lstPeriodos.get(i));
						System.out.println(actObra);
						System.out.println("====================");

						
						if ((i==lstPeriodos.size()-1) || 
								lstactividadObra.get(lstactividadObra.size()-1).getFechafin().getTime()>=
								lstPeriodos.get(i).getFechainicio().getTime() &&
								lstactividadObra.get(lstactividadObra.size()-1).getFechafin().getTime()<=
								lstPeriodos.get(i).getFechafin().getTime() ){
							
							actObraPer.setCantidadPlanif(actObra.getFloatcantplanifao().subtract(acumCantidad));
							actObraPer.setValPlanif(actObra.getValortotalactividadaiu().subtract(acumvalPlanif));	
							System.out.println("REGISTRANDO RESTO DE PERIODO" + actObraPer.getCantidadPlanif());
							acumCantidad = actObra.getFloatcantplanifao();
							acumvalPlanif = actObra.getValortotalactividadaiu();
						}else {							
								actObraPer.setCantidadPlanif(new BigDecimal(porcionDiasPeriodo));											
								actObraPer.setValPlanif(diarioActividad.multiply(new BigDecimal(DiasPeriodo)));
								acumCantidad = acumCantidad.add(new BigDecimal(porcionDiasPeriodo));
								acumvalPlanif = acumvalPlanif.add(actObraPer.getValPlanif());
						}	
						serviceActividadObraPeriodo.guardar(actObraPer);	

						lstPeriodos.get(i).setValtotplanif(
						lstPeriodos.get(i).getValtotplanif()
							.add(actObraPer.getValPlanif()));
						servicePeriodo.guardar(lstPeriodos.get(i));	
								
					}			

				}else {
					
					
					System.out.println("Dias periodo: "+DiasPeriodo);
					
					
					ActividadObraPeriodo actObraPer = 
							serviceActividadObraPeriodo.buscarPorPeriodoActividad(lstPeriodos.get(i).getId(),
									actObra.getOidactiviobra());
					if (DiasPeriodo>0) {
					
						System.out.println(actObra);
						System.out.println(lstPeriodos.get(i).getId());
						
						if (i==lstPeriodos.size()-1 || bRemanente ){
							actObraPer.setCantidadPlanif(actObra.getFloatcantplanifao().subtract(acumCantidad));
							actObraPer.setValPlanif(actObra.getValortotalactividadaiu().subtract(acumvalPlanif).setScale(4, RoundingMode.HALF_UP));	
							
						}else {
							System.out.println("actividad "+actObra.getOidactiviobra()+" valor diario "+diarioActividad);

							actObraPer.setCantidadPlanif(new BigDecimal(porcionDiasPeriodo));											
							actObraPer.setValPlanif(diarioActividad.multiply(new BigDecimal(DiasPeriodo))
									.setScale(4, RoundingMode.HALF_UP));
							acumCantidad = acumCantidad.add(new BigDecimal(porcionDiasPeriodo));
							acumvalPlanif = acumvalPlanif.add(actObraPer.getValPlanif()).setScale(4, RoundingMode.HALF_UP);
							acumControl = acumControl + porcionDiasPeriodo;

						}	
						serviceActividadObraPeriodo.guardar(actObraPer);
						
						lstPeriodos.get(i).setValtotplanif(
						lstPeriodos.get(i).getValtotplanif()
							.add(actObraPer.getValPlanif()));
						servicePeriodo.guardar(lstPeriodos.get(i));		
						System.out.println(lstPeriodos.get(i));
		
					}						
				}
								
			}	
		});
		
		/**FIN PROCESO DE PLANIFICACION DE ACTIVIDADES POR CADA PERIODO**/
		ResponseGeneric rspconfirmacion = new ResponseGeneric();
		rspconfirmacion.setStatus(true);
		rspconfirmacion.setMessage(Constantes.PLANIFICACION_COMPLETADA);
		return rspconfirmacion;

	}

	
	/**
	 * @param request
	 * @return
	 */
	public ResponseEntity<?> ajustarFechaObra(ObraChangeDate request) {
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
		if(obra.getDatefeciniobra()!=null && obra.getDatefecfinobra()!=null )
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
					Date fechafin = null;
					try {
						fechainicio = format.parse(request.getFechaInicioObra());
						fechafin = format.parse(request.getFechaFinObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		

					if ((fechainicio.compareTo(result.getFechainicio())>0) ||
							result.getFechainicio().compareTo(fechafin)>0)
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
					Date fechainicio = null;
					try {
						fechafin = format.parse(request.getFechaFinObra());
						fechainicio = format.parse(request.getFechaInicioObra());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); 
					}			
					
					if (fechafin.compareTo(result.getFechafin())<0 ||
							result.getFechafin().compareTo(fechainicio)<0)
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
	
	public ResponseGeneric valiChangeDate(ObraChangeDate objeto) {		
		ResponseGeneric response = new ResponseGeneric();
		if (objeto.getId()==null || objeto.getFechaFinObra()==null|| objeto.getFechaFinObra()=="" || objeto.getFechaInicioObra()==null|| objeto.getFechaInicioObra()=="") {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return response;
		}
		return null;			
	}
	
	/**
	 * @param obra
	 * @param periodo
	 */
	@SuppressWarnings({ "unused", "null" })
	private void validateSuspension(Obra obra, PeriodoMedida periodoMedida, ObraModificacion obraMod) {
		
		Calendar calendar = Calendar.getInstance();

		List<Periodo> lstPeriodos = servicePeriodo.ListarPorObra(obra.getId());
		if (!lstPeriodos.isEmpty()) {
			Periodo lastPeriodo = lstPeriodos.get(lstPeriodos.size()-1);	
			fechaFinUltimoPeriodo = lastPeriodo.getFechafin();
		}else {
			fechaFinUltimoPeriodo = obra.getDatefeciniobra(); //el ultimo periodo o la fecha de inicio de la obra en caso de no existir periodos
		}
		
		
		if (fechaFinUltimoPeriodo!=null && fechaFinUltimoPeriodo.getTime()<obraMod.getNewfechafin().getTime()) {
			generarNuevosPeriodos(obra, obraMod.getNewfechafin(), periodoMedida.getDiasPeriodo(), fechaFinUltimoPeriodo);		
		}
		

		List<Periodo> lstPeriodoBorrar = servicePeriodo.ListarPorObra(obra.getId());
		lstPeriodoBorrar.forEach(arg0->{
			if (arg0.getFechainicio().getTime()>obraMod.getNewfechafin().getTime()) {
				servicePeriodo.eliminar(arg0.getId());
			}
		});
		
		List<Periodo> lstPeriodoEnd = servicePeriodo.ListarPorObra(obra.getId());
		if (!lstPeriodoEnd.isEmpty()) {
			Periodo lastPeriodo = lstPeriodoEnd.get(lstPeriodoEnd.size()-1);
			lastPeriodo.setFechafin(obraMod.getNewfechafin());		
			servicePeriodo.guardar(lastPeriodo);
		}
		
	}

	/**
	 * @param obra
	 * @param periodoMedida
	 * @param calendar
	 */
	private void generarNuevosPeriodos(Obra obra, Date fechaLimiteGen, long periodoMedida, Date fechaFinUltimoPeriodo) {
			int periodoMedidaAux = (int) periodoMedida;
			
			List<Periodo> lstPeriodos = servicePeriodo.ListarPorObra(obra.getId());
			if (!lstPeriodos.isEmpty()) {
				Periodo lastPeriodo = lstPeriodos.get(lstPeriodos.size()-1);
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(lastPeriodo.getFechainicio());
				calendar.add(calendar.DAY_OF_YEAR, (int) periodoMedida - 1);		
				lastPeriodo.setFechafin(calendar.getTime());
				servicePeriodo.guardar(lastPeriodo);
				
				int lenId = String.valueOf(lastPeriodo.getObra().getId()).length();
				String sIdPeriodo = String.valueOf(lastPeriodo.getId());
				String sIdObra = String.valueOf(lastPeriodo.getObra().getId());

				ultimoIdPeriodo = Integer.parseInt(sIdPeriodo.substring(lenId));
				
				fechaFinUltimoPeriodo = lastPeriodo.getFechafin();
			}else {
				fechaFinUltimoPeriodo = obra.getDatefeciniobra();
			}
			
			
			do
	        {
				Calendar calendarNuevo = Calendar.getInstance();
				calendarNuevo.setTime(fechaFinUltimoPeriodo);
				
				Periodo periodoNuevo = new Periodo();
				if (!(fechaFinUltimoPeriodo.compareTo(obra.getDatefeciniobra())==0)) {
					calendarNuevo.add(calendarNuevo.DAY_OF_YEAR, 1);					
				}

				periodoNuevo.setFechainicio(calendarNuevo.getTime());
				
				long diasEntreFechas = (1+ (fechaLimiteGen.getTime()-fechaFinUltimoPeriodo.getTime())/1000/3600/24);
				periodoMedidaAux = (int) ((diasEntreFechas<periodoMedida) ? diasEntreFechas : periodoMedida -1);

				calendarNuevo.add(calendarNuevo.DAY_OF_YEAR, (int) periodoMedidaAux);
				periodoNuevo.setFechafin(calendarNuevo.getTime());
				if (periodoNuevo.getFechafin().getTime()>=fechaLimiteGen.getTime()){
					periodoNuevo.setFechafin(fechaLimiteGen);
				}	
				periodoNuevo.setValtotplanif(new BigDecimal(0));
				periodoNuevo.setObra(obra);
				ultimoIdPeriodo++;
				periodoNuevo.GenerarId(obra, (int) ultimoIdPeriodo);

				servicePeriodo.guardar(periodoNuevo);
				fechaFinUltimoPeriodo = periodoNuevo.getFechafin();
				
				fechainicio = periodoNuevo.getFechainicio();
				//System.out.println(periodoNuevo.toString());
			}
	        while ((fechaFinUltimoPeriodo.getTime()<fechaLimiteGen.getTime()));
			
		
	}
	
	public double diasSuspension(ActividadobraWS actObra) {

		diasActividad = 0;
		diasActividad = 1 + (actObra.getFechafin().getTime() - actObra.getFechainicio().getTime() )/1000/3600/24;
		
		return diasActividad;
	}
	
	private double diasActividad(Obra obra, ActividadobraWS actObra) {
		diasTotalActividad = 0;
		if (obra.getSuspensionesobra().isEmpty() || obra.getIntestadoobra() != Constantes.ESTADO_OBRA_SUSPENDIDA) {
			diasTotalActividad = 
					1+ ((actObra.getFechafin().getTime() - actObra.getFechainicio().getTime())
							/ 1000 / 3600 / 24);
			return diasTotalActividad;
		}
		
		obra.getSuspensionesobra().forEach(arg0->{
			
			boolean finActividadMenorInicioSusp = actObra.getFechafin().before(arg0.getFechaInicio());
			
			boolean inicioActividadMayorFinSusp = actObra.getFechainicio().after(arg0.getFechaFin());
			
			boolean inicioActividadMenorInicioSusp = actObra.getFechainicio().before(arg0.getFechaInicio());
			
			boolean finActividadMayorInicioSusp = actObra.getFechafin().after(arg0.getFechaInicio());
						
			boolean inicioActividadMayorInicioSusp = actObra.getFechainicio().after(arg0.getFechaInicio());
			
			boolean finActividadMayorFinSusp = actObra.getFechafin().after(arg0.getFechaFin());
						
			boolean inicioActividadIgualFinSusp = actObra.getFechainicio().compareTo(arg0.getFechaFin())==0;
			
			boolean finActividadIgualInicioSusp = actObra.getFechafin().compareTo(arg0.getFechaInicio())==0;
						
			if (finActividadMenorInicioSusp || inicioActividadMayorFinSusp) {
				diasTotalActividad += 1 + ((actObra.getFechafin().getTime() - actObra.getFechainicio().getTime())
								/ 1000 / 3600 / 24);
			}else if (finActividadIgualInicioSusp || inicioActividadIgualFinSusp) {
				diasTotalActividad += 1+ ((actObra.getFechafin().getTime() - actObra.getFechainicio().getTime())
						/ 1000 / 3600 / 24);
			}else if (inicioActividadMenorInicioSusp && finActividadMayorInicioSusp) {
				diasTotalActividad += 1+ ((arg0.getFechaInicio().getTime() - actObra.getFechainicio().getTime())
						/ 1000 / 3600 / 24);
			}else if (inicioActividadMayorInicioSusp && finActividadMayorFinSusp) {
				diasTotalActividad += 1+ ((actObra.getFechafin().getTime() - arg0.getFechaFin().getTime())
						/ 1000 / 3600 / 24);
			}
		});
		return diasTotalActividad;
		
	}
	

	@Override
	public ResponseGeneric planeacionPorPeriodoBack(Integer idObra) {

		Obra obra = serviceObra.buscarPorId(idObra);
		try {
			if (obra == null) {
				ResponseGeneric rspconfirmacion = new ResponseGeneric();
				rspconfirmacion.setStatus(false);
				rspconfirmacion.setMessage(Constantes.OBRA_INCORRECTA);
				return rspconfirmacion;
			}
			
			if (!this.generarPeriodos(obra)) {
				ResponseGeneric rspconfirmacion = new ResponseGeneric();
				rspconfirmacion.setStatus(false);
				rspconfirmacion.setMessage(Constantes.ESTADO_OBRA_INCORRECTO);
				return rspconfirmacion;
			}	
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		

		/*
		 * necesitamos crear un registro ActividadObraPeriodo por cada Periodo}
		 * 
		 * */
		List<Periodo> periodosGenerados = servicePeriodo.ListarPorObra(obra.getId());
		
		periodosGenerados.forEach((periodo)->{
			List<ActividadObraPeriodo> lstActivObraPeriodo = serviceActividadObraPeriodo.listarPorPeriodo(periodo.getId());
			serviceActividadObraPeriodo.eliminarAll(lstActivObraPeriodo);		
			periodo.setValtotplanif(new BigDecimal(0));
			servicePeriodo.actualizar(periodo);
		});

		List<ActividadobraWS> lstactividadObra = serviceactividadWS.desplegarTodos(obra); 
	
		lstactividadObra.forEach((actObra)-> {
			List<Periodo> lstPeriodos = servicePeriodo.ListarPorObra(obra.getId());
			for (int i = 0; i <lstPeriodos.size(); i++) {			
				ActividadObraPeriodo actividadObraPeriodo = new ActividadObraPeriodo(); 
				actividadObraPeriodo.setActividadObra(actObra);
				actividadObraPeriodo.setPeriodo(lstPeriodos.get(i));
				actividadObraPeriodo.setCantidadPlanif(new BigDecimal(0));
				actividadObraPeriodo.setValPlanif(new BigDecimal(0));
				serviceActividadObraPeriodo.guardar(actividadObraPeriodo);
			}	
		});
		//indice de la actividad
		System.out.println(lstactividadObra);
		lstactividadObra.forEach((actObra)-> {
			System.out.println(actObra);
			List<Periodo> lstPeriodos = servicePeriodo.ListarPorObra(obra.getId());
			System.out.println(lstPeriodos);

			idLastPeriod = lstPeriodos.get(lstPeriodos.size()-1).getId();
			
			BigDecimal diarioActividad = new BigDecimal(0);
			double DiasPeriodo  = 0;
			double porcionDiasPeriodo = 0;
			BigDecimal acumCantidad = new BigDecimal(0);
			BigDecimal acumvalPlanif = new BigDecimal (0); 
			diasTotalActividad=0;
			diasTotalActividad = diasSuspension(actObra);
			diarioActividad = actObra.getValortotalactividadaiu()
					.divide(new BigDecimal(Math.max(diasTotalActividad,1)),6,RoundingMode.HALF_EVEN);

			bRemanente = false;
			for (int i = 0; i < lstPeriodos.size(); i++) {				
			
				if (obra.getIntestadoobra() != Constantes.ESTADO_OBRA_SUSPENDIDA) {
					//caso 0
					if (lstPeriodos.get(i).getFechainicio().getTime()<actObra.getFechainicio().getTime()
							&& actObra.getFechainicio().getTime()<= lstPeriodos.get(i).getFechafin().getTime()) {
						
							DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
									-actObra.getFechainicio().getTime()) / 1000/3600/24);		
							System.out.println("caso 0");
						
					}else {
						//Caso 1	Rango de fechas de la actividad esta anterior al rango del periodo	0
						if (actObra.getFechafin().getTime()<=lstPeriodos.get(i).getFechainicio().getTime()) {
							DiasPeriodo = 0;
							System.out.println("caso 1");
						}
						//Caso 2	Rango de fechas de la actividad esta posterior al rango del periodo	0
						if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechafin().getTime()) {
							DiasPeriodo = 0;
							System.out.println("caso 2");
						}
						//Caso 3	Rango de fechas de la actividad inicia dentro del rango del periodo	FechaFinPeriodo - FechaIniActividad
						if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechainicio().getTime()
								&& actObra.getFechainicio().getTime()<=lstPeriodos.get(i).getFechafin().getTime()) {
							
								DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechainicio().getTime()
										-actObra.getFechainicio().getTime()) / 1000/3600/24);
								System.out.println("caso 3");
						}
						//Caso 4 Rango de fechas de la actividad esta entre el rango del periodo	FechaFinActividad - FechaIniActividad
						if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechainicio().getTime()
								&& actObra.getFechafin().getTime()<=lstPeriodos.get(i).getFechafin().getTime()) {

								DiasPeriodo = 1 + ((actObra.getFechafin().getTime()
										-actObra.getFechainicio().getTime()) / 1000/3600/24);
								System.out.println("caso 4");
								
						}
						//Caso 5 Rango de fechas del periodo esta dentro del rango de fechas de la actividad	FechaFinPeriodo - FechaIniPeriodo
						if (lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
								&& actObra.getFechafin().getTime()>=lstPeriodos.get(i).getFechafin().getTime()) {
							
								DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
										-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
								System.out.println("caso 5");
						}
						//Caso 6	Rango de fechas de la actividad termina en el rango del periodo	FechaFinActividad - FechaIniPeriodo
						if (!bRemanente && lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
								&& actObra.getFechafin().getTime()<=lstPeriodos.get(i).getFechafin().getTime()) {
							
								DiasPeriodo = 1 + ((actObra.getFechafin().getTime()
										-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
								System.out.println("caso 6");
								bRemanente = true;
						}
					}
				}else {
					if (lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
							&& lstPeriodos.get(i).getFechafin().getTime()<=actObra.getFechafin().getTime()) {
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime() - lstPeriodos.get(i).getFechainicio().getTime())/ 1000/3600/24);
						System.out.println("caso especial");
					}
					
				}
				
				
				porcionDiasPeriodo =  Utils.truncateDecimal((DiasPeriodo * 
						actObra.getFloatcantplanifao().doubleValue())/Math.max(diasTotalActividad,1), 4).doubleValue();
				porcionDiasPeriodo = porcionDiasPeriodo > 0 ? porcionDiasPeriodo : 0;
				DiasPeriodo = (DiasPeriodo < 0) ? 0 : DiasPeriodo;

				if (obra.getIntestadoobra() == Constantes.ESTADO_OBRA_MODIFICACION) {
					if (DiasPeriodo>0 || porcionDiasPeriodo==0) {
						ActividadObraPeriodo actObraPer = 
								serviceActividadObraPeriodo.buscarPorPeriodoActividad(lstPeriodos.get(i).getId(),
										actObra.getOidactiviobra());
						System.out.println(i);
						System.out.println(porcionDiasPeriodo);
						System.out.println(acumCantidad);
						System.out.println(acumvalPlanif);						
						System.out.println(lstPeriodos.get(i));
						System.out.println(actObra);
						System.out.println("====================");

						
						if ((i==lstPeriodos.size()-1) || 
								lstactividadObra.get(lstactividadObra.size()-1).getFechafin().getTime()>=
								lstPeriodos.get(i).getFechainicio().getTime() &&
								lstactividadObra.get(lstactividadObra.size()-1).getFechafin().getTime()<=
								lstPeriodos.get(i).getFechafin().getTime() ){
							
							actObraPer.setCantidadPlanif(actObra.getFloatcantplanifao().subtract(acumCantidad));
							actObraPer.setValPlanif(actObra.getValortotalactividadaiu().subtract(acumvalPlanif));	
							System.out.println("REGISTRANDO RESTO DE PERIODO" + actObraPer.getCantidadPlanif());
							acumCantidad = actObra.getFloatcantplanifao();
							acumvalPlanif = actObra.getValortotalactividadaiu();
						}else {							
								actObraPer.setCantidadPlanif(new BigDecimal(porcionDiasPeriodo));											
								actObraPer.setValPlanif(diarioActividad.multiply(new BigDecimal(DiasPeriodo)));
								acumCantidad = acumCantidad.add(new BigDecimal(porcionDiasPeriodo));
								acumvalPlanif = acumvalPlanif.add(actObraPer.getValPlanif());
						}	
						serviceActividadObraPeriodo.guardar(actObraPer);	

						lstPeriodos.get(i).setValtotplanif(
						lstPeriodos.get(i).getValtotplanif()
							.add(actObraPer.getValPlanif()));
						servicePeriodo.guardar(lstPeriodos.get(i));	
								
					}			

				}else {
					
					
					System.out.println("Dias periodo: "+DiasPeriodo);
					
					
					ActividadObraPeriodo actObraPer = 
							serviceActividadObraPeriodo.buscarPorPeriodoActividad(lstPeriodos.get(i).getId(),
									actObra.getOidactiviobra());
					if (DiasPeriodo>0) {
						System.out.println(lstPeriodos.get(i).getId());
						

						if (i==lstPeriodos.size()-1){
							actObraPer.setCantidadPlanif(actObra.getFloatcantplanifao().subtract(acumCantidad));
							actObraPer.setValPlanif(actObra.getValortotalactividadaiu().subtract(acumvalPlanif));	
							
						}else {
							actObraPer.setCantidadPlanif(new BigDecimal(porcionDiasPeriodo));											
							actObraPer.setValPlanif(diarioActividad.multiply(new BigDecimal(DiasPeriodo)));
							acumCantidad = acumCantidad.add(new BigDecimal(porcionDiasPeriodo));
							acumvalPlanif = acumvalPlanif.add(actObraPer.getValPlanif());
						}	
						serviceActividadObraPeriodo.guardar(actObraPer);
						
						lstPeriodos.get(i).setValtotplanif(
						lstPeriodos.get(i).getValtotplanif()
							.add(actObraPer.getValPlanif()));
						servicePeriodo.guardar(lstPeriodos.get(i));		
						System.out.println(lstPeriodos.get(i));
		
					}	
					
					if (bRemanente) {
						actObraPer.setCantidadPlanif(actObraPer.getCantidadPlanif().add(new BigDecimal(actObra.getFloatcantplanifao().doubleValue()-acumCantidad.doubleValue()))) ;
						actObraPer.setValPlanif(actObraPer.getValPlanif().add(new BigDecimal(actObra.getValortotalactividadaiu().doubleValue()-acumvalPlanif.doubleValue())));
						serviceActividadObraPeriodo.guardar(actObraPer);
						System.out.println(lstPeriodos.get(i));

						lstPeriodos.get(i).setValtotplanif(
								lstPeriodos.get(i).getValtotplanif()
									.add(new BigDecimal(actObra.getValortotalactividadaiu().doubleValue()-acumvalPlanif.doubleValue())));
						System.out.println(lstPeriodos.get(i).getValtotplanif());
						System.out.println(actObraPer.getValPlanif());
						
						servicePeriodo.guardar(lstPeriodos.get(i));		
						
						System.out.println(lstPeriodos.get(i));
						System.out.println("Fin de proceso: "+acumvalPlanif.doubleValue());
						System.out.println(actObra.getFloatcantplanifao().doubleValue()-acumCantidad.doubleValue());
						System.out.println(actObra.getValortotalactividadaiu().doubleValue()-acumvalPlanif.doubleValue());
						acumCantidad = actObra.getFloatcantplanifao();
						acumvalPlanif = actObra.getValortotalactividadaiu();
						bRemanente = false; //indica que a pesar de haberse agotado los dias de periodo, queda un
						//remanente el cual se debe distribuir
					}

					
				}
								
			}	
		});
		
		/**FIN PROCESO DE PLANIFICACION DE ACTIVIDADES POR CADA PERIODO**/
		ResponseGeneric rspconfirmacion = new ResponseGeneric();
		rspconfirmacion.setStatus(true);
		rspconfirmacion.setMessage(Constantes.PLANIFICACION_COMPLETADA);
		return rspconfirmacion;

	}
}
