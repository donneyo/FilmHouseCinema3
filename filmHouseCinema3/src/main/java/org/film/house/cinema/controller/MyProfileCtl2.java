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
import org.film.house.cinema.exception.DuplicateRecordException2;
import org.film.house.cinema.model.UserModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.DataValidator2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;



/**
 * Servlet implementation class MyProfileCtl2
 */

@WebServlet(name="MyProfileCtl2",urlPatterns={"/ctl/MyProfileCtl2"})
public class MyProfileCtl2 extends BaseCtl2 {
	private static final long serialVersionUID = 1L;
	
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
    public static final String OP_CHANGE_MY_PASSWORD="ChangePassword";   
	 private static Logger log=Logger.getLogger(MyProfileCtl2.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }

    
 @Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("MyProfileCtl Method validate Started");
	
		boolean pass=true;
		
		String op=DataUtility2.getString(request.getParameter("operation"));
		
		if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)||op==null){
			return pass;
		}
		
		
				if(DataValidator2.isNull(request.getParameter("login"))){			
		request.setAttribute("login", PropertyReader2.getValue("error.require","Login ID"));
		pass=false;
		}
		if(DataValidator2.isNull(request.getParameter("firstName"))){
			System.out.println("firstName"+request.getParameter("firstName"));
		request.setAttribute("firstName", PropertyReader2.getValue("error.require","First Name"));
		pass=false;
		}
		
		if (DataValidator2.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader2.getValue("error.require","Last Name"));
			pass = false;
		}
		
		 if (DataValidator2.isNull(request.getParameter("mobile"))) {
	            request.setAttribute("mobile",PropertyReader2.getValue("error.require", "Mobile No"));
	            pass = false;
	        }else if(!DataValidator2.isPhoneNo(request.getParameter("mobile"))){
				request.setAttribute("mobile", PropertyReader2.getValue("error.invalid","Mobile No"));
				pass=false;		
			}		
	
		log.debug("MyProfileCtl Method validate Ended");
		return pass;
	}
    
    
 	@Override
	protected BaseBean2 populateBean(HttpServletRequest request) {
	log.debug("MyProfileCtl Method PopulateBean Started ");
		UserBean2 bean=new UserBean2();
		
		bean.setId(DataUtility2.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility2.getString(request.getParameter("login")));
		bean.setFirstName(DataUtility2.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility2.getString(request.getParameter("lastName")));
		bean.setMobileNo(DataUtility2.getString(request.getParameter("mobile")));
		
		populateDTO(bean, request);
	
		log.debug("MyProfileCtl Method PopulateBean End ");
		return bean;
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MyProfileCTl Method doGet Started");
		
		HttpSession session=request.getSession(true);
		
		UserBean2 userBean=(UserBean2) session.getAttribute("user");
		
		long id=userBean.getId();
		
		String op=DataUtility2.getString(request.getParameter("operation"));
		//get Model
		
		UserModel2 model=new UserModel2();
		
		
		if(id>0||op !=null){
			System.out.println("in id>0 condition");
			UserBean2 bean;
			try{
				bean=model.findByPK(id);
				ServletUtility2.setBean(bean, request);
				
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility2.handleException(e, request, response);
				return;
			}
		}
		ServletUtility2.forward(getView(), request, response);
		 log.debug("MyProfileCtl Method doGet Ended");
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.debug("MyprofileCtl Method doPost Started");
		
		HttpSession session=request.getSession(true);
		
		UserBean2 userBean=(UserBean2) session.getAttribute("user");
		
		long id=userBean.getId();
		
		String op=DataUtility2.getString(request.getParameter("operation"));
		 // get model
		UserModel2 model=new UserModel2();
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			UserBean2 bean=(UserBean2) populateBean(request);
			try{
				if(id>0){
					userBean.setFirstName(bean.getFirstName());
					userBean.setLastName(bean.getLastName());
					userBean.setMobileNo(bean.getMobileNo());
		
					model.update(userBean);
					
					ServletUtility2.setBean(bean, request);
					ServletUtility2.setSuccessMessage("Profile has been updated Successfully. ", request);
				}
				
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility2.handleException(e, request, response);
				return;
			}catch(DuplicateRecordException2 e){
				ServletUtility2.setBean(bean, request);
				ServletUtility2.setErrorMessage("Login id already exists", request);
			}
		}
		else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {
			ServletUtility2.redirect(OTBView2.CHANGE_PASSWORD_CTL, request, response);
		return;
		}
		ServletUtility2.forward(getView(), request, response);
		log.debug("MyProfileCtl doPost method end");
	}
	
	@Override
	protected String getView() {

		
		return OTBView2.MY_PROFILE_VIEW;
	}

}
