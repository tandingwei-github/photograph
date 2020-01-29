var progressNum = 0;
layui.use('element', function () {
    var $ = layui.jquery, element = layui.element;

    //触发事件
    var active = {
        loading: function (othis) {
            var DISABLED = 'layui-btn-disabled';
            if (othis.hasClass(DISABLED)) return;

            //模拟loading
            var n = 0, timer = setInterval(function () {
                n = n + 25;
                if (n > 100) {
                    n = 100;
                    progressNum = 100;
                    clearInterval(timer);
                    othis.removeClass(DISABLED);
                    element.progress('demo', '100%');
                }
                element.progress('demo', n + '%');
                progressNum = n;
            }, 500);
            othis.addClass(DISABLED);
        }
    };

    $('#progressBtn').on('click', function () {
        var othis = $(this), type = $(this).data('type');
        active[type] ? active[type].call(this, othis) : '';
    });
});

$(document).ready(function () {
    firstTenMessage();

    var distanceScrollCount = 0; //滚动距离总长
    var distanceScroll = 0; //滚动到的当前位置
    var divHight = $(".layui-body").height();

    //统计当前是第几个10页
    var tenNum = 1;
    //数据库是否还有数据进行显示
    var loadPage = 1;
    //确保alert("所有信息都加载完了");只提示一次
    var alertCount = 1;

    //滚动至最低处，再加载10条
    $(".layui-body").scroll(function () {
        distanceScrollCount = $(this)[0].scrollHeight;
        distanceScroll = $(this)[0].scrollTop;

        if (distanceScroll + divHight + 40 >= distanceScrollCount) {
            if (loadPage > 0) {
                //进度条动画开启
                document.getElementById("progressBtn").click();
                $('body').everyTime('0.5s', function () {
                    if (progressNum === 100) {
                        moreTenMessage();
                        tenNum += 1;
                    }
                }, 4);
            }
            else {
                if (alertCount === 1) {
                    alertCount = 2;
                }
            }
        }
    });

    //执行“滚动至最低处，再加载10条”
    function moreTenMessage() {
        $.ajax({
            url: "moreTenMessage",
            type: "post",
            dataType: "json",
            data: {page: tenNum},
            success: function (data) {
                if (data[10].pageAll) {
                    loadPage = 0;
                    $("#noMoreLoad").attr("style", "display:block");
                    for (var i = 0; i < 10; i++) {
                        var html =
                            "<div class='layui-col-md12'>" +
                            "    <div class='layui-card'>" +
                            "        <div class='layui-card-header' >" +
                            "标题:" + data[i].messageTitle +
                            "        </div>" +
                            "        <div class='layui-card-body'  >" +
                            "            正文：<br/>" + data[i].messageContent +
                            "<br/><br/>  时间：" + getTsFormatDate(data[i].messageTime) +
                            "<br/>       作者：" + data[i].messageWriter +
                            "        </div>" +
                            "    </div>" +
                            "</div>";
                        $(".layui-row").append(html);
                    }
                }
                else {
                    for (var i = 0; i < 10; i++) {
                        var html =
                            "<div class='layui-col-md12'>" +
                            "    <div class='layui-card'>" +
                            "        <div class='layui-card-header'>标题:" + data[i].messageTitle +
                            "        </div>" +
                            "        <div class='layui-card-body'>  " +
                            "            正文：<br/>" + data[i].messageContent +
                            "<br/><br/>  时间：" + getTsFormatDate(data[i].messageTime) +
                            "<br/>       作者：" + data[i].messageWriter +
                            "        </div>" +
                            "    </div>" +
                            "</div>";
                        $(".layui-row").append(html);
                    }
                }
            }
        });
    }
});

//页面加载完成后，显示前10条信息
function firstTenMessage() {
    $.ajax({
        url: "firstTenMessage",
        type: "post",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < 10; i++) {
                var html =
                    "<div class='layui-col-md12'>" +
                    "    <div class='layui-card'>" +
                    "        <div class='layui-card-header'>标题:" + data[i].messageTitle +
                    "        </div>" +
                    "        <div class='layui-card-body'>  " +
                    "            正文：<br/>" + data[i].messageContent +
                    "<br/><br/>  时间：" + getTsFormatDate(data[i].messageTime) +
                    "<br/>       作者：" + data[i].messageWriter +
                    "        </div>" +
                    "    </div>" +
                    "</div>";
                $(".layui-row").append(html);
            }
        }
    });
}

//将时间戳转化为时间输出
function getTsFormatDate(timeStamp) {
    var date = new Date(timeStamp);

    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();

    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minute >= 0 && minute <= 9) {
        minute = "0" + minute;
    }
    if (second >= 0 && second <= 9) {
        second = "0" + second;
    }
    var dateString = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    return dateString;
}
