package co.com.interkont.wsmiobra.config;

public class Constantes {
	
	
	// passwordEncoder
	
	public static final String NO_EXISTE = "No existe el dato";
	public static final String FALTAN_ATRIBUTOS = "Atributos incompletos o formato incorrecto";
	
	// Spring Security	
	public static final String ELIMINADO = "Registro eliminado";
	
	public static final String NO_MUNICIPIO = "No hay datos suficientes para obtener precio de la actividad";
	public static final String SIN_PRECIO = "No se encontro precio para la actividad";
	
	public static final String OBRA_INCORRECTA = "Obra Inválida";
	public static final String ELEMENTO_INCORRECTO = "Elemento Inválido";
	
	public static final String ACTIVIDAD_INCORRECTO = "Actividad Inválida";
	

	public static final String NO_EXISTE_ACTIVIDAD = "No se encontraron registros para actualizar.";
	public static final String ACTIVIDAD_ACTUALIZADA = "Se han actualizado correctamente: ";
	public static final String PLANIFICACION_COMPLETADA = "Planificación completada exitosamente ";
	
	public static final Integer DISTRIBUCION_ACTIVIDADES_PERIODO_V1= 1;
	public static final Integer DISTRIBUCION_ACTIVIDADES_PERIODO_V2= 2;
	public static final String DISTRIBUCION_ACTIVIDADES_PERIODO_VERSION_INCORRECTA= "La versión de cálculo o distribución de actividades por período es inválida";
	public static final String MIGRATION_OK = "Migración concluida correctamente";
	public static final String MODIFICACION_INICIADA_OK = "Modificación iniciada correctamente";
	public static final String MODIFICACION_INICIADA_NOTFOUND = "La obra no se encuentra en modificación";
	
	public static final String URLPROPERTIES = "./src/main/resources/application.properties";
	public static final String TEMPORAL_EXTENSION = ".new";
	public static final String FIELD_FILE_CRONOGRAMA = "appDev.fileEditCrograma";
	public static final String FIELD_FILE_GALERIA = "appDev.fileEditGaleria";
	public static final String FIELD_ENDPOINT = "appDev.endPoint";
	public static final String FILE_NOT_EXIST_CRONOGRAMA= "El archivo cronograma.js a editar no existe";
	public static final String FILE_NOT_EXIST_GALERIA= "El archivo galeria.js a editar no existe";
	public static final String MODIFICACION_INICIADA = "I";
	public static final String MODIFICACION_FINALIZADA = "F";
	
	public static final String ACTIVIDAD_EXISTENTE= "A";
	public static final String ACTIVIDAD_MODIFICADA = "M";
	public static final String ACTIVIDAD_ELIMINADA = "D";

	public static final Integer CONTRATO_EJECUCION = 1;
	
	
	// passwordEncoder
	
	public static final String ENCODER = "Custom";
	public static final String KEY_ENCODER = "euNuWPuienBrq2t/lEnpCw3f3IpFuQRA";
	
	// Spring Security	
	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Ikont ";

	// JWT	
	public static final String SUPER_SECRET_KEY = "lPOscTr2g6KeICpZ2WQ3CmxjWBfGAHOO";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
	public static final String ISSUER_INFO = "/swagger-ui.html";	
	
	public static final Integer VIDEO_CAMARA = 1;
	public static final Integer VIDEO_COMPLEMENTARIO = 2;
	
	public static final String DATOS_INCORRECTOS = "Parámetros incorrectos";
	public static final String NO_EXISTE_OBRA = "Dato de obra inválido";
	public static final String REGISTRO_ELIMINADO = "Registro Eliminado";
}
