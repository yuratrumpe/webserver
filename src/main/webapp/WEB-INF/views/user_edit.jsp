<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
    <title>User Edit</title>
</head>
<body>
Admin Page

<h3>Edit User </h3>

<form:form method="POST" modelAttribute="user" action="edit-user-post">
    <table>
        <tr>
            <td>ID :</td>
            <td><form:input path="id" readonly="true"/>
            </td>
            <td><form:errors path="id" />
            </td>
        </tr>

        <tr>
            <td>UserName :</td>
            <td><form:input path="userName" />
            </td>
            <td><form:errors path="userName" />
            </td>
        </tr>

        <tr>
            <td>Password :</td>
            <td><form:input path="password" />
            </td>
            <td><form:errors path="password" />
            </td>
        </tr>

        <tr>
            <td>Role :</td>
            <td><form:input path="role" />
            </td>
            <td><form:errors path="role" />
            </td>
        </tr>

        <tr>
            <td><input type="submit" value="SaveChange" />
            </td>
        </tr>
    </table>
</form:form>

<form method="GET" action="/delete-user">
    <input type="text" hidden name="id" value="${user.id}" />
    <input type="submit" value="DeleteUser"/>
</form>

</body>
</html>
