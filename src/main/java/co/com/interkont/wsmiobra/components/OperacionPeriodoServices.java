package co.com.interkont.wsmiobra.components;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.interfaces.ICalculosPeriodo;
import co.com.interkont.wsmiobra.models.ActividadObraPeriodo;
import co.com.interkont.wsmiobra.models.ActividadobraWS;
import co.com.interkont.wsmiobra.models.Periodo;
import co.com.interkont.wsmiobra.models.PeriodoMedida;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.ActividadObraPeriodoService;
import co.com.interkont.wsmiobra.service.ActividadObraWSService;
import co.com.interkont.wsmiobra.service.ObrasService;
import co.com.interkont.wsmiobra.service.PeriodoMedidaService;
import co.com.interkont.wsmiobra.service.PeriodoService;
import co.com.interkont.wsmiobra.utils.Utils;

@Component
public class OperacionPeriodoServices implements ICalculosPeriodo{
	
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
	
	public int i=0;
	public boolean itero = false;

	
	@Override
	public boolean generarPeriodos(Obra obra) {
		System.out.println("REGENERANDO PERIODOS "+obra.getIntestadoobra());
		if (obra.getIntestadoobra()==Constantes.ESTADO_OBRA_MODIFICACION) {
			System.out.println("Actualizando Periodo");
			this.regenerarPeriodos(obra);
			return true;
		}
		else if (obra.getIntestadoobra()==Constantes.ESTADO_OBRA_POR_INICIAR) {
			System.out.println("Regenerando Periodo");
			this.regenerarPeriodos(obra);
			return true;
		}
		return false;
	}

	@SuppressWarnings({ "unused", "static-access" })
	private void actualizarPeriodos(Obra obra) {
		
			List<Periodo> lastPeriodo = servicePeriodo.ListarPorObra(obra.getId());
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());

			Date fechaIni = null;
			int i = lastPeriodo.size();
			
			List<Periodo> periodos = servicePeriodo.ListarPorObra(obra.getId());
			periodos.forEach((per)->{
				servicePeriodo.eliminar(per.getId());
			});

			fechaIni = lastPeriodo.get(lastPeriodo.size()-1).getFechafin();
			BigDecimal DiasObra = new BigDecimal(((obra.getDatefecfinobra().getTime()
					- fechaIni.getTime()) / 1000/3600/24));
			if (!DiasObra.equals(new BigDecimal(0))) {
				DiasObra.add(new BigDecimal(1));
			}
			System.out.println("Fecha fin ultimo periodo: "+ lastPeriodo.get(lastPeriodo.size()-1).getFechafin());
			System.out.println("Fecha fin obra: " +obra.getDatefecfinobra());
			System.out.println("Dias de la obra: " +DiasObra.doubleValue());
			//System.out.println(fechaIni);
			BigDecimal ciclos = DiasObra.divide(
					new BigDecimal(Math.max(periodoMedida.getDiasPeriodo(),1)),6
					,RoundingMode.HALF_EVEN) ;
			int iter  = ciclos.intValue();
			System.out.println("iter de la obra: " +iter);
						
