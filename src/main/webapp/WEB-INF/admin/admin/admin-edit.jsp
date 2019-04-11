<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp" %>
<html>

<head>
    <meta charset="utf-8">
    <title>

    </title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <input type="hidden" name="id" value="${admin.id}">
            <%--<label for="L_title" class="layui-form-label">级别</label>
            <div class="layui-input-block">
                <input type="text" id="L_title" name="level" value="${node.level}" required lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>--%>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="username" value="${admin.username}" type="text" readonly="readonly">
                </div>
            </div>
        </div>

        <%--<div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="password" type="text">
                </div>
            </div>
        </div>--%>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="nickname" value="${admin.nickname}" type="text">
                </div>
            </div>
        </div>
        <div class="layui-form-item">

            <form class="layui-form" action="" lay-filter="example">
            <div class="layui-form-item">
                <label class="layui-form-label">权限等级</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="lbgl"  title="轮播管理">
                    <input type="checkbox" name="gggl"  title="公告管理">
                    <input type="checkbox" name="lsgl"  title="流水管理">
                    <input type="checkbox" name="yhgl" title="用户管理">
                    <input type="checkbox" name="lstj" title="流水统计">
                    <input type="checkbox" name="jyjl" title="交易记录">
                    <input type="checkbox" name="tbsh" title="提币审核">
                    <input type="checkbox" name="xtsz" title="系统设置">
                    <input type="checkbox" name="gdtj" title="挂单统计">
                    <input type="checkbox" name="xtzz" title="系统转账">
                    <input type="checkbox" name="djsz" title="等级设置">
                    <input type="checkbox" name="glysz" title="管理员设置">

                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="add">保存修改</button>
                </div>
            </div>
            </form>
        </div>
    </form>
    <%--<button class="layui-btn" onclick="updatePwd111()">修改密码</button>--%>
    <%--<button class="layui-btn" onclick="updateAccount()">修改手机号</button>--%>
</div>
<script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8">
</script>
<script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8">
</script>
<script>
    layui.use(['form', 'layer', 'layedit', 'laydate', 'element', 'laypage'], function () {
        $ = layui.jquery;
        var form = layui.form()
            , layer = layui.layer
            , layedit = layui.layedit;


        layedit.set({
            uploadImage: {
                url: "${ctx}/admin/notice/upload" //接口url
                , type: 'post' //默认post
            }
        })

        //创建一个编辑器
        editIndex = layedit.build('L_content');


        //监听提交
        form.on('submit(add)', function (data) {
            console.log(data);
            var role = [];


        //var role = $('#aa').val();
            var username = data.field.username;
            var password = data.field.password;
            var nickname = data.field.nickname;
            var id = data.field.id;
            if (data.field.lbgl=='on'){
                role.push(1);
            }
            if (data.field.gggl=='on'){
                role.push(2);
            }
            if (data.field.lsgl=='on'){
                role.push(3);
            }
            if (data.field.yhgl=='on'){
                role.push(4);
            }
            if (data.field.lstj=='on'){
                role.push(5);
            }
            if (data.field.jyjl=='on'){
                role.push(6);
            }
            if (data.field.tbsh=='on'){
                role.push(7);
            }
            if (data.field.xtsz=='on'){
                role.push(8);
            }
            if (data.field.gdtj=='on'){
                role.push(9);
            }
            if (data.field.xtzz=='on'){
                role.push(10);
            }
            if (data.field.djsz=='on'){
                role.push(11);
            }
            if (data.field.glysz=='on'){
                role.push(12);
            }
            //alert(role);
            /*var id_array = [];
            $('input[type="checkbox"]:checked').each(function(){
                id_array.push($(this).val());
            });
            console.log(id_array)
            alert(id_array);
            var aaa = id_array.join(',');
            alert(aaa);*/
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/updateAdminRole", {
                /*username: username,*/
               /* password: password,*/
                id:id,
                nickname: nickname,
                role: role.toString()
            }, function (res) {
                if (res.msg == 'ok') {
                    layer.alert("修改成功", {icon: 6}, function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                } else {
                    layer.alert(res.msg, {icon: "5"});
                }
            })
            return false;
        });


    });



    function updatePwd111() {
        var addr = '${addr}';
        layer.prompt({title: '请填写密码，并确认', formType: 2}, function (text, index) {

            /*layer.msg('演示完毕！写下了：'+text);*/
            $.get("${ctx}/admin/updateAdminRole", {addr: addr, pwd: text}, function (res) {
                if (res.msg == 'ok') {
                    layer.msg("修改成功", {icon: "1"});
                    setTimeout(function () {
                        location.reload();
                    }, 2000)
                } else {
                    layer.msg(res.msg, {icon: "2"});
                }
            })
            layer.close(index);
        })
    }

    //修改账户名
    /*function updateAccount(){
        var addr = '${addr}';
        layer.prompt({title: '请填写新账户名，并确认', formType: 2}, function(text, index){

            layer.msg('账户修改为：'+text,{time:2000});
            $.get("${ctx}/admin/user/updateAccount",{addr:addr,account:text},function(res){
                if(res.msg=='ok'){
                    layer.msg("修改成功",{icon:"1"});
                    setTimeout(function(){
                        location.reload();
                    },2000)
                }else{
                    layer.msg(res.msg,{icon:"2"});
                }
            })
            layer.close(index);
        })
    }*/

</script>
</body>

</html>
