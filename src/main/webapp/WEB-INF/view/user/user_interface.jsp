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
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<style type="text/css">
	ul li{
		font-size:20px;
		height:70px;
		text-align:center;
	}
</style>
<title>Insert title here</title>
</head>
<!--  style="background: url(/resource/images/123.jpg) ; background-size:100% 100% ; background-attachment: fixed" -->
<body>
	<!-- 导航条 -->
	<jsp:include page="../common/top.jsp"></jsp:include>
	<!-- style="background-image: url('/resource/images/1443.jpg')" -->
	<div class="container" >
		<div class="row">
			<div class="col-md-3">
				<ul class="list-group user">
				  <li class="list-group-item list-group-item-danger" data="/user/myArticle?userId=${sessionScope.SESSION_USER_KEY.id}">我的文章</li>
				  <li class="list-group-item list-group-item-warning" data="/user/publish">发布文章</li>
				  <li class="list-group-item list-group-item-warning">我的评论</li>
				  <li class="list-group-item list-group-item-warning">投票管理</li>
				  <li class="list-group-item list-group-item-warning">个人设置</li>
				  <li class="list-group-item list-group-item-warning">我的头像</li>
				</ul>
			</div>
			<div class="col-md-9" id="content">
				<div id="kindEditor" style="display: none">
				   <!-- 引入kindEditor的样式 -->
				  <jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
              </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(".user li").click(function(){
			var url = $(this).attr("data");
			$("#content").load(url); 
		})
		
		var url = $(".user li:eq(0)").attr("data");
		$("#content").load(url); 
	</script>
</body>
</html>