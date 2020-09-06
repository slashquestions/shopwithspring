<%--
  Created by IntelliJ IDEA.
  User: Slash
  Date: 06.09.2020
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Магазин-бт</title>
</head>
<body>
<div style="position: absolute;left:20px;top: 20px;border: 1px solid #999;padding:5px;padding-left:20px
;padding-right:20px;padding-top:100px;padding-bottom:100px">
    Тут фото продукта
</div>

<div style="position: absolute;left:200px;top: 20px">
        <h3>Тип продукта: ${product.type}</h3>
        <h3>ID продукта: ${product.id}</h3>
        <h3>Производитель: ${product.maker}</h3>
        <h3>Кол-во: ${product.counter}</h3>
        <h3>Цена продукта: ${product.price}</h3>
    <h3><a href="/" >На главную страницу</a></h3>
</div>


</body>
</html>
