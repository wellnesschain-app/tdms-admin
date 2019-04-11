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
    /*停用*/
    function wealth_stop(obj,id){
        layer.confirm('确认停止吗？',function(index){
            $.get("${ctx}/admin/wealth/wealthService?id="+id+"&type=1",function(res){
                if(res.msg=='ok'){
                    layer.msg('计划已截止!',{icon: 5,time:1000});
                    setTimeout(function(){
                        location.reload();
                    },1500)
                }else{
                    layer.alert(res.msg,{icon:5});
                }
            })

        });
    }

    /*启用*/
    function wealth_start(obj,id){
        layer.confirm('确认开始吗？',function(index){
            $.get("${ctx}/admin/wealth/wealthService?id="+id+"&type=0",function(res){
                if(res.msg=='ok'){
                    layer.msg('计划已开启!',{icon: 5,time:1000});
                    setTimeout(function(){
                        location.reload();
                    },1500)
                }else{
                    layer.alert(res.msg,{icon:5});
                }
            })

        });
    }
</script>
<div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>审核列表</cite></a>
              <a><cite>审核列表</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i><span style="display: inline-block;line-height: 30px">刷新</span></a>
</div>
<div class="x-body">
    <xblock><button class="layui-btn" onclick="wealth_add('添加计划','${ctx}/admin/wealth/toAddWealth','900','710')"><i class="layui-icon">&#xe608;</i>添加</button><span class="x-right" style="line-height:40px">共有数据：${wealths.size()} 条</span></xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <input onclick="checkAll()" type="checkbox" name="" value=""/>
            </th>
            <th>
                钱包地址
            </th>
            <th>
                昵称
            </th>
            <th>
                兑换积分
            </th>
            <th>
                身份证
            </th>
            <th>
                名字
            </th>
            <th>
                银行卡
            </th>
            <th>
                兑换时间
            </th>

            <th>
                审核状态
            </th>
            <th>
                理由
            </th>
            <th>
                操作
            </th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:if test="${agreed.size()==0}">
            <tr><td colspan="12" style="text-align: center;color: #ccc">暂无数据</td></tr>
        </c:if>
        <c:if test="${agreed.size()>0}">
            <c:forEach items="${agreed}" var="w">
                <tr>
                    <td>
                        <input type="checkbox" value="${w.id}" name="">
                    </td>
                    <td>
                        <u style="cursor:pointer">
                            ${w.addr}
                        </u>
                    </td>
                    <td >
                            ${w.nickname}
                    </td>
                    <td >
                        ${w.integral}
                    </td>
                    <td >
                            ${w.idcard}
                    </td>
                    <td>
                            ${w.name}
                    </td>
                    <td>
                            ${w.bankcard}
                    </td>
                    <td>
                            ${w.time}
                    </td>


                    <td>
                        <c:if test="${w.state==1}">
                            <span class="layui-btn layui-btn-succes layui-btn-mini">
                                通过
                            </span>
                        </c:if>
                        <c:if test="${w.state==2}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">
                                驳回
                            </span>
                        </c:if>
                        <c:if test="${w.state==0}">
                            <span class="layui-btn layui-btn-danger layui-btn-mini">
                                待审核
                            </span>
                        </c:if>
                    </td>
                    <td>
                            ${w.reason}
                    </td>
                    <td class="td-manage">
                        <c:if test="${w.status==0}">
                            <a style="text-decoration:none" onClick="wealth_stop(this,${w.id})" href="javascript:;" title="结束"><i class="layui-icon">&#xe601;</i></a>
                        </c:if>
                        <c:if test="${w.status==1}">
                            <a style="text-decoration:none" onClick="wealth_start(this,${w.id})" href="javascript:;" title="开始"><i class="layui-icon">&#xe62f;</i></a>
                        </c:if>
                        <a title="同意" href="javascript:;" onclick="audit_edit(this,'${w.id}')"
                           class="ml-5" style="text-decoration:none">
                            <i class="layui-icon">&#xe605;</i>
                        </a>
                        <a title="驳回" href="javascript:;" onclick="wealth_edit('驳回确认','${ctx}/admin/audit/toRejected?id='+${w.id},'600','400')"
                           class="ml-5" style="text-decoration:none">
                            <i class="layui-icon">&#x1006;</i>
                        </a>
                        <a title="删除" href="javascript:;" onclick="wealth_del(this,'${w.id}')"
                           style="text-decoration:none">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <%--<input type="hidden" id="pages" value="${wealths.pages}">
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



    });

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

    // 同意
    function  audit_edit(obj,id) {
        layer.confirm('确认通过审核吗？',function(){
            $.post("${ctx}/admin/audit/AgreedAudit",{id:id},function(res){
                if(res.msg=='ok'){
                    layer.alert("成功！",{icon:"6"},function () {
                        location.reload();
                    })

                }else{
                    layer.alert(res.msg,{icon:"2"});
                }
            })
        });
    }

    // 删除计划
    function  wealth_del(obj,id) {
        layer.open({
            title: '提示'
            ,content: '该功能尚未开放'
        });
        /*layer.confirm('确认要删除吗？',function(){
            $.post("${ctx}/admin/wealth/deleteWealth111",{id:id},function(res){
                if(res.msg=='ok'){

                    //捉到所有被选中的，发异步进行删除
                    layer.alert("删除成功！",{icon:"6"},function () {
                        location.reload();
                    })

                }else{
                    layer.alert(res.msg,{icon:"2"});
                }
            })
        });*/
    }


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
            $.post("${ctx}/admin/member/deleteList",{arr:arr},function(res){
                if(res.msg=='ok'){
                    //捉到所有被选中的，发异步进行删除
                    layer.alert("成功："+res.success+"条,失败："+res.error+"条。",{icon:"6"})
                    setTimeout(function(){
                        location.reload();
                    },1500)
                }else{
                    layer.alert(res.msg,{icon:"2"});
                }
            })
        });
    }

    // 编辑
    function wealth_edit (title,url,w,h) {
        //alert(w+":"+h);
        x_admin_show(title,url,w,h);
    }

    /*计划-添加*/
    function wealth_add(title,url,w,h){
        x_admin_show(title,url,w,h);
    }
    /*用户-查看*/
    function member_show(title,url,id,w,h){
        x_admin_show(title,url,w,h);
    }
</script>

</body>
</html>
