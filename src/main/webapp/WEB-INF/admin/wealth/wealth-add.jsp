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
            <label for="L_title" class="layui-form-label">
                计划名
            </label>
            <div class="layui-input-block">
                <input type="text" id="L_title" name="plan_name" required lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    收益利率(%)
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="interest_rate" min="1" step="1" max="100" value="1" type="number">
                </div>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    最小起投金额
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="min_money" type="number">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    最大起投金额
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="max_money"  type="number">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                    规则说明:
                <div class="layui-input-block">
                    <textarea name="instructions" class="layui-textarea" style="width: 400px;"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="add" lay-submit>
                立即发布
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
            var plan_name=data.field.plan_name;
            var min_money=data.field.min_money;
            var max_money=data.field.max_money;
            var interest_rate=data.field.interest_rate;
            var instructions=data.field.instructions;
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //console.log(type+":"+bt+":"+nr);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/wealth/addWealth",{plan_name:plan_name,min_money:min_money,max_money:max_money,interest_rate:interest_rate,instructions:instructions},function(res){
                if(res.msg=='ok'){
                    var al=layer.alert("发布成功", {icon: 6},function () {
                        //关闭当前frame
                        layer.close(al);
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
