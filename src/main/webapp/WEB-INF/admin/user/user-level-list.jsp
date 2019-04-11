<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>
        阳光成单系统
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
              <a><cite>会员管理</cite></a>
              <a><cite>会员列表</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i><span style="display: inline-block;line-height: 30px">刷新</span></a>
</div>
<div class="x-body">
    <form class="layui-form x-center" action="${ctx}/admin/user/toUserLevelSearchList" method="get" style="width:800px">
        <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-form-item" style="width: 500px;margin: 0 auto;margin-bottom: 15px">
                <div class="layui-input-inline">
                    <input type="text" name="id"  placeholder="请输入用户ID" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="width:80px">
                    <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                </div>
            </div>
        </div>
    </form>
    <xblock>共有数据：${wallets.total} 条</span></xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <input onclick="checkAll()" type="checkbox" name="" value=""/>
            </th>
            <th>
                用户ID
            </th>
            <th>
                用户等级
            </th>
            <th>
                手机号
            </th>
            <th>
                邀请人
            </th>
            <%--<th>
                实名信息
            </th>--%>
            <th>
                状态
            </th>
            <th>
                注册时间
            </th>
            <th>
                操作
            </th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:if test="${wallets.list.size()==0}">
            <tr><td colspan="6" style="text-align: center;color: #ccc">暂无数据</td></tr>
        </c:if>
        <c:if test="${wallets.list.size()>0}">
            <c:forEach items="${wallets.list}" var="w">
                <tr>
                    <td>
                        <input type="checkbox" value="${w.id}" name="">
                    </td>
                    <td >
                            ${w.id}
                    </td>
                    <td>
                        <c:if test="${w.level==1}">
                            体验节点
                        </c:if>
                        <c:if test="${w.level==2}">
                            高级用户
                        </c:if>
                        <c:if test="${w.level==3}">
                            初级节点
                        </c:if>
                        <c:if test="${w.level==4}">
                            中级节点
                        </c:if>
                        <c:if test="${w.level==5}">
                            高级节点
                        </c:if>
                        <c:if test="${w.level==6}">
                            超级节点
                        </c:if>
                        <c:if test="${w.level==7}">
                            合伙人
                        </c:if>
                        <c:if test="${w.level==0}">
                            用户
                        </c:if>

                    </td>
                    <td >
                        ${w.account}
                    </td>
                    <td>
                        <c:if test="${w.leaderid == null}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">无邀请人</span>
                        </c:if>
                            <c:if test="${w.leaderid != null}">
                                ${w.leaderid}
                            </c:if>
                    </td>
                    <%--<td>
                        <c:if test="${w.name==null}">
                            <span class="layui-btn layui-btn-warm layui-btn-mini">
                                未实名
                            </span>
                        </c:if>
                        <c:if test="${w.name!= null}">
                            ${w.name}
                        </c:if>

                    </td>--%>
                    <td>
                        <c:if test="${w.status == 1}">
                            正常
                        </c:if>
                        <c:if test="${w.status == 2}">
                            <font color="red">锁定</font>
                        </c:if>

                    </td>
                    <td>
                            ${w.time}
                    </td>
                    <td>
                        <%--<a title="编辑" href="javascript:;" onclick="wealth_edit('编辑','${ctx}/admin/user/toEditbalance?addr='+'${w.addr}'+'&id='+${w.id},'900','380')"
                           class="ml-5" style="text-decoration:none">
                            <i class="layui-icon">查看</i>
                        </a>
                        <a title="编辑" href="javascript:;" onclick="wealth_edit('编辑','${ctx}/admin/user/toEditbalance?addr='+'${w.addr}'+'&id='+${w.id},'900','380')"
                           class="ml-5" style="text-decoration:none">
                            <i class="layui-icon">编辑</i>
                        </a>
                        <a title="编辑" href="javascript:;" onclick="wealth_edit('编辑','${ctx}/admin/user/toEditbalance?addr='+'${w.addr}'+'&id='+${w.id},'900','380')"
                           class="ml-5" style="text-decoration:none">
                            <i class="layui-icon">锁定</i>
                        </a>--%>
                            <a onclick="wealth_edit('用户等级','${ctx}/admin/user/toEditUserLevel?addr='+'${w.addr}'+'&id='+${w.id},'380','380')" style="cursor:pointer;">修改用户等级</a>

                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <input type="hidden" id="pages" value="${wallets.pages}">
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
                url:"${ctx}/admin/user/getUserList",
                type:"GET",
                data:{"pageNum":curr,"pageSize":10},
                dataType:"json",
                success:function(res){
                    if(res.msg=="ok"){
                        $("#tbody").empty();
                        var tr = "";

                        $("#pages").val(res.wallets.pages);
                        if(res.wallets.list.length<=0){
                            tr='<tr><td colspan="6" style="text-align: center;color: #ccc">暂无数据</td></tr>'
                        }else{
                            $.each(res.wallets.list,function (i, v) {
                                    if(v.name== null){
                                        realname='<span class="layui-btn layui-btn-warm layui-btn-mini">未实名</span>'
                                    }else if(v.name != null){
                                        realname=v.name
                                    }
                                    if (v.level==0){
                                        level = '用户';
                                    } else if (v.level==1){
                                        level = '体验节点';
                                    } else if (v.level==2){
                                        level = '高级用户';
                                    } else if (v.level==3){
                                        level = '初级节点';
                                    } else if (v.level==4){
                                        level = '中级节点';
                                    } else if (v.level==5){
                                        level = '高级节点';
                                    } else if (v.level==6){
                                        level = '超级节点';
                                    } else if (v.level==7){
                                        level = '合伙人';
                                    }

                                    if (v.status == 1){
                                        statuss = '正常';
                                    } else {
                                        statuss = '<font color="red">锁定</font>';
                                    }

                                    if (v.leaderid == null){
                                        leaderid = '<span class="layui-btn layui-btn-warm layui-btn-mini">无邀请人</span>';
                                    } else {
                                        leaderid = v.leaderid;
                                    }

                                    if (v.name == null){
                                        names = "";
                                    } else {
                                        names = v.name;
                                    }

                                tr+=' <tr><td><input type="checkbox" value="'+v.id+'" name="">' + ' </td>' +
                                '<td >'+v.id+'</td>' +
                                '<td >'+level+'</td>' +
                                '<td >'+v.account+'</td>' +
                                '<td>' +leaderid+'</td>' +
                                /*'<td>' +names+'</td>' +*/
                                '<td>' + statuss +'</td>' +
                                '<td>' +timeFormat(v.time)+'</td>'+
                                '<td><a onclick="showContent('+v.id+',\''+v.addr+'\')" style="cursor:pointer;">修改用户等级</a></td></tr>'

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

    // 编辑
    function wealth_edit (title,url,w,h) {
        //alert(w+":"+h);
        x_admin_show(title,url,w,h);
    }

    /**
     * 请求个人分红详情
     * @param v1
     * @param v2
     */
    function showContent(id,addr) {
        layer.open({
            type: 2,
            title:id,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 1, //不显示关闭按钮
            anim: 2,
            area:['380px','380px'],
            shadeClose: true, //开启遮罩关闭
            content:"${ctx}/admin/user/toEditUserLevel?id="+id + "&addr="+addr

        });
    }


    /**
     * 锁定
     * @param id
     */
    function locking(id){
        layer.confirm('确认锁定ID为'+id+'的用户吗？',function(){
            $.post("${ctx}/admin/user/editUserStatus",{id:id},function(res){
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


</script>

</body>
</html>
