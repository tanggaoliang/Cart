<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script id="jquery_183" type="text/javascript" class="library" src="jquery-1.8.3.min.js"></script>
<h1 align="center" style="color:green"> 商品列表 </h1>
<c:if test="${!empty user}">
    <div align="right" style="margin-top: 20px">当前用户: ${user.name} <a href="listOrderItem.jsp">查看购物车 </a><a
            href="login.jsp"> 切换用户</a><a
            href="/listOrderItem?history=true"> 购物历史</a></div>
</c:if>
<c:if test="${empty user}">
    <div align="right" style="margin-top: 20px"><a href="login.jsp">登录</a></div>
</c:if>

<title>商品列表</title>
<div style="margin-top: 10px">
    <table align='center' border='1' cellspacing='0' style="margin-top: 50px">
        <tr>
            <td>id</td>
            <td>名称</td>
            <td>价格</td>
            <td>购买</td>
        </tr>
        <%--<jsp:useBean id="products" scope="request" type="java.util.List"/>--%>
        <c:forEach items="${products}" var="product" varStatus="st">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>

                    <form action="addOrderItem" method="post">

                        数量<input type="text" value="1" name="num">
                        <input type="hidden" name="pid" value="${product.id}">
                        <input class="addCartButton" pid="${product.id}" type="submit"
                               value="加入购物车">
                    </form>
                </td>

            </tr>
        </c:forEach>

    </table>
</div>