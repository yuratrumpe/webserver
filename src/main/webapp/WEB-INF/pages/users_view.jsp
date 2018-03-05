<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users View</title>
</head>
<body>
UsersView Page


<h3>All Users</h3>

<table border="2">
    <tr>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Actions</th>
    </tr>

<c:forEach var="user" items="${requestScope.usersList}">
    <tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.password}</td>
        <td>
            <a href="/updateuser?id=${user.id}">Edit</a>
            &nbsp;&nbsp;
            <a href="/deleteuser?id=${user.id}">Delete</a>


        </td>
    </tr>
</c:forEach>
</table>

<br />

<a href="/adduser">Add New User</a>


</body>
</html>