<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Seckill</title>
    <link rel="stylesheet" href="/css/common.css">
</head>

<body style="background-color: #F9F9F9;font-family: -apple-system,BlinkMacSystemFont">
<div class="div_container_top">
    <div class="div_container_top_center">
        <div class="div_logo">
            <img src="/image/logo.png" style="width:53px;height:53px;margin-top:30px;">
        </div>
        <div class="div_word" style="margin-top:50px;">Sign in to Seckill
        </div>
    </div>
</div>
<#if error??>
    <div class="flash-container" id="error">
        <div class="flash-error">
            <div class="container">
                <span class="error-text">Incorrect username or password.</span>
                <button class="error-close-btn" onclick="errorClose()"><span class="close"></span></button>
                <script type="text/javascript">
                    function errorClose(){
                        document.getElementById("error").style.display="none";
                    }
                </script>
            </div>
        </div>
    </div>
</#if>
<div class="div_container_main">
    <div class="div_container_main_center">
        <form action="/dologin" method="post">
            <div class="div_main_first">
                <div class="div_first_word">
                    Username or email address
                </div>
            </div>
            <div class="div_main_second">
                <div class="search full-left">
                    <input id="username" name="username" type="text" value="${username?default("")}">
                </div>
            </div>
            <div class="div_main_third">
                <div class="div_chat_top_word1">
                    Password
                </div>
            </div>
            <div class="div_main_fouth">
                <div class="search full-left">
                    <input id="password" name="password" type="password">
                </div>
            </div>
            <div class="div_main_fifth">
                <input class="div_fifth_button" type="submit" value="Sign in">
            </div>
        </form>
    </div>

</div>
<div class="div_foot">
    <div class="div_foot_main">
        <div class="div_sixth">
            <div class="div_sixth_word1">
                New to Seckill?
            </div>
            <div class="div_sixth_word2">
                Create an account.
            </div>
        </div>
    </div>
</div>
</body>
</html>