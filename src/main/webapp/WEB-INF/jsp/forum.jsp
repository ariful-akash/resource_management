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
    <body class="w3-theme-light" style="font-family: 'Lato', 'sans-serif';">

        <div>
            <div class="navbar container w3-theme-d3 w3-row">

                <div class="navbar-menu w3-right">
                    <a class="w3-btn" style="text-decoration: none;" href="home.php">Complaints</a>
                    <a class="w3-btn" style="text-decoration: none;" href="history.php">Requisitions</a>
                    <a class="w3-btn" style="text-decoration: none;" href="history.php">Leave</a>
                    <a class="w3-btn" style="text-decoration: none;" href="history.php">Forum</a>
                    <div class="w3-dropdown-hover" style="padding-right: 20px">
                        <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                        <div class="w3-theme-d3 w3-dropdown-content w3-bar-block w3-card-4" style="left: -110px">
                            <a href="#" class="w3-bar-item w3-button">Link 1</a>
                            <a href="#" class="w3-bar-item w3-button">Link 2</a>
                            <a href="#" class="w3-bar-item w3-button">Link 3</a>
                        </div>
                    </div>

                </div>
            </div>

            <div class="w3-row">
                <div class="w3-theme-d1 w3-col" style="width: 24%">
                    <a class="w3-btn" style="text-decoration: none;" href="home.php">Home</a><br>
                    <a class="w3-btn" style="text-decoration: none;" href="home.php">akash</a>
                </div>
                <div class="w3-theme-d1 w3-red w3-half" style="margin: 0% 1% 0% 1%;">
                    <a class="w3-btn" style="text-decoration: none;" href="home.php">Home</a>
                </div>
                <div class="w3-theme-d1 w3-col" style="width: 24%">
                    <a class="w3-btn" style="text-decoration: none;" href="home.php">Home</a>
                </div>

            </div>
        </div>

    </body>
</html>