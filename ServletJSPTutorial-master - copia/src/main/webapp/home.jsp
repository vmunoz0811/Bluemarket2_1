<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <h1><%= "Hello, "+ request.getParameter("username") + "!" %></h1>
        <h3><%= "Your role is " + request.getAttribute("role") %></h3>
        <br/>
        <a href="./index.html">Logout</a>
    </body>
</html>