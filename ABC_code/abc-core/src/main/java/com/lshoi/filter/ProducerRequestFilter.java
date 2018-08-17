package com.lshoi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lshoi.service.AuthenticationService;

public class ProducerRequestFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
		if(req.getParameter("token") == null) {
			res.addHeader("Access-Control-Allow-Credentials", "true");
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.addHeader("Transfer-Encoding", "chunked");
			res.addHeader("Vary", "Origin");
			res.addHeader("Vary", "Access-Control-Request-Method");
			res.addHeader("Vary", "Access-Control-Request-Headers");
			res.addHeader("Access-Control-Allow-Headers", "*");
			res.setContentType("application/json;charset=UTF-8");
			res.getWriter().write("{\"status\": \"0\", \"message\": \"not permission\"}");
			return;
		}
		if(AuthenticationService.getInstance().checkIsProducer(req.getParameter("token"))) {
			chain.doFilter(request, response);
		}
		else {
			res.addHeader("Access-Control-Allow-Credentials", "true");
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.addHeader("Access-Control-Allow-Headers", "*");
			res.addHeader("Vary", "Origin");
			res.addHeader("Vary", "Access-Control-Request-Method");
			res.addHeader("Vary", "Access-Control-Request-Headers");
			res.setContentType("application/json;charset=UTF-8");
			res.getWriter().write("{\"status\": \"1\", \"message\": \"not permission\"}");
			return;
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
