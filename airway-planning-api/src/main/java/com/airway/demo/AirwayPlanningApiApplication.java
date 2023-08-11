package com.airway.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

@SpringBootApplication
public class AirwayPlanningApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirwayPlanningApiApplication.class, args);
	}

  @Bean
  public Mapper mapper() {
    return DozerBeanMapperBuilder.buildDefault();
  }

}
