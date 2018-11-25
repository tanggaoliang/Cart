<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tanggl
  Date: 2018/11/24
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script id="jquery_183" type="text/javascript" class="library" src="jquery-1.8.3.min.js"></script>
<html>
<head>
    <title>购物车列表</title>
</head>
<script>

    $(function () {
        $(function () {
            var allpay = 0;
            $(".productResult").each(function () {
                console.log("wo wo wo");
                allpay = allpay +parseInt ($(this).val());
            })
            allpay = parseInt(allpay)
            $('#allpay').val("总金额:" + allpay);
        })

        $('.productNumber').bind('input propertychange', function () {
            console.log("o o o");
            var pid = $(this).attr("pid");
            var num1 = $('.productNumber[pid=' + pid + ']').val();
            var result = $('.productNumber[pid=' + pid + ']').val() * $('.productPrice[pid=' + pid + ']').val();
            var page = "/addOrderItem";
            $.get(
                page,
                {"num": num1, "pid": pid, "replace": true});
            $('.productResult[pid=' + pid + ']').val(result);
            var allpay = 0;
            $(".productResult").each(function () {
                allpay = allpay +parseInt ($(this).val());
            })
            $('#allpay').val("总金额:" + allpay);
            // console.log(result);
        });

    });

</script>
<body>
<h1 align="center" style="color:green"> 购物车 </h1>
<c:if test="${!empty user}">
    <div align="center">当前用户: ${user.name}
        <a href="listProduct">商品列表</a>
    </div>
</c:if>
<div align="center">
    <table align="center" border="1" cellspacing="0">
        <tr>
            <td>商品ID</td>
            <td>商品名称</td>
            <td>单价</td>
            <td>数量</td>
            <td>小计</td>
            <td>删除</td>
        </tr>
        <c:forEach items="${OrderItemList}" var="oi" varStatus="st">
            <tr>
                <td>${oi.product.id}</td>
                <td>${oi.product.name}</td>
                <td><input pid="${oi.product.id}" class="productPrice" type="number" value="${oi.product.price}"/></td>
                <td><input pid="${oi.product.id}" class="productNumber" type="number" min="1" value="${oi.num}"/></td>
                <td><input pid="${oi.product.id}" class="productResult" type="number"
                           value="${oi.num*oi.product.price}" readonly="readonly"/>
                </td>
                <td><a href="deleteOrderItem?pid=${oi.product.id}">删除</a></td>
            </tr>


        </c:forEach>
        <c:if test="${!empty OrderItemList}">
            <tr>
                <td colspan="6" align="right">
                    <input readonly="readonly" id="allpay" value=""></input>
                </td>
            </tr>
            <tr>
                <td colspan="6" align="right">
                    <a href="createOrder">生成订单</a>
                </td>
            </tr>
        </c:if>

    </table>


</div>

</body>
</html>
