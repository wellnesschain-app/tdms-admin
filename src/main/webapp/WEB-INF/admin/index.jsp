<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
    <title>WNCT管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
    <link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />
    <script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/admin/js/x-admin.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>
        var layer;
        var $;
        layui.use('layer',function(){
            layer=layui.layer;
            $=layui.jquery;
        })

        /*添加*/
        function update_pwd(title,url,w,h){
            x_admin_show(title,url,w,h);
        }
    </script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header header-demo">
        <div class="layui-main">
            <div class="admin-logo-box">
                <a class="logo" href="#" title="logo">WNCT</a>
                <div class="larry-side-menu" id="cndb">
                    <i class="fa fa-th-large" aria-hidden="false" style="margin-top: 8px"></i>
                </div>
            </div>
            <%--<ul class="layui-nav layui-layout-left layui-ygyd-menu" style="position:absolute; left:250px;">
                <li class="layui-nav-item"><a href="">合作机构</a></li>
                <li class="layui-nav-item"><a href="javascript:;">订单管理</a></li>
                <li class="layui-nav-item"><a href="">统计报表</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">角色管理</a></dd>
                        <dd><a href="">权限设置</a></dd>
                        <dd><a href="">日志管理</a></dd>
                    </dl>
                </li>
            </ul>--%>

            <ul class="layui-nav" lay-filter="">
                <!-- <li class="layui-nav-item">
                                  <a href="" title="消息">
                                      <i class="layui-icon" style="top: 1px;">&#xe63a;</i>
                                  </a>
                                  </li> -->
                <li class="layui-nav-item"><img src="${ctx}/static/admin/images/logo.png" class="layui-circle" style="border: 2px solid #A9B7B7;" width="35px" alt=""></li>
                <li class="layui-nav-item"> <a href="javascript:;">${loginAdmin.nickname}</a>
                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd><a href="">个人信息</a></dd>
                        <dd><a href="">切换帐号</a></dd>
                        <dd style="cursor:pointer;"><a onclick="update_pwd('修改密码','${ctx}/admin/toUpdatePwd','500','300')">修改密码</a></dd>
                        <dd><a href="${ctx}/admin/logout">退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item x-index"><a href="/">前台首页</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-side layui-bg-black x-side" style="left:-200px;">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="side">
                <%--<li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>财富计划</cite> </a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd class="">--%>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/wealth/toColdList"> <cite>冻结列表</cite> </a> </dd>--%>
                        <%--</dd>--%>
                        <%--<dd class="">--%>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/wealth/toColdedList"> <cite>释放列表</cite> </a> </dd>--%>
                        <%--</dd>--%>
                        <%--<dd class="">--%>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/wealth/toWealthList"> <cite>计划列表</cite> </a> </dd>--%>
                        <%--</dd>--%>

                    <%--</dl>--%>
                <%--</li>--%>
                <li  id='title1' class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe634;</i><cite>轮播管理</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class="">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/banner/toBannerList"> <cite>轮播列表</cite> </a> </dd>
                        </dd>
                    </dl>
                    <%--<dl class="layui-nav-child">
                        <dd class="">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/banner/toActivityList"> <cite>活动计时</cite> </a> </dd>
                        </dd>
                    </dl>--%>
                </li>
                <li id='title2'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>公告管理</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class="">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/notice/toNoticeList"> <cite>公告列表</cite> </a> </dd>
                        </dd>
                    </dl>
                </li>
                <li id='title3' class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe65e;</i><cite>流水管理</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class="">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toUSDTTransferList"> <cite>USDT流水</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toUserTransferList"> <cite>WNCT流水</cite> </a> </dd>
                        </dd>
                    </dl>
                </li>
                <%--<li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe630;</i><cite>分类管理</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="./category.html"> <cite>分类列表</cite> </a> </dd>
                    </dl>
                </li>--%>
                <li id='title4' class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe612;</i><cite>用户管理</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toUserList"> <cite>用户列表</cite> </a> </dd>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toGoldEntryList"> <cite>用户入金</cite> </a> </dd>--%>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toCoreGiveList"> <cite>送分记录</cite> </a> </dd>--%>
                        <%--<dd class=""> <a href="javascript:;" _href="./member-kiss.html"> <cite>积分管理</cite> </a> </dd>--%>
                    </dl>
                </li>
                <li id='title5'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe612;</i><cite>流水统计</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toStaticBonus"> <cite>静态流水</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toDynamicBonus"> <cite>动态流水</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toSystemTransferCountList"> <cite>系统拨款流水</cite> </a> </dd>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toBonus"> <cite>系统奖励</cite> </a> </dd>--%>

                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toGoldEntryList"> <cite>用户入金</cite> </a> </dd>--%>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toCoreGiveList"> <cite>送分记录</cite> </a> </dd>--%>
                        <%--<dd class=""> <a href="javascript:;" _href="./member-kiss.html"> <cite>积分管理</cite> </a> </dd>--%>
                    </dl>
                </li>
                    <li id='title6'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe612;</i><cite>交易记录</cite> </a>
                        <dl class="layui-nav-child">
                            <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toJFTransfer"> <cite>积分转账</cite> </a> </dd>
                            <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toWNCTTransfer"> <cite>WNCT转账</cite> </a> </dd>
                            <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toUSDTTransfer"> <cite>USDT转账</cite> </a> </dd>
                            <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toSystemTransferList"> <cite>系统拨款/扣款</cite> </a> </dd>
                        </dl>
                    </li>
                <%--<li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>系统统计</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/integral/getintegral"> <cite>积分统计</cite> </a> </dd>
                    </dl>
                </li>--%>
                <li id='title7'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>提币审核</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/audit/toSVOAuditList"> <cite>待审核</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/audit/toSVOAgreedList"> <cite>通过</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/audit/toSVORejectedList"> <cite>驳回</cite> </a> </dd>
                       <%-- <dd class=""> <a href="javascript:;" _href="${ctx}/admin/transfer/toPostalTransferList"> <cite>提出交易记录</cite> </a> </dd>--%>
                    </dl>
                </li>
                <li id='title8' class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>系统设置</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/nodeSetup/toNodeSetup"> <cite>节点设置</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/integral/toPercentage"> <cite>百分比设置</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/nodeSetup/toProcedures"> <cite>手续费设置</cite> </a> </dd>
                        <%--<dd class=""> <a href="javascript:;" _href="${ctx}/admin/nodeSetup/toNodeSetup"> <cite>手续费设置</cite> </a> </dd>--%>
                    </dl>
                </li>
                <li id='title9'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>挂单统计</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/documentary/toDocumentaryList"> <cite>挂单</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/documentary/toDocumentarySoldList"> <cite>已售</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/documentary/toCancelOrder"> <cite>撤单</cite> </a> </dd>
                       <%-- <dd class=""> <a href="javascript:;" _href="${ctx}/admin/audit/toRejectedList"> <cite>驳回</cite> </a> </dd>--%>
                    </dl>
                </li>
                <%--<li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>用户实名审核</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/audit/toUserAuditList"> <cite>待审核</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/audit/toUserAdoptList"> <cite>通过</cite> </a> </dd>
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/audit/toUserUnAdoptList"> <cite>驳回</cite> </a> </dd>
                    </dl>
                </li>--%>
                <li id='title10'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>系统转账</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toBalaceList"> <cite>转账</cite> </a> </dd>
                    </dl>
                </li>
                <li id='title11'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>等级设置</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/user/toUserLevelList"> <cite>等级设置</cite> </a> </dd>
                    </dl>
                </li>
                <li id='title12'class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>管理员设置</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/getAdmin"> <cite>管理员设置</cite> </a> </dd>
                    </dl>
                </li>
                <%--<li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe631;</i><cite>工具应用</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="${ctx}/admin/tools/toImportExcel"> <cite>导入Excel表格</cite> </a> </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"><i class="layui-icon" style="top: 3px;">&#xe611;</i><cite>客服管理</cite> </a>
                    <dl class="layui-nav-child">
                        <dd class=""> <a href="javascript:;" _href="https://uworker.yunque360.com/login"> <cite>云雀客服系统</cite> </a> </dd>
                    </dl>
                </li>--%>
                <%--<li class="layui-nav-item" style="height: 30px; text-align: center"> </li>--%>
            </ul>
        </div>
    </div>
    <div class="layui-tab layui-tab-card site-demo-title x-main" lay-filter="x-tab" lay-allowclose="true" style="left: 0px;border-left: solid 2px #2299ee;">
        <ul class="layui-tab-title">
            <li class="layui-this"> 我的桌面 <i class="layui-icon layui-unselect layui-tab-close">ဆ</i> </li>
        </ul>
        <div class="layui-tab-content site-demo site-demo-body">
            <div class="layui-tab-item layui-show">
                <iframe frameborder="0" src="${ctx}/admin/toWelcome" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
    <div class="site-mobile-shade"> </div>
</div>
<script>

        $(document).ready(function(){
            $("#cndb").click();


        });
        var username = '${username}';
        $(function(){
            $.get("${ctx}/admin/getRole",{username:username},function(res){
                if(res.msg=='ok'){
                    for (var a=1;a<13;a++){
                        var titleName="title"+a;
                        $("#"+titleName)[0].style="display:none"
                    }
                   var role = res.role.role;
                   var roles=role.split(",");
                   for (var a=0;a<roles.length;a++){
                       var titleName="title"+roles[a];
                       $("#"+titleName)[0].style="display:block"
                   }
                }else{
                }
            })
        })


</script>
</body>
</html>
