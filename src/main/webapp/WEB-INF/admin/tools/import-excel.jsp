<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8"/>
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
    <script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/admin/js/jquery.min.js"></script>
</head>

<body>
<div class="x-body">
    <form class="layui-form" id="uploadForm">
        <div class="layui-form-item">
            <label for="check" class="layui-form-label">
                <div class="layui-btn-normal" style="width: 110px;height: 38px;color: #fff;
                font-size: 15px;text-align: center;line-height: 38px;cursor: pointer;border-radius: 2px">
                    <i class="layui-icon">&#xe67c;</i>选择文件</div>
            </label>
                <input id="check" style="margin-left: -60px;margin-top: 15px"  type="file" name="excelFile"/>
        </div>
        <input class="layui-btn" type="button" style="width: 100px;height: 20px;line-height: 20px;margin-left: 20px"  id="import" value="开始解析">
    </form>
</div>
<script>
    layui.use("layer",function(){
            layer=layui.layer;

    })
    $(function(){
        $("#import").click(function(){
            $("#import").attr("disabled");
            //加载层
            var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            var formData = new FormData($('#uploadForm')[0]);
            $.ajax({
                type:"POST",
                url:"${ctx}/admin/tools/importExcel",
                processData: false,
                contentType: false,
                data:formData,
                dataType:'json',
                success:function(res){
                    layer.close(index);
                    if(res.msg=='ok'){
                        layer.alert("Excel解析成功！成功:"+res.success+",失败:"+res.error,{icon:1});
                    }else{
                        layer.alert(res.msg+"成功:"+res.success+",失败:"+res.error,{icon:2});
                    }
                }
            })
        })
    })
</script>
</body>

</html>