layui.use('upload', function () {
    var $ = layui.jquery;
    var upload = layui.upload;

    var uploadInst = upload.render({
        elem: '#logoUpload'
        , url: '/toLogoUpload/'
        , auto: false
        , accept: 'images'
        , acceptMime: 'image/*'
        , exts: 'jpg|png|gif|bmp|jpeg' //只允许上传图片文件
        , bindAction: '#toLogoUpload'  //绑定一个按钮触发上传
        , size: 5120, //限制文件大小5MB，单位 KB

        //选择文件后的回调函数。返回一个object参数
        choose: function (obj) {
            obj.preview(function (index, file, result) {
                $('#newLogo').attr('src', result);
                $('#logoText').text(file.name);
            });
        },

        //文件提交上传前的回调。返回一个object参数
        before: function () {
        },

        //执行上传请求后的回调。返回三个参数，
        // 分别为：res（服务端响应信息）、
        // index（当前文件的索引）、
        // upload（重新上传的方法，一般在文件上传失败后使用）
        done: function (res) {
            if (res.code === 1) {
                window.location.replace("/updateYourLogo");
                return layer.msg('修改成功');
            }
            else {
                return layer.msg('修改失败');
            }
            //alert(res.text);
            //修改头像的后台返回信息，
            // 可以用来说明失败或成功的原因

        },

        //执行上传请求出现异常的回调（一般为网络异常、URL 404等）。
        // 返回两个参数，分别为：index（当前文件的索引）、upload（重新上传的方法）
        error: function () {
            var logoText = $('#logoText');
            logoText.html('<span style="color: #FF5722;">上传失败</span>' +
                ' <a class="layui-btn layui-btn-lg demo-reload">重试</a>');
            logoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });
});