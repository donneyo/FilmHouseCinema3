package org.film.house.cinema.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.bean.UserBean2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.DataValidator2;
import org.film.house.cinema.util.ServletUtility2;


@WebServlet("/BaseCtl2")
public abstract class BaseCtl2 extends HttpServlet {

	private static final Logger log=Logger.getLogger(BaseCtl2.class);
	/**
	 * Generic message key constant
	 */
	
	
	
	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";
	public static final String OP_RESET = "Reset";
	public static final String OP_PAYMENT = "Process To PayMent";
	
	public static final String OP_PAYMENT_BOOK = "Payment and Book";

	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";

	/**
	 * Error message key constant
	 */
	public static final String MSG_ERROR = "error";


	public BaseCtl2() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return 
	 */

	protected boolean validate(HttpServletRequest request) {
		return true;
		
	}

	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */

	protected BaseBean2 populateBean(HttpServletRequest request) {
		return null;
	}

	/**
	 * Populates Generic attributes in DTO
	 * 
	 * @param dto
	 * @param request
	 * @return
	 */
	
	
	protected BaseBean2 populateDTO(BaseBean2 dto, HttpServletRequest request) {
		log.debug("BaseCtl populate DTO method start");
	
		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean2 userbean = (UserBean2) request.getSession().getAttribute("user");

		if (userbean == null) {
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {

			modifiedBy = userbean.getLogin();

			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy)|| DataValidator2.isNull(createdBy)) {
				createdBy = modifiedBy;
			}

		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility2.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility2.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility2.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility2.getCurrentTimestamp());
		
		log.debug("BaseCtl populate DTO method end");
		return dto;
	}

	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		log.debug("BaseCtl service method start");
		
		// Load the preloaded data required to display at HTML form
		preload(request);


		String op = DataUtility2.getString(request.getParameter("operation"));

		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation

		System.out.println("operation ="+op);

		if (DataValidator2.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)&& !OP_VIEW.equalsIgnoreCase(op)&& !OP_DELETE.equalsIgnoreCase(op)&&!OP_RESET.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages
			if (!validate(request)) {
				BaseBean2 bean = (BaseBean2) populateBean(request);
				ServletUtility2.setBean(bean, request);
				ServletUtility2.forward(getView(), request, response);
				return;
			}
		}
		
		log.debug("BaseCtl service method end");
		super.service(request, response);
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	protected abstract String getView();

}
