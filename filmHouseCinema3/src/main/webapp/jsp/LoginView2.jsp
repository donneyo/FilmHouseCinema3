<%@page import="org.film.house.cinema.util.ServletUtility2"%>
<%@page import="org.film.house.cinema.controller.LoginCtl2"%>
<%@page import="org.film.house.cinema.controller.OTBView2"%>
<%@page import="org.film.house.cinema.util.DataUtility2"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Login View</title>
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
	<div>
		<div class="container">
			<div class="row">
				<h3>Login</h3>
				<b> <font color="red"> <%=ServletUtility2.getErrorMessage(request)%></font>
				</b> <b> <font color="green"> <%=ServletUtility2.getSuccessMessage(request)%>
				</font>
				</b>
			</div>

			<form action="<%=OTBView2.LOGIN_CTL%>" method="post">

				<jsp:useBean id="bean" class="org.film.house.cinema.bean.UserBean2"
					scope="request"></jsp:useBean>
				<%
					String uri = (String) request.getAttribute("uri");
				%>

				<input type="hidden" name="uri" value="<%=uri%>"> <input
					type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility2.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility2.getTimestamp(bean.getModifiedDatetime())%>">

				<div class="textfield">
					<div class="col-md-6">
						<div class="container">
							<div class="form-group">

								<div class="row">
									<div class="col-md-6">
										<label>Login Id</label> <input type="text"
											class="form-control" placeholder="Enter Login Id"
											name="login"
											value="<%=DataUtility2.getStringData(bean.getLogin())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("login", request)%></font>
									</div>
									<div class="col-md-6">
										<label>Password</label> <input type="password"
											class="form-control" placeholder="Enter Password"
											name="password"
											value="<%=DataUtility2.getStringData(bean.getPassword())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("password", request)%>
										</font>

									</div>
								</div>

								<div id="button">
									<input type="submit" class="btn" name="operation"
										value="<%=LoginCtl2.OP_SIGN_IN%>" class="btn btn-primary">
									<input type="submit" class="btn" name="operation"
										value="<%=LoginCtl2.OP_SIGN_UP%>" class="btn btn-primary">
								</div>


							</div>
						</div>

					</div>
				</div>
			</form>

		</div>
	
	</div>







	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<!-- Popper JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>

	<script src="js/main.js"></script>

</body>

</html>