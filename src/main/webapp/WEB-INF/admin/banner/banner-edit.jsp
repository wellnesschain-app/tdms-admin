<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>
        巴马水积分管理系统
    </title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
    <script>
        //添加轮播图
        function add(){
            var id=$("#id").val();
            var src= $("#LAY_demo_upload").attr("src");
            var link=$("#href").val();
            var describe=$("#describe").val();

            //alert(id+":"+src+":"+link+":"+describe)
            $.ajax({
                url:"${ctx}/admin/banner/editBanner",
                data:{id:id,src:src,href:link,describe:describe},
                dataType:"json",
                type:"GET",
                success:function(res){
                    if(res.msg=='ok'){
                        layer.alert("修改成功", {icon: 6},function () {
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
    </script>
</head>

<body>
<div class="x-body">
    <form class="layui-form">
        <input type="hidden" id="id" value="${banner.id}">
        <div class="layui-form-item">
            <label for="link" class="layui-form-label">
                <span class="x-red">*</span>轮播图
            </label>
            <div class="layui-input-inline">
                <div class="site-demo-upbar">
                    <input type="file" name="file" class="layui-upload-file" id="test">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label  class="layui-form-label">缩略图
            </label>
            <img id="LAY_demo_upload" width="400" src="/banner_upload/${banner.src}">
        </div>
        <div class="layui-form-item">
            <label  class="layui-form-label">
            </label>
            （由于服务器资源有限，所以此处每次给你返回的是同一张图片)
        </div>

        <div class="layui-form-item">
            <label for="href" class="layui-form-label">
                <span class="x-red">*</span>链接
            </label>
            <div class="layui-input-inline">
                <input type="text" id="href" name="href"  value="${banner.href}"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="describe" class="layui-form-label">
                <span class="x-red">*</span>描述
            </label>
            <div class="layui-input-inline">
                <input type="text" id="describe" name="desc" value="${banner.describe}"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <input  value="增加" type="button" onclick="add()" class="layui-btn" >
        </div>
    </form>
</div>
<script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8">
</script>
<script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8">
</script>
<script>
    layui.use(['form','layer','upload'], function(){
        $ = layui.jquery;
        var form = layui.form()
            ,layer = layui.layer;


        //图片上传接口
        layui.upload({
            elem:"#test",
            url: '${ctx}/admin/uploadBanner' //上传接口
            ,success: function(res){ //上传成功后的回调
                console.log(res);
                if(res.msg=='ok'){
                    layer.alert("图片上传成功！",{icon:"1"})
                    $('#LAY_demo_upload').attr('src',"/banner_upload/"+res.url);
                }else{
                    layer.alert("图片上传失败！",{icon:"2"})
                }
            }
        });



    });
</script>

</body>

</html>
