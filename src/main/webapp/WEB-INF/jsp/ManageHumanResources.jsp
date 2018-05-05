<%--
    Document   : HumanResources
    Created on : Apr 22, 2018, 11:32:23 PM
    Author     : ariful
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/web-resources/css/w3.css" var="css"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/js/menuremover.js" var="menuJs"/>
        <spring:url value="/web-resources/images/akash.jpg" var="img"/>
        <spring:url value="/web-resources/js/notification.js" var="notificationJs"/>


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${menuJs}" type="text/javascript"></script>
        <script src="${notificationJs}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Human Resources</title>
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
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 660px">
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%"><span onclick="document.getElementById('id01').style.display = 'block'">Add Human Resources</span></a><br>
                    <a id="statistics"   class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a id="manageOffice" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="office">Add Office Resources</a>

                    <!--<##Pop up window adding new hr-->
                    <div class="w3-container">

                        <div id="id01" class="w3-modal">
                            <div class="w3-modal-content w3-round" style="margin-left: 25%;width: 50%">
                                <div class="w3-container w3-theme-l3 w3-round">
                                    <span onclick="document.getElementById('id01').style.display = 'none'" class="w3-button w3-large w3-red w3-display-topright">&times;</span>
                                    <div class=" w3-margin w3-large" style="padding: 3%">
                                        <form action="" method="post">
                                            <textarea id="postContent" class="w3-large w3-round-small w3-light-gray" style="resize: vertical; height: 100px; width: 100%" required="true" placeholder="Write Somethings..."></textarea> <br>

                                            <input id="postTags" class="w3-input w3-large w3-round-small w3-light-gray" type="text" placeholder="Type Tag: e.g., computer lab">
                                            <div>
                                                <% for (int i = 0; i < 5; i++) {%>
                                                <label class="w3-theme-l3 w3-margin-top w3-margin-left w3-text-black">Tags <a href="" class="w3-small" style="text-decoration: none;">&#x2715;</a></label>
                                                <%}%>
                                            </div>
                                            <input value="Post" class="w3-button w3-theme-l1 w3-round" style="margin-top: 2%">

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

                <!--Middle div-->
                <div class="w3-theme-l2 w3-col" style="margin: 0% 1% 0% 1%;width: 75%">
                    <div class="w3-card w3-padding" style="margin-top: 10px;overflow: auto;height:650px">
                        <div>
                            <input style="margin-left: 3%;width: 50%; margin-bottom: 4%" class="w3-large w3-input w3-theme-l3" type="text" placeholder="Search..." name="searchPost">
                        </div>
                        <div>
                            <% for (int i = 0; i < 50; i++) {%>
                            <div class="w3-card w3-large w3-hover-theme" style="margin:0% 3% 2% 3%; padding: 1% 0% 2% 1% ;width: 60% " onclick="document.getElementById('id02').style.display = 'block'">
                                <img src="${img}" class="w3-circle" style="width: 30px;height: 30px;margin-right: 1%" alt="#">
                                <label class=" w3-text-dark-gray">Ariful Islam</label><br>

                            </div>
                            <%}%>
                            <!--<##Pop up window for hr-->
                            <div class="w3-container">

                                <div id="id02" class="w3-modal">
                                    <div class="w3-modal-content w3-round" style="margin-left: 25%;width: 50%">
                                        <div class="w3-container w3-theme-l3 w3-round">
                                            <span onclick="document.getElementById('id02').style.display = 'none'" class="w3-button w3-large w3-red w3-display-topright">&times;</span>
                                            <div class="w3-large w3-card w3-theme-l2 w3-padding" style="margin: 8% 1% 8% 1%">
                                                <form action="" method="post">
                                                    <div>
                                                        <label>Name: </label>
                                                        <label>Ariful Islam </label>
                                                        <br>
                                                        <label>Email: </label>
                                                        <label>ariful.uiu.cse@gmail.com </label>
                                                    </div>

                                                    <div style="margin-top: 2%">
                                                        <label>Role: </label>
                                                        <label>RoleName</label>
                                                        <br>
                                                        <%for (int l = 0; l < 5; l++) {%>
                                                        <input type="radio" name="role" value="role" checked="true"> Role<br>
                                                        <%}%>
                                                    </div>

                                                    <div style="margin-top: 2%">
                                                        <label>Access: </label>
                                                        <label>AccessName</label>
                                                        <br>
                                                        <%for (int l = 0; l < 5; l++) {%>
                                                        <input type="checkbox" name="access" value="access" checked="true">Access<br>
                                                        <%}%>
                                                    </div>

                                                    <input value="Add" class="w3-button w3-theme-l1 w3-round" style="margin-top: 2%">

                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

    </body>
</html>

