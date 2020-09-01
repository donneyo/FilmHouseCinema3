package org.film.house.cinema.controller;

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

import org.apache.log4j.Logger;

import org.film.house.cinema.util.ServletUtility2;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter(filterName="LogCtl",urlPatterns={"/ctl/*"})
public class LogFilter implements Filter {
	
	private static Logger log=Logger.getLogger(LogFilter.class);

    public LogFilter() {
        
    }

	public void destroy() {
		
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		log.debug("Log controller doFilter method start");
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(true);
	
		
		
		if (session.getAttribute("user") == null) {
			
			ServletUtility2.setErrorMessage("Your session has been expired. Please re-Login.", request);
			
			String hitUri=request.getRequestURI();
		
			req.setAttribute("uri", hitUri);			
			
			ServletUtility2.forward("/LoginCtl2", request, response);
			
		} else {
			chain.doFilter(req, resp);
			System.out.println("response Log controller");
		}
		log.debug("Log controller doFilter method end");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
