<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = (String)request.getAttribute("content1") != null ? (String)request.getAttribute("content1") : "";

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章发布</title>
<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css"',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		
		
		
		function query(){
		  alert(editor1.html())
			//alert( $("[name='content1']").attr("src"))
		} 
	</script>
</head>
<body>
 
				  
	<form action="" id="form">
		<div class="form-group row ">
			<input type="hidden" name="id" value="${article.id }">
			<label for="title">文章标题</label> <input type="text"
				class="form-control" id="title" name="title" placeholder="请输入标题" value="${article.title }">
		</div>


		<div class="form-group row ">
			<textarea name="content1" cols="100" rows="8"
				style="width: 860px; height: 250px; visibility: hidden;" ><%=htmlData%></textarea>
			<br />
		</div>
		<div class="form-group row ">
			<label for="title">文章标题图片</label> <input type="file"
				class="form-control" id="file" name="file">
		</div>
		<div class="form-group row ">
		  	<label for="channel">文章频道</label> 
			<select class="custom-select custom-select-sm mb-3" id="channel"  name="channelId">
			  <option value="0">请选择</option>
			  <c:forEach items="${channels}" var="channel">
			  		<option value="${channel.id}" ${channel.id == article.channel.id ? 'selected' : null }>${channel.name}</option>
			  </c:forEach>
			</select>
			<label for="category">文章分类</label> 
			<select class="custom-select custom-select-sm mb-3" id="category" name="categoryId">
			</select>
			
			<label for="category">文章标签</label> 
				<input name="tags" size="50"/>
			
		</div>
		
		<div class="form-group row" >
		<button type="button" class="btn btn-success" onclick="publish()">发布</button>
		
		</div>
	</form>

</body>


<script type="text/javascript">

	$(function(){
		var id = $("#channel").val();
		var categoryId = "${article.category.id}";
		$.post(
				"<%=request.getContextPath()%>/user/getChannelId",
				{channelId:id},
				function(data){
					if(data.result == 1){
						$("#category").empty();
						$("#category").append("<option value='-1'>请选择</option>");
						for ( var i in data.data) {
							if(data.data[i].id == categoryId){
								$("#category").append("<option value="+data.data[i].id+" selected>"+data.data[i].name+"</option>");
							}else{
								$("#category").append("<option value="+data.data[i].id+">"+data.data[i].name+"</option>");
							}
						}
					}else{
						alert(data.errorMsg);				
					}
				},
				"json"
			);
	})
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
			url:"/user/userUpdateArticle",
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