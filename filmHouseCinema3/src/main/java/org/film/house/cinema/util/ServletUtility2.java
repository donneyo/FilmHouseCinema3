package org.film.house.cinema.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.controller.BaseCtl2;
import org.film.house.cinema.controller.OTBView2;

public class ServletUtility2 {

	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher rd = request.getRequestDispatcher(page);
		System.out.println(page);
		rd.forward(request, response);
	}
	
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl2.MSG_SUCCESS, msg);
	}
	
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}
	
	public static String getErrorMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl2.MSG_ERROR);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}
	
	public static String getSuccessMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl2.MSG_SUCCESS);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}
	
	public static String getErrorMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}
	
	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("exception", e);
		ServletUtility2.forward(OTBView2.ERROR_CTL, request, response);
		e.printStackTrace();
	}
	
	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}
	
	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}
	
	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}
	
	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	public static int getPageNo(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageNo");
	}
	
	public static int getPageSize(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageSize");
	}
	
	public static void setBean(BaseBean2 bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}
	
	public static void setErrorMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl2.MSG_ERROR, msg);
	}
	public static void setOpration(String msg, HttpServletRequest request) {
		request.setAttribute("Opration", msg);
	}

	public static String getOpration(HttpServletRequest request) {
		String val = (String) request.getAttribute("Opration");
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}
}
