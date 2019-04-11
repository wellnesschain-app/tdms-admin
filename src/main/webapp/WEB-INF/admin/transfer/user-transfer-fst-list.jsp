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
              <a><cite>流水管理</cite></a>
              <a><cite>用户FST转账记录</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i><span style="display: inline-block;line-height: 30px">刷新</span></a>
</div>
<div class="x-body">
    <form class="layui-form x-center" action="${ctx}/admin/member/search" method="get" style="width:800px">
        <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-form-item" style="width: 500px;margin: 0 auto;margin-bottom: 15px">
                <div class="layui-input-inline">
                    <input type="text" name="nickname"  placeholder="请输入钱包地址" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="width:80px">
                    <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                </div>
            </div>
        </div>
    </form>
    <xblock>共有数据：${fstTransfers.total} 条</span></xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <input onclick="checkAll()" type="checkbox" name="" value=""/>
            </th>
            <th>
                转出地址
            </th>
            <th>
                转入地址
            </th>
            <th>
                交易类型
            </th>
            <th>
                转账金额
            </th>
            <th>
                实际到账
            </th>
            <th>
                手续费
            </th>
            <th>
                交易时间
            </th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:if test="${fstTransfers.list.size()==0}">
            <tr><td colspan="8" style="text-align: center;color: #ccc">暂无数据</td></tr>
        </c:if>
        <c:if test="${fstTransfers.list.size()>0}">
            <c:forEach items="${fstTransfers.list}" var="t">
                <tr>
                    <td>
                        <input type="checkbox" value="${t.F01}" name="">
                    </td>
                    <td>
                        <u style="cursor:pointer">
                            ${t.F03}
                        </u>
                    </td>
                    <td>
                        <u style="cursor:pointer">
                             ${t.F02}
                        </u>
                    </td>
                    <td >
                        <c:if test="${t.F04==1}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">
                                FST
                            </span>
                        </c:if>
                        <c:if test="${t.F04==2}">
                            <span class="layui-btn layui-btn-danger layui-btn-mini">
                                积分
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <fmt:formatNumber value="${t.F07}" pattern="#.00000000"/>
                    </td>
                    <td>
                        <fmt:formatNumber value="${t.F09}" pattern="#.00000000"/>
                    </td>
                    <td>
                        ${t.F08}
                    </td>
                    <td>
                        <fmt:formatDate value="${t.F06}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <input type="hidden" id="pages" value="${fstTransfers.pages}">
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

        //获取会员列表
        function getMemberList(curr) {
            $.ajax({
                url:"${ctx}/admin/transfer/getUserFSTTransferList",
                type:"GET",
                data:{"pageNum":curr,"pageSize":10},
                dataType:"json",
                success:function(res){
                    if(res.msg=="ok"){
                        $("#tbody").empty();
                        var tr = "";
                        var type="";
                        $("#pages").val(res.fstTransfers.pages);
                        if(res.fstTransfers.list.length<=0){
                            tr='<tr><td colspan="8" style="text-align: center;color: #ccc">暂无数据</td></tr>'
                        }else{
                            $.each(res.fstTransfers.list,function (i, v) {
                                if(v.F04==1){
                                    type='<span class="layui-btn layui-btn-warm layui-btn-mini">FST</span>'
                                }else if(v.F04==2){
                                    type='<span class="layui-btn layui-btn-danger layui-btn-mini">积分</span>'
                                }
                            tr+='<tr><td><input type="checkbox" value="'+v.F01+'" name=""></td>'+
                                '<td><u style="cursor:pointer">' +v.F02+'</u></td>' +
                                '<td><u style="cursor:pointer">' +v.F03+'</u></td>' +
                                '<td >' +type+'</td>' +
                                '<td>' +parseFloat(v.F07).toFixed(8)+'</td>' +
                                '<td>' +parseFloat(v.F09).toFixed(8)+'</td>' +
                                '<td>' +v.F08+'</td>' +
                                '<td>' +timeFormat(v.F06) +'</td></tr>'
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
    /*用户-添加*/
    function member_add(title,url,w,h){
        x_admin_show(title,url,w,h);
    }
    /*用户-查看*/
    function member_show(title,url,id,w,h){
        x_admin_show(title,url,w,h);
    }
</script>

</body>
</html>