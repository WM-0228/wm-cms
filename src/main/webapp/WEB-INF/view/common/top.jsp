<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar navbar-default">
  <div class="container-fluid">
  	
  	<!-- logo -->
  	<div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img alt="Brand" src="/resource/images/logo.png">
      </a>
    </div>
    
    <!-- 搜索框和右侧登录信息 -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        
      </ul>
      
      <form class="navbar-form navbar-left" action="index" method="post">
        <div class="form-group">
          <input type="text" name="key" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><img width="40px" height="40px" src="/resource/images/donghua.gif"/> </a></li>
       <c:choose>
       		<c:when test="${sessionScope.SESSION_USER_KEY == null }">
       			<li><a href="/user/login">登录</a></li>
       			<li><a href="/user/register">注册</a></li>
       		</c:when>
       		<c:otherwise>
	       		 <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          		${sessionScope.SESSION_USER_KEY.username } <span class="caret"></span></a>
		          <ul class="dropdown-menu ">
		            <li><a href="/user/user_interface">个人中心</a></li>
		            <li><a href="/">首页面</a></li>
		            <li><a href="#">个人设置</a></li>
		            <li><a href="#">修改头像</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="/user/exit?flag=index">退出登录</a></li>
		          </ul>
		        </li>
       		</c:otherwise>
       </c:choose>
      </ul>
    </div><!-- /.navbar-collapse -->
    
  </div>
</nav>