<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>论文分配管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var thesisIds = "";
			//保存所选的id存入thesisIds中
			$("#fenpeiAll").click(function(){
				$("input[name='thesisId']:checked").each(  
						function(){
							if($(this).val() != "undefined"){
								thesisIds += $(this).val() + ",";
							}
						}
					) 
				$("#distributeForm").attr("action","${ctx}/jwcmis/distribute/fenpeiAll?thesisIds=" + thesisIds);
				$("#distributeForm").submit();
			});
			
			$("#allFenpei").click(function(){
				$("#distributeForm").attr("action","${ctx}/jwcmis/distribute/allFenpei");
				$("#distributeForm").submit();
			});
				
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function selectall(){
			if($('input[name="thesisId"]').get(0).checked){
				$('input[name="thesisId"]').attr("checked",false); 
			}else{
				$('input[name="thesisId"]').attr("checked",true); 
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/jwcmis/distribute/">论文分配列表</a></li>
	</ul>
	<form:form id="distributeForm" modelAttribute="distribute" action="${ctx}/jwcmis/distribute/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">		
			<li><label>专业：</label>
				<form:input path="major" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			
			<li><label>姓名：</label>
				<form:input path="masterName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>学号：</label>
				<form:input path="studentID" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>分配状态：</label>
				<form:select path="status" class="input-xlarge required">
					<form:option value="" label="请选择分配状态"/>
					<form:option value="0">未分配</form:option>
					<form:option value="1">已分配未评审</form:option>
					<form:option value="2">已评审</form:option>
				</form:select>

			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="btns">
				<input  id="fenpeiAll" class="btn btn-primary" type="button" value="批量分配论文"/>
			</li>
			<li class="btns">
				<input id="allFenpei" class="btn btn-primary" type="button" value="全部分配论文"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;"><a style="cursor:pointer;" onclick="selectall()">全选</a></th>
				
				<th>专业</th>
				<th>硕士名</th>
				<th>论文主题</th>
				<th>学号</th>
				<th>关键字</th>
				<th>分配状态</th>
				<shiro:hasPermission name="jwcmis:distribute:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="distribute">
			<tr>
				<td style="text-align:center;">
					<input type="checkbox" name="thesisId" value="${distribute.thesisid}"/>
				</td>
				<td>
					${distribute.major}
				</td>
				<td>
					${distribute.masterName}
				</td>
				<td>
					${distribute.topic}
				</td>
				<td>
					${distribute.studentID}
				</td>
				<td>
					${distribute.keywords}
				</td>
				<td>
					${fns:getDictLabel(distribute.status, 'thesisStatus', '')}
				</td>
				<shiro:hasPermission name="jwcmis:distribute:edit">
					<td>
    					<a href="${ctx}/jwcmis/distribute/fenpei?id=${distribute.thesisid}">分配</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>