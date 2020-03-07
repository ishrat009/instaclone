package com.instagram;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.instagram.config.persistence.HibernateConfig;

@ComponentScan(basePackages = {
		"com.instagram.service",
		"com.instagram.config.persistence",
		"com.instagram.config.security" })
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	HibernateConfig hibernateConfig() {
		return new HibernateConfig();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	}
}
