package com.kick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class KickAndrushApplication {

	public static void main(String[] args) {
		SpringApplication.run(KickAndrushApplication.class, args);
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return p -> {
			p.setOneIndexedParameters(true);	// 페이지 1부터 시작 기본 default는 false
		};
	}
	
}
