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
import org.film.house.cinema.bean.BookBean2;
import org.film.house.cinema.bean.MovieBean2;
import org.film.house.cinema.bean.UserBean2;
import org.film.house.cinema.controller.OTBView2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.exception.DuplicateRecordException2;
import org.film.house.cinema.model.BookModel2;
import org.film.house.cinema.model.MovieModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.DataValidator2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;


/**
 * Servlet implementation class BookCtl2
 */
@WebServlet(name = "BookCtl2", urlPatterns = { "/ctl/BookCtl2" })
public class BookCtl2 extends BaseCtl2 {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(BookCtl2.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("BookCtl validate method start");
		boolean pass = true;
		
		String op=DataUtility2.getString(request.getParameter("operation"));
		
		if (DataValidator2.isNull(request.getParameter("bookDate"))) {
			request.setAttribute("bookDate", PropertyReader2.getValue("error.require", "Date"));
			pass = false;
		}

		if (DataValidator2.isNull(request.getParameter("noP"))) {
			request.setAttribute("noP", PropertyReader2.getValue("error.require", "No Of Person"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("show"))) {
			request.setAttribute("show",
					PropertyReader2.getValue("error.require", "Show Time"));
			pass = false;
		}
		
		if(OP_PAYMENT_BOOK.equalsIgnoreCase(op)) {
			pass=true;
		}
		
		log.debug("MovieCtl validate method end");
		return pass;
	}
    
    
    @Override
	protected BaseBean2 populateBean(HttpServletRequest request) {
		log.debug("MovieCtl populateBean method start");
		BookBean2 bean = new BookBean2();
		bean.setId(DataUtility2.getLong(request.getParameter("id")));
		bean.setBookDate(DataUtility2.getDate(request.getParameter("bookDate")));
		bean.setShowTime(DataUtility2.getString(request.getParameter("show")));
		bean.setNoOfPerson(DataUtility2.getLong(request.getParameter("noP")));
		populateDTO(bean, request);
		log.debug("MovieCtl populateBean method end");
		return bean;
	}
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MovieCtl doGet method start");
		String op = DataUtility2.getString(request.getParameter("operation"));

		HttpSession session=request.getSession();
		
		long Mid=DataUtility2.getLong(request.getParameter("moID"));
		session.setAttribute("MoId",Mid);
		
		BookModel2 model = new BookModel2();
		long id = DataUtility2.getLong(request.getParameter("id"));
		ServletUtility2.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			BookBean2 bean;
			try {
				bean = model.findByPK(id);
				ServletUtility2.setOpration("Edit", request);
				ServletUtility2.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility2.handleException(e, request, response);
				return;
			}
		}

		ServletUtility2.forward(getView(), request, response);
		log.debug("MovieCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			log.debug("DessertCtl doPost method start");
			String op = DataUtility2.getString(request.getParameter("operation"));
			BookModel2 model = new BookModel2();
			long id = DataUtility2.getLong(request.getParameter("id"));
			HttpSession session=request.getSession();
			if (OP_PAYMENT.equalsIgnoreCase(op)) {

				BookBean2 bean = (BookBean2) populateBean(request);
				
				UserBean2 uBean=(UserBean2)session.getAttribute("user");
				long moovieId=(long)session.getAttribute("MoId");
				
				bean.setMovieId(moovieId);
				MovieModel2 mModel=new MovieModel2();
				MovieBean2 mBean;
				try {
					mBean = mModel.findByPK(moovieId);
					bean.setFinalAmount(mBean.getPrice()*bean.getNoOfPerson());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bean.setUserId(uBean.getId());
				
				session.setAttribute("BookB", bean);
				
				ServletUtility2.forward(OTBView2.PAYMENT_VIEW, request, response);
				
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				BookBean2 bean = (BookBean2) populateBean(request);
				try {
					model.delete(bean);
					ServletUtility2.redirect(OTBView2.MOVIE_LIST_CTL, request, response);
				} catch (ApplicationException e) {
					ServletUtility2.handleException(e, request, response);
					e.printStackTrace();
				}
			} else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility2.redirect(OTBView2.MOVIE_LIST_CTL, request, response);
			
			}else if(OP_PAYMENT_BOOK.equalsIgnoreCase(op)) {
				long pk=0;
				BookBean2 bBean=(BookBean2)session.getAttribute("BookB");
				try {
					 pk=model.add(bBean);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateRecordException2 e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("msg","Ticket is Successfully Booked");
				ServletUtility2.forward(OTBView2.PAYMENT_VIEW, request, response);
			} 

			ServletUtility2.forward(getView(), request, response);
			log.debug("DessertCtl doPost method end");
		}


	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView2.BOOK_VIEW;
	}

}
