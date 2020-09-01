<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.film.house.cinema.controller.*"%>
<%@page import="org.film.house.cinema.bean.UserBean2"%>
<%@page import="org.film.house.cinema.bean.MovieBean2"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Film House Cinema</title>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	<link rel="stylesheet" href="/filmHouseCinema3/css/style.css">
<link rel="stylesheet" href="/filmHouseCinema3/js/main.js">
	<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">

</head>

<body class="body">

	<%
    UserBean2 userBean = (UserBean2) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>

	<header>
		<nav>
			<div class="menu-icons">
				<i class="icon ion-md-menu"></i>
				<i class="icon ion-md-close"></i>
			</div>

			<label class="logo"><a href="<%=OTBView2.WELCOME_CTL%>">Film House Cinema</a></label>
			<ul class="nav-list" id="nav-item">
				<li class="nav-item">
					<a  href="<%=OTBView2.WELCOME_CTL%>">Home</a>
				</li>

				<%if(userLoggedIn){ %>

				<%if(userBean.getRoleId()==1){%>
				<li class="nav-item"><a href="<%=OTBView2.MOVIE_CTL%>">Add Movie</a></li>
				<li class="nav-item"><a href="<%=OTBView2.MOVIE_LIST_CTL%>">Movies</a></li>
				<li class="nav-item"><a href="<%=OTBView2.BOOK_LIST_CTL%>">Book List</a></li>

				<%}else if(userBean.getRoleId()==2){ %>

				<li class="nav-item"><a href="<%=OTBView2.MOVIE_LIST_CTL%>">Movie List</a></li>
				<li class="nav-item"><a href="<%=OTBView2.BOOK_LIST_CTL%>">Book List</a></li>

				<%} %>


				<li class="nav-item">
					<a href="" class=""><%=welcomeMsg%>
						<i class="icon ion-md-arrow-dropdown"></i>
					</a>
					<ul class="sub-menu">
						<li class="nav-item"><a href="<%=OTBView2.MY_PROFILE_CTL%>">My Profile</a></li>
						<li class="nav-item"><a href="<%=OTBView2.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
						<li class="nav-item"><a href="<%=OTBView2.LOGIN_CTL%>?operation=<%=LoginCtl2.OP_LOG_OUT%>">LogOut</a></li>

					</ul>
				</li>



				<%}else{ %>
				<li class="nav-item"><a href="<%=OTBView2.LOGIN_CTL%>">Sign In</a></li>
				<li class="nav-item"><a href="<%=OTBView2.USER_REGISTRATION_CTL%>">Sign Up</a></li>
				<li class="nav-item"><a href=""><%=welcomeMsg%></a></li>
				<%} %>
			</ul>
		</nav>
	</header>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/main.js"></script>

</body>

</html>