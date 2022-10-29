<html>
<head>
    <title>DataBase</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/styles/styles.css">
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
            <#list productFromServer as product>
                <tr>
                    <td style="color: white; text-align: center" >${product.id}</td>
                    <td style="color: white; text-align: center" >${product.numberId.getId()}</td>
                    <td style="color: white; text-align: center">${product.warehouseId.getId()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getId()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getProductId().getProductName()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getQuantity()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getPurchasePriceId().getPrice()}</td>
                </tr>
            </#list>
            </c:forEach>
        </table>

        <center>
            <form action="/window/arrival" method="post">
                <input type="submit" value="Назад" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button2"  />
            </form>
        </center>
    </center>
</div>
</body>
</html>