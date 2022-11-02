
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DataBase</title>
    <link href="${pageContext.request.contextPath}/view/css/styles.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<body>
<div id="container">

    <center>
        <h1><span style="color: #FF0066">&bull;</span> ОШИБКА! <span style="color: #FF0066">&bull;</span></h1>
        <div class="underline">
        </div>
        <form action="${pageContext.request.contextPath}/window/sale/send/report" method="post">
            <input type="submit" value="Назад" style="text-align: center; margin: -4%; padding: 30px 73px;"  id="form_button1"  />
        </form>
    </center>
</div>
</body>

</html>