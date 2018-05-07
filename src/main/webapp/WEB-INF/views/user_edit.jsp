<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>User Edit</title>
</head>
<body>
Admin Page

<h3>Edit User </h3>

<form method="POST" action="/edit-user-post">

    <strong>ID: </strong> ${user.id}
    <input type="text" hidden name="id" value="${user.id}">
    <br />
    <label><input type="text" name="name" value="${user.userName}"></label>Name<br>
    <br />
    <label><input type="text" name="password" value="${user.password}"></label>Password<br>
    <br />
    <label><input type="text" name="role" value="${user.role}"></label>Role<br>
    <br />
    <input type="submit" value="SaveChanges"><br>
</form>

<form method="GET" action="/delete-user">
    <input type="text" hidden name="id" value="${user.id}" />
    <input type="submit" value="DeleteUser"/>
</form>

</body>
</html>
