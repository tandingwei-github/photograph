$(document).ready(function () {
    //统计当前是第几个10页
    var tenNum = 1;
    //数据库是否还有数据进行显示
    var loadPage = 1;

    enableSubmit();
    reset();
    firstTenMessage();

    //加载下一个10条信息
    $("#moreMessage").mousedown(function () {
        if (loadPage > 0) {
            moreTenMessage();
            tenNum += 1;
        }
    });

    function moreTenMessage() {
        $.ajax({
            url: "moreTenMessage",
            type: "post",
            dataType: "json",
            data: {page: tenNum},
            success: function (data) {
                if (data[10].pageAll) {
                    loadPage = 0;
                    $("#moreMessage").attr("style", "display:none");
                    $("#allMessageShowed").text("所有已发布的信息都加载完了");
                    for (var i = 0; i < 10; i++) {
                        var html = "<tr>" +
                            "<td >" +
                            "<span class='input-group-addon'>" +
                            "<span class='glyphicon glyphicon-bookmark'>" + (i + 1 + (tenNum - 1) * 10) +
                            "</span></span></td>" +
                            "<td >" + getTsFormatDate(data[i].messageTime) + "</td>" +
                            "<td >" + data[i].messageWriter + "</td>" +
                            "<td >" + data[i].messageTitle + "</td>" +
                            "<td class='break'>" + data[i].messageContent + "</td>" +
                            "</tr>";
                        $(".MessageWrited").find("tbody").append(html);
                    }
                }
                else {
                    for (var i = 0; i < 10; i++) {
                        var html = "<tr>" +
                            "<td >" +
                            "<span class='input-group-addon'>" +
                            "<span class='glyphicon glyphicon-bookmark'>" + (i + 1 + (tenNum - 1) * 10) +
                            "</span></span></td>" +
                            "<td >" + getTsFormatDate(data[i].messageTime) + "</td>" +
                            "<td >" + data[i].messageWriter + "</td>" +
                            "<td >" + data[i].messageTitle + "</td>" +
                            "<td class='break'>" + data[i].messageContent + "</td>" +
                            "</tr>";
                        $(".MessageWrited").find("tbody").append(html);
                    }
                }
            }
        });
    }

});

//检测输入不能有空值，不能超长
function enableSubmit() {
    $(".input-group ").mouseleave(function () {
        if (0 < $("#message_content").val().length &&
            $("#message_content").val().length <= 500 &&
            0 < $("#message_title").val().length &&
            $("#message_title").val().length <= 20) {
            $(".btn-success").removeClass("useless").removeAttr("disabled").text("发布信息");
        }
        else if ($("#message_title").val().length > 20) {
            if ($("#message_content").val().length > 500) {
                $(".btn-success").addClass("useless").attr("disabled", "disabled").text("标题内容都过长，不能发布");
            }
            else {
                $(".btn-success").addClass("useless").attr("disabled", "disabled").text("标题过长，不能发布");
            }
        }
        else if ($("#message_content").val().length > 500) {
            $(".btn-success").addClass("useless").attr("disabled", "disabled").text("内容过长，不能发布");
        }
        else {
            $(".btn-success").addClass("useless").attr("disabled", "disabled").text("表单有空值，不能发布");
        }
    });
}

//页面加载完成后，显示前10条信息
function firstTenMessage() {
    $.ajax({
        url: "firstTenMessage",
        type: "post",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < 10; i++) {
                var html = "<tr>" +
                    "<td >" +
                    "<span class='input-group-addon'>" +
                    "<span class='glyphicon glyphicon-bookmark'>" + (i + 1) +
                    "</span></span></td>" +
                    "<td >" + getTsFormatDate(data[i].messageTime) + "</td>" +
                    "<td >" + data[i].messageWriter + "</td>" +
                    "<td >" + data[i].messageTitle + "</td>" +
                    "<td class='break'>" + data[i].messageContent + "</td>" +
                    "</tr>";
                $(".MessageWrited").find("tbody").append(html);
            }
        }
    });
}

//重置内容，使按钮disabled
function reset() {
    $(".bt-reset").click(function () {
        $(".btn-success").addClass("useless").attr("disabled", "disabled");
    });
    $(".bt-reset-title").click(function () {
        $(".btn-success").addClass("useless").attr("disabled", "disabled");
        $("#message_title").val("");
    });
    $(".bt-reset-content").click(function () {
        $(".btn-success").addClass("useless").attr("disabled", "disabled");
        $("#message_content").val("");
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