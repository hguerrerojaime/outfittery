package com.outfittery.gol.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by hguerrero on 3/10/17.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);


    }

    @Bean
    public GsonHttpMessageConverter httpMessageConverter() {
        return new GsonHttpMessageConverter();
    }

}
