package com.ms.funcionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FuncionarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuncionarioApplication.class, args);
    }
}