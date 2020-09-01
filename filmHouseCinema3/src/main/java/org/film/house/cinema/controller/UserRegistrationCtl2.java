package org.film.house.cinema.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.bean.RoleBean2;
import org.film.house.cinema.bean.UserBean2;
import org.film.house.cinema.controller.OTBView2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.exception.DuplicateRecordException2;
import org.film.house.cinema.model.UserModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.DataValidator2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;




/**
 * Servlet implementation class UserRegistrationCtl2
 */
@WebServlet(name = "UserRegistrationCtl2", urlPatterns = { "/UserRegistrationCtl2" })
public class UserRegistrationCtl2 extends BaseCtl2 {
	public static final String OP_SIGN_UP = "SignUp";

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		//log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
	

		if (DataValidator2.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader2.getValue("error.require", "First Name"));
			pass = false;
		} 
		if (DataValidator2.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader2.getValue("error.require", "Last Name"));
			pass = false;
		} 

		if (DataValidator2.isNull(login)) {
			request.setAttribute("login",
					PropertyReader2.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator2.isEmail(request.getParameter("login"))) {
			request.setAttribute("login",
					PropertyReader2.getValue("error.email", "Login"));
			pass = false;
		}
		if (DataValidator2.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader2.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		
		

		if (DataValidator2.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader2.getValue("error.require", "Password"));
			pass = false;

		} else if (!DataValidator2.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader2.getValue("error.password", "Password"));
			return false;
		}else if (!DataValidator2.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader2.getValue("error.password", "Password"));
			return false;
		}

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			/*ServletUtility.setErrorMessage("Confirm Password did not match",
					request);*/
			request.setAttribute("confirmPassword", PropertyReader2.getValue("error.confirmPassword","Confirm Password"));
			pass = false;
		}
		
		if (DataValidator2.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader2.getValue("error.require","Mobile No"));
			pass = false;
		}else if(!DataValidator2.isPhoneNo(request.getParameter("mobile"))){
			request.setAttribute("mobile", PropertyReader2.getValue("error.invalid","Mobile No"));
			pass=false;
		} 
			//log.debug("UserRegistrationCtl Method validate Ended");
		return pass;
	}
    
	@Override
	protected BaseBean2 populateBean(HttpServletRequest request) {
		//log.debug("UserRegistrationCtl Method populatebean Started");

		UserBean2 bean = new UserBean2();

		bean.setId(DataUtility2.getLong(request.getParameter("id")));

		bean.setRoleId(RoleBean2.CUSTOMER);

		bean.setFirstName(DataUtility2.getString(request
				.getParameter("firstName")));

		bean.setLastName(DataUtility2.getString(request.getParameter("lastName")));

			bean.setLogin(DataUtility2.getString(request.getParameter("login")));
	
			bean.setPassword(DataUtility2.getString(request.getParameter("password")));
	
			bean.setConfirmPassword(DataUtility2.getString(request
					.getParameter("confirmPassword")));
	
	
			
			bean.setMobileNo(DataUtility2.getString(request.getParameter("mobile")));
			
			populateDTO(bean, request);
	
			//log.debug("UserRegistrationCtl Method populatebean Ended");
	
			return bean;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//log.debug("UserRegistrationCtl Method doGet Started");
		ServletUtility2.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in post method");
		//log.debug("UserRegistrationCtl Method doPost Started");
	
		String op = DataUtility2.getString(request.getParameter("operation"));
		// get model
		UserModel2 model = new UserModel2();
		
		long id = DataUtility2.getLong(request.getParameter("id"));
		
		
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			
			UserBean2 bean = (UserBean2) populateBean(request);
			try {
			//	System.out.println("in try sign up");
				long pk = model.registerUser(bean);
				//System.out.println("register");
				bean.setId(pk);
			
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility2.setSuccessMessage("User Successfully Registered", request);
				ServletUtility2.forward(OTBView2.USER_REGISTRATION_VIEW, request, response);
				return;
			} catch (DuplicateRecordException2 e) {
				//log.error(e);
				ServletUtility2.setBean(bean, request);
				ServletUtility2.setErrorMessage("Login id already exists",
						request);
				ServletUtility2.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility2.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility2.redirect(OTBView2.USER_REGISTRATION_CTL, request, response);
			return;
		}
		//log.debug("UserRegistrationCtl Method doPost Ended");
	}
	

	@Override
		protected String getView() {
			return OTBView2.USER_REGISTRATION_VIEW;
		}
	
}
