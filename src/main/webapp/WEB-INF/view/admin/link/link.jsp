<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="table">
			<caption>链接列表</caption>
			<thead>
				<tr>
					<th>id</th>
					<th>链接地址</th>
					<th>连接名称</th>
					<th>创建时间</th>
					<th colspan="2">操作【<button onclick="add()">添加</button>】</th>
				</tr>
			</thead> 
			
			<tbody>
				<c:forEach items="${info.list }" var="link">
					<tr>
						<td>${link.id }</td>
						<td>${link.url }</td>
						<td>${link.name }</td>
						<td><fmt:formatDate value="${link.created }" pattern="yyyy年MM月dd日"/></td>
						<td><button>修改</button></td>
						<td><button>删除</button></td>
				</c:forEach>
			</tbody>
			
			
		</table>
		
		<script>
			function add(){
				$("#content").load("/link/addLink");
			}
		</script>