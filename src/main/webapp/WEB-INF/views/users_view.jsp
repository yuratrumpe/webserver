<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users View</title>
</head>
<body>
Admin Page


<h3>All Users</h3>

<table border="2">
    <tr>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>

<c:forEach var="user" items="${usersList}">
    <tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.password}</td>
        <td>${user.role}</td>
        <td>
            <a href="/edit-user-get?id=${user.id}">Edit</a>
            &nbsp;&nbsp;
            <a href="/delete-user?id=${user.id}">Delete</a>


        </td>
    </tr>
</c:forEach>
</table>

<br />

<a href="/add-user-get">Add New User</a>

<br />

<a href="/logout">Logout</a>

</body>
</html>