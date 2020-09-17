<%--
  Created by IntelliJ IDEA.
  User: Slash
  Date: 20.08.2020
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Посудомоечные машины</title>
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
    <b>${basket.product.id}</b>
    <b>${basket.product.type}</b>
    <b>${basket.product.maker}</b>
    <b>${basket.counter}</b>
    <b>${basket.product.price}</b>
    <br/>
</c:forEach>
    </div>
</c:if>

<h2>Выберите Посудомоечную машину:</h2>
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
    <c:forEach items="${dishwashers}" var="dishwasher" >
        <tr>
            <th><c:out value="${dishwasher.id}"/></th>
            <th><c:out value="${dishwasher.maker}"/></th>
            <th><c:out value="${dishwasher.counter}"/></th>
            <th><c:out value="${dishwasher.price}"/></th>
        </tr>
    </c:forEach>

</table>
<h3><a href="/">На главную страницу</a></h3>


</body>
</html>