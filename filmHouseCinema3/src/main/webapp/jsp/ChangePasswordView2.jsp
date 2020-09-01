<%@page import="org.film.house.cinema.controller.*"%>
<%@page import="org.film.house.cinema.util.DataUtility2"%>
<%@page import="org.film.house.cinema.util.ServletUtility2"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Change password</title>
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	<link rel="stylesheet" href="/filmHouseCinema3/css/style.css">
<link rel="stylesheet" href="/filmHouseCinema3/js/main.js">
	<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <!--for animation movement-->
</head>

<body class="body">

    <%@ include file="Header2.jsp" %>
    <div>
        <div class="container">
            <div class="row">
                <h3>Change Password</h3>
                <b>
                    <font color="red"> <%=ServletUtility2.getErrorMessage(request)%></font>
                </b>
                <b>
                    <font color="green"> <%=ServletUtility2.getSuccessMessage(request)%>
                    </font>
                </b>

            </div>

            <form action="<%=OTBView2.CHANGE_PASSWORD_CTL%>" method="post">

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

                                <div class="long">
                                    <label>Old Password</label>
                                    <input type="password" class="form-control" placeholder="Enter Old Password"
                                        name="oldPassword" value=<%=DataUtility2.getString(request.getParameter("oldPassword") == null ? "" : DataUtility2.getString(request
                                    .getParameter("oldPassword")))%>>
                                    <font color="red"> <%=ServletUtility2.getErrorMessage("oldPassword", request)%>
                                    </font>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <label>New Password</label>
                                        <input type="password" class="form-control" placeholder="Enter New Password"
                                            name="newPassword" value=<%=DataUtility2.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility2.getString(request.getParameter("newPassword")))%>>
                                        <font color="red"> <%=ServletUtility2.getErrorMessage("newPassword", request)%>
                                        </font>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Confirm Password</label>
                                        <input type="password" class="form-control" placeholder="Confirm Password"
                                            name="confirmPassword" value=<%=DataUtility2.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility2
                    .getString(request.getParameter("confirmPassword")))%>>
                                        <font color="red">
                                            <%=ServletUtility2.getErrorMessage("confirmPassword", request)%></font>
                                    </div>
                                </div>

                                <div id="button">
                                    <input type="submit" name="operation" value="<%=ChangePasswordCtl2.OP_SAVE%>"
                                        class="btn">
                                    <input type="submit" name="operation"
                                        value="<%=ChangePasswordCtl2.OP_CHANGE_MY_PROFILE%>" class="btn">
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