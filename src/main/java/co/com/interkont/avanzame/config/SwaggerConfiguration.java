package co.com.interkont.avanzame.config;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Lists;

import co.com.interkont.avanzame.utils.AppContext;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    /**
     * Publish a bean to generate swagger2 endpoints
     * @return a swagger configuration bean
     */
    /*@Bean
    public Docket cobraWSApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(cobraWSApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.com.interkont.wsmiobra.api"))
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .build();
    }*/

    
   private ApiInfo cobraWSApiInfo() {

        return new ApiInfoBuilder()
                .title("Microservicios Requerimientos de obra, by Interkont")
                .version("1.0")
                .build();

    }
   

	   @Bean
	   public Docket swaggerSpringfoxDocket() {
	    
	       Docket docket = new Docket(DocumentationType.SWAGGER_2)
	           .apiInfo(cobraWSApiInfo())
	           .forCodeGeneration(true)
	           .genericModelSubstitutes(ResponseEntity.class)
	           .ignoredParameterTypes(Pageable.class)
	           .ignoredParameterTypes(java.sql.Date.class)
	           .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
	           .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
	           .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
	           .securityContexts(Lists.newArrayList(securityContext()))
	           .securitySchemes(Lists.newArrayList(apiKey()))
	           .useDefaultResponseMessages(false);
	
	       docket = docket.select()
	           .build();
	      
	       return docket;
	   }
	
	
	   private ApiKey apiKey() {
	       return new ApiKey("Ikont", Constantes.HEADER_AUTHORIZACION_KEY, "header");
	   }
	
	   private SecurityContext securityContext() {
	       return SecurityContext.builder()
	           .securityReferences(defaultAuth())
	           .build();
	   }
	
	   List<SecurityReference> defaultAuth() {
	       AuthorizationScope authorizationScope
	           = new AuthorizationScope("global", "accessEverything");
	       AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	       authorizationScopes[0] = authorizationScope;
	       return Lists.newArrayList(
	           new SecurityReference("Ikont", authorizationScopes));
	   }
	   
	   @Bean(name = "appContext")
	   @Scope(scopeName = "prototype")
	   public AppContext appContext() {
	     return new AppContext();
	   } 
	   
	 
	  
}