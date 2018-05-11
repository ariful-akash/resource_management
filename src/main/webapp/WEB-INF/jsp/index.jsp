<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <!--spring-url link-->

        <spring:url value="/web-resources/css/w3.css" var="css"/>
        <spring:url value="/web-resources/css/w3-theme-blue-grey.css" var="w3BlueDarkTheme"/>

        <link href="${css}" rel="stylesheet" type="text/css"/>
        <link href="${w3BlueDarkTheme}" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>

    <body class="w3-theme-l4" style="font-family: 'Lato', 'sans-serif';">


        <div class="w3-row w3-round-small w3-theme-l2" style="margin:15% 33% 0% 33%;padding: 3% 1% 3% 1%">
            <form action="/office_resource_management/login" method="post">

                <input class="w3-large w3-input  w3-round" name="email" type="text" placeholder="e.g., email@example.com">
                <input class="w3-large w3-input w3-round w3-margin-top" name="password"  type="password" placeholder="e.g., ********">

                <input class="w3-large w3-button w3-margin-top w3-round w3-theme-d3" type="submit" value="Login">
            </form>
        </div>
    </body>
</html>
