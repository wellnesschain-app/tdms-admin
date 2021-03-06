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
    <style>
        .line-limit-length {

            overflow: hidden;

            text-overflow: ellipsis;

            white-space: nowrap; //文本不换行，这样超出一行的部分被截取，显示...

        }
    </style>
</head>
<body>
<div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>公告管理</cite></a>
              <a><cite>公告列表</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="
    line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i><span style="display: inline-block;line-height: 30px">刷新</span></a>
</div>
<div class="x-body">
    <xblock><button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button><button class="layui-btn" onclick="notice_add('添加公告','${ctx}/admin/notice/toAddNotice','900','780')"><i class="layui-icon">&#xe608;</i>添加</button><span class="x-right" style="line-height:40px">共有数据：${notices.list.size()} 条</span></xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <input type="checkbox" onclick="checkAll(this)" name="" value="">
            </th>
            <th>
                标题
            </th>

            <th>
                内容
            </th>
            <th>
                类型
            </th>
            <th>
                发布时间
            </th>
            <th>
                操作
            </th>
        </tr>
        </thead>
        <tbody id="x-img">
        <c:if test="${not empty notices.list}">
            <c:forEach items="${notices.list}" var="n">
                <tr>
                    <td>
                        <input type="checkbox" value="${n.id}" name="">
                    </td>
                    <td>
                        <font style="font-weight:bold">${n.title}</font>
                    </td>
                    <td >
                        <a style="cursor: pointer" onclick="showContent('${n.id}','${n.title}')">(点击查看内容)</a>
                    </td>
                    <td class="td-status">
                        <c:if test="${n.type==0}">
                            <span class="layui-btn layui-btn-normal layui-btn-mini">
                                公告
                            </span>
                        </c:if>
                        <c:if test="${n.type==1}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">
                                周报
                            </span>
                        </c:if>
                        <c:if test="${n.type==2}">
                            <span class="layui-btn layui-btn-danger layui-btn-mini">
                                咨询
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <fmt:formatDate value="${n.time}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                    </td>
                    <td class="td-manage">
                        <c:if test="${n.status==0}">
                            <a style="text-decoration:none" onClick="banner_stop(this,${n.id})" href="javascript:;" title="不显示"><i class="layui-icon">&#xe601;</i></a>
                        </c:if>
                        <c:if test="${n.status==1}">
                            <a style="text-decoration:none" onClick="banner_start(this,${n.id})" href="javascript:;" title="显示"><i class="layui-icon">&#xe62f;</i></a>
                        </c:if>
                        <a title="编辑" href="javascript:;" onclick="notice_edit('编辑公告','${ctx}/admin/notice/toEditNotice?id='+${n.id},'900','710')"
                           class="ml-5" style="text-decoration:none">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" href="javascript:;" onclick="notice_del(this,'${n.id}')"
                           style="text-decoration:none">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty notices.list}">
            <tr><td colspan="6" style="text-align: center;color: #ccc">暂无数据</td></tr>
        </c:if>
        </tbody>
    </table>

    <div id="page"></div>
</div>
<script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8"></script>
<script>
    layui.use(['laydate','element','laypage','layer'], function(){
        $ = layui.jquery;//jquery
        laydate = layui.laydate;//日期插件
        lement = layui.element();//面包导航
        laypage = layui.laypage;//分页
        layer = layui.layer;//弹出层

        //以上模块根据需要引入

        layer.ready(function(){ //为了layer.ext.js加载完毕再执行
            layer.photos({
                photos: '#x-img'
                //,shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
            });
        });

    });

    //批量删除提交
    function delAll () {
        var checks=$("input[type='checkbox']");
        var arr=[];
        var index=0;
        $.each(checks,function(i,v){
            if(i!=0){
                if($(this).is(":checked")){
                    arr[index]=v.value;
                    index++;
                }
            }
        })

        layer.confirm('确认要删除吗？',function(){
            $.post("${ctx}/admin/notice/deletes",{arr:arr},function(res){
                if(res.msg=='ok'){
                    //捉到所有被选中的，发异步进行删除
                    layer.alert("成功："+res.success+"条,失败："+res.error+"条。",{icon:"6"})
                    setTimeout(function(){
                        location.reload();
                    },1500)
                }else{
                    layer.alert(res.msg,{icon:"2"});
                    layer.alert("成功："+res.success+"条,失败："+res.error+"条。",{icon:"6"})
                }
            })
        });
    }
    /*添加*/
    function notice_add(title,url,w,h){
        x_admin_show(title,url,w,h);
    }
    /*停用*/
    function banner_stop(obj,id){
        layer.confirm('确认不显示吗？',function(index){
            $.get("${ctx}/admin/banner/stopBanner?id="+id,function(res){
                if(res.msg=='ok'){
                    layer.msg('不显示!',{icon: 5,time:1000});
                    setTimeout(function(){
                        location.reload();
                    },1500)
                }else{
                    layer.alert(res.msg,{icon:5});
                }
            })

        });
    }

    // 编辑
    function notice_edit (title,url,w,h) {
        //alert(w+":"+h);
        x_admin_show(title,url,w,h);
    }
    /*删除*/
    function notice_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.get("${ctx}/admin/notice/delete?id="+id,function(res){
                if(res.msg=='ok'){
                    layer.msg('已删除!',{icon:1,time:1000});

                    setTimeout(function(){
                        location.reload();
                    },1500)
                }else{
                    layer.msg(res.msg,{icon:"2"})
                }
            })

        });
    }

    //全选
    var isCheckAll = false;
    function checkAll() {
        if (isCheckAll) {
            $("input[type='checkbox']").each(function() {
                this.checked = false;
            });
            isCheckAll = false;
        } else {
            $("input[type='checkbox']").each(function() {
                this.checked = true;
            });
            isCheckAll = true;
        }
    }


    /**
     * 请求公告详情
     * @param v1
     * @param v2
     */
    function showContent(v1,v2) {
        layer.open({
            type: 2,
            title:v2,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 1, //不显示关闭按钮
            anim: 2,
            area:['600px','700px'],
            shadeClose: true, //开启遮罩关闭
            content:"${ctx}/admin/notice/toDetails?id="+v1
        });
    }
</script>
</body>
</html>
