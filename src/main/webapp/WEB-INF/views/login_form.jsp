
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>Login Form</title>

    Welcome Page

    <h3>Login</h3>


</head>
<body>


<form:form method="POST" commandName="user" action="check-user">
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
            <td><input type="submit" value="Login" />
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
