<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
    <script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/admin/js/x-admin.js"></script>
</head>

<body>
<form class="layui-form layui-form-pane" style="margin: 20px">
    <input type="hidden" id="account" value="${loginAdmin.account}">
    <div class="layui-form-item">
        <label for="pwd" class="layui-form-label">
            原密码
        </label>
        <div class="layui-input-block">
            <input type="password" id="pwd" name="title" required lay-verify="title"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="newPwd1" class="layui-form-label">
            新密码
        </label>
        <div class="layui-input-block">
            <input type="password" id="newPwd1" name="title" required lay-verify="title"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="newPwd2" class="layui-form-label">
            确认密码
        </label>
        <div class="layui-input-block">
            <input type="password" id="newPwd2" name="title" required lay-verify="title"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <input class="layui-btn" type="button" value="立即发布" onclick="update()">
    </div>
</form>
<script>
    var layer;
    var $;
    layui.use('layer',function(){
        layer=layui.layer;
        $=layui.jquery;
    })
    //添加活动倒计时
    function update(){
        var pwd=$("#pwd").val();
        var newPwd1=$("#newPwd1").val();
        var newPwd2=$("#newPwd2").val();
        var account=$("#account").val();
        var flag=true;
        //alert(name+":"+endDate);
        if(pwd==''){
            flag=false;
            layer.tips("请输入原密码","#pwd",{tips:[1,"#2299ee"],time:2000})
        }else if(newPwd1==''){
            flag=false;
            layer.tips("请输新密码","#newPwd1",{tips:[1,"#2299ee"],time:2000})
        }else if(newPwd2==''){
            flag=false;
            layer.tips("请确认密码","#newPwd2",{tips:[1,"#2299ee"],time:2000})
        }else if(newPwd1!=newPwd2){
            flag=false;
            layer.tips("两次密码不一致","#newPwd2",{tips:[1,"#2299ee"],time:2000})
        }

        if(flag){
            $.ajax({
                url:"${ctx}/admin/updatePwd",
                data:{pwd:pwd,newPwd1:newPwd1,newPwd2:newPwd2,account:account},
                dataType:"json",
                type:"GET",
                success:function(res){
                    if(res.msg=='ok'){
                        layer.alert("修改密码成功!下次登陆生效。", {icon: 6},function () {

                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                        });
                    }else{
                        layer.msg(res.msg,{icon:"2"})
                    }
                }
            })
        }
    }
</script>
</body>

</html>