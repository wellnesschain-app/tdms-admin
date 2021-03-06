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
                标题
            </label>
            <div class="layui-input-block">
                <input type="text" id="L_title" name="tittle" required lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <div class="layui-input-block">
                        <textarea id="L_content" name="content"
                                  placeholder="请输入内容" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
            </div>
            <label for="L_content" class="layui-form-label" style="top: -2px;">
                内容
            </label>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    公告类型
                </label>
                <div class="layui-input-block">
                    <select lay-verify="required" name="cid">
                        <option value="0" selected>公告</option>
                        <option value="1">周报</option>
                        <option value="2">资讯</option>
                    </select>
                </div>
            </div>
        </div>

     <%--   <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    轮播选择
                </label>
                <div class="layui-input-block">

                    <select lay-verify="" name="cids">
                        <option value="">请选择</option>
                        <c:if test="${not empty bannerLists}">
                            <c:forEach items="${bannerLists}" var="n">


                                <option value="${n.id}">${n.describe}</option>

                            </c:forEach>
                        </c:if>
                        <c:if test="${empty bannerLists}">
                            <tr><td colspan="6" style="text-align: center;color: #ccc">暂无数据</td></tr>
                        </c:if>
                    </select>

                </div>
            </div>
        </div>--%>

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
            //var bannerid=data.field.cids;
            var type=data.field.cid;
            var bt=data.field.tittle;
            var nr=layedit.getContent(editIndex);
            //console.log(type+":"+bt+":"+nr);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/notice/release",{type:type,bt:bt,nr:nr},function(res){
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
