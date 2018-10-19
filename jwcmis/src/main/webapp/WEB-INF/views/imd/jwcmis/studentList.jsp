<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出学生信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/jwcmis/student/export");
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
		<form id="importForm" action="${ctx}/jwcmis/student/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/jwcmis/student/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/jwcmis/student/">学生信息列表</a></li>
		<shiro:hasPermission name="jwcmis:student:edit"><li><a href="${ctx}/jwcmis/student/form">学生信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/jwcmis/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学生姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>学号：</label>
				<form:input path="studentnum" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>导师名：</label>
				<form:input path="supervisor" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>学院：</label>
				<form:input path="department" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学生姓名</th>
				<th>学号</th>
				<th>导师名</th>
				<th>学院</th>
				<th>专业</th>
				<th>邮箱</th>
				<th>手机号码</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="jwcmis:student:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<td><a href="${ctx}/jwcmis/student/form?id=${student.id}">
					${student.name}
				</a></td>
				<td>
					${student.studentnum}
				</td>
				<td>
					${student.supervisor}
				</td>
				<td>
					${student.department}
				</td>
				<td>
					${student.major}
				</td>
				<td>
					${student.email}
				</td>
				<td>
					${student.phone}
				</td>
				<td>
					<fmt:formatDate value="${student.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${student.remarks}
				</td>
				<shiro:hasPermission name="jwcmis:student:edit"><td>
    				<a href="${ctx}/jwcmis/student/form?id=${student.id}">修改</a>
					<a href="${ctx}/jwcmis/student/delete?id=${student.id}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>