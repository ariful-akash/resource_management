<%--
    Document   : requisitions
    Created on : Apr 20, 2018, 12:21:51 PM
    Author     : ariful
--%>

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
        <spring:url value="/web-resources/js/myComplaintsJS.js" var="js" />
        <spring:url value="/web-resources/js/requisitionadd.js" var="requisitionAddJs" />
        <spring:url value="/web-resources/js/menuremover.js" var="menuJs"/>
        <spring:url value="/web-resources/images/loading.gif" var="loading"/>


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${js}" type="text/javascript"></script>
        <script src="${requisitionAddJs}" type="text/javascript"></script>
        <script src="${menuJs}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requisitions</title>
    </head>
    <body onload="setMenu(); getOwnPendingRequisition()" class="w3-theme-l3" style="font-family: 'Lato', 'sans-serif';">

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

                    <div>
                        <a class="w3-button"style="text-align: left; width: 100%; padding-left: 20%">
                            <span onclick="document.getElementById('id01').style.display = 'block'; getTags()">Add Requisition</span>
                        </a>
                        <a class="w3-button" style="text-decoration: none; text-align: left; width: 100%; padding-left: 20%" href="#" onclick="changeOwn(true); getRequisition()">My Requisitions</a>
                        <a id="incomingRequisitions" class="w3-button" style="text-decoration: none; text-align: left; width: 100%; padding-left: 20%" href="#" onclick="changeOwn(false); getRequisition()">Incoming Requisitions</a>
                        <a id="statistics"   class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                        <a id="manageHr"     class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="hr">Manage Human Resources</a>
                        <a id="manageOffice" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="office">Add Office Resources</a>
                    </div>

                    <!--<##Pop up window-->
                    <div class="w3-container">

                        <div id="id01" class="w3-modal">
                            <div class="w3-modal-content" style="margin-left: 25%;width: 50%">
                                <div class="w3-container w3-theme-l3">
                                    <span onclick="document.getElementById('id01').style.display = 'none'" class="w3-button w3-large w3-red w3-display-topright">&times;</span>
                                    <div class=" w3-margin w3-large">
                                        <label>Select a requisition type</label><br>
                                        <select id="tagList" onchange="showDateTime()" class="w3-input w3-round-small" style="margin-bottom: 15px">

                                            <!--options will be placed by js-->

                                        </select>
                                        <label>Quantity</label><br>
                                        <input id="quantity" class="w3-input" type="number" min="1" value="1"/>

                                        <div id="reqDateTimeDiv" class="w3-row" style="display: none">
                                            <label>Requisition date and time</label><br>
                                            <input id="reqDate" class="w3-half" type="date" style="margin-bottom: 10px"/>
                                            <input id="reqTime" class="w3-half" type="time" style="margin-bottom: 10px"/>
                                        </div>

                                        <label>Describe requisition purpose</label>
                                        <textarea id="reqContent" class="w3-large w3-round-small w3-light-gray w3-input" style="resize: vertical; height: 150px; width: 100%;" required="true" placeholder="Write your comlaint here..."></textarea>

                                        <label class="">Remarks(If any)</label>
                                        <input id="remarks" class="w3-large w3-input w3-round-small w3-light-gray" type="text" ><br>
                                        <button onclick="addRequisition()" class="w3-input w3-green w3-round-small w3-hover-light-green">Submit Requisition</button>
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
                        <button class="w3-bar-item w3-button tablink w3-theme-d2" style="width: 50%" onclick="changeTab(event, 'pending'); getPandingRequisitions()">Pending</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 50%" onclick="changeTab(event, 'solved'); getSolvedRequisitions()">Solved</button>
                    </div>

                    <h2 id="noContent" style="display: none;margin-top: 15%; margin-bottom: 30%; text-align: center">No Complaints Available</h2>

                    <img id="loading" src="${loading}" height="300px" style="display: block; margin-left: auto; margin-right: auto; margin-top: 10%; margin-bottom: 10%">

                    <!--pending tab-->
                    <div id="pending" class="w3-container w3-border tab" style="display:block">

                        <!--panding requisitions-->

                    </div>

                    <!--solved tab-->
                    <div id="solved" class="w3-container w3-border tab" style="display:none">

                        <!--Solved Requisition-->

                    </div>
                </div>


            </div>
        </div>

    </body>
</html>

