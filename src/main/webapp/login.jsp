<%--
  Created by IntelliJ IDEA.
  User: Tanggl
  Date: 2018/11/24
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1 align="center" style="color:green"> LOGIN </h1>
<div align="right"><h2><a href="register.jsp">我要注册</a></h2></div>
<div align="center">
    <form action="login" method="post">
        账号:<input type="text" name="username" required="required"><br>
        密码:<input type="password" name="password" required="required"><br>
        <input type="submit" value="登录">
    </form>
</div>


</body>
</html>
