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
	<div class="container" align="center">
		<h2 style="color: green">
			${article.title }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:collect(${article.id },${(collect == null || collect.deleted == 0) ? 1 : 0 })">
	          <span class="glyphicon glyphicon-star-empty" style="font-size: 18px;">${(collect == null || collect.deleted == 0) ? '收藏' : '取消收藏' }</span>
	        </a>
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
	</div>
	<div align="center">
		<textarea rows="8" cols="100%" placeholder="请输入评论" id="comment"></textarea><br>
		<c:if test="${sessionScope.SESSION_USER_KEY.id != null }">
			<button onclick="publish('${article.id }','${sessionScope.SESSION_USER_KEY.id}')">发表评论</button>
		</c:if>
		<c:if test="${sessionScope.SESSION_USER_KEY.id == null }">
			<button onclick="login()">登入并发表文章</button>
		</c:if>
	</div>
	<div align="center">
			<!-- 	显示文章的评论 -->
			 <h3 style="color: red;margin-right: 500px;">热门评论</h3></li>
			<hr style="width: 850px;">
			<c:forEach items="${info.list }" var="comment">
				<div style="margin-right: 700px;">
					<img src="" onerror="this.src='/resource/images/user.png'" width="50px" height="50px">&nbsp;&nbsp;&nbsp;&nbsp;
						<font style="color: red">${comment.user.username }</font>
					<p style="margin-left: 130px;">${comment.content }</p>
					<div style="margin-left: 150px;">
							<fmt:formatDate value="${comment.created }" pattern="yyyy年MM月dd HH:mm:ss"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#">
				          <font class="glyphicon glyphicon-flag">举报</font>
				        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        <a href="#">
				        	回复
				        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				         <a href="javascript:commentLike(${comment.id })">
				          <font class="glyphicon glyphicon-thumbs-up">点赞&nbsp;${comment.commentLike }</font>
				        </a>
					</div>
				</div>
				<hr style="width: 850px;">
			</c:forEach>
			<button type="button" class="btn btn-primary btn-lg" onclick="location='/article/lookDetail?id=${article.id }&pageSize=${info.pageSize == info.total ? info.pageSize : (info.pageSize = info.pageSize + 3) }'">更多评论</button>
		</div>
		<!-- 调用模态框和登入页面jsp  -->
		<jsp:include page="../common/modal.jsp"></jsp:include>
		
		<script type="text/javascript">
		//获取当前系统时间
			$(function(){
				/* var year=date.getFullYear(); //获取当前年份   
				var month=date.getMonth()+1; //获取当前月份   
				var dat=date.getDate(); //获取当前日   
				var day=date.getDay(); //获取当前星期几   
				var hour=date.getHours(); //获取小时   
				var minutes=date.getMinutes(); //获取分钟   
				var second=date.getSeconds(); //获取秒    */
				var date = new Date();
				var year = date.getFullYear();
				var month = date.getMonth();
				var day = date.getDate();
				var time = year+"-"+(month+1)+"-"+day;
				/* alert(time) */
			})
			
			//发表评论
			function publish(articleid,userid){
				var content = $("#comment").val();
				var articleId = parseInt(articleid);
				var userId = parseInt(userid);
				$.post(
					"<%=request.getContextPath()%>/comment/publish",
					{articleId:articleId,content:content,userId:userId},
					function(data){
						if(data.result == 1){
							alert(data.errorMsg);
							location="<%=request.getContextPath()%>/article/lookDetail?id=${article.id}";
						}else{
							alert(data.errorMsg);
						}
					},
					"json"
				);
			}
			
			
			function login(){
				$("#myModal").modal('show');
				$("#registerModal").modal('hide');
				
			}
			
			function register(){
				$("#myModal").modal('hide');
				
			}
			$('#myModal').on('hidden.bs.modal', function () {
				$("#registerModal").modal('show');
			})
			
			$('#registerModal').on('hidden.bs.modal', function () {
				$("#myModal").modal('show');
			})
			
			function load(){
				location.reload(true);
			}
			
			function commentLike(id){
				$.post(
					"<%=request.getContextPath()%>/comment/commentLike",
					{id:id},
					function(data){
						if(data.result == 1){
							location.reload();							
						}else{
							alert("点赞失败");
						}
					},
					"json"
				);
			}
			
			function collect(articleId,deleted){
				$.post(
					"<%=request.getContextPath()%>/comment/collect",
					{articleId:articleId,deleted:deleted},
					function(obj){
						if(obj.result == 1){
							alert(obj.errorMsg);
							location.reload(true);
						}else{
							alert(obj.errorMsg);
						}
					},
					"json"
				);
			}
		</script>
</body>
</html>