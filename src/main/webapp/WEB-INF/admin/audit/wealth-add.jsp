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
                <input type="text" id="L_title" name="name" required lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    收益利率(%)
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="rate" min="1" step="1" max="100" value="1" type="number">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    理财期限(天)
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="day" min="30" step="30" max="90" value="30" type="number">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    众筹期限(天)
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="crowDay" style="width:70px" min="7" step="7" value="7" type="number">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    手续费(ETH)
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="serviceMoney" type="text" placeholder="选填">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    众筹额度(万)
                </label>
                <div class="layui-input-block">
                    <input class="layui-input" name="crowMoney" style="width:70px" min="10" step="5" value="10" type="number">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                    赎回规则说明:
                <div class="layui-input-block">
                    <textarea name="explain" class="layui-textarea" style="width: 400px;"></textarea>
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
            var name=data.field.name;
            var rate=data.field.rate;
            var day=data.field.day;
            var crowDay=data.field.crowDay;
            var serviceMoney=data.field.serviceMoney;
            var crowMoney=data.field.crowMoney;
            var explain=data.field.explain;
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //console.log(type+":"+bt+":"+nr);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/wealth/addWealth",{name:name,rate:rate,day:day,crowDay:crowDay,serviceMoney:serviceMoney,crowMoney:crowMoney,explain:explain},function(res){
                if(res.msg=='ok'){
                    layer.alert("发布成功", {icon: 6},function () {
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
