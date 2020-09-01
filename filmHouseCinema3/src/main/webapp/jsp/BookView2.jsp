<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.film.house.cinema.controller.BookCtl2"%>
<%@page import="org.film.house.cinema.model.MovieModel2"%>
<%@page import="org.film.house.cinema.util.HTMLUtility2"%>
<%@page import="org.film.house.cinema.util.ServletUtility2"%>
<%@page import="org.film.house.cinema.util.DataUtility2"%>
<%@page import="org.film.house.cinema.bean.MovieBean2"%>
<%@page import="org.film.house.cinema.bean.MovieBean2"%>
<%@page import="org.film.house.cinema.bean.BookBean2"%>
<%@page import="org.film.house.cinema.controller.BookListCtl2"%>
<%@page import="java.util.HashMap"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book View</title>
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

	<div id="fh5co-tours">
		<div class="container">
			<div class="row">
				<div class="">
					<h3 id="small-bookview">Book</h3>
				</div>
			</div>

			<%
				long mid = (long) session.getAttribute("MoId");
				MovieModel2 mModel = new MovieModel2();
				MovieBean2 mBean = mModel.findByPK(mid);
			%>



			<div class="row">
				<div class="col-md-12 animate-box">
					<h2 class="heading-title"><%=mBean.getName()%></h2>
				</div>
				<div class="col-md-6 animate-box">
					<ul class="list-group list-group-flush">
									<li class="list-group-item"><span>(<%=mBean.getCertificate()%>),&nbsp;<%=mBean.getLanguage()%>,&nbsp;<%=mBean.getType()%>,&nbsp;<%=mBean.getDuration()%></span></li>
									<li class="list-group-item"><%=mBean.getDirector()%></li>
									<li class="list-group-item"><%=mBean.getCast()%></li>
									<li class="list-group-item">&nbsp;N<%=mBean.getPrice()%></li>
									<li class="list-group-item"><%=mBean.getDescription()%></li>
					</ul>
				</div>
				<div class="col-md-6 animate-box">
					<img class="img-responsive" src="../images/<%=mBean.getImage()%>"
						alt="travel">
				</div>
			</div>

			<form action="<%=OTBView2.BOOK_CTL%>" method="post">

				<jsp:useBean id="bean" class="org.film.house.cinema.bean.BookBean2"
					scope="request"></jsp:useBean>

				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility2.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility2.getTimestamp(bean.getModifiedDatetime())%>">

				<div class="row animate-box">

					<div class="col-md-6">
						<div class="col-md-12">
							<label>Date</label>
							<div class="form-level">
								<input type="text" class="form-control"
									placeholder="Enter Date(MM/dd/yyyy)" name="bookDate"
									value="<%=DataUtility2.getDateString(bean.getBookDate())%>">
								<font color="red"> <%=ServletUtility2.getErrorMessage("bookDate", request)%></font>
							</div>
						</div>

						<div class="col-md-12">
							<label>Show Time</label>
							<div class="form-level">
								<%
									HashMap<String, String> map = new HashMap<String, String>();
									map.put("9:00 AM To 12:00 PM", "9:00 AM To 12:00 PM");
									map.put("10:00 AM To 1:00 PM", "10:00 AM To 9:00 PM");
									map.put("12:00 PM To 3:00 PM", "12:00 PM To 3:00 PM");
									map.put("2:00 PM To 5:00 PM", "2:00 PM To 5:00 PM");
								%>
								<%=HTMLUtility2.getList("show", String.valueOf(bean.getShowTime()), map)%>
								<font color="red"> <%=ServletUtility2.getErrorMessage("show", request)%></font>
							</div>
						</div>

						<div class="col-md-12">
							<label>No Of Person</label>
							<div class="form-level">
								<input type="text" class="form-control"
									placeholder="Enter No Of Person" name="noP"
									value="<%=(bean.getNoOfPerson() == 0) ? "" : bean.getNoOfPerson()%>">
								<font color="red"> <%=ServletUtility2.getErrorMessage("noP", request)%></font>
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-level">
								<div id="button">
									<input type="submit" name="operation"
										value="<%=BookCtl2.OP_PAYMENT%>" class="btn"> <input
										type="submit" name="operation" value="<%=BookCtl2.OP_CANCEL%>"
										class="btn">
								</div>
							</div>
							<br>
						</div>
					</div>
				</div>

			</form>

		</div>
	</div>

	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script src="js/main.js"></script>
	

</body>
</html>