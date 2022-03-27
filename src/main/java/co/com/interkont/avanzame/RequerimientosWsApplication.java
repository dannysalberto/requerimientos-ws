package co.com.interkont.avanzame;

import java.io.IOException;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import co.com.interkont.avanzame.config.SwaggerConfiguration;
import co.com.interkont.avanzame.filters.JWTFilters;
import co.com.interkont.avanzame.utils.AppContext;

@Configuration
@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableJpaAuditing
public class RequerimientosWsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(RequerimientosWsApplication.class, args);
	}
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	   	
           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }
    @Bean
    @Primary
    public JWTFilters tenantFilter() {
       return new JWTFilters();
    }
  
    @Bean(destroyMethod = "destroy")
    public ThreadLocalTargetSource threadLocalAppContext() {
        ThreadLocalTargetSource result = new ThreadLocalTargetSource();
        result.setTargetBeanName("appContext");
        return result;
    }
  
    @Primary
    @Bean(name = "proxiedThreadLocalTargetSource")
    public ProxyFactoryBean proxiedThreadLocalTargetSource(ThreadLocalTargetSource threadLocalTargetSource) {
      ProxyFactoryBean result = new ProxyFactoryBean();
      result.setTargetSource(threadLocalTargetSource);
      return result;
    }

    @Bean(name = "appContext")
    @Scope(scopeName = "prototype")
    public AppContext appContext() {
        return new AppContext();
    }     

    

}
