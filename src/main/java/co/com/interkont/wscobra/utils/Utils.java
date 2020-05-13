package co.com.interkont.wscobra.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.apache.tomcat.util.codec.binary.Base64;

public class Utils {
	
	/**
     * Formato de tiempo utilizado para el subfijo del nombre del archivo
     */
    public static final String FORMATO_TIEMPO = "yyyyMMddHHmmss";
    
    /**
     * Formateador utilizado para el subfijo del nombre del archivo
     */
    public static final SimpleDateFormat subfijoTiempoDateFormat = new SimpleDateFormat(FORMATO_TIEMPO);
    
    /**
     * Separador del subfijo de tiempo
     */
    public static final String SEPARADOR_TIEMPO = "_";
    
    /**
     * Ruta de la carpeta del proyecto web
     */
    public static final String PATH_CARPETA_PROYECTO_WEB = "/srv/tomcat/webapps/condor/";
    
    /**
     * URL de la carpeta de imágenes de alimentacion
     */
    public static final String URL_CARPETA_OBRAS_VIGENTES = "resources/Documentos/ObrasVigentes";
    
    
    
    /**
     * Nombre de la carpeta donde sa almacenan las imágenes de alimentacion
     */
    public static final String CARPETA_IMGS_ALIMENTACION = "ImgsAlimentacion";
	
	public static String distanciaCoord(BigDecimal latitudUno, BigDecimal longitudUno, BigDecimal latitudDos, BigDecimal longitudDos) {  

		double lat1 = latitudUno.doubleValue();
		double lng1 = longitudUno.doubleValue();
		double lat2 = latitudDos.doubleValue();
		double lng2 = longitudDos.doubleValue();
		
        double radioTierra = 6371;  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
        
        int distaciaInt = (int)distancia;
        
        return Integer.toString(distaciaInt)+"Km";  
    }
	
	/**
	 * Almacena un archivo en base 64
	 * @param file String en base 64
	 * @param pathFile Ruta de ubicación del archivo
	 * @throws IOException
	 */
	public static void saveFileBase64(String file, String pathFile)
			throws IOException {
		File fileIni = new File(pathFile);
		if (!fileIni.exists()) {
			fileIni.mkdirs();
		}
		byte[] fotoPrincipal = Base64.decodeBase64(file);
		Path path = Paths.get(pathFile);
		Files.write(path, fotoPrincipal);
	}

}
