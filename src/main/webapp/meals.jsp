
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>

<html>
<head>
    <title>Meals list</title>
    <style type="text/css">
        TABLE {
            width: 500px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
        <c:if test="${meal.excess == true}">
        <tr style="color: red">
            <td>${f:formatLocalDateTime(meal.dateTime, 'dd-MM-yyyy hh:mm')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
        </c:if>
        <c:if test="${meal.excess == false}">
            <tr style="color: darkgreen">
                <td>${f:formatLocalDateTime(meal.dateTime, 'dd-MM-yyyy hh:mm')}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
