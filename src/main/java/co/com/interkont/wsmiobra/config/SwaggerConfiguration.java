package co.com.interkont.wsmiobra.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    /**
     * Publish a bean to generate swagger2 endpoints
     * @return a swagger configuration bean
     */
    @Bean
    public Docket cobraWSApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(cobraWSApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.com.interkont.wsmiobra.api"))
                .build();
    }

    
   private ApiInfo cobraWSApiInfo() {

        return new ApiInfoBuilder()
                .title("Servicios Rest-MiObraWeb, by Interkont")
                .version("1.0")
                .build();

    }
 
}