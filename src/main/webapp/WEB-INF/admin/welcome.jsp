<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>
        天达健康管理系统
    </title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/admin/lib/bootstrap/css/bootstrap.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/admin/lib/bootstrap/js/bootstrap.min.js" media="all">
</head>
<body>
<style type="text/css">
    legend { display: block; width:100px; border-bottom:0px; font-family: "Microsoft YaHei","Helvetica Neue";}
    legend a{ color:#666;} legend a:hover{ text-decoration:none;}
    .layui-table{ font-family: "Microsoft YaHei","Helvetica Neue";}
</style>
<div class="x-body">
    <blockquote class="layui-elem-quote">
        欢迎使用天达健康后台管理系统！
    </blockquote>
    <div class="row">
        <div class="col-xs-6 col-sm-4 col-md-3">
            <section class="panel">
                <div class="symbol bgcolor-blue"> <i class="fa fa-address-card"></i>
                </div>
                <div class="value tab-menu" bind="1">
                    <a data-url="user-info.html" data-parent="true" data-title="今日注册用户"><i class="iconfont " data-icon=""></i>
                        <h1 id="register"></h1>
                    </a>

                    <a href="${ctx}/admin/user/toUserList" href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日注册用户"> <i class="iconfont " data-icon=""></i><span>今日注册用户</span></a>

                </div>
            </section>
        </div>
        <div class="col-xs-6 col-sm-4 col-md-3">
            <section class="panel">
                <div class="symbol bgcolor-blue"> <i class="fa fa-user-circle"></i>
                </div>
                <div class="value tab-menu" bind="1">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="总人数"><i class="iconfont " data-icon=""></i>
                        <h1 id="totalnumber"></h1>
                    </a>

                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="总人数"> <i class="iconfont " data-icon=""></i><span>总人数</span></a>

                </div>
            </section>
        </div>
        <div class="col-xs-6 col-sm-4 col-md-3">
            <section class="panel">
                <div class="symbol bgcolor-commred"> <i class="fa fa-id-card-o "></i>
                </div>
                <div class="value tab-menu" bind="1">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日充值用户"> <i class="iconfont " data-icon=""></i>
                        <h1 id="prepaidusers">0</h1>
                    </a>

                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日充值用户"> <i class="iconfont " data-icon=""></i><span>今日充值用户</span></a>

                </div>
            </section>
        </div>

        <div class="col-xs-6 col-sm-4 col-md-3">
            <section class="panel">
                <div class="symbol bgcolor-dark-green"> <i class="fa fa-gg-circle" aria-hidden="true"></i>
                </div>
                <div class="value tab-menu" bind="1">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="历史下单数"> <i class="iconfont " data-icon=""></i>
                        <h1 id="placeOrder">0</h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="历史下单数"> <i class="iconfont " data-icon=""></i><span>历史下单数</span></a>
                </div>
            </section>
        </div>

        <div class="col-xs-6 col-sm-4 col-md-3">
            <section class="panel">
                <div class="symbol bgcolor-yellow-green"> <i class="fa fa-gbp" aria-hidden="true"></i>
                </div>
                <div class="value tab-menu" bind="1">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日下单用户"> <i class="iconfont " data-icon=""></i>
                        <h1 id="placeOrderUser">0</h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日下单用户"> <i class="iconfont " data-icon=""></i><span>今日下单用户</span></a>
                </div>
            </section>
        </div>

        <%--<div class="col-xs-6 col-sm-4 col-md-3">
            <section class="panel">
                <div class="symbol bgcolor-yellow-green"> <i class="fa fa-gbp" aria-hidden="true"></i>
                </div>
                <div class="value tab-menu" bind="1">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日释放"> <i class="iconfont " data-icon=""></i>
                        <h1 id="placeOrderUser1"></h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日释放"> <i class="iconfont " data-icon=""></i><span>今日释放</span></a>
                </div>
            </section>
        </div>--%>

        <%--<div class="col-xs-6 col-sm-4 col-md-3">
            <section class="panel">
                <div class="symbol bgcolor-yellow-green"> <i class="fa fa-gbp" aria-hidden="true"></i>
                </div>
                <div class="value tab-menu" bind="1">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="动态收益"> <i class="iconfont " data-icon=""></i>
                        <h1 id="dynamic"></h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="动态收益"> <i class="iconfont " data-icon=""></i><span>动态收益</span></a>
                </div>
            </section>
        </div>--%>


    </div>

    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend><a name="default">信息统计</a></legend>
    </fieldset>

    <!--相关统计-->
    <div class="row">
        <%--<div class="col-sm-6">
            <section class="panel">
                <div class="panel-heading">区域统计</div>
                <div class="panel-body">
                    <div class="echarts" id="area" style="height:300px; height:350px"></div>
                </div>
            </section>
        </div>
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-heading">扫码统计</div>
                <div class="panel-body">
                    <div class="echarts" id="years" style="height:300px; height:350px"></div>
                </div>
            </section>
        </div>--%>
        <div class="col-sm-12">
            <section class="panel">
                <div class="panel-heading">月度统计</div>
                <div class="panel-body">
                    <div class="echarts" id="main" style="height:300px; height:350px"></div>
                </div>
            </section>
        </div>
        <%--<div class="col-sm-6">
            <section class="panel">
                <div class="panel-heading">产品统计</div>
                <div class="panel-body">
                    <div class="echarts" id="product" style="height:300px; height:350px"></div>
                </div>
            </section>
        </div>--%>
    </div>

    <script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/admin/lib/js/index.js"></script>
    <script src="${ctx}/static/admin/js/echarts.min.js"></script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        function getBeforeDate(n){//n为你要传入的参数，当前为0，前一天为-1，后一天为1
            var date = new Date() ;
            var year,month,day ;
            date.setDate(date.getDate()+n);
            year = date.getFullYear();
            month = date.getMonth()+1;
            day = date.getDate() ;
            s = year + '-' + ( month < 10 ? ( '0' + month ) : month ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
            return s ;
        }

        var time1 = getBeforeDate(0);
        var time2 = getBeforeDate(-1);
        var time3 = getBeforeDate(-2);
        var time4 = getBeforeDate(-3);
        var time5 = getBeforeDate(-4);
        var time6 = getBeforeDate(-5);
        var time7 = getBeforeDate(-6);
        var time8 = getBeforeDate(-7);
        var time9 = getBeforeDate(-8);
        var time10 = getBeforeDate(-9);
        var time11 = getBeforeDate(-10);
        var time12 = getBeforeDate(-11);
        var time13 = getBeforeDate(-12);
        var time14 = getBeforeDate(-13);
        var time15 = getBeforeDate(-14);
        var time16 = getBeforeDate(-15);
        var time17 = getBeforeDate(-16);
        var time18 = getBeforeDate(-17);
        var time19 = getBeforeDate(-18);
        var time20 = getBeforeDate(-19);
        var time21 = getBeforeDate(-20);
        var time22 = getBeforeDate(-21);
        var time23 = getBeforeDate(-22);
        var time24 = getBeforeDate(-23);
        var time25 = getBeforeDate(-24);
        var time26 = getBeforeDate(-25);
        var time27 = getBeforeDate(-26);
        var time28 = getBeforeDate(-27);
        var time29 = getBeforeDate(-28);
        var time30 = getBeforeDate(-29);
        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['注册人数'/*,'入金数'*/],
                selected:{
                    '入金数':false
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: [time30,time29,time28, time27,time26,time25,time24, time23,time22, time21, time20, time19,time18,time17,time16, time15,time14, time13, time12, time11, time10,time9,time8,time7, time6,time5, time4, time3, time2, time1]
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:'注册人数',
                    type:'line',

                    data:[${registerednumber.day30},${registerednumber.day29},${registerednumber.day28},${registerednumber.day27},${registerednumber.day26},${registerednumber.day25},${registerednumber.day24},${registerednumber.day23},${registerednumber.day22},${registerednumber.day21},${registerednumber.day20},${registerednumber.day19},${registerednumber.day18},${registerednumber.day17},${registerednumber.day16},${registerednumber.day15},${registerednumber.day14},${registerednumber.day13}, ${registerednumber.day12}, ${registerednumber.day11}, ${registerednumber.day10}, ${registerednumber.day9}, ${registerednumber.day8}, ${registerednumber.day7},${registerednumber.day6}, ${registerednumber.day5}, ${registerednumber.day4}, ${registerednumber.day3}, ${registerednumber.day2}, ${registerednumber.day1}],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }
                /*,

                {
                    name:'入金数',
                    type:'line',
                    stack: '总量',
                    data:["111","222","201","55","66"],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }*/


                /*{
                    name:'房贷',
                    type:'line',
                    stack: '总量',
                    data:[220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name:'信用贷',
                    type:'line',
                    stack: '总量',
                    data:[150, 232, 201, 154, 190, 330, 410]
                }*/
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">




        // 指定图表的配置项和数据
        var myChart = echarts.init(document.getElementById('area'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '区域统计',
                subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['北京', '天津','河北','山西']
            },
            series: [{
                name: '省份',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    { value: 200, name: '北京' },
                    { value: 100, name: '天津' },
                    { value: 522, name: '河北' },
                    { value: 600, name: '山西' }



                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('years'));

        function getBeforeDate(n){//n为你要传入的参数，当前为0，前一天为-1，后一天为1
            var date = new Date() ;
            var year,month,day ;
            date.setDate(date.getDate()+n);
            year = date.getFullYear();
            month = date.getMonth()+1;
            day = date.getDate() ;
            s = year + '-' + ( month < 10 ? ( '0' + month ) : month ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
            return s ;

        }

        var time1 = getBeforeDate(0);
        var time2 = getBeforeDate(-1);
        var time3 = getBeforeDate(-2);
        var time4 = getBeforeDate(-3);
        var time5 = getBeforeDate(-4);
        var time6 = getBeforeDate(-5);
        var time7 = getBeforeDate(-6);

        /*$(function(){
            $.get("/admin/getCodeNumber",function(res){
                if(res.msg=='ok'){
                    alert(res.codeNumber.day1);

                }else{
                    alert(res.msg);
                }
            })
        })*/

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '扫码人数统计',
                subtext: '纯属虚构',
                x: 'center'
            },
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: [time7, time6,time5, time4, time3, time2, time1],
                axisTick: {
                    alignWithLabel: true
                }
            }],
            yAxis: [{
                type: 'value'
            }],
            series: [{
                name: '扫码人数',
                type: 'bar',
                barWidth: '60%',
                data: ["100","222","222","222","222","222","222"],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('product'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '产品订单比例',
                subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['车贷', '房贷']
            },
            series: [{
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    { value: 1335, name: '车贷' },
                    { value: 310, name: '房贷' }
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

</div>
<div class="layui-footer footer footer-demo">
    <div class="layui-main">
        <%--<p>--%>
            <%--<a href="/">--%>
                <%--Copyright ©2018 www.bmsjf.net 巴马凌浩.--%>
            <%--</a>--%>
        <%--</p>--%>

    </div>
</div>
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.min.js"></script>
<script>

    $(function(){
        $.get("${ctx}/admin/getTotalNumber",function(res){
            if(res.msg=='ok'){
                $("#totalnumber").text(res.totalNumber.totalNumber+"  人");
                $("#register").text(res.totalNumber.register+"  人");

            }else{
                alert(res.msg);
            }
        })
    })

    /*$(function(){
        $.get("${ctx}/admin/getPrepaidUsers",function(res){
            if(res.msg=='ok'){
                $("#prepaidusers").text(res.prepaidUsers+"  人");
            }else{
                alert(res.msg);
            }
        })
    })*/

    $(function(){
        $.get("${ctx}/admin/getPlaceOrder",function(res){
            if(res.msg=='ok'){
                $("#placeOrder").text(res.PlaceOrder+"  单");
            }else{
                alert(res.msg);
            }
        })
    })

    /*$(function(){
        $.get("${ctx}/admin/getPlaceOrderUser",function(res){
            if(res.msg=='ok'){
                $("#placeOrderUser").text(res.PlaceOrderUser+"  人");
            }else{
                alert(res.msg);
            }
        })
    })

    $(function(){
        $.get("${ctx}/admin/getRelease",function(res){
            if(res.msg=='ok'){
                $("#placeOrderUser1").text(res.Release);
            }else{
                alert(res.msg);
            }
        })
    })

    $(function(){
        $.get("${ctx}/admin/getOrder",function(res){
            if(res.msg=='ok'){
                $("#placeOrder").text(res.Order);
            }else{
                alert(res.msg);
            }
        })
    })

    $(function(){
        $.get("${ctx}/admin/getDynamic",function(res){
            if(res.msg=='ok'){
                $("#dynamic").text(res.Dynamic);
            }else{
                alert(res.msg);
            }
        })
    })*/


</script>
</body>
</html>
