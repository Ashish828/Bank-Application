package com.application.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
//@WebFilter("/user/*")
public class CustomerAuthorizationFilter implements Filter {
  
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			if (request instanceof HttpServletRequest) {
				 String url = ((HttpServletRequest)request).getRequestURL().toString();
				 if(url.endsWith("user"))chain.doFilter(request, response);
				 else {
					 HttpSession session = ((HttpServletRequest)request).getSession();
						if((Integer)session.getAttribute("customerID") != null) {
							chain.doFilter(request, response);
						}
						else {
							((HttpServletResponse)response).sendRedirect("/bank");
						}
				 }
				}
			
		}catch (Exception e) {
			((HttpServletResponse)response).sendRedirect("/bank");
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}

}
