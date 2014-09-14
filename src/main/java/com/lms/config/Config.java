package com.lms.config;  
  
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
  
@Configuration //Marks this class as configuration
@PropertySource({ "classpath:freemarker.properties" })
//Specifies which package to scan
@ComponentScan("com.lms")
//Enables Spring's annotations 
@EnableWebMvc   
public class Config {
    
    @Autowired
    private Environment env;
      
    @Bean  
    public UrlBasedViewResolver setupViewResolver() {  
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreemarkerConfig() {
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setFreemarkerSettings(this.freemarkerProperties());
        result.setTemplateLoaderPath("/WEB-INF/views/");
        return result;
    }
    
    private Properties freemarkerProperties() {
        return new Properties() {
           {
              setProperty("template_update_delay", env.getProperty("template_update_delay"));
              setProperty("auto_import", env.getProperty("auto_import"));
           }
        };
     }

}
