<%--
    Document   : ManageOfficeResources
    Created on : Apr 23, 2018, 12:00:46 AM
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
        <spring:url value="/web-resources/js/menuremover.js" var="menuJs"/>
        <spring:url value="/web-resources/js/notification.js" var="notificationJs"/>


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${menuJs}" type="text/javascript"></script>
        <script src="${notificationJs}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Office Resources</title>
    </head>
    <body onload="setMenu(); countComplaint(); countRequisition()" class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">

        <div>
            <div class="w3-theme-d3 w3-row" style="height: 50px">

                <!--navigation ber-->

                <div class="navbar-menu w3-right w3-large" style="height: 100%">
                    <a class="w3-button" style="text-decoration: none; height: 100%" href="complaints">
                        Complaints
                        <span id="complaintNotification" class="w3-badge w3-red"></span>
                    </a>
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="requisitions">
                        Requisitions
                        <span id="requisitionNotification" class="w3-badge w3-red"></span>
                    </a>
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="#">
                        Leave
                        <span id="leaveNotification" class="w3-badge w3-red">6</span>
                    </a>
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="forum">Forum</a>
                    <div class="w3-dropdown-hover" style="padding-right: 20px">
                        <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                        <div class="w3-theme-d3 w3-dropdown-content w3-bar-block w3-card-4" style="left: -110px">
                            <a href="#" class="w3-bar-item w3-button">View Profile</a>
                            <a href="#" class="w3-bar-item w3-button">Edit Profile</a>
                            <a href="logout" class="w3-bar-item w3-button">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="w3-row">

                <!--Left div-->
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 450px">
                    <a id="addOffice" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="#">Add Office Resources</a>
                    <a id="viewOffice" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="#">View Office Resources</a>
                    <a id="statistics"   class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a id="manageHr"     class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="hr">Manage Human Resources</a>

                </div>

                <!--Middle div-->
                <div class="w3-theme-l2 w3-col" style="margin: 0% 1% 0% 1%;width: 78%;">
                    <div style="margin: 1% 1% 1% 1%">
                        <div class="" style="margin-left: 25%">

                            <label>Type: </label>
                            <select class="w3-theme-d3" style="margin-left: 5.5% ;width: 30%;height: 40px">
                                <option value="type">Table</option>
                            </select> <br>

                            <label>Floor: </label>
                            <select class="w3-theme-d3" style="margin-top: 2%;margin-left: 5% ;width: 30%;height: 40px">
                                <option value="floor">1</option>
                            </select> <br>

                            <label>Room: </label>
                            <select class="w3-theme-d3" style="margin-top: 2%;margin-left: 4.5% ;padding-bottom: 50px;width: 30%;height: 40px">
                                <option value="room">10</option>
                            </select> <br>

                            <label style="display: inline">Quantity: </label>
                            <input class="w3-input w3-round w3-margin w3-theme-d3" style="display: inline ;margin-left: 5% ; width: 30%" type="text">

                            <br>

                            <input class="w3-large w3-button w3-round w3-theme-d3" style="width: 15%;margin-left: 25%" value="Add">

                        </div>

                    </div>
                </div>
            </div>

    </body>
</html>

