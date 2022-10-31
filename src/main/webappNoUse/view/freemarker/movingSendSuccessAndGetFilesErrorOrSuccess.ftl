<html>
<head>
    <title>DataBase</title>
    <link href="/styles/styles.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<#if success??>
<div id="container">

    <center>
        <h1><span style="color: #FF0066">&bull;</span> Успешно <span style="color: #FF0066">&bull;</span></h1>
        <div class="underline">
        </div>
        <form action="/window" method="post">
            <input type="submit" value="Назад" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button1"  />
        </form>
        <h2><span style="color: #FF0066">&bull;</span> Отчеты <span style="color: #FF0066">&bull;</span></h2>

        <form action="/window/moving/send/report_product" method="post">
            <input type="text" placeholder="Необязательно: Введите имя товара" style="text-align: center" name="productName" id="productName" >
            <br>
            <input type="submit" value="Общий список товаров" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button2"  />
        </form>
        <form action="/window/moving/send/report_sklad" method="post">
            <input type="text" placeholder="Необязательно: Введите имя склада" style="text-align: center" name="stockName" id="warehouseName" >
            <br>
            <input type="submit" value="Остатки товаров на складах" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button3"  />
        </form>
        <#else>
            <div style="text-align: center;">
                <div id="container">
                    <h1><span style="color: #FF0066">&bull;</span> ОШИБКА! <span style="color: #FF0066">&bull;</span></h1>
                    <div class="underline"></div>
                    <form action="/window/moving/" method="post" enctype="multipart/form-data">
                        <input type="submit" value="Назад" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button1"  />
                    </form>
                </div>
            </div>
        </#if>
    </center>
</div>
</body>
</html>