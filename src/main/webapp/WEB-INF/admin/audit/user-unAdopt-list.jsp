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
    <%
        String url = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();
    %>
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
</head>
<body>
<div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>用户实名审核</cite></a>
              <a><cite>待审未通过表</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i><span style="display: inline-block;line-height: 30px">刷新</span></a>
</div>
<div class="x-body">
    <form class="layui-form x-center" action="${ctx}/admin/audit/auditSearch" method="get" style="width:800px">
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
    <xblock>共有数据：${userAudits.total} 条</span></xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                用户ID
            </th>
            <th>
                用户姓名
            </th>
            <th>
                手机
            </th>
            <th>
                身份证
            </th>
            <th>
                身份证正面
            </th>
            <th>
                身份证反面
            </th>
            <th>
                手持身份证
            </th>
            <th>
                驳回原因
            </th>
            <th>
                申请时间
            </th>
            <th>
                审核时间
            </th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:if test="${userAudits.list.size()==0}">
            <tr><td colspan="9" style="text-align: center;color: #ccc">暂无数据</td></tr>
        </c:if>
        <c:if test="${userAudits.list.size()>0}">
            <c:forEach items="${userAudits.list}" var="a">
                <tr>
                    <td>
                        ${a.uid}
                    </td>
                    <td>
                        ${a.name}
                    </td>
                    <td>
                        ${a.tel}
                    </td>
                    <td>
                            ${a.num}
                    </td>
                    <td>
                        <img  src="<%=url%>${a.img1}" width="200" height="77" alt="正面"><br/>点击图片试试
                    </td>
                    <td>
                        <img  src="<%=url%>${a.img2}" width="200" height="77" alt="反面"><br/>点击图片试试
                    </td>
                    <td >
                        <img  src="<%=url%>${a.img3}" width="200" height="77" alt="手持"><br/>点击图片试试
                    </td>
                    <td>
                        <a style="cursor: pointer" onclick="textDetails('${a.rejectMsg}')">(点击查看)</a>
                    </td>
                    <td>
                            <fmt:formatDate value="${a.applyTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${a.auditTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <input type="hidden" id="pages" value="${userAudits.pages}">
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


        layer.ready(function(){ //为了layer.ext.js加载完毕再执行
            layer.photos({
                photos: '#tbody'
                //,shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
            });
        });

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
                url:"${ctx}/admin/audit/getUserUnAudoptList",
                type:"GET",
                data:{"pageNum":curr,"pageSize":5},
                dataType:"json",
                success:function(res){
                    if(res.msg=="ok"){
                        $("#tbody").empty();
                        var tr = "";
                        var name="";
                        $("#pages").val(res.userAudits.pages);
                        if(res.userAudits.list.length<=0){
                            tr='<tr><td colspan="9" style="text-align: center;color: #ccc">暂无数据</td></tr>'
                        }else{
                            $.each(res.userAudits.list,function (i, v) {
                            tr+='<tr><td>'+v.uid+'</td>'+
                                '<td>'+v.name+'</td>'+
                                '<td>'+v.tel+'</td>'+
                                '<td>'+v.num+'</td>'+
                                '<td><img  src="<%=url%>'+v.img1+'" width="200" height="77" alt="正面"><br/>点击图片试试</td>'+
                                '<td><img  src="<%=url%>'+v.img2+'" width="200" height="77" alt="反面"><br/>点击图片试试</td>'+
                                '<td><img  src="<%=url%>'+v.img3+'" width="200" height="77" alt="手持"><br/>点击图片试试</td>'+
                                '<td><a style="cursor: pointer" onclick="textDetails(\''+v.rejectMsg+'\')">(点击查看)</a></td>'+
                                '<td>'+timeFormat(v.applyTime)+'</td>'+
                                '<td>'+timeFormat(v.auditTime)+'</td></tr>';
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


    function adopt(id) {
        layer.confirm("确定通过?",{
            btn:['确定','取消']
        },function () {
            //加载层
            var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            $.get("${ctx}/admin/audit/adoptUser?id="+id,function(res){
                if(res.msg=='ok'){
                    layer.close(index);
                    layer.alert("审核已通过",{icon:"6"},function(){
                        location.reload();
                    });
                }else{
                    layer.close(index);
                    layer.alert(res.msg,{icon:"5"});
                }
            })
        })

    }

    /*查看认证信息*/
    function authens(title,url,w,h){
        x_admin_show(title,url,w,h);
    }

    function textDetails(text){
        layer.alert(text);
    }


    function reject(id){
        layer.prompt({title: '请填写驳回原因，并确认', formType: 2}, function(text, index){

            /*layer.msg('演示完毕！写下了：'+text);*/
            $.get("${ctx}/admin/audit/unAdoptUser",{id:id,text:text},function(res){
                if(res.msg=='ok'){
                    layer.msg("已驳回",{icon:"1"});
                    setTimeout(function(){
                        location.reload();
                    },2000)
                }else{
                    layer.msg(res.msg,{icon:"2"});
                }
            })
            layer.close(index);
        })
    }
</script>

</body>
</html>
