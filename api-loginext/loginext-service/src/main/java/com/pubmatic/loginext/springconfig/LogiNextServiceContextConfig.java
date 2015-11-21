package com.pubmatic.loginext.springconfig;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import com.pubmatic.loginext.rest.LocationPointsResource;
import com.pubmatic.loginext.spring.LogiNextContextConfig;


/** 
 * 
 * @author Bhavesh 
 * This is the Spring Application Context loader class.
 * 
 */
@Configuration
@Import({LogiNextContextConfig.class})
@ComponentScan(basePackages={"com.pubmatic.loginext"})
public class LogiNextServiceContextConfig{
	
	/**
	 * This class imports the similar configuration class provided in in its jar project, i.e. loginext
	 */
	
	
	@ApplicationPath("/")
    public class JaxRsApiApplication extends Application { }
 
    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }
 
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer(ApplicationContext appContext) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(), JAXRSServerFactoryBean.class);
        factory.setServiceBeans(restResources(appContext));
        factory.setAddress("/" + factory.getAddress());
        factory.setProvider(jsonProvider());
        return factory.create();
    }
 
    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
 
    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
	
    public List<Object> restResources(ApplicationContext appContext){
    	Object creativeScanResource = appContext.getBean(LocationPointsResource.class);
    	
    	List<Object> restResourceList = new ArrayList<Object>();
    	restResourceList.add(creativeScanResource);
    	
    	return restResourceList;
    }
    
	
	public LogiNextServiceContextConfig(){
		super();
	}	
	
}
