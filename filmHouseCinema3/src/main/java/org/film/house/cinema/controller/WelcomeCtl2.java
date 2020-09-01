package org.film.house.cinema.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.film.house.cinema.util.ServletUtility2;



@WebServlet(name = "WelcomeCtl2", urlPatterns = { "/WelcomeCtl2" })
public class WelcomeCtl2 extends BaseCtl2 {
	private static final long serialVersionUID = 1L;
       
    
    public WelcomeCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility2.forward(OTBView2.WELCOME_VIEW, request, response);
	}

	
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView2.WELCOME_VIEW;
	}
}



	
