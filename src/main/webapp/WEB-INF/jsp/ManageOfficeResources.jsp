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
        <spring:url value="/web-resources/js/officeresource.js" var="officeresourceJs"/>
        <spring:url value="/web-resources/images/logo.png" var="logo"/>

        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${menuJs}" type="text/javascript"></script>
        <script src="${notificationJs}" type="text/javascript"></script>
        <script src="${officeresourceJs}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Office Resources</title>
    </head>
    <body onload="setMenu(); placeOfficeResourceData(); countComplaint(); countRequisition()" class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">

        <div>
            <div class="w3-theme-d3 w3-row" style="height: 50px">

                <!--navigation ber-->

                <div class="navbar-menu w3-left">
                    <img src="${logo}" height="45px" style="margin-left: 20%">
                </div>

                <div class="navbar-menu w3-right w3-large" style="height: 100%">
                    <a class="w3-button" style="text-decoration: none; height: 100%" href="complaints">
                        Complaints
                        <span id="complaintNotification" class="w3-badge w3-red"></span>
                    </a>
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="requisitions">
                        Requisitions
                        <span id="requisitionNotification" class="w3-badge w3-red"></span>
                    </a>
                    <a class="w3-button" style="text-decoration: none;height: 100%" href="forum">Forum</a>
                    <div class="w3-dropdown-hover" style="padding-right: 20px">
                        <img src="" id="menuImage" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                        <div class="w3-theme-d3 w3-dropdown-content w3-bar-block w3-card-4" style="left: -110px">
                            <a href="profile" class="w3-bar-item w3-button">Edit Profile</a>
                            <a href="logout" class="w3-bar-item w3-button">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="w3-row">

                <!--Left div-->
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 450px">
                    <a id="addOffice" onclick="changeDisplay('addOffice'); changeSideMenuColor(this)" class="w3-button side-menu" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="#">Add Office Resources</a>
                    <a id="viewOffice" onclick="changeDisplay('viewOffice'); changeSideMenuColor(this)" class="w3-button side-menu w3-theme-l2" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="#">View Office Resources</a>
                    <a class="w3-button side-menu" onclick="changeDisplay('addType'); changeSideMenuColor(this)" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="#">Add Office Resources Type</a>
                    <a id="statistics" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a id="manageHr" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="hr">Manage Human Resources</a>

                </div>

                <!--Middle div-->
                <div class="w3-theme-l2 w3-col" style="width: 80%">
                    <div style="margin: 5% 5% 5% 5%">

                        <!--Office Resource Adding div-->
                        <div id="addResourceDiv" onload="" style="display: none">
                            <table class="w3-large" style="width: 50%">
                                <tr>
                                    <td><label>Type: </label></td>
                                    <td>
                                        <select id="typeOption" class="w3-theme-l4 w3-input w3-round">

                                            <!--Type options are placed here by js-->

                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td><label>Floor: </label></td>
                                    <td>
                                        <select id="floorOption" onchange="placeRoomOption();" class="w3-theme-l4 w3-input w3-round">

                                            <!--Floor options are placed here by js-->

                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td><label>Room: </label></td>
                                    <td>
                                        <select id="roomOption" class="w3-theme-l4 w3-input w3-round">

                                            <!--Room options are placed here by js-->

                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td><label>Quantity: </label></td>
                                    <td>
                                        <input id="typeQuantity" class="w3-theme-l4 w3-input w3-round" type="number" value="1" min="1">
                                    </td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td>
                                        <input onclick="addResource()" class="w3-button w3-theme-d1 w3-hover-green w3-round" style="width: 50%" type="button" value="Add">
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <!--office resource display table-->
                        <div id="viewResourceDiv">

                            <table class="w3-table w3-large" style="margin-bottom: 15px">
                                <tr>
                                    <td style="width: 30%">
                                        <label>Type</label>
                                        <select id="viewTypeOption" onclick="fetchSpecificResources()" class="w3-theme-l4 w3-input w3-round">

                                            <!--Type options are placed here by js-->

                                        </select>
                                    </td>
                                    <td style="width: 30%">
                                        <label>Floor</label>
                                        <select id="viewFloorOption" onchange="placeRoomOption(); fetchSpecificResources()" class="w3-theme-l4 w3-input w3-round">

                                            <!--Type options are placed here by js-->

                                        </select>
                                    </td>
                                    <td style="width: 30%">
                                        <label>Room</label>
                                        <select id="viewRoomOption" onchange="fetchSpecificResources()" class="w3-theme-l4 w3-input w3-round">

                                            <!--Type options are placed here by js-->

                                        </select>
                                    </td>
                                    <td>
                                        <button onclick="fetchSpecificResources()" class="w3-button w3-theme-d4">Refresh</button>
                                    </td>
                                </tr>
                            </table>

                            <div>
                                <table class="w3-table" border="1">
                                    <caption id="caption" class="w3-xlarge w3-theme-d4"></caption>
                                    <thead>
                                        <tr>
                                            <th>Floor</th>
                                            <th>Room</th>
                                            <th>Quantity</th>
                                            <th>Total in floor</th>
                                        </tr>
                                    </thead>
                                    <tbody id="byTypeTableBody">


                                    </tbody>
                                </table>
                            </div>

                        </div>

                        <div id="addResourceTypeDiv" style="display: none; height: 490px" class="w3-large">

                            <label>Resource Type: </label>
                            <input type="text" class="w3-input w3-theme-l3" style="width: 30%"><br>
                            <input type="button" value="Add" class="w3-input w3-hover-gray w3-theme-d3" style="width: 10%">
                        </div>
                    </div>
                </div>
            </div>

    </body>
</html>

