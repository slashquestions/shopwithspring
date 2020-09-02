<%--
  Created by IntelliJ IDEA.
  User: Slash
  Date: 19.08.2020
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Холодильники</title>
    <style>
        table, td, th {
            border: 1px solid #999;
            padding: 5px;
        }
    </style>
</head>
<body>
<c:if test="${not empty basketProducts}">
    <div style="position: absolute;right:100px;top: 20px">
        <h3><a href="/basketproducts" >Ваша корзина:</a></h3>
        <c:forEach items="${basketProducts}" var="basket">
            <b>${basket.id}</b>
            <b>${basket.type}</b>
            <b>${basket.maker}</b>
            <b>${basket.counter}</b>
            <b>${basket.price}</b>
            <br/>
        </c:forEach>
    </div>
</c:if>
<h2>Выберите холодильник:</h2>
<c:if test="${messageAboutProduct!=null}">
    <h4>${messageAboutProduct}</h4>
</c:if>
<form method="post">
    <h3>Введите id товара</h3>
    <div><label><input type="number" name="id" required="true"></label></div>
    <input type="submit" value="Добавить в корзину">
</form>

<table>
    <tr>
        <th>Id</th>
        <th>Maker</th>
        <th>Counter</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${refrigerators}" var="refrigerator" >
        <tr>
            <th><c:out value="${refrigerator.id}"/></th>
            <th><c:out value="${refrigerator.maker}"/></th>
            <th><c:out value="${refrigerator.counter}"/></th>
            <th><c:out value="${refrigerator.price}"/></th>
        </tr>
    </c:forEach>

</table>
<h3><a href="/">На главную страницу</a></h3>

</body>
</html>
