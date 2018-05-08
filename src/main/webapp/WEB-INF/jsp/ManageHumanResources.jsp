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
        <spring:url value="/web-resources/js/jquery-1.12.4.js" var="jquery"/>
        <spring:url value="/web-resources/js/jquery-ui.js" var="jqueryUi"/>
        <spring:url value="/web-resources/css/jquery-ui.min.css" var="jQueryUIcss"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/js/menuremover.js" var="menuJs"/>
        <spring:url value="/web-resources/images/dummy.jpg" var="img"/>
        <spring:url value="/web-resources/js/notification.js" var="notificationJs"/>
        <spring:url value="/web-resources/js/humanresources.js" var="hrJs"/>


        <link href="${jQueryUIcss}" rel="stylesheet" type="text/css"/>
        <link href="${css}" rel="stylesheet" type="text/css"/>
        <script src="${jquery}" type="text/javascript"></script>
        <script src="${jqueryUi}" type="text/javascript"></script>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${menuJs}" type="text/javascript"></script>
        <script src="${notificationJs}" type="text/javascript"></script>
        <script src="${hrJs}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Human Resources</title>
    </head>
    <body onload="setMenu(); getAllUsers(); countComplaint(); countRequisition();" class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">

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
                        <img src="" id="menuImage" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                        <div class="w3-theme-d3 w3-dropdown-content w3-bar-block w3-card-4" style="left: -110px">
                            <a href="profile" class="w3-bar-item w3-button">Profile</a>
                            <a href="logout" class="w3-bar-item w3-button">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="w3-row">

                <!--Left div-->
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 595px">
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%"><span onclick="fetchHrTypes(); document.getElementById('id01').style.display = 'block'">Add Human Resources</span></a><br>
                    <a id="statistics"   class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a id="manageOffice" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="office">Add Office Resources</a>

                    <!--<##Pop up window adding new hr-->
                    <div class="w3-container">

                        <div id="id01" class="w3-modal">
                            <div class="w3-modal-content w3-round" style="margin-left: 25%;width: 50%">
                                <div class="w3-container w3-theme-l3 w3-round">
                                    <span onclick="document.getElementById('id01').style.display = 'none'" class="w3-button w3-large w3-red w3-display-topright">&times;</span>
                                    <div class=" w3-margin w3-large" style="padding: 3%">
                                        <div class="w3-row w3-margin">
                                            <div class="w3-half w3-padding-small">
                                                <label>First Name</label>
                                                <input type="text" id="addFirstname" onkeyup="checkField(this)" class="w3-input w3-theme-l4" style="border: 0px">
                                            </div>

                                            <div class="w3-half w3-padding-small">
                                                <label>Last Name</label>
                                                <input type="text" id="addLastname" onkeyup="checkField(this)" class="w3-input w3-theme-l4"style="border: 0px">
                                            </div>
                                        </div>

                                        <div class="w3-margin w3-padding-small">
                                            <label>Email</label>
                                            <input type="email" id="addEmail" onkeyup="checkField(this)" class="w3-input w3-theme-l4" style="border: 0px">
                                        </div>

                                        <div class="w3-margin w3-padding-small">
                                            <label>Phone</label>
                                            <input type="text"id="addPhone" onkeyup="checkField(this)" class="w3-input w3-theme-l4" style="border: 0px">
                                        </div>

                                        <div class="w3-margin w3-row">
                                            <div class="w3-half w3-padding-small">
                                                <label>Department</label>
                                                <select id="addDepartment" class="w3-theme-l4" style="height: 40px;width: 100%">
                                                    <option>CSE</option>
                                                    <option>BBA</option>
                                                    <option>EEE</option>
                                                    <option>ECONOMICS</option>
                                                    <option>ETE</option>
                                                </select>
                                            </div>

                                            <div class="w3-half w3-padding-small">
                                                <label >Designation</label>
                                                <select id="addDesignation" class="w3-theme-l4" style="height: 40px;width: 100%">

                                                </select>
                                            </div>
                                        </div>

                                        <div class="w3-margin">
                                            <input onclick="addHumanResource()" value="Add Human Resource" type="button" class="w3-input w3-button w3-hover-light-green w3-theme-l1 w3-round" style="margin-top: 2%">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

                <!--Middle div-->
                <div class="w3-theme-l2 w3-col" style="margin: 0% 0% 0% 1%;width: 79%">
                    <div style="margin-top: 10px;">

                        <input id="userSearch" onkeyup="clearSearch()" style="margin-left: 3%;width: 50%; display: inline" class="ui-widget w3-large w3-input w3-theme-l3 w3-round w3-card-2" type="text" placeholder="Search..." name="search">
                        <input onclick="placeSearchedUser()" type="button" style="margin-left: 3%; width: 10%; display: inline" class="w3-large w3-input w3-round w3-card-2 w3-theme-l3" value="Find">

                        <div class="w3-card-4 w3-round" id="messageDiv" style="float: right; width: 96%; padding: 10px 10px 10px 40px; margin: 5px; background-color: #42f48f; display: none">
                            <label id="msg"></label>
                            <a
                                href="#"
                                class="w3-right w3-hover-red"
                                onclick="document.getElementById('messageDiv').style.display = 'none'"
                                style="padding: 3px; text-decoration: none">
                                &#x2715;
                            </a>
                        </div>


                        <div class="w3-row" style="padding-top: 15px; margin-top: 15px">
                            <div id="allHr" class="w3-third" style="overflow: auto; height: 510px; padding-top: 1px">

                                <!--the user list are placed here-->


                            </div>
                            <div class="w3-rest" id="details" style="overflow: auto; height: 510px; padding: 1px 0% 0% 5%; display: none">

                                <div id="id02" class="w3-large">
                                    <img src="" id="singleUserImg" alt="#" width="30%" class="w3-round"/>

                                    <div style="margin-top: 20px">
                                        <label>Name: </label>
                                        <label id="singleUserName">Ariful Islam</label>
                                        <br>
                                        <label>Email: </label>
                                        <label id="singleUserEmail">ariful.uiu.cse@gmail.com</label>
                                        <br>
                                        <label>Designation: </label>
                                        <label id="singleUserDeg">CSE</label>
                                        <br>
                                        <label>Department: </label>
                                        <label id="singleUserDep">vice chancelor</label>
                                    </div>

                                    <div class="w3-row">
                                        <div class="w3-third" style="margin-top: 2%" id="">
                                            <label>Role: </label>
                                            <br>
                                            <div style="margin-left: 10%" id="userRole">
                                                <!--<input type="radio" name="role" value="role" checked="true"><label>Role</label><br>-->
                                            </div>
                                        </div>

                                        <div class="w3-rest" id="" style="margin-top: 2%; padding-left: 1%;">
                                            <label>Access: </label>
                                            <br>
                                            <div style="margin-left: 10%" id="userAccess">
                                                <%for (int l = 0; l < 5; l++) {%>
                                                <input type="checkbox" name="access" value="access" checked="true">Access<br>
                                                <%}%>
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

