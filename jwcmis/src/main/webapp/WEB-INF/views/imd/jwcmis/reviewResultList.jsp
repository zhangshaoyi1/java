<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评审结果管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/jwcmis/reviewResult/">评审结果列表</a></li>
		<shiro:hasPermission name="jwcmis:reviewResult:edit"><li><a href="${ctx}/jwcmis/reviewResult/form">评审结果添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reviewResult" action="${ctx}/jwcmis/reviewResult/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>论文ID</th>
				<th>专家ID</th>
				<th>分数</th>
				<th>评语</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="jwcmis:reviewResult:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reviewResult">
			<tr>
				<td><a href="${ctx}/jwcmis/reviewResult/form?id=${reviewResult.id}">
					${reviewResult.thesisid}
				</a></td>
				<td>
					${reviewResult.expertid}
				</td>
				<td>
					${reviewResult.score}
				</td>
				<td>
					${reviewResult.comment}
				</td>
				<td>
					<fmt:formatDate value="${reviewResult.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${reviewResult.remarks}
				</td>
				<shiro:hasPermission name="jwcmis:reviewResult:edit"><td>
    				<a href="${ctx}/jwcmis/reviewResult/form?id=${reviewResult.id}">修改</a>
					<a href="${ctx}/jwcmis/reviewResult/delete?id=${reviewResult.id}" onclick="return confirmx('确认要删除该评审结果吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>