package com.wit.challenge.calculadora.rest.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.access.servlet.TeeFilter;

/**
 * Filtro de requisições de acesso
 * 
 * @author phsg5
 *
 */
@Configuration
public class HttpLoggingConfig {

	@Bean
	public FilterRegistrationBean<TeeFilter> requestLoggingFilter() {
		FilterRegistrationBean<TeeFilter> filterRegistrationBean = new FilterRegistrationBean<TeeFilter>(
				new TeeFilter());
		return filterRegistrationBean;
	}

}
