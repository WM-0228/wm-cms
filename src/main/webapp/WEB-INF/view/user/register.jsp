<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<title>用户注册</title>
</head>
<body>
<div class="container">
    <!-- row是珊栏系统的包裹容器-->
    <div class="row">
        <!-- col-md-x 表示此珊栏占据几个格子，offset-x 是偏移x格子-->
        <div class="col-md-4 col-md-offset-4">
            <!-- panel分为3个部分，heading,body,footer其中heading部分的颜色来自于panel-default的颜色-->
            <div class="panel panel-default">
                <!-- panel-headeing是有颜色的，此颜色来自于panel-default-->
                <div class="panel-heading">用户注册</div>
                <div class="panel-body">
                    <form action="/user/register" method="post" id="regForm">
                        <!--
                             1. form-group设置了距离上下部分div的距离，更美观。
                             2. 对于form-group 设置了has-feedback可以使其有背景图
                        -->
                        <div class="form-group has-feedback">
                            <!-- form-control 设置了边框有弧度圆角以及其中输入字体的pading以及margin,placeholder为默认的问题-->
                            <input type="text" class="form-control" name="username"
                            	   maxlength="8" minlength="2" remote="/user/checkName"
                                   placeholder="用户名">
                            <!-- 此项如果要显示必须在form-group的class上添加has-feedback且还要添加一个span-->
                            <!-- glyphicon是添加图片，form-control-feedback是指示这个是一个样式，同时设置其在右边-->
                            <span class="glyphicon  glyphicon-user form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control" name="password"
                                   placeholder="密码">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <!-- button的btn-block 可以占满当前珊栏而不用自适应。-->
                        <a href="/user/login">已有账号?点击登入</a>
                        <button class="btn btn-success btn-block btn-lg">注冊</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
	$("#regForm").validate();
	
</script>
</body>
</html>