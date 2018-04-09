<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <!--spring-url link-->
        <spring:url value="/web-resources/js/service_test.js" var="service_test_JS"/>

        <script src="${service_test_JS}" type="text/javascript"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <p>This is the index page</p>

        <a href="/office_resource_management/add">Add Person</a>

        <input id="date" type="date"/>

        <input id="time" type="time"/>

        <br>
        <a onclick="insertPost()" href="#">Insert Post</a> <br>
        <a onclick="editPost()" href="#">Edit Post</a> <br>
        <a onclick="insertComment()" href="#">Insert Comment</a> <br>
        <a onclick="editComment()" href="#">Edit Comment</a> <br>
    </body>
</html>
