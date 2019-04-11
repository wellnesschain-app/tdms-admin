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
            <input type="hidden" name="id" value="${percentage.id}">
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">WNCT最大值(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="wnct_percentage_max" value="${percentage.wnct_percentage_max}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">WNCT最小值(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="wnct_percentage_min" value="${percentage.wnct_percentage_min}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 140px;">TP最大值(%)</label>
            <div class="layui-input-block" style="margin-left: 170px;">
                <input class="layui-input" name="tp_percentage_max"  value="${percentage.tp_percentage_max}" type="text" style="width: 80%;">
            </div>
        </div>

    </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">TP最小值(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="tp_percentage_min" value="${percentage.tp_percentage_min}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="add" lay-submit>
                保存修改
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
            var id=data.field.id;
            var wnct_percentage_max=data.field.wnct_percentage_max;
            var wnct_percentage_min=data.field.wnct_percentage_min;
            var tp_percentage_max=data.field.tp_percentage_max;
            var tp_percentage_min=data.field.tp_percentage_min;

            var add = "+";
            var sumA = eval(wnct_percentage_max + add + tp_percentage_min);
            var sumB = eval(wnct_percentage_min + add + tp_percentage_max);
            if (sumA < 100 || sumA > 100){
                alert("WNCT最大值或TP最小值设置错误!");
                return false;
            }
            if (sumB < 100 || sumB > 100){
                alert("WNCT最小值或TP最大值设置错误!");
                return false;
            }
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/integral/update",{id:id,wnct_percentage_max:wnct_percentage_max,wnct_percentage_min:wnct_percentage_min,tp_percentage_max:tp_percentage_max,tp_percentage_min:tp_percentage_min},function(res){
                if(res.msg=='ok'){
                    layer.alert("修改成功", {icon: 6},function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
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
