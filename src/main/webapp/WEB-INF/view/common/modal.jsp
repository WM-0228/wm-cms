<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" onclick="load()">×</button>
		            </div>
		            <div class="modal-body" align="center">
						
						
						 <div class="panel panel-default" style="width: 350px;">
						                <!-- panel-headeing是有颜色的，此颜色来自于panel-default-->
						                <div class="panel-heading">用户登入</div>
						                <div class="panel-body">
						                    <form action="/comment/login" method="post">
						                    
						                    	<input type="hidden" name="articleId" value="${article.id }">
						                        <!--
						                             1. form-group设置了距离上下部分div的距离，更美观。
						                             2. 对于form-group 设置了has-feedback可以使其有背景图
						                        -->
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
						                                </label>
						                                <a href="javascript:register()">没有账号?点击注册</a>
						                            </div>
						                        </div>
						                        <!-- button的btn-block 可以占满当前珊栏而不用自适应。-->
						                        <button class="btn btn-success btn-block btn-lg">登录</button>
						                    </form>
						                </div>
						            </div>
						
						
						
					</div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div>
		
		
		
		<!-- 注册账号 -->
		<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		            </div>
		            <div class="modal-body" align="center">
						
						
						 <div class="panel panel-default" style="width: 350px;">
                <!-- panel-headeing是有颜色的，此颜色来自于panel-default-->
                <div class="panel-heading">用户注册</div>
                <div class="panel-body">
                    <form action="/comment/register" method="post" id="regForm">
                    	<input type="hidden" name="articleId" value="${article.id }">
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
                        <a href="javascript:login()">已有账号?点击登入</a>
                        <button class="btn btn-success btn-block btn-lg">注冊</button>
                    </form>
                </div>
            </div>
						
						
						
					</div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div>