package org.film.house.cinema.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.bean.MovieBean2;
import org.film.house.cinema.controller.MovieListCtl2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.model.MovieModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;


/**
 * Servlet implementation class MovieListCtl2
 */
@WebServlet(name = "MovieListCtl2", urlPatterns = { "/ctl/MovieListCtl2" })
public class MovieListCtl2 extends BaseCtl2 {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MovieListCtl2.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieListCtl2() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected BaseBean2 populateBean(HttpServletRequest request) {
		log.debug("MovieListCtl populateBean method start");
		MovieBean2 bean = new MovieBean2();
		bean.setName(DataUtility2.getString(request.getParameter("name")));
		log.debug("MovieListCtl populateBean method end");
		return bean;
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MovieListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility2.getInt(PropertyReader2.getValue("page.size"));

		MovieModel2 model = new MovieModel2();
		MovieBean2 bean = (MovieBean2) populateBean(request);
		try {
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
		log.debug("MovieListCtl doGet method end");
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

		MovieBean2 bean = (MovieBean2) populateBean(request);

		MovieModel2 model = new MovieModel2();
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
			ServletUtility2.redirect(OTBView2.MOVIE_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				MovieBean2 deletebean = new MovieBean2();
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
			ServletUtility2.redirect(OTBView2.MOVIE_LIST_CTL, request, response);
			return;

		}

		try {

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
		return OTBView2.MOVIE_LIST_VIEW;
	}


}
