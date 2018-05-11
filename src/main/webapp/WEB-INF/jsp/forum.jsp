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
        <spring:url value="/web-resources/js/jquery-1.12.4.js" var="jquery"/>
        <spring:url value="/web-resources/js/jquery-ui.js" var="jqueryUi"/>
        <spring:url value="/web-resources/css/jquery-ui.min.css" var="jQueryUIcss"/>
        <spring:url value="/web-resources/js/forum.js" var="forumJs"/>
        <spring:url value="/web-resources/js/postadd.js" var="postAddJs"/>
        <spring:url value="/web-resources/js/myComplaintsJS.js" var="myComplaintsJs"/>
        <spring:url value="/web-resources/js/menuremover.js" var="menuJs"/>
        <spring:url value="/web-resources/js/notification.js" var="notificationJs"/>
        <spring:url value="/web-resources/js/commentreply.js" var="commentreplyJs"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/images/akash.jpg" var="img"/>
        <spring:url value="/web-resources/images/loading.gif" var="loading"/>


        <link href="${jQueryUIcss}" rel="stylesheet" type="text/css"/>
        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${jquery}" type="text/javascript"></script>
        <script src="${jqueryUi}" type="text/javascript"></script>
        <script src="${forumJs}" type="text/javascript"></script>
        <script src="${postAddJs}" type="text/javascript"></script>
        <script src="${menuJs}" type="text/javascript"></script>
        <script src="${myComplaintsJs}" type="text/javascript"></script>
        <script src="${notificationJs}" type="text/javascript"></script>
        <script src="${commentreplyJs}" type="text/javascript"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
    </head>
    <body onload="setMenu(); getRecentPosts(); getAllUsers(); getAllTags(); countComplaint(); countRequisition()" class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">

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
                    <a class="w3-button w3-round-small w3-theme-l2" style="text-decoration: none;height: 100%" href="forum">Forum</a>
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
                <div class="w3-theme-d1 w3-col" style="width: 20%; height: 450px">
                    <span class="w3-button" style="text-align: left; width: 100%; padding-left: 20%"><span onclick="getAllTags(); document.getElementById('id01').style.display = 'block'">Add New Posts </span></span><br>
                    <span onclick="getRecentPosts()" class="w3-button" style="text-align: left; width: 100%; padding-left: 20%">Recent Posts</span><br>
                    <span onclick="getOwnPosts()" class="w3-button" style="text-align: left; width: 100%; padding-left: 20%">My Posts</span><br>
                    <a id="statistics"   class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a id="manageHr"     class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="hr">Manage Human Resources</a>
                    <a id="manageOffice" class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="office">Add Office Resources</a>


                    <!--<##Pop up window-->
                    <div class="w3-container">

                        <div id="id01" class="w3-modal">
                            <div class="w3-modal-content w3-round" style="margin-left: 25%;width: 50%">
                                <div class="w3-container w3-theme-l3 w3-round">
                                    <span onclick="document.getElementById('id01').style.display = 'none'" class="w3-button w3-large w3-red w3-display-topright">&times;</span>
                                    <div class=" w3-margin w3-large" style="padding: 3%">
                                        <textarea id="postContent" class="w3-large w3-input w3-round-small w3-light-gray" style="resize: vertical; height: 150px;" required="true" placeholder="Write Somethings..."></textarea> <br>

                                        <div style="margin-bottom: 15px">
                                            <input id="postTags" onkeypress="addToTagList(event, 'postTags')" style="margin-bottom: 15px" class="ui-widget w3-input w3-large w3-round-small w3-light-gray" type="text" placeholder="Type Tag: e.g., computer lab">

                                        </div>
                                        <input onclick="addPost()" value="Post" type="button" class="w3-button w3-input w3-green w3-hover-light-green w3-round" style="margin-top: 2%">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Middle div-->
                <div class="w3-theme-l2 w3-col" style="margin: 0% 1% 0% 1%;width: 61%">
                    <div class="w3-card w3-padding" style="margin-top: 10px">
                        <input id="tagSearch" onkeypress="addToTagList(event, 'tagSearch')" style="margin-left: 2%;width: 50%; margin-bottom: 1%" class="ui-widget w3-large w3-input w3-theme-l3" type="text" placeholder="Search Post By Tag">

                    </div>

                    <img id="loading" src="${loading}" height="300px" style="display: block; margin-left: auto; margin-right: auto">

                    <h2 id="noContent" style="display: none; margin-top: 15%; margin-bottom: 30%; text-align: center">No Post Available</h2>
                    <!--posts div-->
                    <div id="allPostDiv" style="overflow: auto; height: 500px; margin-top: 10px">

                        <!--posts are shown here from AJAX by forum.js-->

                    </div>
                </div>

                <!--Right div-->
                <div class="w3-theme-l5 w3-col" style="width: 17%">
                    <div id="allUsersDiv" style="overflow: auto; height: 530px; padding-left:1%">

                        <!--users are shown here-->

                    </div>
                    <div style="margin-top: 10px">
                        <input class="w3-large w3-input w3-theme-l3" style="position: fixed;" type="text" placeholder="Search User" name="search">
                    </div>

                </div>

            </div>
        </div>

    </body>
</html>
