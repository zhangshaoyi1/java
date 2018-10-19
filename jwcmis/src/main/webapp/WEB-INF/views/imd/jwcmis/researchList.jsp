<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>研究方向信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出研究方向信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/jwcmis/research/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/jwcmis/research/");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
    		});
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/jwcmis/research/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/jwcmis/research/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/jwcmis/research/">研究方向信息列表</a></li>
		<shiro:hasPermission name="jwcmis:research:edit"><li><a href="${ctx}/jwcmis/research/form">研究方向信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="research" action="${ctx}/jwcmis/research/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>研究方向名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id = "btnExport" class = "btn btn-primary" type = "button" value = "导出"/>
				<input id = "btnImport" class = "btn btn-primary" type = "button" value = "导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>研究方向名</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="jwcmis:research:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="research">
			<tr>
				<td><a href="${ctx}/jwcmis/research/form?id=${research.id}">
					${research.name}
				</a></td>
				<td>
					<fmt:formatDate value="${research.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${research.remarks}
				</td>
				<shiro:hasPermission name="jwcmis:research:edit"><td>
    				<a href="${ctx}/jwcmis/research/form?id=${research.id}">修改</a>
					<a href="${ctx}/jwcmis/research/delete?id=${research.id}" onclick="return confirmx('确认要删除该研究方向信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>