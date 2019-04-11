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
<c:if test="${abcdefg.size()>0}">
    <c:forEach items="${abcdefg}" var="s">
            <input type="hidden" name="id" id="id" value="${s.id}">
            <input type="hidden" name="addr" id="addr" value="${s.addr}">
            <input type="hidden" name="integral" id="integral" value="${s.integral}">
            <label >
                驳回理由
            </label>
            <input type="text" id="res" name="res" style="width:200px;height:30px;">
            <div class="layui-input-block">

            </div>
        </div>

    </c:forEach>
        </c:if>
        <%--<div class="layui-form-item">
            <button class="layui-btn" onclick="add()">
                保存修改
            </button>
        </div>--%>

        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="add" lay-submit>
                确定驳回
            </button>
        </div>

    </form>
</div>
<script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8">
</script>
<script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8">
</script>
<script>

    function add(){
        /*var id = ("#id").val();
        alert(id);*/
        var id = document.getElementById("id").value;
        var text = document.getElementById("res").value;

        alert(value+text);

        $.post("${ctx}/admin/audit/AgreedAudit",{id:id,text:text},function(res){
            if(res.msg=='ok'){
                layer.alert("成功！",{icon:"6"},function () {
                    location.reload();
                })

            }else{
                layer.alert(res.msg,{icon:"2"});
            }
        })


    }


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
            var id = document.getElementById("id").value;
            var text = document.getElementById("res").value;
            var addr = document.getElementById("addr").value;
            var integral = document.getElementById("integral").value;
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/audit/rejected",{id:id,text:text,addr:addr,integral:integral},function(res){
                if(res.msg=='ok'){
                    layer.alert("驳回成功", {icon: 6},function () {
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
