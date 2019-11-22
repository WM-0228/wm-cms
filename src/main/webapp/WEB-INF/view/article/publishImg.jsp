<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章发布</title>

</head>
<body>
 
				  
	<form action="" id="form">
		<div class="form-group row ">
			<label for="title">文章标题</label> <input type="text"
				class="form-control" id="title" name="title" placeholder="请输入标题">
		</div>
		<div class="form-group row " id="img">
			<label for="title">选择图片和添加标签</label> <input type="file"
				class="form-control" id="file" name="file">
				<input type="text"
				class="form-control" id="desc" name="desc">
		</div>
		<div class="form-group row ">
		  	<label for="channel">文章频道</label> 
			<select class="custom-select custom-select-sm mb-3" id="channel"  name="channelId">
			  <option value="0">请选择</option>
			  <c:forEach items="${channels}" var="channel">
			  		<option value="${channel.id}">${channel.name}</option>
			  </c:forEach>
			</select>
			<label for="category">文章分类</label> 
			<select class="custom-select custom-select-sm mb-3" id="category" name="categoryId">
			</select>
			
			<label for="category">文章标签</label> 
				<input name="tags" size="50"/>
			
		</div>
		
		<div class="form-group row" >
			<button type="button" class="btn btn-success" onclick="addColumn()">添加</button>
			<button type="button" class="btn btn-success" onclick="publish()">发布</button>
		</div>
	</form>

</body>


<script type="text/javascript">


//发布文章
function publish(){
	     
		//序列化表单数据带文件
		 var formData = new FormData($( "#form" )[0]);
		//改变formData的值
		// alert(editor1.html())// 是富文本的内容
		 formData.set("content",editor1.html());
		$.ajax({
			type:"post",
			data:formData,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			url:"/user/publishImg",
			success:function(obj){
				if(obj.result == 1){
					alert(obj.errorMsg)
					$("#content").load($(".user li:eq(0)").attr("data")); 
				}else{
					alert(obj.errorMsg)
				}
			}
		})
	}

	$("#channel").change(function(){
		$.post(
			"<%=request.getContextPath()%>/user/getChannelId",
			{channelId:$(this).val()},
			function(data){
				if(data.result == 1){
					$("#category").empty();
					$("#category").append("<option value='-1'>请选择</option>");
					for ( var i in data.data) {
						$("#category").append("<option value="+data.data[i].id+">"+data.data[i].name+"</option>");
					}
				}else{
					alert(data.errorMsg);				
				}
			},
			"json"
		);
	})
	
	function addColumn(){
		var str = "<br/><label for='title'>选择图片和添加标签</label><input type='file' class='form-control' id='file' name='file'>"+
		"<input type='text' class='form-control' id='desc' name='desc'>";
		$("#img").append(str);
	}
	
</script>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
</html>