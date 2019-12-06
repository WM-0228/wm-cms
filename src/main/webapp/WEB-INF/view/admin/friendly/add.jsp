<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
    	<form:form action="/friendly/add" method="post" modelAttribute="friendly">
    		友情链接文本:<form:input path="text"/><br>
    		友情链接地址:<form:input path="url"/><form:errors path="url"/><br>
    		<form:button>添加</form:button>
    	</form:form>
    </div>
</body>
</html>