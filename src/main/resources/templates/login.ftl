<html>
<head>
    <title>Login</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<div id="container">
    <h1><span style="color: #FF0066">&bull;</span> Авторизация <span style="color: #FF0066">&bull;</span></h1>

    <div class="underline">
    </div>
    <center>
        <form action="/login_check" method="post">
            <div class="Login">
                <label for="Login"></label>
                <input type="text" placeholder="Логин" style="text-align: center" name="login" id="login" required>
            </div>
            <div class="password">
                <label for="password"></label>
                <input type="password" placeholder="Пароль" style="text-align: center" name="password" id="password" required>
            </div>
            <#if error??>
                <h3></span> Неправильный логин или пароль </h3>
            </#if>
            <input type="submit" value="Авторизоваться" style="text-align: center; margin-top: 5%" id="form_button" />
        </form><!-- // End form -->
    </center>
</div><!-- // End #container -->


</body>
</html>