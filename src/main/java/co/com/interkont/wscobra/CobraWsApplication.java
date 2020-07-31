package co.com.interkont.wscobra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import co.com.interkont.wscobra.config.SwaggerConfiguration;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class CobraWsApplication {

	@Autowired
	public static Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(CobraWsApplication.class, args);
		

	}
	
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }

}
