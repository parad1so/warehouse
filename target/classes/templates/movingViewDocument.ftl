<html>
<head>
    <title>DataBase</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
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
                <th style="color: white; text-align: center" >Продукт ID</th>
                <th style="color: white; text-align: center" >Имя продукта</th>
                <th style="color: white; text-align: center" >Кол-во</th>
            </tr>
            <#list productFromServer as product>
                <tr>
                    <td style="color: white; text-align: center">${product.id}</td>
                    <td style="color: white; text-align: center">${product.numberId.getId()}</td>
                    <td style="color: white; text-align: center">${product.warehouseAId.getId()}</td>
                    <td style="color: white; text-align: center">${product.warehouseBId.getId()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getId()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getProductId().getProductName()}</td>
                    <td style="color: white; text-align: center">${product.listOfProductId.getQuantity()}</td>
                </tr>
            </#list>
        </table>

        <center>
            <form action="/window/moving" method="post">
                <input type="submit" value="Назад" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button2"  />
            </form>
        </center>
    </center>
</div>
</body>
