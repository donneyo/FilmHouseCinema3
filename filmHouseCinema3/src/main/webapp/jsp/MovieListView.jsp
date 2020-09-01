<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.film.house.cinema.controller.*"%>
<%@page import="org.film.house.cinema.bean.MovieBean2"%>
<%@page import="org.film.house.cinema.util.ServletUtility2"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Movies List</title>
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
			<h3>Movies</h3>
			<b> <font color="red"><%=ServletUtility2.getErrorMessage(request)%></font>
			</b> <b> <font color="green"><%=ServletUtility2.getSuccessMessage(request)%></font>
			</b>

		</div>


		<form action="<%=OTBView2.MOVIE_LIST_CTL%>" method="post">

			<div class="container">
				<div class="cover-card">
					<div class="row">


						<%
							int pageNo = ServletUtility2.getPageNo(request);
							int pageSize = ServletUtility2.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;
							MovieBean2 bean = null;
							List list = ServletUtility2.getList(request);
							Iterator<MovieBean2> i = list.iterator();
							while (i.hasNext()) {
								bean = i.next();
						%>


						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="card">
								<img class="card-img-top" src="../images/<%=bean.getImage()%>"
									alt="Card image cap">
								<div class="card-body">
									<h5 class="card-title"><%=bean.getName()%></h5>
								</div>

								<ul class="list-group list-group-flush">
									<li class="list-group-item"><span>(<%=bean.getCertificate()%>),&nbsp;<%=bean.getLanguage()%>,&nbsp;<%=bean.getType()%>,&nbsp;<%=bean.getDuration()%></span></li>
									<li class="list-group-item"><%=bean.getDirector()%></li>
									<li class="list-group-item"><%=bean.getCast()%></li>
									<li class="list-group-item">&nbsp;N<%=bean.getPrice()%></li>
								</ul>
								
								
								<%if (userBean.getRoleId() == 2) { %>

										<a  href="BookCtl2?moID=<%=bean.getId()%>" id="boo">Book</a>
									 <%}else { %>
									
										<a  href="MovieCtl2?id=<%=bean.getId()%>" id="boo">Edit</a>
										
										<%}  %>
									</div>
								</div>

										
								<div class="clearfix visible-sm-block"></div>
								<%} %>



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