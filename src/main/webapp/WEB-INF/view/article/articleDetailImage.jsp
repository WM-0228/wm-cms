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
<title>Insert title here</title>
</head>
<body>
	
	<div id="myCarousel" class="carousel slide" style="minheight:200px" >
		<h3>${article.title }</h3>
		<!-- 轮播（Carousel）指标 -->
		<ol class="carousel-indicators">
			<c:forEach items="${article.imageList }" var="image" varStatus="index">
				<li data-target="#myCarousel" data-slide-to="0" class="${index.index == 0 ? 'active' : null }"></li>
			</c:forEach>
		</ol>   
		<!-- 轮播（Carousel）项目 -->
		<div class="carousel-inner">
			<c:forEach items="${article.imageList }" var="image" varStatus="index">
				<div class="item ${index.index == 0 ? 'active' : null }">
					<img src="/pic/${image.url }" alt="First slide">
				</div>
			</c:forEach>
			
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
</body>
</html>