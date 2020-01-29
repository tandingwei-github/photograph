$(document).ready(function () {
    check();
    reset();
    enableSubmit();
});
//检测输入不能有空值
//检测正则表达式和两次新密码是否相同
function check() {
    $("#oldPassword").mouseleave(function () {
        if ($("#oldPassword").val() === "") {
            $("#tips1").text("旧密码不能为空");
        }
        else {
            $("#tips1").text("");
        }
    });
    $("#newPassword").mouseleave(function () {
        if ($("#newPassword").val() === "") {
            $("#tips2").text("新密码不能为空");
        }
        else if (!isStudentNo($("#newPassword").val())) {
            $("#tips2").text("新密码不符合格式");
        }
        else if ($("#newPassword").val() !== $("#newPassword2").val()) {
            $("#tips2").text("两次新密码不相同");
        }
        else {
            $("#tips2").text("");
        }
    });
    $("#newPassword2").mouseleave(function () {
        if ($("#newPassword2").val() === "") {
            $("#tips3").text("重复密码不能为空");
        }
        else if ($("#newPassword").val() !== $("#newPassword2").val()) {
            $("#tips3").text("两次新密码不相同");
        }
        else {
            $("#tips2").text("");
            $("#tips3").text("");
        }
    });

}

//重置所有输入，清除提示
function reset() {
    $(".bt-reset").click(function () {
        $("#tips1,#tips2,#tips3").text("");
    })
}

//去掉提交按钮的disabled属性
function enableSubmit() {
    $(".layui-form-item").mouseleave(function () {
        if ($("#oldPassword").val() !== "" &&
            $("#newPassword").val() !== "" &&
            $("#newPassword2").val() !== "" &&
            $("#tips1").text() === ("") &&
            $("#tips2").text() === ("") &&
            $("#tips3").text() === ("")) {
            $(".bt-submit").removeClass("active").removeAttr("disabled");
        }
        else {
            $(".bt-submit").addClass("active").attr("disabled", "disabled");
        }
    })

}

//检查新密码的格式
function isStudentNo(str) {
    var reg = /^.{1,12}$/;
    /*长度在6-12之间*/
    return reg.test(str);
    /*进行验证*/
}

