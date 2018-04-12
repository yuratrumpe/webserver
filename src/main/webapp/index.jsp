<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

Welcome Page

<h3>Login</h3>

<form method="POST" action="/login">

    <label><input type="text" name="login"></label>Login<br>
    <br />
    <label><input type="password" name="password"></label>Password<br>
    <br />
    <input type="submit" value="Login"><br>
</form>



<a href="/admin/showusers">Go go go admin</a>
<a href="/user/user">Go go go user</a>

<a href="/logout">Logout</a>

</body>
</html>
