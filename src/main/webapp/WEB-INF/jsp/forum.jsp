<%-- 
    Document   : forum
    Created on : Apr 13, 2018, 3:40:04 PM
    Author     : ariful
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/web-resources/css/w3.css" var="css"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/images/akash.jpg" var="img"/>


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
    </head>
    <body class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">

        <div>
            <div class="w3-theme-d3 w3-row" style="height: 50px">

                <!--navigation ber-->

                <div class="navbar-menu w3-right w3-large" style="height: 100%">
                    <a class="w3-btn" style="text-decoration: none;" href="#">Complaints</a>
                    <a class="w3-btn" style="text-decoration: none;" href="#">Requisitions</a>
                    <a class="w3-btn" style="text-decoration: none;" href="#">Leave</a>
                    <a class="w3-btn" style="text-decoration: none;" href="#">Forum</a>
                    <div class="w3-dropdown-hover" style="padding-right: 20px">
                        <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                        <div class="w3-theme-d3 w3-dropdown-content w3-bar-block w3-card-4" style="left: -110px">
                            <a href="#" class="w3-bar-item w3-button">View Profile</a>
                            <a href="#" class="w3-bar-item w3-button">Edit Profile</a>
                            <a href="#" class="w3-bar-item w3-button">Logout</a>
                        </div>
                    </div>

                </div>
            </div>

            <div class="w3-row">

                <!--Left div-->
                <div class="w3-theme-l1 w3-col" style="width: 24%">
                    <a class="w3-btn" style="text-decoration: none;" href="#">My Posts</a><br>
                    <a class="w3-btn" style="text-decoration: none;" href="#">Add New Posts</a>
                </div>

                <!--Middle div-->
                <div class="w3-theme-l1 w3-red w3-col" style="margin: 0% 1% 0% 1%;width: 57%">
                    <div class="w3-card w3-padding" style="margin-top: 10px">
                        <input style="margin-left: 2%;width: 50%; margin-bottom: 1%" class="w3-large w3-input w3-theme-l3" type="text" placeholder="Search Post By Tag" name="searchPost">

                        <% for (int i = 0; i < 5; i++) {%>
                        <label class="w3-white w3-margin-top w3-margin-left w3-text-black">Table <a href="" style="text-decoration: none;">&#x2715;</a></label>
                        <%}%>
                    </div>

                    <div style="overflow: auto; height: 500px; margin-top: 10px">
                        <% for (int i = 0; i < 10; i++) {%>
                        <div class="w3-row w3-card w3-margin">
                            <div class="w3-col" style="width: 5%; margin-right: 3%; padding: 1% 0% 0% 1%">
                                <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                            </div>
                            <div class="w3-col" style="width: 92%">
                                <label class="w3-small w3-text-dark-gray"><b>Ariful Islam Akash</b></label><br>
                                <label class="w3-tiny w3-text-dark-gray">14/04/2018</label>
                            </div>

                            <div style="margin: 8% 5% 1% 5%">
                                <p>akshsjkashfxjsdk dsjkxfhdjksf dsjkxfhdjksf dsjkxfhdjksf
                                    dsjkxfhdjksf dsjkxfhdjksf dsjkxfhdjksf  esdkjfxhcdsjkfc 
                                    sdfjkxhjkds ksdfj kurj sksh ksfh ksdfh... <a class="w3-text-dark-gray" href="">See more</a></p>
                            </div>
                            <div>
                                <% for (int j = 0; j < 5; j++) {%>
                                <label class=" w3-small w3-text-dark-gray" style="margin-left: 5%">Table</label>
                                <%}%>
                            </div>
                        </div>
                        <%}%>

                    </div>
                </div>

                <!--Right div-->
                <div class="w3-theme-l1 w3-col" style="width: 17%">
                    <div  style="overflow: auto; height: 530px; padding-left:1%">
                        <% for (int i = 0; i < 20; i++) { %>
                        <div class="w3-row w3-margin">
                            <div class="w3-col" style="width: 10%; margin-right: 15%">
                                <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                            </div>
                            <div class="w3-col" style="width: 75%">
                                <label>Ariful Islam Akash</label><br>
                                <label class="w3-small">Assistant Professor</label>
                            </div>


                        </div>
                        <%}%>

                    </div>
                    <div style="margin-top: 10px">
                        <input class="w3-large w3-input w3-theme-l3" style="position: fixed;" type="text" placeholder="Search User" name="search">
                    </div>

                </div>

            </div>
        </div>

    </body>
</html>