package com.hms.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
public class ConfigClass {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
