<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>邮件模板管理</title>
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
		<li class="active"><a href="${ctx}/gsmis/email/">邮件模板列表</a></li>
		<shiro:hasPermission name="gsmis:email:edit"><li><a href="${ctx}/gsmis/email/form">邮件模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="email" action="${ctx}/gsmis/email/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模板名</th>
				<th>内容</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="gsmis:email:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="email">
			<tr>
				<td><a href="${ctx}/gsmis/email/form?id=${email.id}">
					${email.name}
				</a></td>
				<td>
					${email.text}
				</td>
				<td>
					${fns:getDictLabel(email.status, 'emailStatus', '')}
				</td>
				<td>
					<fmt:formatDate value="${email.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${email.remarks}
				</td>
				<shiro:hasPermission name="gsmis:email:edit"><td>
    				<a href="${ctx}/gsmis/email/form?id=${email.id}">修改</a>
					<a href="${ctx}/gsmis/email/delete?id=${email.id}" onclick="return confirmx('确认要删除该邮件模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>