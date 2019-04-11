<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>
        水链后台管理系统
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
<div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>提币审核</cite></a>
              <a><cite>驳回列表</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i><span style="display: inline-block;line-height: 30px">刷新</span></a>
</div>
<div class="x-body">
    <form class="layui-form x-center" action="${ctx}/admin/audit/toSVORejectedList" method="get" style="width:800px">
        <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-form-item" style="width: 500px;margin: 0 auto;margin-bottom: 15px">
                <div class="layui-input-inline">
                    <input type="text" name="id"  placeholder="请输入ID" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="width:80px">
                    <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                </div>
            </div>
        </div>
    </form>
    <xblock>共有数据：${audits.total} 条</span></xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <input onclick="checkAll()" type="checkbox" name="" value=""/>
            </th>
            <th>
                提出账户
            </th>
            <th>
                用户ID
            </th>
            <th>
                接收账户
            </th>
            <%--<th>
                是否实名
            </th>--%>
            <th>
                手续费
            </th>
            <th>
                实际到账
            </th>
            <th>
                驳回理由
            </th>
            <th>
                申请时间
            </th>
            <th>
                审核时间
            </th>
            <th>
                类型
            </th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:if test="${audits.list.size()==0}">
            <tr><td colspan="9" style="text-align: center;color: #ccc">暂无数据</td></tr>
        </c:if>
        <c:if test="${audits.list.size()>0}">
            <c:forEach items="${audits.list}" var="a">
                <tr>
                    <td>
                        <input type="checkbox" value="${a.id}" name="">
                    </td>
                    <td>
                        <u style="cursor:pointer">
                             ${a.fromAddr}
                        </u>
                    </td>
                    <td>
                        ${a.userid}
                    </td>
                    <td>
                        <u style="cursor:pointer">
                             ${a.toAddr}
                        </u>
                    </td>
                    <%--<td>
                        <c:if test="${a.isAuthen==0}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">
                                未实名
                            </span>
                        </c:if>
                        <c:if test="${a.isAuthen==1}">
                            <span class="layui-btn layui-btn-succes layui-btn-mini">
                                已实名
                            </span>
                            <a style="cursor: pointer" onclick="authens('认证信息','${ctx}/admin/user/authenDetails?addr=${a.from}',600,380)">(点击查看)</a>
                        </c:if>
                    </td>--%>
                    <td >
                        ${a.fee}
                    </td>
                    <td>
                        ${a.count}
                    </td>
                    <td>
                        <%--<a style="cursor: pointer" onclick="textDetails('${a.text}')">(点击查看)</a>--%>
                                ${a.rejectMsg}
                    </td>
                    <td>
                         <fmt:formatDate value="${a.applicationTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${a.auditTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                    </td>
                    <td>
                        <c:if test="${a.type==1}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">
                                WNCT
                            </span>
                        </c:if>
                        <c:if test="${a.type==2}">
                            <span class="layui-btn layui-btn-succes layui-btn-mini">
                                USDT
                            </span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <input type="hidden" id="pages" value="${audits.pages}"/>
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
        laypage({
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
        });


        //日期格式化
        function timeFormat(time){
            var date = new Date(time);
            Y = date.getFullYear() + '年';
            M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '月';
            D = (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + '日 ';
            h = (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
            m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
            s = (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds());


            var YMDHMS = Y+M+D+h+m+s;

            // var d=new Date();
            // var YMDHMS = d.getFullYear() + "年" +(d.getMonth()+1) + "月" + d.getDate() + "日   " +
            //     d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
            return YMDHMS;
        }

        //获取审核未通过列表
        function getMemberList(curr) {
            var status = 2;
            var id = '${id}';
            $.ajax({
                url:"${ctx}/admin/audit/auditSearchPage",
                type:"GET",
                data:{"pageNum":curr,"pageSize":10,"status":status,"id":id},
                dataType:"json",
                success:function(res){
                    if(res.msg=="ok"){
                        $("#tbody").empty();
                        var tr = "";
                        var authen="";
                        $("#pages").val(res.audits.pages);
                        if(res.audits.list.length<=0){
                            tr='<tr><td colspan="8" style="text-align: center;color: #ccc">暂无数据</td></tr>'
                        }else{
                            $.each(res.audits.list,function (i, v) {
                                if(v.isAuthen==0){
                                    authen='<td><span class="layui-btn layui-btn-warm layui-btn-mini">未实名</span></td>'
                                }else if(v.isAuthen==1){
                                    authen='<td><span class="layui-btn layui-btn-succes layui-btn-mini">已实名</span><a style="cursor: pointer" onclick="authens(\'认证信息\',\'${ctx}/admin/user/authenDetails?addr='+v.from+'\',600,380)">(点击查看)</a></td>'
                                }
                                if(v.type==1){
                                    type='<span class="layui-btn layui-btn-warm layui-btn-mini">WNCT</span>';
                                }else if(v.type==2){
                                    type='<span class="layui-btn layui-btn-succes layui-btn-mini">USDT</span>';
                                }
                            /*tr+='<tr><td>'+v.id+'</td>'+
                                '<td><u style="cursor:pointer">'+v.to+'</u></td>' +
                                '<td><u style="cursor:pointer">' +v.from+'</u></td>' +
                                authen +
                                '<td>' +v.fee+'</td>' +
                                '<td>' +v.actualValue +'</td>' +
                                '<td><a style="cursor: pointer" onclick="textDetails(\''+v.text+'\')">(点击查看)</a></td>'+
                                '<td>'+timeFormat(v.applyTime)+'</td>'+
                                '<td>'+timeFormat(v.auditTime)+'</td>'*/


                                tr+=' <tr><td><input type="checkbox" value="'+v.id+'" name="">' + ' </td>' +
                                    '<td >'+v.fromAddr+'</td>' +
                                    '<td >'+v.userid+'</td>' +
                                    '<td >'+v.toAddr+'</td>' +
                                    '<td >'+v.fee+'</td>' +
                                    '<td>' +v.count+'</td>' +
                                    '<td>' +v.rejectMsg+'</td>' +
                                    '<td>'+timeFormat(v.applicationTime)+'</td>'+
                                    '<td>'+timeFormat(v.auditTime)+'</td>'+
                                    '<td>' + type +'</td><tr>'
                            })
                        }

                        $("#tbody").append(tr);
                    }else{
                        layer.msg(res.msg)
                    }
                }
            })
        }


        var start = {
            min: '1918-01-01 00:00:00'
            ,max: '2099-06-16 23:59:59'
            ,istoday: false
            ,choose: function(datas){
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };

        var end = {
            min: '1918-01-01 00:00:00'
            ,max: '2099-06-16 23:59:59'
            ,istoday: false
            ,choose: function(datas){
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };



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


    /*查看认证信息*/
    function authens(title,url,w,h){
        x_admin_show(title,url,w,h);
    }



    function textDetails(text){
        layer.alert(text);
    }
</script>

</body>
</html>
