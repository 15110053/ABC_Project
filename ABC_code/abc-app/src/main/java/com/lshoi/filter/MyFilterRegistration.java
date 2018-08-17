package com.lshoi.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

public class MyFilterRegistration {
	private ConsumerRequestFilter filter;

	public ConsumerRequestFilter getFilter() {
		return filter;
	}

	public void setFilter(ConsumerRequestFilter filter) {
		this.filter = filter;
	}

	public void init() {
		System.out.println("Test");
		FilterRegistrationBean<ConsumerRequestFilter> request 
		= new FilterRegistrationBean<ConsumerRequestFilter>();
		request.setFilter(filter);
		request.addUrlPatterns("/User/*", "/Cart/*");
	}
}
