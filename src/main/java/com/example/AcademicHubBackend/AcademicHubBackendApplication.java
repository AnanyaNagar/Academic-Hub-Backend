package com.example.AcademicHubBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@EnableSwagger2
@EnableMongoRepositories
@EnableWebMvc
public class AcademicHubBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicHubBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer  corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS").allowedOrigins("https://638529de48c655543bf793b4--academic-hub-frontend.netlify.app");
			}
		};
	}

}
