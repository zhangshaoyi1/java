<%@page import="cn.edu.ccnu.imd.jwcmis.service.ExpertService"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专家信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出专家信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/jwcmis/expert/export");
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
		<form id="importForm" action="${ctx}/jwcmis/expert/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/jwcmis/expert/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/jwcmis/expert/">专家信息列表</a></li>
		<shiro:hasPermission name="jwcmis:expert:edit"><li><a href="${ctx}/jwcmis/expert/form">专家信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="expert" action="${ctx}/jwcmis/expert/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>专家姓名：</label>
				<form:input path="expertName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>专业：</label>
				<form:input path="major" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>研究方向：</label>
				<form:input path="researchName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="phone" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>专家类型：</label>
				<form:radiobuttons path="expertType" items="${fns:getDictList('expertType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>专家名</th>
				<th>专业</th>
				<th>研究方向</th>
				<th>专家类型</th>
				<th>已分配论文数</th>				
				<th>邮箱</th>
				<th>手机号</th>
				<th>单位</th>
				<th>备注信息</th>
				<shiro:hasPermission name="jwcmis:expert:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="expert">
			<tr>
				<td><a href="${ctx}/jwcmis/expert/form?id=${expert.id}">
					${expert.expertName}
				</a></td>
				<td>
					${expert.major}
				</td>
				<td >
					${expert.researchName}
				</td>
				<td>
					${expert.expertType}
				</td>
				<td>
					${expert.distributeNum}
				</td>
				<td>
					${expert.email}
				</td>
				<td>
					${expert.phone}
				</td>
				<td>
					${expert.place}
				</td>
				<td>
					${expert.remarks}
				</td>
				<shiro:hasPermission name="jwcmis:expert:edit"><td>
    				<a href="${ctx}/jwcmis/expert/form?id=${expert.id}">修改</a>
					<a href="${ctx}/jwcmis/expert/delete?id=${expert.id}" onclick="return confirmx('确认要删除该专家信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>