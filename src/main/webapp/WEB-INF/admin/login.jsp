<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>WNCT管理系统</title>
    <link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/static/admin/css/login.css">
    <script type="text/javascript" src="${ctx}/static/admin/js/jquery.min.js"></script>
    <script src="${ctx}/static/admin/lib/layui/layui.js" charset="ut
  f-8"></script>
</head>
<body class="login-bg">
<canvas id="fullstarbg">你的浏览器不支持canvas标签</canvas>
<div class="login">
    <div class="message">WNCT后台登陆</div>
    <div id="darkbannerwrap"></div>
    <form class="layui-form" id="form" >
        <input name="account" placeholder="用户名" id="account"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" id="password" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录"  style="width:100%;" type="button" id="login">
        <hr class="hr20" >
    </form>
</div>
<script>
    layui.use(['form'],
        function() {
            var layer=layui.layer;
            $("#login").click(function(){
                var account=$("#account").val();
                var password=$("#password").val();
                var flag=true;
                if(account==''){
                    flag=false;
                    layer.tips("请输入账号！","#account",{
                        tips:[1,"#1E90FF"],
                        time:2000
                    })
                }else if(password==''){
                    flag=false;
                    layer.tips("请输入密码！","#password",{
                        tips:[1,"#1E90FF"],
                        time:2000
                    })
                }

                if(flag){
                    $.ajax({
                        url:"${ctx}/admin/Login",
                        data:{account:account,password:password},
                        dataType:"json",
                        type:"POST",
                        success:function(data){
                            if(data.result=='ok'){
                                location.href="${ctx}/";
                            }else{
                                layer.alert(data.result,{icon:"2"})
                            }
                        }
                    })
                }
            })

        });

</script>

<!-- 底部结束 -->
</body>
</html>
