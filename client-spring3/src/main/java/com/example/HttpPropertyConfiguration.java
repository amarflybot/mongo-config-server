package com.example;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by amarendra on 26/03/17.
 */
@Configuration
public class HttpPropertyConfiguration {


    @Bean
    public PropertyPlaceholderConfigurer placeholderConfigurer(){

        PropertyPlaceholderConfigurer placeholderConfigurer = new HttpPropertyPlaceholderConfigurer();

        placeholderConfigurer.setLocation(new ClassPathResource("application.properties"));

        return placeholderConfigurer;
    }
}
