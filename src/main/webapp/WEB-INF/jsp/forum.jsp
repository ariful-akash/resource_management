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
        <spring:url value="/web-resources/js/forum.js" var="forumJs"/>
        <spring:url value="/web-resources/js/postadd.js" var="postAddJs"/>
        <spring:url value="/web-resources/js/jquery-1.12.4.js" var="jquery"/>
        <spring:url value="/web-resources/js/jquery-ui.js" var="jqueryUi"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>
        <spring:url value="/web-resources/images/akash.jpg" var="img"/>


        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <script src="${forumJs}" type="text/javascript"></script>
        <script src="${postAddJs}" type="text/javascript"></script>
        <script src="${jquery}" type="text/javascript"></script>
        <script src="${jqueryUi}" type="text/javascript"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
    </head>
    <body onload="getRecentPosts(); getAllUsers(); getAllTags()" class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">

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
                    <span class="w3-button" style="text-align: left; width: 100%; padding-left: 20%"><span onclick="document.getElementById('id01').style.display = 'block'">Add New Posts </span></span><br>
                    <span onclick="getRecentPosts()" class="w3-button" style="text-align: left; width: 100%; padding-left: 20%">Recent Posts</span><br>
                    <span onclick="getOwnPosts()" class="w3-button" style="text-align: left; width: 100%; padding-left: 20%">My Posts</span><br>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="admin/statistics">Statistics</a>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="admin/hr">Manage Human Resources</a>
                    <a class="w3-button" style="text-decoration: none;text-align: left; width: 100%; padding-left: 20%" href="admin/office">Manage Office Resources</a>


                    <!--<##Pop up window-->
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
                <div class="w3-theme-l2 w3-col" style="margin: 0% 1% 0% 1%;width: 61%">
                    <div class="w3-card w3-padding" style="margin-top: 10px">
                        <input style="margin-left: 2%;width: 50%; margin-bottom: 1%" class="w3-large w3-input w3-theme-l3" type="text" placeholder="Search Post By Tag" name="searchPost">

                        <% for (int i = 0; i < 5; i++) {%>
                        <label class="w3-theme-l3 w3-margin-top w3-margin-left w3-text-black">Tags <a href="" class="w3-small" style="text-decoration: none;">&#x2715;</a></label>
                        <%}%>
                    </div>

                    <!--posts div-->
                    <div id="allPostDiv" style="overflow: auto; height: 470px; margin-top: 10px">

                        <!--posts are shown here from AJAX by forum.js-->
                        <!--postCommentDiv-->
                        <div class="w3-row w3-card w3-margin" style="display: none">
                            <div class="w3-col" style="width: 5%; margin-right: 3%; padding: 1% 0% 0% 1%">
                                <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                            </div>
                            <div class="w3-col" style="width: 92%">
                                <label class="w3-small w3-text-dark-gray"><b>Ariful Islam Akash</b></label><br>
                                <label class="w3-tiny w3-text-dark-gray">14/04/2018</label>
                            </div>

                            <div style="margin: 8% 5% 1% 5%">
                                <span>As a describer of life and manners, he must be allowed to
                                    stand perhaps the first of the first rank. His humour, which,
                                    as Steele observes, is peculiar to himself, is so happily
                                    diffused as to give the grace of novelty to domestic scenes
                                    and daily occurrences. He never "o'ersteps the modesty of
                                    nature," nor raises merriment or wonder by the violation
                                    of truth. His figures neither divert by distortion nor amaze
                                    by aggravation. He copies life with so much fidelity that he
                                    can be hardly said to invent; yet his exhibitions have an air
                                    so much original, that it is difficult to suppose them not
                                    merely the product of imagination.
                                </span>
                            </div>
                            <!--comment div-->

                            <!--allCommentDiv-->
                            <div> 

                                <!--placeCommentDiv-->
                                <div class="w3-margin" style="padding-left: 3%">
                                    <input class="w3-input w3-round-large" style="width: 60%; display: inline;padding-left: 2%" type="text" placeholder="Write a Comment...">
                                    <input class="w3-btn w3-round w3-theme-d1" style="display: inline; margin-left: 1%" value="Comment">
                                </div>
                                <% for (int j = 0; j < 5; j++) {%>

                                <!--commentDiv-->
                                <div class="w3-row w3-margin">

                                    <!--imgDiv-->
                                    <div class="w3-col" style="width: 4%; margin-right: 3%; padding: 1% 0% 0% 1%">
                                        <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                                    </div>

                                    <!--nameDiv-->
                                    <div class="w3-col" style="width: 92%">
                                        <label class="w3-small w3-text-dark-gray"><b>Ariful Islam Akash</b></label><br>
                                        <label class="w3-tiny w3-text-dark-gray">14/04/2018</label>
                                    </div>

                                    <!--showCommentsDiv-->
                                    <div style="margin: 7% 5% 1% 3%">

                                        <!--showTextCommentDiv-->
                                        <div  class="w3-white w3-round-large w3-padding">   
                                            <span>As a describer of life and manners, he must be allowed to.
                                                As a describer of life and manners, he must be allowed to</span>
                                        </div>

                                        <!--Comment replay div-->
                                        <div style="margin-left: 3%">
                                            <% for (int k = 0; k < 5; k++) {%>

                                            <!--single comment Reply div-->
                                            <div class="w3-row" style="display:">

                                                <!--reply img  div-->
                                                <div class="w3-col" style="width: 3%; margin-right: 4%; padding: 1% 1% 0% 1%">
                                                    <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                                                </div>

                                                <!--reply name div-->
                                                <div class="w3-col" style="width: 92%">

                                                    <!--reply name label-->
                                                    <label class="w3-small w3-text-dark-gray"><b>Ariful Islam Akash</b></label><br>

                                                    <!--reply date label-->
                                                    <label class="w3-tiny w3-text-dark-gray">14/04/2018</label>
                                                </div>

                                                <!--reply content div-->
                                                <div class="w3-white w3-round-large w3-padding" style="margin: 8% 0% 1% 2%">

                                                    <span >As a describer of life and manners, he must be allowed to.
                                                        As a describer of life and manners, he must be allowed to</span>
                                                </div>
                                            </div>
                                            <%}%>

                                            <!--reply input field div-->
                                            <div class="w3-margin" style="padding-left: 2%">
                                                <input class="w3-input w3-round-large" style="width: 60%; display: inline;padding-left: 2%" type="text" placeholder="Write a reply...">
                                                <input class="w3-btn w3-round w3-theme-d1" style="display: inline;width: 100px; margin-left: 1%" value="Comment">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                            </div>
                        </div>

                    </div>
                </div>

                <!--Right div-->
                <div class="w3-theme-l5 w3-col" style="width: 17%">
                    <div id="allUsersDiv" style="overflow: auto; height: 530px; padding-left:1%">
                        <% for (int i = 0;
                                    i < 20; i++) { %>
                        <div class="w3-row w3-padding w3-round-small w3-hover-blue-grey" style="display: none">
                            <div class="w3-col" style="width: 10%; margin-right: 15%">
                                <img src="${img}" class="w3-circle" style="width: 30px;height: 30px" alt="#">
                            </div>
                            <div onclick="getUserPosts(106)" class="w3-col" style="width: 75%">
                                <label>Ariful Islam Akash</label><br>
                                <label class="w3-small">Assistant Professor</label>
                            </div>


                        </div>
                        <%}%>

                    </div>
                    <div style="margin-top: 10px">
                        <input class="w3-large w3-input w3-theme-l3" style="position: fixed;" type="text" placeholder="Search User" name="search">
                    </div>

                </div>

            </div>
        </div>

    </body>
</html>
