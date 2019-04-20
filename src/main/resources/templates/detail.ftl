<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Seckill</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
    <script src="/js/seckill.js"></script>
</head>
<body>
<div class="container">
    <div class="card" style="width:400px;margin:0 auto">
        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image"
             style="width:100%">
        <div class="card-body">
            <h4 class="card-title">${seckillGoods.goods.goodsName}</h4>
            <p class="card-text">${seckillGoods.goods.goodsDetail}</p>
            <p>原价:${seckillGoods.goods.goodsPrice}</p>
            <p>秒杀价:${seckillGoods.seckillPrice}</p>
            <span class="btn btn-primary" id="countdown"></span>
            <script>
                var countdown = $('#countdown');
                var killTime = '${seckillGoods.startDate?datetime}';
                countdown.countdown(killTime, function (event) {
                    var format = event.strftime('%D天 %H:%M:%S');
                    countdown.html(format);
                });
            </script>
        </div>
    </div>

</div>
</body>
</html>