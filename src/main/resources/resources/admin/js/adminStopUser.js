//初始化多选框被选中的个数为0
var checkNum = 0;

//单击多选框，统计选中多选框的个数
function changeCheckbox(obj) {
    if (obj.checked === true) {
        checkNum += 1;
    }
    else {
        checkNum -= 1;
    }
}

//全选和取消全选
function selectAll() {
    //1.获取checkbox的元素对象
    var chElt = document.getElementById("checkAll");
    //2.获取选中状态
    var checkedElt = chElt.checked;
    //3.若checked=true,将所有的复选框选中,checked=false,将所有的复选框取消
    var allCheck = document.getElementsByClassName("select");
    //4.循环遍历取出每一个复选框中的元素
    if (checkedElt) {
        //全选
        for (var i = 0; i < allCheck.length; i++) {
            allCheck[i].checked = true;
            checkNum += 1;
        }
    }
    else {
        //取消全选
        for (var i = 0; i < allCheck.length; i++) {
            allCheck[i].checked = false;
            checkNum = 0;
        }
    }
}

//提示那些账号被封禁，并执行封禁
function toStopUser() {
    var allCheck = document.getElementsByClassName("select");
    if (checkNum === 0) {
        alert("你未选中任何账号！");
    }
    else {
        for (var i = 0; i < allCheck.length; i++) {
            if (allCheck[i].checked === true) {
                var td = allCheck[i].parentNode;
                var tr = td.parentNode;
                var tdCon = tr.children[4];
                var tdCon1 = tdCon.innerHTML;
                var username = tr.children[1];
                var username1 = username.innerHTML;

                if (tdCon1 === "正常") {
                    tr.classList.add("danger");
                    tdCon.innerHTML = "封禁";
                    stopUser(username1);
                }
                else {
                    alert("账号" + username1 + "未被封禁，操作不允许");
                }
            }
        }
    }
}

//执行封禁
function stopUser(username1) {
    $.ajax({
        url: "toStopUser",
        type: "post",
        dataType: "json",
        data: {username: username1},
        success: function (data) {
            alert(data[0]);
        }
    });
    var allCheck = document.getElementsByClassName("select");
    //取消全选
    for (var i = 0; i < allCheck.length; i++) {
        allCheck[i].checked = false;
        checkNum = 0;
    }
}

//解禁账号
function toBeginUsername() {
    var allCheck = document.getElementsByClassName("select");
    if (checkNum === 0) {
        alert("你未选中任何账号！");
    }
    else {
        for (var i = 0; i < allCheck.length; i++) {
            var td = allCheck[i].parentNode;
            var tr = td.parentNode;
            var tdCon = tr.children[4];
            var tdCon1 = tdCon.innerHTML;
            var username = tr.children[1];
            var username1 = username.innerHTML;

            if (allCheck[i].checked === true) {
                if (tdCon1 === "正常") {
                    alert(username1 + "是未封禁的账号，操作不允许");
                }
                else {
                    tr.classList.remove("danger");
                    tdCon.innerHTML = "正常";
                    beginUser(username1);
                }
            }
        }
    }
}

//执行解禁
function beginUser(username1) {
    $.ajax({
        url: "toBeginUser",
        type: "post",
        dataType: "json",
        data: {username: username1},
        success: function (data) {
            alert(data[0]);
        }
    });
    var allCheck = document.getElementsByClassName("select");
    //取消全选
    for (var i = 0; i < allCheck.length; i++) {
        allCheck[i].checked = false;
        checkNum = 0;
    }
}

//为弹出的模态框，添加账号信息
function alertUsername() {
    $("#myModalLabel").text("你确定要封禁这些账号吗??");
    var allCheck = document.getElementsByClassName("select");
    var usernameToStop = document.getElementById("usernameToStop");
    usernameToStop.innerHTML = "";
    for (var i = 0; i < allCheck.length; i++) {
        var td = allCheck[i].parentNode;
        var tr = td.parentNode;
        var username = tr.children[1];
        var username1 = username.innerHTML;
        if (allCheck[i].checked === true) {
            usernameToStop.innerHTML += username1 + "<br/>";
        }
    }
}

//搜索用户信息，并显示在页面上
function searchUser() {
    var username = $("#username").val();
    $("tbody").html("");
    if (username === "") {
        alert("请输入一个账号");
    }
    else {
        var chElt = document.getElementById("checkAll");
        chElt.checked = false;
        checkNum = 0;
        toSearchUser(username);
    }
}

//搜索用户
function toSearchUser(username) {
    $.ajax({
        url: "toSearchUser",
        type: "post",
        dataType: "json",
        data: {username: username},
        success: function (data) {
            if (data.length <= 1) {
                alert("找不到相关账号的信息!!!")
            }
            else {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].userIsStopped === 'no') {
                        var html =
                            "<tr >" +
                            "<td >" +
                            "<input type='checkbox' class='select'" +
                            "       onclick='changeCheckbox(this)'>" +
                            "</td>" +
                            "<td >" + data[i].username + "</td>";

                        if (data[i].userNick === undefined) {
                            html += "<td >不留名用户</td>"
                        }
                        else {
                            html += "<td >" + data[i].userNick + "</td>";
                        }
                        if (data[i].userLastLogin === undefined) {
                            html += "<td >该账户从未登录过</td>"
                        }
                        else {
                            html += "<td >" + getTsFormatDate(data[i].userLastLogin) + "</td>";
                        }
                        html += "<td class='condition'>正常</td></tr>";
                    }
                    else {
                        var html =
                            "<tr class='danger'>" +
                            "<td >" +
                            "<input type='checkbox' class='select'" +
                            "       onclick='changeCheckbox(this)'>" +
                            "</td>" +
                            "<td >" + data[i].username + "</td>";

                        if (data[i].userNick === undefined) {
                            html += "<td >不留名用户</td>"
                        }
                        else {
                            html += "<td >" + data[i].userNick + "</td>";
                        }
                        if (data[i].userLastLogin === undefined) {
                            html += "<td >该账户从未登录过</td>"
                        }
                        else {
                            html += "<td >" + getTsFormatDate(data[i].userLastLogin) + "</td>";
                        }
                        html += "<td class='condition'>封禁</td></tr>";
                    }
                    $("tbody").append(html);
                }
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