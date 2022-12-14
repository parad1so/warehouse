
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.seoweblab.model.ArrivalOfProduct" %>
<%@ page import="ru.seoweblab.view.jdbcView.SaleProductViewImpl" %>
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
                <th style="color: white; text-align: center">Номер Склада ID</th>
                <th style="color: white; text-align: center" >Список Товаров ID</th>
                <th style="color: white; text-align: center" >Продукт ID</th>
                <th style="color: white; text-align: center" >Имя продукта</th>
                <th style="color: white; text-align: center" >Кол-во</th>
                <th style="color: white; text-align: center" >Цена</th>
            </tr>
            <c:forEach items="${saleProduct}" var="product">
                <tr>
                    <td style="color: white; text-align: center" >${product.id}</td>
                    <td style="color: white; text-align: center" >${product.numberId.getId()}</td>
                    <td style="color: white; text-align: center">${product.warehouseId.getId()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getId()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getProductId().getProductName()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getQuantity()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getSellingPriceId().getPrice()}</td>
                </tr>
            </c:forEach>
        </table>

        <center>
            <form action="${pageContext.request.contextPath}/window/sale" method="post">
                <input type="submit" value="Назад" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button2"  />
            </form>
        </center>
    </center>
</div>
</body>
</html>
