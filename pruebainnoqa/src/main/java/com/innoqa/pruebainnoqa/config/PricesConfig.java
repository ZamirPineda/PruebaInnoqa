package com.innoqa.pruebainnoqa.config;

import com.innoqa.pruebainnoqa.repository.PricesRepo;
import com.innoqa.pruebainnoqa.service.PricesController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.innoqa.pruebainnoqa.repository")
public class PricesConfig {
    //Clase de configuración para el bean del repository
}
