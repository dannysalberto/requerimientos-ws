package co.com.interkont.avanzame;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import co.com.interkont.avanzame.config.SwaggerConfiguration;

@Configuration
@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableJpaAuditing
public class CobraWsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CobraWsApplication.class, args);
	}
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	   	
           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }

    

}
