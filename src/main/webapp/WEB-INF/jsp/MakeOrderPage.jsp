<%--
  Created by IntelliJ IDEA.
  User: Slash
  Date: 16.09.2020
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<form method="post">
    <h3>Введите ФИО</h3>
    <div><label><input type="text" name="fullName" required="true" pattern=".{5,20}" title="от 5 до 20 символов"></label></div>
    <h3>Введите номер телефона</h3>
    <div><label><input type="text" name="phoneNumber" required="true" pattern=".{5,12}" title="от 5 до 12 символов"></label></div>
    <input type="submit" value="Оформить">

    <h3><a href="/">На главную страницу</a></h3>
</form>
</body>
</html>
