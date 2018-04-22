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
        <spring:url value="/web-resources/css/jquery-ui.min.css" var="jQueryUIcss"/>
        <spring:url value="/web-resources/js/forum.js" var="forumJs"/>
        <spring:url value="/web-resources/js/postadd.js" var="postAddJs"/>
        <spring:url value="/web-resources/js/jquery-1.12.4.js" var="jquery"/>
        <spring:url value="/web-resources/js/jquery-ui.js" var="jqueryUi"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/images/akash.jpg" var="img"/>


        <link href="${jQueryUIcss}" rel="stylesheet" type="text/css"/>
        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${jquery}" type="text/javascript"></script>
        <script src="${jqueryUi}" type="text/javascript"></script>
        <script src="${forumJs}" type="text/javascript"></script>
        <script src="${postAddJs}" type="text/javascript"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
    </head>
    <body onload="getRecentPosts(); getAllUsers();" class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">

        <div>
            <div class="w3-theme-d3 w3-row" style="height: 50px">

                <!--navigation ber-->

                <div class="navbar-menu w3-right w3-large" style="height: 100%">
                    <a class="w3-button" style="text-decoration: none; height: 100%" href="complaints">Complaints</a>
                    <a class="w3-button" style="text-decoration: none;" href="requisitions">Requisitions</a>
                    <a class="w3-button" style="text-decoration: none;" href="#">Leave</a>
                    <a class="w3-button" style="text-decoration: none;" href="forum">Forum</a>
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
                    <span class="w3-button" style="text-align: left; width: 100%; padding-left: 20%"><span onclick="getAllTags(); document.getElementById('id01').style.display = 'block'">Add New Posts </span></span><br>
                    <span onclick="getRecentPosts()" class="w3-button" style="text-align: left; width: 100%; padding-left: 20%">Recent Posts</span><br>
                    <span onclick="getOwnPosts()" class="w3-button" style="text-align: left; width: 100%; padding-left: 20%">My Posts</span><br>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="statistics">Statistics</a>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="hr">Manage Human Resources</a>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="office">Manage Office Resources</a>


                    <!--<##Pop up window-->
                    <div class="w3-container">

                        <div id="id01" class="w3-modal">
                            <div class="w3-modal-content w3-round" style="margin-left: 25%;width: 50%">
                                <div class="w3-container w3-theme-l3 w3-round">
                                    <span onclick="document.getElementById('id01').style.display = 'none'" class="w3-button w3-large w3-red w3-display-topright">&times;</span>
                                    <div class=" w3-margin w3-large" style="padding: 3%">
                                        <form action="" method="post">
                                            <textarea id="postContent" class="w3-large w3-input w3-round-small w3-light-gray" style="resize: vertical; height: 150px; margin-bottom: 15px" required="true" placeholder="Write Somethings..."></textarea> <br>

                                            <input id="postTags" class="ui-widget w3-input w3-large w3-round-small w3-light-gray" type="text" placeholder="Type Tag: e.g., computer lab">
                                            <div style="margin-top: 15px; margin-bottom: 15px">
                                                <% for (int i = 0; i < 5; i++) {%>
                                                <label class="w3-theme-l3 w3-margin-top w3-margin-left w3-text-black">Tags <a href="" class="w3-small" style="text-decoration: none;">&#x2715;</a></label>
                                                <%}%>
                                            </div>
                                            <input value="Post" class="w3-button w3-green w3-hover-light-green w3-round" style="margin-top: 2%">

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Middle div-->
                <div class="w3-theme-l2 w3-col" style="margin: 0% 1% 0% 1%;width: 61%">
                    <div class="w3-card w3-padding" style="margin-top: 10px">
                        <input id="tagSearch" style="margin-left: 2%;width: 50%; margin-bottom: 1%" class="ui-widget w3-large w3-input w3-theme-l3" type="text" placeholder="Search Post By Tag">

                        <% for (int i = 0; i < 5; i++) {%>
                        <label class="w3-theme-l3 w3-margin-top w3-margin-left w3-text-black">Tags <a href="" class="w3-small" style="text-decoration: none;">&#x2715;</a></label>
                        <%}%>
                    </div>

                    <!--posts div-->
                    <div id="allPostDiv" style="overflow: auto; height: 470px; margin-top: 10px">

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
