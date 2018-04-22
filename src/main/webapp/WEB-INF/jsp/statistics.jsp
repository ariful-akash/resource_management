<%-- 
    Document   : adminView
    Created on : Apr 14, 2018, 10:22:23 PM
    Author     : ariful
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/web-resources/css/w3.css" var="css"/>
        <spring:url value="/web-resources/css/toggleStyle.css" var="tog"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/images/akash.jpg" var="img"/>
        <spring:url value="/web-resources/js/statistics.js" var="js" />
        <spring:url value="/web-resources/js/jquery-1.12.4.js" var="jquery" />
        <spring:url value="/web-resources/js/jquery.canvasjs.min.js" var="jqueryConv" />


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${tog}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${js}" type="text/javascript"></script>
        <script src="${jquery}" type="text/javascript"></script>
        <script src="${jqueryConv}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistics</title>
    </head>
    <body class="w3-theme-l3" style="font-family: 'Lato', 'sans-serif';">

        <div>
            <div class="w3-theme-d3 w3-row" style="height: 50px">

                <!--navigation ber-->

                <div class="navbar-menu w3-right w3-large" style="height: 100%">
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="complaints">Complaints</a>
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="requisitions">Requisitions</a>
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="#">Leave</a>
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
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 500px;margin-right: 1%">

                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="hr">Manage Human Resources</a>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="office">Add Office Resources</a>

                </div>

                <!--Right div-->
                <div class="w3-theme-l2 w3-col" style="width: 79%">

                    <!--Tabs-->
                    <div class="w3-bar w3-col w3-theme-d1" style="width:70%">
                        <button class="w3-bar-item w3-button tablink w3-theme-d2" style="width: 25%" onclick="changeTab(event, 'hr')">Human Resource</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 25%" onclick="changeTab(event, 'or')">Office Resource</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 25%" onclick="changeTab(event, 'com')">Complaints</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 25%" onclick="changeTab(event, 'req')">Requsitions</button>
                    </div>

                    <!--HR Tab-->
                    <div id="hr" class="w3-container w3-border tab">
                        <!--Toggel and Print Button-->
                        <div class="w3-bar w3-col" >
                            <div class="w3-bar-item" style="margin-left: 1%;margin-top: 1%">
                                <div id="toggle" onclick="getToggleValue()">
                                    <button class="w3-btn w3-theme-d3 w3-round-large">See List</button>
                                </div>
                            </div>
                            <div class="w3-bar-item w3-right"style="margin-right: 3%">
                                <button class="w3-button w3-theme-d3">Print</button>
                            </div>
                        </div>

                        <div class="w3-col" style="margin: 1% 5% 2% 1%"> 
                            <div id="chartContainer" style="height: 430px;width: 98%">

                            </div>
                        </div>
                    </div>

                    <!--OR tab-->
                    <div id="or" class="w3-container w3-border tab" style="display:none">
                        <!--Toggel and Print Button-->
                        <div class="w3-bar w3-col" >
                            <div class="w3-bar-item" style="margin-left: 1%;margin-top: 1%">
                                <div>
                                    <label class="switch"s>
                                        <input class="switch-input" type="checkbox" />
                                        <span class="switch-label" data-on="Graph" data-off="List"></span> 
                                        <span class="switch-handle"></span> 
                                    </label>

                                </div>
                            </div>

                            <div class="w3-padding" style="display: inline">
                                <select class="w3-theme-d3" style="margin-top: 2%;height: 30px">
                                    <option value="floor">Floor</option>
                                    <option value="room">Room</option>
                                    <option value="total">Total</option>
                                </select>
                            </div>

                            <div style="display: inline;">
                                <select class="w3-theme-d3" style="margin-top: 2%;height: 30px"">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                </select>
                            </div>

                            <div class="w3-bar-item w3-right"style="margin-right: 3%">
                                <button class="w3-button w3-theme-d3">Print</button>
                            </div>
                        </div>

                        <table class="w3-table w3-margin-bottom" border="1">
                            <tr>
                                <th class="w3-center">Type</th>
                                <th class="w3-center">Name</th>
                                <th class="w3-center">Total</th>
                            </tr>
                            <%for (int i = 0; i < 10; i++) {%>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <%}%>

                        </table>
                    </div>

                    <!--solved tab-->
                    <div id="com" class="w3-container w3-border tab" style="display:none">

                        <% for (int i = 0; i < 10; i++) {%>
                        <div class="w3-row w3-card  w3-margin-top w3-margin-bottom">

                            <div class="w3-col" style="width: 5%; padding: 1% 0% 0% 1%">
                                <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                            </div>

                            <div class="w3-col" style="width: 92%;margin-top: 1.5%">
                                <label class="w3-small w3-text-dark-gray"><b>Ariful Islam Akash</b></label>
                            </div>

                            <div>
                                <label class="w3-text-dark-gray w3-large w3-right" style="margin-right: 5%;">&#x2714; Solved</label>
                            </div>

                            <div style="margin: 6% 5% 1% 5%">
                                <table class="w3-table w3-striped" border="1">
                                    <tr>
                                        <td>Complaints Type</td>
                                        <td>Table</td>
                                    </tr>
                                    <tr>
                                        <td>Description</td>
                                        <td>Table was Broken</td>
                                    </tr>
                                    <tr>
                                        <td>Placeing Date</td>
                                        <td>14/04/2018</td>
                                    </tr>
                                    <tr>
                                        <td>Placeing Date</td>
                                        <td>14/04/2018</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <%}%>

                    </div>
                </div>


            </div>
        </div>

    </body>
</html>

