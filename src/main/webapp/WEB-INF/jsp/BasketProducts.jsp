<%--
  Created by IntelliJ IDEA.
  User: Slash
  Date: 20.08.2020
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>Корзина покупок</title>
    <style>
        table, td, th {
            border: 1px solid #999;
            padding: 5px;
        }
    </style>
</head>

<h3>Ваша корзина:</h3>

<table>
    <tr>
        <th>Id</th>
        <th>Type</th>
        <th>Maker</th>
        <th>Counter</th>
        <th>Price</th>
    </tr>
<c:if test="${not empty basketProducts}">

        <c:forEach items="${basketProducts}" var="basket">
    <tr>
            <th>${basket.product.id}</th>
            <th>${basket.product.type}</th>
            <th>${basket.product.maker}</th>
            <th>${basket.counter}</th>
            <th>${basket.product.price}</th>

    </tr>
        </c:forEach>

</c:if>

    <h3>Цена:${overPrice}</h3>
</table>

<h3><a href="/makeorder">Оформить заказ</a></h3>

<h3><a href="/">На главную страницу</a></h3>

</body>
</html>
