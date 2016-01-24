package com.astraltear.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	Calculator calculator() {
		return new AddCalculator();
	}
}
