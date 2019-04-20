<!DOCTYPE html>
<html>
<head>
    <title>Seckill</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
</head>
<body>

<div class="container">
    <h2 style="text-align: center">宇宙秒杀</h2>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>商品名称</th>
            <th>商品描述</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>原价</th>
            <th>秒杀价</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list seckillGoodsList as seckillGoods>
            <tr>
                <td>${seckillGoods.goods.goodsName}</td>
                <td>${seckillGoods.goods.goodsDetail}</td>
                <td>${(seckillGoods.startDate)?string('yyyy-MM-dd HH:mm:ss')}</td>
                <td>${(seckillGoods.endDate)?string('yyyy-MM-dd HH:mm:ss')}</td>
                <td>${seckillGoods.goods.goodsPrice}￥</td>
                <td>${seckillGoods.seckillPrice}￥</td>
                <td><a href="/goods/${(seckillGoods.goods.id)?c}/detail">详情</a></td>
            </tr>
        </#list>
        </tbody>
    </table>


</div>

</body>
</html>