
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.seoweblab.model.ArrivalOfProduct" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DataBase</title>
    <link href="${pageContext.request.contextPath}/view/css/styles.css" rel="stylesheet" type="text/css">

</head>
<body>
<div id="container">
    <br>
    <center>
        <table style="border-spacing: 20px;">
            <tr>
                <th style="color: white; text-align: center">Номер ID</th>
                <th style="color: white; text-align: center">Номер Склада 1 ID</th>
                <th style="color: white; text-align: center">Номер Склада 2 ID</th>
                <th style="color: white; text-align: center" >Список Товаров ID</th>
            </tr>
            <c:forEach items="${sentMovingProduct}" var="product">
                <tr>
                    <td style="color: white; text-align: center" >${product.numberId.getId()}</td>
                    <td style="color: white; text-align: center">${product.warehouseAId.getId()}</td>
                    <td style="color: white; text-align: center">${product.warehouseBId.getId()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getId()}</td>
                </tr>
            </c:forEach>
        </table>
        <center>
            <form action="${pageContext.request.contextPath}/window/moving/send" method="post">
                <input type="submit" value="Отправить в Базу Данных" style="text-align: center; margin: -10%; padding: 30px 32px;"  id="form_button1"  />
            </form>
            <form action="${pageContext.request.contextPath}/window/moving" method="post">
                <input type="submit" value="Назад" style="text-align: center; margin: -10%; padding: 30px 112px;"  id="form_button2"  />
            </form>

        </center>
    </center>
</div>
</body>
</html>