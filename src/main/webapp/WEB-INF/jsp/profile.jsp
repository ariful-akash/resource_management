<%--
    Document   : profile
    Created on : May 3, 2018, 5:48:26 PM
    Author     : ashif
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/web-resources/css/w3.css" var="css"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/images/akash.jpg" var="img"/>
        <spring:url value="/web-resources/js/jquery-1.12.4.js" var="jQuery" />
        <spring:url value="/web-resources/js/jquery-ui.js" var="jQueryUi" />
        <spring:url value="/web-resources/js/myComplaintsJS.js" var="js" />
        <spring:url value="/web-resources/js/complaintadd.js" var="complaintAddJs" />
        <spring:url value="/web-resources/js/menuremover.js" var="menuJs"/>
        <spring:url value="/web-resources/js/notification.js" var="notificationJs"/>
        <spring:url value="/web-resources/js/profile.js" var="profileJs"/>
        <spring:url value="/web-resources/images/loading.gif" var="loading"/>


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${jQuery}" type="text/javascript"></script>
        <script src="${jQueryUi}" type="text/javascript"></script>
        <script src="${js}" type="text/javascript"></script>
        <script src="${complaintAddJs}" type="text/javascript"></script>
        <script src="${menuJs}" type="text/javascript"></script>
        <script src="${notificationJs}" type="text/javascript"></script>
        <script src="${profileJs}" type="text/javascript"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body onload="setMenu(); getUserInfo();" class="w3-theme-l3" style="font-family: 'Lato', 'sans-serif';">
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
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 500px;margin-right: 1%">

                    <div>

                        <a class="w3-button"style="text-align: left; width: 100%; padding-left: 20%">
                            <span onclick="document.getElementById('id01').style.display = 'block';">Change Password</span>
                        </a>
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
                                    <div class=" w3-margin w3-large" style="padding-top: 10px">

                                        <label>Enter Old Password</label><br>
                                        <input type="password" id="oldpass" class="w3-input"/><br>

                                        <label>Enter New Password</label><br>
                                        <input type="password" id="newpass" onkeyup="changeConfBack()" class="w3-input"/><br>

                                        <label>Confirm Password</label>
                                        <input type="password" id="confpass" onkeyup="changeConfBack()" class="w3-input"><br><br>
                                        <button onclick="changePassword()" class="w3-input w3-green w3-round-small w3-hover-light-green">Change Password</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--pop up for image upload-->
                    <div class="w3-container">

                        <div id="uploadWindow" class="w3-modal">
                            <div class="w3-modal-content" style="margin-left: 25%;width: 50%">
                                <div class="w3-container w3-theme-l3">
                                    <span onclick="document.getElementById('uploadWindow').style.display = 'none'" class="w3-button w3-large w3-red w3-display-topright">&times;</span>
                                    <div class=" w3-margin w3-large" style="padding-top: 10px">

                                        <div style="margin-left: 30%">
                                            <img id="imgUploaded" src="" class="w3-center" width="60%">
                                        </div>

                                        <form action="#" id="imageUploadForm">
                                            <input onchange="uploadImage()" id="imgInput" name="image" type="file" accept="image/*" class="w3-button w3-theme-l4 w3-input" style="margin: 30px 0px 20px 0px">

                                            <input type="submit" value="Upload Photo" class="w3-input w3-green w3-round-small w3-hover-light-green">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Right div-->
                <div class="w3-col w3-row" style="width: 79%">

                    <!--message showing div-->
                    <div class="w3-card-4 w3-round" id="messageDiv" style="float: right; width: 100%; padding: 10px 10px 10px 40px; margin: 5px; background-color: #42f48f; display: none">
                        <label id="msg"></label>
                        <a
                            href="#"
                            class="w3-right w3-hover-red"
                            onclick="document.getElementById('messageDiv').style.display = 'none'"
                            style="padding: 3px; text-decoration: none">
                            &#x2715;
                        </a>
                    </div>

                    <div class="w3-quarter w3-margin">
                        <img src="" id="userImage" alt="User's Photo" class="w3-round w3-hover-opacity w3-center w3-card-4" width="80%"/>
                        <button onclick="document.getElementById('uploadWindow').style.display = 'block'" class="w3-button w3-green w3-margin w3-round w3-hover-light-green">Change Photo</button>
                    </div>
                    <div class="w3-rest w3-margin w3-card w3-round-large w3-large" style="height: 560px; overflow: auto">

                        <div class="w3-row w3-margin">
                            <div class="w3-half w3-padding-small">
                                <label>First Name</label>
                                <input
                                    type="text"
                                    onblur="updateUserinfo('firstname')"
                                    id="firstname"
                                    class="w3-input w3-theme-l4"
                                    disabled="true"
                                    style="border: 0px">
                                <a
                                    href="#"
                                    onclick="makeEditable('firstname')"
                                    style="text-decoration: none"
                                    class="w3-small w3-text-blue-gray w3-hover-text-blue w3-round">
                                    Change
                                </a>
                            </div>
                            <div class="w3-half w3-padding-small">
                                <label>Last Name</label>
                                <input
                                    type="text"
                                    onblur="updateUserinfo('lastname')"
                                    id="lastname"
                                    class="w3-input w3-theme-l4"
                                    disabled="true"
                                    style="border: 0px">
                                <a
                                    href="#"
                                    onclick="makeEditable('lastname')"
                                    style="text-decoration: none"
                                    class="w3-small w3-text-blue-gray w3-hover-text-blue w3-round">
                                    Change
                                </a>
                            </div>
                        </div>

                        <div class="w3-margin w3-padding-small">
                            <label>Email</label>
                            <input
                                type="email"
                                onblur="updateUserinfo('email')"
                                id="email"
                                class="w3-input w3-theme-l4"
                                disabled="true"
                                style="border: 0px">
                            <a
                                href="#"
                                onclick="makeEditable('email')"
                                style="text-decoration: none"
                                class="w3-small w3-text-blue-gray w3-hover-text-blue w3-round">
                                Change
                            </a>
                        </div>

                        <div class="w3-margin w3-padding-small">
                            <label>Phone</label>
                            <input
                                type="text"
                                onblur="updateUserinfo('phone')"
                                id="phone"
                                class="w3-input w3-theme-l4"
                                disabled="true"
                                style="border: 0px">
                            <a
                                href="#"
                                onclick="makeEditable('phone')"
                                style="text-decoration: none"
                                class="w3-small w3-text-blue-gray w3-hover-text-blue w3-round">
                                Change
                            </a>
                        </div>

                        <div class="w3-margin w3-padding-small">
                            <label>Department</label>
                            <input type="text" id="department" class="w3-input w3-theme-l4" disabled="true" style="border: 0px">
                        </div>

                        <div class="w3-row w3-margin w3-padding-small">
                            <div class="w3-theme-l4 w3-padding-small">
                                <label><b>Access</b></label>
                                <ul id="access" style="list-style: none; color: green">

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </body>
</html>
