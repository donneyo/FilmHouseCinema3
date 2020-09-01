package org.film.house.cinema.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.bean.BookBean2;
import org.film.house.cinema.bean.UserBean2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.model.BookModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;


/**
 * Servlet implementation class BookListCtl2
 */
@WebServlet(name = "BookListCtl2", urlPatterns = { "/ctl/BookListCtl2" })
public class BookListCtl2 extends BaseCtl2 {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(BookListCtl2.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected BaseBean2 populateBean(HttpServletRequest request) {
		log.debug("BookListCtl populateBean method start");
		BookBean2 bean = new BookBean2();
		bean.setUserName(DataUtility2.getString(request.getParameter("name")));
		log.debug("BookListCtl populateBean method end");
		return bean;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			log.debug("BookListCtl doGet method start");
			List list = null;
			int pageNo = 1;
			int pageSize = DataUtility2.getInt(PropertyReader2.getValue("page.size"));

			long bid=DataUtility2.getLong(request.getParameter("Bid"));
			BookModel2 model = new BookModel2();
			BookBean2 bean = (BookBean2) populateBean(request);
			try {
				HttpSession session=request.getSession();
				UserBean2 uBean=(UserBean2)session.getAttribute("user");
				
				if(bid>0) {
					bean.setId(bid);
					model.delete(bean);
					ServletUtility2.setErrorMessage("Ticlet Is Canceled", request);
				}
				
				if(uBean.getRoleId()==2) {
					bean.setUserName(uBean.getFirstName()+" "+uBean.getLastName());
				}
				list = model.search(bean, pageNo, pageSize);
				if (list == null || list.size() == 0) {
					ServletUtility2.setErrorMessage("No Record Found", request);
				}
				ServletUtility2.setList(list, request);
				ServletUtility2.setPageNo(pageNo, request);
				ServletUtility2.setPageSize(pageSize, request);
				ServletUtility2.forward(getView(), request, response);

			} catch (ApplicationException e) {
				ServletUtility2.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
			log.debug("BookListCtl doGet method end");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			log.debug("MovieListCtl doPost method start");
			List list = null;

			int pageNo = DataUtility2.getInt(request.getParameter("pageNo"));

			int pageSize = DataUtility2.getInt(request.getParameter("pageSize"));

			pageNo = (pageNo == 0) ? 1 : pageNo;

			pageSize = (pageSize == 0) ? DataUtility2.getInt(PropertyReader2.getValue("page.size")) : pageSize;

			BookBean2 bean = (BookBean2) populateBean(request);

			BookModel2 model = new BookModel2();
			String[] ids = request.getParameterValues("ids");
			String op = DataUtility2.getString(request.getParameter("operation"));

			if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {

					pageNo = 1;

				} else if (OP_NEXT.equalsIgnoreCase(op)) {

					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility2.redirect(OTBView2.BOOK_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					BookBean2 deletebean = new BookBean2();
					for (String id : ids) {
						deletebean.setId(DataUtility2.getInt(id));
						try {
							model.delete(deletebean);
						} catch (ApplicationException e) {
							ServletUtility2.handleException(e, request, response);
							e.printStackTrace();
							return;
						}
					}
					ServletUtility2.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility2.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility2.redirect(OTBView2.BOOK_LIST_CTL, request, response);
				return;

			}

			try {
				
				HttpSession session=request.getSession();
				UserBean2 uBean=(UserBean2)session.getAttribute("user");
				if(uBean.getRoleId()==2) {
					bean.setUserName(uBean.getFirstName()+" "+uBean.getLastName());
				}

				list = model.search(bean, pageNo, pageSize);
				if (list == null || list.size() == 0) {
					ServletUtility2.setErrorMessage("NO Record Found", request);
				}
				ServletUtility2.setList(list, request);
				ServletUtility2.setPageNo(pageNo, request);
				ServletUtility2.setPageSize(pageSize, request);
				ServletUtility2.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility2.handleException(e, request, response);
				e.printStackTrace();
				return;
			}

			log.debug("MovieListCtl doPost method end");
		}
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView2.BOOK_LIST_VIEW;
	}

}
