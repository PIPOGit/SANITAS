package com.atsistemas.sanitas.calculadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import io.corp.calculator.TracerImpl;

@SpringBootApplication
public class CalculadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculadoraApplication.class, args);
	}

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(new ApiInfoBuilder()
            .title("My SANITAS Calculator API")
            .description("SANITAS Calculator Public REST API")
            .version("1.0.0")
            .license("MIT")
            .licenseUrl("https://opensource.org/licenses/MIT")
            .build())
        .tags(new Tag("Operaciones", "Endpoints for some mathematical operations"))
        .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .build();
  }

	@Bean
	public TracerImpl getTracer() {
		return new TracerImpl();
	}

}
