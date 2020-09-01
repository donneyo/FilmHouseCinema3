<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.film.house.cinema.controller.*"%>
<%@page import="org.film.house.cinema.util.DataUtility2"%>
<%@page import="org.film.house.cinema.util.ServletUtility2"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	<link rel="stylesheet" href="/filmHouseCinema3/css/style.css">
<link rel="stylesheet" href="/filmHouseCinema3/js/main.js">
	<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <!--for animation movement-->
    <title>My Profile</title>
</head>

<body class="body">
    <%@ include file="Header2.jsp" %>

    <div>
        <div class="container">
            <div class="row">
                <h3 id="small-profile">My Profile</h3>
                <b>
                    <font color="red"> <%=ServletUtility2.getErrorMessage(request)%></font>
                </b>
                <b>
                    <font color="green"> <%=ServletUtility2.getSuccessMessage(request)%>
                    </font>
                </b>

            </div>

            <form action="<%=OTBView2.MY_PROFILE_CTL%>" method="post">

                <jsp:useBean id="bean" class="org.film.house.cinema.bean.UserBean2" scope="request"></jsp:useBean>

                <input type="hidden" name="id" value="<%=bean.getId()%>"> <input type="hidden" name="createdBy"
                    value="<%=bean.getCreatedBy()%>">
                <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input type="hidden"
                    name="createdDatetime" value="<%=DataUtility2.getTimestamp(bean.getCreatedDatetime())%>">
                <input type="hidden" name="modifiedDatetime"
                    value="<%=DataUtility2.getTimestamp(bean.getModifiedDatetime())%>">

                <div class="textfield">

                    <div class="col-md-6">

                        <div class="container">

                            <div class="form-group">

                                <div class="row">
                                    <div class="col-md-6">
                                        <label>First Name</label>
                                        <input type="text" class="form-control" placeholder="Enter First Name"
                                            name="firstName"
                                            value="<%=DataUtility2.getStringData(bean.getFirstName())%>">
                                        <font color="red"> <%=ServletUtility2.getErrorMessage("firstName", request)%>
                                        </font>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Last Name</label>
                                        <input type="text" class="form-control" placeholder="Enter Last Name"
                                            name="lastName" value="<%=DataUtility2.getStringData(bean.getLastName())%>">
                                        <font color="red"> <%=ServletUtility2.getErrorMessage("lastName", request)%>
                                        </font>
                                    </div>
                                </div>


                                <div class="long">
                                    <label>Login</label>  
                                        <input type="text" class="form-control" placeholder="Enter Login Id"
                                            readonly="readonly" name="login"
                                            value="<%=DataUtility2.getStringData(bean.getLogin())%>">
                                        <font color="red"> <%=ServletUtility2.getErrorMessage("login", request)%></font>
                                </div>

                                <div class="long">
                                    <label>Mobile</label>
                                        <input type="text" class="form-control" placeholder="Enter Mobile No"
                                            name="mobile" value="<%=DataUtility2.getStringData(bean.getMobileNo())%>">
                                        <font color="red"> <%=ServletUtility2.getErrorMessage("mobile", request)%>
                                        </font>
                                </div>

                                <div id="button">
                                        <input type="submit" name="operation" value="<%=MyProfileCtl2.OP_SAVE%>"
                                            class="btn">
                                        <input type="submit" name="operation"
                                            value="<%=MyProfileCtl2.OP_CHANGE_MY_PASSWORD%>" class="btn">
                                </div>
                           

							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
<!--<script src="https://cdn.rawgit.com/michalsnik/aos/2.1.1/dist/aos.js">-->
<script src="main.js"></script>
</body>

</html>