package co.com.interkont.wscobra.api.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("DTO-RESPONSE Datos necesarios para efectuar una alimentación")
public class DatosAlimentacionResponse {
	
	@ApiModelProperty(value = "Si (100 - 100 * porcentajeAvance / porcentajeProyectado) > limitePorcentajeAtraso  entonces está atrasado")
    private Double limitePorcentajeAtraso;
	@ApiModelProperty(value = "Listado de periodos")
	private List<PeriodoResponse> periodos;
	@ApiModelProperty(value = "Listado de actividades")
	private List<ActividadResponse> actividades;
	@ApiModelProperty(value = "Listado de indicadores de alcance")
	private List<IndicadorAlcanceResponse> indicadoresAlcance;
	@ApiModelProperty(value = "Listado de aspectos a evaluar en el avance cualitativo")
	private List<AspectoEvaluarResponse> apectosEvaluar;
	@ApiModelProperty(value = "Listado de tipos de factores de atraso")
	private List<TipoFactorAtrasoResponse> tiposFactorAtraso;
	@ApiModelProperty(value = "Listado de factores de atraso")
	private List<FactorAtrasoResponse> factoresAtraso;
	
	public void mock() {
		limitePorcentajeAtraso=7.0;
		periodos= new ArrayList<>();
		Date fechaIni1 = new Date();
		periodos.add(new PeriodoResponse(1, "14.Feb.19", "13.Mar.19",50.0));
		periodos.add(new PeriodoResponse(2, "14.Mar.19", "13.Abr.19", 100.0));
		actividades= new ArrayList<>();
//		actividades.add(new ActividadResponse(1l, "Actividad 1", "Ml", BigDecimal.valueOf(1000000L), 10.0, 3.0, BigDecimal.valueOf(10000000L), BigDecimal.valueOf(3000000L), 30.0));
//		actividades.add(new ActividadResponse(2l, "Actividad 2", "Ml", BigDecimal.valueOf(1000000L), 10.0, 3.0, BigDecimal.valueOf(10000000L), BigDecimal.valueOf(3000000L), 30.0));
//		actividades.add(new ActividadResponse(3l, "Actividad 3", "Ml", BigDecimal.valueOf(1000000L), 10.0, 3.0, BigDecimal.valueOf(10000000L), BigDecimal.valueOf(3000000L), 30.0));
		
		indicadoresAlcance = new ArrayList<>();
		indicadoresAlcance.add(new IndicadorAlcanceResponse(1, "Indicador Alcance 1", "Ml", 10.0, 3.0, 30.0));
		indicadoresAlcance.add(new IndicadorAlcanceResponse(2, "Indicador Alcance 2", "Ml", 10.0, 3.0, 30.0));
		indicadoresAlcance.add(new IndicadorAlcanceResponse(3, "Indicador Alcance 3", "Ml", 10.0, 3.0, 30.0));
		
		apectosEvaluar= new ArrayList<>();
		apectosEvaluar.add(new AspectoEvaluarResponse(1, "Administrativo"));
		apectosEvaluar.add(new AspectoEvaluarResponse(2, "Financiero"));
		apectosEvaluar.add(new AspectoEvaluarResponse(3, "Técnico"));
		apectosEvaluar.add(new AspectoEvaluarResponse(4, "Jurídico"));
		apectosEvaluar.add(new AspectoEvaluarResponse(5, "Social"));
		tiposFactorAtraso= new ArrayList<>();
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(1, "Administrativos"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(2, "Ambientales"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(3, "Contratación"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(4, "Entregables"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(5, "Estudios y diseños"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(6, "Financieros"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(7, "Fraude y corrupción"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(8, "Información"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(9, "Mano de obra, materiales y equipos"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(10, "Políticos y legales"));
		tiposFactorAtraso.add(new TipoFactorAtrasoResponse(11, "Sociales"));
		
		factoresAtraso= new ArrayList<>();
		factoresAtraso.add(new FactorAtrasoResponse(1, "PERMISOS", 1));
		factoresAtraso.add(new FactorAtrasoResponse(2, "PLANOS", 1));
		factoresAtraso.add(new FactorAtrasoResponse(3, "TRÁMITES FIDUCIA", 1));
		factoresAtraso.add(new FactorAtrasoResponse(4, "SOPORTES LIQUIDACIÓN", 1));
		factoresAtraso.add(new FactorAtrasoResponse(5, "LICENCIAS", 1));
		factoresAtraso.add(new FactorAtrasoResponse(6, "CONEXIONES SERVICIOS PÚBLICOS", 1));
		factoresAtraso.add(new FactorAtrasoResponse(7, "ATRASO DE LOS PAGOS", 1));
		factoresAtraso.add(new FactorAtrasoResponse(8, "OTROS TRÁMITES", 1));

		factoresAtraso.add(new FactorAtrasoResponse(9, "LLUVIAS", 2));
		factoresAtraso.add(new FactorAtrasoResponse(10, "VENDAVAL", 2));
		factoresAtraso.add(new FactorAtrasoResponse(11, "SEQUÍA", 2));
		factoresAtraso.add(new FactorAtrasoResponse(12, "DERRUMBE", 2));
		factoresAtraso.add(new FactorAtrasoResponse(13, "TERREMOTOS", 2));
		factoresAtraso.add(new FactorAtrasoResponse(14, "TERRENOS ADVERSOS", 2));
		factoresAtraso.add(new FactorAtrasoResponse(15, "LICENCIAS AMBIENTALES", 2));
		factoresAtraso.add(new FactorAtrasoResponse(16, "PATRIMONIO CULTURAL", 2));
		factoresAtraso.add(new FactorAtrasoResponse(17, "OTROS", 2));

		factoresAtraso.add(new FactorAtrasoResponse(18, "ELABORACIÓN DE LA PROPUESTA", 3));
		factoresAtraso.add(new FactorAtrasoResponse(19, "NO FIRMA DEL CONTRATO", 3));
		factoresAtraso.add(new FactorAtrasoResponse(20, "CONVOCATORIA DESIERTA", 3));
		factoresAtraso.add(new FactorAtrasoResponse(21, "INCUMPLIMIENTO DE REQUISITOS", 3));

		factoresAtraso.add(new FactorAtrasoResponse(22, "MODIFICACIÓN DEL ALCANCE", 4));
		factoresAtraso.add(new FactorAtrasoResponse(23, "ENTREGABLES INSUFICIENTES, DEFECTUOSOS, Y/O INCOMPLETOS ", 4));

		factoresAtraso.add(new FactorAtrasoResponse(24, "RESULTADOS FASES", 5));
		factoresAtraso.add(new FactorAtrasoResponse(25, "VALIDACIONES DE LOS ESTUDIOS", 5));
		factoresAtraso.add(new FactorAtrasoResponse(26, "DEFICIENTE PLANEACIÓN", 5));
		factoresAtraso.add(new FactorAtrasoResponse(27, "DEFICIENCIA EN LOS PLANOS", 5));
		factoresAtraso.add(new FactorAtrasoResponse(28, "ESTUDIO DE TÍTULOS DE LOS PREDIOS", 5));
		factoresAtraso.add(new FactorAtrasoResponse(29, "CAMBIOS POR PARTE DEL CLIENTE", 5));

		factoresAtraso.add(new FactorAtrasoResponse(30, "EMBARGOS", 6));
		factoresAtraso.add(new FactorAtrasoResponse(31, "MULTAS", 6));
		factoresAtraso.add(new FactorAtrasoResponse(32, "SANCIONES", 6));
		factoresAtraso.add(new FactorAtrasoResponse(33, "DEFICIENCIAS EN EL PRESUPUESTO", 6));
		factoresAtraso.add(new FactorAtrasoResponse(34, "ALZA INESPERADA DE INSUMOS NO REGULADOS", 6));
		factoresAtraso.add(new FactorAtrasoResponse(35, "INSOLVENCIA", 6));
		factoresAtraso.add(new FactorAtrasoResponse(36, "NUEVOS TRIBUTOS", 6));
		factoresAtraso.add(new FactorAtrasoResponse(37, "COBERTURA CAMBIARIA", 6));

		factoresAtraso.add(new FactorAtrasoResponse(38, "CORRUPCIÓN", 7));
		factoresAtraso.add(new FactorAtrasoResponse(39, "GRUPOS AL MARGEN DE LA LEY", 7));

		factoresAtraso.add(new FactorAtrasoResponse(40, "DIFICULTAD EN EL ACCESO A LAS FUENTES DE INFORMACIÓN", 8));
		factoresAtraso.add(new FactorAtrasoResponse(41, "PERDIDA DE INFORMACIÓN", 8));
		factoresAtraso.add(new FactorAtrasoResponse(42, "UTILIZACIÓN INDEBIDA DE LA INFORMACIÓN", 8));

		factoresAtraso.add(new FactorAtrasoResponse(43, "ACCIDENTES LABORALES", 9));
		factoresAtraso.add(new FactorAtrasoResponse(44, "AUSENTISMO", 9));
		factoresAtraso.add(new FactorAtrasoResponse(45, "FALTA PERSONAL CALIFICADO", 9));
		factoresAtraso.add(new FactorAtrasoResponse(46, "MUERTE DEL CONTRATISTA", 9));
		factoresAtraso.add(new FactorAtrasoResponse(47, "ESCASEZ DE INSUMOS Y DEFICIENTE MAQUINARIA", 9));

		factoresAtraso.add(new FactorAtrasoResponse(48, "DEFINICIONES MUNICIPIO", 10));
		factoresAtraso.add(new FactorAtrasoResponse(49, "RECIBIDO MUNICIPIO", 10));
		factoresAtraso.add(new FactorAtrasoResponse(50, "DEMANDAS", 10));
		factoresAtraso.add(new FactorAtrasoResponse(51, "CAMBIOS NORMATIVOS", 10));
		factoresAtraso.add(new FactorAtrasoResponse(52, "TRÁMITES LEGALES", 10));
		factoresAtraso.add(new FactorAtrasoResponse(53, "PRIORIZACIÓN DE OTROS PROYECTOS", 10));
		factoresAtraso.add(new FactorAtrasoResponse(54, "COORDINACIÓN INTERINSTITUCIONAL", 10));
		factoresAtraso.add(new FactorAtrasoResponse(55, "APREMIOS", 10));
		factoresAtraso.add(new FactorAtrasoResponse(56, "CESIONES", 10));

		factoresAtraso.add(new FactorAtrasoResponse(57, "PAROS", 11));
		factoresAtraso.add(new FactorAtrasoResponse(58, "ORDEN PÚBLICO", 11));
		factoresAtraso.add(new FactorAtrasoResponse(59, "CONSULTA POPULAR", 11));
		factoresAtraso.add(new FactorAtrasoResponse(60, "JUNTAS DE ACCIÓN COMUNAL", 11));
		factoresAtraso.add(new FactorAtrasoResponse(61, "FALTA DE SOCIALIZACIÓN", 11));
		factoresAtraso.add(new FactorAtrasoResponse(62, "COMUNIDADES INDÍGENAS", 11));
		factoresAtraso.add(new FactorAtrasoResponse(63, "ACTAS DE VECINDAD", 11));
		factoresAtraso.add(new FactorAtrasoResponse(64, "PETICIONES, QUEJAS, RECLAMOS Y DERECHOS DE PETICIÓN INTERPUESTOS POR LA COMUNIDAD", 11));
		factoresAtraso.add(new FactorAtrasoResponse(65, "REASENTAMIENTO TEMPORAL O PERMANENTE ", 11));
		factoresAtraso.add(new FactorAtrasoResponse(66, "DAÑOS A TERCEROS", 11));
	}
	
	public DatosAlimentacionResponse() {
		mock();
	}


	public Double getLimitePorcentajeAtraso() {
		return limitePorcentajeAtraso;
	}


	public void setLimitePorcentajeAtraso(Double limitePorcentajeAtraso) {
		this.limitePorcentajeAtraso = limitePorcentajeAtraso;
	}

	public List<PeriodoResponse> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<PeriodoResponse> periodos) {
		this.periodos = periodos;
	}

	public List<ActividadResponse> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadResponse> actividades) {
		this.actividades = actividades;
	}

	public List<IndicadorAlcanceResponse> getIndicadoresAlcance() {
		return indicadoresAlcance;
	}

	public void setIndicadoresAlcance(List<IndicadorAlcanceResponse> indicadoresAlcance) {
		this.indicadoresAlcance = indicadoresAlcance;
	}

	public List<AspectoEvaluarResponse> getApectosEvaluar() {
		return apectosEvaluar;
	}

	public void setApectosEvaluar(List<AspectoEvaluarResponse> apectosEvaluar) {
		this.apectosEvaluar = apectosEvaluar;
	}

	public List<TipoFactorAtrasoResponse> getTiposFactorAtraso() {
		return tiposFactorAtraso;
	}

	public void setTiposFactorAtraso(List<TipoFactorAtrasoResponse> tiposFactorAtraso) {
		this.tiposFactorAtraso = tiposFactorAtraso;
	}

	public List<FactorAtrasoResponse> getFactoresAtraso() {
		return factoresAtraso;
	}

	public void setFactoresAtraso(List<FactorAtrasoResponse> factoresAtraso) {
		this.factoresAtraso = factoresAtraso;
	}
	
	
	
}
