<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信模板管理</title>
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
		<li class="active"><a href="${ctx}/jwcmis/phone/">短信模板列表</a></li>
		<shiro:hasPermission name="jwcmis:phone:edit"><li><a href="${ctx}/jwcmis/phone/form">短信模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="phone" action="${ctx}/jwcmis/phone/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模板名</th>
				<th>内容</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="jwcmis:phone:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="phone">
			<tr>
				<td><a href="${ctx}/jwcmis/phone/form?id=${phone.id}">
					${phone.name}
				</a></td>
				<td>
					${phone.text}
				</td>
				<td>
					<fmt:formatDate value="${phone.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${phone.remarks}
				</td>
				<shiro:hasPermission name="jwcmis:phone:edit"><td>
    				<a href="${ctx}/jwcmis/phone/form?id=${phone.id}">修改</a>
					<a href="${ctx}/jwcmis/phone/delete?id=${phone.id}" onclick="return confirmx('确认要删除该短信模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>