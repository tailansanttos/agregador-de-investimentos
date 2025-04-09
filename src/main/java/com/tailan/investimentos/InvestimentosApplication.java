package com.tailan.investimentos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Agregador de Investimentos", version = "1.0", description = "API para usuários investirem."))
public class InvestimentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestimentosApplication.class, args);
	}

}
