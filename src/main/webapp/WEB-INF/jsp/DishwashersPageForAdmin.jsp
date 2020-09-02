<%--
  Created by IntelliJ IDEA.
  User: Slash
  Date: 18.08.2020
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<h1>Посудомоечные машины:</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Type</th>
        <th>Maker</th>
        <th>Counter</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${dishwashers}" var="dishwasher" >
        <tr>
            <td><c:out value="${dishwasher.id}"/> </td>
            <td><c:out value="${dishwasher.type}"/> </td>
            <td><c:out value="${dishwasher.maker}"/> </td>
            <td><c:out value="${dishwasher.counter}"/> </td>
            <td><c:out value="${dishwasher.price}"/> </td>
        </tr>
    </c:forEach>

</table>
<h3>Добавить Посудомоечную машину</h3>
<form method="post">
    <input type="text" name="type"  placeholder="type"/>
    <input type="text" name="maker"  placeholder="maker"/>
    <input type="text" name="counter"  placeholder="counter"/>
    <input type="text" name="price"  placeholder="price"/>
    <button type="submit" >Добавить</button>
</form>
<h3><a href="/admin">На страницу администратора</a></h3>
</body>
</html>
