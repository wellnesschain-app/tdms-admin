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
    <link rel="stylesheet" href="${ctx}/static/admin/css/x-admin.css" media="all">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane">
        <center>
        <div class="layui-form-item">
            <input type="hidden" name="id" value="${balance.id}">
            <%--<label for="L_title" class="layui-form-label">级别</label>
            <div class="layui-input-block">
                <input type="text" id="L_title" name="level" value="${node.level}" required lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>--%>
        </div>
        <%--<div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户等级</label>
                <div class="layui-input-block">
                    &lt;%&ndash;<input class="layui-input" name="balance" value="${balance.balance}" type="text">&ndash;%&gt;
                        <select id="aa" value="${user.level}">
                            <option value ="111">请选择</option>
                            <option value ="0">用户</option>
                            <option value ="1">普通用户</option>
                            <option value="2">高级用户</option>
                            <option value="3">初级节点</option>
                            &lt;%&ndash;<option value="4">中级节点</option>
                            <option value="5">高级节点</option>
                            <option value="6">超级节点</option>
                            <option value="7">合伙人</option>&ndash;%&gt;
                        </select>
                </div>
            </div>
        </div>--%>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">积分余额</label>
                <div class="layui-input-block">
                    <input class="layui-input"  value="${balance.balance}" type="text" readonly="readonly">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">积分转账</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="balance" type="text">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">WNCT余额</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="" value="${balance.useBalance}" type="text" readonly="readonly">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">WNCT转账</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="useBalance" type="text">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">USDT余额</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="" value="${balance.ustdBalance}" type="text" readonly="readonly">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">USDT转账</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="ustdBalance"  type="text">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input class="layui-input" name="remarks"  type="text">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="add" lay-submit>
                确认转账
            </button>
        </div>
        </center>
    </form>
</div>
<script src="${ctx}/static/admin/lib/layui/layui.js" charset="utf-8">
</script>
<script src="${ctx}/static/admin/js/x-layui.js" charset="utf-8">
</script>
<script>
    layui.use(['form','layer','layedit'], function(){
        $ = layui.jquery;
        var form = layui.form()
            ,layer = layui.layer
            ,layedit = layui.layedit;


        layedit.set({
            uploadImage: {
                url: "${ctx}/admin/notice/upload" //接口url
                ,type: 'post' //默认post
            }
        })

        //创建一个编辑器
        editIndex = layedit.build('L_content');



        //监听提交
        form.on('submit(add)', function(data){
            //console.log(data);
            var addr = '${addr}';
            var level2 = $('#aa').val();
            var level = null;
            if (level2=="111"){
                level=${user.level};
            }else {
                level = $('#aa').val();
            }
            var username = '${username}';
            var id=data.field.id;
            var balance=data.field.balance;
            var useBalance=data.field.useBalance;
            var ustdBalance=data.field.ustdBalance;
            var remarks=data.field.remarks;
            //layer.alert(name+":"+rate+":"+day+":"+crowDay+":"+serviceMoney+":"+crowDay+":"+explain);
            //发异步，把数据提交给php
            $.get("${ctx}/admin/user/EditbalanceReduce",{id:id,balance:balance,useBalance:useBalance,ustdBalance:ustdBalance,level:level,addr:addr,username:username,remarks:remarks},function(res){
                if(res.msg=='ok'){
                    layer.alert("转出成功", {icon: 6},function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                        location.reload();

                    });
                }else{
                    layer.alert(res.msg,{icon:"5"});
                }
            })
            return false;
        });


    });
</script>

</body>

</html>
