<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container" align="center">
		<h2 style="color: green">
			${article.title }
		</h2>
		<h4 style="margin-left: 80px;color: pink">
			作者:${article.user.username}<br>
		</h4>
		<h5 style="margin-left: 130px;color: red">
			发布时间:${article.created}
			&nbsp;&nbsp;&nbsp;&nbsp; 频道:${article.channel.name}
			&nbsp;&nbsp;&nbsp;&nbsp; 分类:${article.category.name}
		</h5>
		
		<div>
			${article.content }
		</div>
		
		<div>
			<nav aria-label="...">
			  <ul class="pager">
			    <li><a href="/chapter?value=pre&aid=${article.id}">上一篇</a></li>
			    <li><a href="/chapter?value=next&aid=${article.id}">下一篇</a></li>
			  </ul>
			</nav>
		</div>
		<div>
			<!-- 	显示文章的评论 -->
		</div>
	</div>
</body>
</html>