			Date fechaInicio = fechaIni;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaInicio);

			iterarPeriodos(obra, periodoMedida, DiasObra, iter, calendar);
	}

	
	private void regenerarPeriodos(Obra obra) {

		if (obra.getIntidperiomedida()>0) {
			PeriodoMedida periodoMedida = servicePeriodoMedida.buscarPorId(obra.getIntidperiomedida());

	
			BigDecimal DiasObra = new BigDecimal(((obra.getDatefecfinobra().getTime()
					- obra.getDatefeciniobra().getTime()) / 1000/3600/24));
			if (!DiasObra.equals(new BigDecimal(0))) {
				DiasObra.add(new BigDecimal(1));
			}
			
			//System.out.println("Dias obra: "+DiasObra.doubleValue());
			BigDecimal ciclos = DiasObra.divide(new BigDecimal(Math.max(periodoMedida.getDiasPeriodo(),1)),6,RoundingMode.HALF_EVEN) ;
			int iter  = ciclos.intValue();
			
			int diasRestantes = 0;
			//System.out.println("Generando Periodos, iteraciones: "+iter);	

			List<Periodo> periodos = servicePeriodo.ListarPorObra(obra.getId());
			periodos.forEach((per)->{
				servicePeriodo.eliminar(per.getId());
			});
			
			Date fechaInicio = obra.getDatefeciniobra();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaInicio);

			iterarPeriodos(obra, periodoMedida, DiasObra, iter, calendar);
		}
		
	}
	
	@SuppressWarnings("static-access")
	private void iterarPeriodos(Obra obra, PeriodoMedida periodoMedida, BigDecimal DiasObra, int iter,
								Calendar calendar) {
			System.out.println("Generando Periodos, iteraciones: "+iter);	

			
			int i;
			int diasRestantes = 0;
			if (iter>0) {
				diasRestantes = DiasObra.intValue();				
			}

			itero=false;
			for (i=1;i<=iter;i++) {
					
					Periodo periodo = new Periodo();
					if (i==1) {
						periodo.setFechainicio(calendar.getTime());
					}else {
						calendar.add(calendar.DAY_OF_YEAR,1);
						periodo.setFechainicio(calendar.getTime());
					}
					calendar.add(calendar.DAY_OF_YEAR,periodoMedida.getDiasPeriodo()-1);
					periodo.setFechafin(calendar.getTime());
					periodo.setValtotplanif(new BigDecimal(0));				
					periodo.setObra(obra);
					diasRestantes = diasRestantes - (periodoMedida.getDiasPeriodo());
					periodo.GenerarId(obra, i);
					Periodo val = servicePeriodo.buscarPorObraFecha(obra.getId(), periodo.getFechainicio());
					if (val!=null) {
						periodo.setId(val.getId());
					}
					servicePeriodo.guardar(periodo);	
					itero = true;
				
			}
				
			if (diasRestantes>0){
					Periodo periodo = new Periodo();
					calendar.add(calendar.DAY_OF_YEAR,1);
					periodo.setFechainicio(calendar.getTime());
					periodo.setFechafin(obra.getDatefecfinobra());
					periodo.setValtotplanif(new BigDecimal(0));
					periodo.setObra(obra);
					periodo.GenerarId(obra, i);
					Periodo val = servicePeriodo.buscarPorObraFecha(obra.getId(), periodo.getFechainicio());
					if (val!=null) {
						periodo.setId(val.getId());
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
		};
		
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
			long DiasPeriodo = 0;
			double porcionDiasPeriodo = 0;
			BigDecimal acumCantidad = new BigDecimal(0);
			BigDecimal acumvalPlanif = new BigDecimal (0); 
		
			for (int i = 0; i < lstPeriodos.size(); i++) {
				double diasTotalActividad = 
						1+ ((actObra.getFechafin().getTime() - actObra.getFechainicio().getTime())
								/ 1000 / 3600 / 24);
				diarioActividad = actObra.getValortotalactividadaiu().divide(new BigDecimal(diasTotalActividad),6,RoundingMode.HALF_EVEN);

				
				
				//caso 0
				if (lstPeriodos.get(i).getFechainicio().getTime()<actObra.getFechainicio().getTime()
						&& actObra.getFechainicio().getTime()<= lstPeriodos.get(i).getFechafin().getTime()) {
					DiasPeriodo = 1 + ((lstPeriodos.get(i).getFechafin().getTime()
							-actObra.getFechainicio().getTime()) / 1000/3600/24);
//					System.out.println(DiasPeriodo);
//					System.out.println(actObra);
//					System.out.println(lstPeriodos.get(i).getId());
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
				
				porcionDiasPeriodo =  Utils.truncateDecimal((DiasPeriodo * 
						actObra.getFloatcantplanifao().doubleValue())/diasTotalActividad, 4).doubleValue();
				porcionDiasPeriodo = porcionDiasPeriodo > 0 ? porcionDiasPeriodo : 0;
				
				//buscar relacionactividadobraperiodo
				
				
				if (DiasPeriodo>0) {
					ActividadObraPeriodo actObraPer = 
							serviceActividadObraPeriodo.buscarPorPeriodoActividad(lstPeriodos.get(i).getId(),
									actObra.getOidactiviobra());
					System.out.println(DiasPeriodo);
					
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

}
