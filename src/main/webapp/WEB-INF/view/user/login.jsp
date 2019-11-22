<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<title>登录注册</title>
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
                <div class="panel-heading">登录信息</div>
                <div class="panel-body">
                    <form action="/user/login" method="post">
                        <!--
                             1. form-group设置了距离上下部分div的距离，更美观。
                             2. 对于form-group 设置了has-feedback可以使其有背景图
                        -->
                         <p style="color: red">${error }</p>
                        <div class="form-group has-feedback">
                            <!-- form-control 设置了边框有弧度圆角以及其中输入字体的pading以及margin,placeholder为默认的问题-->
                           
                            <input type="text" class="form-control" name="username"
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

                        <div class="form-group">
                            <div class="checkbox">
                                <!-- 此处用label包裹checkbox 的原因是为了点击记住密码也能对复选框进行选择。-->
                                <label>
                                    <input type="checkbox" name="remeber">记住密码 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="/user/register">没有账号?点击注册</a>
                                </label>
                            </div>
                        </div>
                        <!-- button的btn-block 可以占满当前珊栏而不用自适应。-->
                        <button class="btn btn-success btn-block btn-lg">登录</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>