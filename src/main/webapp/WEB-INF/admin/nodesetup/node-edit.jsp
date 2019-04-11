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
            <input type="hidden" name="id" value="${node.id}">
            <label for="L_title" class="layui-form-label">级别</label>
            <div class="layui-input-block">
                <input type="text" id="L_title" name="level" value="${node.level}" required lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">晋级条件(美金)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="promotion_conditions" value="${node.promotion_conditions}" type="text" style="width: 80%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">直推奖励(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="direct_promotion_award" value="${node.direct_promotion_award}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">发展奖励(第二层)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="development_award"  value="${node.development_award}" type="text" style="width: 80%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">同级奖励(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="same_award" value="${node.same_award}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">旅游基金(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="tourism_fund" value="${node.tourism_fund}" type="text" style="width: 80%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">购车基金(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="car_purchase_fund" value="${node.car_purchase_fund}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">购房基金(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="purchase_fund" value="${node.purchase_fund}" type="text" style="width: 80%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">现金基金(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="cash_fund" value="${node.cash_fund}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">购房基金(%)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="purchase_fund" value="${node.purchase_fund}" type="text" style="width: 80%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">级差</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="gradation" value="${node.gradation}" type="text" style="width: 80%;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">静态算力</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="static_calculation" value="${node.static_calculation}" type="text" style="width: 80%;">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">次数</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="frequency" value="${node.frequency}" type="text" style="width: 80%;">
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 140px;">购买的节点收益(份数)</label>
                <div class="layui-input-block" style="margin-left: 170px;">
                    <input class="layui-input" name="bought_static_return" value="${node.bought_static_return}" type="text" style="width: 80%;">
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
            var level=data.field.level;
            var promotion_conditions=data.field.promotion_conditions;
            var direct_promotion_award=data.field.direct_promotion_award;
            var development_award=data.field.development_award;
            var same_award=data.field.same_award;
            var tourism_fund=data.field.tourism_fund;
            var car_purchase_fund=data.field.car_purchase_fund;
            var purchase_fund=data.field.purchase_fund;
            var cash_fund=data.field.cash_fund;
            var gradation=data.field.gradation;
            var static_calculation=data.field.static_calculation;
            var static_return=data.field.static_return;
            var frequency=data.field.frequency;
            var bought_static_return=data.field.bought_static_return;
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/nodeSetup/upNodeSetup",{id:id,level:level,promotion_conditions:promotion_conditions,direct_promotion_award:direct_promotion_award,development_award:development_award,same_award:same_award,tourism_fund:tourism_fund,car_purchase_fund:car_purchase_fund,purchase_fund:purchase_fund,cash_fund:cash_fund,gradation:gradation,static_calculation:static_calculation,static_return:static_return,frequency:frequency,bought_static_return:bought_static_return},function(res){
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
