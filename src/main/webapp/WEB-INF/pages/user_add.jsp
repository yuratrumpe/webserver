<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>User Add</title>
</head>
<body>
UsersAdd Page

<h3>Add New User</h3>

<form method="POST" action="/adduser">

    <label><input type="text" name="name"></label>Name<br>
    <br />
    <label><input type="text" name="password"></label>Password<br>
    <br />
    <input type="submit" value="AddUser"><br>
</form>

</body>
</html>
