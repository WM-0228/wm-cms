<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <style>
    	span{
    		color:red;
    	}
    </style>
	
	<form:form modelAttribute="link" method="post" id="form">
		名称:<form:input path="name"/><form:errors path="name"/><br/>
		地址:<form:input path="url"/><form:errors path="url"/><br/>
		<input type="button" onclick="commit()" value="添加"/>
	</form:form>
	
	
	<script>
		function commit(){
			$.post(
				"<%=request.getContextPath()%>/link/addLink",
				$("#form").serialize(),
				function(html){
					$("#content").html(html);
				}
				
			);
		}
	</script>