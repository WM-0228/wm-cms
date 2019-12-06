<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<table class="table">
		<c:forEach items="${collects }" var="collect" varStatus="index">
			<tr>
				<td><a href="javascript:articleLook(${collect.article.id })">${index.count}、${collect.article.title }</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<script>
		function articleLook(articleId){
			window.open("/article/lookDetail?id="+articleId);
		}
	</script>