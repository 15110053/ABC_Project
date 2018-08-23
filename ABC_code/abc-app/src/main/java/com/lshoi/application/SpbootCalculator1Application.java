package com.lshoi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.lshoi.filter.ConsumerRequestFilter;
import com.lshoi.filter.LogedInUserRequestFilter;
import com.lshoi.filter.ProducerRequestFilter;

@SpringBootApplication
@ComponentScan("com.lshoi.controller")
@ImportResource("classpath:applicationContext.xml")
public class SpbootCalculator1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpbootCalculator1Application.class, args);
	}
	
    @Bean
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
    
	@Bean
	public FilterRegistrationBean<LogedInUserRequestFilter> logedInUserRequestFilter(){
		FilterRegistrationBean<LogedInUserRequestFilter> request 
			= new FilterRegistrationBean<LogedInUserRequestFilter>();
		request.setFilter(new LogedInUserRequestFilter());
		request.addUrlPatterns("/User/*");
		return request;
	}
	
	@Bean
	public FilterRegistrationBean<ConsumerRequestFilter> consumerRequestFilter(){
		FilterRegistrationBean<ConsumerRequestFilter> request 
			= new FilterRegistrationBean<ConsumerRequestFilter>();
		request.setFilter(new ConsumerRequestFilter());
		request.addUrlPatterns("/Cart/*", "/Order/*");
		return request;
	}
	
	@Bean
	public FilterRegistrationBean<ProducerRequestFilter> producerRequestFilter(){
		FilterRegistrationBean<ProducerRequestFilter> request 
			= new FilterRegistrationBean<ProducerRequestFilter>();
		request.setFilter(new ProducerRequestFilter());
		request.addUrlPatterns("/ModifyProduct/*", "/ChangeOrderStatus/*");
		return request;
	}
}
