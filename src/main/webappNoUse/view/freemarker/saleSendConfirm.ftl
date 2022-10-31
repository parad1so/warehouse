<html>
<head>
    <title>DataBase</title>
    <link href="/styles/styles.css" rel="stylesheet" type="text/css">

</head>
<body>
<#if sentSaveProduct??>
    <div style="text-align: center;">
        <div id="container">
            <CENTER>
                <table style="border-spacing: 20px;">
                    <tr>
                        <th style="color: white; text-align: center">Номер ID</th>
                        <th style="color: white; text-align: center">Номер Склада ID</th>
                        <th style="color: white; text-align: center" >Список Товаров ID</th>
                    </tr>
                    <#list sentSaveProduct as product>
                        <tr>
                            <td style="color: white; text-align: center" >${product.numberId.getId()}</td>
                            <td style="color: white; text-align: center">${product.warehouseId.getId()}</td>
                            <td style="color: white; text-align: center">${product.listOfProductId.getId()}</td>
                        </tr>
                    </#list>
                </table>

                <form action="/window/sale/send" method="post" enctype="multipart/form-data">
                    <input type="submit" value="Отправить в Базу Данных" style="text-align: center; margin: -10%; padding: 30px 32px;"  id="form_button1"  />
                </form>
                <form action="/window/sale" method="post">
                    <input type="submit" value="Назад" style="text-align: center; margin: -10%; padding: 30px 112px;"  id="form_button2"  />
                </form>
            </CENTER>
        </div>
    </div>
<#else>
    <div style="text-align: center;">
        <div id="container">
            <h1><span style="color: #FF0066">&bull;</span> ОШИБКА! <span style="color: #FF0066">&bull;</span></h1>
            <div class="underline"></div>
            <form action="/window/sale" method="post">
                <input type="submit" value="Назад" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button1"  />
            </form>
        </div>
    </div>

</#if>
</body>
</html>