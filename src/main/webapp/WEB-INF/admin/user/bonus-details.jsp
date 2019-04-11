<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>
        巴马水积分后台管理系统
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
<script>

</script>
<div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>积分统计</cite></a>
              <a><cite>积分详情</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i><span style="display: inline-block;line-height: 30px">刷新</span></a>
</div>
<div class="x-body">
    <xblock><span class="x-right" style="line-height:40px">共有数据：${bonus.size()} 条</span></xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th style="display:none">
                <input onclick="checkAll()" type="checkbox" name="" value="" style="display:none"/>
            </th>
            <th>
                数量
            </th>
            <th>
                类型
            </th>
            <th>
                记录时间
            </th>


        </tr>
        </thead>
        <tbody id="tbody">
        <c:if test="${empty bonus}">
            <tr><td colspan="6" style="text-align: center;color: #ccc">暂无数据</td></tr>
        </c:if>
        <c:if test="${not empty bonus}">
            <c:forEach items="${bonus}" var="b">
                <tr>
                    <td style="display:none">
                        <input type="checkbox" value="${b.id}" name="">
                    </td>
                    <td>
                            ${b.val}
                    </td>
                    <td >
                        <c:if test="${b.type==0}">
                            <span class="layui-btn layui-btn-succes layui-btn-mini">
                                理财分红
                            </span>
                        </c:if>
                        <c:if test="${b.type==1}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">
                                邀请分红
                            </span>
                        </c:if>
                    </td>
                    <td >
                            ${b.time}
                    </td>

                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <%--<input type="hidden" id="pages" value="${integrals.pages}">
    <div id="page"></div>--%>
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
        /*laypage({
            cont: 'page'
            ,pages: $("#pages").val()
            ,first: 1
            ,last: $("#pages").val()
            ,prev: '<em>&laquo;</em>'
            ,next: '<em>&raquo;</em>'
            ,jump: function(obj, first) {
                if (!first) {
                    console.log(obj.curr)
                    getMemberList(obj.curr)
                }
            }
        });*/

    });

</script>

</body>
</html>
