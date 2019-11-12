<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap声明 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<!-- js声明 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/css/sb-admin.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<title>CMS管理平台</title>


</head>
<body id="page-top">
	<body id="page-top">
	<!-- 后台管理系统顶部 -->
	<jsp:include page="top.jsp" />
	<!--  <hr style="color: white;height: 0.5px" /> -->
	<!--   <hr style="height:3px;border:none;border-top:3px double red;" /> -->
	<div id="wrapper">
		<!-- 后台管理系统左部菜单 -->
		<jsp:include page="left.jsp" />
		<!-- 中间内容显示区域 -->
		<div id="content-wrapper">

			<div class="container-fluid">
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">后台首页</a></li>
					<li class="breadcrumb-item active">概览</li>
				</ol>

				<!-- Icon Cards-->
				<br /> <br />
				<h1 align="center">欢迎光临后台管理系统</h1>
				<br /> <br />
				<div class="row">
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-primary o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-comments"></i>
								</div>
								<div class="mr-5">26 篇文章!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>

					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-warning o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-list"></i>
								</div>
								<div class="mr-5">11 新用户!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>


					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-success o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-shopping-cart"></i>
								</div>
								<div class="mr-5">6 个频道!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-danger o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-life-ring"></i>
								</div>
								<div class="mr-5">20 个分类!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
				</div>

			</div>

			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright Â© Your Website 2019</span>
					</div>
				</div>
			</footer>

		</div>
		<!-- /.content-wrapper -->

	</div>

<script type="text/javascript"
		src="/resource/js/jquery-3.2.1.js"></script>

	<script type="text/javascript">
	$(function(){
		$(".nav-link").click(function(){
			var url =$(this).attr("data");
			//alert(url)
			$("#content-wrapper").load(url);
			
		})
	})
	
	
	</script>
</body>
</html>