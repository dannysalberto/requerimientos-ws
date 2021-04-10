package co.com.interkont.wsmiobra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.config.SwaggerConfiguration;

@Configuration
@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableJpaAuditing
public class CobraWsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CobraWsApplication.class, args);
		/*
		Properties properties= new Properties();
		properties.load(new FileInputStream(new File(Constantes.URLPROPERTIES)));
		String ruta = (String) properties.get(Constantes.FIELD_FILE_CRONOGRAMA);
		String rutaGaleria = (String) properties.get(Constantes.FIELD_FILE_GALERIA);
		String rutaDestino = ruta+Constantes.TEMPORAL_EXTENSION;
		String rutaDestinoGaleria = rutaGaleria+Constantes.TEMPORAL_EXTENSION;
		String urlEndpoint = (String) properties.get(Constantes.FIELD_ENDPOINT);;
		
		File archivo = new File(ruta);
		File archivoDestino = new File(rutaDestino);
		BufferedWriter bw;
		BufferedReader br;
		boolean editedLine = false;
		String linea = null;
		if(archivo.exists()) {

			br = new BufferedReader(new FileReader(archivo));
			bw = new BufferedWriter(new FileWriter(archivoDestino));
			
            linea = br.readLine();
			while((linea)!=null) {
				
	            System.out.println(linea);
				if (linea.indexOf("ENDPOINT")>=0 && !editedLine){
					linea = "var ENDPOINT ='"+urlEndpoint+"';";
		            System.out.println(linea);
		            editedLine = true;
				};
				bw.write(linea);
			    bw.newLine();
				linea = br.readLine();
			}
	        bw.close();
	        br.close();
	        FileUtils.forceDelete(archivo);
	        archivoDestino.renameTo(archivo);
		} else {
			System.out.println(Constantes.FILE_NOT_EXIST_CRONOGRAMA);
		}
        
		
		//GALERIA DE VIDEOS
		File archivoGaleria = new File(rutaGaleria);
		File archivoDestinoGaleria = new File(rutaDestinoGaleria);
		BufferedWriter bwGaleria;
		BufferedReader brGaleria;
		editedLine = false;
		linea = null;
		if(archivo.exists()) {

			brGaleria = new BufferedReader(new FileReader(archivoGaleria));
			bwGaleria = new BufferedWriter(new FileWriter(archivoDestinoGaleria));
			
            linea = brGaleria.readLine();
			while((linea)!=null) {
				
	            System.out.println(linea);
				if (linea.indexOf("ENDPOINT")>=0 && !editedLine){
					linea = "var ENDPOINT ='"+urlEndpoint+"';";
		            System.out.println(linea);
		            editedLine = true;
				};
				bwGaleria.write(linea);
			    bwGaleria.newLine();
				linea = brGaleria.readLine();
			}
	        bwGaleria.close();
	        brGaleria.close();
	        FileUtils.forceDelete(archivoGaleria);
	        archivoDestinoGaleria.renameTo(archivoGaleria);
		} else {
			System.out.println(Constantes.FILE_NOT_EXIST_GALERIA);
		}

		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
	    filterProvider.addFilter("contratoEjecucion",
	              SimpleBeanPropertyFilter.filterOutAllExcept("name", "phone"));

	    ObjectMapper om = new ObjectMapper();
	    om.setFilterProvider(filterProvider);
		 */
	}
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	   	
           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }

    

}
