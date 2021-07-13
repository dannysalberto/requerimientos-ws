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
import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.models.PeriodoMedida;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.models.SuspensionObra;
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoService;
import co.com.interkont.wsmiobra.service.ActividadObraWSService;
import co.com.interkont.wsmiobra.service.ObrasService;
import co.com.interkont.wsmiobra.service.PeriodoMedidaService;
import co.com.interkont.wsmiobra.service.PeriodoService;
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
	
	public int i,j=0;
	public boolean itero = false;
	public int cantidad=0;
	public double diasTotalActividad;
	double diasSusp = 0;
	Date fechaFinUltimaSusp;
	Date fechaFinUltimoPeriodo;
	int ultimoIdPeriodo = 0;
	
	@Override
	public boolean generarPeriodos(Obra obra) {
		System.out.println("REGENERANDO PERIODOS "+obra.getIntestadoobra());
		if ((obra.getIntestadoobra() == Constantes.ESTADO_OBRA_MODIFICACION && obra.getSuspensionesobra().isEmpty() ) ||
				obra.getIntestadoobra()==Constantes.ESTADO_OBRA_POR_INICIAR ) {
			System.out.println("Actualizando Periodo");
			this.regenerarPeriodos(obra);
			return true;
		}
		if (obra.getIntestadoobra() == Constantes.ESTADO_OBRA_MODIFICACION && !obra.getSuspensionesobra().isEmpty() ) {
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());
			validateSuspension(obra,periodoMedida);	
			return true;
		}
		return false;
	}
	
	private void regenerarPeriodos(Obra obra) {

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
		
		/*
		 * necesitamos crear un registro ActividadObraPeriodo por cada Periodo}
		 * 
		 * */
		List<Periodo> periodosGenerados = servicePeriodo.ListarPorObra(obra.getId());
		
		periodosGenerados.forEach((periodo)->{
			List<ActividadObraPeriodo> lstActivObraPeriodo = serviceActividadObraPeriodo.listarPorPeriodo(periodo.getId());
			serviceActividadObraPeriodo.eliminarAll(lstActivObraPeriodo);			
		});

		List<ActividadobraWS> lstactividadObra = serviceactividadWS.desplegarTodos(obra); 
	
		lstactividadObra.forEach((actObra)-> {
			List<Periodo> lstPeriodos = periodosGenerados;
			for (int i = 0; i < lstPeriodos.size(); i++) {			
				ActividadObraPeriodo actividadObraPeriodo = new ActividadObraPeriodo(); 
				actividadObraPeriodo.setActividadObra(actObra);
				actividadObraPeriodo.setPeriodo(lstPeriodos.get(i));
				actividadObraPeriodo.setCantidadPlanif(new BigDecimal(0));
				actividadObraPeriodo.setValPlanif(new BigDecimal(0));
				serviceActividadObraPeriodo.guardar(actividadObraPeriodo);
			}	
		});

		lstactividadObra.forEach((actObra)-> {

			List<Periodo> lstPeriodos = periodosGenerados;
			BigDecimal diarioActividad = new BigDecimal(0);
			double DiasPeriodo = 0;
			double porcionDiasPeriodo = 0;
			BigDecimal acumCantidad = new BigDecimal(0);
			BigDecimal acumvalPlanif = new BigDecimal (0); 
			diasTotalActividad=0;
			for (int i = 0; i < lstPeriodos.size(); i++) {
				diasTotalActividad = diasActividad(obra, actObra);
				diarioActividad = actObra.getValortotalactividadaiu().divide(new BigDecimal(Math.max(diasTotalActividad,1)),6,RoundingMode.HALF_EVEN);
			
				if (obra.getIntestadoobra() != Constantes.ESTADO_OBRA_SUSPENDIDA) {
					//caso 0
					if (lstPeriodos.get(i).getFechainicio().getTime()<actObra.getFechainicio().getTime()
							&& actObra.getFechainicio().getTime()<= lstPeriodos.get(i).getFechafin().getTime()) {
						
							DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
									-actObra.getFechainicio().getTime()) / 1000/3600/24);						
						
					}else {
						//Caso 1	Rango de fechas de la actividad esta anterior al rango del periodo	0
						if (actObra.getFechafin().getTime()<=lstPeriodos.get(i).getFechainicio().getTime()) {
							DiasPeriodo = 0;
						}
						//Caso 2	Rango de fechas de la actividad esta posterior al rango del periodo	0
						if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechafin().getTime()) {
							DiasPeriodo = 0;
						}
						//Caso 3	Rango de fechas de la actividad inicia dentro del rango del periodo	FechaFinPeriodo - FechaIniActividad
						if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechainicio().getTime()
								&& actObra.getFechainicio().getTime()<=lstPeriodos.get(i).getFechafin().getTime()) {
							
								DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechainicio().getTime()
										-actObra.getFechainicio().getTime()) / 1000/3600/24);
								
						}
						//Caso 4 Rango de fechas de la actividad esta entre el rango del periodo	FechaFinActividad - FechaIniActividad
						if (actObra.getFechainicio().getTime()>=lstPeriodos.get(i).getFechainicio().getTime()
								&& actObra.getFechafin().getTime()<=lstPeriodos.get(i).getFechafin().getTime()) {

								DiasPeriodo = 1 + ((actObra.getFechafin().getTime()
										-actObra.getFechainicio().getTime()) / 1000/3600/24);
								
						}
						//Caso 5 Rango de fechas del periodo esta dentro del rango de fechas de la actividad	FechaFinPeriodo - FechaIniPeriodo
						if (lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
								&& actObra.getFechafin().getTime()>=lstPeriodos.get(i).getFechafin().getTime()) {
							
								DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
										-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
						
						}
						//Caso 6	Rango de fechas de la actividad termina en el rango del periodo	FechaFinActividad - FechaIniPeriodo
						if (lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
								&& actObra.getFechafin().getTime()<=lstPeriodos.get(i).getFechafin().getTime()) {
							
								DiasPeriodo = 1 + ((actObra.getFechafin().getTime()
										-lstPeriodos.get(i).getFechainicio().getTime()) / 1000/3600/24);
							
						}
					}
				}else {
					if (lstPeriodos.get(i).getFechainicio().getTime()>=actObra.getFechainicio().getTime()
							&& lstPeriodos.get(i).getFechafin().getTime()<=actObra.getFechafin().getTime()) {
						DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime() - lstPeriodos.get(i).getFechainicio().getTime())/ 1000/3600/24);
						//DiasPeriodo = DiasPeriodo - diasSuspension(actObra,lstPeriodos.get(i));
					}
					
				}
			
				
				
				porcionDiasPeriodo =  Utils.truncateDecimal((DiasPeriodo * 
						actObra.getFloatcantplanifao().doubleValue())/Math.max(diasTotalActividad,1), 4).doubleValue();
				porcionDiasPeriodo = porcionDiasPeriodo > 0 ? porcionDiasPeriodo : 0;
				
				if (DiasPeriodo>0) {
					ActividadObraPeriodo actObraPer = 
							serviceActividadObraPeriodo.buscarPorPeriodoActividad(lstPeriodos.get(i).getId(),
									actObra.getOidactiviobra());
					
					if (i==lstPeriodos.size()-1) {
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
	private void validateSuspension(Obra obra, PeriodoMedida periodoMedida) {
		System.out.println("ESTAMOS VALIDANDO");
		obra.getPeriodos().forEach(periodo->{
			periodo.setValtotplanif(new BigDecimal(0));
			servicePeriodo.guardar(periodo);
		});	
		if (obra.getSuspensionesobra().isEmpty()) {
			System.out.println("No tiene suspensiones");
		}else {
			System.out.println("Calculo de periodos por suspensión");
			fechaFinUltimaSusp = null;
			fechaFinUltimoPeriodo = null;
			ultimoIdPeriodo = 0;
			Set<SuspensionObra> lstSuspensiones = obra.getSuspensionesobra();
			System.out.println(lstSuspensiones.toString());
			fechaFinUltimaSusp = lstSuspensiones.stream().map(u -> u.getFechaFin()).max(Date::compareTo).get();
			System.out.println("Fecha de ultim suspension "+fechaFinUltimaSusp+" "+obra.getId());

			List<Periodo> periodos = servicePeriodo
					.ListarPeriodosPorObraFechaInicio(obra.getId(),fechaFinUltimaSusp);
			
			System.out.println(periodos);
			periodos.forEach((per) -> {
				servicePeriodo.eliminar(per.getId());
				fechaFinUltimoPeriodo = per.getFechafin();
				ultimoIdPeriodo = per.getId();
				//System.out.println("Ciclo 1");


			});
			
			List<Periodo> periodosRestantes = servicePeriodo
					.ListarPeriodosPorObraFechaInicio(obra.getId(),fechaFinUltimaSusp);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaFinUltimaSusp);
			
			periodosRestantes.forEach((per) -> {
				
				calendar.add(calendar.DAY_OF_YEAR, 1);
				per.setFechainicio(calendar.getTime());
				calendar.add(calendar.DAY_OF_YEAR, periodoMedida.getDiasPeriodo() - 1);
				per.setFechafin(calendar.getTime());
				
				servicePeriodo.guardar(per);
				calendar.setTime(per.getFechafin());
				fechaFinUltimoPeriodo = per.getFechafin();
				ultimoIdPeriodo = per.getId();
				//System.out.println("Ciclo 2");

			});
			
			if (periodosRestantes.isEmpty()) {
				List<Periodo> periodoUltimo = servicePeriodo.ListarPorObra(obra.getId());
				fechaFinUltimoPeriodo = periodoUltimo.get(periodoUltimo.size()-1).getFechafin();
				ultimoIdPeriodo = periodoUltimo.get(periodoUltimo.size()-1).getId();

			}
			
		
			if (fechaFinUltimoPeriodo.after(obra.getDatefecfinobra()) || fechaFinUltimoPeriodo.compareTo(obra.getDatefecfinobra())==0) {
				Periodo per = servicePeriodo.buscarPorId(ultimoIdPeriodo);
				per.setFechafin(obra.getDatefecfinobra());
				servicePeriodo.guardar(per);
			}else {
				do
		        {
					Calendar calendarNuevo = Calendar.getInstance();
					calendarNuevo.setTime(fechaFinUltimoPeriodo);
					
					Periodo periodoNuevo = new Periodo();
					calendarNuevo.add(calendar.DAY_OF_YEAR, 1);
					periodoNuevo.setFechainicio(calendarNuevo.getTime());
					calendar.add(calendar.DAY_OF_YEAR, periodoMedida.getDiasPeriodo() - 1);
					periodoNuevo.setFechafin(calendar.getTime());
					periodoNuevo.setValtotplanif(new BigDecimal(0));
					periodoNuevo.setObra(obra);
					ultimoIdPeriodo++;
					periodoNuevo.setId(ultimoIdPeriodo);	
					//System.out.println(periodoNuevo.toString());
					servicePeriodo.guardar(periodoNuevo);
					fechaFinUltimoPeriodo = periodoNuevo.getFechafin();
					ultimoIdPeriodo = periodoNuevo.getId();
		        }
		        while (!fechaFinUltimoPeriodo.after(obra.getDatefecfinobra()));
				
				if (fechaFinUltimoPeriodo.after(obra.getDatefecfinobra())) {
					Periodo per = servicePeriodo.buscarPorId(ultimoIdPeriodo);
					per.setFechafin(obra.getDatefecfinobra());
					servicePeriodo.guardar(per);
				}
				
			}	
			
		}
	}
	
	public double diasSuspension(ActividadobraWS actObra, Periodo periodo) {

		Obra obra = actObra.getObra();
		diasSusp = 0;
		obra.getSuspensionesobra().forEach(susp->{
			diasSusp = susp.getFechaFin().getTime() - susp.getFechaInicio().getTime();			
		});	
		return diasSusp;
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
	


}
