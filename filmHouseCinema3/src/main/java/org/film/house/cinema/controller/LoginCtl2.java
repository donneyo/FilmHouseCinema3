package org.film.house.cinema.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.bean.RoleBean2;
import org.film.house.cinema.bean.UserBean2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.model.RoleModel2;
import org.film.house.cinema.model.UserModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.DataValidator2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;





@WebServlet (name = "LoginCtl2", urlPatterns = { "/LoginCtl2" })
public class LoginCtl2 extends BaseCtl2 {
	
	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
	public static String HIT_URI = null;
		private static Logger log = Logger.getLogger(LoginCtl2.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {

		//log.debug("LoginCtl Method validate Started");
		
		boolean pass = true;
		
		String op = request.getParameter("operation");
		
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		
		
		String login = request.getParameter("login");
		
		if (DataValidator2.isNull(login)) {
			request.setAttribute("login", PropertyReader2.getValue("error.require", "Login Id"));
			pass = false;
		
		} else if (!DataValidator2.isEmail(login)) {
			request.setAttribute("login", PropertyReader2.getValue("error.email", "Login Id "));
			pass = false;
		}
		if (DataValidator2.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader2.getValue("error.require", "Password"));
			pass = false;
		}
		//log.debug("LoginCtl Method validate Ended");
		return pass;
	}
    
    @Override
	protected BaseBean2 populateBean(HttpServletRequest request) {

		//log.debug("LoginCtl Method populateBean Started");

		UserBean2 bean = new UserBean2();
		
		bean.setId(DataUtility2.getLong(request.getParameter("id")));
		
		bean.setLogin(DataUtility2.getString(request.getParameter("login")));
		
		bean.setPassword(DataUtility2.getString(request.getParameter("password")));

		//log.debug("LOginCtl Method PopulatedBean End");

		return bean;
	}

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//log.debug("Method doGet Started");
		
		
		HttpSession session = request.getSession(true);
		String op = DataUtility2.getString(request.getParameter("operation"));
		
		UserModel2 model = new UserModel2();
		RoleModel2 role = new RoleModel2();
		
		long id = DataUtility2.getLong(request.getParameter("id"));

		if (id > 0) {
			UserBean2 userBean;
			try {
				userBean = model.findByPK(id);
				ServletUtility2.setBean(userBean, request);
		
			} catch (Exception e) {
				//log.error(e);
				ServletUtility2.handleException(e, request, response);
				return;
			}
		} else if (OP_LOG_OUT.equals(op)) {
			session = request.getSession(false);
			session.invalidate();
			ServletUtility2.setSuccessMessage("You have been logged out successfully", request);
			
			ServletUtility2.forward(OTBView2.LOGIN_VIEW, request, response);
			return;
		}
		if (session.getAttribute("user") != null) {
			ServletUtility2.redirect(OTBView2.WELCOME_CTL, request, response);
			return;
		}
		ServletUtility2.forward(getView(),request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		String op = DataUtility2.getString(request.getParameter("operation"));
		// get Model
		UserModel2 model = new UserModel2();
		RoleModel2 role = new RoleModel2();
		
		long id = DataUtility2.getLong(request.getParameter("id"));
		
		
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserBean2 bean = (UserBean2) populateBean(request);
			try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				
				if (bean != null) {
					session.setAttribute("user", bean);
					session.setMaxInactiveInterval(10 * 6000);

					long rollId = bean.getRoleId();
					RoleBean2 roleBean = role.findByPK(rollId);
					if (roleBean != null) {
						session.setAttribute("role", roleBean.getName());
					}
					// save state of session remember URL
					String uri = request.getParameter("uri");
					
					
					if (uri == null || "null".equalsIgnoreCase(uri)) {
						ServletUtility2.redirect(OTBView2.WELCOME_CTL, request, response);
						return;
					} else {
						ServletUtility2.redirect(uri, request, response);
					}
					return;
				} else {
					bean = (UserBean2) populateBean(request);
					ServletUtility2.setBean(bean, request);
					ServletUtility2.setErrorMessage("Invalid LoginId And Password", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility2.handleException(e, request, response);
				
				return;
			}
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility2.redirect(OTBView2.USER_REGISTRATION_CTL, request, response);
		return;
		}
		
		
		ServletUtility2.forward(getView(), request, response);
		//log.debug("UserCtl Method doPost Ended");
	}

	
	
	@Override
	protected String getView() {
		return OTBView2.LOGIN_VIEW;
    }


}

