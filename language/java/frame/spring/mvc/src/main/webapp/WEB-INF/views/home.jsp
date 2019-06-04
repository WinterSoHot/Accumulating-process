<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spitter</title>
    <% String path = request.getContextPath();%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
    <div class="container">
        <h1>Welcome to Spitter</h1>
        <a href="<c:url value='/spittles'/>">Spittles</a>
        <a href="<c:url value='/spittles/register'/>">Register</a>
    </div>
    <script src="<%=path%>/js/jquery-3.1.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
</body>
</html>
