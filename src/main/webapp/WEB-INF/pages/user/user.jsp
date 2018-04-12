<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>User Page</title>
</head>
<body>
User Page

<h3>Hi user, ${user.userName} </h3>

<br />

<a href="/logout">Logout</a>

</body>
</html>