<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
 <style type="text/css">
	.menu li{
		font-size:20px;
		text-align:center;
	}
	.menu li:hover{
		background:pink;
	}
	
	
</style>
 <title>有花堪折直须折</title>
</head>
<!-- style="background: url(/resource/images/index.jpg) ; background-size:100% 100% ; background-attachment: fixed" -->
<body>
<!-- 导航条 -->
	<jsp:include page="common/top.jsp"></jsp:include>

<div class="container-fluid" id="content">
	<div class="container" style="minheight:200px" >
		<div class="row">
			<!-- 左侧菜单 -->
			<div class="col-md-2" style="minheight:200px;margin-top:20px" >
				
					<ul class="list-group menu">
					    <li class="list-group-item active">热门文章</li>
					    <c:forEach items="${channels }" var="channel" varStatus="index">
					    	<li class="list-group-item" data="/channel?channelId=${channel.id }">${channel.name}</li>
					    </c:forEach>
					</ul>
			</div>
			
			<!-- 中间的内容 -->
			<div class="col-md-8" style="background:white;minheight:200px">
				<div>
						<hr>
						
				</div>
				
				<div id="myCarousel" class="carousel slide" style="minheight:200px" >
						<!-- 轮播（Carousel）指标 -->
						<ol class="carousel-indicators">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<li data-target="#myCarousel" data-slide-to="1"></li>
							<li data-target="#myCarousel" data-slide-to="2"></li>
						</ol>   
						<!-- 轮播（Carousel）项目 -->
						<div class="carousel-inner">
							<div class="item active">
								<img src="/resource/images/z1.jpg" alt="First slide">
							</div>
							<div class="item">
								<img src="/resource/images/z2.jpg" alt="Second slide">
							</div>
							<div class="item">
								<img src="/resource/images/z3.jpg" alt="Third slide">
							</div>
						</div>
						<!-- 轮播（Carousel）导航 -->
						<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
					<!-- 放文章的列表 -->
					<div class="container" >
						<c:forEach items="${info.list }" var="article">
							<div class="row">
								<hr style="width: 700px;margin-left: 200px;color: black">
								<div class="col-md-2"><img  height="80px" width="80px"  src="/pic/${article.picture}" onerror="this.src='/resource/images/z2.jpg'"></div>
								<div class="col-md-10">
									<a href="javascript:articleLook(${article.id })">${article.title }</a>
									<br>
									频道:<a>${article.channel.name }</a>&nbsp;&nbsp;&nbsp;&nbsp;
									分类:<a>${article.category.name }</a>
									<br>
									<br>
									<div style="margin-left: 200px;"><p style="color: pink">${article.user.username} 发布于  <fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd"/></p> </div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div align="center">
						<ul class="pagination">
						    <li><a href="/index?pageNum=${info.prePage}">&laquo;</a></li>
						    <c:forEach begin="${info.pageNum-2 > 1 ? info.pageNum-2:1}" end="${info.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">    		
						    	<c:if test="${info.pageNum!=index.index}">
						    		<li><a href="/index?pageNum=${index.index}">${index.index}</a></li>
						    	</c:if>
						    	<c:if test="${info.pageNum==index.index}">
						    		<li><a href="/index?pageNum=${index.index}"><strong> ${index.index} </strong> </a></li>
						    	</c:if>
						    	
						    </c:forEach>
						    <li><a href="/index?pageNum=${info.nextPage}">&raquo;</a></li>
						</ul>
					</div>
			</div>
			<!-- 中间的内容结束 -->
			
			
			<div class="col-md-2" style="minheight:200px" >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">面板标题</h3>
					</div>
					<div class="panel-body">
						这是一个基本的面板
					</div>
				</div>
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">图片文章</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${images.list }" var="article" varStatus="index">
							${index.count } . <a href="javascript:articleLook(${article.id })">${article.title }</a><br>
						</c:forEach>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">最新文章</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${articleList }" var="article" varStatus="index">
							${index.count } . <a>${article.title }</a><br>
						</c:forEach>
					</div>
				</div>
			
			</div>
		</div>
		
	</div>

</div>



<!-- 底部 -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
  
  </div>
</nav>



	<script type="text/javascript">
		function articleLook(articleId){
			window.open("/article/lookDetail?id="+articleId);
		}
		
		$(function(){
			$(".menu li").click(function(){
				$("#content").load($(this).attr("data"));
			})
		})
	</script>
</body>
</html>