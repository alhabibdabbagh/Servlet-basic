package com.servlet.demo.filtre;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


@WebFilter(filterName="ServletFiltre",urlPatterns ="/greeting" )
public class ServletFiltre implements Filter {

	List<String> ticket;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
		ticket=Arrays.asList("habib","habib1");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq=(HttpServletRequest) request; // burda dikkat HttpServletRequest servletRequest degil 
		HttpServletResponse httpResp=(HttpServletResponse) response;
		
		
		String apiKey=httpReq.getHeader("x-api-key");
		
		
		if (StringUtils.isBlank(apiKey)) {
			httpResp.setContentType("text/plain;chartset=UTF-8");
			httpResp.getWriter().println("set the api key ");
			//return;
			
		}else if (!ticket.contains(apiKey)) {
			httpResp.setContentType("text/plain;chartset=UTF-8");
			httpResp.getWriter().println("the api key fail ");
			//return;
		}else {
			chain.doFilter(httpReq, httpResp);
		}
		
		
	}

	@Override
	public void destroy() {
		
		ticket.clear();
	}

}
