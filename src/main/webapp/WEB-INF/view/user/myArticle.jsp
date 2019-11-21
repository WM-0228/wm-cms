<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	<table class="table">
	  <caption style="color:pink;">用户文章管理界面</caption>
		  <thead>
		    <tr>
		      <th>id</th>
		      <th>标题</th>
		      <th>频道</th>
		      <th>分类</th>
		      <th>发布日期</th>
		      <th>状态</th>
		      <th>操作</th>
		     </tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${info.list }" var="article">
		    	<tr class="active">
			      <td>${article.id }</td>
			      <td>${article.title }</td>
			      <td>${article.channel.name }</td>
			      <td>${article.category.name }</td>
			      <td><fmt:formatDate value="${article.created }" pattern="yyyy年MM月dd日"/></td>
			      <td>${article.status == 0 ? '待审核' :  article.status == 1 ? '审核通过' : article.status == 2 ? '审核被拒' : '未知'}</td>
			      <td>
			      	<input type="button" onclick="userUpdateArticle('/user/toUserUpdateArticle?articleId=${article.id }')" value="修改" class="btn-info">
			      	<input type="button" onclick="deleteArticle('${article.id}')" value="删除" class="btn-danger">
			      </td>
			     </tr>
		    </c:forEach>
		  </tbody>
	</table>
		<div align="center">
			
			<ul class="pagination">
			    <li><a href="javascript:goPage('/user/myArticle?userId=${sessionScope.SESSION_USER_KEY.id}&pageNum=${info.prePage}')">&laquo;</a></li>
			    <c:forEach begin="${info.pageNum-2 > 1 ? info.pageNum-2:1}" end="${info.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">    		
			    	<c:if test="${info.pageNum!=index.index}">
			    		<li><a href="javascript:goPage('/user/myArticle?userId=${sessionScope.SESSION_USER_KEY.id}&pageNum=${index.index}')">${index.index}</a></li>
			    	</c:if>
			    	<c:if test="${info.pageNum==index.index}">
			    		<li><a href="javascript:goPage('/user/myArticle?userId=${sessionScope.SESSION_USER_KEY.id}&pageNum=${index.index}')"><strong> ${index.index} </strong> </a></li>
			    	</c:if>
			    </c:forEach>
			    <li><a href="javascript:goPage('/user/myArticle?userId=${sessionScope.SESSION_USER_KEY.id}&pageNum=${info.nextPage}')">&raquo;</a></li>
			</ul>
		</div>
		
		<script>
		
			function goPage(url){
				$("#content").load(url);
			}
		
			function userUpdateArticle(url){
				$("#content").load(url);
			}
			
			function deleteArticle(id){
				$.post(
					"<%=request.getContextPath()%>/user/deleteArticle",
					{id:id},
					function(data){
						if(data.result == 1){
							alert(data.errorMsg);
							$("#content").load("/user/myArticle?userId=${sessionScope.SESSION_USER_KEY.id}");
						}else{
							alert(data.errorMsg);
						}
					},
					"json"
				);
			}
			
		</script>
