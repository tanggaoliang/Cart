<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanggl
  Date: 2018/11/25
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>我的订单</title>
</head>
<body>
<h1 align="center" style="color:green"> 购物历史 </h1>
<c:if test="${!empty user}">
    <div align="center">当前用户: ${user.name}
        <a href="listProduct">商品列表</a>
    </div>
</c:if>
<table align="center" cellspacing="0" border="1">
    <tr>
        <td>产品ID</td>
        <td>产品名称</td>
        <td>产品数量</td>
    </tr>
    <c:if test="${!empty OrderItemList}">
        <c:forEach items="${OrderItemList}" var="item" varStatus="st">
            <tr>
                <td>${item.product.id}</td>
                <td>${item.product.name}</td>
                <td>${item.num}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
