layui.use('element', function () {
    var element = layui.element;
});

$(document).ready(function () {
    var url = window.location.pathname;
    leftItem(url);
    toTop();//返回顶部
});

function leftItem(url) {
    if (url === "/yourInfo") {
        $(".list1").addClass("layui-this");
    }
    else if (url === "/you") {
        $(".list1").addClass("layui-this");
    }
    else if (url === "/yourCommented") {
        $(".list2").addClass("layui-this");
    }
    else if (url === "/yourShared") {
        $(".list3").addClass("layui-this");
    }
    else if (url === "/yourDownloaded") {
        $(".list4").addClass("layui-this");
    }
    else if (url === "/updateYourNickname" || url === "/toUpdateNickname") {
        $(".item-left-fold").addClass("layui-nav-itemed");
        $(".list5").addClass("layui-this");
    }
    else if (url === "/updateYourPassword" || url === "/toUpdatePassword") {
        $(".item-left-fold").addClass("layui-nav-itemed");
        $(".list6").addClass("layui-this");
    }
    else if (url === "/updateYourLogo" || url === "/toUpdateLogo") {
        $(".item-left-fold").addClass("layui-nav-itemed");
        $(".list7").addClass("layui-this");
    }
    else if (url === "/yourMessage") {
        $(".list8").addClass("layui-this");
    }
    else if (url === "/updateYourPhone") {
        $(".list9").addClass("layui-this");
    }
}

function toTop() {
    $(".layui-body").scroll(function () {
        //创建一个变量存储当前窗口下移的高度
        var Top = $(".layui-body").scrollTop();
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
        $(".layui-body").animate({scrollTop: 0}, 500);
    })
}