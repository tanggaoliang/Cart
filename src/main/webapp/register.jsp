<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<h1 align="center" style="color:green"> REGISTER </h1>
<div align="right"><h2><a href="login.jsp">我要登陆</a></h2></div>
<div align="center">
    <form action="register" method="post">
        <span>账号:</span><input type="text" required="required" name="username"><br>
        <span>密码:</span><input type="password" required="required" name="password"><br>
        <input type="submit" value="注册">
    </form>


</div>
</body>
</html>