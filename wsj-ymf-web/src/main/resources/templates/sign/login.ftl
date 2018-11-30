<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>挖水晶登陆页</title>
    <meta name="keywords" content="wsj管理系统">
    <meta name="description" content="wsj管理系统">
    <link href="${req.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${req.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${req.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${req.contextPath}/css/style.css" rel="stylesheet">
    <link href="${req.contextPath}/css/login.css" rel="stylesheet">
    <link href="${req.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-5" style="margin: auto;float: none!important;">

            <form class="login-form" action="${req.contextPath}/login" method="post">

                <p class="m-t-md">欢迎登录wsj后台管理系统</p>
                <div class="form-group">
                    <input type="text" id="loginName" name="loginName" class="form-control uname" placeholder="用户名" value="admin"/>
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" class="form-control pword m-b" placeholder="密码" value="111111"/>
                </div>

                <div class="form-actions">
                    <div style="margin-left: 20px;"><label class="checkbox"><input type="checkbox" name="rememberMe" id="rememberMe" value="true"/> 下次自动登录 </label></div>
                </div>
                <div class="form-actions">
                    <button  type="submit" class="btn btn-success btn-block">登录 <i  class="m-icon-swapright m-icon-white"></i></button>
                </div>

            <#--<button class="btn btn-success btn-block">登录</button>-->
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div style="text-align: center;"> Copyright © 2004 Adobe Systems Incorporated. All rights reserved. 北京市朝阳区瀚海蓝鲸科技服务有限公司
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${req.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${req.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script src="${req.contextPath}/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${req.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${req.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
<script src="${req.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${req.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${req.contextPath}/js/slife/toast.js"></script>

<script>
    // function login() {
    //     $.ajax({
    //         url:'/login',
    //         type:'post',
    //         data:'loginName='+ $("#loginName").val()+'&password='+ $("#password").val(),
    //         error:function(){
    //             toast_error("",'error');
    //         },
    //         success:function(data){
    //             var data = $.parseJSON(data);
    //             if(data.msg=="success"){
    //
    //             }else{
    //                 toast_error("",data.returnStr);
    //                 console.log(111111111111111111111)；
    //                 setTimeout("alert('对不起, 要你久候')", 10000 )
    //             }
    //         }
    //     });
        $(document).ready(function () {
        <#if info??>
            <#if info.msg=="success">
               window.location.href ='${info.url}';
            <#else>
               toast_error("",'${info.returnStr}');
            </#if>
        </#if>

        });

        $('.login-form').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },
            messages: {
                username: {
                    required: "用户名不能为空."
                },
                password: {
                    required: "密码不能为空."
                }
            },
            invalidHandler: function (event, validator) {

                toast_error("","用户名或密码不能为空");

                /* $('.form-group', $('.login-form')).show();*/
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },
            submitHandler: function (form) {
                form.submit();
            }
        });

        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit();
                }
                return false;
            }
        });
</script>

</body>
</html>
