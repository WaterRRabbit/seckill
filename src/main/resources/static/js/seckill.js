var seckill = {
    URL: {
        expose: function (id) {
            return '/seckill/' + id + '/expose';
        },
        now: function () {
            return '/seckill/now';
        },
        execution: function (id) {
            return '/seckill/' + id + '/execution';
        },
        result: function (id) {
            return '/seckill/' + id + '/result';
        }
    },
    handleSeckill: function (goodsId, node) {
        node.removeClass('pr');
        node.hide().html('<button class="btn btn-primary" id="kill-btn">秒杀</button>');
        $.get(seckill.URL.expose(goodsId), {}, function (res) {
            if (res && res['code'] === 0) {
                var expose = res['data'];
                if (expose['start']) {
                    $('#kill-btn').one('click', function () {
                        $(this).addClass('disabled');
                        $.get(seckill.URL.execution(goodsId), {}, function (res) {
                            if (res) {
                                if (res['code'] === 5002 || res['code'] === 5003) {
                                    node.addClass('pr');
                                    node.html(res['message']);
                                } else if (res['code'] === 2001) {
                                    node.addClass('pr');
                                    node.html(res['message']);
                                    var req = {
                                        url: seckill.URL.result(goodsId),
                                        data: {},
                                        success: function (res) {
                                            if (res) {
                                                if (res['code'] === 2001) {
                                                    node.addClass('pr');
                                                    node.html(res['message']);
                                                    seckill.getResult(req);
                                                } else if (res['code'] === 5002 || res['code'] === 5003) {
                                                    node.addClass('pr');
                                                    node.html(res['message']);
                                                } else if (res['code'] === 0){
                                                    //秒杀成功
                                                    node.addClass('pr');
                                                    node.html(res['message']);
                                                }
                                            }
                                        }
                                    };
                                    seckill.getResult(req);
                                }
                            }
                        });
                    });
                } else {
                    seckill.countdown(goodsId, expose['startTime'],
                        expose['endTime'], expose['nowTime']);

                }
            } else {

            }
        });
        node.show();
    },
    countdown: function (goodsId, start, end, now) {
        var box = $('#countdown');
        if (now > end) {
            box.addClass('pr');
            box.html('秒杀结束');
        } else if (now < start) {
            box.addClass('pr');
            box.countdown(start, function (event) {
                var format = event.strftime('%D天 %H:%M:%S');
                box.html(format);
            }).on('finish.countdown', function () {
                seckill.handleSeckill(goodsId, box);
            });
        } else {
            seckill.handleSeckill(goodsId, box);
        }
    },
    init: function (params) {
        var start = params['startTime'];
        var end = params['endTime'];
        var goodsId = params['goodsId'];
        $.get(seckill.URL.now(), {}, function (res) {
            if (res && res['code'] === 0) {
                var now = new Date(res['data']);
                // 时间判断，计时交互
                seckill.countdown(goodsId, start, end, now);
            } else {

            }
        });
    },
    getResult: function (params) {
        $.get(params['url'], params['data'], params['success']);
    }
};