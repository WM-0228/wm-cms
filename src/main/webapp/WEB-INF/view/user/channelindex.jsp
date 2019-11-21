<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="container-fluid" >
	<div class="container" style="minheight:200px" >
		<div class="row">
			<!-- 左侧菜单 -->
			<div class="col-md-2" style="minheight:200px;margin-top:20px" >
				
					<ul class="list-group menu">
					    <li class="list-group-item" onclick="location='/'">热门文章</li>
					    <c:forEach items="${channels }" var="channel" varStatus="index">
					    <!-- data="/indexchn?id=${channel.id}" -->
					    	<li class="list-group-item ${channelId==channel.id ? 'active' : ''}"  data="/channel?channelId=${channel.id }">${channel.name}</li>
					    </c:forEach>
					</ul>
			</div>
			
			<!-- 中间的内容 -->
			<div class="col-md-8" style="background:white;minheight:200px" >
				<div>
					<ul class="nav nav-tabs">
						<li class="${categoryId == 0 ? 'active' : '' }"><a href="javascript:category('/channel/?channelId=${channelId }')">${info.list[0].channel.name }</a></li>
						<c:forEach items="${categorys }" var="category">
							<li class="${categoryId == category.id ? 'active' : '' }"><a href="javascript:category('/channel/?categoryId=${category.id }&channelId=${channelId }')">${category.name }</a></li>
						</c:forEach>
					</ul>
				</div>
				
					<!-- 放文章的列表 -->
					<div class="container">
						<c:forEach items="${info.list }" var="article">
							<div class="row">
								<hr>
								<div class="col-md-2"><img  height="80px" width="80px"  src="/pic/${article.picture}"></div>
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
						    <li><a href="javascript:category('/channel/?categoryId=${categoryId }&channelId=${channelId }&pageNum=${info.prePage}')">&laquo;</a></li>
						    <c:forEach begin="${info.pageNum-2 > 1 ? info.pageNum-2:1}" end="${info.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">    		
						    	<c:if test="${info.pageNum!=index.index}">
						    		<li><a href="javascript:category('/channel/?categoryId=${categoryId }&channelId=${channelId }&pageNum=${index.index}')">${index.index}</a></li>
						    	</c:if>
						    	<c:if test="${info.pageNum==index.index}">
						    		<li><a href="javascript:category('/channel/?categoryId=${categoryId }&channelId=${channelId }&pageNum=${index.index}')"><strong> ${index.index} </strong> </a></li>
						    	</c:if>
						    	
						    </c:forEach>
						    <li><a href="javascript:category('/channel/?categoryId=${categoryId }&channelId=${channelId }&pageNum=${info.nextPage}')">&raquo;</a></li>
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
						<h3 class="panel-title">面板标题</h3>
					</div>
					<div class="panel-body">
						这是一个基本的面板
					</div>
					<div class="panel-body">
						这是一个基本的面板
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">面板标题</h3>
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





	<script type="text/javascript">
		function articleLook(articleId){
			window.open("/article/lookDetail?id="+articleId);
		}
		
		$(function(){
			$(".menu li").click(function(){
				$("#content").load($(this).attr("data"));
			})
		})
		
		function category(url){
			$("#content").load(url);
		}
		
		function indexLoad(url){
			location.load(url);
		}
	</script>
