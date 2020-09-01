<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.film.house.cinema.controller.BookCtl2"%>
<%@page import="org.film.house.cinema.model.MovieModel2"%>
<%@page import="org.film.house.cinema.util.HTMLUtility2"%>
<%@page import="org.film.house.cinema.util.ServletUtility2"%>
<%@page import="org.film.house.cinema.util.DataUtility2"%>
<%@page import="org.film.house.cinema.bean.MovieBean2"%>
<%@page import="org.film.house.cinema.bean.BookBean2"%>
<%@page import="org.film.house.cinema.controller.BookListCtl2"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Book List View</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<link rel="stylesheet" href="/filmHouseCinema3/css/style.css">
<link rel="stylesheet" href="/filmHouseCinema3/js/main.js">
<link
	href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
<!--for animation movement-->

</head>

<body class="body">


	<%@ include file="Header2.jsp"%>


	<div class="container">
		<div class="row">
			<h3>Book Movie List</h3>
			<b> <font color="red"><%=ServletUtility2.getErrorMessage(request)%></font>
			</b> <b> <font color="green"><%=ServletUtility2.getSuccessMessage(request)%></font>
			</b>

		</div>


		<form action="<%=OTBView2.BOOK_LIST_CTL%>" method="post">

			<div class="container">
				<div class="cover-card">
					<div class="row">


						<%
							int pageNo = ServletUtility2.getPageNo(request);
							int pageSize = ServletUtility2.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;
							BookBean2 bean = null;
							List list = ServletUtility2.getList(request);
							Iterator<BookBean2> i = list.iterator();
							while (i.hasNext()) {
								bean = i.next();
								MovieModel2 mModel = new MovieModel2();
								MovieBean2 mBean = mModel.findByPK(bean.getMovieId());
						%>


						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="card">
								<img class="card-img-top" src="../images/<%=mBean.getImage()%>"
									alt="Card image cap">
								<div class="card-body">
									<h5 class="card-title"><%=bean.getMovieName()%></h5>
								</div>

								<ul class="list-group list-group-flush">
									<li class="list-group-item">User Name:&nbsp;<%=bean.getUserName()%></li>
									<li class="list-group-item">No Of Person:&nbsp;<%=bean.getNoOfPerson()%></li>
									<li class="list-group-item"><span>(<%=mBean.getCertificate()%>),&nbsp;<%=mBean.getLanguage()%>,&nbsp;<%=mBean.getType()%>,&nbsp;<%=mBean.getDuration()%></span></li>
									<li class="list-group-item"><%=mBean.getDirector()%></li>
									<li class="list-group-item"><%=mBean.getCast()%></li>
									<li class="list-group-item">&nbsp;N<%=bean.getFinalAmount()%></li>
								</ul>
								<%
									if (userBean.getRoleId() == 2) { %>


								<a type="submit" href="BookListCtl2?Bid=<%=bean.getId()%>"
									id="boo">Cancel</a>
								<%} %>
							</div>
						</div>

						<div class="clearfix visible-sm-block"></div>
						<% } %>





					</div>
				</div>

			</div>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

		</form>
	</div>

	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script src="js/main.js"></script>
</body>

</html>