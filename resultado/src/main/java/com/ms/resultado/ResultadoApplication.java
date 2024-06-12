package com.ms.resultado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResultadoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResultadoApplication.class, args);
    }
}