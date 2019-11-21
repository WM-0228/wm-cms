<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	<table class="table">
	  <caption style="color:pink;">管理员文章管理界面</caption>
		  <thead>
		    <tr>
		      <th>id</th>
		      <th style="width: 30%;">标题</th>
		      <th>频道</th>
		      <th>分类</th>
		      <th>发布日期</th>
		      <th>热门</th>
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
			      <td><font style="color:${article.hot == 1 ? 'red' : null}">${article.hot == 1 ? '热门文章' : '冷门文章'}</font></td>
			      <td><font style="color:${article.status == 0 ? null :  article.status == 1 ? 'red' : article.status == 2 ? 'green' : 'pink'}">${article.status == 0 ? '待审核' :  article.status == 1 ? '审核通过' : article.status == 2 ? '审核被拒' : '未知'}</font></td>
			      <td>
			      	<input type="button" onclick="detailArticle('${article.id}')" value="修改" class="btn-info">
			      	<input type="button" onclick="deleteArticle('${article.id}')" value="删除" class="btn-danger">
			      </td>
			     </tr>
		    </c:forEach>
		  </tbody>
	</table>
	<div align="center">
			
			<ul class="pagination">
			    <li><a href="javascript:goPage('/admin/articles?pageNum=${info.prePage}')">&laquo;</a></li>
			    <c:forEach begin="${info.pageNum-2 > 1 ? info.pageNum-2:1}" end="${info.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">    		
			    	<c:if test="${info.pageNum!=index.index}">
			    		<li><a href="javascript:goPage('/admin/articles?pageNum=${index.index}')">${index.index}</a></li>
			    	</c:if>
			    	<c:if test="${info.pageNum==index.index}">
			    		<li><a href="javascript:goPage('/admin/articles?pageNum=${index.index}')"><strong> ${index.index} </strong> </a></li>
			    	</c:if>
			    </c:forEach>
			    <li><a href="javascript:goPage('/admin/articles?pageNum=${info.nextPage}')">&raquo;</a></li>
			</ul>
		</div>
		
		
		<!-- 模态框 -->
		<div class="modal fade" id="identifier" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		        <div class="modal-dialog" style="width: 1000px;height: 1000px;">
		                <div class="modal-content">
		                        <div class="modal-header">
		                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                                <h4 class="modal-title" id="myModalLabel">审核文章</h4>
		                        </div>
		                        <!-- 用于文章展示 -->
		                        <div class="modal-body" style="overflow-y:scroll;overflow-x:scroll;">
									<h3 id="title"></h3><br>
									<div id="info"></div><br>
									<div id="contentInfo"></div>
								</div>
		                        <div class="modal-footer">
		                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                                
		                                <button type="button" class="btn btn-primary" onclick="approved(1)">通过</button>
										<!-- 表示应谨慎采取的动作 -->
										<button type="button" class="btn btn-warning" onclick="approved(2)">拒绝</button>
										<!-- 表示一个成功的或积极的动作 -->
										<button type="button" class="btn btn-success" onclick="approved(3)">热门</button>
										<!-- 信息警告消息的上下文按钮 -->
										<button type="button" class="btn btn-info" onclick="approved(4)">冷门</button>
		                        </div>
		                </div><!-- /.modal-content -->
		        </div><!-- /.modal-dialog -->
		</div>
		<script>
			function goPage(url){
				$("#content").load(url);
			}
			
			function deleteArticle(id){
				$.post(
					"<%=request.getContextPath()%>/user/deleteArticle",
					{id:id},
					function(data){
						if(data.result == 1){
							alert(data.errorMsg);
							$("#content").load("/admin/articles?pageNum=${info.pageNum}");
						}else{
							alert(data.errorMsg);
						}
					},
					"json"
				);
			}
			var articleId = "";
			function detailArticle(id){
				$('#identifier').modal('show');
				$.post(
					"<%=request.getContextPath()%>/admin/detailArticle",
					{id:id},
					function(data){
						if(data.result == 1){
							$("#title").text(data.data.title);
							$("#info").text("作者 : "+data.data.user.username+
									"     频道        : "+data.data.channel.name+
									"     分类        : "+data.data.category.name);
							$("#contentInfo").html(data.data.content);
							articleId = data.data.id;
						}else{
							
						}
					},
					"json"
				);
			}
			
			
			function approved(status){
				$.post(
					"<%=request.getContextPath()%>/admin/updateStatus",
					{status:status,id:articleId},
					function(data){
						if(data.result == 1){
							alert(data.errorMsg);
							$('#identifier').modal('hide')
						}else{
							alert(data.errorMsg);
						}
					},
					"json"
				);
			}
			
			$('#identifier').on('hidden.bs.modal', function () {
				$("#content").load("/admin/articles?pageNum=${info.pageNum}");
			})
			
		</script>
