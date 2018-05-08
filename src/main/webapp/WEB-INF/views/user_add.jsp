<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
    <title>User Add</title>
</head>
<body>
Admin Page

<h3>Add New User</h3>

<form:form method="POST" modelAttribute="user" action="add-user-post">
    <table>
        <tr>
            <td>UserName :</td>
            <td><form:input path="userName" />
            </td>
            <td><form:errors path="userName" />
            </td>
        </tr>
        <tr>
        <tr>
            <td>Password :</td>
            <td><form:password path="password" />
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
            <td><input type="submit" value="AddUser" />
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
