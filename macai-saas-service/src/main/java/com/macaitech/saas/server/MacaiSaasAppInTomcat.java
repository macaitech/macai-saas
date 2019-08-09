/**
 * 
 */
package com.macaitech.saas.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuhui.tang
 *
 */
@Configuration
@SpringBootApplication
public class MacaiSaasAppInTomcat extends SpringBootServletInitializer {
		
	private static Logger logger = LoggerFactory.getLogger(MacaiSaasAppInTomcat.class);
	
	private static boolean resetContextPath = false;
	
	@Value("${server.servlet.context-path}")
	private String servletPath;
	
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(MacaiSaasAppInTomcat.class);
		app.run(args);
		logger.info("YnPushAppInTomcat启动");
    }
    
    //for deploy to tomcat
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
    	MacaiSaasAppInTomcat.resetContextPath = true;
        return builder.sources(this.getClass());  
    }  
    
	@Bean
	@ConditionalOnBean(MacaiSaasAppInTomcat.class)
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>(){
            @Override
           public void customize(ConfigurableServletWebServerFactory factory) {
            	if(MacaiSaasAppInTomcat.resetContextPath) {
            		factory.setContextPath("");
            	}
           }
       };
    }
}
