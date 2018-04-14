<%-- 
    Document   : myComplaints
    Created on : Apr 14, 2018, 1:32:01 PM
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
        <spring:url value="web-resources/js/myComplaintsJS.js" var="js" />


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${js}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Complaints</title>
    </head>
    <body class="w3-theme-l3" style="font-family: 'Lato', 'sans-serif';">

        <div>
            <div class="w3-theme-d3 w3-row" style="height: 50px">

                <!--navigation ber-->

                <div class="navbar-menu w3-right w3-large" style="height: 100%">
                    <a class="w3-button" style="text-decoration: none;" href="#">Complaints</a>
                    <a class="w3-button" style="text-decoration: none;" href="#">Requisitions</a>
                    <a class="w3-button" style="text-decoration: none;" href="#">Leave</a>
                    <a class="w3-button" style="text-decoration: none;" href="#">Forum</a>
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
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 500px;margin-right: 1%">

                    <div>
                        <a class="w3-button" style="text-decoration: none;width: 100% " href="#">My Complaints</a><br>
                        <a class="w3-button" style="text-decoration: none;width: 100%" href="#">ADD Items</a>
                    </div>

                    <!--<##Pop up window-->
                    <div class="w3-container">
                        <a class="w3-button" style="width: 100%"><span onclick="document.getElementById('id01').style.display = 'block'">New Complaints</span></a>

                        <div id="id01" class="w3-modal">
                            <div class="w3-modal-content" style="margin-left: 25%;width: 50%">
                                <div class="w3-container w3-theme-l3">
                                    <span onclick="document.getElementById('id01').style.display = 'none'" class="w3-button w3-red w3-display-topright">&times;</span>
                                    <div class=" w3-margin w3-half w3-large">
                                        <form action="" method="post">
                                            <label class="">Description: </label>
                                            <input name="descr"  class="w3-large w3-input w3-round-large w3-light-gray" type="text" ><br>

                                            <div class="w3-row">
                                                <input type="submit" value="Submit" class="w3-btn w3-theme-l1 w3-round" style="padding-left: 7%; padding-right: 7%;">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Right div-->
                <div class="w3-theme-l2 w3-col" style="width: 79%">

                    <!--Tabs-->
                    <div class="w3-bar w3-col w3-theme-d1" style="width:50%">
                        <button class="w3-bar-item w3-button tablink w3-theme-d2" style="width: 33%" onclick="changeTab(event, 'all')">All</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 33%" onclick="changeTab(event, 'pending')">Pending</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 34%" onclick="changeTab(event, 'solved')">Solved</button>
                    </div>

                    <!--all Tab-->
                    <div id="all" class="w3-container w3-border tab">

                    </div>

                    <!--pending tab-->
                    <div id="pending" class="w3-container w3-border tab" style="display:none">
                        <% for (int i = 0; i < 10; i++) {%>
                        <div class="w3-row w3-card  w3-margin-top w3-margin-bottom">

                            <div class="w3-col" style="width: 5%; padding: 1% 0% 0% 1%">
                                <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                            </div>

                            <div class="w3-col" style="width: 92%;margin-top: 1.5%">
                                <label class="w3-small w3-text-dark-gray"><b>Ariful Islam Akash</b></label>
                            </div>

                            <div>
                                <span class=" w3-btn w3-theme-l3 w3-round w3-text-white w3-medium w3-right" style="margin-right: 5%;">Solve</span>
                            </div>

                            <div style="margin: 4% 5% 1% 5%">
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

                    <!--solved tab-->
                    <div id="solved" class="w3-container w3-border tab" style="display:none">

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
