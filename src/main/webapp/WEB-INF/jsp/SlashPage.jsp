<%--
  Created by IntelliJ IDEA.
  User: Slash
  Date: 19.08.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Магазин-бт</title>
</head>
<body>
<b>Номер для заказов: 8-999-999-99-99</b>
<h1>Магазин бытовой техники для вашего дома!</H1>
<form method="get" action="/search/">
    <h3>Поиск товара по id</h3>
    <div><label><input type="number" name="id" placeholder="Введите ID" required="true"></label></div>
    <input type="submit" value="Найти">
</form>
<br/>
<h3>Каталог товаров:</h3>
<a href="/products/refrigerators">Холодильники</a>
<br/>
<a href="/products/washmachines">Стиральные машины</a>
<br/>
<a href="/products/dishwashers">Посудомоечные машины</a>
<br/>
<br/>

<c:if test="${loginName==null}">
<a href="/registration">Регистация на сайте</a>
    <br/>
<a href="/login">Авторизация на сайте</a>
</c:if>

<c:if test="${not empty loginName}">
    <a href="/logout">Выйти с сайта</a>
</c:if>
<c:if test="${not empty basketProducts}">
    <div style="position: absolute;right:100px;top: 100px">
        <h3><a href="/basketproducts" >Корзина товаров</a></h3>

    </div>
</c:if>
</body>
</html>
