package org.film.house.cinema.controller;

public interface OTBView2 {
	

	public String APP_CONTEXT = "/filmHouseCinema3";
	public String PAGE_FOLDER ="/jsp";
	
	public String INDEX = PAGE_FOLDER + "/index.jsp"; 
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView2.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome2.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView2.jsp";
	
	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";
	public String MOVIE_VIEW = PAGE_FOLDER + "/MovieView2.jsp";
	public String MOVIE_CTL = APP_CONTEXT + "/ctl/MovieCtl2";
	public String MOVIE_LIST_CTL = APP_CONTEXT + "/ctl/MovieListCtl2";
	public String MOVIE_LIST_VIEW = PAGE_FOLDER + "/MovieListView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView2.jsp";
	public String BOOK_VIEW = PAGE_FOLDER + "/BookView2.jsp";	
	public String PAYMENT_VIEW = PAGE_FOLDER + "/PaymentView2.jsp";
	public String BOOK_LIST_VIEW = PAGE_FOLDER + "/BookList2.jsp";
	
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl2";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl2";

	
	public String BOOK_CTL = APP_CONTEXT + "/ctl/BookCtl2";
	public String BOOK_LIST_CTL = APP_CONTEXT + "/ctl/BookListCtl2";
	public String ERROR_CTL = "/ctl/ErrorCtl";
	
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl2";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl2";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView2.jsp";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl2";

}
