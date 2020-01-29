$(document).ready(function () {
    var url = window.location.pathname;
    logo(url);//根据URL切换图标logo
    toTop();//返回顶部
});

function logo(url) {
    if (url === "/adminStopUser") {
        $("#list1").removeClass("list").addClass("active");
        $("#img1").attr("src", "/static/tatan.gif");
    }
    else if (url === "/adminCheckPhoto") {
        $("#list2").removeClass("list").addClass("active");
        $("#img2").attr("src", "/static/tatan.gif");
    }
    else if (url === "/adminStopPhoto") {
        $("#list3").removeClass("list").addClass("active");
        $("#img3").attr("src", "/static/tatan.gif");
    }
    else if (url === "/adminDeleteComment") {
        $("#list4").removeClass("list").addClass("active");
        $("#img4").attr("src", "/static/tatan.gif");
    }
    else if (url === "/adminTagCopyright") {
        $("#list5").removeClass("list").addClass("active");
        $("#img5").attr("src", "/static/tatan.gif");
    }
    else if (url === "/adminPushMessage") {
        $("#list6").removeClass("list").addClass("active");
        $("#img6").attr("src", "/static/tatan.gif");
    }
}

function toTop() {
    $(".right").scroll(function () {
        //创建一个变量存储当前窗口下移的高度
        var Top = $(".right").scrollTop();
        //判断当前窗口滚动高度
        //如果大于100，则显示顶部元素，否则隐藏顶部元素
        if (Top > 100) {
            $('.toTop').fadeIn(1000);
        }
        else {
            $('.toTop').fadeOut(0);
        }
    });
    $(".toTop").click(function () {
        $(".right").animate({scrollTop: 0}, 500);
    })
}
