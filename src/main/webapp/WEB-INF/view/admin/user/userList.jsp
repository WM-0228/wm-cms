<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">输入姓名</a>
			</div>
			
			<div>
		        <form class="navbar-form navbar-left" role="search">
		            <div class="form-group">
		                <input type="text" value="${name }" class="form-control" placeholder="Search" id="searchName">
		            </div>
		            <input type="button" class="btn btn-default" value="查询" onclick="search()"/>
		        </form>
		    </div>
		</div>
	</nav>
	
	
	<div class="table-responsive">
		<table class="table">
			<caption>用户列表</caption>
			<thead>
				<tr>
					<th>用户id</th>
					<th>用户名称</th>
					<th>注册日期</th>
					<th>生日</th>
					<th>角色</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead> 
			
			<tbody>
				<c:forEach items="${info.list }" var="user">
					${user.locked == 1 ? '<tr class="active">' : '<tr>' }
						<td>${user.id }</td>
						<td>${user.username }</td>
						<td><fmt:formatDate value="${user.createTime }" pattern="yyyy年MM月dd日"/></td>
						<td><fmt:formatDate value="${user.birthday }" pattern="yyyy年MM月dd日"/></td>
						<td>${user.role == 0 ? '注册用户' : user.role == 1 ? '管理员' : '未知' }</td>
						<td>${user.locked == 1 ? '禁用' : '正常' }</td>
						<td>
							<c:if test="${user.locked==1 }">
								<input type="button" class="btn btn-warning" value="解除" onclick="updateState('${user.id}',0)"/>
							</c:if>
							<c:if test="${user.locked!=1}">
								<input type="button" class="btn btn-danger" value="禁用" onclick="updateState('${user.id}',1)"/>
							</c:if>
						</td> 
					</tr>
				</c:forEach>
			</tbody>
			
			
		</table>
	</div>

	 <ul class="pagination">
		 	
		    <li class=""><a href="javascript:goPage(${info.pageNum == 1 ? 1 : info.pageNum - 1 })" >&laquo;</a></li>
		    
		    <c:if test="${info.pages == 5 }">
				<c:forEach var="count" varStatus="index" begin="1" end="5">
				    <li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
				 </c:forEach>
		    </c:if>
		    
		    <c:if test="${info.pages != 5 }">
			 	<c:choose>
			 		<c:when test="${info.pageNum > 2 && info.pageNum < (info.pages -1) }">
					    <c:forEach var="count" varStatus="index" begin="${info.pageNum -2}" end="${info.pageNum + 2}">
					    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
					    </c:forEach>
			 		</c:when>
			 		<c:when test="${info.pageNum == 2 }">
					    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 1}" end="${info.pageNum + 3}">
					    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
					    </c:forEach>
			 		</c:when>
			 		<c:when test="${info.pageNum == 1 }">
					    <c:forEach var="count" varStatus="index" begin="${info.pageNum}" end="${info.pageNum + 4}">
					    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
					    </c:forEach>
			 		</c:when>
			 		<c:when test="${info.pageNum == info.pages }">
					    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 4}" end="${info.pages}">
					    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
					    </c:forEach>
			 		</c:when>
			 		<c:when test="${info.pageNum == (info.pages - 1)}">
					    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 3}" end="${info.pageNum +1 }">
					    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
					    </c:forEach>
			 		</c:when>
			 	</c:choose>
			 </c:if>
		    <li><a href="javascript:goPage(${info.pageNum == info.pages ? info.pageNum : info.pageNum + 1 })">&raquo;</a></li>
		</ul>
	
	
	<script type="text/javascript">
		function updateState(userId,locked){
			$.post(
				"<%=request.getContextPath()%>/admin/upUserLocked",
				{userId:userId,locked:locked},
				function(data) {
					if(data.result == 1){
						alert(data.errorMsg);
						$("#content").load("/admin/users?pageNum=${info.pageNum}&name=${name}");
					}else{
						alert(data.errorMsg)
					}
				},
				"json"
			);
		}
		
		function goPage(pageNum){
			var url = "/admin/users?pageNum="+pageNum+"&name=${name}";
			$("#content").load(url);
		}
		
		function search(){
			var url = "/admin/users?name="+$("#searchName").val();
			$("#content").load(url);
		}
	
</script>