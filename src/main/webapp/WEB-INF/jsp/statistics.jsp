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
        <spring:url value="/web-resources/js/jquery-1.12.4.js" var="jquery" />
        <spring:url value="/web-resources/js/jquery.canvasjs.min.js" var="jqueryConv" />
        <spring:url value="/web-resources/js/statistics.js" var="js" />
        <spring:url value="/web-resources/js/menuremover.js" var="menuJs"/>
        <spring:url value="/web-resources/js/notification.js" var="notificationJs"/>
        <spring:url value="/web-resources/js/statisticsHR.js" var="statisticsHRjs"/>
        <spring:url value="/web-resources/js/statisticsOR.js" var="statisticsORjs"/>
        <spring:url value="/web-resources/js/statisticsComplaint.js" var="statisticsComplaintJs"/>
        <spring:url value="/web-resources/js/statisticsRequisition.js" var="statisticsRequisitionJs"/>



        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${tog}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${jquery}" type="text/javascript"></script>
        <script src="${jqueryConv}" type="text/javascript"></script>
        <script src="${js}" type="text/javascript"></script>
        <script src="${menuJs}" type="text/javascript"></script>
        <script src="${notificationJs}" type="text/javascript"></script>
        <script src="${statisticsHRjs}" type="text/javascript"></script>
        <script src="${statisticsORjs}" type="text/javascript"></script>
        <script src="${statisticsComplaintJs}" type="text/javascript"></script>
        <script src="${statisticsRequisitionJs}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistics</title>
    </head>
    <body onload="setMenu(); countComplaint(); countRequisition(); loadAllHr()" class="w3-theme-l3" style="font-family: 'Lato', 'sans-serif';">

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
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 500px;margin-right: 1%">

                    <a id="statistics"   class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a id="manageHr"     class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="hr">Manage Human Resources</a>
                    <a id="manageOffice" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="office">Add Office Resources</a>

                </div>

                <!--Right div-->
                <div class="w3-theme-l4 w3-col" style="width: 79%">

                    <!--Tabs-->
                    <div class="w3-bar w3-col w3-theme-d1" style="width:70%">
                        <button class="w3-bar-item w3-button tablink w3-theme-d2" style="width: 25%" onclick="changeStatTab(event, 'hr')">Human Resource</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 25%" onclick="changeStatTab(event, 'or'); getFloorGraphAJAX();">Office Resource</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 25%" onclick="changeStatTab(event, 'com'); getYears();">Complaints</button>
                        <button class="w3-bar-item w3-button tablink" style="width: 25%" onclick="changeStatTab(event, 'req')">Requsitions</button>
                    </div>



                    <!--HR Tab-->
                    <div id="hr" class="w3-container w3-border tab" style="display: block">
                        <!--Toggel and Print Button-->
                        <div class="w3-row w3-margin-top">
                            <div class="w3-col" style="width: 90%; margin-left: 1%;margin-top: 1%">
                                <button onclick="changeViewStyle(this)" class="w3-btn w3-theme-d3 w3-round-large">See List</button>
                                <select id="graphStyle" onchange="changeGraph(this)" class="w3-large w3-btn w3-theme-d3 w3-round">
                                    <option>Pie Chart</option>
                                    <option>Columnar</option>
                                </select>
                            </div>
                            <div class="w3-col"style="width: 6%; margin-right: 3%">
                                <button class="w3-button w3-theme-d3">Print</button>
                            </div>
                        </div>


                        <!--HR Graphs and list-->
                        <div style="margin: 3% 3%">
                            <div id="hrChartContainer" style="height: 450px; width: 100%">

                            </div>

                            <div id="hrListContainer" style="display: none; width: 100%">

                                <table class="w3-table" border="1">
                                    <thead class="w3-theme">
                                        <tr>
                                            <th style="width: 50%;">HR Type</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody id="hrListBody">

                                    </tbody>
                                </table>

                            </div>
                        </div>

                    </div>

                    <!--OR tab-->
                    <div id="or" class="w3-container w3-border tab" style="display:none">
                        <!--Toggel and Print Button-->
                        <div class="w3-row" style="margin: 20px 0px 20px 0px">
                            <div class="w3-col w3-large" style="width: 90%; margin-left: 1%;margin-top: 1%">
                                <button onclick="changeORGraphList(this)" id="orlistOrGraph" class="w3-btn w3-theme-d3 w3-round-large">See List</button>

                                <select id="orGraphStyle" onchange="placeORGraph()" class="w3-btn w3-theme-d3 w3-round">
                                    <option>Pie Chart</option>
                                    <option>Columnar</option>
                                </select>

                                <label class="w3-large" style="margin-left: 20px">Floor:</label>
                                <select id="orGraphFloor" onchange="placeRoomGraph(); getResourceNumber()" class="w3-btn w3-theme-d3 w3-round" style="width: 120px">

                                </select>

                                <label class="w3-large" style="margin-left: 20px">Room:</label>
                                <select id="orGraphRoom" onchange="getResourceNumber()" class="w3-btn w3-theme-d3 w3-round" style="width: 120px">

                                </select>

                            </div>
                            <div class="w3-col"style="width: 6%; margin-right: 3%">
                                <button class="w3-button w3-theme-d3">Print</button>
                            </div>
                        </div>

                        <!--OR Graph content-->
                        <div id="orChartContainer" style="display: block; height: 450px; width: 100%; margin-bottom: 20px;">

                        </div>

                        <!--OR List Content-->
                        <div id="orListContainer" style="display: none; width: 100%; margin-bottom: 10px;">

                            <table class="w3-table" border="1" style="width: 100%">
                                <thead class="w3-theme" style="width: 100%; display: block">
                                    <tr style="width: 100%">
                                        <th style="width: 100%">Resource Type</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody id="orListBody" style="overflow-y: auto; height: 410px; width: 100%; display: block">

                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!--Complaint tab-->
                    <div id="com" class="w3-container w3-border tab" style="display:none">

                        <div class="w3-row" style="margin: 20px 0px 20px 0px">
                            <div class="w3-col w3-large" style="width: 90%; margin-left: 1%;margin-top: 1%">
                                <button onclick="changeComplaintGraphList()" id="comlistOrGraph" class="w3-btn w3-theme-d3 w3-round-large">See List</button>

                                <select id="comGraphStyle" onchange="placeComplaintGraph()" class="w3-btn w3-theme-d3 w3-round">
                                    <option>Pie Chart</option>
                                    <option>Columnar</option>
                                </select>

                                <label class="w3-large" style="margin-left: 20px">Year:</label>
                                <select id="comGraphYear" onchange="placeMonths(); getComplaintsNumber()" class="w3-btn w3-theme-d3 w3-round" style="width: 120px">

                                </select>

                                <label class="w3-large" style="margin-left: 20px">Month:</label>
                                <select id="comGraphMonth" onchange="getComplaintsNumber()" class="w3-btn w3-theme-d3 w3-round" style="width: 120px">

                                </select>

                            </div>
                            <div class="w3-col"style="width: 6%; margin-right: 3%">
                                <button class="w3-button w3-theme-d3">Print</button>
                            </div>
                        </div>

                        <!--OR Graph content-->
                        <div id="comChartContainer" style="display: block; height: 450px; width: 100%; margin-bottom: 20px;">

                        </div>

                        <!--OR List Content-->
                        <div id="comListContainer" style="display: none; width: 100%; margin-bottom: 10px;">

                            <table class="w3-table" border="1" style="width: 100%">
                                <thead class="w3-theme" style="width: 100%; display: block">
                                    <tr style="width: 100%">
                                        <th style="width: 100%">Complaint Type</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody id="comListBody" style="overflow-y: auto; height: 410px; width: 100%; display: block">

                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

    </body>
</html>

