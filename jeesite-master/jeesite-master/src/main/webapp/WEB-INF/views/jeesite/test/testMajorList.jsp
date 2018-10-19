<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专业管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				//提示框
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/test/testMajor/export");
						$("#searchForm").submit();
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
		<form id="importForm" action="${ctx}/test/testMajor/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/testMajor/">专业管理列表</a></li>
		<shiro:hasPermission name="test:testMajor:edit"><li><a href="${ctx}/test/testMajor/form">专业管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testMajor" action="${ctx}/test/testMajor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学院名</th>
				<th>专业名</th>
				<th>专业编号</th>
				<th>专业性质</th>
				<th>是否师范</th>
				<th>创建日期</th>
				<th>更新日期</th>
				<th>标记</th>
				<shiro:hasPermission name="test:testMajor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testMajor">
			<tr>
				<td>
					${testMajor.schoolname}
				</a></td>
				<td>
					${testMajor.name}
				</a></td>
				<td>
					${testMajor.zydm}
				</a></td>
				<td>
					${fns:getDictLabel(testMajor.zyxz, 'ZYXZ', '')}
				</td>
				<td>
					${fns:getDictLabel(testMajor.sfxz, 'SFXZ', '')}
				</td>
				<td>
					<fmt:formatDate value="${testMajor.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${testMajor.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${testMajor.remarks}
				</td>
				<shiro:hasPermission name="test:testMajor:edit"><td>
    				<a href="${ctx}/test/testMajor/form?id=${testMajor.id}">修改</a>
					<a href="${ctx}/test/testMajor/delete?id=${testMajor.id}" onclick="return confirmx('确认要删除该专业管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>