package org.film.house.cinema.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import org.film.house.cinema.bean.BaseBean2;
import org.film.house.cinema.bean.MovieBean2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.exception.DuplicateRecordException2;
import org.film.house.cinema.model.MovieModel2;
import org.film.house.cinema.util.DataUtility2;
import org.film.house.cinema.util.DataValidator2;
import org.film.house.cinema.util.PropertyReader2;
import org.film.house.cinema.util.ServletUtility2;

/**
 * Servlet implementation class MovieCtl
 */

@WebServlet(name = "MovieCtl2", urlPatterns = { "/ctl/MovieCtl2" })
@MultipartConfig(maxFileSize = 16177215)
public class MovieCtl2 extends BaseCtl2 {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MovieCtl2.class);
	
       
  
    public MovieCtl2() {

    }

    
    @Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("MovieCtl validate method start");
		boolean pass = true;

		if (DataValidator2.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader2.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator2.isNull(request.getParameter("certificate"))) {
			request.setAttribute("certificate", PropertyReader2.getValue("error.require", "Certificate"));
			pass = false;
		}

		if (DataValidator2.isNull(request.getParameter("type"))) {
			request.setAttribute("type", PropertyReader2.getValue("error.require", "Type"));
			pass = false;
		}
		if (DataValidator2.isNull(request.getParameter("language"))) {
			request.setAttribute("language", PropertyReader2.getValue("error.require", "Language"));
			pass = false;
		}

		if (DataValidator2.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader2.getValue("error.require", "Duration"));
			pass = false;
		}

		if (DataValidator2.isNull(request.getParameter("director"))) {
			request.setAttribute("director", PropertyReader2.getValue("error.require", "Director"));
			pass = false;
		}

		if (DataValidator2.isNull(request.getParameter("cast"))) {
			request.setAttribute("cast", PropertyReader2.getValue("error.require", "Cast"));
			pass = false;
		}
		
		if (DataValidator2.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader2.getValue("error.require", "Price"));
			pass = false;
		}

		if (DataValidator2.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader2.getValue("error.require", "Description"));
			pass = false;
		}

		log.debug("MovieCtl validate method end");
		return pass;
	}
    
    @Override
	protected BaseBean2 populateBean(HttpServletRequest request) {
		log.debug("MovieCtl populateBean method start");
		MovieBean2 bean = new MovieBean2();
		bean.setId(DataUtility2.getLong(request.getParameter("id")));
		bean.setName(DataUtility2.getString(request.getParameter("name")));
		bean.setCertificate(DataUtility2.getString(request.getParameter("certificate")));
		bean.setType(DataUtility2.getString(request.getParameter("type")));
		bean.setDuration(DataUtility2.getString(request.getParameter("duration")));
		bean.setDirector(DataUtility2.getString(request.getParameter("director")));
		bean.setCast(DataUtility2.getString(request.getParameter("cast")));
		bean.setLanguage(DataUtility2.getString(request.getParameter("language")));
		bean.setDescription(DataUtility2.getString(request.getParameter("description")));
		bean.setPrice(DataUtility2.getLong(request.getParameter("price")));
		populateDTO(bean, request);
		log.debug("MovieCtl populateBean method end");
		return bean;
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MovieCtl doGet method start");
		String op = DataUtility2.getString(request.getParameter("operation"));

		MovieModel2 model = new MovieModel2();
		long id = DataUtility2.getLong(request.getParameter("id"));
		ServletUtility2.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			MovieBean2 bean;
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

    
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("In Do pOst================");
		log.debug("DessertCtl doPost method start");
		String op = DataUtility2.getString(request.getParameter("operation"));
		MovieModel2 model = new MovieModel2();
		long id = DataUtility2.getLong(request.getParameter("id"));

		String savePath = DataUtility2.getString(PropertyReader2.getValue("path"));

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("image");

		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);

		String filePath =fileName;

		if (OP_SAVE.equalsIgnoreCase(op)) {

			MovieBean2 bean = (MovieBean2) populateBean(request);
			bean.setImage(filePath);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility2.setOpration("Edit", request);
					ServletUtility2.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility2.setBean(bean, request);

				} else {

					long pk = model.add(bean);
					// bean.setId(id);
					ServletUtility2.setSuccessMessage("Data is successfully Saved", request);
					ServletUtility2.forward(getView(), request, response);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility2.forward(OTBView2.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException2 e) {
				ServletUtility2.setBean(bean, request);
				ServletUtility2.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			MovieBean2 bean = (MovieBean2) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility2.redirect(OTBView2.MOVIE_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility2.handleException(e, request, response);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility2.redirect(OTBView2.MOVIE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility2.redirect(OTBView2.MOVIE_CTL, request, response);
			return;
		}

		ServletUtility2.forward(getView(), request, response);
		log.debug("DessertCtl doPost method end");
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	@Override
	protected String getView() {
		return OTBView2.MOVIE_VIEW;
	}
}


