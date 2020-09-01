<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.film.house.cinema.util.ServletUtility2"%>
<%@page import="org.film.house.cinema.util.DataUtility2"%>
<%@page import="org.film.house.cinema.controller.MovieCtl2"%>
<%@page import="org.film.house.cinema.bean.MovieBean2"%>



<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Movie view</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	<link rel="stylesheet" href="/filmHouseCinema3/css/style.css">
<link rel="stylesheet" href="/filmHouseCinema3/js/main.js">
	<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!--<link href="https://cdn.rawgit.com/michalsnik/aos/2.1.1/dist/aos.css" rel="stylesheet">-->
<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
<!--for animation movement-->
</head>

<body class="body">

	<%@ include file="Header2.jsp"%>
	<div>
		<div class="container">
			<div class="row">
				<h3>Add Movie</h3>
				<b> <font color="red"> <%=ServletUtility2.getErrorMessage(request)%></font>
				</b> <b> <font color="green"> <%=ServletUtility2.getSuccessMessage(request)%>
				</font>
				</b>
			</div>


			<form action="<%=OTBView2.MOVIE_CTL%>" method="post"
				enctype="multipart/form-data">

				<jsp:useBean id="bean" class="org.film.house.cinema.bean.MovieBean2"
					scope="request"></jsp:useBean>

				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
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
										<label>Name</label> <input type="text" class="form-control"
											placeholder="Enter Name" name="name"
											value="<%=DataUtility2.getStringData(bean.getName())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("name", request)%></font>
									</div>
									<div class="col-md-6">
										<label>Certificate</label> <input type="text"
											class="form-control" placeholder="Enter Certificate"
											name="certificate"
											value="<%=DataUtility2.getStringData(bean.getCertificate())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("certificate", request)%>
										</font>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6">
										<label>Type</label> <input type="text" class="form-control"
											placeholder="Enter Type" name="type"
											value="<%=DataUtility2.getStringData(bean.getType())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("type", request)%></font>

									</div>
									<div class="col-md-6">
										<label>Duration</label> <input type="text"
											class="form-control" placeholder="Enter Duration"
											name="duration"
											value="<%=DataUtility2.getStringData(bean.getDuration())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("duration", request)%>
										</font>

									</div>
								</div>

								<div class="row">
									<div class="col-md-6">
										<label>Language</label> <input type="text"
											class="form-control" placeholder="Enter Language"
											name="language"
											value="<%=DataUtility2.getStringData(bean.getLanguage())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("language", request)%>
										</font>
									</div>
									<div class="col-md-6">
										<label>Director</label> <input type="text"
											class="form-control" placeholder="Enter Director"
											name="director"
											value="<%=DataUtility2.getStringData(bean.getDirector())%>">
										<font color="red"> <%=ServletUtility2.getErrorMessage("director", request)%>
										</font>
									</div>
								</div>

								<div class="long">
									<label for="fname">Cast</label>
									<textarea name="cast" class="form-control" cols="30" rows="4"
										placeholder="Enter Cast"><%=DataUtility2.getStringData(bean.getCast())%></textarea>
									<font color="red"> <%=ServletUtility2.getErrorMessage("cast", request)%></font>
								</div>

								<div class="long">
									<label for="fname">Image</label> <input type="file"
										class="form-control" placeholder="Upload  Image" name="image"
										value="<%=DataUtility2.getStringData(bean.getImage())%>">
									<font color="red"> <%=ServletUtility2.getErrorMessage("image", request)%></font>
								</div>

								<div class="long">
									<label for="fname">Price</label> <input type="text"
										class="form-control" placeholder="Enter Price" name="price"
										value="<%=(bean.getPrice() == 0) ? "" : bean.getPrice()%>">
									<font color="red"> <%=ServletUtility2.getErrorMessage("price", request)%></font>
								</div>

								<div class="long">
									<label for="fname">Description</label>
									<textarea name="description" class="form-control" cols="30"
										rows="4" placeholder="Enter Description"><%=DataUtility2.getStringData(bean.getDescription())%></textarea>
									<font color="red"> <%=ServletUtility2.getErrorMessage("description", request)%>
									</font>
								</div>


								<div id="button">
									<button type="submit" class="btn" name="operation"
										value="<%=MovieCtl2.OP_SAVE%>">Save</button>
									<button type="submit" class="btn" name="operation"
										value="<%=MovieCtl2.OP_RESET%>">Reset</button>
								</div>

							</div>
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