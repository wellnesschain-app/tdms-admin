<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
</head>
<script src="${ctx}/static/admin/js/jquery.min.js"></script>
<script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/static/admin/lib/layui/lay/modules/laydate.js"></script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        var $ = layui.jquery;
        var end = {
            min: laydate.now()
            ,max: '2099/06/16'
            ,istoday: false
        };

        document.getElementById('endDate').onclick=function(){
            end.elem=this;
            end.format='YYYY/mm/DD';
            laydate(end);
        }

    });
</script>
<script>
    layui.use('layer',function(){
        var layer=layui.layer;

    })

</script>
<body>
<form class="layui-form layui-form-pane" style="margin: 20px">
    <div class="layui-form-item">
        <label for="name" class="layui-form-label">
            活动名称
        </label>
        <div class="layui-input-block">
            <input type="text" id="name" name="title" required lay-verify="title"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">结束日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="endDate" placeholder="yyyy-MM-dd">
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <input class="layui-btn" type="button" value="立即发布" onclick="add()">
    </div>
</form>
<script>
    //添加活动倒计时
    function add(){
        var name=$("#name").val();
        var endDate=$("#endDate").val();
        var flag=true;
        //alert(name+":"+endDate);
        if(name==''){
            flag=false;
            layer.tips("请输入内容",{tips:[1,"#2299ee"],time:2000})
        }else if(endDate==''){
            flag=false;
            layer.tips("请输入内容",{tips:[1,"#2299ee"],time:2000})
        }

        if(flag){
            $.ajax({
                url:"${ctx}/admin/banner/addActivity",
                data:{name:name,endTime:endDate},
                dataType:"json",
                type:"GET",
                success:function(res){
                    if(res.msg=='ok'){
                        layer.alert("发布成功", {icon: 6},function () {
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
    }
</script>
</body>

</html>