$(document).ready(function () {
    regPhoneCode();
    regPhone();
});

var time = 30;

function toGetPhoneCode() {
    $("#PhoneCodeBtn").addClass("active").attr("disabled", "disabled");
    getPhoneCode();
    if (time === 30) {
        var time1 = setInterval(function () {
            if (time === 0) {
                $("#PhoneCodeBtn").removeClass("active").removeAttr("disabled").val("获取验证码");
                clearInterval(time1);
            }
            else {
                $("#PhoneCodeBtn").addClass("active").attr("disabled", "disabled").val(time + "秒继续获取");
                time--;
            }
        }, 1000);
    }
}

//获取验证码
function getPhoneCode() {
    var phone = $("#phone").val();
    $.ajax({
        type: "POST",
        url: "toGetPhoneCode",
        data: {phone: phone},
        success: function (data) {
            if (data[1].phoneUsed === 1) {
                window.location.replace("/updateYourPhone");
                alert("该号码已被绑定使用，请换个号码试试");
            }
        }
    });
}

//11位手机号的正则表达式
function regPhone() {
    var reg = /^[1][3-9][0-9]{9}$/;
    $("#phone").mouseleave(function () {
        if (reg.test($("#phone").val())) {
            $("#PhoneCodeBtn").removeClass("active").removeAttr("disabled");
        }
        else {
            $("#PhoneCodeBtn").addClass("active").attr("disabled", "disabled");
        }
    })
}

//6位验证码的正则表达式
function regPhoneCode() {
    var reg = /^\d{6}$/;
    $("#phoneCode").mouseleave(function () {
        if (reg.test($("#phoneCode").val())) {
            $(".bt-submit").removeClass("active").removeAttr("disabled");
        }
        else {
            $(".bt-submit").addClass("active").attr("disabled", "disabled");
        }
    })
}