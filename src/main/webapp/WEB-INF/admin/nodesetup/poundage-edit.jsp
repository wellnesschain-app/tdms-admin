<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
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
            <input type="hidden" name="id" value="${procedures.id}">
            <%--<label for="L_title" class="layui-form-label">级别</label>
            <div class="layui-input-block">
                <input type="text" id="L_title" name="level" value="${node.level}" required lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>--%>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 115px;">TP转账手续费</label>
                <div class="layui-input-block" style="margin-left: 84px;">
                    <input class="layui-input" name="tp_transfer"  value="${procedures.tp_transfer}" type="text" style="width: 65%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 115px;">WNCT转账手续费</label>
                <div class="layui-input-block" style="margin-left: 84px;">
                    <input class="layui-input" name="wnct_transfer" value="${procedures.wnct_transfer}" type="text" style="width: 65%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 115px;">WNCT提币手续费</label>
                <div class="layui-input-block" style="margin-left: 84px;">
                    <input class="layui-input" name="wnct_withdraw" value="${procedures.wnct_withdraw}" type="text" style="width: 65%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 115px;">USDT转账手续费</label>
                <div class="layui-input-block" style="margin-left: 84px;">
                    <input class="layui-input" name="usdt_transfer" value="${procedures.usdt_transfer}" type="text" style="width: 65%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 136px;">USDT提币手续费(个)</label>
                <div class="layui-input-block" style="margin-left: 84px;">
                    <input class="layui-input" name="usdt_withdraw" value="${procedures.usdt_withdraw}" type="text" style="width: 53%;">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="add" lay-submit>
                确认修改
            </button>
        </div>

    </form>
</div>
<script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8">
</script>
<script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8">
</script>
<script>
    layui.use(['form','layer','layedit'], function(){
        $ = layui.jquery;
        var form = layui.form()
            ,layer = layui.layer
            ,layedit = layui.layedit;


        layedit.set({
            uploadImage: {
                url: "${ctx}/admin/notice/upload" //接口url
                ,type: 'post' //默认post
            }
        })

        //创建一个编辑器
        editIndex = layedit.build('L_content');



        //监听提交
        form.on('submit(add)', function(data){
            //console.log(data);
            /*var addr = '${addr}';
            var level2 = $('#aa').val();
            var level = null;
            if (level2=="111"){
                level=${user.level};
            }else {
                level = $('#aa').val();
            }*/
            var id=data.field.id;
            var tp_transfer=data.field.tp_transfer;
            var wnct_transfer=data.field.wnct_transfer;
            var wnct_withdraw=data.field.wnct_withdraw;
            var usdt_transfer=data.field.usdt_transfer;
            var usdt_withdraw=data.field.usdt_withdraw;
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/nodeSetup/EditProcedures",{id:id,tp_transfer:tp_transfer,wnct_transfer:wnct_transfer,wnct_withdraw:wnct_withdraw,usdt_transfer:usdt_transfer,usdt_withdraw:usdt_withdraw},function(res){
                if(res.msg=='ok'){
                    layer.alert("修改成功", {icon: 6},function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                        location.reload();

                    });
                }else{
                    layer.alert(res.msg,{icon:"5"});
                }
            })
            return false;
        });


    });
</script>

</body>

</html>
