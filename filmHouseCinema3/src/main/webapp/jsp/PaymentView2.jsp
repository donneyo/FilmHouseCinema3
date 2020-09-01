<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.film.house.cinema.controller.*"%>
<%@page import="org.film.house.cinema.model.MovieModel2"%>
<%@page import="org.film.house.cinema.util.*"%>
<%@page import="org.film.house.cinema.bean.*"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="/filmHouseCinema3/css/style.css">
<link rel="stylesheet" href="/filmHouseCinema3/js/main.js">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <!--for animation movement-->
</head>

<body class="body">
    <%@ include file="Header2.jsp" %>
    <div class="animate-box" data-aos="fade-up" data-aos-delay="200">
        <div class="container">
            <div class="row">
                <div>
                    <h3 id="small-book">Book</h3>
                </div>
            </div>
            <%
            	BookBean2 bBean=(BookBean2)session.getAttribute("BookB");
                        		
                        			MovieModel2 mModel=new MovieModel2();
                        			MovieBean2 mBean=mModel.findByPK(bBean.getMovieId());
            %>


            <div class="row">
                <div class="col-md-12 animate-box">
                    <h2 class="heading-title"><%=mBean.getName()%></h2>
                    <%
                    	String mssg=(String)request.getAttribute("msg");
                    %>
                    <b>
                        <font color="green"> <%=(mssg==null)?"":mssg%>
                        </font>
                    </b>
                </div>

                <div class="col-md-6 animate-box">
                    <span
                        class="posted_by">(<%=mBean.getCertificate()%>),&nbsp;<%=mBean.getLanguage()%>,&nbsp;<%=mBean.getType()%>,&nbsp;<%=mBean.getDuration()%></span>
                    <p><%=mBean.getDirector()%></p>
                    <p><%=mBean.getCast()%></p>
                    <p><a href="#">&nbsp;N<%=mBean.getPrice()%></a></p>
                    <p><%=mBean.getDescription()%></p>
                    <p><%=userBean.getFirstName()+" "+userBean.getLastName()%></p>
                    <p>Total Amount:&nbsp;N<%=bBean.getFinalAmount()%></p>
                </div>

                <div class="col-md-6 animate-box">
                    <img class="img-responsive" src="../images/<%=mBean.getImage()%>" alt="travel">
                </div>
            </div>


            <%
            	if(mssg==null){
            %>
            <form action="<%=OTBView2.BOOK_CTL%>" method="post">

                <jsp:useBean id="bean" class="org.film.house.cinema.bean.BookBean2" scope="request"></jsp:useBean>

                <input type="hidden" name="id" value="<%=bean.getId()%>"> <input type="hidden" name="createdBy"
                    value="<%=bean.getCreatedBy()%>">
                <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input type="hidden"
                    name="createdDatetime" value="<%=DataUtility2.getTimestamp(bean.getCreatedDatetime())%>">
                <input type="hidden" name="modifiedDatetime"
                    value="<%=DataUtility2.getTimestamp(bean.getModifiedDatetime())%>">

                <div class="row animate-box">

                    <div class="col-md-6">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Credit Card Number</label>
                                    <input type="text" class="form-control" placeholder="Enter Credit Card Number"
                                        name="" value="">
                                </div>
                                <div class="col-md-6">
                                    <label>Name on Credit Card</label>
                                    <input type="text" class="form-control" placeholder="Enter Name on Credit Card"
                                        name="" value="">
                                </div>
                            </div>

                            <div class="long">
                                <label>Credit Card Type</label>
                                <% HashMap<String,String> map=new HashMap<String,String>();
										map.put("Saving","Saving");
										map.put("Cureent","Current");	
									%>
                                <%=HTMLUtility2.getList("show",String.valueOf(bean.getShowTime()), map) %>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <label>Month</label>
                                    <% HashMap<String,String> map1=new HashMap<String,String>();
										map1.put("January","January");
										map1.put("Febuary","Febuary");
										map1.put("March","March");
										map1.put("April","April");
									
										
									%>
                                    <%=HTMLUtility2.getList("show",String.valueOf(bean.getShowTime()), map1) %>
                                </div>

                                <div class="col-md-6">
                                    <label>Year</label>
                                    <% HashMap<String,String> map2=new HashMap<String,String>();
										map2.put("2019","2019");
										map2.put("2021","2021");
										map2.put("2022","2022");
										map2.put("2023","2023");
									
										
									%>
                                    <%=HTMLUtility2.getList("show",String.valueOf(bean.getShowTime()), map2) %>
                                </div>
                            </div>


                            <div class="long">
                                <label>CVV No.</label>
                                <input type="text" class="form-control" placeholder="Enter CVV No." name="" value="">
                            </div>

                            <div class="long">
                                <label>Amount Paid</label>
                                <input type="text" class="form-control" placeholder="Enter Amount Paid"
                                    readonly="readonly" name="" value="<%=bBean.getFinalAmount()%>">
                            </div>



                            <div class="col-md-12">
                                <div class="form-level">
                                    <div id="button">
                                        <input type="submit" name="operation" value="<%=BookCtl2.OP_PAYMENT_BOOK%>"
                                            class="btn">
                                        <input type="submit" name="operation" value="<%=BookCtl2.OP_CANCEL%>"
                                            class="btn">
                                    </div>
                                </div>
                                <br>
                            </div>
		
                        </div>
                    </div>
                </div>
                
                
            </form>
            <%} %>
        </div>
    </div>

    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
<!--<script src="https://cdn.rawgit.com/michalsnik/aos/2.1.1/dist/aos.js">-->
<script src="js/main.js"></script>

</body>

</html>