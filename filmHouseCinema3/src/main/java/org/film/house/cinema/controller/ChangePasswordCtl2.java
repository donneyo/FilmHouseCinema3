package org.film.house.cinema.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.bean.UserBean2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.exception.RecordNotFoundException2;
import org.film.house.cinema.model.UserModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.DataValidator2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;





/**
 * Servlet implementation class ChangePasswordCtl2
 */
@WebServlet(name = "ChangePasswordCtl2", urlPatterns = { "/ctl/ChangePasswordCtl2" })
public class ChangePasswordCtl2 extends BaseCtl2 {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ChangePasswordCtl2.class);

	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ChangePasswordCtl  validate method start");

		boolean pass = true;

		String op = request.getParameter("operation");

		if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {

			return pass;
		}
		
		if (DataValidator2.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", PropertyReader2.getValue("error.require", "Old Password"));
			pass = false;
		}else if (!DataValidator2.isPassword(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", PropertyReader2.getValue("error.password", "Old Password"));
			return false;
		}
		if (DataValidator2.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader2.getValue("error.require", "New Password"));
			pass = false;
		} else if (!DataValidator2.isPassword(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader2.getValue("error.password", "New Password"));
			return false;
		}
		if (DataValidator2.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader2.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		if (!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility2.setErrorMessage("New and confirm passwords not matched", request);

			pass = false;
		}

		log.debug("ChangePasswordCtl  validate method end");
		return pass;
	}
    
    
    
    @Override
	protected BaseBean2 populateBean(HttpServletRequest request) {
		log.debug("ChangePasswordCtl  populateBean method start");

		UserBean2 bean = new UserBean2();
		bean.setPassword(DataUtility2.getString(request.getParameter("oldPassword")));

		bean.setConfirmPassword(DataUtility2.getString(request.getParameter("confirmPassword")));
		populateDTO(bean, request);
		log.debug("ChangePasswordCtl  populateBean method end");
		return bean;
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ChangePasswordCtl  doGet method start");
		ServletUtility2.forward(getView(), request, response);
		log.debug("ChangePasswordCtl  doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			log.debug("ChangePasswordCtl  doPost method start");

			HttpSession session = request.getSession(true);

			String op = DataUtility2.getString(request.getParameter("operation"));
			// get model
			UserModel2 model = new UserModel2();
			UserBean2 bean = (UserBean2) populateBean(request);

			UserBean2 UserBean = (UserBean2) session.getAttribute("user");

			String newPassword = request.getParameter("newPassword");
			
			long id = UserBean.getId();
			
			if (OP_SAVE.equalsIgnoreCase(op)) {
				try {
					boolean flag = model.changePassword(id, bean.getPassword(), newPassword);
			
					if (flag == true) {
					
						bean = model.findByLogin(UserBean.getLogin());
						
						session.setAttribute("user", bean);
						
						ServletUtility2.setBean(bean, request);
						ServletUtility2.setSuccessMessage("Password has been changed Successfully", request);
					}
				} catch (ApplicationException e) {

					ServletUtility2.handleException(e, request, response);
					return;

				} catch (RecordNotFoundException2 e) {
					ServletUtility2.setErrorMessage("Old Password is Invalid", request);
				}
			} else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
				ServletUtility2.redirect(OTBView2.MY_PROFILE_CTL, request, response);
				return;
			}

			ServletUtility2.forward(OTBView2.CHANGE_PASSWORD_VIEW, request, response);
			log.debug("ChangePasswordCtl  doPost method end");
		}
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView2.CHANGE_PASSWORD_VIEW;
	}



}
